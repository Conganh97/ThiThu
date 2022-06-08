package Controller;

import Models.Product;
import Sort.SortbyAscending;
import Validate.ValiDateProduct;
import io.ReaderAndWriter;

import java.util.ArrayList;
import java.util.Scanner;

public class ProductManager {
    Scanner sc = new Scanner(System.in);
    ArrayList <Product> products = new ArrayList<>();
    ValiDateProduct valiDateProduct = new ValiDateProduct();

    ReaderAndWriter readerAndWriter = new ReaderAndWriter();

    public void menu (){
        System.out.println("=====Menu=====");
        System.out.println("1.| Display product list");
        System.out.println("2.| Create product");
        System.out.println("3.| Edit product");
        System.out.println("4.| Delete product");
        System.out.println("5.| Sort product list");
        System.out.println("6.| Search the most expensive product");
        System.out.println("7.| Read on file");
        System.out.println("8.| Writen on file");
        System.out.println("9.| Exit!!!");
        int choice = -1;
        try {
            System.out.println("Choose option: ");
            choice = Integer.parseInt(sc.nextLine());
            if (choice < 0 || choice > 8) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.err.println("No option!! choose again!!");
        }

        switch (choice) {
            case 1:
                displayProduct(products);
                break;
            case 2:
                addProduct(createProduct());
                System.out.println("Create success");
                break;
            case 3:
                editProduct();
                break;
            case 4:
                deleteProduct();
                break;
            case 5:
                products.sort(new SortbyAscending());
                System.out.println("Sort success");
                break;
            case 6:
                searchExpensiveProduct();
                break;
            case 7:
                readerAndWriter.read();
                break;
            case 8:
                readerAndWriter.write(products);
                break;
            case 9:
                System.exit(0);
                break;
        }
    }
    public void displayProduct(ArrayList <Product> products){
        for (int i = 0; i < products.size(); i++) {
            if ((i+1) % 5 == 0) {
                sc.nextLine();
                System.out.println(products.get(i));
            } else
                System.out.println(products.get(i));
        }
    }

    public Product createProduct (){
        int Id = valiDateProduct.valiDateId(products);
        String name = valiDateProduct.valiDateName();
        float price = valiDateProduct.valiDatePrice();
        int amount = valiDateProduct.valiDateAmount();
        System.out.println("Enter describe: ");
        String describe = sc.nextLine();
        return new Product(Id, name, price, amount, describe);
    }

    public void addProduct (Product product){
        products.add(product);
    }

    public void editProduct() {
        while (true){
            try {
                System.out.println("Enter Id: ");
                int id = Integer.parseInt(sc.nextLine());
                int index = valiDateProduct.getIndex(id,products);
                if (index != -1){
                    products.set(index, createProduct());
                    return;
                } else {
                    System.err.println("Id not exist");
                    return;
                }
            } catch (Exception e) {
                System.out.println("Enter again.");
            }
        }

    }

    public void deleteProduct (){
        while (true){
            try {
                System.out.println("Enter id: ");
                int Id = Integer.parseInt(sc.nextLine());
                int index = valiDateProduct.getIndex(Id,products);
                if (index == -1) {
                    System.out.println("Not found product to delete");
                } else {
                    products.remove(index);
                    return;}
            } catch (Exception e){
                System.out.println("Enter again");
            }
        }
    }

    public void searchExpensiveProduct (){
        float max = products.get(0).getPrice();
        int index = 0;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getPrice() > max ) {
                max = products.get(i).getPrice();
                index = i;
            }
            System.out.println("The most expensive product is: " + products.get(index).getName() + " price: " + max);
        }
    }

}
