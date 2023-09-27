/**
 * Sample Skeleton for 'AddAcc_Menu.fxml' Controller Class
 */

package com.example.fx_passmanage_app;

import DataAccess.PassEntry;
import DataAccess.PassManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.io.IOException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.util.ResourceBundle;

public class AddAccControl
{
    @FXML
    public TextField password;
    @FXML
    public TextField username;
    @FXML
    public TextField category;
    @FXML
    public Label IntroLabel;
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;
    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    @FXML // fx:id="cancelButton"
    private Button cancelButton; // Value injected by FXMLLoader
    @FXML // fx:id="submitButton"
    private Button submitButton; // Value injected by FXMLLoader

    private PassManager manager;
    private Stage stage;

    public void setUp(PassManager p)
    {
        this.manager = p;
    }

    public void setStage2(Stage stage)
    {
        this.stage = stage;
    }

    @FXML
    void addSubmit()
    throws IllegalBlockSizeException, IOException, BadPaddingException, InvalidKeyException
    {
        String catIn = category.getText(), useIn = username.getText(), passIn = password.getText();
        if (!catIn.isBlank() && !passIn.isBlank() && !useIn.isBlank()) {
            manager.insertData(new PassEntry(catIn, useIn, passIn));
            this.stage.close();
        }
        else IntroLabel.setText("Make sure none of the three are blank");
    }

    @FXML
    void cancelAdd()
    {
        this.stage.close();
    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize()
    {
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'AddAcc_Menu" +
                ".fxml'.";
        assert submitButton != null : "fx:id=\"submitButton\" was not injected: check your FXML file 'AddAcc_Menu" +
                ".fxml'.";

    }
}