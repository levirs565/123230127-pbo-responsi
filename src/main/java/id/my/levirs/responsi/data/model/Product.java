package id.my.levirs.responsi.data.model;

public class Product {
    private String mId, mNama;
    private int mJumlahUnit, mJamKerja, mJumlahTenagaKerja, mBiayaBahanBaku;

    public Product(String id, String nama, int jumlahUnit, int jamKerja, int jumlahTenagaKerja, int biayaBahanBaku) {
        mId = id;
        mNama = nama;
        mJumlahUnit = jumlahUnit;
        mJamKerja = jamKerja;
        mJumlahTenagaKerja = jumlahTenagaKerja;
        mBiayaBahanBaku = biayaBahanBaku;
    }

    public String getId() {
        return mId;
    }

    public String getNama() {
        return mNama;
    }

    public int getJumlahUnit() {
        return mJumlahUnit;
    }

    public int getJamKerja() {
        return mJamKerja;
    }

    public int getJumlahTenagaKerja() {
        return mJumlahTenagaKerja;
    }

    public int getBiayaBahanBaku() {
        return mBiayaBahanBaku;
    }

    public int getBiayaTenagaKerja() {
        return mJamKerja * mJumlahTenagaKerja * 15000;
    }

    public double getEfficiency() {
        return (double) mJumlahUnit / (mJamKerja * mJumlahTenagaKerja) * 100;
    }

    public int getTotalBiaya() {
        return mBiayaBahanBaku + getBiayaTenagaKerja();
    }
}
