import java.util.Scanner;

public class KasirRestoran {

    // Variabel statis untuk data menu, harga, dan pesanan
    static String[] daftarMenu = {
        "Nasi Goreng Spesial", "Mie Goreng Seafood", "Ayam Bakar Madu",
        "Sate Ayam (10 tusuk)", "Gurame Asam Manis", "Es Teh Manis", "Es Jeruk"
    };
    static double[] hargaMenu = {
        28000, 32000, 35000, 25000, 75000, 8000, 10000
    };
    static int[] pesananItem = new int[100];
    static int[] jumlahPesanan = new int[100];
    static int jumlahItemDipesan = 0;
    
    // Objek untuk menerima input dari pengguna
    static Scanner scanner = new Scanner(System.in);

    // Fungsi utama untuk menjalankan program
    public static void main(String[] args) {
        System.out.println("=============================================");
        System.out.println("   SELAMAT DATANG DI RESTORAN SEDERHANA    ");
        System.out.println("=============================================");

        tampilkanMenu();
        terimaPesanan();
        
        // Hanya cetak struk jika ada pesanan yang dibuat
        if (jumlahItemDipesan > 0) {
            cetakStruk();
        } else {
            System.out.println("\nTidak ada pesanan yang dibuat.");
        }
        
        System.out.println("\n=============================================");
        System.out.println("          TERIMA KASIH ATAS KUNJUNGANNYA         ");
        System.out.println("=============================================");
        
        // Menutup scanner setelah selesai digunakan
        scanner.close();
    }

    // Fungsi untuk menampilkan semua menu yang tersedia
    public static void tampilkanMenu() {
        System.out.println("\n----------- MENU KAMI -----------");
        for (int i = 0; i < daftarMenu.length; i++) {
            System.out.println((i + 1) + ". " + daftarMenu[i] + "\t | Rp " + hargaMenu[i]);
        }
        System.out.println("---------------------------------");
    }

    // Fungsi untuk memproses input pesanan dari pengguna
    public static void terimaPesanan() {
        System.out.println("\nSilakan masukkan pesanan Anda.");
        System.out.println("(Masukkan nomor menu, ketik '0' untuk selesai)");

        while (true) {
            System.out.print("\nPilih item (1-" + daftarMenu.length + "): ");
            int pilihan = scanner.nextInt();

            // Hentikan loop jika pengguna memasukkan 0
            if (pilihan == 0) {
                break; 
            }

            // Validasi input pilihan menu
            if (pilihan > 0 && pilihan <= daftarMenu.length) {
                int indeksMenu = pilihan - 1;
                
                System.out.print("Jumlah       : ");
                int jumlah = scanner.nextInt();
                
                // Simpan pesanan jika jumlah valid
                if (jumlah > 0) {
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

    // Fungsi untuk menghitung total dan mencetak struk pembayaran
    public static void cetakStruk() {
        System.out.println("\n\n=============== STRUK PEMBAYARAN ===============");
        System.out.println("Item Dipesan:");
        
        double subtotal = 0;

        // Iterasi semua pesanan untuk menghitung subtotal
        for (int i = 0; i < jumlahItemDipesan; i++) {
            int indeksItem = pesananItem[i];
            int kuantitas = jumlahPesanan[i];
            double hargaItemTotal = hargaMenu[indeksItem] * kuantitas;
            subtotal += hargaItemTotal;

            System.out.println("- " + daftarMenu[indeksItem] + " (" + kuantitas + "x) \t = Rp " + hargaItemTotal);
        }
        
        System.out.println("----------------------------------------------");
        System.out.println("Subtotal \t\t: Rp " + subtotal);

        // Hitung diskon jika pembelian di atas 300.000
        double diskon = 0;
        if (subtotal > 300000) {
            diskon = subtotal * 0.03; 
            System.out.println("Diskon (3%) \t\t: Rp " + diskon);
        }

        // Hitung pajak 11% dari harga setelah diskon
        double subtotalSetelahDiskon = subtotal - diskon;
        double pajak = subtotalSetelahDiskon * 0.11; 
        System.out.println("Pajak (11%) \t\t: Rp " + pajak);

        // Hitung total akhir yang harus dibayar
        double totalAkhir = subtotalSetelahDiskon + pajak;
        System.out.println("----------------------------------------------");
        System.out.println("TOTAL BAYAR \t\t: Rp " + totalAkhir);
        System.out.println("==============================================");
    }
}

