package jp.adlibjapan.android;

import java.util.List;

import jp.adlibjapan.android.magicapp1.R;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class magicapp1Activity extends Activity implements SensorEventListener {
	private static final String TAG = "Kinsetu";
	private SensorManager sensorManager;
	private int mShow = 1;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Window window = getWindow();

		// メニューバーを非表示にする
		window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// タイトルバーを非表示
		window.requestFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.main);

		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		List<Sensor> sensors = sensorManager
				.getSensorList(Sensor.TYPE_PROXIMITY);
		Sensor sensor = sensors.get(0);
		sensorManager.registerListener(this, sensor,
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO 自動生成されたメソッド・スタブ
	}

	public void onSensorChanged(SensorEvent event) {
		float[] values = event.values;
//		for (int i = 0; i < values.length; i++) {
//			Log.i(TAG, i + " " + values[i]);
//		}
		if(values[0]>0.1F){
			// 外れた
			Log.i(TAG, "out");

		} else {
			Log.i(TAG, "cover");
			mShow = -mShow ;
			ImageView iv = (ImageView)findViewById(R.id.imageView1);
			switch (mShow) {
			case 1:
				iv.setImageResource(R.drawable.desktop);
				break;
			case -1:
				iv.setImageResource(R.drawable.home);
				break;

			default:
				break;
			}
		}
	}
}