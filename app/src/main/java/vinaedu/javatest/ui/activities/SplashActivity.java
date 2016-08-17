package vinaedu.javatest.ui.activities;

import android.content.Intent;
import android.os.Handler;

import vinaedu.javatest.R;
import vinaedu.javatest.ui.intalizes.BaseActivity;

/**
 * Created by bscenter on 10/08/2016
 */
public class SplashActivity extends BaseActivity {

    private static final long DELAY_TIME = 3000; // = 3s

    @Override
    protected int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void main() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                openActivity(new Intent(SplashActivity.this, HomeActivity.class), OPEN);
                finish();
            }
        }, DELAY_TIME);
    }
}
