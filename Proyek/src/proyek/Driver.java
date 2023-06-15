/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyek;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
        // Path Calvin Kwan
       String path="D:\\Software Testing\\Chromedriver\\chromedriver.exe";       
        // Path Ariel
       // String path="D:\\SoftwareTesting\\chromedriver\\chromedriver.exe";
//          Path timot
        // String path="C:\\Users\\HP\\Documents\\Kuliah\\sem6\\st\\chromedriver_win32\\chromedriver.exe";
        // Path victor
        //String path="D:\\Kuliah\\sem6\\Software Testing\\uas\\uas\\chromedriver.exe";
        
        System.setProperty("webdriver.chrome.driver",path);
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
    
    public static void waitDone(){
        d.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@data-notify='container']")));
        d.wait.until(ExpectedConditions.invisibilityOf(getElement("//div[@data-notify='container']")));
    }
    
    public static void waitClick(String by){
        d.wait.until(ExpectedConditions.elementToBeClickable(getElement(by)));
    }
    
    public static void waitEmpty(String by,String attribute){
        d.wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBeNotEmpty(getElement(by),attribute)));
    }
    
    public static void waitNumberMore(By by, int attribute){
        d.wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(by, 2));
    }
    
    public static void SelectItem(String by, int id){
        Select select=new Select((getElement(by)));
        select.selectByIndex(id);
    }
    
    public static void SelectItem(String by, String value){
        Select select=new Select((getElement(by)));
        try {
            select.selectByValue(value);
        } catch (Exception e) {
            select.selectByVisibleText(value);
        }
    }
    
    public static void SelectItemByIndex(String by, int value){
        Select select=new Select((getElement(by)));
        select.selectByIndex(value);
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
            return d.getElementTag(classname);
        }    
    }
    
    private WebElement getElementTag(String tagname) {
        try {
            return driver.findElement(By.tagName(tagname));
        } catch (Exception e) {
            return d.getElementCSS(tagname);
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
