/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mng;

import tools.MyTool;
import data.Product;
import data.ProductList;
import java.text.ParseException;

/**
 *
 * @author ADMIN
 */
public class ProductMng {

    public static void main(String[] args) throws ParseException {
        boolean cont = false;
        boolean valid = false;

        String[] options = {"Print", "Create a product", "Check exist Product", "Search product's information by name", "Update product", "Delete product", "Save product to file", "Print list Products from the line"};
        Menu mnu = new Menu(options);
        ProductList pList = new ProductList();
        pList.initWithFile();
        int choice = 0;
        do {
            choice = mnu.getChoice("Managing products");
            switch (choice) {
                case 1:
                    pList.printAllProduct();
                    break;
                case 2:
                    pList.addProduct();
                    break;
                case 3:
                    pList.checkExistProduct();
                    break;
                case 4:
                    pList.searchProduct();
                    break;
                case 5:
                    pList.updateProduct();
                    break;
                case 6:
                    pList.deleteProduct();
                    break;
                case 7:
                    pList.writeProductToFile();
                    break;
                case 8:
                    pList.printAllProductInFile();
                    break;
                default:
                    if (pList.isChanged()) {
                        boolean res = MyTool.readBool("Data changed. Write to file?");
                        if (res = true) {
                            pList.writeProductToFile();
                        }
                    }
            }
        } while (choice > 0 && choice < mnu.size());
        System.out.println("Good Bye! Thank you for using my services");
    }
}
