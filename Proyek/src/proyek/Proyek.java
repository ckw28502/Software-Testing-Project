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
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

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
        Driver.Click(menu_theme_xpath);
        Driver.Click("//img[@src='https://gruplm.com/assets/front/img/user/templates/home_six.png']");
        Driver.Click("submitBtn");
    }
    
}
