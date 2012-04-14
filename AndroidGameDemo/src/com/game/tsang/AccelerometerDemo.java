package com.game.tsang;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

public class AccelerometerDemo extends Activity implements SensorEventListener {

    TextView textView;
    StringBuilder builder = new StringBuilder();
    private int screenRotation;
    
    @Override
    public void onResume() {
        WindowManager windowMgr =
                (WindowManager)getSystemService(Activity.WINDOW_SERVICE);
        // getOrientation() is deprecated in Android 8 but is the same as
        // getRotation() which is the rotation from the natural orientation of the device
        screenRotation = windowMgr.getDefaultDisplay().getOrientation();
        super.onResume();
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView = new TextView(this);
        setContentView(textView);
        
        SensorManager manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        
        final List<Sensor> sensorList = manager.getSensorList(Sensor.TYPE_ORIENTATION);
        if (sensorList.size() == 0){
            textView.setText("No accelerometer installed.");
        } else {
            Sensor accelerometer = sensorList.get(0);
            if (!manager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME)) {
                textView.setText("Couldn't register sensor listener.");
            }
        }
    }
    
    static final int ACCELEROMETER_AXIS_SWAP[][] = {
        {1, -1, 0, 1}, // ROTATION_0
        {-1, -1, 1, 0}, // ROTATION_90
        {-1, 1, 0, 1}, // ROTATION_180
        {1, 1, 1, 0}}; // ROTATION_270
    
    @Override
    public void onSensorChanged(SensorEvent event) {
        
        final int[] as = ACCELEROMETER_AXIS_SWAP[screenRotation];
        float screenX = (float)as[0] * event.values[as[2]];
        float screenY = (float)as[1] * event.values[as[3]];
        float screenZ = event.values[2];
        // use screenX, screenY and screenZ as your accelerometer values now!
        
        builder.setLength(0);
        builder.append("x: ");
        builder.append(screenX);
        builder.append(", y: ");
        builder.append(screenY);
        builder.append(", z: ");
        builder.append(screenZ);
        textView.setText(builder.toString());
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Nothing to do here
    }

}
