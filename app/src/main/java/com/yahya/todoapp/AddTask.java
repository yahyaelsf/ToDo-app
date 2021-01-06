package com.yahya.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.yahya.todoapp.classes.TaskClass;

import java.util.Date;

public class AddTask extends AppCompatActivity {
    EditText title,contextDes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        title=findViewById(R.id.edit_text_task_name);
        contextDes=findViewById(R.id.edit_text_task_description );
    }

    public void backFromAddNewActivity(View view) {
        startActivity(new Intent(AddTask.this,ShowTask.class));
        finish();
    }

    public void OnClickAdd(View view) {

        TaskClass note=new TaskClass(Lists.currentNotebookId,TaskClass.generateNoteID(),title.getText().toString(),contextDes.getText().toString(),new Date().getTime());
        ShowTask.writeNote(note);
        Toast.makeText(this,"Note is added successfully" , Toast.LENGTH_SHORT).show();
        startActivity(new Intent(AddTask.this,ShowTask.class));
        finish();
    }
}