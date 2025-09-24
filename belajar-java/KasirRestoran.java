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
        System.out.println("==================================================");
        System.out.println("     SELAMAT DATANG DI RESTORAN SEDERHANA     ");
        System.out.println("==================================================");

        tampilkanMenu();
        terimaPesanan();
        
        // Hanya cetak struk jika ada pesanan yang dibuat
        if (jumlahItemDipesan > 0) {
            cetakStruk();
        } else {
            System.out.println("\nTidak ada pesanan yang dibuat.");
        }
        
        System.out.println("\n==================================================");
        System.out.println("        TERIMA KASIH ATAS KUNJUNGANNYA        ");
        System.out.println("==================================================");
        
        // Menutup scanner setelah selesai digunakan
        scanner.close();
    }

    // Fungsi untuk membuat teks rata kanan dengan menambahkan spasi
    private static String padRight(String text, int length) {
        StringBuilder sb = new StringBuilder(text);
        while (sb.length() < length) {
            sb.append(" ");
        }
        return sb.toString();
    }

    // Fungsi untuk menampilkan semua menu yang tersedia dengan format
    public static void tampilkanMenu() {
        System.out.println("\n------------------- MENU KAMI --------------------");
        
        // Cari nama menu terpanjang untuk menentukan lebar kolom
        int panjangMenuTerpanjang = 0;
        for (String nama : daftarMenu) {
            if (nama.length() > panjangMenuTerpanjang) {
                panjangMenuTerpanjang = nama.length();
            }
        }

        for (int i = 0; i < daftarMenu.length; i++) {
            // Gunakan fungsi padRight untuk merapikan nama menu
            String menuRapi = padRight(daftarMenu[i], panjangMenuTerpanjang);
            System.out.println((i + 1) + ". " + menuRapi + " | Rp " + hargaMenu[i]);
        }
        System.out.println("--------------------------------------------------");
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

    // Fungsi untuk menghitung total dan mencetak struk pembayaran dengan format
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

            String detailItem = "- " + daftarMenu[indeksItem] + " (" + kuantitas + "x)";
            String itemRapi = padRight(detailItem, 35); // Atur lebar kolom item
            System.out.println(itemRapi + " = Rp " + hargaItemTotal);
        }
        
        System.out.println("--------------------------------------------------");
        // Gunakan padRight untuk merapikan bagian total
        System.out.println(padRight("Subtotal", 25) + ": Rp " + subtotal);

        // Hitung diskon jika pembelian di atas 300.000
        double diskon = 0;
        if (subtotal > 300000) {
            diskon = subtotal * 0.03; 
            System.out.println(padRight("Diskon (3%)", 25) + ": Rp " + diskon);
        }

        // Hitung pajak 11% dari harga setelah diskon
        double subtotalSetelahDiskon = subtotal - diskon;
        double pajak = subtotalSetelahDiskon * 0.11; 
        System.out.println(padRight("Pajak (11%)", 25) + ": Rp " + pajak);

        // Hitung total akhir yang harus dibayar
        double totalAkhir = subtotalSetelahDiskon + pajak;
        System.out.println("--------------------------------------------------");
        System.out.println(padRight("TOTAL BAYAR", 25) + ": Rp " + totalAkhir);
        System.out.println("==================================================");
    }
}

