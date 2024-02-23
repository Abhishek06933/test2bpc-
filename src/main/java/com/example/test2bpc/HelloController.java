package com.example.test2bpc;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class HelloController implements Initializable {
    public TextField AProductID;
    public TextField AProductName;
    public TextField AProductPrice;
    public TextField AProductQuantity;
    public Label welcomeText;
    public Button Load;
    @FXML
    private TableView<Inventory> tableView;
    @FXML
    private TableColumn<Inventory, Integer> ProductID;
    @FXML
    private TableColumn<Inventory, String> ProductName;
    @FXML
    private TableColumn<Inventory, Integer> ProductPrice;
    @FXML
    private TableColumn<Inventory, Integer> ProductQuantity;
    ObservableList<Inventory> list = FXCollections.observableArrayList();
    private ActionEvent actionEvent;

    public HelloController() {
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.ProductID.setCellValueFactory(new PropertyValueFactory<>("ProductID"));
        this.ProductName.setCellValueFactory(new PropertyValueFactory<>("ProductName"));
        this.ProductPrice.setCellValueFactory(new PropertyValueFactory<>("ProductPrice"));
        this.ProductQuantity.setCellValueFactory(new PropertyValueFactory<>("ProductQuantity"));
        this.tableView.setItems(this.list);
    }

    @FXML
    protected void onHelloButtonClick() {
        this.populateTable();
    }

    public void populateTable() {
        this.list.clear();
        String jdbcUrl = "jdbc:mysql://localhost:3306/test2bpc";
        String dbUser = "root";
        String dbPassword = "";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

            try {
                String query = "SELECT * FROM `tbl_product_inventory`";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                while(resultSet.next()) {
                    int ProductID = resultSet.getInt("ProductID");
                    String ProductName = resultSet.getString("ProductName");
                    int ProductPrice = resultSet.getInt("ProductPrice");
                    int ProductQuantity = resultSet.getInt("ProductQuantity");
                    this.tableView.getItems().add(new Inventory(ProductID, ProductName, ProductPrice, ProductQuantity));
                }
            } catch (Throwable var14) {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (Throwable var13) {
                        var14.addSuppressed(var13);
                    }
                }

                throw var14;
            }

            connection.close();
        } catch (SQLException var15) {
            var15.printStackTrace();
        }

    }

    public void InsertData() {
        InsertData(null);
    }

    public void InsertData(ActionEvent ignoredActionEvent) {
        String AProductID = this.AProductID.getText();
        String AProductName = this.AProductName.getText();
        String AProductPrice = this.AProductPrice.getText();
        String AProductQuantity = this.AProductQuantity.getText();
        this.InserTable(AProductID, AProductName, AProductPrice, AProductQuantity);
    }

    public void InserTable(String AProductID, String AProductName, String AProductPrice, String AProductQuantity) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/test2bpc";
        String dbUser = "root";
        String dbPassword = "";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

            try {
                String query = "INSERT INTO `tbl_product_inventory`( `ProductID`, `ProductName`, `ProductPrice`, `ProductQuantity`) VALUES ('" + AProductID + "','" + AProductName + "','" + AProductPrice + "','" + AProductQuantity + "')";
                Statement statement = connection.createStatement();
                statement.execute(query);
            } catch (Throwable var12) {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (Throwable var11) {
                        var12.addSuppressed(var11);
                    }
                }

                throw var12;
            }

            connection.close();
        } catch (SQLException var13) {
            var13.printStackTrace();
        }

    }
    public void DeleteData() {
        DeleteData(null);
    }
    private void DeleteData(ActionEvent actionEvent) {
        this.actionEvent = actionEvent;
        String AProductID = this.AProductID.getText();
        String jdbcUrl = "jdbc:mysql://localhost:3306/test2bpc";
        String dbUser = "root";
        String dbPassword = "";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

            try {
                String query = "DELETE FROM `tbl_product_inventory` WHERE ProductID='" + AProductID + "'";
                Statement statement = connection.createStatement();
                statement.execute(query);
            } catch (Throwable var10) {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (Throwable var9) {
                        var10.addSuppressed(var9);
                    }
                }

                throw var10;
            }

            connection.close();
        } catch (SQLException var11) {
            var11.printStackTrace();
        }

    }

    public void UpdateData() {
        UpdateData(null);
    }

    public void UpdateData(ActionEvent actionEvent) {
        String AProductID = this.AProductID.getText();
        String AProductName = this.AProductName.getText();
        String AProductPrice = this.AProductPrice.getText();
        String AProductQuantity = this.AProductQuantity.getText();
        String jdbcUrl = "jdbc:mysql://localhost:3306/test2bpc";
        String dbUser = "root";
        String dbPassword = "";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

            try {
                String query = "UPDATE `tbl_product_inventory` SET `ProductName`='" + AProductName + "',`ProductPrice`='" + AProductPrice + "',`ProductQuantity`='" + AProductQuantity + "' WHERE ProductID='" + AProductID + "'";
                Statement statement = connection.createStatement();
                statement.execute(query);
                this.populateTable();
            } catch (Throwable var14) {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (Throwable var13) {
                        var14.addSuppressed(var13);
                    }
                }

                throw var14;
            }

            connection.close();
        } catch (SQLException var15) {
            var15.printStackTrace();
        }

    }

    public void LoadData() {
        LoadData(null);
    }

    public void LoadData(ActionEvent actionEvent) {
        String AProductID = this.AProductID.getText();
        String jdbcUrl = "jdbc:mysql://localhost:3306/test2bpc";
        String dbUser = "root";
        String dbPassword = "";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

            try {
                String query = "SELECT * FROM `tbl_product_inventory` WHERE ProductID='" + AProductID + "'";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                while(resultSet.next()) {
                    int ProductID = resultSet.getInt("ProductID");
                    String ProductName = resultSet.getString("ProductName");
                    int ProductPrice = resultSet.getInt("ProductPrice");
                    int ProductQuantity = resultSet.getInt("ProductQuantity");
                    this.AProductName.setText(ProductName);
                    this.AProductPrice.setText(String.valueOf(ProductPrice));
                    this.AProductQuantity.setText(String.valueOf(ProductQuantity));
                }
            } catch (Throwable var16) {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (Throwable var15) {
                        var16.addSuppressed(var15);
                    }
                }

                throw var16;
            }

            connection.close();
        } catch (SQLException var17) {
            var17.printStackTrace();
        }

    }

    public void set_username(String messge) {
        this.welcomeText.setText(messge);
    }
}
