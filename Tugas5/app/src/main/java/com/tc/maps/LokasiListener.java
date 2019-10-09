package com.tc.maps;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class LokasiListener implements LocationListener {
    private TextView txtLat, txtLong;
    private Context context;
    private GoogleMap googleMap;

    public LokasiListener(GoogleMap googleMap,Context context, TextView txtLat, TextView txtLong){
        this.txtLat = txtLat;
        this.txtLong = txtLong;
        this.context = context;
        this.googleMap = googleMap;
    }

    @Override
    public void onLocationChanged(Location location) {
        txtLat.setText(String.valueOf(location.getLatitude()));
        txtLong.setText(String.valueOf(location.getLongitude()));

        LatLng myPos = new LatLng(location.getLatitude(), location.getLongitude());

        Toast.makeText(context, "GPS Capture", Toast.LENGTH_SHORT).show();
        googleMap.addMarker(new MarkerOptions().position(myPos).title("My Position"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myPos,15));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
