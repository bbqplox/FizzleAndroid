package com.example.noellin.fizzle;

/**
 * Created by Jace on 8/29/16.
 */
public class FizzleCard {
    private String photoUri;
    private String moodMsg;

    public FizzleCard(){
        //empty constructor
    }
    public FizzleCard(String moodMsg, String photoUri) {
        this.moodMsg = moodMsg;
        this.photoUri = photoUri;
    }

    public String getPhotoUri(){
        return this.photoUri;
    }

    public String getMoodMsg(){
        return this.moodMsg;
    }

    public void setPhotoUri(String photoUri){
        this.photoUri = photoUri;
    }

    public void setMoodMsg(String moodMsg){
        this.moodMsg = moodMsg;
    }

}
