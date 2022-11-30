package com.example.suppy_chain_management;

public class Order {
    public static boolean placeSingleOrder(Product product,String customerEmail){
//        String orderQuery  = String.format("insert into orders(quantity,customer_id,status) values(1,(select cid from customer where email = '%s'),%s,'Ordered')",customerEmail,product.getId());
        String orderQuery = String.format("INSERT INTO orders (quantity, customer_id, product_id, status) VALUES (1, (SELECT cid FROM customer WHERE email = '%s'), %s, 'ORDERED')",
                customerEmail, product.getId()
        );
        Database_Connection dbConn = new Database_Connection();
        //dbConn.insertData(orderQuery);
        if(dbConn.insertData(orderQuery)>=1){
            return true;
        }
        System.out.println(orderQuery);
        return false;
    }
}
