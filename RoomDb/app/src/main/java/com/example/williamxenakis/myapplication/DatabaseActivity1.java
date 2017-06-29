package com.example.williamxenakis.myapplication;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.*;
import android.arch.persistence.room.migration.Migration;
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

public class DatabaseActivity1 extends AppCompatActivity {
    private String person1UUID;
    private RoomDatabase roomDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TextView text1 = (TextView) findViewById(R.id.textView1);
        TextView text2 = (TextView) findViewById(R.id.textView2);

        final Migration MIGRATION_1_2 = new Migration(1, 2) {
            @Override
            public void migrate(SupportSQLiteDatabase database) {
                database.execSQL("ALTER TABLE People "
                        + "ADD COLUMN birthYear INTEGER");;
            }
        };
        final Migration MIGRATION_2_3 = new Migration(2, 3) {
            @Override
            public void migrate(SupportSQLiteDatabase database) {
                database.execSQL("ALTER TABLE People "
                        + "ADD COLUMN birthDay INTEGER");
            }
        };
        //Room db
        roomDB = Room.databaseBuilder(getApplicationContext(), RoomDatabase.class, "RoomPeople")
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3).allowMainThreadQueries().build();
        person1UUID = UUID.randomUUID().toString();
        PersonEntity person1 = new PersonEntity();
        person1.setUUID(person1UUID);
        person1.setName("Rexx");
        person1.setAge(24);
        person1.setHomeAddress("1984 Happy Valley Rd, Lafayette, CA");
        person1.setJobSeekerId("1423");
        person1.setMemberID("124324");
        roomDB.PersonDAO().insertUsers(person1);
        boolean isGuest = false;
        boolean isValidUser = false;
        if(person1.getJobSeekerId() == null && person1.getUUID().equals(roomDB.PersonDAO().findByUUID(person1UUID).getUUID())){
            isGuest = true;
            text1.setText("Guest");
        }
        else if(person1.getMemberID().equals(roomDB.PersonDAO().findByUUID(person1UUID).getMemberID()) && person1.getJobSeekerId().equals(roomDB.PersonDAO().findByUUID(person1UUID).getJobSeekerId())){
            isValidUser = true;
            text1.setText(person1.getHomeAddress());
            text2.setText(Integer.toString(person1.getAge()));
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
        PersonEntity persona = new PersonEntity();
        persona.setUUID(person1UUID);
        persona.setName(editText.getText().toString());
        roomDB.PersonDAO().updateUsers(persona);

        TextView text = (TextView) findViewById(R.id.textView1);
        text.setText(roomDB.PersonDAO().findByUUID(person1UUID).getName());

    }
}
