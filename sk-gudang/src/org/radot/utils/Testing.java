package org.radot.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.codec.binary.Base64;
import org.radot.json.handlers.JsonReportingDate;

import com.google.gson.Gson;







public class Testing {
	
	public static void basicAuth() {
		try {
			String webPage = "https://api.pbdevtest.com/apiservice/oauth/token";
			String name = "73e843cd-0b28-4f98-a262-b5dbdf9ee549";
			String password = "2d45705c-e992-4bd8-b7fe-7d2d1dc6c4c1";

			String authString = name + ":" + password;
			System.out.println("auth string: " + authString);
			byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
			String authStringEnc = new String(authEncBytes);
			System.out.println("Base64 encoded auth string: " + authStringEnc);
			URL url = new URL(webPage);
			URLConnection urlConnection = url.openConnection();
			urlConnection.setRequestProperty("Authorization", "Basic " + authStringEnc);
			urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			urlConnection.setRequestProperty("keyid", "b6e1231e-3f15-48f1-aed6-d72986ea1ce9");
			urlConnection.setRequestProperty("OAUTH-Signature", "IjxhMrgLcj8Jp6K83xWF8pAO+o50ZTuE1CxkXDBvdEA=");
			urlConnection.setRequestProperty("OAUTH-Timestamp", "2017-12-09T03:52:01.000+07:00");
			urlConnection.addRequestProperty("grant_type", "client_credentials");
			
			
			InputStream is = urlConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
			}
			String result = sb.toString();

			System.out.println("*** BEGIN ***");
			System.out.println(result);
			System.out.println("*** END ***");
			
			//NzNlODQzY2QtMGIyOC00Zjk4LWEyNjItYjVkYmRmOWVlNTQ5OjJkNDU3MDVjLWU5OTItNGJkOC1iN2ZlLTdkMmQxZGM2YzRjMQ==
			//NzNlODQzY2QtMGIyOC00Zjk4LWEyNjItYjVkYmRmOWVlNTQ5OjJkNDU3MDVjLWU5OTItNGJkOC1iN2ZlLTdkMmQxZGM2YzRjMQ==
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void hmac() throws Exception{

		String permataStaticKey = "WD628b40f7f480f6445279eeb9b76003";
		
		Map<String,Object> BalInqRq = new HashMap<String,Object>();  
		
		
		Gson _k = new Gson();
		Map<String, Object> MsgRqHdr = new HashMap<String, Object>();
		MsgRqHdr.put("RequestTimestamp", "2017-07-21T14:32:01+07:00");
		MsgRqHdr.put("CustRefID", "0878987654321");
		
		Map<String, Object> InqInfo = new HashMap<String, Object>();
		InqInfo.put("AccountNumber", "701075323");
		
		BalInqRq.put("MsgRqHdr", MsgRqHdr);
		BalInqRq.put("InqInfo", InqInfo);
		
		Map<String,Object> domObj = new HashMap<String,Object>();  
		domObj.put("BalInqRq", BalInqRq);
		String hash = _k.toJson(domObj);
		System.out.println(hash);
		String message = "NzNlODQzY2QtMGIyOC00Zjk4LWEyNjItYjVkYmRmOWVlNTQ5OjJkNDU3MDVjLWU5OTItNGJkOC1iN2ZlLTdkMmQxZGM2YzRjMQ==:2017-12-09T03:52:01.000+07:00:grant_type=client_credentials";
		Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
		SecretKeySpec secret_key= new SecretKeySpec(permataStaticKey.getBytes(), "HmacSHA256");
		sha256_HMAC.init(secret_key);
		String hash2 = Base64.encodeBase64String(sha256_HMAC.doFinal(message.getBytes("UTF-8")));
		
		System.out.println(hash2);
		System.out.println(message);
		
	}
	
	public static void testurl() {
		
		String https_url = "https://jsonplaceholder.typicode.com/posts";
        URL url;
        try {
            byte[] postData = "test".getBytes();
            url = new URL(https_url);

            HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            connection.setReadTimeout(10000);
            connection.setDoOutput(true);
            
            connection.connect();
            connection.getOutputStream().write(postData);

            Reader in  = null;
            try {
                in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            }catch(SocketException ce) {
              ce.printStackTrace();
            }
            StringBuilder sb = new StringBuilder();

            for (int c; (c = in.read()) >= 0;) {
                sb.append((char) c);
            }
            System.out.println(sb);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

	}
	
	public static void main(String[] args) throws Exception{
	
		testurl();
		
		//basicAuth();
//		SimpleDateFormat p = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
//		System.out.println(p.format(new Date())+"+07:00");
//		hmac();
		//GB0L+1v34aywchmwhi/I6RMKAtR/44uMNCgzWcsczxM=
		//GB0L+1v34aywchmwhi/I6RMKAtR/44uMNCgzWcsczxM=
		//NzNlODQzY2QtMGIyOC00Zjk4LWEyNjItYjVkYmRmOWVlNTQ6MmQ0NTcwNWMtZTk5Mi00YmQ4LWI3ZmUtN2QyZDFkYzZjNGMx
		//NzNlODQzY2QtMGIyOC00Zjk4LWEyNjItYjVkYmRmOWVlNTQ5OjJkNDU3MDVjLWU5OTItNGJkOC1iN2ZlLTdkMmQxZGM2YzRjMQ==
		
		//15hBgzeXpK6M8WZJqPwL5z615iFxFS2OW1hKTnUV6c17OEuJKSXsKy:2017-11-07T10:22:57:{"BalInqRq":{"MsgRqHdr":{"RequestTimestamp":"2017-07-21T14:32:01+07:00","CustRefID":"0878987654321"},"InqInfo":{"AccountNumber":"701075323"}}}
		//15hBgzeXpK6M8WZJqPwL5z615iFxFS2OW1hKTnUV6c17OEuJKSXsKy:2017-11-07T10:22:57:{"BalInqRq":{"MsgRqHdr":{"RequestTimestamp":"2017-07-21T14:32:01+07:00","CustRefID":"0878987654321"},"InqInfo":{"AccountNumber":"701075323"}}}
		
	}
}
