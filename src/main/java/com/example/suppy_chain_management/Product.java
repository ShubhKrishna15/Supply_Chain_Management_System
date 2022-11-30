package com.example.suppy_chain_management;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class Product {
    public SimpleIntegerProperty id;
    public SimpleStringProperty name;
    public SimpleDoubleProperty price;

    Product(int id, String name , double price){
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
    }

    public static ObservableList<Product> getAllProducts(){
        Database_Connection dbConn = new Database_Connection();
        ObservableList<Product> data = FXCollections.observableArrayList();
        String selectAllProduct = "Select * FROM product";
        try{
            ResultSet rs = dbConn.getQueryTable(selectAllProduct);
            while(rs.next()){
                data.add(new Product(rs.getInt("pid"),rs.getString("name"),rs.getDouble("price")));

            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return data;
    }

    public static ObservableList<Product> getProductsByName(String name){
        Database_Connection dbConn = new Database_Connection();
        ObservableList<Product> data = FXCollections.observableArrayList();
        String selectAllProduct = String.format( "Select * FROM product where LOWER(name) like '%%%s%%' ",name.toLowerCase());
        try{
            ResultSet rs = dbConn.getQueryTable(selectAllProduct);
            while(rs.next()){
                data.add(new Product(rs.getInt("pid"),rs.getString("name"),rs.getDouble("price")));

            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return data;
    }

    public int getId(){
        return id.get();
    }
    public String getName(){
        return name.get();
    }
    public double getPrice(){
        return price.get();
    }
}
