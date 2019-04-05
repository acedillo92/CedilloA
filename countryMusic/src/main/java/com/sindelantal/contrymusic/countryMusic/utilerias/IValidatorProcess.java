package com.sindelantal.contrymusic.countryMusic.utilerias;

public interface IValidatorProcess {
	boolean validateLetter(String value);
	String  returnTempCelcius(String currencyTemp);
	boolean validateCategories(String categoryValue);
	boolean validateCoordinateLatitud(String valueCoordinate);
	boolean validateCoordinateLongitud(String valueCoordinate);
}
