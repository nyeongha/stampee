package service;

import javafx.application.Application;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.web.WebErrorEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng ;

public class MapService extends Application {


	private static Float[] findGeoPoint(String location){

		GeoApiContext context = new GeoApiContext.Builder()
			.apiKey("AIzaSyBvjCKUVPjnHctl6ZPoz-jrbVKIzeydo9E")
			.build();
		try {
			GeocodingResult[] results = GeocodingApi.geocode(context, location).await();
			if (results.length > 0) {
				LatLng latLng = results[0].geometry.location;
				System.out.println(" found: " + latLng.lat + ", " + latLng.lng);
				return new Float[]{(float) latLng.lat, (float) latLng.lng};
			} else {
				System.out.println("No results found for address: " + location);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			context.shutdown();
		}
		return null;

		// try {
		// 	Geocoder geocoder = new Geocoder();
		// 	GeocoderRequest geocoderRequest = new GeocoderRequestBuilder()
		// 		.setAddress(new String(location.getBytes("UTF-8"), "UTF-8"))
		// 		.setLanguage("ko")
		// 		.getGeocoderRequest();
		//
		// 	System.out.println("Geocoding request for address: " + new String(location.getBytes("UTF-8"), "UTF-8"));
		// 	GeocodeResponse geocoderResponse = geocoder.geocode(geocoderRequest);
		// 	System.out.println("Geocoding response received");
		// 	System.out.println("Full API Response: " + geocoderResponse.toString());
		//
		// 	if (!geocoderResponse.getResults().isEmpty()) {
		// 		GeocoderResult result = geocoderResponse.getResults().get(0);
		// 		LatLng latLng = result.getGeometry().getLocation();
		// 		System.out.println("Coordinates found: " + latLng.getLat() + ", " + latLng.getLng());
		// 		return new Float[]{latLng.getLat().floatValue(), latLng.getLng().floatValue()};
		// 	} else {
		// 		System.err.println("No results found for address: " + new String(location.getBytes("UTF-8"), "UTF-8"));
		// 	}
		// } catch (IOException e) {
		// 	System.err.println("Geocoding failed: " + e.getMessage());
		// 	e.printStackTrace();
		// }
		//
		// return null;


		// Geocoder geocoder = new Geocoder();
		// GeocoderRequest geocoderRequest = new GeocoderRequestBuilder()
		// 	.setAddress(location).setLanguage("ko").getGeocoderRequest();
		//
		//
		// try {
		// 	System.out.println("Geocoding request for address: " + location);
		// 	GeocodeResponse geocoderResponse = geocoder.geocode(geocoderRequest);
		// 	System.out.println("Geocoding response received");
		//
		// 	if (!geocoderResponse.getResults().isEmpty()) {
		// 		GeocoderResult result = geocoderResponse.getResults().get(0);
		// 		LatLng latLng = result.getGeometry().getLocation();
		// 		return new Float[]{latLng.getLat().floatValue(), latLng.getLng().floatValue()};
		// 	} else {
		// 		System.err.println("No results found for address: " + location);
		// 	}
		// } catch (IOException e) {
		// 	System.err.println("Geocoding failed: " + e.getMessage());
		// 	e.printStackTrace();  // 스택 트레이스 출력
		// }
		// return null;
	}


	@Override
	public void start(Stage stage) {
		String location = "서울 종로구 성균관로3길 15";
		Float[] lntLng = findGeoPoint(location);
		if (lntLng != null) {
			float lat = lntLng[0];
			float lng = lntLng[1];
			WebView webView = new WebView();
			webView.getEngine().loadContent(

				"<html><body>" +
					"<div id='map' style='width:70%;height:70%;'></div>" +
					"<script src='https://maps.googleapis.com/maps/api/js?key=AIzaSyBDzyQVcNJVv9m0b_9MDX03bWav-T_yYrk'></script>" +
					"<script>" +
					"var map = new google.maps.Map(document.getElementById('map'), {" +
					"  center: {lat: " + lat + ", lng: " + lng + "}," +

					"  zoom: 13, " +
					" mapTypeId: 'roadmap'" +
					"});" +
					"</script>" +
					"</body></html>"
			);
			// webView.getEngine().setOnError((WebErrorEvent event) -> {
			// 	System.out.println("WebView error: " + event.getMessage());
			// 	webView.getEngine().loadContent("<html><body><h1>지도를 로드할 수 없습니다.</h1></body></html>");
			// });
			// webView.getEngine().getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
			// 	if (newValue == Worker.State.SUCCEEDED) {
			// 		webView.getEngine().executeScript("initMap();"); // initMap은 지도를 초기화하는 JavaScript 함수
			// 	}
			// });

			// 화면 사이즈 설정 및 화면 출력
			Scene scene = new Scene(webView, 640, 400);
			stage.setScene(scene);
			stage.show();


		} else {
			System.out.println("좌표를 찾을 수 없습니다.");
		}
	}

	public static void launchApp(String[] args){
		launch(args);
	}

}
