/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionaryen_vi;

import static clickandsee.Jnativehook.text;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import translation.Sentence;
import translation.Word;

/**
 * FXML Controller class
 *
 * @author dieunguyen
 */
public class UI_cnsFormController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML TextArea txtMeaning;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String result="";
        
        int spaces = (int) text.chars().filter(c -> c == (int)' ').count();
        if (spaces == 0){
            Word rs = new Word();         
            List _rs = rs.Translator(text, 1);
            result = _rs.get(0) + "\n -----Từ liên quan----- \n" + _rs.get(1);
        }
        else{
            Sentence sentence = new Sentence();
            
            try {
                result = sentence.Translator(text, 1);
            } catch (Exception ex) {
                Logger.getLogger(UI_cnsFormController.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }    
        txtMeaning.appendText(result);
    }    
    
}
