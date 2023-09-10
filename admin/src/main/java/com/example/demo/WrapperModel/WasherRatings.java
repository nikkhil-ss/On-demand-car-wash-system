package com.example.demo.WrapperModel;

import java.util.List;
import com.example.demo.model.Ratings;

public class WasherRatings {
    private String WasherID;
    private String WasherName;
    private String WasherEmailID;
    private List<com.example.demo.model.Ratings> Ratings;

    //Default Constructor
    public WasherRatings(){

    }

    //Constructor
    public WasherRatings(String washerID, String washerName, String washerEmailID, List<com.example.demo.model.Ratings> ratings) {
        WasherID = washerID;
        WasherName = washerName;
        WasherEmailID = washerEmailID;
        Ratings = ratings;
    }

    //Getter and Setters
    public String getWasherID() {
        return WasherID;
    }
    public void setWasherID(String washerID) {
        WasherID = washerID;
    }
    public String getWasherName() {
        return WasherName;
    }
    public void setWasherName(String washerName) {
        WasherName = washerName;
    }
    public String getWasherEmailID() {
        return WasherEmailID;
    }
    public void setWasherEmailID(String washerEmailID) {
        WasherEmailID = washerEmailID;
    }
    public List<com.example.demo.model.Ratings> getRatings() {
        return Ratings;
    }
    public void setRatings(List<Ratings> ratings) {
        Ratings = ratings;
    }

    @Override
    public String toString() {
        return "WasherRatings{" +
                "WasherID='" + WasherID + '\'' +
                ", WasherName='" + WasherName + '\'' +
                ", WasherEmailID='" + WasherEmailID + '\'' +
                ", Ratings=" + Ratings +
                '}';
    }
}