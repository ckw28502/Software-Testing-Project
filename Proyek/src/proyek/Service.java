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

/**
 *
 * @author User
 */
public class Service {

    public static void addServices() {
        Driver.Type("//input[@name='term']","serv");
        Driver.Click("//p[contains(text(),'Services')]");
        for (Service s : list) {
            Driver.Click("//a[@data-target='#createModal']");
            String xpath="//input[@id='image']";
            Driver.waitVisibility(xpath);
            Driver.Type(xpath, Paths.get("").toAbsolutePath().toString()+"\\Images\\Service\\"+s.image);
            Driver.SelectItem("//select[@name='user_language_id']", "English");
            Driver.Type("//input[@name='name']", s.name);
            Driver.Type("//input[@type='number']", s.id);
            Driver.Click("featured");
            Driver.Click("submitBtn");
            Driver.waitDone();
        }

    }
    private String id,name,image;
    private static ArrayList<Service>list=new ArrayList();

    public Service(String id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }
    
    public static void Init(){
        String[] values={};
        try {
            URL url = new URL("https://docs.google.com/spreadsheets/d/e/2PACX-1vRhDKotdM2UE2rj8k8KUBG9Ww2CAGQZ22PWMYOtiQrmHAeDZx4WBZVdFG8Rmc_hN6JdzajYZ7JuhI3A/pub?gid=173648628&single=true&output=csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            boolean title=true;
            String line; 
            while ((line = reader.readLine()) != null) {
                // Process each line of the CSV data
                if(!title){
                     values= line.split(",");
                    list.add(new Service(values[0], values[1], values[2]));
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
