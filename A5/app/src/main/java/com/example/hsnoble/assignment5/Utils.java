package com.example.hsnoble.assignment5;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Stephen Noble on 4/10/2018.
 */

public class Utils {
    public static final String LOG_TAG = Utils.class.getSimpleName();
    public static List<String> fetchEarthquakeData(String requestUrl)
    {
        URL url = createUrl(requestUrl);
        String res = null;
        try{
            res = httpReq(url);
        } catch (IOException e){
            Log.e(LOG_TAG, "Error Closing input stream", e);
        }
        return extractFeatureFromJson(res);
    }
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try{
            url = new URL(stringUrl);
        }catch (MalformedURLException e){
            Log.e(LOG_TAG,"Error with creating URL ", e);
        }

        return url;
    }
    private static String httpReq(URL url) throws IOException
    {
        String res ="";
        if(url==null)
        {
            return res;
        }

        HttpURLConnection urlConnection= null;
        InputStream inputStream = null;
        try
        {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if(urlConnection.getResponseCode()==200){
                inputStream = urlConnection.getInputStream();
                res=streamReader(inputStream);
            }else{
                Log.e(LOG_TAG,"Error response code: "+urlConnection.getResponseCode());
            }
        }catch (IOException e){
            Log.e(LOG_TAG, "Problem retriving the earthquake JSON reults.",e);
        }finally {
            if(urlConnection!=null){
                urlConnection.disconnect();
            }
            if(inputStream!=null){
                inputStream.close();
            }
        }
        return res;

    }
    private static String streamReader(InputStream inputStream)throws IOException{
        StringBuffer output = new StringBuffer();
        if(inputStream != null)
        {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line!=null){
                output.append(line);
                line=reader.readLine();
            }

        }
        return output.toString();
    }
    private static List<String> extractFeatureFromJson(String earthquakeJSON){
        if(TextUtils.isEmpty(earthquakeJSON)){
            return null;
        }
        List<String> quakeList = new ArrayList<>();
        //List<String> urls = new ArrayList<>();
        try{
            JSONObject baseJsonResponse = new JSONObject(earthquakeJSON);
            JSONArray earthquakeArray = baseJsonResponse.getJSONArray("features");
            for(int i=0;i<earthquakeArray.length();i++){
                JSONObject currentEarthquake = earthquakeArray.getJSONObject(i);
                JSONObject properties = currentEarthquake.getJSONObject("properties");
                String title = properties.getString("title");
                // String time = properties.getString("time");
                Long date1 = properties.getLong("time");

                Date dateObject = new Date(date1);
                SimpleDateFormat dateFormatter = new SimpleDateFormat("EEE MMM dd  ");
                SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss z yyyy");
                String dateToDisplay = dateFormatter.format(dateObject);
                String timeToDisplay = timeFormatter.format(dateObject);
                String time = dateToDisplay + " "+ timeToDisplay ;


                String url = properties.getString("url");
                quakeList.add(title + "@@" + time + "@@" + url);


            }
            return quakeList;
        }catch (JSONException e){
            Log.e(LOG_TAG,"Problem Parsing the earthquake JSON results", e);
        }
        return null;

    }
}
