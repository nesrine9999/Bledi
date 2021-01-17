package com.example.bledi;


import android.Manifest;
import android.app.FragmentManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class LastActivity extends AppCompatActivity implements LocationListener {
    private static final int PERMS_CALL_ID = 1234;

    private LocationManager lm;
    private GoogleMap googleMap;

    private MapFragment mapFragment;


    Button button;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last);
        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        button = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.editText);
        Spinner spin = (Spinner) findViewById(R.id.spinner);

        final String[] anomalie = {"Depot Sauvage", "Eclairage Public", "Tags", "Trous sur Voirie", "voiture sur Territoire"};
        int images[] = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e};

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(LastActivity.this, "You Select Position: " + position + " " + anomalie[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), images, anomalie);


        spin.setAdapter(customAdapter);
        FragmentManager FragmentManager = getFragmentManager();
        mapFragment = (MapFragment) FragmentManager.findFragmentById(R.id.map);

    }


    @Override
    //@SuppressWarnings("MissingPermission")
    public void onResume() {
        super.onResume();
       checkPermissions();



    }

    private void checkPermissions() {

        lm = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION


            }, PERMS_CALL_ID);

            return;
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, this);
    }
            if(lm.isProviderEnabled(LocationManager.PASSIVE_PROVIDER))

    {
        lm.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 10000, 0, this);
    }
            if(lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER))

    {
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 0, this);

    }
loadMap();
}

@Override
public void onRequestPermissionsResult(int requestCode , @NonNull String[] permissions, @NonNull int[] grantResults)
{
    super.onRequestPermissionsResult(requestCode, permissions , grantResults );
    if (requestCode == PERMS_CALL_ID)
    {
    checkPermissions();
    }
}

    @Override
    public void onPause() {
        super.onPause();
if (lm != null){
        lm.removeUpdates(this);
    }}

    @SuppressWarnings("MissingPermission")
    private void loadMap() {
        mapFragment.getMapAsync(new OnMapReadyCallback() {
                                    @Override
                                    public void onMapReady(GoogleMap googleMap) {

                                        LastActivity.this.googleMap =googleMap ;

                                        googleMap.moveCamera(CameraUpdateFactory.zoomBy(15) );
                                        googleMap.setMyLocationEnabled(true);
                                        googleMap.addMarker(new MarkerOptions().position(new LatLng(43.799345 , 6.7254267))


                                        .title("Infini Software"));

                                    }
                                }

        );
    }



































    @Override
    public void onLocationChanged(final Location location) {
         double latitude = location.getLatitude();
         double longitude= location.getLongitude();

        Toast.makeText(this, "Location:" + latitude + "/"+ longitude,  Toast.LENGTH_LONG).show();
        if (googleMap != null)
        {
            LatLng googleLocation = new LatLng( latitude , longitude);
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(googleLocation));

    }}

    @Override
    public void onStatusChanged(final String provider, final int status, final Bundle extras) {

    }

    @Override
    public void onProviderEnabled( final String provider) {
        if("gps".equals(provider)) {

        }

    }

    @Override
    public void onProviderDisabled(final String provider) {
        if("gps".equals(provider)) {

        }

    }
}




































