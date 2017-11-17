package com.example.air.tagthebus.adapter;

import android.content.Context;
import android.media.ThumbnailUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.air.tagthebus.R;
import com.example.air.tagthebus.model.Picture;

import java.util.ArrayList;

import static android.graphics.BitmapFactory.decodeFile;

/**
 * Created by Air on 16/06/2017.
 */

public class PictureAdapter extends ArrayAdapter<Picture>{

    private final int height = 80;
    private final int width = 70;
    String pictureTitle = "";

    public PictureAdapter(Context context, ArrayList<Picture> pictures) {
        super(context, 0, pictures);
    }

    @Override public View getView(int position, View convertView,
                                  ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.station_detail_row, parent, false);
            viewHolder.titleTxtView = (TextView) convertView.findViewById(R.id.titleTxtView);
            viewHolder.dateTxtView = (TextView) convertView.findViewById(R.id.dateTxtView);
            viewHolder.stationImgView = (ImageView) convertView.findViewById(R.id.stationImgView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Picture picture = getItem(position);

        if(picture.getPicture_title() != null)
            pictureTitle = picture.getPicture_title();

        viewHolder.titleTxtView.setText(pictureTitle);
        viewHolder.dateTxtView.setText(String.valueOf(picture.getPicture_date()));
        viewHolder.stationImgView.setImageBitmap(ThumbnailUtils
                .extractThumbnail(decodeFile(picture.getPicture_path()),width,height));

        return convertView;
    }

    private static class ViewHolder {
        TextView titleTxtView;
        TextView dateTxtView;
        ImageView stationImgView;
    }
}
