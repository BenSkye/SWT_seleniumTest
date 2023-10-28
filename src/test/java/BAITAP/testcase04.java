package BAITAP;

import driver.driverFactory;
import junit.framework.Assert;

import java.util.concurrent.TimeUnit;

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
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/*-------------------------------------------------- -TC04------------------------------------------

Kịch bản tiếp theo là “Xác minh rằng bạn có thể so sánh hai sản phẩm”

Điều này sẽ cần bạn làm việc với cửa sổ bật lên.

Các bước kiểm tra:

1. Truy cập http://live.techpanda.org/

2. Nhấp vào menu �DI ĐỘNG�

3. Trong danh sách sản phẩm di động, nhấp vào �Thêm để so sánh� cho 2 điện thoại di động (Sony Xperia & Iphone)

4. Nhấp vào nút �SO SÁNH�. Một cửa sổ bật lên mở ra

5. Xác minh cửa sổ bật lên và kiểm tra xem sản phẩm có được phản ánh trong đó không

Tiêu đề “SO SÁNH SẢN PHẨM” với các sản phẩm được chọn trong đó.

6. Đóng cửa sổ bật lên

*/

public class testcase04 {
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

            // Step 3: Click on "Add To Compare" for 2 mobiles (Sony Xperia & iPhone)
            WebElement sonyXperiaCompareButton = driver.findElement(By.xpath(
                    "//a[contains(text(),'Sony Xperia')]/../following-sibling::div[@class='actions']/button[@title='Compare']"));
            sonyXperiaCompareButton.click();

            WebElement iPhoneCompareButton = driver.findElement(By.xpath(
                    "//a[contains(text(),'IPhone')]/../following-sibling::div[@class='actions']/button[@title='Compare']"));
            iPhoneCompareButton.click();

            // Step 4: Click on "COMPARE" button. A popup window opens
            WebElement compareButton = driver.findElement(By.xpath("//button[@title='Compare']"));
            compareButton.click();

            // Step 5: Verify the pop-up window and check that the products are reflected in
            // it
            long time = 20000;
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));

            String mainWindowHandle = driver.getWindowHandle();

            for (String handle : driver.getWindowHandles()) {
                if (!handle.equals(mainWindowHandle)) {
                    driver.switchTo().window(handle);
                    break;
                }
            }

            WebElement compareHeading = driver.findElement(By.xpath("//h1[normalize-space()='COMPARE PRODUCTS']"));
            String expectMess = "COMPARE PRODUCTS";
            boolean isMessage = compareHeading.equals(expectMess);
            org.junit.Assert.assertEquals("Message is not true", true, isMessage);

            // Step 6: Close the Popup Window
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
            driver.close();
            driver.switchTo().window(mainWindowHandle);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Quit the browser session
            driver.quit();

        }
    }
}
