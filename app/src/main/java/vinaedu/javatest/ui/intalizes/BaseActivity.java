package vinaedu.javatest.ui.intalizes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import vinaedu.javatest.R;

/**
 * Created by bscenter on 10/08/2016
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected static final int OPEN = 0;
    protected static final int CLOSE = 1;

    protected abstract int getLayout();

    protected abstract void initViews();

    protected abstract void main();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initViews();
        main();
    }

    /**
     * @param intent
     * @param type   = OPEN or CLOSE
     */
    protected void openActivity(Intent intent, int type) {
        startActivity(intent);
        if (type == OPEN) {
            overridePendingTransition(R.anim.anim_right_in, R.anim.anim_left_out);
        } else {
            overridePendingTransition(R.anim.anim_left_in, R.anim.anim_right_out);
        }
    }

    protected void close() {
        finish();
        overridePendingTransition(R.anim.anim_left_in, R.anim.anim_right_out);
    }


}
