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

/**
 *
 * @author User
 */
public class User {
    private String username,email,password,first_name,last_name,phone_number,company_name,country;

    public User(String[] data) {
        this.username = data[0];
        this.email = data[1];
        this.password=data[2];
        this.first_name=data[3];
        this.last_name=data[4];
        this.phone_number=data[5];
        this.company_name=data[6];
        this.country=data[7];
    }
    
    public static User Init(){
        String[] values={};
        try {
            URL url = new URL("https://docs.google.com/spreadsheets/d/e/2PACX-1vRhDKotdM2UE2rj8k8KUBG9Ww2CAGQZ22PWMYOtiQrmHAeDZx4WBZVdFG8Rmc_hN6JdzajYZ7JuhI3A/pub?gid=0&single=true&output=csv");
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
        return null;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getCompany_name() {
        return company_name;
    }

    public String getCountry() {
        return country;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    
}
