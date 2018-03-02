package model;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;

public class HTTPModels {	

	private final String USER_AGENT = "Mozilla/5.0";

	public static void main(String[] args) throws Exception {

		HTTPModels http = new HTTPModels();

		System.out.println("Testing 1 - Send Http GET request");
		http.sendGet(43);

		System.out.println("\nTesting 2 - Send Http POST request");
		http.sendPost("1645z64ad65zd4a6");

	}

	// HTTP GET request
	private String sendGet(int id) throws Exception {

		//String url = "http://89.86.39.88:1880/UGA/get";
		String url = "http://192.168.1.9:1880/UGA/get?transaction="+id;
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());
		
		return response.toString();

	}

	// HTTP POST request
	private int sendPost(String digest) throws Exception {
		/*
		//String url = "http://89.86.39.88:1880/UGA/post";
		String url = "http://192.168.1.9:1880/UGA/post";
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		String urlParameters = "digest="+digest;

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());
		
		return Integer.parseInt(response.toString());
		
		*/
		
		String urlParameters  = "{\n\"digest\":\""+digest+"\"\n}";
		byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
		int    postDataLength = postData.length;
		String request        = "http://192.168.1.9:1880/UGA/post"; //String url = "http://89.86.39.88:1880/UGA/post
		URL    url            = new URL( request );
		HttpURLConnection conn= (HttpURLConnection) url.openConnection();           
		conn.setDoOutput( true );
		conn.setInstanceFollowRedirects( false );
		conn.setRequestMethod( "POST" );
		conn.setRequestProperty( "Content-Type", "application/json"); 
		conn.setRequestProperty( "charset", "utf-8");
		conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
		conn.setUseCaches( false );
		try( DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
		   wr.write( postData );
		}
		
		int responseCode = conn.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		System.out.println("Response : "+response.toString());
		return Integer.parseInt(response.toString());
	}
}