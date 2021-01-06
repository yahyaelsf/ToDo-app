package com.yahya.todoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yahya.todoapp.adapters.AllListsAdapter;
import com.yahya.todoapp.adapters.TasksAdapter;
import com.yahya.todoapp.adapters.horizontalAdapter;
import com.yahya.todoapp.classes.ListClass;
import com.yahya.todoapp.classes.TaskClass;

import java.util.ArrayList;

import static com.yahya.todoapp.ShowTask.notes;

public class Lists extends AppCompatActivity {
    public static String nameOfList="non";
    public static String currentNotebookId="non";
    private static DatabaseReference mDatabase;
    public static ValueEventListener valueEventListener;
    //book tools
    static AllListsAdapter  bookAdapterr;
    private RecyclerView bookRecyclerView;
    static horizontalAdapter bookAdapter;
    private RecyclerView.LayoutManager bookLayoutManager;
    public static ArrayList<ListClass> books=new ArrayList<>();
     TextView NOList;
    static int position1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);
        bookAdapterr = new AllListsAdapter(books);
        //Initialize Realtime Reference.
        mDatabase = FirebaseDatabase.getInstance().getReference();
        //sharedPreferences

        SharedPreferences preferences=getSharedPreferences("Prefs",MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putBoolean("is_logged_in",true);
        editor.apply();
        //recycler of Lists
        bookRecyclerView = (RecyclerView) findViewById(R.id.list_recycler_view);
        bookRecyclerView.setHasFixedSize(true);
        bookLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        bookRecyclerView.setLayoutManager(bookLayoutManager);
        bookAdapter = new horizontalAdapter(books);
        bookRecyclerView.setAdapter(bookAdapter);
        //Lists data
        initListData();

        //onListCLick
        bookAdapter.setOnItemClickListener(new AllListsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                position1=position;
                String namee=books.get(position).name;
                nameOfList=namee;
               String id1=books.get(position).id;
                currentNotebookId=id1;
                startActivity(new Intent(Lists.this,ShowTask.class));
                finish();
            }
        });

        //search filter
        EditText searchEditText=findViewById(R.id.search_edit_text1);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });


    }
    //get Lists from the fireBase database
    public static void initListData() {
        FirebaseDatabase.getInstance().getReference().child("User").child(FirebaseAuth.getInstance().getUid()).child("Lists")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        books.clear();
                        for(DataSnapshot snapshot: dataSnapshot.getChildren() ){
                            ListClass notebook = snapshot.getValue(ListClass.class);
                            books.add(notebook);

                        }
                        bookAdapter.notifyDataSetChanged();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
    }


    public static void writeNotebook(ListClass list) {
        Log.d("FIREBASE", "Writing notebook");
        String userId =FirebaseAuth.getInstance().getUid();
        mDatabase.child("User").child(userId).child("Lists").child(list.name).setValue(list);
    }






    private void filter(String text) {
        ArrayList<ListClass> filteredList = new ArrayList<>();

        for (ListClass item : Lists.books) {
            if (item.name.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        bookAdapterr.filterList(filteredList);
    }


    public void back(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(Lists.this)
//set icon
                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                .setTitle("logout")
//set message
                .setMessage("Log out of ToDo List ")
//set positive button
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseAuth.getInstance().signOut();
                        Intent intent=new Intent(Lists.this,LoginActivity.class);
                        startActivity(intent);
                        finish();

                    }
                })
//set negative button
                .setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();
    }

    public void OnClickCreateNewList(View view) {
    Intent intent=new Intent(Lists.this,AddList.class);
    startActivity(intent);
    finish();
    }

    public void logout(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(Lists.this)
//set icon
                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                .setTitle("logout")
//set message
                .setMessage("Log out of ToDo List ")
//set positive button
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseAuth.getInstance().signOut();
                        Intent intent=new Intent(Lists.this,LoginActivity.class);
                        startActivity(intent);
                        finish();

                    }
                })
//set negative button
                .setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();
    }
}