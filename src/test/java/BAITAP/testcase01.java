package BAITAP;

import driver.driverFactory;
import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class testcase01 {
    @Test
    public void runTestCase() {
        // 1. Initialize the WebDriver
        WebDriver driver = driverFactory.getChromeDriver();

        // 2. Navigate to the website
        driver.get("http://live.techpanda.org/");

        // 3. Verify the Title of the page
        String expectedTitle = "Home page";
        String actualTitle = driver.getTitle();
        org.junit.Assert.assertEquals(expectedTitle, actualTitle);
        
        // 4. Find and click on the 'MOBILE' menu
        WebElement mobileMenu = driver.findElement(By.xpath("//a[normalize-space()='Mobile']"));
        mobileMenu.click();

        // 5. In the list of all mobile, select SORT BY -> dropdown as name
        Select sortByDropdown = new Select(driver.findElement(By.cssSelector("select[title='Sort By']")));
        sortByDropdown.selectByVisibleText("Name");

        // 6. Verify all products are sorted by name
        // You can add an assertion to verify that the products are sorted by name.

        try {
            // 7. Add your test actions for verification here.
            List<WebElement> productElements = driver
                    .findElements(By.cssSelector("h2[class='product-name'] a[title='Sony']"));

            // Get the names of the products in the default order
            List<String> defaultOrder = productElements.stream()
                    .map(WebElement::getText)
                    .collect(Collectors.toList());

            // Sort the names in ascending order
            List<String> sortedOrder = new ArrayList<>(defaultOrder);
            Collections.sort(sortedOrder);
            // Verify that the product names are sorted correctly
            boolean isSortedAscending = sortedOrder.equals(defaultOrder);
            org.junit.Assert.assertEquals("Product names are not sorted in ascending order", true, isSortedAscending);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 7. Quit the browser session
        driver.quit();
    }
}