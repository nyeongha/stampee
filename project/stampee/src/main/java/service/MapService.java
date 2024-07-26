package service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import javafx.scene.web.WebEngine;


public class MapService extends Application {

	private WebView webView;
	private WebEngine webEngine;
	private static final String API_KEY = "AIzaSyBDzyQVcNJVv9m0b_9MDX03bWav-T_yYrk";

	@Override
	public void start(Stage stage) {
		initializeWebView();
		setupWebViewSettings();

		// location에 주소 입력 필요(동적으로 작업할 시 변수 활용)
		String location = "서울 종로구 성균관로3길 15";
		Float[] coords = findGeoPoint(location);
		if (coords != null) {
			loadMap(coords[0], coords[1], location);
		} else {
			System.out.println("Failed to find coordinates for the given location.");
		}

		Scene scene = new Scene(webView, 800, 600);
		stage.setScene(scene);
		stage.setTitle("Stampee Cafe 위치 정보");
		stage.show();
	}

	private void initializeWebView() {
		webView = new WebView();
		webEngine = webView.getEngine();
	}

	private void setupWebViewSettings() {
		webEngine.setJavaScriptEnabled(true);
	}

	private static Float[] findGeoPoint(String location) {
		GeoApiContext context = new GeoApiContext.Builder()
			.apiKey(API_KEY)
			.build();
		try {
			GeocodingResult[] results = GeocodingApi.geocode(context, location).await();
			if (results.length > 0) {
				LatLng latLng = results[0].geometry.location;
				System.out.println("Found: " + latLng.lat + ", " + latLng.lng);
				return new Float[]{(float) latLng.lat, (float) latLng.lng};
			} else {
				System.out.println("no result : " + location);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			context.shutdown();
		}
		return null;
	}

	private void loadMap(float lat, float lng, String location) {
		String mapContent =
			"<!DOCTYPE html>" +
				"<html>" +
				"<head>" +
				"    <title>Leaflet Map</title>" +
				"    <link rel=\"stylesheet\" href=\"https://unpkg.com/leaflet@1.7.1/dist/leaflet.css\" />" +
				"    <script src=\"https://unpkg.com/leaflet@1.7.1/dist/leaflet.js\"></script>" +
				"    <style>" +
				"        html, body, #map { height: 100%; width: 100%; margin: 0; padding: 0; }" +
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
				"            .bindPopup('" + location +"<br>"+"<center>무슨무슨 카페<center>" + "')" +
				"            .openPopup();" +
				"    </script>" +
				"</body>" +
				"</html>";

		webEngine.loadContent(mapContent);
	}


	public static void launchApp(String[] args){
		launch(args);
	}
}