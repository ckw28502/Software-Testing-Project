/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyek;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 *
 * @author User
 */
public abstract class Action {
    private static Actions action=null;
    
    public static void initAction(WebDriver driver){
        if (action==null) {
            action=new Actions(driver);
        }
    }
    
    public static void Scroll(WebElement element){
        action.scrollToElement(element);
    }
    
    public static void Click(WebElement element){
        action.click(element);
    }
    
    public static void buildPerform(WebElement element){
        action.build().perform();
    }
}
