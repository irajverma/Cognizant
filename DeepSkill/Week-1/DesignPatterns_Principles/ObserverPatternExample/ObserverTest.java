public class ObserverTest {
    public static void main(String[] args) {
        System.out.println("=== Exercise 7: Observer Pattern ===");

        StockMarket googleStock = new StockMarket("GOOGL", 175.50);

        Observer mobileApp1 = new MobileApp("StockTrader");
        Observer mobileApp2 = new MobileApp("WealthManager");
        Observer webDashboard = new WebApp("GoogleFinance");

        // Register observers
        googleStock.registerObserver(mobileApp1);
        googleStock.registerObserver(mobileApp2);
        googleStock.registerObserver(webDashboard);

        // Change price -> notifies all
        googleStock.setPrice(178.20);

        System.out.println();

        // Deregister one observer and update price
        googleStock.deregisterObserver(mobileApp2);
        googleStock.setPrice(180.40);
    }
}
