package com.example.fitnationgym.model;

public class Jadwal {

    private String id_jadwal;
    private String tanggal;
    private String sesi;
    private String latihan;
    private String waktu_mulai;
    private String waktu_selesai;

    public String getId_jadwal() {
        return id_jadwal;
    }

    public void setId_jadwal(String id_jadwal) {
        this.id_jadwal = id_jadwal;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getSesi() {
        return sesi;
    }

    public void setSesi(String sesi) {
        this.sesi = sesi;
    }

    public String getLatihan() {
        return latihan;
    }

    public void setLatihan(String latihan) {
        this.latihan = latihan;
    }

    public String getWaktu_mulai() {
        return waktu_mulai;
    }

    public void setWaktu_mulai(String waktu_mulai) {
        this.waktu_mulai = waktu_mulai;
    }

    public String getWaktu_selesai() {
        return waktu_selesai;
    }

    public void setWaktu_selesai(String waktu_selesai) {
        this.waktu_selesai = waktu_selesai;
    }

    public String getId_pelatih() {
        return id_pelatih;
    }

    public void setId_pelatih(String id_pelatih) {
        this.id_pelatih = id_pelatih;
    }

    private String id_pelatih;
}
