package com.example.hashcheck;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;

public class HelloController {

    @FXML
    private Label welcomeText;

    @FXML
    private TextField filePathTextField; // Reference to the TextField

    @FXML
    protected void onBrowseButtonClick() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File");

        // Set initial directory (optional)
        File initialDirectory = new File(System.getProperty("user.home"));
        fileChooser.setInitialDirectory(initialDirectory);

        // Show open file dialog
        Stage stage = (Stage) filePathTextField.getScene().getWindow(); // Get the current stage
        File selectedFile = fileChooser.showOpenDialog(stage);

        // Handle the selected file
        if (selectedFile != null) {
            filePathTextField.setText(selectedFile.getAbsolutePath());
            // You can perform further actions with the selected file here
        } else {
            filePathTextField.setText("No file selected.");
        }


    }
}