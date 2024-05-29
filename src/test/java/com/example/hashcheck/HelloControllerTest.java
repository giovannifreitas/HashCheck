package com.example.hashcheck;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class HelloControllerTest {

    @Test
    void testCalculateFileHashes() { // Renamed to testCalculate...

        String filePath = "C:\\Users\\Giovanni Freitas\\Downloads\\pycharm-professional-2024.1.1.exe"; // Use String directly
        String expectedSHA1 = "011c05009fb76d2da61eb4558878b64a3720cb36";

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis);
            String generatedSHA1 = DigestUtils.sha1Hex(fis);
            System.out.println("Generated:" + generatedSHA1);
            System.out.println("Expected:" + expectedSHA1);
            assertEquals(expectedSHA1, generatedSHA1);
        } catch (IOException e) {
            // Handle the exception more gracefully (log, throw custom exception)
            e.printStackTrace();
        }
    }
}