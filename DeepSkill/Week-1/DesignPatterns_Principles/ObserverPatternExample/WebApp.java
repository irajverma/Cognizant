public class WebApp implements Observer {
    private String webAppName;

    public WebApp(String webAppName) {
        this.webAppName = webAppName;
    }

    @Override
    public void update(String stockName, double price) {
        System.out.println("[Web App - " + webAppName + "] Dashboard: Stock " + stockName + " updated to $" + price);
    }

    @Override
    public String toString() {
        return "WebApp{" + "webAppName='" + webAppName + '\'' + '}';
    }
}
