package com.example.hashcheck;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onBrowseButtonClick()  {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}