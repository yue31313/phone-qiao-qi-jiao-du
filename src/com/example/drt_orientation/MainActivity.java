package com.example.drt_orientation;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;

import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView textView01 = null;
	private TextView textView02 = null;
	private TextView textView03 = null;
	private SensorManager sensorManager = null;
	private Sensor sensor = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		textView01 = (TextView) findViewById(R.id.TextView01);
		textView02 = (TextView) findViewById(R.id.TextView02);
		textView03 = (TextView) findViewById(R.id.TextView03);
		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
		
		textView01.setText("手机Orientation sensor详细信息：\n" +
				"设备名称：  " + sensor.getName() + "\n" +
				"设备供应商：  " + sensor.getVendor() + "\n" +
				"设备功率：  " + sensor.getPower()+ "\n");
	}
	
	private SensorEventListener listener = new SensorEventListener() {

		@Override
		public void onAccuracyChanged(Sensor arg0, int arg1) {

		}

		
		@Override
		public void onSensorChanged(SensorEvent event) {
			String topBottomString = "";
			String leftRightString = "";
			
			if (event.values[1] == 0) {
				topBottomString = "手机头部或底部没有翘起";
			} else if (event.values[1] > 0) {
				topBottomString = "底部向上翘起" + event.values[1] + "度";
			} else if (event.values[1] < 0) {
				topBottomString = "顶部向上翘起" + Math.abs(event.values[1]) + "度";
			}
			
			if (event.values[2] == 0) {
				leftRightString = "手机左侧或右侧没有翘起";
			} else if (event.values[2] > 0) {
				leftRightString = "右侧向上翘起" + event.values[2] + "度";
			} else if (event.values[2] < 0) {
				leftRightString = "左侧向上翘起" + Math.abs(event.values[2]) + "度";
			}
			
			textView02.setText("手机顶部或尾部翘起的角度:  " + topBottomString);
			textView03.setText("手机左侧或右侧翘起的角度:  " + leftRightString);
		}
	};
	
	@Override
	protected void onResume() {
		super.onResume();
		sensorManager.registerListener(listener, sensor,
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onStop() {
		super.onStop();
		sensorManager.unregisterListener(listener);
	}

	
}
