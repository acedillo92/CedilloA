package com.sindelantal.contrymusic.countryMusic.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.function.Consumer;

import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sindelantal.contrymusic.countryMusic.businessImpl.BusinessPlayListIml;
import com.sindelantal.contrymusic.countryMusic.vo.GeneralInformation;

@RestController
public class countryMusicService {

	@Autowired
	BusinessPlayListIml businessPlayListIml;

	@GetMapping("/getListSpotifyMusicByCity/{nameCityOrLatitud}/{longitud}")
	public ResponseEntity<?> getListSpotifyMusicByCity(@PathVariable String nameCityOrLatitud, @PathVariable String longitud ) throws IOException {
		try {
			List<GeneralInformation> ltGeneralInformation = businessPlayListIml.getInformationPlayList(nameCityOrLatitud,longitud);
			if (ltGeneralInformation.isEmpty()) {
				return new ResponseEntity<>("Error Interno!! Lista Vacia ", HttpStatus.BAD_REQUEST);
			}

			if (ltGeneralInformation.get(0).getMessageError().equals("SN")) {
				return new ResponseEntity<>(ltGeneralInformation, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(ltGeneralInformation.get(0).getMessageError(), HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return new ResponseEntity<>("Error Interno!!! ", HttpStatus.BAD_REQUEST);
	}

}
