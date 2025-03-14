import java.util.ArrayList;
import java.util.Scanner;

abstract class Employee {
    private int id;
    private String name;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    abstract double calculateSalary();

    @Override
    public String toString() {
        return "Employee[name=" + name + ", id=" + id + ", salary=" + calculateSalary() + "]";
    }
}

class FulltimeEmployee extends Employee {
    private double monthlySalary;

    public FulltimeEmployee(int id, String name, double monthlySalary) {
        super(id, name);
        this.monthlySalary = monthlySalary;
    }

    @Override
    double calculateSalary() {
        return monthlySalary;
    }
}

class PartTimeEmployee extends Employee {
    private int hoursWorked;
    private double hourlyRate;

    public PartTimeEmployee(int id, String name, int hoursWorked, double hourlyRate) {
        super(id, name);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    double calculateSalary() {
        return hoursWorked * hourlyRate;
    }
}

class PayrollSystem {
    private ArrayList<Employee> employeeList;

    public PayrollSystem() {
        employeeList = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    public void removeEmployee(int id) {
        Employee employeeToRemove = null;
        for (Employee employee : employeeList) {
            if (employee.getId() == id) {
                employeeToRemove = employee;
                break;
            }
        }
        if (employeeToRemove != null) {
            employeeList.remove(employeeToRemove);
        }
    }

    public void updateEmployee(int id, Employee newEmployee) {
        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).getId() == id) {
                employeeList.set(i, newEmployee);
                break;
            }
        }
    }

    public void display() {
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
    }

    public Employee createEmployee(Scanner scanner, String type) {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        if (type.equalsIgnoreCase("fulltime")) {
            System.out.print("Enter Monthly Salary: ");
            double monthlySalary = scanner.nextDouble();
            return new FulltimeEmployee(id, name, monthlySalary);
        } else {
            System.out.print("Enter Hours Worked: ");
            int hoursWorked = scanner.nextInt();
            System.out.print("Enter Hourly Rate: ");
            double hourlyRate = scanner.nextDouble();
            return new PartTimeEmployee(id, name, hoursWorked, hourlyRate);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        PayrollSystem pr = new PayrollSystem();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Add Employee");
            System.out.println("2. Remove Employee");
            System.out.println("3. Update Employee");
            System.out.println("4. Display Employees");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Employee Type (fulltime/parttime): ");
                    String type = scanner.next();
                    Employee employee = pr.createEmployee(scanner, type);
                    pr.addEmployee(employee);
                    System.out.println("Employee added.");
                    break;
                case 2:
                    System.out.print("Enter Employee ID to remove: ");
                    int removeId = scanner.nextInt();
                    pr.removeEmployee(removeId);
                    System.out.println("Employee removed.");
                    break;
                case 3:
                    System.out.print("Enter Employee ID to update: ");
                    int updateId = scanner.nextInt();
                    System.out.print("Enter new Employee Type (fulltime/parttime): ");
                    String newType = scanner.next();
                    Employee newEmployee = pr.createEmployee(scanner, newType);
                    pr.updateEmployee(updateId, newEmployee);
                    System.out.println("Employee updated.");
                    break;
                case 4:
                    pr.display();
                    break;
                case 5:
                    scanner.close();
                    System.out.println("Exiting program.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        }
    }
}