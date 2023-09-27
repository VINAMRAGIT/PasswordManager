package com.example.fx_passmanage_app;

import DataAccess.PassManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application
{
    public static void main(String[] args)
    {
        launch();
    }

    @Override
    public void start(Stage stage)
    throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login_Menu.fxml"));
        Parent root = fxmlLoader.load();
        LoginControl control = fxmlLoader.getController();

        control.setUp(new PassManager());
        control.setStage(stage);

        stage.setTitle("Login Screen");
        stage.setScene(new Scene(root));
        stage.show();
    }
}