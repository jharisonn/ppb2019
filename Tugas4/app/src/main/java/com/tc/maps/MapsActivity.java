package com.tc.maps;

import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private EditText txtLat, txtLong, txtZoom, txtCari;
    private Button btnGo,btnCari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        txtLat = (EditText) findViewById(R.id.txtLat);
        txtLong = (EditText) findViewById(R.id.txtLong);
        txtZoom = (EditText) findViewById(R.id.txtZoom);

        txtCari  = (EditText) findViewById(R.id.txtCari);

        btnGo = (Button) findViewById(R.id.btnGo);
        btnCari = (Button) findViewById(R.id.btnCari);

        btnCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Geocoder g = new Geocoder(getBaseContext());
                List<Address> daftar = null;
                try {
                    daftar = g.getFromLocationName(txtCari.getText().toString(),1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Address alamat = daftar.get(0);

                String nemuAlamat =  alamat.getAddressLine(0);
                Double lintang = alamat.getLatitude();
                Double bujur = alamat.getLongitude();

                txtLat.setText(lintang.toString());
                txtLong.setText(bujur.toString());

                goToMap(lintang,bujur,Float.parseFloat(txtZoom.getText().toString()));

                Toast.makeText(MapsActivity.this, "Ketemu : "+nemuAlamat, Toast.LENGTH_SHORT).show();

            }
        });

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(v);

                String lat = txtLat.getText().toString();
                String lon = txtLong.getText().toString();
                String zoom = txtZoom.getText().toString();

                double dLat=0,dLon=0;
                float dZoom=0;
                try {
                    dLat = Double.parseDouble(lat);
                    dLon = Double.parseDouble(lon);
                    dZoom = Float.parseFloat(zoom);
                }catch (NumberFormatException e){
                    Toast.makeText(MapsActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    return;
                }


                goToMap(dLat,dLon,dZoom);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.normal : mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); break;
            case R.id.terrain : mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); break;
            case R.id.satelite : mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); break;
            case R.id.hybrid : mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); break;
            case R.id.none : mMap.setMapType(GoogleMap.MAP_TYPE_NONE); break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void hideKeyboard(View v){
        InputMethodManager a = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        a.hideSoftInputFromWindow(v.getWindowToken(),0);
    }

    private void goToMap(double dLat, double dLon, float dZoom){
        LatLng newLoc = new LatLng(dLat,dLon);
        mMap.addMarker(new MarkerOptions().position(newLoc).title("New Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newLoc,dZoom));
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng its = new LatLng(-7.28, 112.79);
        mMap.addMarker(new MarkerOptions().position(its).title("Marker in ITS"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(its));
    }
}
