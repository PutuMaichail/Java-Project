import java.util.Scanner;

public class AnalisisAngka {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] bilangan = new int[10];
        double total = 0;

        // Minta 10 input angka dari user
        System.out.println("\nMasukkan 10 bilangan bulat:");
        for (int i = 0; i < bilangan.length; i++) {
            System.out.print("Bilangan ke-" + (i + 1) + ": ");
            bilangan[i] = input.nextInt();
            total += bilangan[i];
        }

        // Tentukan nilai min & max awal dari elemen pertama
        int maks = bilangan[0];
        int min = bilangan[0];
        int indeksMaks = 0;
        int indeksMin = 0;

        // Cari nilai min & max sebenarnya dengan membandingkan sisa elemen
        for (int i = 1; i < bilangan.length; i++) {
            if (bilangan[i] > maks) { // Cek nilai maksimum baru
                maks = bilangan[i];
                indeksMaks = i;
            }
            if (bilangan[i] < min) { // Cek nilai minimum baru
                min = bilangan[i];
                indeksMin = i;
            }
        }

        // Hitung rata-rata
        double rataRata = total / bilangan.length;

        // Tampilkan semua hasil
        System.out.println("\n--- HASIL ANALISIS ---\n");
        System.out.println("Nilai rata-rata   : " + rataRata);
        System.out.println("Nilai maksimum    : " + maks + " [indeks ke-" + indeksMaks + "]");
        System.out.println("Nilai minimum     : " + min + " [indeks ke-" + indeksMin + "]\n");

        input.close(); // Tutup scanner
    }
}