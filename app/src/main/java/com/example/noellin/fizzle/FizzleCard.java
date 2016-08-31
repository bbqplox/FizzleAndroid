package com.example.noellin.fizzle;

/**
 * Created by Jace on 8/29/16.
 */
public class FizzleCard {
    private String photoUri;
    private String userName;
    private String invitation;

    public FizzleCard(){
        //empty constructor
    }
    public FizzleCard(String photoUri, String userName, String invitation) {
        this.photoUri = photoUri;
        this.userName = userName;
        this.invitation = invitation;
    }

    public String getInvitation() {
        return invitation;
    }

    public void setInvitation(String invitation) {
        this.invitation = invitation;
    }

    public String getPhotoUri() {
        return photoUri;
    }

    public void setPhotoUri(String photoUri) {
        this.photoUri = photoUri;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
