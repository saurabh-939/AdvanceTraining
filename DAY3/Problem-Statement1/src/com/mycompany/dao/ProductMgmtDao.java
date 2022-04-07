package com.mycompany.dao;



import java.sql.*;
import java.util.*;
import com.mycompany.dbutil.DBUtil;
import com.mycompany.domain.Product;

public class ProductMgmtDao {

    //get all products method.  used List instead of ArrayList so it's better code management
    //incase we want to change productList ArrayList to a LinkedList, we don't have to change the jdbc code that much
    public List<Product> getAllProducts()
    {
        List<Product> productList = new ArrayList<Product>();
        try
        {
           
            Connection conn = DBUtil.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM products");
            while(rs.next())
            {
                Product product = new Product(rs.getString("prod_id"), rs.getString("prod_name"), rs.getInt("prod_price"));
                productList.add(product);
            }
            DBUtil.closeConnection(conn);  
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return productList;
    }

  
    public Product getProductByid(String productId)
    {
        Product product = null;
        try
        {
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM products WHERE prod_id = ?");
            ps.setString(1, productId);
            ResultSet rs = ps.executeQuery();
            //iterate through result
            while(rs.next())
            {
                product = new Product(rs.getString("prod_id"), rs.getString("prod_name"), rs.getInt("prod_price"));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return product;
    }

    public int addProduct(Product product)
    {
      
        int status = 0;
        try
        {
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO products VALUES(?,?,?)");
         
            ps.setString(1, product.getProductid());
            ps.setString(2, product.getProductName());
            ps.setInt(3, product.getProductPrice());
            status = ps.executeUpdate(); 
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return status;
    }

    
    public int updateProduct(Product product)
    {
        int status = 0;
        try
        {
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE product SET prod_name=?, prod_price=? WHERE prod_id=?");
           
            ps.setString(1, product.getProductName());
            ps.setInt(2, product.getProductPrice());
            ps.setString(3, product.getProductid());
            status = ps.executeUpdate();  
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return status;
    }

   
    public int deleteProduct(String productId)
    {
        int status = 0;
        try
        {
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM products where prod_id = ?");
          
            ps.setString(1, productId);
            status = ps.executeUpdate();  

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return status;
    }

}
