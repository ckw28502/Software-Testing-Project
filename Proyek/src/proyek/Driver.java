/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyek;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author User
 */
public class Driver {
    private WebDriver driver;
    private WebDriverWait wait;
    private static Driver d=null;
    public  Driver(String url,int wait) {
        System.setProperty("webdriver.chrome.driver","D:\\Software Testing\\Chromedriver\\chromedriver.exe");
        ChromeOptions option=new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
        driver=new ChromeDriver(option);
        driver.manage().window().maximize();
        driver.get(url);
        this.wait=new WebDriverWait(driver, Duration.ofSeconds(wait));
        Action.initAction(driver);
    }

    public static void initDriver(String url,int wait){
        if (d==null) {
            d=new Driver(url,wait);
        }
    }
    
    public static void Click(String by){
        WebElement element=getElement(by);
        element.click();
    }
    
    public static void Type(String by, String text){
        WebElement element=getElement(by);
        element.sendKeys(text);
    }
    
    public static void waitVisibility(String by){
        d.wait.until(ExpectedConditions.visibilityOf(getElement(by)));
    }
    
    public static void waitPresence(By by){
        d.wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
    
    public static void SelectItem(String by, int id){
        Select select=new Select((getElement(by)));
        select.selectByIndex(id);
    }
    
    public static void SelectItem(String by, String value){
        Select select=new Select((getElement(by)));
        select.selectByValue(value);
    }
    
    public static WebElement getElement(String by){
        return d.getElementId(by);
    }

    private WebElement getElementId(String id) {
        try {
            return driver.findElement(By.id(id));
        } catch (Exception e) {
            return d.getElementLinkText(id);
        }
    }

    private WebElement getElementLinkText(String text) {
        try {
            return driver.findElement(By.linkText(text));
        } catch (Exception e) {
            return d.getElementClass(text);
        }
    }

    private WebElement getElementClass(String classname) {
        try {
            return driver.findElement(By.className(classname));
        } catch (Exception e) {
            return d.getElementCSS(classname);
        }    
    }

    private WebElement getElementCSS(String css) {
        try {
            return driver.findElement(By.cssSelector(css));
        } catch (Exception e) {
            return d.getElementXPath(css);
        }  
    }

    private WebElement getElementXPath(String xpath) {
       return driver.findElement(By.xpath(xpath));
    }

    public WebDriver getDriver() {
        return driver;
    }
    
}