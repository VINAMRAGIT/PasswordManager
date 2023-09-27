/**
 * Sample Skeleton for 'Confirm_Remove.fxml' Controller Class
 */

package com.example.fx_passmanage_app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ConRemControl
{

    public Button decRem;
    private DisplayControl parentControl;
    private Stage conRemStage;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="conRem"
    private Button conRem; // Value injected by FXMLLoader

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize()
    {
        assert conRem != null : "fx:id=\"conRem\" was not injected: check your FXML file 'Confirm_Remove.fxml'.";
    }

    public void setUp(DisplayControl control, Stage stage)
    {
        this.parentControl = control;
        this.conRemStage = stage;
    }

    public void SendConfirm(ActionEvent event)
    {
        parentControl.confirmRemoval = true;
        this.conRemStage.close();
    }

    public void Cancel(ActionEvent event)
    {
        parentControl.confirmRemoval = false;
        this.conRemStage.close();
    }
}