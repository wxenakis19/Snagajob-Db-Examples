package com.example.williamxenakis.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.util.UUID;


/**
 * Created by william.xenakis on 6/20/17.
 */

public class DatabaseActivity2 extends AppCompatActivity {
    private String uniqueID1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final TextView text1 = (TextView) findViewById(R.id.textView1);
        TextView text2 = (TextView) findViewById(R.id.textView2);

        //SQLite db
        sqlDBHandler SQLiteDb = new sqlDBHandler(this);
        uniqueID1 = UUID.randomUUID().toString();
        final Person person1 = new Person(uniqueID1, "memberId 2", "jobseekerid 2", "Bob", "1984 Happy Valley Rd, Lafayette, CA", 24);
        SQLiteDb.addPerson(person1);
        boolean isGuest = false;
        boolean isValidUser = false;

        if (person1.getJobSeekerId() == null && person1.getUUID().equals(SQLiteDb.getPerson(uniqueID1).getUUID())) {
            isGuest = true;
            text1.setText("Guest");
        } else if (person1.getMemberId().equals(SQLiteDb.getPerson(uniqueID1).getMemberId()) && person1.getJobSeekerId().equals(SQLiteDb.getPerson(uniqueID1).getJobSeekerId())) {
            isValidUser = true;
            text1.setText(SQLiteDb.getPerson(uniqueID1).getAddress());
            text2.setText(Integer.toString(SQLiteDb.getPerson(uniqueID1).getAge()));
        }
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
        final EditText editText = (EditText) findViewById(R.id.textEdit);
        sqlDBHandler SQLiteDb = new sqlDBHandler(this);
        Person persona = new Person();
        persona.setUUID(uniqueID1);
        persona.setName(editText.getText().toString());
        SQLiteDb.updatePerson(persona);
        TextView text = (TextView) findViewById(R.id.textView1);
        Person person = SQLiteDb.getPerson(uniqueID1);
        text.setText(person.getName());

    }

}
