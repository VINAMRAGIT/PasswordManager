/**
 * Sample Skeleton for 'Display_Menu.fxml' Controller Class
 */

package com.example.fx_passmanage_app;

import DataAccess.PassEntry;
import DataAccess.PassManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.io.IOException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.util.ResourceBundle;

public class DisplayControl
{
    boolean confirmRemoval = false;
    private PassManager manager;
    private Stage stage1;
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="Add"
    private Button Add; // Value injected by FXMLLoader

    @FXML // fx:id="Details"
    private AnchorPane Details; // Value injected by FXMLLoader

    @FXML // fx:id="RemoveAcc"
    private Button RemoveAcc; // Value injected by FXMLLoader

    @FXML // fx:id="catDis"
    private TextField catDis; // Value injected by FXMLLoader

    @FXML // fx:id="passDis"
    private TextField passDis; // Value injected by FXMLLoader

    @FXML // fx:id="scroller"
    private ScrollPane scroller; // Value injected by FXMLLoader

    @FXML // fx:id="userDis"
    private TextField userDis; // Value injected by FXMLLoader

    @FXML
    void addAcc(ActionEvent event)
    throws IOException
    {
        Stage innerAdd = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddAcc_Menu.fxml"));
        Parent root = fxmlLoader.load();
        AddAccControl control = fxmlLoader.getController();

        //Setting up controller for further use
        control.setUp(manager);
        control.setStage2(innerAdd);

        //Attaching stage to new scene to display
        innerAdd.setTitle("Add Login");
        innerAdd.setScene(new Scene(root));

        //Showing the new stage and waiting for exit
        innerAdd.showAndWait();
        setLogin();
    }

    private void setLogin()
    {
        VBox vBox = new VBox();
        manager.getPassbook()
               .forEach(passEntry -> {
                   vBox.getChildren()
                       .add(genPane(passEntry));
               });
        scroller.setContent(vBox);
    }

    private Node genPane(PassEntry entry)
    {
        AnchorPane pane = new AnchorPane();
        pane.setPrefWidth(scroller.getPrefWidth());
        pane.setPrefHeight(15);
        pane.setStyle("-fx-background-color: #b8d9d1;");
        Button button = new Button(entry.category() + "\n********");
        button.setStyle("-fx-background-color: #b8d9d1;");
        button.setStyle("-fx-text-fill: #277362;");
        button.setOnAction(event -> {
            button.setStyle("-fx-background-color: #277362;");
            button.setStyle("-fx-text-fill: #b8d9d1;");
            Details.setVisible(true);
            Details.setDisable(false);
            catDis.setText(entry.category());
            userDis.setText(entry.user());
            passDis.setText(entry.pass());
        });
        pane.getChildren()
            .add(button);
        return pane;
    }

    public void setUp(PassManager manager)
    {
        this.manager = manager;
        setLogin();
    }

    public void setStage1(Stage stage1)
    {
        this.stage1 = stage1;
    }

    @FXML
    private void handleRemove(ActionEvent event)
    throws IllegalBlockSizeException, IOException, BadPaddingException, InvalidKeyException
    {
        Stage confirmStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Confirm_Remove.fxml"));
        Parent root = fxmlLoader.load();
        ConRemControl control = fxmlLoader.getController();

        //Establishing values for controller
        control.setUp(this, confirmStage);

        //Attaching stage to new scene to display
        confirmStage.setTitle("Confirming removal");
        confirmStage.setScene(new Scene(root));

        //Showing the new stage and waiting for exit
        confirmStage.showAndWait();
        if (this.confirmRemoval)
            manager.remove(catDis.getText());
        resetDetails();
        setLogin();
    }

    private void resetDetails()
    {
        Details.setVisible(false);
        Details.setDisable(true);
        catDis.clear();
        userDis.clear();
        passDis.clear();
    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize()
    {
        assert Add != null : "fx:id=\"Add\" was not injected: check your FXML file 'Display_Menu.fxml'.";
        assert Details != null : "fx:id=\"Details\" was not injected: check your FXML file 'Display_Menu.fxml'.";
        assert RemoveAcc != null : "fx:id=\"RemoveAcc\" was not injected: check your FXML file 'Display_Menu.fxml'.";
        assert catDis != null : "fx:id=\"catDis\" was not injected: check your FXML file 'Display_Menu.fxml'.";
        assert passDis != null : "fx:id=\"passDis\" was not injected: check your FXML file 'Display_Menu.fxml'.";
        assert scroller != null : "fx:id=\"scroller\" was not injected: check your FXML file 'Display_Menu.fxml'.";
        assert userDis != null : "fx:id=\"userDis\" was not injected: check your FXML file 'Display_Menu.fxml'.";
    }
}