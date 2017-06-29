package com.example.williamxenakis.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;


/**
 * Created by william.xenakis on 6/20/17.
 */

public class DatabaseActivity2 extends AppCompatActivity {
    private String uniqueID1;
    private DatabaseReference fireBaseDb;
    private boolean isValidUser;
    private boolean isGuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TextView text1 = (TextView) findViewById(R.id.textView1);
        final TextView text2 = (TextView) findViewById(R.id.textView2);
        uniqueID1 = UUID.randomUUID().toString();
        final Person person1 = new Person(uniqueID1,"memberId 2", "jobseekerid 2", "Bob", "1984 Happy Valley Rd, Lafayette, CA", 24);
        //Firebase db
        fireBaseDb = FirebaseDatabase.getInstance().getReference("Dogs");
        fireBaseDb.child("Dogs").child(uniqueID1).setValue(person1);
        fireBaseDb.child("Dogs").child("2").setValue(new Person("id2","asdf", "asdf", "bobb", "1986 Happy Valley Rd, Lafayette, CA", 25));
        Query query = fireBaseDb.child("Dogs").orderByChild("uuid").equalTo(uniqueID1);
        isValidUser = false;
        isGuest = false;
        // Attach a listener to read the data at our posts reference
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snap: dataSnapshot.getChildren()){
                    Person person = snap.getValue(Person.class);
                    if(person.getJobSeekerId() == null && person.getUUID().equals(person1.getUUID())){
                        isGuest = true;
                        text1.setText("Guest");
                    }
                    else if(person.getmemberId().equals(person1.getmemberId()) && person.getJobSeekerId().equals(person1.getJobSeekerId())){
                        isValidUser = true;
                        text1.setText(person1.getAddress());
                        text2.setText(Integer.toString(person1.getAge()));
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        fireBaseDb.child("Dogs").child("1").child("name").setValue("Bob 2.0");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void buttonClicked(View view) {
        EditText editText = (EditText) findViewById(R.id.textEdit);
        final TextView text = (TextView) findViewById(R.id.textView1);
        fireBaseDb.child("Dogs").child(uniqueID1).child("name").setValue(editText.getText().toString());

        Query query = fireBaseDb.child("Dogs").orderByChild("uuid").equalTo(uniqueID1);

        // Attach a listener to read the data at our posts reference
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snap: dataSnapshot.getChildren()){
                    Person person = snap.getValue(Person.class);
                    if(isGuest && !isValidUser){
                        text.setText("Guest");
                    }
                    else if(isValidUser){
                        text.setText(person.getName());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

    }
}
