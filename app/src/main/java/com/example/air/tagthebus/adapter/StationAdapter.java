package com.example.air.tagthebus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.air.tagthebus.R;
import com.example.air.tagthebus.model.Station;

import java.util.List;

/**
 * Created by Air on 15/06/2017.
 */

public class StationAdapter extends ArrayAdapter<Station> {

    private List<Station> stations;
    private Context context;

    public StationAdapter(Context context, List<Station> stations) {
        super(context, R.layout.station_row, stations);
        this.context = context;
        this.stations = stations;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Station station = getItem(position);

        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.station_row, parent, false);
            holder.stationNameTxtView = (TextView) convertView.findViewById(R.id.stationNameTxtView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.stationNameTxtView.setText(station.getStreet_name());

        return convertView;
    }

    static class ViewHolder {
        TextView stationNameTxtView;

    }

}
