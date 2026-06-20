public class Light {
    private String roomName;

    public Light(String roomName) {
        this.roomName = roomName;
    }

    public void turnOn() {
        System.out.println("The light in the " + roomName + " is ON.");
    }

    public void turnOff() {
        System.out.println("The light in the " + roomName + " is OFF.");
    }
}
