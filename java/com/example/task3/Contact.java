package com.example.task3;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Contact implements Serializable {


    public String name;
    public String cellNumber;
    public int iconIndex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public int getIconIndex() {
        return iconIndex;
    }

    public void setIconIndex(int iconIndex) {
        this.iconIndex = iconIndex;
    }

    public Contact(String name, String cellNumber, int iconIndex) {
        this.name = name;
        this.cellNumber = cellNumber;
        this.iconIndex = iconIndex;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", cellNumber='" + cellNumber + '\'' +
                ", iconIndex=" + iconIndex +
                '}';
    }

        public static List<Contact> getContacts() {
        List<Contact> contact = new ArrayList<>();

        contact.add(new Contact("Aisha\nvery long name\nand a bit more.", "21", 8));
        contact.add(new Contact("Bob", "18", 1));
        /*contact.add(new Person("CJ\nla\nla\nla la..\nla*", 23, 2));
        contact.add(new Person("Donna", 15, 3));
        contact.add(new Person("Ethan", 18, 5));
        contact.add(new Person("Faith\nalso long name", 21, 4));
        contact.add(new Person("Geoff", 21, 6));
        contact.add(new Person("Hahn", 17, 0));
        contact.add(new Person("Irene", 19, 7));
        contact.add(new Person("Jackson", 23, 0));*/

        return contact;
    }

}
