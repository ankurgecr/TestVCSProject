package info.ankurpandya.testvcsproject.govind;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import info.ankurpandya.testvcsproject.R;

public class LocationActivity extends AppCompatActivity implements LocationListener {
    Button btnCurrentLocation;
    TextView tvLocation;
    ProgressDialog progressDialog;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_layout);
        btnCurrentLocation = findViewById(R.id.btn_location);
        tvLocation = findViewById(R.id.tv_location);


        btnCurrentLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    if (ActivityCompat.shouldShowRequestPermissionRationale(LocationActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)) {

                        AlertDialog.Builder ad = new AlertDialog.Builder(LocationActivity.this);
                        ad.setTitle("Permission");
                        ad.setMessage("Permission needed to fetch your current location");
                        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(LocationActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
                                getLocation();
                            }
                        });
                        ad.setNegativeButton("CANCLE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        ad.show();
                    } else {
                        ActivityCompat.requestPermissions(LocationActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
                        getLocation();
                    }
                } else {
                    getLocation();
                    progressDialog = new ProgressDialog(LocationActivity.this);
                    progressDialog.setMessage("Fetching...");
                    progressDialog.show();

                }
            }
        });
    }

    @SuppressLint("MissingPermission")
    void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 2000, 5, this);

        } catch (Exception e) {

        }
    }


    @Override
    public void onLocationChanged(Location location) {
        try {
            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(this, Locale.getDefault());

            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

            String address = addresses.get(0).getAddressLine(0);
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName();
            progressDialog.dismiss();
            tvLocation.setText(address + "\n" + city + "\n" + state + "\n" + country + "\n" + postalCode + "\n" + knownName);
        } catch (Exception e) {
            Log.e("Error", e.toString());
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

    }
}

