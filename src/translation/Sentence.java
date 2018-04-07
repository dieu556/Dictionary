/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package translation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;

/**
 *
 * @author dieunguyen
 */
public class Sentence {
    public static String Translator(String word, int option){
        StringBuilder response = null;
        String result="";
        try{
            String url ="";
            if (option == 1){
                url = "https://translate.googleapis.com/translate_a/single?"
                    +"client=gtx&"
                    +"sl=en&tl=vi&dt=t&q="
                    + URLEncoder.encode(word, "UTF-8");
            }
            else
                url = "https://translate.googleapis.com/translate_a/single?"
                    +"client=gtx&"
                    +"sl=vi&tl=en&dt=t&q="
                    + URLEncoder.encode(word, "UTF-8");
       
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection(); 
            con.setRequestProperty("User-Agent", "Safari/11.0.3");

            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }
            
            result = parseResult(response.toString());
        } catch (Exception ex){
            Logger.getLogger(Sentence.class.getName()).log(Level.SEVERE, null, ex);
            result = "Rất tiếc, không dịch được \n '"+word+"'";
        }

        return result;
    }
    public static String parseResult(String inputJson) throws Exception{
        String result = "";
        JSONArray jsonArray = new JSONArray(inputJson);
        JSONArray jsonArray1 = (JSONArray) jsonArray.get(0);
      
        for (int i = 0; i < jsonArray1.length(); i++) {
            JSONArray jsonArray3 = (JSONArray) jsonArray1.get(i);
            result += jsonArray3.get(0).toString();
        }   
        return result;
    }
}
