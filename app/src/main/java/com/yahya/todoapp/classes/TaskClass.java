package com.yahya.todoapp.classes;

import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskClass {
    public String ListsId="public";
    public String idOfTask;
    public String titleOfNote;
    public String contextOfNote;
    public long dateOfNote;
    public TaskClass(){}
    public TaskClass(String noteBookId,String idOfNote, String titleOfNote, String contextOfNote, long dateOfNote) {
        this.idOfTask = idOfNote;
        this.ListsId=noteBookId;
        this.titleOfNote = titleOfNote;
        this.contextOfNote = contextOfNote;
        this.dateOfNote = dateOfNote;
    }
    public TaskClass(String noteBookId,String idOfNote) {
        this.idOfTask = idOfNote;
        this.ListsId=noteBookId;

    }

    public static String generateNoteID(){
        return FirebaseDatabase.getInstance().getReference().child("user").child("Task").push().getKey();

    }

    public static String longToDate(long val){
        Date date=new Date(val);
        SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yy");
        String dateText = df2.format(date);
        return dateText;
    }
    public String getidTask( ){
        return  idOfTask ;
    }
    // id getter and setter
}