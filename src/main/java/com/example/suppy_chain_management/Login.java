package com.example.suppy_chain_management;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.ResultSet;

public class Login {
    Database_Connection dbConn = new Database_Connection();

    public static byte[] getSHA(String input){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return md.digest(input.getBytes(StandardCharsets.UTF_8));

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    static String getEncryptedPassword(String password){
        String encryptedPassword="";
        BigInteger number=new BigInteger(1,getSHA(password));
        StringBuilder hexString  = new StringBuilder(number.toString(16));
        encryptedPassword= hexString.toString();
        return encryptedPassword;
    }
    public boolean customerLogin(String Email , String Password){
        try{
            String query = String.format("Select * from customer where email ='%s' and password ='%s'",Email,getEncryptedPassword(Password));
            ResultSet rs  = dbConn.getQueryTable(query);
            if(rs == null )return false;
            if(rs.next())
            {
                return true;
            }
            else return false;

        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }

    }

//    //public static void main(String[] args) {
//        System.out.println(getEncryptedPassword("123"));
//    }
}
