/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyek;

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
