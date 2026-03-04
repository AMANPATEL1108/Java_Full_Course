package com.example.Databases_System_Design_09.api.serviceImpl;

import com.example.Databases_System_Design_09.api.dto.request.BookingRequest;
import com.example.Databases_System_Design_09.api.dto.response.BookingResponse;
import com.example.Databases_System_Design_09.api.entity.*;
import com.example.Databases_System_Design_09.api.enumTypes.PaymentMethod;
import com.example.Databases_System_Design_09.api.enumTypes.PaymentStatus;
import com.example.Databases_System_Design_09.api.exception.ResourceNotFoundException;
import com.example.Databases_System_Design_09.api.exception.SeatUnavailableException;
import com.example.Databases_System_Design_09.api.repository.*;
import com.example.Databases_System_Design_09.api.service.BookingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ShowRepository showRepository;
    private final SeatRepository seatRepository;
    private final BookedSeatRepository bookedSeatRepository;
    private final PaymentRepository paymentRepository;

    public BookingServiceImpl(BookingRepository bookingRepository,
                              UserRepository userRepository,
                              ShowRepository showRepository,
                              SeatRepository seatRepository,
                              BookedSeatRepository bookedSeatRepository,
                              PaymentRepository paymentRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.seatRepository = seatRepository;
        this.bookedSeatRepository = bookedSeatRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    @Transactional
    public BookingResponse bookTicket(BookingRequest request) {

        // Step 1: Validate user
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + request.getUserId()));

        // Step 2: Validate show
        MovieShow show = showRepository.findById(request.getShowId())
                .orElseThrow(() -> new ResourceNotFoundException("Show not found with id: " + request.getShowId()));

        // Step 3: Validate seats & check availability
        List<Seat> seats = new ArrayList<>();
        for (Long seatId : request.getSeatIds()) {
            Seat seat = seatRepository.findById(seatId)
                    .orElseThrow(() -> new ResourceNotFoundException("Seat not found with id: " + seatId));

            // Ensure seat belongs to the correct screen
            if (!seat.getScreen().getId().equals(show.getScreen().getId())) {
                throw new IllegalArgumentException("Seat " + seat.getSeatNumber() + " does not belong to this show's screen");
            }

            // Check seat availability (not booked and not locked)
            boolean alreadyBooked = bookedSeatRepository.existsByShowAndSeatAndStatusNot(show, seat, "CANCELLED");
            if (alreadyBooked) {
                throw new SeatUnavailableException("Seat " + seat.getSeatNumber() + " is already booked or locked");
            }

            seats.add(seat);
        }

        // Step 4: Calculate total price
        double totalPrice = show.getPrice() * seats.size();

        // Step 5: Create booking with PENDING status
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setShow(show);
        booking.setTotalPrice(totalPrice);
        booking.setStatus("PENDING");
        bookingRepository.save(booking);

        // Step 6: Lock the seats
        for (Seat seat : seats) {
            BookedSeat bookedSeat = new BookedSeat();
            bookedSeat.setBooking(booking);
            bookedSeat.setShow(show);
            bookedSeat.setSeat(seat);
            bookedSeat.setStatus("LOCKED");
            bookedSeatRepository.save(bookedSeat);
        }

        // Step 7: Process payment (simulated)
        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setAmount(totalPrice);
        payment.setPaymentMethod(PaymentMethod.valueOf(request.getPaymentMethod().toUpperCase()));
        payment.setPaymentTime(LocalDateTime.now());

        // Simulate payment success (in real app, integrate payment gateway)
        payment.setStatus(PaymentStatus.SUCCESS);
        paymentRepository.save(payment);

        // Step 8: Confirm booking and update seat status
        if (payment.getStatus() == PaymentStatus.SUCCESS) {
            booking.setStatus("CONFIRMED");
            bookingRepository.save(booking);

            // Update booked seat status to CONFIRMED
            bookedSeatRepository.findByShow(show).forEach(bs -> {
                if (bs.getBooking().getId().equals(booking.getId())) {
                    bs.setStatus("CONFIRMED");
                    bookedSeatRepository.save(bs);
                }
            });
        } else {
            booking.setStatus("PAYMENT_FAILED");
            bookingRepository.save(booking);
            throw new IllegalArgumentException("Payment processing failed");
        }

        return mapToBookingResponse(booking, seats, payment);
    }

    @Override
    public List<BookingResponse> getBookingsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        return bookingRepository.findByUser(user).stream()
                .map(booking -> {
                    List<Seat> seats = bookedSeatRepository.findByShowAndStatusNot(booking.getShow(), "CANCELLED")
                            .stream()
                            .filter(bs -> bs.getBooking().getId().equals(booking.getId()))
                            .map(BookedSeat::getSeat)
                            .collect(Collectors.toList());

                    Payment payment = paymentRepository.findByBooking(booking).orElse(null);
                    return mapToBookingResponse(booking, seats, payment);
                })
                .collect(Collectors.toList());
    }

    @Override
    public BookingResponse getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id));

        List<Seat> seats = bookedSeatRepository.findByShowAndStatusNot(booking.getShow(), "CANCELLED")
                .stream()
                .filter(bs -> bs.getBooking().getId().equals(booking.getId()))
                .map(BookedSeat::getSeat)
                .collect(Collectors.toList());

        Payment payment = paymentRepository.findByBooking(booking).orElse(null);
        return mapToBookingResponse(booking, seats, payment);
    }

    @Override
    @Transactional
    public void cancelBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id));

        if ("CANCELLED".equals(booking.getStatus())) {
            throw new IllegalArgumentException("Booking is already cancelled");
        }

        // Release seats
        bookedSeatRepository.findByShow(booking.getShow()).forEach(bs -> {
            if (bs.getBooking().getId().equals(booking.getId())) {
                bs.setStatus("CANCELLED");
                bookedSeatRepository.save(bs);
            }
        });

        booking.setStatus("CANCELLED");
        bookingRepository.save(booking);

        // Update payment status
        paymentRepository.findByBooking(booking).ifPresent(payment -> {
            payment.setStatus(PaymentStatus.FAILED);
            paymentRepository.save(payment);
        });
    }

    private BookingResponse mapToBookingResponse(Booking booking, List<Seat> seats, Payment payment) {
        BookingResponse response = new BookingResponse();
        response.setId(booking.getId());
        response.setUuid(booking.getUuid());
        response.setUserName(booking.getUser().getName());
        response.setMovieTitle(booking.getShow().getMovie().getTitle());
        response.setScreenName(booking.getShow().getScreen().getName());
        response.setTheatreName(booking.getShow().getScreen().getTheatre().getName());
        response.setShowStartTime(booking.getShow().getStartTime());
        response.setSeatNumbers(seats.stream().map(Seat::getSeatNumber).collect(Collectors.toList()));
        response.setTotalPrice(booking.getTotalPrice());
        response.setStatus(booking.getStatus());
        response.setPaymentStatus(payment != null ? payment.getStatus().name() : "UNKNOWN");
        return response;
    }
}