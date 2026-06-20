public class BuilderTest {
    public static void main(String[] args) {
        System.out.println("=== Exercise 3: Builder Pattern ===");

        // Basic computer (only required parameters, defaults for optional)
        Computer basicComp = new Computer.Builder("Intel i3", "8GB").build();
        System.out.println("--- Basic Computer Configuration ---");
        System.out.println(basicComp);

        // Gaming computer (custom optional parameters)
        Computer gamingComp = new Computer.Builder("Intel i9", "32GB")
                .setStorage("2TB NVMe SSD")
                .setGraphicsCardEnabled(true)
                .setBluetoothEnabled(true)
                .build();
        System.out.println("--- Gaming Computer Configuration ---");
        System.out.println(gamingComp);

        // Office computer (custom storage and bluetooth but no graphics card)
        Computer officeComp = new Computer.Builder("AMD Ryzen 5", "16GB")
                .setStorage("512GB SSD")
                .setBluetoothEnabled(true)
                .build();
        System.out.println("--- Office Computer Configuration ---");
        System.out.println(officeComp);
    }
}
