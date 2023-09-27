/**
 * Sample Skeleton for 'Login_Menu.fxml' Controller Class
 */

package com.example.fx_passmanage_app;

import DataAccess.PassManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import javax.crypto.*;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class LoginControl
{
    @FXML // fx:id="submitButton1"
    public Button submitButton1; // Value injected by FXMLLoader
    @FXML // fx:id="inputPass"
    private PasswordField inputPass; // Value injected by FXMLLoader
    @FXML // fx:id="submitButton"
    private Button submitButton; // Value injected by FXMLLoader
    private Stage stage;
    private PassManager manager;

    @FXML
    void handleSubmit()
    throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException,
           IOException, BadPaddingException, InvalidKeyException
    {
        String input = inputPass.getText();
        if (manager.init(input)) {
            stage.close();
            Stage stage1 = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Display_Menu.fxml"));
            Parent root = fxmlLoader.load();
            DisplayControl control = fxmlLoader.getController();

            //Setting up controller for further use
            control.setUp(manager);
            control.setStage1(stage1);

            //Attaching stage to new scene to display
            stage1.setTitle("Display Logins");
            stage1.setScene(new Scene(root));

            stage1.show();
        }
        stage.close();

    }

    public void setStage(Stage stage)
    {
        this.stage = stage;
    }

    public void setUp(PassManager passManager)
    {
        this.manager = passManager;
    }

    public void handleQuit(ActionEvent event)
    {
        Platform.exit();
    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize()
    {
        assert inputPass != null : "fx:id=\"inputPass\" was not injected: check your FXML file 'Login_Menu.fxml'.";
        assert submitButton != null : "fx:id=\"submitButton\" was not injected: check your FXML file 'Login_Menu" +
                ".fxml'.";
    }
}