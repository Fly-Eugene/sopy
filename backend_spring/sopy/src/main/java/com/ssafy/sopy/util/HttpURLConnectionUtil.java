package com.ssafy.sopy.util;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

@Component
public class HttpURLConnectionUtil {
    private final int TIMEOUT;

    public HttpURLConnectionUtil() {
        this.TIMEOUT = 2100000000;
    }

    public JSONObject get(String strUrl) {
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(strUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(TIMEOUT); //서버에 연결되는 Timeout 시간 설정
            con.setReadTimeout(TIMEOUT); // InputStream 읽어 오는 Timeout 시간 설정
            con.setRequestMethod("GET");
            con.setDoOutput(false);

            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(con.getInputStream(), "utf-8"));
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                br.close();
                System.out.println("" + sb.toString());
            } else {
                System.out.println(con.getResponseMessage());
            }

        } catch (Exception e) {
            System.err.println(e.toString());
        } finally {
            return null;
//            return new JSONObject(sb.toString());
        }
    }

    public JSONObject post(String strUrl,Object jsonData){
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(strUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(TIMEOUT); //서버에 연결되는 Timeout 시간 설정
            con.setReadTimeout(TIMEOUT); // InputStream 읽어 오는 Timeout 시간 설정
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoInput(true);
            con.setDoOutput(true); //POST 데이터를 OutputStream으로 넘겨 주겠다는 설정
            con.setUseCaches(false);
            con.setDefaultUseCaches(false);

            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
            System.out.println(mapToJsonStr((HashMap<String, String>) jsonData));
//            wr.write("{\"name\": \"Upendra\", \"job\": \"Programmer\"}"); //json 형식의 message 전달
            wr.write(mapToJsonStr((HashMap<String, String>) jsonData));
            wr.flush();

            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(con.getInputStream(), "utf-8"));
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                br.close();
                System.out.println("" + sb.toString());
            } else {
                System.out.println(con.getResponseMessage());
            }
        } catch (Exception e){
            System.err.println(e.toString());
        } finally {
//            return new JSONObject(sb.toString());
            return null;
        }
    }
    public String mapToJsonStr(HashMap<String, String> map){
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        map.forEach((key, value)->{
            sb.append("\""+ key + "\"" + ": " + "\"" + convert(value) + "\"" + ", ");
        });
        return sb.toString().substring(0, sb.length()-2) + "}";
    }
    public String convert(String src){
        return src.replace("\\", "/");
    }
}
