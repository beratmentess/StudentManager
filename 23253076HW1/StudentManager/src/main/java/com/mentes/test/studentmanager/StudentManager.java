/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mentes.test.studentmanager;

/**
 * Mahir Berat Menteş
 * 23253076
 * 06.03.2025
 * @author berat-mentes
 */

import java.util.*;

public class StudentManager {
    // Öğrencileri saklayacak liste
    private static List<Student> students = new ArrayList<>();
    // Kullanıcıdan giriş almak için Scanner nesnesi
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Kaç öğrenci gireceksiniz? ");
        int n = scanner.nextInt(); // Kullanıcıdan öğrenci sayısını alır
        scanner.nextLine(); // Satır sonu temizliği yapar

        // Öğrenci bilgilerini alır ve listeye ekler
        for (int i = 0; i < n; i++) {
            System.out.println((i + 1) + ". öğrenci bilgilerini girin:");
            System.out.print("Okul Numarası: ");
            int id = scanner.nextInt(); // Öğrenci numarasını alır
            scanner.nextLine(); // Satır sonu temizliği yapar
            System.out.print("Ad-Soyad: ");
            String name = scanner.nextLine(); // Öğrencinin adını ve soyadını alır
            System.out.print("Nesneye yönelik programlama dersinin vize Notu: ");
            double midterm = scanner.nextDouble(); // Nesneye yönelik programlama dersinin vize notunu alır
            System.out.print("Nesneye yönelik programlama dersinin final Notu: ");
            double finalExam = scanner.nextDouble(); // Nesneye yönelik programlama dersinin final notunu alır
            scanner.nextLine(); // Satır sonu temizliği yapar

            // Yeni öğrenci nesnesi oluşturup listeye ekler
            students.add(new Student(id, name, midterm, finalExam));
        }

        // Menü ekranını gösterir
        menu();
        scanner.close(); // Program sonunda Scanner'ı kapatır
    }

    // Menü ve kullanıcı seçimlerine göre işlemleri yapar
    private static void menu() {
        boolean running = true; // Programın devam etmesini kontrol eder
        while (running) {
            // Kullanıcıya sunulan menü seçenekleri
            System.out.println("\n1. Öğrenci Listesi");
            System.out.println("2. Sınav Ortalaması");
            System.out.println("3. 60'dan Küçük Notlar");
            System.out.println("4. Ortalamanın Üstündeki Notlar");
            System.out.println("5. 60'dan Küçük Not Sayısı");
            System.out.println("6. En Yüksek Not");
            System.out.println("7. En Düşük Not");
            System.out.println("8. Sıralı Liste");
            System.out.println("9. Çıkış");
            System.out.print("Seçim: ");
            int choice = scanner.nextInt(); // Kullanıcının seçim numarasını alır
            scanner.nextLine(); // Satır sonu temizliği yapar

            // Kullanıcı seçim yaparak uygun fonksiyonu çağırır
            switch (choice) {
                case 1: displayStudents(); break; // Öğrenci listesini gösterir
                case 2: displayAverage(); break; // Sınıf ortalamasını gösterir
                case 3: displayBelow60(); break; // 60'dan düşük ortalamaları gösterir
                case 4: displayAboveAverage(); break; // Ortalamanın üstündeki notları gösterir
                case 5: countBelow60(); break; // 60'dan küçük not sayısını gösterir
                case 6: displayHighestScore(); break; // En yüksek notu gösterir
                case 7: displayLowestScore(); break; // En düşük notu gösterir
                case 8: displaySorted(); break; // Öğrencileri sıralı şekilde gösterir
                case 9: 
                    System.out.println("Çıkılıyor...");
                    running = false; // Programı sonlandırır
                    break;
                default: System.out.println("Geçersiz seçim!"); // Hatalı seçimde uyarı verir
            }
        }
    }

    // Öğrencilerin listesini ekrana yazdırır
    private static void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("Öğrenci listesi boş!"); // Liste boşsa uyarı verir
            return;
        }
        for (Student s : students)
            System.out.println(s.getStudentId() + " - " + s.getName() + " - Ortalama: " + s.getAverage()); // Her öğrenciyi yazdırır
    }

    // Sınıf ortalamasını hesaplar ve ekrana yazdırır
    private static void displayAverage() {
        if (students.isEmpty()) {
            System.out.println("Öğrenci listesi boş!"); // Liste boşsa uyarı verir
            return;
        }
        double total = students.stream().mapToDouble(Student::getAverage).sum(); // Öğrencilerin ortalamalarını toplar
        System.out.println("Sınıf Ortalaması: " + (total / students.size())); // Sınıf ortalamasını yazdırır
    }

    // 60'dan küçük notları ekrana yazdırır
    private static void displayBelow60() {
        boolean found = false; // 60'dan küçük not bulunup bulunmadığını kontrol eder
        for (Student s : students) {
            if (s.getAverage() < 60) {
                System.out.println(s.getStudentId() + " - " + s.getName() + " - Ortalama: " + s.getAverage()); // 60'dan küçük notu yazdırır
                found = true; // Bulundu olarak işaretler
            }
        }
        if (!found) System.out.println("60'dan düşük not yok."); // Eğer 60'dan küçük not yoksa uyarı verir
    }

    // Ortalamanın üstündeki öğrencileri ekrana yazdırır
    private static void displayAboveAverage() {
        if (students.isEmpty()) {
            System.out.println("Öğrenci listesi boş!"); // Liste boşsa uyarı verir
            return;
        }
        double avg = students.stream().mapToDouble(Student::getAverage).average().orElse(0); // Ortalamayı hesaplar
        students.stream().filter(s -> s.getAverage() > avg) // Ortalama üstü olanları filtreler
                .forEach(s -> System.out.println(s.getStudentId() + " - Ortalama: " + s.getAverage())); // Öğrenciyi yazdırır
    }

    // 60'dan küçük not sayısını ekrana yazdırır
    private static void countBelow60() {
        long count = students.stream().filter(s -> s.getAverage() < 60).count(); // 60'dan küçük notların sayısını sayar
        System.out.println("60'dan küçük not sayısı: " + count); // Sayıyı yazdırır
    }

    // En yüksek notu ekrana yazdırır
    private static void displayHighestScore() {
        if (students.isEmpty()) {
            System.out.println("Öğrenci listesi boş!"); // Liste boşsa uyarı verir
            return;
        }
        Student highest = Collections.max(students, Comparator.comparing(Student::getAverage)); // En yüksek notu bulur
        System.out.println("En yüksek not: " + highest.getAverage()); // En yüksek notu yazdırır
    }

    // En düşük notu ekrana yazdırır
    private static void displayLowestScore() {
        if (students.isEmpty()) {
            System.out.println("Öğrenci listesi boş!"); // Liste boşsa uyarı verir
            return;
        }
        Student lowest = Collections.min(students, Comparator.comparing(Student::getAverage)); // En düşük notu bulur
        System.out.println("En düşük not: " + lowest.getAverage()); // En düşük notu yazdırır
    }

    // Öğrencileri ortalamalarına göre sıralar ve yazdırır
    private static void displaySorted() {
        if (students.isEmpty()) {
            System.out.println("Öğrenci listesi boş!"); // Liste boşsa uyarı verir
            return;
        }
        students.stream()
                .sorted(Comparator.comparingDouble(Student::getAverage)) // Öğrencileri ortalamaya göre sıralar
                .forEach(s -> System.out.println(s.getStudentId() + " - " + s.getName() + " - " + s.getAverage())); // Öğrencileri yazdırır
    }
}