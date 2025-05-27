package id.my.levirs.responsi.data.repository;

import id.my.levirs.responsi.data.model.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> getAll();
    boolean add(Product product);
    boolean update(Product product);
    boolean delete(String id);
}

