package com.example.spora_trial.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "users")
public class UserEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public int age;
    public String email;
    public String adress;
    public String phone;

    public String img;

    public UserEntity(String name, int age, String email, String adress, String phone, String img) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.adress = adress;
        this.phone = phone;
        this.img = img;
    }

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

}
