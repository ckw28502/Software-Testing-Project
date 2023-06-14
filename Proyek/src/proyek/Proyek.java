/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import static proyek.Driver.getElement;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
/**
 *
 * @author User
 */
public class Proyek {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Driver.initDriver("https://gruplm.com/", 60);
        Driver.Click("//button[contains(text(),'Allow cookies')]");
        
        // BACA SHEET
        User user=ReadCSV();
        
        // BUAT WEBSITE
        //createWebsite(user);
        
        //LOGIN
        Login(user);
        
        //Chose Theme
        chooseTheme();
         try {
            Thread.sleep(4000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Proyek.class.getName()).log(Level.SEVERE, null, ex);
        }
        toCategory();
        
        addCategory("civil", "Civil Litigation");
        addCategory("criminal", "Criminal Litigation");
        
        addCategory("tax", "Taxation");
//        toSubcategory();
        
//        addSubcategory("Commercial & Corporate Disputes","223");
//        addSubcategory("Property & Real Estate Disputes ","223");
//        toAdditem();
//        addSubcategory("Economic Crime","230");
//        addSubcategory("Document & Financial Fraud","230");
//        addSubcategory("Corporate Income Tax","231");


    }
    
    public static void Pricing(){
        Driver.Click("Pricing");
        Driver.Click("Lifetime");
        String xpath_plutinum="//a[@href='https://gruplm.com/registration/step-1/regular/21']";
        Driver.waitVisibility(xpath_plutinum);
        Driver.Click(xpath_plutinum);
    }

    private static void Registration(User user) {
        Driver.Type("//input[@name='username']", user.getUsername());
        Driver.Type("//input[@name='email']", user.getEmail());
        Driver.Type("//input[@name='password']", user.getPassword());
        Driver.Type("password-confirm", user.getPassword());
        Driver.Click("//button[contains(text(),'continue')]");
        Driver.Type("first_name",user.getFirst_name());
        Driver.Type("last_name",user.getLast_name());
        Driver.Type("phone",user.getPhone_number());
        Driver.Type("company_name",user.getCompany_name());
        Driver.Type("country",user.getCountry());
        Driver.Type("//input[@name='coupon']","softwaretesting"+Keys.ENTER);
        Driver.Click(".nice-select.olima_select");
        Driver.Click("//li[@data-value='Flutterwave']");
        Driver.waitPresence(By.className("alert-success"));
        Driver.Click("confirmBtn");
        Driver.Click("a");
    }

    private static User ReadCSV() {
        String[] values={};
        try {
            URL url = new URL("https://docs.google.com/spreadsheets/d/e/2PACX-1vRhDKotdM2UE2rj8k8KUBG9Ww2CAGQZ22PWMYOtiQrmHAeDZx4WBZVdFG8Rmc_hN6JdzajYZ7JuhI3A/pub?output=csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            boolean title=true;
            String line; 
            while ((line = reader.readLine()) != null) {
                // Process each line of the CSV data
                if(!title){
                     values= line.split(",");
                    return new User(values);
                } else {
                    title=false;
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new User(values);
    }

    private static void createWebsite(User user) {
        Pricing();
        Registration(user);
    }

    private static void Login(User user) {
        Driver.Click("navbar-btn");
        Driver.Type("//input[@name='email']", user.getEmail());
        Driver.Type("//input[@name='password']", user.getPassword()+Keys.ENTER);
    }

    private static void chooseTheme() {
        Driver.Click("//p[contains(text(),'Settings')]");
        String menu_theme_xpath="//a[@href='https://gruplm.com/user/theme/version']";
        Driver.waitPresence(By.xpath(menu_theme_xpath));
        Driver.Click(menu_theme_xpath);
        Driver.Click("//img[@src='https://gruplm.com/assets/front/img/user/templates/home_six.png']");
        Driver.Click("submitBtn");
    }
    
    private static void toCategory() {
       
        String shop_management_xpath="//p[contains(text(),'Shop Management')]";
        Driver.waitPresence(By.xpath(shop_management_xpath));
//
        Driver.Click(shop_management_xpath);
        String menu_manageitem_xpath="//a[@href='#productManagement']";
        Driver.waitPresence(By.xpath(menu_manageitem_xpath));
        
        
             Driver.waitPresence(By.xpath(menu_manageitem_xpath));

        Driver.Click(menu_manageitem_xpath);
        
        String menu_category_xpath="//a[@href='https://gruplm.com/user/category?language=en']";
        Driver.waitPresence(By.xpath(menu_category_xpath));
        Driver.Click(menu_category_xpath);
        
       
        
    }
    private static void addCategory(String path,String name) {
        String addcategory_xpath="//a[@data-target='#createModal']";

        

        Driver.Click(addcategory_xpath);
        Driver.waitClick("//input[@name='name']");
        Driver.SelectItem("language","266");
//        try{
//                    FileOutputStream fileOut = new FileOutputStream("\\orderhis.ser");
//                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
//                    out.writeObject(arrorderhistory);
//                    out.close();
//                    fileOut.close();
//                    System.out.println("order history Berhasil Disimpan");
//                }
//                catch (Exception e){
//                    
//                }
        Driver.getElement("image").sendKeys(Paths.get("").toAbsolutePath().toString()+"\\Images\\"+path+".jpeg");
//                Driver.getElement("image").sendKeys("D:\\SoftwareTesting\\Software-Testing-Project\\Images\\"+path+".jpeg");

        Driver.Type("//input[@name='name']", name);
//        Driver.SelectItem("status","1");
        Driver.SelectItem("//select[@name='status']", "1");
//        select.selectByValue("1");
        Driver.Click("submitBtn");
        
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Proyek.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private static void addSubcategory(String name,String value) {
        String addsubcategory_xpath="//a[@data-target='#createModal']";

        Driver.Click(addsubcategory_xpath);

        Driver.waitClick("//input[@name='name']");

        Driver.Type("//input[@name='name']", name );

        
        Driver.SelectItem("//select[@name='user_language_id']","266");

        

        
//        Driver.SelectItem("status","1");
        Driver.SelectItem("//select[@name='status']", "1");
        
        
        
        Driver.SelectItem("//select[@name='category_id']",value);
         try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Proyek.class.getName()).log(Level.SEVERE, null, ex);
        }
        Driver.SelectItem("//select[@name='category_id']",value);
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Proyek.class.getName()).log(Level.SEVERE, null, ex);
        }
//        select.selectByValue("1");
        Driver.Click("submitBtn");
        
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Proyek.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     private static void toSubcategory() {    
          String domains_xpath="//p[contains(text(),'Domains & URLs')]";
        Driver.waitPresence(By.xpath(domains_xpath));

        Driver.Click(domains_xpath);
        
//        Driver.Click("//p[contains(text(),'Dashboard')]");
//        Driver.Click("//p[contains(text(),'Shop Management')]");
//        String menu_manageitem_xpath="//a[@href='#productManagement']";
//
//        Driver.waitPresence(By.xpath(menu_manageitem_xpath));
//                try {
//            Thread.sleep(1000);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Proyek.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        Driver.Click(menu_manageitem_xpath);
//        Driver.waitPresence(By.xpath(menu_manageitem_xpath));
//        Driver.Click(menu_manageitem_xpath);


//        String menu_category_xpath="//a[@href='https://gruplm.com/user/category?language=en']";
//        Driver.waitPresence(By.xpath(menu_category_xpath));
//        Driver.Click(menu_category_xpath);
//        String scrollbar="//div[@class='scroll-bar ui-draggable ui-draggable-handle'";
//        Action.Scroll(getElement(menu_subcategory_xpath));
//<div class="scroll-bar ui-draggable ui-draggable-handle" style="height: 225px; top: 0px;"></div>
//        Driver.executeScript("arguments[0].scrollIntoView(true);", Driver.getElement(menu_subcategory_xpath));

//        Driver.waitPresence(By.xpath(menu_subcategory_xpath));
//        Action.mouseMove(100,0);
//        new Actions(driver).moveByOffset(-x coordinate, -y coordinate).perform();


         String menu_subcategory_xpath="//a[@href='https://gruplm.com/user/subcategory?language=en']";

        Driver.waitPresence(By.xpath(menu_subcategory_xpath));
        Driver.Click(menu_subcategory_xpath);   
        
    }
     
    private static void toAdditem() {    
          String domains_xpath="//p[contains(text(),'Domains & URLs')]";
        Driver.waitPresence(By.xpath(domains_xpath));
        Driver.Click(domains_xpath);
     
        String additem_xpath="//a[@href='https://gruplm.com/user/item/type']";

        Driver.waitPresence(By.xpath(additem_xpath));
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Proyek.class.getName()).log(Level.SEVERE, null, ex);
//        }
        Driver.Click(additem_xpath);
        
        
        
        //add item digital
        Driver.Click("//h2[contains(text(),'Digital Product')]");  
        String slider_xpath="//form[@action='https://gruplm.com/user/item/slider']";
        Driver.waitPresence(By.xpath(slider_xpath));
        Driver.Click(slider_xpath);  
        //Driver.getElement("my-dropzone").sendKeys("C:\\Users\\HP\\Pictures\\Camera Roll\\WIN_20221109_18_31_06_Pro.JPG");
        
        //input image
        String imagePath = "C:\\Users\\HP\\Pictures\\Camera Roll\\WIN_20221109_18_31_06_Pro.JPG";
        // Keyboardnya ngetik sendiri di filemanager
        StringSelection stringSelection = new StringSelection(imagePath);
        try {
                  Thread.sleep(2000); // nek ga di wait ga konsisten
              } catch (InterruptedException ex) {
                  Logger.getLogger(Proyek.class.getName()).log(Level.SEVERE, null, ex);
              }
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
        Robot robot;
        try {
            robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
              try {
                  Thread.sleep(2000); 
              } catch (InterruptedException ex) {
                  Logger.getLogger(Proyek.class.getName()).log(Level.SEVERE, null, ex);
              }
            
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
              try {
                  Thread.sleep(2000); // Wait for the file selection to be confirmed
              } catch (InterruptedException ex) {
                  Logger.getLogger(Proyek.class.getName()).log(Level.SEVERE, null, ex);
              }
        } catch (AWTException ex) {
            Logger.getLogger(Proyek.class.getName()).log(Level.SEVERE, null, ex);
        }
        
// tidak perlu karena tidak di drag drop        
//        WebElement targetElement = Driver.getElement("my-dropzone");
//        Action.DragDrop(targetElement);
        
//        WebDriver driver = new ChromeDriver();  
//        Actions actions = new Actions(driver);
//            actions.clickAndHold(targetElement)
//                .moveByOffset(10, 10) // Adjust the offset as needed
//                .release()
//                .build()
//                .perform();
    
    }
}
