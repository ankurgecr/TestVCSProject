package info.ankurpandya.testvcsproject.himanshu.Location_himanshu;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

import info.ankurpandya.testvcsproject.R;

public class LocationMainActivity extends AppCompatActivity implements LocationListener {

    private TextView locationText;
    private Button btngetLocation;
    private LocationManager locationManager;
    private static final int REQUEST_PERMISSION_FINE_LOCATION_RESULT = 0;

    @Override
    public void onLocationChanged(Location location) {
        locationText.setText("Latitude :" + location.getLatitude() + "\n Longitude :" + location.getLongitude());
        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> address = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            locationText.setText(locationText.getText() + "\n " + address.get(0).getAddressLine(0));
            //+", " + address.get(0).getAddressLine(1) + ", " +address.get(0).getAddressLine(2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(this, "Please enable Internet And GPS ", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main_h_location);

        locationText = (TextView) findViewById(R.id.txt_Location);
        btngetLocation = (Button) findViewById(R.id.btn_getLocation);

        btngetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) ==
                            PackageManager.PERMISSION_GRANTED) {
                        getLocation();
                    } else {
                        if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                            Toast.makeText(LocationMainActivity.this, "Application Require to Access Location", Toast.LENGTH_SHORT).show();
                        }
                        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_PERMISSION_FINE_LOCATION_RESULT);
                    }
                } else {
                    getLocation();
                }

            }
        });
    }

    void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(locationManager.NETWORK_PROVIDER, 500, 1, this);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_FINE_LOCATION_RESULT) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Please provide permission", Toast.LENGTH_SHORT).show();
            } else {
                getLocation();
            }
        }

    }
}


