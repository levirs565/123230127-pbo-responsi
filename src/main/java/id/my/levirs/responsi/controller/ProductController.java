package id.my.levirs.responsi.controller;

import id.my.levirs.responsi.data.RepositoryProvider;
import id.my.levirs.responsi.data.model.Product;
import id.my.levirs.responsi.data.repository.ProductRepository;
import id.my.levirs.responsi.ui.ProductView;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ProductController {
    private final ProductRepository mRepository;
    private final ProductView mView;
    private Product mSelectedProduct = null;
    private List<Product> mProductList = List.of();
    private final DefaultTableModel mTableModel = new DefaultTableModel(
            new String[]{"Nama Produk", "Biaya Tenaga Kerja", "Efisiensi Produk", "Total Biaya Produksi"},
            0
    ) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public ProductController(ProductView view) {
        mView = view;
        mRepository = RepositoryProvider.get().getProductRepository();

        mView.setTableModel(mTableModel);
        refresh();
    }

    private void refresh() {
        mTableModel.setRowCount(0);
        mProductList = mRepository.getAll();
        for (var data : mProductList) {
            mTableModel.addRow(new Object[]{
                    data.getNama(),
                    data.getBiayaTenagaKerja(),
                    data.getEfficiency(),
                    data.getTotalBiaya()
            });
        }

        clearSelection();
    }

    public void setSelection(int index) {
        mSelectedProduct = mProductList.get(index);
        mView.updateSelected(mSelectedProduct);
    }

    public void clearSelection() {
        mSelectedProduct = null;
        mView.updateSelected(mSelectedProduct);
    }

    private int parseIntThrowable(String str, String nama) throws ControllerException {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new ControllerException(String.format("%s bukan angka valid", nama));
        }
    }

    private Product makeProduct(String id, String nama, String jumlahUnit, String jamKerja, String jumlahTenagaKerja, String biayaBahanBaku) throws ControllerException {
        if (nama.isEmpty())
            throw new ControllerException("Nama tidak boleh kosong");

        return new Product(
                id,
                nama,
                parseIntThrowable(jumlahUnit, "Jumlah Unit"),
                parseIntThrowable(jamKerja, "Jam Kerja"),
                parseIntThrowable(jumlahTenagaKerja, "Jumlah Tenaga Kerja"),
                parseIntThrowable(biayaBahanBaku, "Biaya Bahan Baku")
        );
    }

    private void validateProduct(Product product) throws ControllerException {
        if (product.getEfficiency() < 20) {
            throw new ControllerException(String.format("Efficiency kurang dari 20. (%f)", product.getEfficiency()));
        }
    }

    public void add(String nama, String jumlahUnit, String jamKerja, String jumlahTenagaKerja, String biayaBahanBaku) {
        try {
            var product = makeProduct(null, nama, jumlahUnit, jamKerja, jumlahTenagaKerja, biayaBahanBaku);
            validateProduct(product);

            if (mRepository.add(product)) {
                mView.showMessage("Berhasil ditambahkan", false);
                refresh();
            } else {
                throw new ControllerException("Gagal tambah. Lihat Stacktrace. Untuk lebih jelasnya");
            }
        } catch (ControllerException e) {
            mView.showMessage(e.getMessage(), true);
        }
    }

    private void validateSelection() throws ControllerException {
        if (mSelectedProduct == null) {
            throw new ControllerException("Belum ada yang dipilih");
        }
    }

    public void update(String nama, String jumlahUnit, String jamKerja, String jumlahTenagaKerja, String biayaBahanBaku) {
        try {
            validateSelection();
            var product = makeProduct(mSelectedProduct.getId(), nama, jumlahUnit, jamKerja, jumlahTenagaKerja, biayaBahanBaku);
            validateProduct(product);

            if (mRepository.update(product)) {
                mView.showMessage("Berhasil update", false);
                refresh();
            } else {
                throw new ControllerException("Gagal update. Lihat Stacktrace. Untuk lebih jelasnya");
            }
        } catch (ControllerException e) {
            mView.showMessage(e.getMessage(), true);
        }
    }

    public void delete() {
        try {
            validateSelection();

            if (mRepository.delete(mSelectedProduct.getId())) {
                mView.showMessage("Berhasil dihapus", false);
                refresh();
            } else {
                throw new ControllerException("Gagal dihapus. Lihat Stacktrace. Untuk lebih jelasnya");
            }
        } catch (ControllerException e) {
            mView.showMessage(e.getMessage(), true);
        }
    }
}
