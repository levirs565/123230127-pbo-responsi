package id.my.levirs.responsi.data;

import id.my.levirs.responsi.data.datasource.ProductDataSource;
import id.my.levirs.responsi.data.repository.ProductRepository;

import java.sql.DriverManager;
import java.sql.SQLException;

public class RepositoryProvider {
    private static final String sURL = "jdbc:mysql://localhost:3306/123230127_pbo_responsi";
    private static final String sUSER = "root";
    private static final String sPASSWORD = "";

    private static final RepositoryProvider sInstance = new RepositoryProvider();
    public static RepositoryProvider get() {
        return sInstance;
    }

    ProductDataSource mProductDataSource;

    private RepositoryProvider() {
        try {
            var connection = DriverManager.getConnection(sURL, sUSER, sPASSWORD);
            mProductDataSource = new ProductDataSource(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ProductRepository getProductRepository() {
        return mProductDataSource;
    }
}
