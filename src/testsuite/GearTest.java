package testsuite;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

public class GearTest extends Utility {
    String baseUrl = "https://magento.softwaretestingboard.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }
    @Test
    public void userShouldAddProductSuccessFullyToShoppingCart()throws InterruptedException{
        mouseHoverOnElement(By.xpath("//a[@id='ui-id-6']//span[@class='ui-menu-icon ui-icon ui-icon-carat-1-e']"));
        mouseHoverAndClickOnElement(By.xpath("//span[normalize-space()='Bags']"));
        Thread.sleep(2000);
        clickOnElement(By.xpath("//a[normalize-space()='Overnight Duffle']"));

        String expectedMessage ="Overnight Duffle";
        WebElement actualMessage = driver.findElement(By.xpath("//span[@class='base']"));
        String actualMessage1 = actualMessage.getText();
        verifyElementMethod(expectedMessage,actualMessage1);

        driver.findElement(By.xpath("//input[@id='qty']")).clear();
        sendTextToElement(By.xpath("//input[@id='qty']"),"3");
        clickOnElement(By.xpath("//button[@id='product-addtocart-button']"));

        String expectedMessageAdded = "You added Overnight Duffle to your shopping cart.";
        WebElement actualMessageAdded = driver.findElement(By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']"));
        String actualMessageAdded1 = actualMessageAdded.getText();
        verifyElementMethod(expectedMessageAdded,actualMessageAdded1);
        Thread.sleep(2000);
        clickOnElement(By.xpath("//a[normalize-space()='shopping cart']"));
        String expectedMessageBag = "Overnight Duffle";
        WebElement actualMessageBag = driver.findElement(By.xpath("//td[@class='col item']//a[normalize-space()='Overnight Duffle']"));
        String actualMessageBag1 = actualMessageBag.getText();
        verifyElementMethod(expectedMessageBag,actualMessageBag1);

        WebElement actualQTY1 = driver.findElement(By.cssSelector(".input-text.qty"));
        String actualQty=  actualQTY1.getAttribute("value");
        String expectedQty= "3";
        verifyElementMethod(expectedQty,actualQty);

        String expectedMessagePrice = "$135.00";
        WebElement actualMessagePrice = driver.findElement(By.xpath("//span[@class='cart-price']//span[@class='price'][normalize-space()='$135.00']"));
        String actualMessagePrice1 = actualMessagePrice.getText();
        verifyElementMethod(expectedMessagePrice,actualMessagePrice1);


        WebElement element2 = driver.findElement(By.cssSelector(".input-text.qty"));
        element2.clear();
        sendTextToElement(By.cssSelector(".input-text.qty"),"5");

        Thread.sleep(2000);
        clickOnElement(By.xpath("//span[normalize-space()='Update Shopping Cart']"));


        //  * Verify the product price ‘$225.00’
        String expectedPrice1= "$225.00";
        String actualPrice1=getTextFromElement(By.xpath("//span[@class='cart-price']//span[@class='price'][normalize-space()='$225.00']"));
        verifyElementMethod(expectedPrice1,actualPrice1);

    }


    @After
    public void tearDown() {
       driver.close();
    }


}
