package service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

import javafx.fxml.FXML;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;
import util.GoogleAPIConfigLoader;

public class MapService {
	@FXML private WebView webView;

	private WebEngine webEngine;
	private static final String API_KEY = GoogleAPIConfigLoader.getApiKey();

	@FXML
	public void initialize() {
		webEngine = webView.getEngine();
	}

	private static Float[] findGeoPoint(String location) {
		GeoApiContext context = new GeoApiContext.Builder()
			.apiKey(API_KEY)
			.build();
		try {
			GeocodingResult[] results = GeocodingApi.geocode(context, location).await();
			if (results.length > 0) {
				LatLng latLng = results[0].geometry.location;
				return new Float[]{(float) latLng.lat, (float) latLng.lng};
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			context.shutdown();
		}
		return null;
	}

	private void loadMap(float lat, float lng, String location, String cafeName) {
		String mapContent =
			"<!DOCTYPE html>" +
				"<html>" +
				"<head>" +
				"    <title>Leaflet Map</title>" +
				"    <link rel=\"stylesheet\" href=\"https://unpkg.com/leaflet@1.7.1/dist/leaflet.css\" />" +
				"    <script src=\"https://unpkg.com/leaflet@1.7.1/dist/leaflet.js\"></script>" +
				"    <style>" +
				"        html, body { height: 100%; width: 100%; margin: 0; padding: 0; }" +
				"#map { height: 200px; width: 100%; margin: 0; padding: 0; }" +
				"    </style>" +
				"</head>" +
				"<body>" +
				"    <div id=\"map\"></div>" +
				"    <script>" +
				"        var map = L.map('map').setView([" + lat + ", " + lng + "], 15);" +
				"        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {" +
				"            attribution: '&copy; <a href=\"https://www.openstreetmap.org/copyright\">OpenStreetMap</a> contributors'" +
				"        }).addTo(map);" +
				"        L.marker([" + lat + ", " + lng + "]).addTo(map)" +
				"            .bindPopup('" + location + "<br><center>" + cafeName + "</center>')" +
				"            .openPopup();" +
				"    </script>" +
				"</body>" +
				"</html>";

		webEngine.loadContent(mapContent);
	}

	public void initializeMap(String name, String address) {
		Float[] coords = findGeoPoint(address);
		if (coords != null) {
			loadMap(coords[0], coords[1], address, name);
		}
	}
}