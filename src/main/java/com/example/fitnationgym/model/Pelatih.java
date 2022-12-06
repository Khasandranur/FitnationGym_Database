package com.example.fitnationgym.model;

public class Pelatih {
    private String id_pelatih;
    private String nama;
    private String email;

    public String getId_pelatih() {
        return id_pelatih;
    }

    public void setId_pelatih(String id_pelatih) {
        this.id_pelatih = id_pelatih;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    private String salary;

}
