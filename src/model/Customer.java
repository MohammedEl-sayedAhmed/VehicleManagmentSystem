package model;

import java.io.Serializable;

public class Customer implements Serializable {
    private static final long serialVersionUID = 1L; // For serialization
    private String id; 
    private String phoneNumber;
    private String name;
    private int age; 

    public Customer(String name, int age, String phoneNumber) { 
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    public String getId() { 
        return id;
    }

    public void setId(String id) { 
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age; 
    }
}