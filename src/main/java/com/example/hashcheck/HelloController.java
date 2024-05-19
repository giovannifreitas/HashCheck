package com.example.hashcheck;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;
import java.io.FileInputStream;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.scene.paint.Color;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;

import java.io.File;
import java.io.IOException;

public class HelloController {

    @FXML
    private Label welcomeText;

    @FXML
    private VBox VBoxCompareHash;

    @FXML
    private TextField filePathTextField; // Reference to the TextField

    @FXML
    private TextField MD5TextField; // Reference to the MD5TextField

    @FXML
    private TextField SHA1TextField; // Reference to the MD5TextField

    @FXML
    private TextField SHA256TextField; // Reference to the MD5TextField

    @FXML
    private TextField SHA384TextField; // Reference to the MD5TextField

    @FXML
    private TextField SHA512TextField; // Reference to the MD5TextField

    @FXML
    private TextField CompareHashTextField; // Reference to the MD5TextField

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

    @FXML
    public void initialize() {
        CompareHashTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    onCompareHashTextFieldAction();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @FXML
    protected void onCompareHashTextFieldAction() throws IOException{


        String[] computedHashes  = {MD5TextField.getText(), SHA1TextField.getText(), SHA256TextField.getText(),SHA384TextField.getText(),SHA512TextField.getText()};

        if(CompareHashTextField.getText() != null && !CompareHashTextField.getText().isEmpty()) {
            String inputText = CompareHashTextField.getText();
            boolean matchFound = false;

            for (String element : computedHashes) {
                if (element.equals(inputText)) {
                    matchFound = true;
                    break; // Exit the loop early if a match is found
                }
            }

            if (matchFound) {
                welcomeText.setText("Hash matched");
                VBoxCompareHash.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            } else {
                welcomeText.setText("Hash don't matched");
                VBoxCompareHash.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
            }


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

        SHA1TextField.setText(DigestUtils.sha1Hex(fis));
        SHA256TextField.setText(DigestUtils.sha256Hex(fis));
        SHA384TextField.setText(DigestUtils.sha384Hex(fis));
        SHA512TextField.setText(DigestUtils.sha512Hex(fis));

    }
    }






