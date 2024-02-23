package com.example.test2bpc;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    public TextField Email;
    public PasswordField Password;
    public Label Message;

    public LoginController() {
    }

    public void LoginClick(ActionEvent actionEvent) {
        this.Message.setText("");
        String EmailAdd = this.Email.getText();
        String gvnPass = this.Password.getText();
        if (!EmailAdd.equals("") && !gvnPass.equals("")) {
            String jdbcUrl = "jdbc:mysql://localhost:3306/test2bpc";
            String dbUser = "root";
            String dbPassword = "";

            try {
                Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

                try {
                    String query = "SELECT * FROM `tbl_user_login` WHERE email='" + EmailAdd + "'";
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);
                    if (resultSet.next()) {
                        int UserID = resultSet.getInt("UserID");
                        String UserName = resultSet.getString("UserName");
                        String UserEmail = resultSet.getString("UserEmail");
                        String UserPassword = resultSet.getString("UserPassword");
                        if (UserPassword.equals(gvnPass)) {
                            this.Message.setText("Success");

                            try {
                                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("login-view.fxml"));
                                Parent secondScene = (Parent)loader.load();
                                HelloController userController = (HelloController) loader.getController();
                                userController.set_username("Welcome" + UserName);
                                Stage secondStage = new Stage();
                                secondStage.setTitle("Employee Scene");
                                secondStage.setScene(new Scene(secondScene));
                                Stage firstSceneStage = (Stage)this.Password.getScene().getWindow();
                                firstSceneStage.close();
                                secondStage.show();
                            } catch (IOException var21) {
                                var21.printStackTrace();
                            }
                        } else {
                            this.Message.setText("Invalid email or password");
                        }
                    } else {
                        this.Message.setText("Invalid email or password");
                    }
                } catch (Throwable var22) {
                    if (connection != null) {
                        try {
                            connection.close();
                        } catch (Throwable var20) {
                            var22.addSuppressed(var20);
                        }
                    }

                    throw var22;
                }

                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException var23) {
                var23.printStackTrace();
            }
        } else {
            this.Message.setText("Please Give Email or Password");
        }

    }
}

