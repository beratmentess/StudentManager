/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mentes.test.studentmanager;

/**
 * Mahir Berat Menteş
 * 23253076
 * 06.03.2025
 * @author berat-mentes
 */
public class Student {
    private int studentId;   // Öğrenci numarası
    private String name;     // Öğrencinin adı
    private double midterm;  // Vize notu
    private double finalExam; // Final notu

    
    public Student(int studentId, String name, double midterm, double finalExam) {
        this.studentId = studentId; // Öğrenci numarasını atar
        this.name = name;           // Öğrencinin adını atar
        this.midterm = midterm;     // Vize notunu atar
        this.finalExam = finalExam; // Final notunu atar
    }

    // Getter metodları (Öğrencinin bilgilerine erişim için)

    /**
     * Öğrencinin numarasını döndürür.
     * @return Öğrenci numarası
     */
    public int getStudentId() {
        return studentId; // Öğrenci numarasını döndürür
    }

    /**
     * Öğrencinin adını döndürür.
     * @return Öğrencinin adı
     */
    public String getName() {
        return name; // Öğrencinin adını döndürür
    }

    /**
     * Öğrencinin vize notunu döndürür.
     * @return Vize notu
     */
    public double getMidterm() {
        return midterm; // Vize notunu döndürür
    }

    /**
     * Öğrencinin final notunu döndürür.
     * @return Final notu
     */
    public double getFinalExam() {
        return finalExam; // Final notunu döndürür
    }

    /**
     * Öğrencinin ortalama notunu hesaplar ve döndürür.
     * Ortalamayı hesaplamak için vize notu %40 ve final notu %60 ağırlıklı olarak kullanılır.
     * @return Öğrencinin ortalama notu
     */
    public double getAverage() {
        return (midterm * 0.4) + (finalExam * 0.6); // Ortalama hesaplanır ve döndürülür
    }
}
