package testsuite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WomenTest extends Utility {
    String baseUrl = "https://magento.softwaretestingboard.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyTheSortByProductNameFilter()throws InterruptedException {
        //3.1* Mouse Hover on Women Menu
        //* Mouse Hover on Tops
        //* Click on Jackets
        //* Select Sort By filter “Product Name” * Verify the products name display in
        //alphabetical order
        mouseHoverOnElement(By.xpath("//a[@id='ui-id-4']//span[contains(text(),'Women')]"));
        mouseHoverOnElement(By.xpath("//a[@id='ui-id-9']"));
        mouseHoverAndClickOnElement(By.xpath("//a[@id='ui-id-11']//span[contains(text(),'Jackets')]"));
        List<WebElement> beforeSelectionList = driver.findElements(By.xpath("//strong[@class='product name product-item-name']"));
        List<String> beforeSelectElementList1 = new ArrayList<>();
        for (WebElement product : beforeSelectionList) {
            beforeSelectElementList1.add(String.valueOf(product.getText()));
        }
        selectFromDropDownMenu(By.xpath("//div[2]//div[3]//select[1]"), "Product Name");
        List<WebElement> afterSelectElementList = driver.findElements(By.xpath("//strong[@class='product name product-item-name']"));
        List<String> afterSelectElementList1 = new ArrayList<>();
        for (WebElement list : afterSelectElementList) {
            afterSelectElementList1.add(String.valueOf(list.getText()));
        }
        Collections.sort(beforeSelectElementList1);
        Assert.assertEquals("Product is not displayed ", beforeSelectElementList1, afterSelectElementList1);

    }

    @Test
    public void verifyTheSortByPriceFilter()throws InterruptedException {
        /*
1.  Mouse Hover on Women Menu
2. Mouse Hover on Tops
3. Click on Jackets
4.Select Sort By filter “Price” * Verify the products price display inLow to High
         */
        Thread.sleep(2000);
        mouseHoverOnElement(By.xpath("//a[@id='ui-id-4']//span[contains(text(),'Women')]"));
        mouseHoverOnElement(By.xpath("//a[@id='ui-id-9']"));
        mouseHoverAndClickOnElement(By.xpath("//a[@id='ui-id-11']//span[contains(text(),'Jackets')]"));
        List<WebElement> beforePriceSelectedList =driver.findElements(By.xpath("//span[@class='price-wrapper ']"));
        List<String> beforePriceSelectedList1 = new ArrayList<>();
        for (WebElement price:beforePriceSelectedList) {
            beforePriceSelectedList1.add(String.valueOf(price.getText()));
        }
        selectFromDropDownMenu(By.xpath("//div[2]//div[3]//select[1]"),"Price");
       // clickOnElement(By.xpath("//div[@class='column main']//div[2]//div[3]//a[1]"));
        List<WebElement> afterPriceSelectElementList = driver.findElements(By.xpath("//span[@class='price-wrapper ']"));
        List<String> afterSelectElementList1 = new ArrayList<>();
        for (WebElement list : afterPriceSelectElementList) {
            afterSelectElementList1.add(String.valueOf(list.getText()));
        }
        Collections.sort(beforePriceSelectedList1);
      //  Collections.reverse(beforePriceSelectedList1);

        verifyElementMethod(beforePriceSelectedList1.toString(),afterSelectElementList1.toString());

    }


    @After
    public void tearDown() {
        driver.close();
    }
}
