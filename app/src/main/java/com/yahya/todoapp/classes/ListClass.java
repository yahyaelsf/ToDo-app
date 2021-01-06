package com.yahya.todoapp.classes;

import com.google.firebase.database.FirebaseDatabase;


public class ListClass {
    public String id;
    public String name;


    public ListClass(){

    }

    public ListClass(String id, String name ) {
        this.id = id;
        this.name = name;

    }

    public static String generateCategoryID(){
        return FirebaseDatabase.getInstance().getReference().child("user").child("category").push().getKey();

    }
    //// id getter and setter
//    public void setId(int id) {
//        this.id = id;
//    }
    public String getId() {
        return id;
    }
    ////  name getter and setter
    public String getName() {
        return name;
    }
//    public void setName(String name) {
//        this.name = name;
//    }
////   image getter and setter
//    public int getImageIcon() {
//        return imageIcon;
//    }
//    public void setImageIcon(int imageIcon) {
//        this.imageIcon = imageIcon;
//    }

}


