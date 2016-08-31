package com.example.noellin.fizzle;

/**
 * User.java
 *
 * Class represents a user, with a username, email, and password.
 *
 * Created by bijanfarahani on 5/3/16.
 */
public class User {
    private String userName;
    private String email;
    private String photoUri;
    private String uid;
    private String partnerEmail;
    private String partnerName;
    private String moodMsg;

    public User() {
    }

    public User(String email, String partnerEmail, String photoUri, String uid, String userName, String partnerName, String moodMsg) {
        this.email = email;
        this.partnerEmail = partnerEmail;
        this.photoUri = photoUri;
        this.uid = uid;
        this.userName = userName;
        this.partnerName = partnerName;
        this.moodMsg = moodMsg;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public String getMoodMsg(){
        return moodMsg;
    }

    public void setMoodMsg(String moodMsg){
        this.moodMsg = moodMsg;
    }
    public boolean hasPartner(){
        return !(partnerEmail.equals(""));
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPartnerEmail(String partnerEmail) {
        this.partnerEmail = partnerEmail;
    }

    public void setPhotoUri(String photoUri) {
        this.photoUri = photoUri;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhotoUri() {
        return photoUri;
    }

    public String getEmail() {
        return email;
    }

    public String getUid() {
        return uid;
    }

    public String getPartnerEmail() {
        return partnerEmail;
    }

    public String getUserName() {
        return userName;
    }



}
