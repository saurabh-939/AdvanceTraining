package com.mycompany.app;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import com.mycompany.dao.ProductMgmtDao;
import com.mycompany.domain.Product;

public class ProductMgmt {


    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
    static ProductMgmtDao dao = new ProductMgmtDao();

    public static void main(String[] args) throws Exception
    {
        String option = "";

        do
        {
            System.out.println("A. View Products");
            System.out.println("B. Add Product");
            System.out.println("C. Update Product");
            System.out.println("D. Delete Product");
            System.out.println("E. Search Product");
            System.out.println("F. Exit");
            System.out.println("-------------------------------------------");
            System.out.println("Enter an option");
            System.out.println("--------------------------------------------");
           
            option = br.readLine();
            System.out.println("\n");

            switch(option.toUpperCase())
            {
                case "A":
                    viewProducts();
                    break;

                case "B":
                    addProduct();
                    break;

                case "C":
                    updateProduct();
                    break;

                case "D":
                    deleteProduct();
                    break;

                case "E":
                    searchProduct();
                    break;

                case "F":
                    System.out.println("----------Thank You-----------");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid Option! Please enter again");
                    break;
            }
        }while(!option.equals("F"));
    }
    public static void viewProducts()
    {
        System.out.println("-----------------------------------------------");

       
        List<Product> productList = dao.getAllProducts();
        for(Product product: productList)
        {
           
            displayProduct(product);
        }
        System.out.println("-----------------------------------------------");
        System.out.println("\n");

    }

    public static void addProduct() throws Exception
    {
        System.out.println("------------------------------------------------");
        System.out.println("Enter Product ID:");
        System.out.println("------------------------------------------------");
        String productId = br.readLine();
        System.out.println("------------------------------------------------");
        System.out.println("Enter Product Name:");
        System.out.println("------------------------------------------------");
        String productName = br.readLine();
        System.out.println("------------------------------------------------");
        System.out.println("Enter Product Price:");
        System.out.println("------------------------------------------------");
        int productPrice = Integer.parseInt(br.readLine());
      
        Product product = new Product(productId, productName,productPrice);
        int status = dao.addProduct(product);
        if(status ==1 )
        {
            System.out.println("Product added successfully");
        }
        else
        {
            System.out.println("ERROR while adding product");
        }
        System.out.println("\n");
    }

 
    public static void updateProduct() throws Exception
    {
        System.out.println("------------------------------------------------");
        System.out.println("Enter Product ID:");
        System.out.println("------------------------------------------------");
        String productId = br.readLine();
        System.out.println("------------------------------------------------");
        System.out.println("Enter New Product Name:");
        System.out.println("------------------------------------------------");
        String productName = br.readLine();
        System.out.println("------------------------------------------------");
        System.out.println("Enter New Product Price:");
        System.out.println("------------------------------------------------");
        int productPrice = Integer.parseInt(br.readLine());
       
        Product product = new Product(productId, productName,productPrice);
        int status = dao.updateProduct(product);
        if(status ==1 )
        {
            System.out.println("Product updated successfully");
        }
        else
        {
            System.out.println("ERROR while updating product");
        }
        System.out.println("\n");

    }

    public static void deleteProduct() throws Exception
    {
        System.out.println("------------------------------------------------");
        System.out.println("Enter Product ID:");
        System.out.println("------------------------------------------------");
        String productId = br.readLine();
        int status = dao.deleteProduct(productId);
        if(status == 1 )
        {
            System.out.println("Product deleted successfully");
        }
        else
        {
            System.out.println("ERROR while deleting product");
        }
        System.out.println("\n");

    }

  
    public static void searchProduct() throws Exception
    {
        System.out.println("------------------------------------------------");
        System.out.println("Enter Product ID:");
        System.out.println("------------------------------------------------");
        String productId = br.readLine();
        Product product = dao.getProductByid(productId);
        displayProduct(product);
        System.out.println("\n");
    }

    public static void displayProduct(Product product)
    {
        System.out.println("Product ID: "+product.getProductid());
        System.out.println("Product Name: "+product.getProductName());
        System.out.println("Product Price: "+product.getProductPrice());
        System.out.println("\n");
    }



}
