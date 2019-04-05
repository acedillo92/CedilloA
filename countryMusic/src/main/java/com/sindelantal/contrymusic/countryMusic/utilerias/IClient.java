package com.sindelantal.contrymusic.countryMusic.utilerias;

import java.util.List;

import com.sindelantal.contrymusic.countryMusic.vo.cityWeather;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.requests.data.browse.GetCategorysPlaylistsRequest;

public interface IClient {
	List<cityWeather> clientWeatherByCityName(String cityName);
	Paging<PlaylistSimplified> getCategorysPlaylists_Sync(GetCategorysPlaylistsRequest getCategoryRequest);
	List<cityWeather> clientWeatherByCoordinates(String latitud, String ongitud);
}
