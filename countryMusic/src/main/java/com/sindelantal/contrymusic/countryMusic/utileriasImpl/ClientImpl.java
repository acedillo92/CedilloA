package com.sindelantal.contrymusic.countryMusic.utileriasImpl;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.io.CharStreams;
import com.neovisionaries.i18n.CountryCode;
import com.sindelantal.contrymusic.countryMusic.utilerias.IClient;
import com.sindelantal.contrymusic.countryMusic.utilerias.IGenerateTokenSpotify;
import com.sindelantal.contrymusic.countryMusic.vo.cityWeather;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.model_objects.specification.Category;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import com.wrapper.spotify.requests.data.browse.GetCategorysPlaylistsRequest;
import com.wrapper.spotify.requests.data.browse.GetListOfCategoriesRequest;

@Service
public class ClientImpl implements IClient {
	
	
	private final String ID_TOKEN_CITY_NAME= "470a9efc30d84fdc106532241701092e";
	private  String categoryId="rock";
	
	public List<cityWeather> clientWeatherByCityName(String cityName) {
		cityWeather cobjWether = new cityWeather();
		List<cityWeather> ltObjWether = new ArrayList<cityWeather>();
		HttpURLConnection conn = null;
		try {
			URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q="+cityName+"&APPID="+ID_TOKEN_CITY_NAME);//your url i.e fetch data from .
			conn = (HttpURLConnection) url.openConnection();
			 conn.setRequestMethod("GET");
		     conn.setRequestProperty("Accept", "application/json");
		     if (conn.getResponseCode() != 200) {
		            throw new RuntimeException("Failed : HTTP Error code : "
		                    + conn.getResponseCode());
		        }
		     
		     String stringFromStream = CharStreams.toString(new InputStreamReader(new BufferedInputStream(conn.getInputStream()), "UTF-8"));
				JSONObject json = new JSONObject(stringFromStream);
				System.out.println("CONVERT JSON === > " + json);
				
				String cityNameJson = cityName;
				JSONObject  sysJson= json.getJSONObject("sys");
				JSONObject  mainJson= json.getJSONObject("main");
				String countryCode= sysJson.getString("country");
				String temMaxJson = mainJson.getDouble("temp_max")+"";
				String temMinJson = mainJson.getDouble("temp_min")+"";
				String temCurrentJson = mainJson.getDouble("temp")+"";
				
				cobjWether= new  cityWeather(countryCode, cityNameJson, temMaxJson, 
						temMinJson, temCurrentJson);
				ltObjWether.add(cobjWether);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			conn.disconnect();
		}
		
		return ltObjWether;
	}
	
	

	  public  Paging<PlaylistSimplified> getCategorysPlaylists_Sync(GetCategorysPlaylistsRequest getCategoryRequest) {
	    try {
	      final Paging<PlaylistSimplified> playlistSimplifiedPaging = getCategoryRequest.execute();
	      return playlistSimplifiedPaging;
	      
	    } catch (IOException | SpotifyWebApiException e) {
	      System.out.println("Error: " + e.getMessage());
	      e.printStackTrace();
	    }
	    
	    return null;
	  }
	  
	  public List<cityWeather> clientWeatherByCoordinates(String latitud, String Longitud) {
			cityWeather cobjWether = new cityWeather();
			List<cityWeather> ltObjWether = new ArrayList<cityWeather>();
			HttpURLConnection conn = null;
			try {
				URL url = new URL("http://api.openweathermap.org/data/2.5/weather?lat="+latitud+"&lon="+Longitud+"&APPID="+ID_TOKEN_CITY_NAME);//your url i.e fetch data from .
				conn = (HttpURLConnection) url.openConnection();
				 conn.setRequestMethod("GET");
			     conn.setRequestProperty("Accept", "application/json");
			     if (conn.getResponseCode() != 200) {
			            throw new RuntimeException("Failed : HTTP Error code : "
			                    + conn.getResponseCode());
			        }
			     
			     String stringFromStream = CharStreams.toString(new InputStreamReader(new BufferedInputStream(conn.getInputStream()), "UTF-8"));
					JSONObject json = new JSONObject(stringFromStream);
					System.out.println("CONVERT JSON === > " + json);
					
					String cityNameJson = json.getString("name");
					JSONObject  sysJson= json.getJSONObject("sys");
					JSONObject  mainJson= json.getJSONObject("main");
					String countryCode= sysJson.getString("country");
					String temMaxJson = mainJson.getDouble("temp_max")+"";
					String temMinJson = mainJson.getDouble("temp_min")+"";
					String temCurrentJson = mainJson.getDouble("temp")+"";
					
					cobjWether= new  cityWeather(countryCode, cityNameJson, temMaxJson, 
							temMinJson, temCurrentJson);
					ltObjWether.add(cobjWether);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally {
				conn.disconnect();
			}
			
			return ltObjWether;
		}

	  
	 

}
