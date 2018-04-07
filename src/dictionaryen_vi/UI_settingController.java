/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionaryen_vi;

import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javax.swing.AbstractButton;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

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
    private ToggleButton togbtn_clickandsee;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         togbtn_clickandsee.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //System.out.println("Hello World!");
                if(togbtn_clickandsee.isSelected())
                {
                    System.out.println("hello");
                    try {
                    GlobalScreen.unregisterNativeHook();
		} catch (NativeHookException e1) {
                    e1.printStackTrace();
                    }
                }
                else
                    System.out.println("holla");
            }
        });
    }    
    
}
