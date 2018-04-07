/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionaryen_vi;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import translation.Sentence;
import net.sourceforge.tess4j.*;
import org.apache.commons.io.FilenameUtils;

/**
 * FXML Controller class
 *
 * @author dieunguyen
 */
public class UI_dichtuanhController implements Initializable {

    /**
     * Initializes the controller class.
     */
   
    @FXML
    private Label lb_path;
    @FXML
    private TextArea txt_result;
    @FXML
    private ImageView imageView;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO            
        imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(final MouseEvent event) {
            try {
                process();
            } catch (Exception ex) {
                Logger.getLogger(UI_dichtuanhController.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
        });
    }    
   @FXML
    private void handlebtn_filechooser(ActionEvent event) throws Exception    {
        process();
    }
    private void process() throws Exception{
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        fileChooser.getExtensionFilters().addAll(
        new ExtensionFilter("IMAGE Files", "*.png", "*.jpg", "*.pdf"));
        
        if (selectedFile != null) {           
            lb_path.setText(selectedFile.getPath());  
            
            Image image = null;
            if (FilenameUtils.getExtension(selectedFile.getPath()).equals("pdf")){
                image = PDFtoIMAGE(selectedFile.getPath());
            }else{
                image = new Image(selectedFile.toURI().toString());
            }
            
            imageView.setImage(image);
                     
            ITesseract instance = new Tesseract();  // JNA Interface Mapping  
 
            try {
                String text = instance.doOCR(selectedFile); 
                txt_result.appendText(text);                                    
            } catch (TesseractException e) {
                System.err.println(e.getMessage());
            }
            
            Sentence sentence = new Sentence();
            String[] rows = txt_result.getText().split("\n");
            txt_result.clear();
            for(int i=0; i<rows.length; i++ ){
                String result = "";
                result = sentence.Translator(rows[i].toString(), 1);
                txt_result.appendText(result + "\n");               
            }        
        }
        else{
                System.out.println("File selection cancelled.");
            }
    }
    private Image PDFtoIMAGE(String path) throws IOException {

            //Loading an existing PDF document
            File file = new File(path);
            PDDocument document = PDDocument.load(file);

            //Instantiating the PDFRenderer class
            PDFRenderer renderer = new PDFRenderer(document);

            //Rendering an image from the PDF document
            BufferedImage image = renderer.renderImage(0);

            //Writing the image to a file
            //ImageIO.write(image, "JPEG", new File("/Users/dieunguyen/kkaka.jpg"));

            System.out.println("Image created");
            Image img = SwingFXUtils.toFXImage(image, null);

            //Closing the document
            document.close();
            
            return img;
    }
}
