package tdc.edu.vn;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private LinearLayout linearLayout;
    private ImageView img_lamp;
    private TextView tv_title;
    private TextView tv_message;
    private TextView tv_current;

    private SensorManager mSensorManager;
    private Sensor mSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        changeStatusBarColor();
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        img_lamp = (ImageView) findViewById(R.id.img_lamp);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_current = (TextView) findViewById(R.id.tv_current);


        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        if (mSensor == null) {
            Toast.makeText(MainActivity.this, "Your device not supported Light Sensor", Toast.LENGTH_LONG).show();
        } else {
            mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();

        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT) {
            float mCurrentLight = sensorEvent.values[0];
            tv_current.setText(mCurrentLight +" %");
            if (mCurrentLight <50) {
                linearLayout.setBackgroundColor(getColor(R.color.colorDen));
                img_lamp.setImageDrawable(getDrawable(R.drawable.img_toi));
                tv_title.setText("màn hình tối");
                tv_title.setTextColor(getColor(R.color.colorTrang));
                tv_current.setTextColor(getColor(R.color.colorTrang));


            } else {
                linearLayout.setBackgroundColor(getColor(R.color.colorTrang));

                img_lamp.setImageDrawable(getDrawable(R.drawable.img_sang));
                tv_title.setText("màn hình sáng");
                tv_title.setTextColor(getColor(R.color.colorDen));
                tv_current.setTextColor(getColor(R.color.colorDen));


            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
}
