package com.example.air.tagthebus.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.air.tagthebus.R;
import com.example.air.tagthebus.database.DataSource;
import com.example.air.tagthebus.model.Picture;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class PictureDetailActivity extends AppCompatActivity {

    ImageView picturePreviewImgView;
    EditText titleEditTxt;
    Button addPictureBtn;
    String picturePath = "";
    String uriToParse = "";
    int stationId = 0;
    DataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picture_detail_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        picturePreviewImgView = (ImageView) findViewById(R.id.picturePreviewImgView);
        titleEditTxt = (EditText) findViewById(R.id.titleEditTxt);
        addPictureBtn = (Button) findViewById(R.id.addPictureBtn);

        //DONT LOAD IMAGES IN UI THREAD
        Intent intent = getIntent();

        if(intent.getExtras().get("pictureUri") != null)
            uriToParse = intent.getExtras().get("pictureUri").toString();

        Uri myUri = Uri.parse(uriToParse);
        picturePath = getPathFromUri(myUri);
        stationId = intent.getExtras().getInt("stationId");


        Bitmap bitmap = intent.getParcelableExtra("BitmapPicture");
        if (bitmap != null) {
            picturePreviewImgView.setImageBitmap(bitmap);
        }

        addPictureBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dataSource = new DataSource(getBaseContext());

                String pictureDate = new SimpleDateFormat("dd/MM/yyyy HH:mm a",
                        Locale.getDefault()).format(new Date()).replace("AM", "am").replace("PM","pm");

                Picture picture = new Picture();
                picture.setPicture_title(titleEditTxt.getText().toString());
                picture.setPicture_date(pictureDate);
                picture.setStation_id(stationId);
                picture.setPicture_path(picturePath);

                dataSource.addPicture(picture);

                finish();
            }
        });


    }


    private String getPathFromUri(Uri selectedImageUri)
    {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(selectedImageUri, projection, null, null, null);
        if (cursor != null)
        {
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            return cursor.getString(columnIndex);
        }
        return selectedImageUri.getPath();
    }




}
