class Car {
    private String make;
    private String model;
    private int year;

    public Car(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public void displayDetails() {
        System.out.println("Car Details: " + year + " " + make + " " + model);
    }
}

public class Problem_17_ClassAndObjectCreation {
    public static void main(String[] args) {
        Car car1 = new Car("Toyota", "Corolla", 2022);
        Car car2 = new Car("Tesla", "Model 3", 2025);
        
        car1.displayDetails();
        car2.displayDetails();
    }
}