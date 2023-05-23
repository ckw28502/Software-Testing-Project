/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author User
 */
public class Driver {
    private WebDriver driver;
    private Actions action;
    private WebDriverWait wait;
    private static Driver d=null;
    public  Driver(String url,int wait) {
        System.setProperty("webdriver.chrome.driver","D:\\Software Testing\\Chromedriver\\chromedriver.exe");
        ChromeOptions option=new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
        driver=new ChromeDriver(option);
        driver.manage().window().maximize();
        driver.get(url);
        action=new Actions(driver);
        this.wait=new WebDriverWait(driver, Duration.ofMinutes(1));
    }

    public static void initDriver(String url,int wait){
        if (d==null) {
            d=new Driver(url,wait);
        }
    }
    public static void Click(String by){
        WebElement element=d.getElementId(by);
        element.click();
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
}
