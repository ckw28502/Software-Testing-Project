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
public class Action {
    private static Actions action=null;
    
    public static void initAction(WebDriver driver){
        if (action==null) {
            action=new Actions(driver);
        }
    }
    
    public static void Scroll(WebElement element){
        action.scrollToElement(element);
    }
    public static void mouseMove(int x, int y){
        action.moveByOffset(x,y).perform();
    }
    
    public static void moveTo(WebElement element){
        action.moveToElement(element);
    }
    public static void Click(WebElement element){
        action.click(element);
    }
    
    public static void buildPerform(){
        action.build().perform();
    }
    
    public static void DragDrop(WebElement targetElement){
       action.clickAndHold(targetElement)
                .moveByOffset(10, 10) // Adjust the offset as needed
                .release()
                .build()
                .perform(); 
    }
}
