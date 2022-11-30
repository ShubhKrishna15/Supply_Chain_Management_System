package com.example.suppy_chain_management;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class SupplyChain extends Application {
    Pane bodyPane = new Pane();
    Login login = new Login();
    Label loginLabel ;
    Button orderButton;
    public static final int height = 600,width=700,upperLine=50;
    ProductDetails productDetails = new ProductDetails();
    boolean loggedIn = false;
    private GridPane headbar(){
        GridPane gridPane = new GridPane();
        gridPane.setPrefSize(width-10,upperLine-15);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(5);


        TextField searchText = new TextField();
        searchText.setMinWidth(250);
        searchText.setPromptText("Please search here");

         loginLabel  = new Label("Please Login!!!");

        Button loginButton = new Button("Login");

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(loggedIn== false){
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(loginPage());
                //loggedIn=true;
                }
            }
        });

        Button searchButton = new Button("Search");
        gridPane.add(searchText,0,0);
        gridPane.add(searchButton,1,0);
        gridPane.add(loginLabel,2,0);
        gridPane.add(loginButton,3,0);
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
               // bodyPane.getChildren().add(productDetails.getAllProduct());
                String search  = searchText.getText();
                bodyPane.getChildren().add(productDetails.getProductbyName(search));
            }
        });

        return gridPane;
    }

    private GridPane footerBar(){
        GridPane gridPane = new GridPane();
        orderButton = new Button("Buy Now");
        gridPane.add(orderButton,0,0);
        gridPane.setMinWidth(width);
        gridPane.setTranslateY(height+80);

        orderButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                productDetails.getSelectedProducts();

                if(loggedIn==false){
                    bodyPane.getChildren().clear();
                    bodyPane.getChildren().add(loginPage());
                }
                else{
                    Product product  = productDetails.getSelectedProducts();
                    if(product != null){
                        String email = loginLabel.getText();
                        email  = email.substring(9);
                        System.out.println(email);
                        if(Order.placeSingleOrder(product,email)){
                            System.out.println("order placed");
                        }
                        else{
                            System.out.println("order placing fail");
                        }
                    }
                    else{
                        System.out.println("Please select a product");
                    }
                }
            }
        });


        return gridPane;
    }


    private GridPane loginPage(){
        Label EmailLabel = new Label("Email");
        Label PasswordLabel = new Label("Password");
        Label Displaymsg  = new Label("See here");
        TextField  EmailTextField  = new TextField();
        EmailTextField.setPromptText("Enter Email");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter Valid Password");
        Button loginButton = new Button("Login");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String email = EmailTextField.getText();
                String password = passwordField.getText();
                if(login.customerLogin(email,password))
                {
                    loginLabel.setText("Welcome :" + email);
                    bodyPane.getChildren().clear();
                    bodyPane.getChildren().add(productDetails.getAllProduct());
                    loggedIn = true;
                   Displaymsg.setText("Login succesful");
                }
                else {
                    Displaymsg.setText("Try Again, Invalid Inputs");
                }


            }
        });

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(bodyPane.getMinWidth(),bodyPane.getMinHeight()); // doubt
        gridPane.setAlignment(Pos.CENTER);

        gridPane.setVgap(10);
        gridPane.setHgap(30);
        gridPane.add(EmailLabel,0,0);
        gridPane.add(PasswordLabel,0,1);
        gridPane.add(EmailTextField,1,0);
        gridPane.add(passwordField,1,1);
        gridPane.add(Displaymsg,1,4);
        gridPane.add(loginButton,0,4);

        return gridPane;

    }
    Pane CreateContent(){
        Pane root = new Pane();
        root.setPrefSize(width,height+upperLine+80);
        bodyPane.setTranslateY(upperLine);
        root.getChildren().addAll(bodyPane,headbar(),footerBar());
        bodyPane.setMinSize(width,height);
        bodyPane.getChildren().addAll(productDetails.getAllProduct());

        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
       // FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(CreateContent());
        stage.setTitle("Supply chain Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}