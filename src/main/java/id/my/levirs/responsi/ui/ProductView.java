package id.my.levirs.responsi.ui;

import id.my.levirs.responsi.data.model.Product;

import javax.swing.table.TableModel;

public interface ProductView {
    void setTableModel(TableModel model);
    void updateSelected(Product product);
    void showMessage(String message, boolean isError);
}
