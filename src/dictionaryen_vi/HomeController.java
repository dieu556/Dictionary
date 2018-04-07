/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionaryen_vi;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 *
 * @author quochung
 */
public class HomeController implements Initializable {
    
    @FXML
    private Button btn_anhviet;
    @FXML
    private Button btn_vietanh;
    @FXML
    private Button btn_dichcau;
    @FXML
    private Button btn_caidat;
    @FXML
    private Button btn_dichtuanh;
    @FXML
    private Button btn_infor;
    @FXML
    private ImageView img_logo, img_clicked0, img_clicked1, img_clicked2, img_clicked3;
    @FXML
    private Pane contentArea;
    
    
    File file = new File("clicked.png");
    Image image = new Image(file.toURI().toString());
   
    
    private int flag;
    
    private void change_UI() throws IOException{
        Parent fxml;
                  
        switch(flag)
        {
            case 0:
                img_clicked0.setVisible(true);
                img_clicked1.setVisible(false);
                img_clicked2.setVisible(false);
                img_clicked3.setVisible(false); 
                
                fxml= FXMLLoader.load(getClass()
                        .getResource("/dictionaryen_vi/UI_anhviet.fxml"));
                contentArea.getChildren().removeAll();
                contentArea.getChildren().setAll(fxml);
            break;
            case 1:    
                img_clicked0.setVisible(false);
                img_clicked1.setVisible(true);
                img_clicked2.setVisible(false);
                img_clicked3.setVisible(false); 
                
                fxml= FXMLLoader.load(getClass()
                        .getResource("/dictionaryen_vi/UI_vietanh.fxml"));
                contentArea.getChildren().removeAll();
                contentArea.getChildren().setAll(fxml);
            break;
            case 2:
                img_clicked0.setVisible(false);
                img_clicked1.setVisible(false);
                img_clicked2.setVisible(true);
                img_clicked3.setVisible(false); 

                fxml= FXMLLoader.load(getClass()
                        .getResource("/dictionaryen_vi/UI_dichcau.fxml"));
                contentArea.getChildren().removeAll();
                contentArea.getChildren().setAll(fxml);
                break;
            case 3:
                img_clicked0.setVisible(false);
                img_clicked1.setVisible(false);
                img_clicked2.setVisible(false);
                img_clicked3.setVisible(true); 

                fxml= FXMLLoader.load(getClass()
                        .getResource("/dictionaryen_vi/UI_dichtuanh.fxml"));
                contentArea.getChildren().removeAll();
                contentArea.getChildren().setAll(fxml);
                break;
            case 4:
                fxml= FXMLLoader.load(getClass()
                        .getResource("/dictionaryen_vi/UI_setting.fxml"));
                contentArea.getChildren().removeAll();
                contentArea.getChildren().setAll(fxml);
                break;
            case 5:
                fxml= FXMLLoader.load(getClass()
                        .getResource("/dictionaryen_vi/UI_About.fxml"));
                contentArea.getChildren().removeAll();
                contentArea.getChildren().setAll(fxml);
                break;
        }
    }
    private void FadeEffect(Node node){
        FadeTransition ft = new FadeTransition();
        ft.setDuration(Duration.millis(1000));
        ft.setNode(node);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play(); 
 
    }
    @FXML
    private void handlebtn_anhviet(ActionEvent event) throws IOException{
        flag=0;
        change_UI();
        FadeEffect(contentArea);
    }
    
    @FXML
    private void handlebtn_vietanh(ActionEvent event) throws IOException {       
        flag=1;      
        change_UI();   
        FadeEffect(contentArea);
    }
    
    @FXML
    private void handlebtn_dichcau(ActionEvent event) throws IOException {       
        flag=2;      
        change_UI(); 
        FadeEffect(contentArea);
    }
    
    @FXML
    private void handlebtn_dichtuanh(ActionEvent event) throws IOException {       
        flag=3;      
        change_UI(); 
        FadeEffect(contentArea);
        
    }
    
    @FXML
    private void handlebtn_caidat(ActionEvent event) throws IOException {      
        flag=4;
        change_UI();               
    }
    @FXML
    private void handlebtn_infor(ActionEvent event) throws IOException {        
        flag=5;
        change_UI();           
    }
    
    @FXML
    private void handle_mouseEnter(MouseEvent event) {        
        img_logo.setScaleX(1.2);
        img_logo.setScaleY(1.2);
    }
    
    @FXML
    private void handle_mouseExited(MouseEvent event) {        
        img_logo.setScaleX(1.0);
        img_logo.setScaleY(1.0);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {    
        flag=0;
       
        img_clicked0.setVisible(true);
        img_clicked1.setVisible(false);
        img_clicked2.setVisible(false);
        img_clicked3.setVisible(false); 
           
        try {                   
            Parent fxml;           
            fxml= FXMLLoader.load(getClass()
                    .getResource("/dictionaryen_vi/UI_anhviet.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }    
    
}
