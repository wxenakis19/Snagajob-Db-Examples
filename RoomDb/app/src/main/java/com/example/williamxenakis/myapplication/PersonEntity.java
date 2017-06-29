package com.example.williamxenakis.myapplication;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by william.xenakis on 6/20/17.
 */
@Entity(tableName = "People")
public class PersonEntity {
    @PrimaryKey
    private String UUID;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "home_Address")
    private String homeAddress;

    @ColumnInfo(name = "age")
    private int age;

    @ColumnInfo(name = "memberId")
    private String memberID;

    @ColumnInfo(name = "jobSeekerId")
    private String jobSeekerId;

    @ColumnInfo(name = "birthYear")
    private int birthYear;

//    @ColumnInfo(name = "birthDay")
//    private int birthDay;
//
//    public int getBirthDay(){
//        return birthDay;
//    }
//    public void setBirthDay(int birthDay){
//        this.birthDay = birthDay;
//    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear){
        this.birthYear = birthYear;
    }

    public String getUUID() {
        return UUID;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getJobSeekerId() {
        return jobSeekerId;
    }

    public void setJobSeekerId(String jobSeekerId) {
        this.jobSeekerId = jobSeekerId;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }
}

