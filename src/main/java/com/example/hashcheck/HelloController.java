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

        try (FileInputStream fis = new FileInputStream(file)){
            String md5 = calculateMD5(fis);
            String sha1 = calculateSHA1(fis);
            String sha256 = calculateSHA256(fis);
            String sha384 = calculateSHA384(fis);
            String sha512 = calculateSHA512(fis);

            MD5TextField.setText(md5);
            SHA1TextField.setText(sha1);
            SHA256TextField.setText(sha256);
            SHA384TextField.setText(sha384);
            SHA512TextField.setText(sha512);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private static String calculateMD5(FileInputStream fis) throws IOException {
        String md5 = DigestUtils.md5Hex(fis);
        resetStream(fis);
        return md5;
    }

    private static String calculateSHA1(FileInputStream fis) throws IOException {
        String sha1 = DigestUtils.sha1Hex(fis);
        resetStream(fis);
        return sha1;
    }

    private static String calculateSHA256(FileInputStream fis) throws IOException {
        String sha256 = DigestUtils.sha256Hex(fis);
        resetStream(fis);
        return sha256;
    }

    private static String calculateSHA384(FileInputStream fis) throws IOException {
        String sha384 = DigestUtils.sha384Hex(fis);
        resetStream(fis);
        return sha384;
    }

    private static String calculateSHA512(FileInputStream fis) throws IOException {
        String sha512 = DigestUtils.sha512Hex(fis);
        resetStream(fis);
        return sha512;
    }

    private static void resetStream(FileInputStream fis) throws IOException {
        fis.getChannel().position(0);
    }


    }






