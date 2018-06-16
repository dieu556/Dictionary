/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionaryen_vi;

import clickandsee.Jnativehook;
import com.jfoenix.controls.JFXToggleButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author dieunguyen
 */
public class UI_settingController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXToggleButton togbtn_clickandsee;

    private static boolean flag=false;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        togbtn_clickandsee.setSelected(flag);
        togbtn_clickandsee.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //System.out.println("Hello World!");
                Jnativehook jnative = new Jnativehook();
                if (togbtn_clickandsee.isSelected()) {
                    System.out.println("On");
                    
                    jnative.Execute();
                    flag=true;

                } else {
                    jnative.cancelNativeHook();
                    System.out.println("Off");
                    flag=false;
                }

            }
        });
    }

}