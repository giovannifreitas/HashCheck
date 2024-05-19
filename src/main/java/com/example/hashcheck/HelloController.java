package com.example.hashcheck;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;
import java.io.FileInputStream;

import java.io.File;
import java.io.IOException;

public class HelloController {

    @FXML
    private Label welcomeText;

    @FXML
    private TextField filePathTextField; // Reference to the TextField

    @FXML
    private TextField MD5TextField; // Reference to the MD5TextField

    @FXML
    protected void onBrowseButtonClick() throws IOException {

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
            FileHashCalculator();
            // You can perform further actions with the selected file here
        } else {
            filePathTextField.setText("No file selected.");
        }


    }

    private void FileHashCalculator() throws IOException {

        String file = filePathTextField.getText();
        FileInputStream fis = new FileInputStream(file);
        try {
            MD5TextField.setText(DigestUtils.md5Hex(fis));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //switch (algorithm.toUpperCase()) {
        //    case "MD5":
        //        return DigestUtils.md5Hex(fis);
        //    case "SHA-1":
        //        return DigestUtils.sha1Hex(fis);
        //    case "SHA-256":
        //        return DigestUtils.sha256Hex(fis);
        //    case "SHA-512":
        //        return DigestUtils.sha512Hex(fis);
    }
}