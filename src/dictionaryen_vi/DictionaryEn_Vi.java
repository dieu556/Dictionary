/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionaryen_vi;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

/**
 *
 * @author quochung
 */
public class DictionaryEn_Vi extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Từ điển Anh - Việt");
        stage.getIcons().add(new Image("dictionaryen_vi/icon.png"));
        stage.setOnCloseRequest((WindowEvent event1) -> {
           Platform.exit();
           try {
               GlobalScreen.unregisterNativeHook();
           } catch (NativeHookException ex) {               
               
               System.exit(0);
           }
        });
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);   
    }
    
}
