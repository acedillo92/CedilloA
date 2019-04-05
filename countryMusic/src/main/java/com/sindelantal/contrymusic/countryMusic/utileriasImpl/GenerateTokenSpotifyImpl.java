package com.sindelantal.contrymusic.countryMusic.utileriasImpl;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.sindelantal.contrymusic.countryMusic.utilerias.IGenerateTokenSpotify;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;

@Service
public class GenerateTokenSpotifyImpl implements IGenerateTokenSpotify {
	private  String clientId = "301b32d0c8af4b428d09e72d1ecd0322";
	private  String clientSecret = "3a0afa2e19d34861bc6e4174573649b3";
	
	private final SpotifyApi spotifyApi = new SpotifyApi.Builder()
	          .setClientId(clientId)
	          .setClientSecret(clientSecret)
	          .build();
	
	  private final ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials()
	          .build();
	  
	  public String clientCredentials_Sync() {
		    try {
		      final ClientCredentials clientCredentials = clientCredentialsRequest.execute();
		      spotifyApi.setAccessToken(clientCredentials.getAccessToken());
		      System.out.println("Expires in: " + clientCredentials.getExpiresIn());
		      System.out.println("Acces in: " + clientCredentials.getAccessToken());
		      System.out.println("type in: " + clientCredentials.getTokenType());
		      
		      return clientCredentials.getAccessToken();
		      
		    } catch (IOException | SpotifyWebApiException e) {
		      System.out.println("Error: " + e.getMessage());
		      e.printStackTrace();
		    }
		    
		    return "Error al obtener el token";
		  }
	   

}
