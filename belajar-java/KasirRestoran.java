import java.util.Scanner;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Program simulasi mesin kasir sederhana untuk sebuah restoran.
 * Fungsionalitas: Menampilkan menu, menerima pesanan, menghitung total,
 * serta menerapkan pajak dan diskon.
 */
public class KasirRestoran {

    // --- Representasi Menu (Menggunakan Array Paralel) ---
    static String[] daftarMenu = {
        "Nasi Goreng Spesial",
        "Mie Goreng Seafood",
        "Ayam Bakar Madu",
        "Sate Ayam (10 tusuk)",
        "Gurame Asam Manis",
        "Es Teh Manis",
        "Es Jeruk"
    };
    static double[] hargaMenu = {
        28000,
        32000,
        35000,
        25000,
        75000,
        8000,
        10000
    };

    // --- Array untuk menyimpan pesanan pengguna ---
    // Diasumsikan maksimal 100 jenis item berbeda dalam 1 transaksi
    static int[] pesananItem = new int[100];
    static int[] jumlahPesanan = new int[100];
    static int jumlahItemDipesan = 0;
    
    // Objek Scanner untuk input pengguna
    static Scanner scanner = new Scanner(System.in);

    /**
     * Method utama untuk menjalankan seluruh alur program kasir.
     */
    public static void main(String[] args) {
        System.out.println("=============================================");
        System.out.println("   SELAMAT DATANG DI RESTORAN SEDERHANA    ");
        System.out.println("=============================================");

        // Menjalankan method untuk setiap fungsionalitas
        tampilkanMenu();
        terimaPesanan();
        
        // Hanya cetak struk jika ada pesanan
        if (jumlahItemDipesan > 0) {
            cetakStruk();
        } else {
            System.out.println("\nTidak ada pesanan yang dibuat.");
        }
        
        System.out.println("\n=============================================");
        System.out.println("          TERIMA KASIH ATAS KUNJUNGANNYA         ");
        System.out.println("=============================================");
        
        scanner.close();
    }

    /**
     * Method untuk menampilkan daftar menu yang tersedia.
     * Menggunakan perulangan untuk iterasi melalui array menu.
     */
    public static void tampilkanMenu() {
        System.out.println("\n----------- MENU KAMI -----------");
        // Format mata uang ke Rupiah
    NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.of("id", "ID"));
        
        for (int i = 0; i < daftarMenu.length; i++) {
            // Nomor menu dimulai dari 1, bukan 0
            System.out.printf("%d. %-25s | %s%n", (i + 1), daftarMenu[i], formatter.format(hargaMenu[i]));
        }
        System.out.println("---------------------------------");
    }

    /**
     * Method untuk menerima dan memproses input pesanan dari pengguna.
     * Menggunakan perulangan (while) dan percabangan (if-else).
     */
    public static void terimaPesanan() {
        System.out.println("\nSilakan masukkan pesanan Anda.");
        System.out.println("(Masukkan nomor menu, ketik '0' untuk selesai)");

        while (true) {
            System.out.print("\nPilih item (1-" + daftarMenu.length + "): ");
            int pilihan = scanner.nextInt();

            if (pilihan == 0) {
                break; // Keluar dari loop jika pengguna selesai memesan
            }

            // Validasi input
            if (pilihan > 0 && pilihan <= daftarMenu.length) {
                // Konversi pilihan ke indeks array (pilihan 1 -> indeks 0)
                int indeksMenu = pilihan - 1;
                
                System.out.print("Jumlah       : ");
                int jumlah = scanner.nextInt();
                
                if (jumlah > 0) {
                    // Simpan pesanan ke dalam array
                    pesananItem[jumlahItemDipesan] = indeksMenu;
                    jumlahPesanan[jumlahItemDipesan] = jumlah;
                    jumlahItemDipesan++;
                    System.out.println("'" + daftarMenu[indeksMenu] + "' sebanyak " + jumlah + " telah ditambahkan.");
                } else {
                    System.out.println("Jumlah harus lebih dari 0.");
                }
            } else {
                System.out.println("Pilihan tidak valid. Silakan pilih nomor menu yang tersedia.");
            }
        }
    }

    /**
     * Method untuk menghitung total biaya dan mencetak struk belanja.
     * Menerapkan perhitungan subtotal, diskon, dan pajak.
     */
    public static void cetakStruk() {
        System.out.println("\n\n=============== STRUK PEMBAYARAN ===============");
        System.out.println("Item Dipesan:");
        
        double subtotal = 0;
    NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.of("id", "ID"));

        // Perulangan untuk menghitung subtotal dan menampilkan item yang dipesan
        for (int i = 0; i < jumlahItemDipesan; i++) {
            int indeksItem = pesananItem[i];
            int kuantitas = jumlahPesanan[i];
            double hargaItemTotal = hargaMenu[indeksItem] * kuantitas;
            subtotal += hargaItemTotal;

            System.out.printf("- %-25s %2d x %-10s = %s%n", 
                daftarMenu[indeksItem], 
                kuantitas, 
                formatter.format(hargaMenu[indeksItem]),
                formatter.format(hargaItemTotal));
        }
        
        System.out.println("----------------------------------------------");
        System.out.printf("%-30s: %s%n", "Subtotal", formatter.format(subtotal));

        // Percabangan untuk perhitungan diskon
        double diskon = 0;
        if (subtotal > 300000) {
            diskon = subtotal * 0.03; // Diskon 3%
            System.out.printf("%-30s: %s%n", "Diskon (3%)", formatter.format(diskon));
        }

        // Perhitungan Pajak
        double subtotalSetelahDiskon = subtotal - diskon;
        double pajak = subtotalSetelahDiskon * 0.11; // Pajak 11%
        System.out.printf("%-30s: %s%n", "Pajak (11%)", formatter.format(pajak));

        // Perhitungan Total Akhir
        double totalAkhir = subtotalSetelahDiskon + pajak;
        System.out.println("----------------------------------------------");
        System.out.printf("%-30s: %s%n", "TOTAL BAYAR", formatter.format(totalAkhir));
        System.out.println("==============================================");
    }
}
