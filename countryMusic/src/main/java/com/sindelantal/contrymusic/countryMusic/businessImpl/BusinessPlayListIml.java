package com.sindelantal.contrymusic.countryMusic.businessImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neovisionaries.i18n.CountryCode;
import com.sindelantal.contrymusic.countryMusic.business.IBusinessPlayList;
import com.sindelantal.contrymusic.countryMusic.utilerias.IClient;
import com.sindelantal.contrymusic.countryMusic.utilerias.IValidatorProcess;
import com.sindelantal.contrymusic.countryMusic.utileriasImpl.GenerateTokenSpotifyImpl;
import com.sindelantal.contrymusic.countryMusic.vo.GeneralInformation;
import com.sindelantal.contrymusic.countryMusic.vo.SpotifyPlayList;
import com.sindelantal.contrymusic.countryMusic.vo.cityWeather;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.requests.data.browse.GetCategorysPlaylistsRequest;

@Service
public class BusinessPlayListIml implements IBusinessPlayList {
	
	@Autowired
	IClient iClient;
	
	@Autowired
	IValidatorProcess iValidatorProcess;
	
	
	GenerateTokenSpotifyImpl iGenerateTokenSpotify = new GenerateTokenSpotifyImpl() ;
	
	private String ID_TOKEN_SPOTIFY= iGenerateTokenSpotify.clientCredentials_Sync();

	@Override
	public List<GeneralInformation> getInformationPlayList(String nameCityOrLatitud, String longitud) {
		// TODO Auto-generated method stub
		List<GeneralInformation> ltGeneralInformation = new ArrayList<GeneralInformation>();
		GeneralInformation objGneralInfo = new GeneralInformation();
		List<cityWeather> cityWeatherlt=null;
		try {
			
			if(longitud.equals("NA")) {
				boolean valueValidator=iValidatorProcess.validateLetter(nameCityOrLatitud);
				if(valueValidator) {
					cityWeatherlt = iClient.clientWeatherByCityName(nameCityOrLatitud);
				}else {
					objGneralInfo.setMessageError("Error! Escribe un nombre de ciudad valida, solo letras y espacios.");
					ltGeneralInformation.add(objGneralInfo);
					return ltGeneralInformation;
				}
			}else {
				boolean valueLatitud=iValidatorProcess.validateCoordinateLatitud(nameCityOrLatitud);
				if(valueLatitud) {
					boolean valueLongitud=iValidatorProcess.validateCoordinateLongitud(longitud);
					if(valueLongitud) {
						cityWeatherlt = iClient.clientWeatherByCoordinates(nameCityOrLatitud, longitud);
					}else {
						objGneralInfo.setMessageError("Error! Captura una Longitud Valida.");
						ltGeneralInformation.add(objGneralInfo);
						return ltGeneralInformation;
					}
				}else {
					objGneralInfo.setMessageError("Error! Captura una Latitud Valida.");
					ltGeneralInformation.add(objGneralInfo);
					return ltGeneralInformation;
				}
			}	
				if(cityWeatherlt.isEmpty()) {
					objGneralInfo.setMessageError("Error! Ciudad no encontrada, Verifique los datos capturados.");
					ltGeneralInformation.add(objGneralInfo);
					return ltGeneralInformation;
				}
				
				
				String gradosCel = iValidatorProcess.returnTempCelcius(cityWeatherlt.get(0).getCurretTemp());
				String categories = "rock";
				
				
				if(Double.parseDouble(gradosCel)>30.0) 
					categories="party";
				if(Double.parseDouble(gradosCel)<=30.0 && Double.parseDouble(gradosCel)>=15.0) 
					categories="pop";
				if(Double.parseDouble(gradosCel)<=14.0 && Double.parseDouble(gradosCel)>=10.0)
					categories="rock";
				if(Double.parseDouble(gradosCel)<10.0) 
					categories="classical";
				
				final SpotifyApi spotifyApi = new SpotifyApi.Builder()
				          .setAccessToken(ID_TOKEN_SPOTIFY)
				          .build();
				
				final GetCategorysPlaylistsRequest getCategoryRequest = spotifyApi.getCategorysPlaylists(categories)
				          .country(CountryCode.SE)
				          .offset(0)
				          .build();
				
				Paging<PlaylistSimplified> playlistSimplifiedPaging = iClient.getCategorysPlaylists_Sync(getCategoryRequest);
				
				 List<SpotifyPlayList> ltPlayList= new ArrayList<SpotifyPlayList>();
				 
				 if(playlistSimplifiedPaging == null) {
					 	objGneralInfo.setMessageError("Error! Ocurrio un error al intentar traer el contenido de Spotify.");
						ltGeneralInformation.add(objGneralInfo);
						return ltGeneralInformation;
					}else {
						for(Integer i =0; i<playlistSimplifiedPaging.getItems().length;i++) {
							SpotifyPlayList objSpotifyPlayList = new SpotifyPlayList();
							 objSpotifyPlayList.setName(playlistSimplifiedPaging.getItems()[i].getName());
							 ltPlayList.add(objSpotifyPlayList);
						}
					}
				 
				objGneralInfo.setCityName(cityWeatherlt.get(0).getCityName());
				objGneralInfo.setCountryCod(cityWeatherlt.get(0).getCountry());
				objGneralInfo.setCurrentTemp(gradosCel);
				objGneralInfo.setTotalPlayList(playlistSimplifiedPaging.getTotal());
				objGneralInfo.setPlayList(ltPlayList);
				objGneralInfo.setCategoryMusic(categories);
				objGneralInfo.setMessageError("SN");
				ltGeneralInformation.add(objGneralInfo);
			
		}catch (Exception e) {
			// TODO: handle exception
			objGneralInfo.setMessageError("Error! Ocurrio un error Interno.");
			ltGeneralInformation.add(objGneralInfo);
			e.printStackTrace();
		}
		return ltGeneralInformation;
	}

	
}
