package com.example.noellin.fizzle;

/**
 * Created by Jace on 8/29/16.
 */
public class Data {

    private String description;

    private String imagePath;

    private String userName;

    private String userEmail;

    public Data(String imagePath, String description, String userName, String userEmail) {
        this.imagePath = imagePath;
        this.description = description;
        this.userEmail = userEmail;
        this.userName = userName;
    }

    public String getDescription() {
        return description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getUserName(){
        return this.userName;
    }

    public String getUserEmail(){
        return this.userEmail;
    }

}