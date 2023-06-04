/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyek;

/**
 *
 * @author asus
 */
public class Category {
     private String imagepath,name;

    public Category(String[] data) {
        this.imagepath = data[0];
        this.name = data[1];
        
    }



    public String getImagepath() {
        return imagepath;
    }

    public String getName() {
        return name;
    }
}
