package data;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import tools.MyTool;
public class ProductList extends ArrayList<Product>{
    private String dataFile = "";
    boolean changed = false;
    private void loadProductFromFile() {
        List<String> lines = MyTool.readLinesFromFile(dataFile);
        for (String line : lines) {
            this.add(new Product(line));
        }
    }
    public void initWithFile() {
        Config cR = new Config();
        dataFile = cR.getProductFile();
        loadProductFromFile();
    }

    public void writeProductToFile() {
        if (changed) {
            MyTool.writeFile(dataFile, this);
            changed = false;
        }
    }

    public boolean isChanged() {
        return changed;
    }

    public void printAllProduct() {
        if (this.isEmpty()) {
            System.out.println("Empty list!");
        } else {
            System.out.println("+----------+----------+--------------------+---------------+--------------------------------+");
            System.out.println("|    ID    |          PRODUCT NAME          |     UNIT PRICE     |  QUANTITY  |    STATUS    |");
            System.out.println("+----------+----------+--------------------+---------------+--------------------------------+");
            for (Product product : this) {
                System.out.format("|%-10s|%-32s|%-20s|%-12s|%-14s|\n", product.getProductID(), product.getProductName(), product.getUnitPrice(), product.getQuantity(), product.getStatus());
            }
            System.out.println("+----------+----------+--------------------+---------------+--------------------------------+");
        }
    }

    public int checkID(String ID){
        if (this.isEmpty()){
            return -1;
        }
        for (int i=0; i<this.size(); i++){
            if (this.get(i).getProductID().equalsIgnoreCase(ID)){
                return i;
            }
        }
        return -1;
    }

    public void searchProduct(){
        String find = MyTool.readPattern("Enter product name: ", Product.PRODUCT_FORMAT);
        ArrayList<Product>  pList = new ArrayList<Product>();
        for (Product product : this) {
            if (product.getProductName().toLowerCase().contains(find.toLowerCase())) {
                pList.add(product);
            }
        }
        if (pList.size() == 0) System.out.println("Not found!");
        else{
        System.out.println("+----------+----------+--------------------+---------------+--------------------------------+");
        System.out.println("|    ID    |          PRODUCT NAME          |     UNIT PRICE     |  QUANTITY  |    STATUS    |");
        System.out.println("+----------+----------+--------------------+---------------+--------------------------------+");
        for (Product product : pList) {
                System.out.format("|%-10s|%-32s|%-20s|%-12s|%-14s|\n", product.getProductID(), product.getProductName(), product.getUnitPrice(), product.getQuantity(), product.getStatus());
            }
        }
        System.out.println("+----------+----------+--------------------+---------------+--------------------------------+");

    }


    public boolean searchProduct(String name){
        for (Product p: this){
            if (p.getProductName().toLowerCase().contains(name.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public void addProduct(){
        String productID;
        String productName;
        double unitPrice;
        int quantity;
        String status;
        int pos;
        do {
            productID = MyTool.readPattern("ID of new Product, format [P000]", Product.ID_FORMAT);
            productID = productID.toUpperCase();
            pos = checkID(productID);
            if (pos>=0){
                System.out.println("ID is duplicated!");
            }
        } while (pos>=0);

        do {
            productName = MyTool.readPattern("Name of new Product", Product.PRODUCT_FORMAT);
            if (searchProduct(productName)){
                System.out.println("Product name is duplicated!");
            }
        } while (searchProduct(productName));

        unitPrice = MyTool.readRangeDouble("Price of new Product", 0,10000);
        quantity = MyTool.readRangeInt("Quantity of new Product", 0, 1000);
        status = MyTool.readStatus("Status of new product: Available or Not Available");
        Product p = new Product(productID,productName,unitPrice,quantity,status);
        this.add(p);
        System.out.println("New product has been added.");
        changed = true;
    }

    public void checkExistProduct(){
        boolean check = false;
        String find = MyTool.readPattern("Enter product name you want to check", Product.PRODUCT_FORMAT);
        for (Product p: this){
            if (p.getProductName().toLowerCase().equals(find.toLowerCase())) {
                check = true;
            }
        }
        if (check == true){
            System.out.println("Product exists!");
        }
        else {
            System.out.println("Product not found!");
        }
    }

    public void updateProduct(){
        String productID = MyTool.readPattern("Enter ID of product that needs updating:", Product.ID_FORMAT);
        int pos = checkID(productID);
        String name;
        double price;
        int quantity;
        String status;
        if (pos < 0) {
            System.out.println("Product not found!");
        }
        else {
            do {
                name = MyTool.readPattern("Name of new product:", Product.PRODUCT_FORMAT);
                if (searchProduct(name)){
                    System.out.println("Product name is duplicated!");
                }
            } while (searchProduct(name));
            price = MyTool.readRangeDouble("Price of new product", 0, 10000);
            quantity = MyTool.readRangeInt("Quantity of new product", 0,1000);
            status = MyTool.readStatus("Status of new product: Available or Not Available.");
            this.get(pos).setProductID(productID);
            this.get(pos).setProductName(name);
            this.get(pos).setUnitPrice(price);
            this.get(pos).setQuantity(quantity);
            this.get(pos).setStatus(status);
            System.out.println("New product has been updated.");
        }
        changed = true;
    }

    public void deleteProduct() {
        String productID = MyTool.readPattern("Enter ID of product needs removing", Product.ID_FORMAT);
        int pos = checkID(productID);
        if (pos < 0) {
            System.out.println("Not found!");
        } else {
            this.remove(pos);
            System.out.println("Product has been deleted.");
        }
        changed = true;
    }

    public void printAllProductInFile() throws ParseException {
        List<String> lines = MyTool.readLinesFromFile(dataFile);
        ArrayList<Product> list = new ArrayList<>();
        for (String line : lines) {
            list.add(new Product(line));
        }
        Collections.sort(list);
        System.out.println("+----------+----------+--------------------+---------------+--------------------------------+");
        System.out.println("|    ID    |          PRODUCT NAME          |     UNIT PRICE     |  QUANTITY  |    STATUS    |");
        System.out.println("+----------+----------+--------------------+---------------+--------------------------------+");
        for (Product product : list) {
            System.out.format("|%-10s|%-32s|%-20s|%-12s|%-14s|\n", product.getProductID(), product.getProductName(), product.getUnitPrice(), product.getQuantity(), product.getStatus());
        }
        System.out.println("+----------+----------+--------------------+---------------+--------------------------------+");

    }
}
    

