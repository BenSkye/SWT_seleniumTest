package BAITAP;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import driver.driverFactory;

import java.time.Duration;

public class testcase02 {
    @Test
    public void runTestCase() {
        // 1. Initialize the WebDriver
        WebDriver driver = driverFactory.getChromeDriver();

        try {
            // 1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");

            // 2. Click on 'MOBILE' menu
            WebElement mobileMenu = driver.findElement(By.xpath("//a[normalize-space()='Mobile']"));
            mobileMenu.click();

            // 3. Read the cost of Sony Xperia mobile
            WebElement sonyXperiaPriceElement = driver.findElement(By.xpath("//a[contains(text(),'Sony Xperia')]/../following-sibling::div/p"));
            String sonyXperiaPrice = sonyXperiaPriceElement.getText();

            // 4. Click on Sony Xperia mobile
            WebElement sonyXperiaLink = driver.findElement(By.xpath("//a[contains(text(),'Sony Xperia')]"));
            sonyXperiaLink.click();

            // 5. Read the Sony Xperia mobile from the detail page
            WebElement sonyXperiaDetailPriceElement = driver.findElement(By.xpath("//span[@id='our_price_display']"));
            String sonyXperiaDetailPrice = sonyXperiaDetailPriceElement.getText();

            // 6. Compare the product value in the list and details page
            if (sonyXperiaPrice.equals(sonyXperiaDetailPrice)) {
                System.out.println("Product value in list and details page is equal: " + sonyXperiaPrice);
            } else {
                System.out.println("Product value in list and details page is not equal.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Quit the browser session
            driver.quit();
        }
    }
}