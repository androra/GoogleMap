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
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mMap;
    GoogleApiClient googleApiClient;
    LocationRequest locationRequest;
    Marker marker;
    TextView adress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        adress = findViewById(R.id.adress);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        /*LatLng Andaman = new LatLng(11.66702557, 92.73598262);
        LatLng Andhra  = new LatLng(14.7504291,78.57002559);
        LatLng Arunachal = new LatLng(27.10039878	,93.61660071);
        LatLng Assam  = new LatLng(26.7499809,	94.21666744);
        LatLng Bihar = new LatLng(25.78541445,	87.4799727);
        LatLng Delhi  = new LatLng(	28.6699929,	77.23000403);
        LatLng bhopal = new LatLng(23.2599333, 77.412615);
        LatLng jaipur = new LatLng(26.9124336, 75.7872709);*/

        //Types of Map
        //mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        //mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        //mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        //mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

        //set Info Window
       /* Marker melbourne = mMap.addMarker(new MarkerOptions().position(bhopal).title("Bhopal").snippet("Population: 4,137,400"));
        melbourne.showInfoWindow();*/

        //Icon Size
       /* int height = 100;
        int width = 100;
        BitmapDrawable bitmapdraw = (BitmapDrawable)getResources().getDrawable(R.drawable.mark);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);*/

        //set Icon
       /* mMap.addMarker(new MarkerOptions().position(bhopal)
                .title("Marker in Sydney")).
                setIcon(BitmapDescriptorFactory.fromBitmap(smallMarker));*/

        //default marker
       /* mMap.addMarker(new MarkerOptions().position(jaipur).title("Marker in Sydney"));
        mMap.addMarker(new MarkerOptions().position(Andaman).title("Marker in Sydney"));
        mMap.addMarker(new MarkerOptions().position(Andhra).title("Marker in Sydney"));
        mMap.addMarker(new MarkerOptions().position(Arunachal).title("Marker in Sydney"));
        mMap.addMarker(new MarkerOptions().position(Assam).title("Marker in Sydney"));
        mMap.addMarker(new MarkerOptions().position(Bihar).title("Marker in Sydney"));
        mMap.addMarker(new MarkerOptions().position(Delhi).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bhopal));*/

        if (ContextCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MapsActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        } else if (ContextCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MapsActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
        } else {
            mMap.setMyLocationEnabled(true);
            googleApiClient = new GoogleApiClient.Builder(MapsActivity.this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
            googleApiClient.connect();
        }

    }

    //current location in map
    @Override
    public void onConnected(@Nullable Bundle bundle) {

        locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());

                MarkerOptions markerOptions=new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title("bhopal");
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                marker=mMap.addMarker(markerOptions);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(11));

                List<Address> addresses;
                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

                try {
                    addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                    String address1 = addresses.get(0).getAddressLine(0);
                    String city = addresses.get(0).getLocality();
                    String state = addresses.get(0).getAdminArea();
                    String country = addresses.get(0).getCountryName();
                    String addressAll =address1 + "" + city + "" + state +""+ country;
                    Log.i("dhhdhdhd", "onSuccess: " + address1 + "" + city + "" + state +""+ country);

                    adress.setText(addressAll);

                    //Toast.makeText(MapsActivity.this, addressAll, Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}