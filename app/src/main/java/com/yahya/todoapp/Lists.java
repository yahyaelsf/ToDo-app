package com.yahya.todoapp;

public class Lists {
    String Title;
    int count;

    public Lists() {

    }

    public Lists(String title, int count) {
        Title = title;
        this.count = count;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
    public int getCount(){
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}