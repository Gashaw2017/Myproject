package com.geosolution.app.service;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Service;

import com.geosolution.app.dao.StoreRepository;
import com.geosolution.app.entity.Store;
import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.google.code.geocoder.model.LatLng;

@Service
public class StoreService {

	@Autowired
	private StoreRepository storeRepository;
	
	private Client client = ClientBuilder.newClient();
	private WebTarget target;
	private static final Logger LOG = Logger.getLogger("StoreService");
	
	private static final String GOOGLE_GEOCODING_URL = "https://maps.googleapis.com/maps/api/geocode/json";
	private static final String KEY_VALUE = "AIzaSyBlLGs9_xpF30lHQJpTJjJWD9X2nP-KMx0";

	public List<Store> getAllStores() {
		List<Store> allStores = storeRepository.findAll();
		allStores.forEach(store -> reverseGeoCoding(store));
		return allStores;
	}

	//Method that converts geographic latitude and longitude to string form of physical address 
	public void reverseGeoCoding(Store store) {
		target = client.target(GOOGLE_GEOCODING_URL)
			    .queryParam("latlng", store.getLatitude().concat(",").concat(store.getLongitude()))
			    .queryParam("key", KEY_VALUE);
		String fullAddress = null;
		String jsonString = target.request().get(String.class);
		JSONObject jsonObject = null;
		try {
			jsonObject = new JSONObject(jsonString);
			JSONArray resultsArray = jsonObject.getJSONArray("results");
			fullAddress = resultsArray.getJSONObject(0).getString("formatted_address");
			store.setPhysicalAddress(fullAddress);
		} catch (JSONException exception) {
			LOG.info(exception.getLocalizedMessage());
		}
	}
	
	//Method that converts string form of physical address to geographic latitude and longitude
	public void performGeoCoding(Store store) {
		target = client.target(GOOGLE_GEOCODING_URL)
				.queryParam("address", store.getPhysicalAddress())
				.queryParam("key", KEY_VALUE);
		double latitude = 0.0;
		double longitude = 0.0;
		String jsonString = target.request().get(String.class);
		try {
			latitude = new JSONObject(jsonString).getJSONArray("results")
							.getJSONObject(0).getJSONObject("geometry")
							.getJSONObject("location")
							.getDouble("lat");
			longitude = new JSONObject(jsonString).getJSONArray("results")
							.getJSONObject(0).getJSONObject("geometry")
							.getJSONObject("location")
							.getDouble("lng");
			store.setLatitude(Double.toString(latitude));
			store.setLongitude(Double.toString(longitude));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public static String getLocation(Store store) {
		Geocoder geocoder = new Geocoder();
		LatLng p = new LatLng(store.getLatitude(), store.getLongitude());
		GeocoderRequest geocoderRequest = new GeocoderRequestBuilder()
				.setLocation(p).setLanguage("en").getGeocoderRequest();
		GeocodeResponse geocoderResponse;
		try {
			geocoderResponse = geocoder.geocode(geocoderRequest);
			GeocoderResult result = geocoderResponse.getResults().get(0);

			return result.getFormattedAddress();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public void addNewStore(Store store) {
		performGeoCoding(store);
		storeRepository.save(store);
	}

	public List<Store> getStoresByName(String storeName) {
		return storeRepository.findByName(storeName);
	}
	
	public void setStoreRepository(StoreRepository storeRepository) {
		this.storeRepository = storeRepository;
	}
	
	public void setTarget(WebTarget target) {
		this.target = target;
	}
	
	public void setClient(Client client) {
		this.client = client;
	}
	

}
