package br.com.nmsystems.baireslistdemo.util;

import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class ToolBox {
    private static final Map<Character, Character> ACCENT_MAP = initAccentMap();

    private static Map<Character, Character> initAccentMap() {
        Map<Character, Character> map = new HashMap();
        map.put('à', 'a');
        map.put('á', 'a');
        map.put('â', 'a');
        map.put('ã', 'a');
        map.put('ä', 'a');
        map.put('å', 'a');
        map.put('ç', 'c');
        map.put('č', 'c');
        map.put('ć', 'c');
        map.put('è', 'e');
        map.put('é', 'e');
        map.put('ê', 'e');
        map.put('ë', 'e');
        map.put('ì', 'i');
        map.put('í', 'i');
        map.put('î', 'i');
        map.put('ï', 'i');
        map.put('ñ', 'n');
        map.put('ò', 'o');
        map.put('ó', 'o');
        map.put('ô', 'o');
        map.put('õ', 'o');
        map.put('ö', 'o');
        map.put('ø', 'o');
        map.put('ß', 's');
        map.put('§', 's');
        map.put('ù', 'u');
        map.put('ú', 'u');
        map.put('û', 'u');
        map.put('ü', 'u');
        map.put('ÿ', 'y');
        return map;
    }

    public static String AccentMapper(String string) {
        if (string == null) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder(string);

            for (int i = 0; i < string.length(); ++i) {
                Character c = (Character) ACCENT_MAP.get(sb.charAt(i));
                if (c != null) {
                    sb.setCharAt(i, c);
                }
            }

            return sb.toString();
        }
    }

    public static String getBreakNewLine(String s) {
        try {
            String[] lines = s.split("\\r?\\n");
            //
            if (lines != null && lines.length > 0) {
                return lines[0];
            } else {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getSafeSubstring(String s, int maxLength) {
        if (!TextUtils.isEmpty(s)) {
            if (s.length() >= maxLength) {
                return s.substring(0, maxLength) + " ...";
            }
        }
        return s;
    }

    public static String connWebService(String urlEnd, String params) throws Exception {
        StringBuilder sb = new StringBuilder();

        URL url;
        HttpsURLConnection conn = null;

        url = new URL(urlEnd);

        conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
        conn.setRequestProperty("X-Mobile-Platform", "Android;charset=utf-8");

        conn.setReadTimeout(60000);
        conn.setConnectTimeout(60000);

        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);
        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

        writer.write(params.toCharArray());
        writer.flush();
        writer.close();
        os.close();

        int httpStatus = conn.getResponseCode();
        if (httpStatus == HttpURLConnection.HTTP_OK) {
            sb.append(readStreamAux(conn.getInputStream()));
        } else {
            throw new Exception("WS_EXCEPTION_HTTP_STATUS_ERROR");
        }

        if (conn != null) {
            conn.disconnect();
        }

        return sb.toString();
    }

    private static String readStreamAux(InputStream inputStream) {
        Reader reader = null;
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];

        try {

            reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            int n;

            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }

        } catch (Exception e) {
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return writer.toString();
    }

    public static String sDays(String sDTFormat, String mDate, int days_to_add) {
        String sFormat = null;
        String sResults = "";

        if (sDTFormat == null || sDTFormat.isEmpty()) {
            sFormat = "yyyy-MM-dd";
        } else {
            sFormat = sDTFormat;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(sFormat);

        Calendar calAux = Calendar.getInstance();
        //
        try{
            calAux.setTime(dateFormat.parse(mDate));
        } catch (Exception e){
        }
        //
        calAux.set(Calendar.DAY_OF_MONTH, calAux.get(Calendar.DAY_OF_MONTH) + (days_to_add));
        //


        try {
            sResults = dateFormat.format(calAux.getTime());
        } catch (Exception e) {
            sResults = "1900-01-01";
        }

        return sResults;
    }

}
