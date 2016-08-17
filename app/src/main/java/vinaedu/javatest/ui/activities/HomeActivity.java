package vinaedu.javatest.ui.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import vinaedu.javatest.R;
import vinaedu.javatest.common.interfaces.LevelListening;
import vinaedu.javatest.common.objects.Level;
import vinaedu.javatest.ui.fragments.GuideFragment;
import vinaedu.javatest.ui.fragments.PlayFragment;
import vinaedu.javatest.ui.fragments.ScoreFragment;
import vinaedu.javatest.ui.intalizes.BaseActivity;

public class HomeActivity extends BaseActivity implements View.OnClickListener, LevelListening {

    private static final String TAG = HomeActivity.class.getSimpleName();

    private Button mBtnPlay;
    private Button mBtnScore;
    private Button mBtnGuide;
    private Button mBtnExit;
    private FrameLayout mFrHome;

    @Override
    protected int getLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void initViews() {
        mBtnPlay = (Button) findViewById(R.id.btnPlay);
        mBtnScore = (Button) findViewById(R.id.btnScore);
        mBtnGuide = (Button) findViewById(R.id.btnGuide);
        mBtnExit = (Button) findViewById(R.id.btnExit);
        mFrHome = (FrameLayout) findViewById(R.id.frHome);

        setVisibleAllButtons(false);
        mFrHome.setVisibility(View.GONE);

        mBtnPlay.setOnClickListener(this);
        mBtnScore.setOnClickListener(this);
        mBtnGuide.setOnClickListener(this);
        mBtnExit.setOnClickListener(this);
    }

    @Override
    protected void main() {

        showAnimationButtons();
        showFrameHome();
        replayFr(new PlayFragment());
    }

    private void showAnimationButtons() {
        List<Button> buttons = new ArrayList<>();
        buttons.add(mBtnPlay);
        buttons.add(mBtnScore);
        buttons.add(mBtnGuide);
        buttons.add(mBtnExit);

        int timeBettewn = 500;
        int timeStart = 50;

        for (Button button : buttons) {
            TranslateAnimation animShowInLeft = (TranslateAnimation) AnimationUtils.loadAnimation(this, R.anim.anim_left_in);
            animShowInLeft.setStartOffset(timeStart);
            button.setVisibility(View.VISIBLE);
            button.startAnimation(animShowInLeft);
            timeStart += timeBettewn;
        }
    }

    private void showFrameHome() {
        int timeStart = 50;
        TranslateAnimation animShowInLeft = (TranslateAnimation) AnimationUtils.loadAnimation(this, R.anim.anim_right_in);
        animShowInLeft.setStartOffset(timeStart);
        mFrHome.setVisibility(View.VISIBLE);
        mFrHome.startAnimation(animShowInLeft);
    }

    private void setVisibleAllButtons(boolean isVisible) {
        mBtnPlay.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        mBtnScore.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        mBtnGuide.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        mBtnExit.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onClick(View view) {
        if (view == mBtnPlay) {
            replayFr(new PlayFragment());
        } else if (view == mBtnScore) {
            replayFr(new ScoreFragment());
        } else if (view == mBtnGuide) {
            replayFr(new GuideFragment());
        } else if (view == mBtnExit) {
            close();
        }
    }

    private void replayFr(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(mFrHome.getId(), fragment, fragment.toString());
        fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.commit();
    }

    @Override
    public void onChoiceLevel(Level level) {
        Log.d(TAG, "choice level= " + level.getValue());
        Intent intent = new Intent(this, PlayActivity.class);
        intent.putExtra("LEVEL", level.getValue());
        openActivity(intent, OPEN);
    }
}
