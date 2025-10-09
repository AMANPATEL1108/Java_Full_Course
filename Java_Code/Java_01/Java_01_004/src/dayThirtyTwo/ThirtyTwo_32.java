package dayThirtyTwo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class ThirtyTwo_32 {
    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        LocalDate start = LocalDate.of(2020, 1, 1);
        LocalDate end = LocalDate.of(2025, 10, 9);

        long daysBetween = ChronoUnit.DAYS.between(start, end);
        System.out.println("Days between: " + daysBetween);

        LocalTime tstart = LocalTime.of(10, 30, 15);
        LocalTime tend = LocalTime.of(13, 45, 50);

        long secondsBetween = ChronoUnit.SECONDS.between(tstart, tend);

        long hours = secondsBetween / 3600;
        long minutes = (secondsBetween % 3600) / 60;
        long seconds = secondsBetween % 60;

        String formattedTime = String.format("%02d:%02d:%02d", hours, minutes, seconds);

        System.out.println("Time between: " + formattedTime);

        LocalDateTime initialDateTime = LocalDateTime.of(2023, 10, 9, 14, 30);

        System.out.println("Initial Date-Time: " + initialDateTime);

        LocalDateTime updatedDateTime = initialDateTime.plusDays(10)
                .plusHours(3)
                .plusMinutes(30);
        System.out.println("After adding 10 days, 3 hours, and 30 minutes: " + updatedDateTime);

        LocalDateTime finalDateTime = updatedDateTime.minusDays(5);
        System.out.println("After subtracting 5 days: " + finalDateTime);

        String inputDate = "2025-10-09";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate givenDate = LocalDate.parse(inputDate, formatter);

            LocalDate currentDate = LocalDate.now();

            if (givenDate.isBefore(currentDate)) {
                System.out.println("The date is in the past");
            } else if (givenDate.isAfter(currentDate)) {
                System.out.println("The date is in the future");
            } else {
                System.out.println("The date is today");
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
        }
    }
}
