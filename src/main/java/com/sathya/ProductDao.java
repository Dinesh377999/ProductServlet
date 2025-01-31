package com.sathya;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
	public int saveProduct(Product product) {
		//Declare the resources 
		Connection connection = null; 
		PreparedStatement preparedStatement = null;
		int saveResult = 0;
		
		try
		{ connection = DatabaseUtils.createConnection();
		  preparedStatement = connection.prepareStatement
				  ("insert into products values(?,?,?,?,?,?,?,?)");
		  preparedStatement.setString(1, product.getProId());
		  preparedStatement.setString(2, product.getProName());
              
		  preparedStatement.setDouble(3, product.getProPrice());
		  preparedStatement.setString(4, product.getProBrand());
		  preparedStatement.setString(5, product.getProMadeIn());
             
		  preparedStatement.setDate(6, product.getProMfgDate());
		  preparedStatement.setDate(7, product.getProExpDate());
              
		  preparedStatement.setBinaryStream(8, product.getProImage());
		  saveResult = preparedStatement.executeUpdate();
	      } catch (SQLException e) {
	          e.printStackTrace();
	      }	
		  finally {
			  try {
				  if(connection!=null) 
					  connection.close();
				  if(preparedStatement!=null) 
					  preparedStatement.close(); 
			  }
			  catch(SQLException e)
			  {	e.printStackTrace();
			  }
		  }
		  return saveResult;
	}
	
	public List<Product> displayListOfProducts() {
        List<Product> productList = new ArrayList<Product>();
        //try-with resources :: Resources released automatically
        try(Connection connection = DatabaseUtils.createConnection();
        	Statement statement = connection.createStatement();) 
        {	
        	//Read the data from resultSet store into ResultSet 
            ResultSet resultSet = statement.executeQuery("select * from products");
            
            //Read the data from ResultSet set to Product object & store into List  
            //The loop repeated how many records present in table. 
            while (resultSet.next()) {
	         	  Product product = new Product();
	         	  product.setProId(resultSet.getString("proId"));
				  product.setProName(resultSet.getString("proName"));
				  product.setProPrice(Double.parseDouble(resultSet.getString("proPrice")));
				  product.setProBrand(resultSet.getString("proBrand"));
				  product.setProMadeIn(resultSet.getString("proMadeIn"));
				  product.setProMfgDate(Date.valueOf(resultSet.getString("proMfgdate")));
				  product.setProExpDate(Date.valueOf(resultSet.getString("proExpDate")));
				  product.setProImage(resultSet.getBinaryStream("proImage"));
				  productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }
	
	public int deleteProduct(int proId) {
		int result = 0;
		try(Connection connection = DatabaseUtils.createConnection()) 
		{	PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM products WHERE proId = ?");
			preparedStatement.setInt(1, proId);
		    result = preparedStatement.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	 public Product getProductById(String proId) {
		Product product = null; 
	 	try(Connection connection = DatabaseUtils.createConnection()) 
	 	{   // SQL query to retrieve product details by ID
            String sql = "SELECT * FROM products WHERE proId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, proId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
        	  product = new Product();
         	  product.setProId(resultSet.getString("proId"));
			  product.setProName(resultSet.getString("proName"));
			  product.setProPrice(Double.parseDouble(resultSet.getString("proPrice")));
			  product.setProBrand(resultSet.getString("proBrand"));
			  product.setProMadeIn(resultSet.getString("proMadeIn"));
			  product.setProMfgDate(Date.valueOf(resultSet.getString("proMfgdate")));
			  product.setProExpDate(Date.valueOf(resultSet.getString("proExpDate")));
			  product.setProImage(resultSet.getBinaryStream("proImage"));
            }
        }
	      catch (SQLException e) {
	            e.printStackTrace();
	        } 
	      return product; 
	    }	 
	 
	 
	 public int updateProduct(Product updatedProduct) {
	    int result = 0;
	        try(Connection connection = DatabaseUtils.createConnection())
	        {
	             // SQL query to update product details
	            String sql = "UPDATE products SET proName=?, proPrice=?, proBrand=?, proMadeIn=?, " +
	                    "proMfgDate=?, proExpDate=?, proImage=? WHERE proId=?";
	            
	            PreparedStatement preparedStatement = connection.prepareStatement(sql);
	                // Set the parameters for the update statement
	            preparedStatement.setString(1, updatedProduct.getProName());
	            preparedStatement.setDouble(2, updatedProduct.getProPrice());
	            preparedStatement.setString(3, updatedProduct.getProBrand());
	            preparedStatement.setString(4, updatedProduct.getProMadeIn());
	            preparedStatement.setDate(5, updatedProduct.getProMfgDate());
	            preparedStatement.setDate(6, updatedProduct.getProExpDate());
	            preparedStatement.setBinaryStream(7, updatedProduct.getProImage());
	            preparedStatement.setString(8, updatedProduct.getProId());

	                // Execute the update
	                result = preparedStatement.executeUpdate();
	            }
	         catch (SQLException e) {
	            e.printStackTrace();
	        } 
	        return result;
	    }
}