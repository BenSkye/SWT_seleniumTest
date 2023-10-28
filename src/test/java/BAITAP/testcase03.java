package BAITAP;

import driver.driverFactory;
import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/*
---------------------------------------TC03---------------------------------------

Test Steps:

1. Go to http://live.techpanda.org/

2. Click on �MOBILE� menu

3. In the list of all mobile , click on �ADD TO CART� for Sony Xperia mobile

4. Change �QTY� value to 1000 and click �UPDATE� button. Expected that an error is displayed

"The requested quantity for "Sony Xperia" is not available.

5. Verify the error message

6. Then click on �EMPTY CART� link in the footer of list of all mobiles. A message "SHOPPING CART IS EMPTY" is shown.

7. Verify cart is empty */
public class testcase03 {
    @Test
    public void testCaseRun() {
        // Initialize the WebDriver
        WebDriver driver = new ChromeDriver();

        try {
            // Step 1: Go to "http://live.techpanda.org/"
            driver.get("http://live.techpanda.org/");

            // Step 2: Click on 'MOBILE' menu
            WebElement mobileMenu = driver.findElement(By.xpath("//a[normalize-space()='Mobile']"));
            mobileMenu.click();

            // Step 3: Click on "ADD TO CART" for Sony Xperia mobile
            WebElement addToCartButton = driver
                    .findElement(By.xpath("//a[contains(text(),'Sony Xperia')]/../following-sibling::div/button"));
            addToCartButton.click();

            // Step 4: Change "QTY" value to 1000 and click "UPDATE" button
            WebElement qtyInput = driver.findElement(By.xpath("//input[@title='Qty']"));
            qtyInput.clear();
            qtyInput.sendKeys("1000");

            WebElement updateButton = driver.findElement(By.xpath("//button[@title='Update']"));
            updateButton.click();

            // Step 5: Verify the error message
            WebElement errorMessage = driver.findElement(By.xpath("//li[@class='error-msg']"));
            String expectedErrorMessage = "The requested quantity for \"Sony Xperia\" is not available.";
            String actualErrorMessage = errorMessage.getText();

            boolean isMessage = expectedErrorMessage.equals(actualErrorMessage);
            org.junit.Assert.assertEquals("Message update is not true", true, isMessage);

            // Step 6: Click on "EMPTY CART" link
            WebElement emptyCartLink = driver.findElement(By.xpath("//a[normalize-space()='EMPTY CART']"));
            emptyCartLink.click();

            // Step 7: Verify cart is empty
            WebElement emptyCartMessage = driver.findElement(By.xpath("//p[@class='cart-empty']"));
            String expectedEmptyCartMessage = "SHOPPING CART IS EMPTY";
            String actualEmptyCartMessage = emptyCartMessage.getText();

            boolean isMessageWhenEmpty = actualEmptyCartMessage.equals(expectedEmptyCartMessage);
            org.junit.Assert.assertEquals("Message empty is not true", true, isMessageWhenEmpty);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Quit the browser session
            driver.quit();
        }
    }
}
