package testCase;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class testCase implements TestManager {
    private WebDriver driver;

    public static void main(String[] args) {
        testCase test = new testCase();
        test.runTest();
    }

    public void runTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        addProductToCart("https://hb-np-mnhk-cdn-ms.azureedge.net/centrum-gut-and-immunity-probiotics-1-5g-x-30pcs/p/321265");
        addProductToCart("https://hb-np-mnhk-cdn-ms.azureedge.net/loacker-quadratini-napolitaner-wafer-250g/p/839878");
        addProductToCart("https://hb-np-mnhk-cdn-ms.azureedge.net/kotex-fresh-breathable-long-liner-twin-pack-40pcs-x-2bags/p/812818");

        double total = getTotalPrice("https://hb-np-mnhk-cdn-ms.azureedge.net/cart");
        System.out.printf("%.2f\n", total);

        double totalDisplay = getDisplayTotalPrice("https://hb-np-mnhk-cdn-ms.azureedge.net/cart");
        if (total == totalDisplay) {
            System.out.println("Total Price Test Case Passed");
        } else {
            System.out.println("Total Price Test Case Failed");
        }

        //clickBanner(Homeurl)
        clickBanner("https://www.mannings.com.hk/");

    }

    @Override
    public void addProductToCart(String productURL) {
        driver.navigate().to(productURL);
        driver.findElement(By.id("addToCartButton")).click();
    }

    @Override
    public double getTotalPrice(String cartUrl) {
        driver.navigate().to(cartUrl);
        double total = 0.0;

        for (int i = 1; i <= 3; i++) {

            String xpathPriceA = String.format("/html/body/main/div[2]/div[10]/div[4]/div[3]/div[1]/div[5]/ul/div[%d]/div[2]/div[2]", i);
            String xpathPriceB = String.format("/html/body/main/div[2]/div[10]/div[4]/div[3]/div[1]/div[5]/ul/div[%d]/div[2]/div[2]/span", i);
            try {
                String price = driver.findElement(By.xpath(xpathPriceB)).getText();
                total += Double.parseDouble(price.replace("$", ""));
            } catch (NoSuchElementException e) {
                String price = driver.findElement(By.xpath(xpathPriceA)).getText();
                total += Double.parseDouble(price.replace("$", ""));
            }
        }

        return total;
    }
    @Override
    public double getDisplayTotalPrice(String cartUrl) {
        driver.navigate().to(cartUrl);
        String displayPrice = driver.findElement(By.xpath("/html/body/main/div[2]/div[10]/div[4]/div[3]/div[1]/div[5]/div[1]/div[3]/b")).getText();
        String displayTotalPrice = displayPrice.replace("$", "");
        return Double.parseDouble(displayTotalPrice);
    }

    @Override
    public void clickBanner(String homeUrl) {

        System.out.println("Banner Name: ");

        for (int i = 1; i <= 5; i++) {
            driver.navigate().to(homeUrl);
            String Xpath = String.format("/html/body/main/div[2]/div[11]/div[2]/div[3]/div[2]/div/div/div[%d]/div/div/div[1]/a/div[2]/div", i);
            driver.findElement(By.xpath(Xpath)).click();
            String bannerName = driver.findElement(By.xpath("/html/body/main/div[2]/div[11]/div[2]/ol/li[2]/span")).getText();
            if (bannerName != null)
                System.out.println(bannerName);
            else
                System.out.println("Banner Test Case Failed");
        }

        for (int i = 1; i <= 4; i++) {
            driver.navigate().to(homeUrl);
            String Xpath = String.format("/html/body/main/div[2]/div[11]/div[2]/div[3]/div[2]/div/div/div[%d]/div/div/div[2]/a/div[1]", i);

            driver.findElement(By.xpath(Xpath)).click();
            String bannerName = driver.findElement(By.xpath("/html/body/main/div[2]/div[11]/div[2]/ol/li[2]/span")).getText();
            try {
                if (bannerName != null)
                    System.out.println(bannerName);
                else
                    System.out.println("Banner Test Case Failed");
            }
            catch (NoSuchElementException e) {
                System.out.println("Banner Test Case Failed");
            }


        }
    }


}