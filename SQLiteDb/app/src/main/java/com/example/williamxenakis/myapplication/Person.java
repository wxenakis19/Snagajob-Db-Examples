package com.example.williamxenakis.myapplication;

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
 //   private int birthYear;

    public Person() {
    }

    public Person(String UUID){
        this.UUID = UUID;
    }

    public Person(String UUID, String memberId, String jobSeekerId, String name, String homeAddress, int age/*, int birthYear*/) {
        this.UUID = UUID;
        this.memberId = memberId;
        this.jobSeekerId = jobSeekerId;
        this.name = name;
        this.homeAddress = homeAddress;
        this.age = age;
   //     this.birthYear = birthYear;
    }

//    public int getBirthYear() {
//        return birthYear;
//    }
//
//    public void setBirthYear(int birthYear){
//        this.birthYear = birthYear;
//    }

    public void setJobSeekerId(String jobSeekerId) {
        this.jobSeekerId = jobSeekerId;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getJobSeekerId() {
        return jobSeekerId;
    }

    public void setMemberId(String memberId) {
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

    public String getMemberId() {
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
