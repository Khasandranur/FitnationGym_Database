package com.example.fitnationgym.model;

public class Klien {
    private String id_klien;
    private String email;
    private String gender;
    private String usia;
    private String id_jadwal;

    private String nama;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getId_klien() {
        return id_klien;
    }

    public void setId_klien(String id_klien) {
        this.id_klien = id_klien;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUsia() {
        return usia;
    }

    public void setUsia(String usia) {
        this.usia = usia;
    }

    public String getId_jadwal() {
        return id_jadwal;
    }

    public void setId_jadwal(String id_jadwal) {
        this.id_jadwal = id_jadwal;
    }
}
