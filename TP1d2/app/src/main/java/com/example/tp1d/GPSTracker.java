package com.example.tp1d;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

public class GPSTracker implements LocationListener {
    private final Context context;

    public GPSTracker(Context context) {
        this.context = context;
    }

    public Location getLocation() {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(context, "Permission not granted", Toast.LENGTH_LONG).show();
            return null;
        }

        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        boolean isGpsEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (isGpsEnabled) {
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 10, this);
            return lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }

        Toast.makeText(context, "Please enable GPS", Toast.LENGTH_LONG).show();
        return null;
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }
}