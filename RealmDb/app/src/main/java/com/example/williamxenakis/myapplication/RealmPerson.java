package com.example.williamxenakis.myapplication;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

import static android.R.attr.actionDropDownStyle;
import static android.R.attr.id;

/**
 * Created by william.xenakis on 6/21/17.
 */

@RealmClass
public class RealmPerson extends RealmObject {
    @PrimaryKey
    @Required
    public String UUID;

    public String memberId;
    public String jobSeekerId;
    public String name;
    public int age;
    public String address;

    public RealmPerson(){
    }

    public RealmPerson(String UUID, String memberId, String jobSeekerId, String name, int age, String address){
        this.UUID = UUID;
        this.memberId = memberId;
        this.jobSeekerId = jobSeekerId;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public String getMemberId(){
        return memberId;
    }
    public String getJobSeekerId(){
        return jobSeekerId;
    }
    public void setMemberId(String memberId){
        this.memberId=memberId;
    }
    public void setJobSeekerId(String jobSeekerId){
        this.jobSeekerId=jobSeekerId;
    }
    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }
}
