package testCase;

public interface TestManager {
    void addProductToCart(String productURL);
    double getTotalPrice(String cartUrl);
    double  getDisplayTotalPrice(String cartUrl);
    void clickBanner(String homeUrl);
}