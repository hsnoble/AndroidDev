package com.example.hsnoble.assignment5;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String dataLink = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2018-01-01&endtime=2018-12-31&minmagnitude=6";
    ListView earthquakeList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EarthquakeAsync e = new EarthquakeAsync();
        e.execute(dataLink);
    }

    class EarthquakeAsync extends AsyncTask<String, Void, List<String>>
    {
        @Override
        protected List<String> doInBackground(String... stringurl)
        {
            return Utils.fetchEarthquakeData(stringurl[0]);
        }
        public void onPostExecute(List<String> postExecuteResult)
        {
            CustomListAdapter arrayAdapter = new CustomListAdapter (MainActivity.this, postExecuteResult);
            earthquakeList= findViewById(R.id.data);
            earthquakeList.setAdapter(arrayAdapter);

            earthquakeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    TextView currentURL = view.findViewById(R.id.url);
                    Intent broswer = new Intent(Intent.ACTION_VIEW, Uri.parse(currentURL.getText().toString()));
                    startActivity(broswer);


                }
            });
        }
    }

}
class CustomListAdapter extends ArrayAdapter<String>
{
    Activity context;
    List<String> itemname1;

    public CustomListAdapter (Activity activity, List<String> itemnameA)
    {
        super(activity, R.layout.one_quake,itemnameA );
        this.context=activity;
        this.itemname1=itemnameA;
    }

    public View getView(int position, View view, ViewGroup parent)
    {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.one_quake,null,true);
        String earthInfo[] = itemname1.get(position).split("@@");
        TextView textInfo = rowView.findViewById(R.id.nameID);
        textInfo.setText(earthInfo[0]);
        if(position%2 == 0)
        {
            textInfo.setBackgroundColor(Color.parseColor("#00FF00"));
        }
        textInfo = rowView.findViewById(R.id.date);



        textInfo.setText(earthInfo[1]);
        if(position%2 == 0)
        {
            textInfo.setBackgroundColor(Color.parseColor("#00FF00"));
        }
        textInfo = rowView.findViewById(R.id.url);
        textInfo.setText(earthInfo[2]);
        textInfo.setVisibility(View.GONE);


        return rowView;

    }
}
