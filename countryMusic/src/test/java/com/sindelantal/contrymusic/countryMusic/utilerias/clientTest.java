package com.sindelantal.contrymusic.countryMusic.utilerias;

import java.util.List;
import java.util.function.Consumer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import com.neovisionaries.i18n.CountryCode;
import com.sindelantal.contrymusic.countryMusic.businessImpl.BusinessPlayListIml;
import com.sindelantal.contrymusic.countryMusic.utileriasImpl.ClientImpl;
import com.sindelantal.contrymusic.countryMusic.utileriasImpl.GenerateTokenSpotifyImpl;
import com.sindelantal.contrymusic.countryMusic.utileriasImpl.ValidatorProcess;
import com.sindelantal.contrymusic.countryMusic.vo.GeneralInformation;
import com.sindelantal.contrymusic.countryMusic.vo.SpotifyPlayList;
import com.sindelantal.contrymusic.countryMusic.vo.cityWeather;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.requests.data.browse.GetCategorysPlaylistsRequest;

import jdk.nashorn.internal.ir.annotations.Ignore;

@RunWith(SpringRunner.class)
@SpringBootTest
public class clientTest {
	
	
  ValidatorProcess iValidatorProcess = new ValidatorProcess();
	GenerateTokenSpotifyImpl iGenerateTokenSpotify = new GenerateTokenSpotifyImpl() ;
	ClientImpl clientObj = new ClientImpl();
	GenerateTokenSpotifyImpl gtsp = new GenerateTokenSpotifyImpl();
	private String ID_TOKEN_SPOTIFY= iGenerateTokenSpotify.clientCredentials_Sync();
	BusinessPlayListIml bn = new BusinessPlayListIml();
	
	
	
	
	
	@Ignore
	@Test
	public void clientWeatherByCityName() {
		try {
			String cityName = "nuevo leon";
			System.out.println("---------------------Va entrar al cliente-------------------");
			List<cityWeather> ltClient = clientObj.clientWeatherByCityName(cityName);
			System.out.println("---------------------Salio del cliente-------------------");
			if(ltClient.isEmpty()) {
				System.out.println("Ciudad no encontrada, favor de verificar la ciudad capturada");
			}else {
				String gradosCel = iValidatorProcess.returnTempCelcius(ltClient.get(0).getCurretTemp());
				System.out.println("El reltado de l temperatura es : = > " + ltClient.get(0).getCurretTemp()
						+ " en C: ==> " + gradosCel);
				ltClient.forEach((final cityWeather clientObjFo)->
				System.out.println("code Country: " +clientObjFo.getCountry()
				+" name city: " + clientObjFo.getCityName() + " current Temp: "+clientObjFo.getCurretTemp()
				+" max Temp: "+ clientObjFo.getTempMax()+ " min Temp: "+clientObjFo.getTempMin()));
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	@Ignore
	@Test
	public void generateTokenSpotify() {
		try {
			String tokenSpitofy= gtsp.clientCredentials_Sync();
			System.out.println("El token generado ======> " + tokenSpitofy);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	
	@Ignore
	@Test
	public void clientSpotifyByCategories() {
		try {
			final SpotifyApi spotifyApi = new SpotifyApi.Builder()
			          .setAccessToken(ID_TOKEN_SPOTIFY)
			          .build();
			  final GetCategorysPlaylistsRequest getCategoryRequest = spotifyApi.getCategorysPlaylists("party")
			          .country(CountryCode.SE)
			          .offset(0)
			          .build();
			Paging<PlaylistSimplified> playlistSimplifiedPaging =clientObj.getCategorysPlaylists_Sync(getCategoryRequest);
			if(playlistSimplifiedPaging == null) {
				System.out.println("Ocurrio un error al intentar traer la Lista");
			}else {
				System.out.println("===============> total ========>"+playlistSimplifiedPaging.getTotal());
				for(Integer i =0; i<playlistSimplifiedPaging.getItems().length;i++) {
					  System.out.println("===============> category : " + i +" " + playlistSimplifiedPaging.getItems()[i].getName());
				      
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	
	@Ignore
	@Test
	public void validateLetther() {
		boolean valueValidator=iValidatorProcess.validateLetter("nuevo león");
		if(valueValidator) {
			System.out.println("Es correcto!!! solo letras y espacios");
		}else {
			System.out.println("Error!!! Contiene caracteres no permitidos");
		}
	}
	
	@Ignore
		@Test
	public void businessTest() {
		try {
			List<GeneralInformation> ltGeneralInformation = bn.getInformationPlayList("10.491","-66.902");
			ltGeneralInformation.forEach(new Consumer<GeneralInformation>() {

				@Override
				public void accept(GeneralInformation genralInfoObj) {
					if(genralInfoObj.getMessageError().equals("SN")) {
						System.out.print(" countryCod : " + genralInfoObj.getCountryCod());
						System.out.print(" cityName : " + genralInfoObj.getCityName());
						System.out.print(" currentTemp : " + genralInfoObj.getCurrentTemp());
						System.out.print(" messageError : " + genralInfoObj.getMessageError());
						System.out.print(" categorieMusic : " + genralInfoObj.getCategoryMusic());
						genralInfoObj.getPlayList().forEach((final SpotifyPlayList objSpo) ->
						System.out.println(" play name: " +objSpo.getName()));	
					}else {
						System.out.print("========ERROR!!!!!! : " + genralInfoObj.getMessageError());
					}	
				}
				 });
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	
	@Ignore
	@Test
	public void validateCoordinatesTest() {
		try {
			boolean lat= iValidatorProcess.validateCoordinateLatitud("27.2722");
			boolean longitud=iValidatorProcess.validateCoordinateLongitud("-127.2722");
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>><<Latitud: " + lat + " Longitud: " + longitud);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
