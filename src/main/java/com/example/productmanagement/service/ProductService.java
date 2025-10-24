package com.example.productmanagement.service;

import com.example.productmanagement.model.Product;
import com.example.productmanagement.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private final Random random = new Random();
    private final String[] OBJEK = {"Kopi", "Teh", "Susu", "Roti", "Baju", "Celana", "Sepatu", "Laptop", "Mouse", "Keyboard", "Earphone", "Charger", "Tas", "Dompet", "Jam Tangan"};
    private final String[] SIFAT = {"Premium", "Organik", "Gaming", "Kantor", "Anak", "Spesial", "Ekstra", "Mini", "Lokal", "Impor", "Hitam", "Putih", "Pintar", "Digital"};
    private final String[] SERI = {"Pro", "Series X", "Basic", "Plus", "Lite", "Advanced", "Edisi 2025", "Gold", "Classic", "Prime", "Ultra", "Max", "Air", "Go", "Signature", "Ultimate", "Compact", "Eco"};

    private final String[] KATEGORI = {"Minuman", "Makanan", "Pakaian Pria", "Pakaian Wanita", "Elektronik", "Rumah Tangga", "Aksesoris", "Olahraga", "Kecantikan", "Mainan"};

    public int generateProducts() {
        List<Product> newProducts = new ArrayList<>(10000);

        for (int i = 0; i < 10000; i++) {

            String nama = OBJEK[random.nextInt(OBJEK.length)] + " " +
                    SIFAT[random.nextInt(SIFAT.length)] + " " +
                    SERI[random.nextInt(SERI.length)];

            String kategori = KATEGORI[random.nextInt(KATEGORI.length)];

            long minHarga = 10000;
            long maxHarga = 1000000;

            long randomCents = random.nextLong(minHarga, maxHarga + 1);

            BigDecimal harga = BigDecimal.valueOf(randomCents);

            int stok = random.nextInt(1, 101);

            Product product = new Product();
            product.setNamaProduk(nama);
            product.setKategori(kategori);
            product.setHarga(harga);
            product.setStok(stok);

            newProducts.add(product);
        }

        productRepository.saveAll(newProducts);
        return newProducts.size();
    }


    public List<Product> searchProducts(String query) {
        return productRepository.findByNamaProdukContainingIgnoreCaseOrKategoriContainingIgnoreCase(query, query);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void clearAllProducts() {
        productRepository.deleteAll();
    }
}
