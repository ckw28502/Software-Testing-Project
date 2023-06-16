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
public class Blog {
    private static ArrayList<Blog>list=new ArrayList();
    private String id,title,category,content,image;

    public Blog(String id, String title, String category, String content,String image) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.content = content;
        this.image=image;
    }
    
    public static void Init(){
        String[] values={};
        try {
            URL url = new URL("https://docs.google.com/spreadsheets/d/e/2PACX-1vRhDKotdM2UE2rj8k8KUBG9Ww2CAGQZ22PWMYOtiQrmHAeDZx4WBZVdFG8Rmc_hN6JdzajYZ7JuhI3A/pub?gid=87823569&single=true&output=csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            boolean title=true;
            String line; 
            while ((line = reader.readLine()) != null) {
                // Process each line of the CSV data
                if(!title){
                     values= line.split(",");
                    list.add(new Blog(values[0],values[1],values[2],values[3],values[4]));
                } else {
                    title=false;
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void addBlog() {
        Driver.Type("//input[@name='term']","bl");
        Driver.Click("//span[contains(text(),'Blog')]");
        for (Blog b : list) {
            Driver.Click("//a[@data-target='#createModal']");
            String id="language";
            Driver.waitVisibility(id);
            Driver.Type("image", Paths.get("").toAbsolutePath().toString()+"\\Images\\portfolio\\"+b.image);
            Driver.SelectItem(id, "English");
            Driver.Type("//input[@name='title']",b.title);
            id="ucategory";
            Driver.waitNumberMore(By.xpath("//select[@id='ucategory']/option"), 2);
            Driver.SelectItem(id,Category.getListSize()-Integer.parseInt(b.category)+1);
            Driver.Type("//div[@contenteditable='true']", b.content);
            Driver.Type("//input[@name='serial_number']",b.id);
            Driver.Click("submitBtn");
            Driver.waitDone();
        }
    }
}
