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
public class cPortfolio {
    
    private String id, nama;
    private static ArrayList<cPortfolio> list =new ArrayList();

    public cPortfolio(String id, String nama) {
        this.id = id;
        this.nama = nama;
    }
    
    public static void Init(){
        String[] values={};
        try {
            URL url = new URL("https://docs.google.com/spreadsheets/d/e/2PACX-1vRhDKotdM2UE2rj8k8KUBG9Ww2CAGQZ22PWMYOtiQrmHAeDZx4WBZVdFG8Rmc_hN6JdzajYZ7JuhI3A/pub?gid=555229245&single=true&output=csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            boolean title=true;
            String line; 
            while ((line = reader.readLine()) != null) {
                // Process each line of the CSV data
                if(!title){
                     values= line.split(",");
                    list.add(new cPortfolio(values[0], values[1]));
                } else {
                    title=false;
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static int getListSize(){
        return list.size();
    }
    
    
    public static void addPortfolioCategory(){
        Driver.Type("//input[@name='term']","portf");
        Driver.Click("//p[contains(text(),'Portfolio')]");
        Driver.Click("//span[contains(text(),'Category')]");
        for (cPortfolio cp : list) {
            Driver.Click("//a[@data-target='#createModal']");
            String xpath="//select[@name='user_language_id']";
            Driver.waitVisibility(xpath);
            Driver.SelectItem(xpath, "266");
            Driver.Type("//input[@name='name']",cp.nama);
            Driver.SelectItem("//select[@name='status']", "1");
            Driver.Type("//input[@type='number']", cp.id);
            Driver.Click("submitBtn");
            Driver.waitDone();
        }
        Driver.waitDone();
    }
    
    
}
