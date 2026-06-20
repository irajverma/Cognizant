public class CommandTest {
    public static void main(String[] args) {
        System.out.println("=== Exercise 9: Command Pattern ===");

        // Receivers
        Light livingRoomLight = new Light("Living Room");
        Light kitchenLight = new Light("Kitchen");

        // Commands
        Command livingRoomLightOn = new LightOnCommand(livingRoomLight);
        Command livingRoomLightOff = new LightOffCommand(livingRoomLight);
        Command kitchenLightOn = new LightOnCommand(kitchenLight);
        Command kitchenLightOff = new LightOffCommand(kitchenLight);

        // Invoker
        RemoteControl remote = new RemoteControl();

        // Turn Living Room Light On
        remote.setCommand(livingRoomLightOn);
        remote.pressButton();

        // Turn Living Room Light Off
        remote.setCommand(livingRoomLightOff);
        remote.pressButton();

        System.out.println();

        // Turn Kitchen Light On
        remote.setCommand(kitchenLightOn);
        remote.pressButton();

        // Turn Kitchen Light Off
        remote.setCommand(kitchenLightOff);
        remote.pressButton();
    }
}
