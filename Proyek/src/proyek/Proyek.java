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
import java.util.Random;
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
        
//        addCategory("civil", "Civil Litigation");
//        addCategory("criminal", "Criminal Litigation");
//        addCategory("tax", "Taxation");
//        
//        
        toSubcategory();
        addSubcategory("Commercial & Corporate Disputes",1);
        addSubcategory("Property & Real Estate Disputes ",1);
        addSubcategory("Economic Crime",2);
        addSubcategory("Document & Financial Fraud",2);
        addSubcategory("Corporate Income Tax",3);
        
        String sliderimage1 = "C:\\Users\\HP\\Pictures\\Camera Roll\\WIN_20221109_18_31_06_Pro.JPG";
        String sliderimage2 = "C:\\Users\\HP\\Pictures\\Screenshots\\Screenshot 2023-03-21 211139.png";
        String uploadImage1 = "C:\\Users\\HP\\Pictures\\Camera Roll\\WIN_20230609_14_42_43_Pro.JPG";
        String uploadImage2 = "C:\\Users\\HP\\Pictures\\Camera Roll\\WIN_20230609_15_05_08_Pro.JPG";
        String filepath = "\\Images\\doc1.zip";
        String currentprice = "200";
        String prevprice = "600";
        toAdditem(sliderimage1, sliderimage2, uploadImage1, uploadImage2, filepath, currentprice, prevprice  );



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
    
    private static void addSubcategory(String name,int value) {
        String addsubcategory_xpath="//a[@data-target='#createModal']";

        Driver.Click(addsubcategory_xpath);

        Driver.waitClick("//input[@name='name']");

        Driver.Type("//input[@name='name']", name );

        
        Driver.SelectItem("//select[@name='user_language_id']","266");

        

        
//        Driver.SelectItem("status","1");
        Driver.SelectItem("//select[@name='status']", "1");
        
        
            

        Driver.SelectItemByIndex("//select[@name='category_id']",value);
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
     
    private static void toAdditem(String sliderImage1, String sliderImage2, String uploadImage1, String uploadImage2, String filepath, String currentprice, String prevprice) {    
        String domains_xpath="//p[contains(text(),'Domains & URLs')]";
        Driver.waitPresence(By.xpath(domains_xpath));
        Driver.Click(domains_xpath);
     
        String additem_xpath="//a[@href='https://gruplm.com/user/item/type']";

        Driver.waitPresence(By.xpath(additem_xpath));
        Driver.Click(additem_xpath);
        
        //add item digital
        Driver.Click("//h2[contains(text(),'Digital Product')]");  
        
        //input image slider
        String slider_xpath="//form[@action='https://gruplm.com/user/item/slider']";
        imageitem(sliderImage1, slider_xpath);
        imageitem(sliderImage2, slider_xpath);
    
        try {
            Thread.sleep(1000); // nek ga di wait ga konsisten
        } catch (InterruptedException ex) {
            Logger.getLogger(Proyek.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //isi image 
        String uploadimg_xpath1="/html/body/div[1]/div[3]/div/div/div[2]/div/div/div[2]/div/div/form/div[1]/input";
        String uploadimg_xpath2="/html/body/div[1]/div[3]/div/div/div[2]/div/div/div[2]/div/div/form/div[2]/input";
        Driver.getElement(uploadimg_xpath1).sendKeys(uploadImage1);
        Driver.getElement(uploadimg_xpath2).sendKeys(uploadImage2);

        //file upload
        String uploadfile_xpath="/html/body/div[1]/div[3]/div/div/div[2]/div/div/div[2]/div/div/form/div[5]/div/div[1]/input";
        Driver.getElement(uploadfile_xpath).sendKeys(Paths.get("").toAbsolutePath().toString()+filepath);
        
        //click dd status
        Random random = new Random();
        double randomNumber = random.nextDouble();
        String res = Double.toString(randomNumber);
        String status_xpath = "";
        if (res =="1") {
            status_xpath=("//select[@class='form-control ltr']//option[@value=1]");
        }else{
            status_xpath=("//select[@class='form-control ltr']//option[@value=0]");
        }
        Driver.Click("//select[@class='form-control ltr']");  
        Driver.waitPresence(By.xpath(status_xpath));
        Driver.Click(status_xpath);
        
        //input current price
        String current_xpath= "/html/body/div[1]/div[3]/div/div/div[2]/div/div/div[2]/div/div/form/div[6]/div[2]/div/input";
        Driver.Click(current_xpath);
        Driver.Type(current_xpath, currentprice);
        
        //input prev price
        String prev_xpath= "/html/body/div[1]/div[3]/div/div/div[2]/div/div/div[2]/div/div/form/div[6]/div[3]/div/input";
        Driver.Click(prev_xpath);
        Driver.Type(prev_xpath, prevprice);
        
        //select category
        Driver.Click("/html/body/div[1]/div[3]/div/div/div[2]/div/div/div[2]/div/div/form/div[7]/div/div[2]/div/div[1]/div[1]/div/select");
        try {
            Thread.sleep(1000); // nek ga di wait ga konsisten
        } catch (InterruptedException ex) {
            Logger.getLogger(Proyek.class.getName()).log(Level.SEVERE, null, ex);
        }
        int rancat = random.nextInt(3) + 1;
        String cat_xpath= "";
        if (rancat == 1) {
            cat_xpath = "/html/body/div[1]/div[3]/div/div/div[2]/div/div/div[2]/div/div/form/div[7]/div/div[2]/div/div[1]/div[1]/div/select/option[2]";
        }else if (rancat == 2){
            cat_xpath = "/html/body/div[1]/div[3]/div/div/div[2]/div/div/div[2]/div/div/form/div[7]/div/div[2]/div/div[1]/div[1]/div/select/option[3]";
        }else{
            cat_xpath = "/html/body/div[1]/div[3]/div/div/div[2]/div/div/div[2]/div/div/form/div[7]/div/div[2]/div/div[1]/div[1]/div/select/option[4]";
        }
        Driver.Click(cat_xpath);
        
        //select subcategory
        Driver.Click("/html/body/div[1]/div[3]/div/div/div[2]/div/div/div[2]/div/div/form/div[7]/div/div[2]/div/div[1]/div[2]/div/select");
        try {
            Thread.sleep(1000); // nek ga di wait ga konsisten
        } catch (InterruptedException ex) {
            Logger.getLogger(Proyek.class.getName()).log(Level.SEVERE, null, ex);
        }
        double ran = random.nextDouble();
        String sran = Double.toString(ran);
        String subcat_xpath = "";
        if (sran =="1") {
            subcat_xpath=("/html/body/div[1]/div[3]/div/div/div[2]/div/div/div[2]/div/div/form/div[7]/div/div[2]/div/div[1]/div[2]/div/select/option[1]");
        }else{
            subcat_xpath=("/html/body/div[1]/div[3]/div/div/div[2]/div/div/div[2]/div/div/form/div[7]/div/div[2]/div/div[1]/div[2]/div/select/option[2]");
        }
        Driver.Click(subcat_xpath);
        
        
        
////                  
////        String title_xpath=("//input[@type='text'|@name='en_title']");
//        String title_xpath = ("//input[@name='en_title']");
//         Driver.Click(title_xpath);
//        Driver.Type(title_xpath,"asdasdadasdasdasdasdasd");
    }
    
    
    public static void imageitem(String imagePath, String slider_xpath){
        
        Driver.waitPresence(By.xpath(slider_xpath));
        Driver.Click(slider_xpath);  
        
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
    }
}
