package r3software.org.lunarinvasion.engine.impl;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class CompassHandler implements SensorEventListener {
	float yaw;
	float pitch;
	float roll;
	
	public CompassHandler(Context context) {
		SensorManager manager = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
		if(manager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0) {
			Sensor accelerometer = manager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
			manager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
		}
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		yaw = event.values[0];
		pitch = event.values[1];
		roll = event.values[2];

	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		//nuthin
	}
	
	
	public float getYaw() {
		return yaw;
	}
	
	public float getPitch() {
		return pitch;
	}
	
	public float getRoll() {
		return roll;
	}


}
