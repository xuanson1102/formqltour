package tdc.edu.vn.formqltour.GiaoDien;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tomer.fadingtextview.FadingTextView;

import tdc.edu.vn.formqltour.R;
public class MainActivity2 extends AppCompatActivity {
    ProgressBar progressBar;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ImageView cat = findViewById(R.id.imgView);

        FadingTextView fadingTextView = findViewById(R.id.textt);
        fadingTextView.setTimeout(FadingTextView.MILLISECONDS);
        final AnimationDrawable runningCat = (AnimationDrawable) cat.getDrawable();
        runningCat.start();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        progressBar = findViewById(R.id.progress_bar);
        textView = findViewById(R.id.text_view);

        progressBar.setMax(100);
        progressBar.setScaleY(3f);

        progressAnimation();
    }

    public void progressAnimation(){
        ProgressBarAnimation anim = new ProgressBarAnimation(this,progressBar,textView,0f,100f);
        anim.setDuration(5000);
        progressBar.setAnimation(anim);
    }
}