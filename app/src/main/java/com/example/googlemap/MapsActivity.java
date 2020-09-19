package com.example.googlemap;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng Andaman = new LatLng(11.66702557, 92.73598262);
        LatLng Andhra  = new LatLng(14.7504291,78.57002559);
        LatLng Arunachal = new LatLng(27.10039878	,93.61660071);
        LatLng Assam  = new LatLng(26.7499809,	94.21666744);
        LatLng Bihar = new LatLng(25.78541445,	87.4799727);
        LatLng Delhi  = new LatLng(	28.6699929,	77.23000403);
        LatLng bhopal = new LatLng(23.2599333, 77.412615);
        LatLng jaipur = new LatLng(26.9124336, 75.7872709);

        //Types of Map
        //mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        //mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        //mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        //mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

        //set Info Window
        Marker melbourne = mMap.addMarker(new MarkerOptions().position(bhopal).title("Bhopal").snippet("Population: 4,137,400"));
        melbourne.showInfoWindow();

        //Icon Size
        int height = 100;
        int width = 100;
        BitmapDrawable bitmapdraw = (BitmapDrawable)getResources().getDrawable(R.drawable.mark);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

        //set Icon
        mMap.addMarker(new MarkerOptions().position(bhopal)
                .title("Marker in Sydney")).
                setIcon(BitmapDescriptorFactory.fromBitmap(smallMarker));

        //default marker
        mMap.addMarker(new MarkerOptions().position(jaipur).title("Marker in Sydney"));
        mMap.addMarker(new MarkerOptions().position(Andaman).title("Marker in Sydney"));
        mMap.addMarker(new MarkerOptions().position(Andhra).title("Marker in Sydney"));
        mMap.addMarker(new MarkerOptions().position(Arunachal).title("Marker in Sydney"));
        mMap.addMarker(new MarkerOptions().position(Assam).title("Marker in Sydney"));
        mMap.addMarker(new MarkerOptions().position(Bihar).title("Marker in Sydney"));
        mMap.addMarker(new MarkerOptions().position(Delhi).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bhopal));
    }
}