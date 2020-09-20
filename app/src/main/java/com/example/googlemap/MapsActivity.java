package com.example.googlemap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.Tile;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.TileProvider;
import com.google.android.gms.maps.model.UrlTileProvider;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    GoogleApiClient googleApiClient;
    LocationRequest locationRequest;
    Marker marker;
    Button search_btn;
    EditText search;
    ArrayList<LatLng> points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        search_btn = findViewById(R.id.search_btn);
        search = findViewById(R.id.search);

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

        //add marker
        mMap.addMarker(new MarkerOptions().position(bhopal).title("Marker in Sydney"));
        mMap.addMarker(new MarkerOptions().position(jaipur).title("Marker in Sydney"));
        mMap.addMarker(new MarkerOptions().position(Andaman).title("Marker in Sydney"));
        mMap.addMarker(new MarkerOptions().position(Andhra).title("Marker in Sydney"));
        mMap.addMarker(new MarkerOptions().position(Arunachal).title("Marker in Sydney"));
        mMap.addMarker(new MarkerOptions().position(Assam).title("Marker in Sydney"));
        mMap.addMarker(new MarkerOptions().position(Bihar).title("Marker in Sydney"));
        mMap.addMarker(new MarkerOptions().position(Delhi).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bhopal));

        //Polylines are useful for marking paths and routes on the map.
         mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(23.2599333, 77.412615),
                        new LatLng(25.78541445, 87.4799727),
                        new LatLng(28.6699929, 77.23000403),
                        new LatLng(26.9124336, 75.7872709),
                        new LatLng(14.7504291, 78.57002559))
                .width(5)
                .color(Color.RED));


         //add circle in perticular location
       mMap.addCircle(new CircleOptions()
                .center(new LatLng(23.2599333, 77.412615))
                .radius(10000)
                .strokeColor(Color.GREEN)
                .fillColor(Color.BLUE));

       //adpolygone for cover area
       mMap.addPolygon(new PolygonOptions()
       .add(new LatLng(23.2599333, 77.412615),
               new LatLng(25.78541445, 87.4799727),
               new LatLng(28.6699929, 77.23000403),
               new LatLng(26.9124336, 75.7872709),
               new LatLng(14.7504291, 78.57002559))
       .fillColor(Color.GREEN)
       .strokeColor(Color.RED));

      //Ground overlays are useful when you wish to fix a single image at one area on the map
        BitmapDescriptor image = BitmapDescriptorFactory.fromResource(R.drawable.mark);
        GroundOverlayOptions groundOverlay = new GroundOverlayOptions()
                .image(image)
                .position(bhopal, 8600f, 6500f);
        mMap.addGroundOverlay(groundOverlay);

    }


}