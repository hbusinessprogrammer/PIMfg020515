package com.pimfg.www.pimfg020515.userInterface;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.pimfg.www.pimfg020515.R;
import com.pimfg.www.pimfg020515.database.DBAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by Hyuk on 2/5/2015.
 */
public class Cable extends Fragment {
    public static EditText txtCableLength;
    private LinearLayout linearLayout;
    private ArrayList<String> urlLinks;
    int imageLink;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.cable, container, false);
        txtCableLength=(EditText)view.findViewById(R.id.txt_cable_length);
        urlLinks=new ArrayList<String>();
        urlLinks.add("http://www.pimfg.com/Product-Detail/YE-15-03-USB");
        urlLinks.add("http://www.pimfg.com/Sub-Category/Stands-and-Mounts#Selfie%20Sticks");
        urlLinks.add("http://www.pimfg.com/Product-Detail/UAE3-16");
        urlLinks.add("http://www.pimfg.com/Product-Detail/FAPP-12-1U-18SC");
        return view;
    }//end onCreateView()


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Spinner spCable=(Spinner)getActivity().findViewById(R.id.sp_cable);
        CustomArrayAdapter adCable=new CustomArrayAdapter(getActivity(),
                R.layout.spinner_item, new DBAdapter(getActivity()).getCableType());
        spCable.setAdapter(adCable);
        spCable.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.strCableType=new DBAdapter(getActivity())
                        .getCableType().get(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });//end Listener


        linearLayout=(LinearLayout)getActivity().findViewById(R.id.linear_layout_cable);
        new DownloadImageTask().execute(
                "http://www.pimfg.com/appbanner/1.jpg",
                "http://www.pimfg.com/appbanner/2.jpg",
                "http://www.pimfg.com/appbanner/3.jpg",
                "http://www.pimfg.com/appbanner/4.jpg"
         );

    }//end onActivityCreated()

    private InputStream openHttpConnection(String urlString) throws IOException{
        InputStream in=null;
        int response=-1;
        URL url=new URL(urlString);
        URLConnection conn=url.openConnection();
        if(!(conn instanceof HttpURLConnection))
            throw new IOException("Not an HTTP connection");
        try {
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();
            response = httpConn.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
            }//end if
        }catch (Exception e){
            Log.d("Networking", e.getLocalizedMessage());
            throw new IOException("Error connecting");
        }//end try catch

        return in;
    }//end openHttpConnection()

    private Bitmap downloadImage(String URL){
        Bitmap bitmap=null;
        InputStream in=null;

        try {
            in=openHttpConnection(URL);
            bitmap= BitmapFactory.decodeStream(in);
            in.close();
        } catch (IOException e) {
            Log.d("NetworkingActivity", e.getLocalizedMessage());
        }//end try catch

        return bitmap;
    }//end downloadImage()

    private class DownloadImageTask extends AsyncTask<String, Bitmap, Long>{

        @Override
        protected Long doInBackground(String... params) {
            long imagesCount=0;
            for(int i=0;i<params.length; i++){
                Bitmap imgDownloaded=downloadImage(params[i]);
                publishProgress(imgDownloaded);
            }//end for

            return imagesCount;
        }//end doInBackground()

        @Override
        protected void onProgressUpdate(Bitmap... values) {
            imageLink++;
            addImageView(linearLayout, values[0], imageLink);
        }//end onProgressUpdate()
    }//end inner class

    private void addImageView(LinearLayout linearLayout, Bitmap value, final int imageInt) {
        ImageView imgView=new ImageView(getActivity());
        imgView.setImageBitmap(value);
        imgView.setLayoutParams(new ViewGroup.LayoutParams(800, 600));
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_VIEW,
                        Uri.parse(urlLinks.get(imageInt-1)));
                startActivity(i);
            }//end onClick()
        });//end listener()
        linearLayout.addView(imgView);
    }//end addImageView()

    private void showLink(int imageLink) {
        Toast.makeText(getActivity(), "image #"+imageLink, Toast.LENGTH_SHORT).show();
    }//end showLink()

    //need to do this for the redraw
    @Override
    public void onStart() {
        super.onStart();
    }//end onStart()
}//end class
