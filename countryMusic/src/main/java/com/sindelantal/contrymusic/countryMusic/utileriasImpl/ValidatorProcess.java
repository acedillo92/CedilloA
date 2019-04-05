package com.sindelantal.contrymusic.countryMusic.utileriasImpl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.sindelantal.contrymusic.countryMusic.utilerias.IValidatorProcess;


@Service
public class ValidatorProcess implements IValidatorProcess {
	private Pattern patLetter = Pattern.compile("[^A-Za-z ]");
	private String[] categories = {"pop","rock","classical","party"};
	
	@Override
	public boolean validateLetter(String value) {
		// TODO Auto-generated method stub
		Matcher valueMatch=patLetter.matcher(value);
		if(valueMatch.find())
           return false;
        
		return true;
	}


	@Override
	public String returnTempCelcius(String currencyTemp) {
		// TODO Auto-generated method stub
		Double gradosCel = Double.parseDouble(currencyTemp) -273.0 ;
		return gradosCel.toString();
	}


	@Override
	public boolean validateCategories(String categoryValue) {
		// TODO Auto-generated method stu
		boolean categoriesValue=false;
		for(Integer i =0; i<categories.length;i++) {
			if(categories[0].equals(categoryValue)) {
				categoriesValue=true;
			}
		}
		return categoriesValue;
	}


	@Override
	public boolean validateCoordinateLatitud(String valueCoordinate) {
		try {
			Double latitud=Double.parseDouble(valueCoordinate);
			System.out.println("latitud ==> " + latitud);
			if(latitud>=-90 && latitud<=90) {
				return true;
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	
	@Override
	public boolean validateCoordinateLongitud(String valueCoordinate) {
		try {
			Double latitud=Double.parseDouble(valueCoordinate);
			System.out.println("latitud ==> " + latitud);
			if(latitud>=-180 && latitud<=180) {
				return true;
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return false;
	}

}
