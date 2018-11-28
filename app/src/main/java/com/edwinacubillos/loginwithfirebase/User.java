package com.edwinacubillos.loginwithfirebase;

public class User {
    String uid, email,nombre;

    public User(String uid, String email) {
        this.uid = uid;
        this.email = email;
    }

    public User(String uid, String email, String nombre) {
        this.uid = uid;
        this.email = email;
        this.nombre = nombre;
    }

    public User() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
