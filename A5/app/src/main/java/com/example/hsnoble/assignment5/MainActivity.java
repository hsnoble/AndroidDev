package com.example.hsnoble.assignment5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> arr = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get data from JSON
        getData();
    }

    public void getData()
    {
        String jsonData;
        try
        {
            InputStream is = getAssets().open("data.json");
            int sz = is.available();
            byte[] buffer = new byte[sz];
            is.read();
            is.close();

            jsonData = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(jsonData);

            /*
            for (int x; x < jsonArray.length(); x++)
            {
                JSONObject obj = jsonArray.getJSONObject(x);
                arr.add()
            }*/
        } catch (IOException e)
        {
            e.printStackTrace();
        }catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}
