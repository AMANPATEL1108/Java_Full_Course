package miniProject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Abstract base class
abstract class Employee {
    Long id;
    String name;
    String department;
    String role;
    Integer baseSalary;

    public Employee(Long id, String name, String department, String role, Integer baseSalary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.role = role;
        this.baseSalary = baseSalary;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public String getRole() { return role; }
    public Integer getBaseSalary() { return baseSalary; }

    public void setRole(String role) { this.role = role; }
    public void setBaseSalary(Integer baseSalary) { this.baseSalary = baseSalary; }

    public abstract int calculateSalary();

    public void displayDetails() {
        System.out.println("ID: " + id +
                ", Name: " + name +
                ", Dept: " + department +
                ", Role: " + role +
                ", Salary: " + calculateSalary());
    }
}

// Manager class
class Manager extends Employee {
    public Manager(Long id, String name, String department, Integer baseSalary) {
        super(id, name, department, "Manager", baseSalary);
    }

    @Override
    public int calculateSalary() {
        return baseSalary + 10000; // Example bonus
    }
}

// Developer class
class Developer extends Employee {
    public Developer(Long id, String name, String department, Integer baseSalary) {
        super(id, name, department, "Developer", baseSalary);
    }

    @Override
    public int calculateSalary() {
        return baseSalary + 5000;
    }
}

// Intern class
class Intern extends Employee {
    public Intern(Long id, String name, String department, Integer baseSalary) {
        super(id, name, department, "Intern", baseSalary);
    }

    @Override
    public int calculateSalary() {
        return baseSalary; // No bonus
    }
}

// Main Employee Management System
public class EmployeeMangement {
    static List<Employee> employeeList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Employee Management ---");
            System.out.println("1. Display All Employees");
            System.out.println("2. Add Employee");
            System.out.println("3. Promote Employee");
            System.out.println("4. List by Department");
            System.out.println("5. Exit");
            System.out.print("Choose: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    displayAll();
                    break;
                case 2:
                    addEmployee(sc);
                    break;
                case 3:
                    promoteEmployee(sc);
                    break;
                case 4:
                    listByDepartment(sc);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    static void displayAll() {
        for (Employee e : employeeList) {
            e.displayDetails();
        }
    }

    static void addEmployee(Scanner sc) {
        System.out.print("Enter ID: ");
        Long id = sc.nextLong();
        sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Department: ");
        String dept = sc.nextLine();

        System.out.print("Enter Role (Manager/Developer/Intern): ");
        String role = sc.nextLine();

        System.out.print("Enter Base Salary: ");
        int baseSalary = sc.nextInt();

        Employee newEmp = null;
        switch (role.toLowerCase()) {
            case "manager":
                newEmp = new Manager(id, name, dept, baseSalary);
                break;
            case "developer":
                newEmp = new Developer(id, name, dept, baseSalary);
                break;
            case "intern":
                newEmp = new Intern(id, name, dept, baseSalary);
                break;
            default:
                System.out.println("Invalid role!");
                return;
        }

        employeeList.add(newEmp);
        System.out.println("Employee added.");
    }

    static void promoteEmployee(Scanner sc) {
        System.out.print("Enter Employee ID to promote: ");
        Long id = sc.nextLong();

        for (int i = 0; i < employeeList.size(); i++) {
            Employee e = employeeList.get(i);
            if (e.getId().equals(id)) {
                System.out.print("Enter New Role (Manager/Developer): ");
                sc.nextLine();
                String newRole = sc.nextLine();
                System.out.print("Enter New Base Salary: ");
                int newSalary = sc.nextInt();

                Employee promoted = null;
                switch (newRole.toLowerCase()) {
                    case "manager":
                        promoted = new Manager(e.getId(), e.getName(), e.getDepartment(), newSalary);
                        break;
                    case "developer":
                        promoted = new Developer(e.getId(), e.getName(), e.getDepartment(), newSalary);
                        break;
                    default:
                        System.out.println("Invalid promotion role.");
                        return;
                }

                employeeList.set(i, promoted);
                System.out.println("Employee promoted.");
                return;
            }
        }

        System.out.println("Employee not found.");
    }

    static void listByDepartment(Scanner sc) {
        System.out.print("Enter Department to filter: ");
        String dept = sc.nextLine();

        for (Employee e : employeeList) {
            if (e.getDepartment().equalsIgnoreCase(dept)) {
                e.displayDetails();
            }
        }
    }
}
