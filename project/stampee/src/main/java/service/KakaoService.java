package service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
/*
public class KakaoService {
	private static final String ACCESS_TOKEN = "";

	public static void SendKakaotalk(String temID, String uuID) throws Exception{
		String url = "https://kapi.kakao.com/v1/api/talk/friends/message/scrap/send" ;
		URL obj = new URL(url) ;
		HttpURLConnection conn = (HttpURLConnection)obj.openConnection() ;

		conn.setRequestMethod("POST");
		conn.setRequestProperty("Authorization", "Bearer " + ACCESS_TOKEN);
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

		String urlParameters = "template_id=" + temID + "&receiver_uuids=[\"" + uuID + "\"]";

		conn.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		// 서버 응답 코드를 받아 출력
		int responseCode = conn.getResponseCode();
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		System.out.println(response.toString());
	}

	public static String FindUUID(String apiUrl, String accessToken) throws Exception{
		URL url = new URL(apiUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Authorization", "Bearer " + accessToken);

		int responseCode = conn.getResponseCode();
		System.out.println("Response Code: " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		return response.toString();
	}
}
*/