/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FXMLControllers;

import MainClasses.MainClass;
import MainClasses.MainScreen;
import MainClasses.RegisterScreen;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *  File Name: LoginScreenController
 *  Date: Jun 6, 2018
 *  Description:
 */
public class LoginScreenController implements Initializable {
    
    @FXML
    private Button enterButton;

    @FXML
    private ImageView backgroundImage;

    @FXML
    private VBox centerBox;

    @FXML
    private HBox buttonBox;

    @FXML
    private Label signInLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private HBox userNameBox;

    @FXML
    private Pane topPane;

    @FXML
    private ImageView logoImageView;

    @FXML
    private TextField userNameField;

    @FXML
    private Button registerButton;

    @FXML
    private Label userNameLabel;

    @FXML
    private TextField passwordField;

    @FXML
    private HBox passwordBox;

    @FXML
    private Pane centerPane;
    
    @FXML
    private Label outputLabel;
    
    String password;
    String username;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
        
        enterButton.setOnAction(new EventHandler<ActionEvent> () {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("pressed");
                try {
                    username = userNameField.getText();
                    password = passwordField.getText();
            
                    DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
                    Document doc = docBuilder.parse(new File("src/DataStorage/XMLFiles/" + username + "/userdata.xml"));
                    doc.getDocumentElement().normalize();
            
                    NodeList pass = doc.getElementsByTagName("Password");
                    if (password.equals(pass.item(0).getTextContent())) {
                        MainClass.currentUsername = username;
                        new MainScreen();
                    } else {
                        outputLabel.setText("Incorrect Password");
                    }
                } catch (IOException e) {
                    outputLabel.setText("This Username Does Not Exist");
                } catch (SAXException ex) {
                    Logger.getLogger(LoginScreenController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParserConfigurationException ex) {
                    Logger.getLogger(LoginScreenController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        
        registerButton.setOnAction(new EventHandler<ActionEvent> () {
            @Override
            public void handle(ActionEvent event) {
                try {
                    new RegisterScreen();
                } catch (IOException ex) {
                    Logger.getLogger(LoginScreenController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
    }
}
