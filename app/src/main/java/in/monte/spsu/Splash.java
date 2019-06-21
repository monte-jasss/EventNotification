package in.monte.spsu;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import in.monte.spsu.Activity.MainActivity;
import in.monte.spsu.Notice.Notice;
import in.monte.spsu.TimeTable.TimeTable;

public class Splash extends AppCompatActivity {
    Animation splash, splash_a;
    TextView tv;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        splash = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.btm_scale);
        splash_a = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.top_rot);
        tv = findViewById(R.id.textView);
        iv = findViewById(R.id.imageView);

        tv.startAnimation(splash);
        iv.startAnimation(splash_a);

        splash.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(Splash.this,MainActivity.class));
                        finish();
                    }
                },2000);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        splash_a.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
