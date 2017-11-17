package com.example.air.tagthebus.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.air.tagthebus.R;
import com.example.air.tagthebus.adapter.PictureAdapter;
import com.example.air.tagthebus.database.DataSource;
import com.example.air.tagthebus.model.Picture;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


/**
 * Created by Air on 15/06/2017.
 */

public class PicturesActivity extends AppCompatActivity {

    private Uri capturedPictureURI;
    PictureAdapter pictureAdapter;
    private ArrayList<Picture> pictures;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    ListView listView;
    DataSource dataSource;
    Picture picture;
    int stationId = 0;
    String stationName = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pictures_activity);

        TextView toolbar_titleTxtView = (TextView) findViewById(R.id.toolbar_title);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_child_toolbar);

        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        stationId = intent.getExtras().getInt("stationId");
        stationName = intent.getExtras().getString("stationName");

        toolbar_titleTxtView.setText(stationName);

        pictures = new ArrayList<>();
        pictureAdapter = new PictureAdapter(this, pictures);

        listView = (ListView) findViewById(R.id.picturesListView);
        listView.setAdapter(pictureAdapter);
        addItemClickListener(listView);
        initDb(stationId);

    }

    private void initDb(int stationId) {
        dataSource = new DataSource(this);
        for (Picture picture : dataSource.getPictureByStationId(stationId)){
            pictures.add(picture);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_add){
            takePicture();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {
        getMenuInflater().inflate( R.menu.actionbar, menu );
        return true;
    }

    private void takePicture() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmm", Locale.getDefault()).format(new Date());
            String pictureName = stationName + "_"+ timeStamp +  ".jpg";

            File storageDir = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES);
            String picturePath = storageDir.getAbsolutePath() + "/" + pictureName;

            File file = new File(picturePath);
            Uri outputFileUri = Uri.fromFile(file);

            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

        }
    }


    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

                if(data != null){

                    Bitmap bitmapPicture = (Bitmap) data.getExtras().get("data");
                    Uri uri = data.getData();

                    Intent intent = new Intent(this, PictureDetailActivity.class);
                    intent.putExtra("BitmapPicture", bitmapPicture);
                    intent.putExtra("pictureUri", uri.toString());
                    intent.putExtra("stationId", stationId);
                    startActivity(intent);
                }
            }
    }

    private void addItemClickListener(final ListView listView) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                picture = (Picture) listView.getItemAtPosition(position);

                AlertDialog.Builder alert = new AlertDialog.Builder(PicturesActivity.this);
                alert.setTitle("Confirm");
                alert.setMessage("Are you sure ?");
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dataSource.deletePicture(picture.getPicture_id());
                        pictureAdapter.remove(picture);
                        pictureAdapter.notifyDataSetChanged();
                    }
                });
                alert.show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        pictures.clear();
        initDb(stationId);
    }


    @Override protected void onSaveInstanceState(Bundle outState) {
        if (capturedPictureURI != null) {
            outState.putString("capturedPictureURI", capturedPictureURI.toString());
        }
        super.onSaveInstanceState(outState);
    }

    @Override protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey("capturedPictureURI")) {
            capturedPictureURI = Uri.parse(savedInstanceState.getString("capturedPictureURI"));
        }
    }



}
