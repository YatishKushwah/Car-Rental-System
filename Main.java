import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Car {
    private String registrationNumber;
    private String make;
    private boolean available;

    public Car(String registrationNumber, String make) {
        this.registrationNumber = registrationNumber;
        this.make = make;
        this.available = true;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getMake() {
        return make;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

class RentalSystem {
    private List<Car> cars;

    public RentalSystem() {
        this.cars = new ArrayList<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void displayAvailableCars() {
        System.out.println("Available Cars:");
        for (Car car : cars) {
            if (car.isAvailable()) {
                System.out.println("Registration Number: " + car.getRegistrationNumber() +
                        ", Make: " + car.getMake());
            }
        }
    }

    public Car rentCar(String registrationNumber) {
        for (Car car : cars) {
            if (car.getRegistrationNumber().equals(registrationNumber) && car.isAvailable()) {
                car.setAvailable(false);
                return car;
            }
        }
        return null; // Car not found or not available
    }

    public void returnCar(Car car) {
        car.setAvailable(true);
        System.out.println("Car with Registration Number " + car.getRegistrationNumber() + " has been returned.");
    }
}

public class Main {
    public static void main(String[] args) {
        RentalSystem rentalSystem = new RentalSystem();

        Car car1 = new Car("ABC123", "Toyota");
        Car car2 = new Car("XYZ789", "Honda");

        rentalSystem.addCar(car1);
        rentalSystem.addCar(car2);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Display available cars");
            System.out.println("2. Rent a car");
            System.out.println("3. Return a car");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    rentalSystem.displayAvailableCars();
                    break;
                case 2:
                    System.out.print("Enter the registration number of the car to rent: ");
                    String regNumberToRent = scanner.next();
                    Car rentedCar = rentalSystem.rentCar(regNumberToRent);
                    if (rentedCar != null) {
                        System.out.println("Car with Registration Number " + rentedCar.getRegistrationNumber() + " has been rented.");
                    } else {
                        System.out.println("Car not available or not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter the registration number of the car to return: ");
                    String regNumberToReturn = scanner.next();
                    Car returnedCar = rentalSystem.rentCar(regNumberToReturn);
                    if (returnedCar != null) {
                        rentalSystem.returnCar(returnedCar);
                    } else {
                        System.out.println("Car not found.");
                    }
                    break;
                case 4:
                    System.out.println("Exiting the system.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
