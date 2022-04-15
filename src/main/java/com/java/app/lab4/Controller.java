package com.java.app.lab4;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Controller {

    @FXML
    private TextField inp_1;

    @FXML
    private TextField inp_2;

    @FXML
    private TextField inp_3;

    @FXML
    private TextField inp_4;

    @FXML
    private TextField inp_5;

    @FXML
    private TextField inp_6;

    @FXML
    private TextField inp_7;

    @FXML
    private TextField inp_8;

    @FXML
    private TextField inp_9;

    @FXML
    private Label matrix_field;

    @FXML
    private Button button;

    private static String matrixToOutput;

    @FXML
    private void initialize() {
        try {
            final Socket clientSocket = new Socket("127.0.0.1", 2525);

            ObjectOutputStream coos =
                    new ObjectOutputStream(clientSocket.getOutputStream());//создание
            ObjectInputStream cois =
                    new ObjectInputStream(clientSocket.getInputStream());//создание
            button.setOnAction(actionEvent -> {
                if (isMatrix()) {
                    String clientMessage = inp_1.getText() + " " + inp_2.getText() + " " + inp_3.getText() + " " +
                            inp_4.getText() + " " + inp_5.getText() + " " + inp_6.getText() + " " +
                            inp_7.getText() + " " + inp_8.getText() + " " + inp_9.getText() + " ";
                    try {
                        coos.writeObject(clientMessage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        matrix_field.setText(cois.readObject().toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private boolean isMatrix() {
        if (inp_1.getText() == null || inp_2.getText() == null || inp_3.getText() == null ||
                inp_4.getText() == null || inp_5.getText() == null || inp_6.getText() == null ||
                inp_7.getText() == null || inp_8.getText() == null || inp_9.getText() == null) {
            return false;
        }
        return true;

    }

}