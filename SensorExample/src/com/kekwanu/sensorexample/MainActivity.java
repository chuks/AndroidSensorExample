package com.kekwanu.sensorexample;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends Activity implements SensorEventListener {
		private static String TAG = MainActivity.class.getCanonicalName();
	 	private SensorManager mgr;
	    private Sensor light;
	    private TextView valueField;
	    private TextView accuracyField;

	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);

	       mgr =  (SensorManager)this.getSystemService(SENSOR_SERVICE);

	       List<Sensor> systemSensors = mgr.getSensorList(Sensor.TYPE_ALL);

	        for (Sensor s: systemSensors){
	            Log.i(TAG, "Sensor name is: " + s.getName());
	            Log.i(TAG, "Sensor type is: " + s.getType());
	        }

	        light  = mgr.getDefaultSensor(Sensor.TYPE_LIGHT);
	        
	        valueField = (TextView) findViewById(R.id.value);
	        accuracyField = (TextView) findViewById(R.id.accuracy);
	    }

	    @Override
	    protected void onResume(){
	        super.onResume();

	        mgr.registerListener(this,light,SensorManager.SENSOR_DELAY_NORMAL);
	    }

	    @Override
	    protected void onPause(){
	        super.onPause();

	        mgr.unregisterListener(this,light);
	    }

	    @Override
	    public void onSensorChanged(SensorEvent event) {

	        Log.i(TAG, event.sensor.getName()+" value: "+event.values[0]);
	        
	        valueField.setText(event.sensor.getName()+" value: "+event.values[0]);
	    }

	    @Override
	    public void onAccuracyChanged(Sensor sensor, int accuracy) {
	    	Log.i(TAG,"Accuracy for Sensor "+sensor.getName()+" is: "+accuracy);
	    	
	    	accuracyField.setText(sensor.getName()+" accuracy: "+accuracy);
	    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
