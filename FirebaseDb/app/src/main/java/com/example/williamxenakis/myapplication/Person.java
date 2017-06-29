package com.example.williamxenakis.myapplication;

import android.location.Address;
import android.util.StringBuilderPrinter;

/**
 * Created by william.xenakis on 6/20/17.
 */

public class Person {
    private String UUID;
    private String memberId;
    private String jobSeekerId;
    private String name;
    private String homeAddress;
    private int age;

    public Person() {
    }

    public Person(String UUID){
        this.UUID = UUID;
    }
    public Person(String UUID, String memberId, String jobSeekerId, String name, String homeAddress, int age) {
        this.UUID = UUID;
        this.memberId = memberId;
        this.jobSeekerId = jobSeekerId;
        this.name = name;
        this.homeAddress = homeAddress;
        this.age = age;
    }

    public void setJobSeekerId(String jobSeekerId) {
        this.jobSeekerId = jobSeekerId;
    }

    public String getUUID() {
        return UUID;
    }

    public String getJobSeekerId() {
        return jobSeekerId;
    }

    public void setmemberId(String memberId) {
        this.memberId = memberId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getmemberId() {
        return memberId;
    }

    public String getAddress() {
        return homeAddress;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
