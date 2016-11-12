package com.knetworktask.asheeshsharma.kisaannetwork.Model;

/**
 * Created by Asheesh.Sharma on 12-11-2016.
 * Sent Message model of a particular message
 */
public class SentMessage {
    private String mText;
    private String mName;

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    private String mDate;
    public SentMessage(){}
    public SentMessage(String mNumber, String mName, String mText, String mDate){
        this.mName = mName;
        this.mNumber = mNumber;
        this.mText = mText;
        this.mDate = mDate;
    }
    public String getmText() {
        return mText;
    }

    public void setmText(String mText) {
        this.mText = mText;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmNumber() {
        return mNumber;
    }

    public void setmNumber(String mNumber) {
        this.mNumber = mNumber;
    }

    @Override
    public String toString() {
        return "SentMessage{" +
                "mText='" + mText + '\'' +
                ", mName='" + mName + '\'' +
                ", mNumber='" + mNumber + '\'' +
                '}';
    }

    private String mNumber;
}
