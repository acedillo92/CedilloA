package com.mx.avon.pruebaAvon.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import org.springframework.stereotype.Service;

import com.mx.avon.pruebaAvon.model.MessageAlexisCedilloDTO;

@Service
public class ClientUtils {
	
	public JsonObject getClient (MessageAlexisCedilloDTO rq) {
		JsonObject object = null;
		try {
			String urlPam = "http://api.c3ntrosms.com:8181/?username="+rq.getUsername()
    		+ "&password="+rq.getPassword()
    		+ "&number="+rq.getNumber()
    		+ "&message="+rq.getMessage().replaceAll(" ", "20%");
			

            URL url = new URL(urlPam);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode() + " message "+ conn.getResponseMessage());
            }
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
            	response.append(inputLine);
            }
            in.close();
        
            JsonReader jsonReader = Json.createReader(new StringReader(response.toString()));
            object = jsonReader.readObject();
            jsonReader.close();
        } catch (Exception e) {
            System.out.println("Exception in getClient" + e);
        }
		
		 return object;
	}
	
	public void paramaeterValidate(String st) {
		try {
			JsonReader jsonReader = Json.createReader(new StringReader(st));
			JsonObject object = jsonReader.readObject();
			List<String> params = Arrays.asList(new String[]{"username", "password", "number","message"});
			params.forEach( val -> {
				if ( object.isNull(val) ) {
					throw new RuntimeException(val + " not found");
				}
				String value = object.getString(val).replaceAll(" ", "");
				if ( value.equals("") ||  value == null ) {
					throw new RuntimeException(val + " is empty");
				}
			});
			
		}catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}

}
