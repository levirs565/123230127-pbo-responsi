package id.my.levirs.responsi.data.datasource;

import id.my.levirs.responsi.data.model.Product;
import id.my.levirs.responsi.data.repository.ProductRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDataSource implements ProductRepository {
    private Connection mConnection;

    public ProductDataSource(Connection connection) {
        mConnection = connection;
    }

    @Override
    public List<Product> getAll() {
        var result = new ArrayList<Product>();
        try (var stmt = mConnection.prepareStatement("SELECT * FROM product")) {
            var rs = stmt.executeQuery();
            while (rs.next()) {
                result.add(new Product(
                        rs.getString("id"),
                        rs.getString("nama"),
                        rs.getInt("jumlah_unit"),
                        rs.getInt("jam_kerja"),
                        rs.getInt("jumlah_tenaga_kerja"),
                        rs.getInt("biaya_bahan_baku")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean add(Product product) {
        try (var stmt = mConnection.prepareStatement("INSERT INTO " +
                "product(nama, jumlah_unit, jam_kerja, jumlah_tenaga_kerja, biaya_bahan_baku) " +
                "VALUES (?, ?, ?, ?, ?)")) {
            stmt.setString(1, product.getNama());
            stmt.setInt(2, product.getJumlahUnit());
            stmt.setInt(3, product.getJamKerja());
            stmt.setInt(4, product.getJumlahTenagaKerja());
            stmt.setInt(5, product.getBiayaBahanBaku());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Product product) {
        try (var stmt = mConnection.prepareStatement("UPDATE product " +
                "SET nama = ?, jumlah_unit = ?, jam_kerja = ?, jumlah_tenaga_kerja = ?, biaya_bahan_baku = ? " +
                "WHERE id = ?")) {
            stmt.setString(1, product.getNama());
            stmt.setInt(2, product.getJumlahUnit());
            stmt.setInt(3, product.getJamKerja());
            stmt.setInt(4, product.getJumlahTenagaKerja());
            stmt.setInt(5, product.getBiayaBahanBaku());
            stmt.setString(6, product.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        try (var stmt = mConnection.prepareStatement("DELETE FROM product WHERE id = ?")) {
            stmt.setString(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
