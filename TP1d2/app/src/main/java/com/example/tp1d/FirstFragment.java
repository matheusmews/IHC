package com.example.tp1d;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.tp1d.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment  implements SensorEventListener {

    private FragmentFirstBinding binding;

    private SensorManager sensorManager;
    private Sensor light;
    TextView lightValue, tempValue, latValue, lonValue;


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @SuppressLint("ResourceType")
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sensorManager = (SensorManager) requireActivity().getSystemService(Context.SENSOR_SERVICE);
        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        if(light != null)
        {
            sensorManager.registerListener(FirstFragment.this, light,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }else
        {
            binding.light.setText("Light sensor not supported");
        }

        GPSTracker gpsTracker = new GPSTracker(getContext());
        Location l = gpsTracker.getLocation();
        if (l != null) {
            binding.lat.setText(String.format(Double.toString(l.getLatitude())));
            binding.lon.setText(String.format(Double.toString(l.getLongitude())));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT)
        {
            binding.light.setText(String.format(Float.toString(sensorEvent.values[0])));
        }
        else if (sensorEvent.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE)
        {
            binding.temp.setText(String.format(Float.toString(sensorEvent.values[0])));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}