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
import java.nio.file.Paths;
import java.util.ArrayList;
import org.openqa.selenium.By;

/**
 *
 * @author User
 */
public class Portfolio {

    static void addPortfolio() {
        Driver.Type("//input[@name='term']","portf");
        Driver.Click("//p[contains(text(),'Portfolio')]");
        Driver.Click("//span[contains(text(),'Portfolios')]");
        for (Portfolio p : list) {
            Driver.Click("//a[@data-target='#createModal']");
            String id="my-dropzone";
            String path=Paths.get("").toAbsolutePath().toString()+"\\Images\\portfolio\\"+p.image;
            Driver.waitVisibility(id);
            Proyek.imageitem(path, "//form[@id='my-dropzone']");
            Driver.Type("image",path);
            Driver.SelectItem("language", "266");
            id="pcategory";
            Driver.waitNumberMore(By.xpath("//select[@id='pcategory']/option"), 2);
            Driver.SelectItem(id,cPortfolio.getListSize()-Integer.parseInt(p.category)+1);
            Driver.SelectItem("status", p.status);
            Driver.Type("//input[@name='title']",p.title);
            Driver.Type("//input[@type='number']",p.id);
            Driver.Type("//div[@contenteditable='true']", p.desc);
            Driver.Click("featured");
            Driver.Click("submitBtn");
            Driver.waitDone();
        }
    }

    String image,category,title,id,desc,status;
    private static ArrayList<Portfolio>list=new ArrayList();

    public Portfolio(String image, String category, String title, String id,String desc,String status) {
        this.image = image;
        this.category = category;
        this.title = title;
        this.id = id;
        this.desc=desc;
        this.status=status;
    }
    
    public static void Init(){
        String[] values={};
        try {
            URL url = new URL("https://docs.google.com/spreadsheets/d/e/2PACX-1vRhDKotdM2UE2rj8k8KUBG9Ww2CAGQZ22PWMYOtiQrmHAeDZx4WBZVdFG8Rmc_hN6JdzajYZ7JuhI3A/pub?gid=79573121&single=true&output=csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            boolean title=true;
            String line; 
            while ((line = reader.readLine()) != null) {
                // Process each line of the CSV data
                if(!title){
                     values= line.split(",");
                    list.add(new Portfolio(values[3], values[2], values[1],values[0],values[4],values[5]));
                } else {
                    title=false;
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
