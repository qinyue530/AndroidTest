package com.example.amdroidtestjava.enity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RoomUser {
    @PrimaryKey(autoGenerate = true )
    private int id;
    private String name;
    private String age;
    private Boolean married;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Boolean getMarried() {
        return married;
    }

    public void setMarried(Boolean married) {
        this.married = married;
    }

    @Override
    public String toString() {
        return "RoomUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", married=" + married +
                '}';
    }
}
