package com.example.williamxenakis.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.util.UUID;

import io.realm.DynamicRealm;
import io.realm.FieldAttribute;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.RealmResults;
import io.realm.RealmSchema;

/**
 * Created by william.xenakis on 6/20/17.
 */

public class DatabaseActivity2 extends AppCompatActivity {
    private String personUUID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TextView text1 = (TextView) findViewById(R.id.textView1);
        final TextView text2 = (TextView) findViewById(R.id.textView2);

       // Realm db
        personUUID = UUID.randomUUID().toString();
        Realm.init(this);
        final Realm realmDb = Realm.getDefaultInstance();
        RealmPerson person = new RealmPerson(personUUID, "1232", "1231", "Fidelity", 5, "1984 Happy Valley Rd, Lafayette, CA");
        realmDb.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmPerson person1 = realm.createObject(RealmPerson.class, personUUID);
                person1.name = "Inhuman";
                person1.age  = 5;
                person1.address = "1984 Happy Valley Rd, Lafayette, CA";
                person1.jobSeekerId = "1231";
                person1.memberId = "1232";

            }
        });
        RealmMigration migration = new RealmMigration() {
            @Override
            public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
                // DynamicRealm exposes an editable schema
                RealmSchema schema = realm.getSchema();
                if (oldVersion == 0) {
                    schema.get("Person")
                            .addField("UUID", String.class, FieldAttribute.PRIMARY_KEY);
                    oldVersion++;
                }
                if (oldVersion == 1) {
                    schema.get("Person")
                            .addField("UUID", String.class, FieldAttribute.PRIMARY_KEY);
                    oldVersion++;
                }
            }
        };
        RealmConfiguration config = new RealmConfiguration.Builder()
                .schemaVersion(2) // Must be bumped when the schema changes
                .migration(migration) // Migration to run instead of throwing an exception
                .build();

        RealmResults<RealmPerson> people = realmDb.where(RealmPerson.class)
                .equalTo("UUID", personUUID)
                .findAll();
        boolean isValidUser = false;
        boolean isGuest = false;
        if(people.get(0).getJobSeekerId() == null && person.getUUID().equals(people.get(0).getUUID())){
            isGuest = true;
            text1.setText("Guest");
        }
        else if(person.getMemberId().equals(people.get(0).getMemberId()) && person.getJobSeekerId().equals(people.get(0).getJobSeekerId())){
            isValidUser = true;
            text1.setText(people.get(0).getAddress());
            text2.setText(people.get(0).getName());
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
        final Realm realmDb = Realm.getDefaultInstance();
        final EditText editText = (EditText) findViewById(R.id.textEdit);

        RealmPerson persona = new RealmPerson();
        persona.setUUID(personUUID);
        persona.setName(editText.getText().toString());
        realmDb.beginTransaction();
        realmDb.copyToRealmOrUpdate(persona);
        realmDb.commitTransaction();
        TextView text = (TextView) findViewById(R.id.textView1);
        text.setText(persona.getName());
        //startActivity(new Intent(this, DatabaseActivity2.class));

    }
}
