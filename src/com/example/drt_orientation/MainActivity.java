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
		
		textView01.setText("�ֻ�Orientation sensor��ϸ��Ϣ��\n" +
				"�豸���ƣ�  " + sensor.getName() + "\n" +
				"�豸��Ӧ�̣�  " + sensor.getVendor() + "\n" +
				"�豸���ʣ�  " + sensor.getPower()+ "\n");
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
				topBottomString = "�ֻ�ͷ����ײ�û������";
			} else if (event.values[1] > 0) {
				topBottomString = "�ײ���������" + event.values[1] + "��";
			} else if (event.values[1] < 0) {
				topBottomString = "������������" + Math.abs(event.values[1]) + "��";
			}
			
			if (event.values[2] == 0) {
				leftRightString = "�ֻ������Ҳ�û������";
			} else if (event.values[2] > 0) {
				leftRightString = "�Ҳ���������" + event.values[2] + "��";
			} else if (event.values[2] < 0) {
				leftRightString = "�����������" + Math.abs(event.values[2]) + "��";
			}
			
			textView02.setText("�ֻ�������β������ĽǶ�:  " + topBottomString);
			textView03.setText("�ֻ������Ҳ�����ĽǶ�:  " + leftRightString);
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
