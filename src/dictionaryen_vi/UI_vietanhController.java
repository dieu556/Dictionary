/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionaryen_vi;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.net.URLConnection;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import translation.Sentence;
import translation.Word;

/**
 * FXML Controller class
 *
 * @author quochung
 */
public class UI_vietanhController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button btn_search;
    @FXML
    private Button btn_sound;
    @FXML
    private Button btn_voice;
    @FXML
    private Button btn_displayImg;
    @FXML
    private TextField txtFi_word;
    @FXML
    private ScrollPane Scr_pane_Images = new ScrollPane();
    @FXML
    private TextArea txt_meaning;

    @FXML
    HBox box = new HBox();

    @FXML
    private void handlebtn_displayImg(ActionEvent event) throws IOException {
        String link = "https://www.google.com/search?q="
                + txtFi_word.getText().trim() + "&tbm=isch";
        Document doc = (Document) Jsoup.connect(link).get();
        Elements images = doc.select("img");

        box.getChildren().clear();
        int i = 0;
        for (Element image : images) {
            String k = "";
            System.out.println("Image Source: " + image.attr("data-src"));
            k = image.attr("data-src");
            if (!k.equals("")) {
                ImageView pic = new ImageView(createImage(k));
                pic.setFitHeight(290);
                pic.setPreserveRatio(true);;
                box.getChildren().add(pic);
                i++;
            }
            if (i == 10) {
                break;
            }
        }

    }

    @FXML
    private void handlebtn_search(ActionEvent event) {
        if (txtFi_word.getText().trim().equals("")) {
            txtFi_word.setStyle("-fx-border-color: red; -fx-border-radius : 2");
        } else {
            //Word result = new Word();
            //String _result = "";
            //List rs = result.Translator(txtFi_word.getText().trim(), 2);
            //_result = rs.get(0) + "\n -----Từ liên quan----- \n" + rs.get(1);
            
            Sentence result = new Sentence(); 
            String _result = result.Translator(txtFi_word.getText().trim(), 2);
                
            txt_meaning.clear();
            txt_meaning.appendText(_result);
        }
    }

    Image createImage(String url) throws IOException {
        URLConnection conn = new URL(url).openConnection();
        conn.setRequestProperty("User-Agent", "Wget/1.13.4 (linux-gnu)");
        try (InputStream stream = conn.getInputStream()) {
            return new Image(stream);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // System.setProperty("mbrola.base", "D:/Jar/New folder/mbrola");
        txtFi_word.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                handlebtn_search(new ActionEvent());
            }
        });
    }
}
