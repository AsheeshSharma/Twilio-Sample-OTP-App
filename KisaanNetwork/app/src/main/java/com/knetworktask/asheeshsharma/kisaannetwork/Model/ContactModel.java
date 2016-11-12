package com.knetworktask.asheeshsharma.kisaannetwork.Model;

/**
 * Created by Asheesh.Sharma on 11-11-2016.
 * Contact Model of a user
 */
public class ContactModel {
    private String firstName;
    private String lastName;
    private String contactNumber;

    public ContactModel(String firstName, String lastName, String contactNumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        return "ContactModel{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                '}';
    }
}
