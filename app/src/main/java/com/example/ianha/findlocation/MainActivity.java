package com.example.ianha.findlocation;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends AppCompatActivity {
    private Button myButton;
    private FusedLocationProviderClient client;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermission();

        client = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(MainActivity.this, ACCESS_FINE_LOCATION) !=PackageManager.PERMISSION_GRANTED) {

            return;
        }
        client.getLastLocation().addOnSuccessListener(MainActivity.this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                if(location!=null){
                    TextView myLongitude = findViewById(R.id.longitude);
                    TextView myLatitude = findViewById(R.id.latitude);
                    TextView myState = findViewById(R.id.state);
                    String longitude = location.toString().substring(15, 24);
                    String latitude = location.toString().substring(25, 35);
                    String state = "Not North Carolina";
                    if(Double.parseDouble(longitude) > 33.4452 && Double.parseDouble(longitude) < 40.16 && Double.parseDouble(latitude) < 60.984 && Double.parseDouble(latitude) > 82.433){
                        state = "North Carolina";
                    }
                    else if(Double.parseDouble(longitude) > 30.2722 && Double.parseDouble(longitude) < 42.16 && Double.parseDouble(latitude) < -99.6 && Double.parseDouble(latitude) > -130.7) {
                        state = "California";
                    }
                    myLongitude.setText("Longitude: " + latitude);
                    myLatitude.setText("Latitude: " + longitude);
                    myState.setText(state);
                }

            }
        });

        myButton = (Button) findViewById(R.id.continueButton);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNext();
            }
        });


    }

    public void openNext(){
        Intent intent = new Intent(this, papersActivity.class);
        startActivity(intent);
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 1);
    }
}
