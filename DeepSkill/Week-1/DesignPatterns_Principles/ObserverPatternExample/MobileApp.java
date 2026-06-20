public class MobileApp implements Observer {
    private String appName;

    public MobileApp(String appName) {
        this.appName = appName;
    }

    @Override
    public void update(String stockName, double price) {
        System.out.println("[Mobile App - " + appName + "] Notification: Stock " + stockName + " is now $" + price);
    }

    @Override
    public String toString() {
        return "MobileApp{" + "appName='" + appName + '\'' + '}';
    }
}
