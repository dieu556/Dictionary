/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionaryen_vi;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import translation.Sentence;

/**
 * FXML Controller class
 *
 * @author dieunguyen
 */
public class UI_dichcauController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ComboBox cmb_language;
    @FXML
    private TextArea txt_src;
    @FXML
    private TextArea txt_result;
    @FXML
    private Button btn_trans;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cmb_language.getItems().addAll(
        "Anh - Viet",
        "Viet - Anh"
        );
      btn_trans=new Button();
        btn_trans.setMnemonicParsing(true);
        btn_trans.setText("_Dlick");
         cmb_language.getSelectionModel().select(0);
    }
    
    @FXML
    private void handlebtn_trans(ActionEvent event) throws IOException, Exception {
        Sentence sentence = new Sentence();
        String result = "";
        if (cmb_language.getSelectionModel().getSelectedItem().toString().equals("Anh - Viet")){
            result = sentence.Translator(txt_src.getText(), 1);
        }
        else result = sentence.Translator(txt_src.getText(), 2);
        txt_result.clear();
        txt_result.appendText(result);       
    }
    @FXML
    private void handlebtn_convert(ActionEvent event) throws IOException {
        String temp_src = "";
        String temp_result = "";
        temp_src = txt_src.getText();
        temp_result = txt_result.getText();
        
        txt_result.clear();
        txt_src.clear();
        
        txt_src.appendText(temp_result);
        txt_result.appendText(temp_src);
        
        if (cmb_language.getSelectionModel().getSelectedItem().toString().equals("Anh - Viet")){
            cmb_language.getSelectionModel().select(1);
        }
        else cmb_language.getSelectionModel().select(0);           
    }
}
