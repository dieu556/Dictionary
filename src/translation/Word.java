/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package translation;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
/**
 *
 * @author dieunguyen
 */
public class Word {
    public List Translator(String _word, int option){  
        List<String> result = new ArrayList<>();
        StringBuilder result_mean = new StringBuilder();
        StringBuilder result_related = new StringBuilder();
            
        try {
            Document doc = Jsoup.connect("https://vdict.com/"+_word+","+option+",0,0.html").get();
            Element pronounce = doc.select("div.pronounce").first();        

            result_mean.append("Phiên âm: "+pronounce.text() + "\n");

            Elements wordforms = doc.select(".phanloai");
            Elements mean = doc.select("ul.list1:not(div.relatedWord > ul.list1)");
            
            
            for (Element wordform : wordforms){
                result_mean.append("-----"+wordform.text().toUpperCase()+"-----" + "\n"); // wordform

                Node node = wordform.nextSibling();
                if (node.attr("id").equals("adsensediv"))
                    node = node.nextSibling();
                
                while(node.attr("class").equals("list1")){
                    String html = node.toString();
                    Document doc_node = Jsoup.parse(html);
                    Elements content = doc_node.getAllElements();
                    result_mean.append("*"+content.select("b").text() + "\n"); // content

                    Elements subcontents = content.select("ul.list2");
                    for (Element subcontent : subcontents){
                        Node node_sub = subcontent.select("br").first().nextSibling();               
                        String subcontent1 = subcontent.select("span").text();
                        String subcontent2 = node_sub.toString();
                        result_mean.append("    "+subcontent1 + "\n"); // subcontent 1
                        result_mean.append("   "+subcontent2 + "\n"); // subcontent 2
                    }
                    node = node.nextSibling();
                }
            }
            
            Element relatedwords = doc.select("div.relatedWord").first();
            for (Element relatedword : relatedwords.select("li")){
                result_related.append(relatedword.select("strong").text() +"\n");
                String text = "";
                for (Element word : relatedword.select("a")){
                    text = text + word.text() + ", ";
                }
                result_related.append(text.trim().substring(0, text.length()-2) + "\n");
            }
            
            result.add(result_mean.toString());
            result.add(result_related.toString());
   
        } 
        
        catch (UnknownHostException ex) {
            Logger.getLogger(Word.class.getName()).log(Level.SEVERE, null, ex);
            result.add("Không có kết nối mạng");
            result.add("Không tìm thấy");
        } 
        catch (SocketTimeoutException ex) {
            Logger.getLogger(Word.class.getName()).log(Level.SEVERE, null, ex);
            result.add("Vui lòng thử lại sau (Read time out)");
            result.add("Không tìm thấy");
        } 
        catch (NullPointerException ex) {
            Logger.getLogger(Word.class.getName()).log(Level.SEVERE, null, ex);
            result.add("Rất tiếc, chúng tôi không tìm thấy từ '"+_word+"'");
            result.add("Không tìm thấy");
        } catch (IOException ex) { 
            Logger.getLogger(Word.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
