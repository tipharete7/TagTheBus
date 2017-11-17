package com.example.air.tagthebus.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.air.tagthebus.R;
import com.example.air.tagthebus.adapter.StationAdapter;
import com.example.air.tagthebus.model.Station;
import com.example.air.tagthebus.webservice.WebService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    StationAdapter adapter;
    static WebService webService = new WebService();
    List<Station> stationsList = new ArrayList<>();
    Station station;
    int stationId  = 0;
    String stationName = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        listView = (ListView) findViewById(R.id.stationsListView);
        new MyTask().execute();
        addItemClickListener(listView);
    }

    private void addItemClickListener(final ListView listView) {

        listView.setOnItemLongClickListener( new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getBaseContext(), PicturesActivity.class);
                station = (Station) listView.getItemAtPosition(position);
                stationName = station.getStreet_name();
                stationId = (int) id + 1;
                intent.putExtra("stationId", stationId);
                intent.putExtra("stationName", stationName);
                startActivity(intent);
                return true;
            }
        });
    }



    private class MyTask extends AsyncTask<String, Void, StationAdapter>{

        @Override
        protected StationAdapter doInBackground(String... params) {
            stationsList = webService.getStations();
            adapter = new StationAdapter(getBaseContext(),stationsList);
            return adapter;
        }

        @Override
        protected void onPostExecute(StationAdapter adapter) {
            super.onPostExecute(adapter);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

}
