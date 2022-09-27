package data;

import tools.MyTool;

public class Product implements Comparable<Product>{
    public static final char SEPARATOR = ',';
    public static final String PRODUCT_FORMAT = "[a-zA-Z0-9\" \"]{5,100}";
    public static final String ID_FORMAT = "P\\d{3}";
    private String productID;
    private String productName;
    private double unitPrice;
    private int quantity;
    private String status;

    public Product(String productID, String productName, double unitPrice, int quantity, String status) {
        this.productID = productID;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.status = status;
    }

    public Product(String line) {
        String[] parts = line.split("" + this.SEPARATOR);
        productID = parts[0].trim();
        productName = parts[1].trim();
        unitPrice = MyTool.parseDouble(parts[2]);
        quantity = MyTool.parseInt(parts[3]);
        status = parts[4].trim();
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return productID + SEPARATOR +  productName + SEPARATOR + unitPrice + SEPARATOR + quantity + SEPARATOR + status + "\n" ;
    }

    @Override
    public int compareTo(Product o) {
        return this.getProductID().compareToIgnoreCase(o.getProductID());
    }
}
