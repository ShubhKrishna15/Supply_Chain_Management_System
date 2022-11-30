package com.example.suppy_chain_management;
import java.sql.*;

public class Database_Connection {
    private static final String DB_URL="jdbc:mysql://localhost:3306/chainsupplysystem_database.";
    private static final String USER = "root";
    private static final String PASS = "0o0o0o0o0o";
    public Statement getStatement(){
        Statement statement = null;
        Connection conn;
        try{
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            statement = conn.createStatement();
        }catch(Exception e){
            e.printStackTrace();
        }
        return statement;
    }
    public ResultSet getQueryTable (String query){
        Statement statement  = getStatement();
        try{
            return statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public int insertData (String query){
        Statement statement  = getStatement();
        try{
            return statement.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

//    public static void main(String[] args) {
//        Database_Connection dbConn = new Database_Connection();
//
//        String query = "SELECT * FROM customer";
//        ResultSet rs =  dbConn.getQueryTable(query);
//        try{
//            while (rs!= null && (rs).next()){
//                System.out.println("Fetched result");
//                System.out.print("cid "+ ( rs).getInt("cid")+"name: " + (rs).getString("First_name")+
//                        "email"+( rs).getString("email") );
//            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
}
