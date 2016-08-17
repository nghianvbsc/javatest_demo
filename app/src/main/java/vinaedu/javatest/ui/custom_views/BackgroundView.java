package vinaedu.javatest.ui.custom_views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import vinaedu.javatest.R;

/**
 * Created by bscenter on 11/08/2016
 */
public class BackgroundView extends RelativeLayout implements View.OnClickListener {

    private View mLnStartGame;
    private View mLnDetailPlay;
    private Button mBtnContinueGame;
    private Button mBtnExitGame;
    private Button mBtnStartGame;
    private TextView mTvDetail;
    public static final int ONSTART = 0;
    public static final int ONPAUSE = 1;
    public static final int ONFINISH = 2;
    public static final int ONPENDDING = 3;
    private int type;

    private BackgroundListening mBackgroundListening;

    public BackgroundView(Context context) {
        this(context, null);
    }

    public BackgroundView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BackgroundView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.custom_background_view, null);
        initViews(view);
        addView(view);
    }

    private void initViews(View view) {
        mLnStartGame = view.findViewById(R.id.lnStartGame);
        mLnDetailPlay = view.findViewById(R.id.lnDetailPlay);
        mBtnStartGame = (Button) view.findViewById(R.id.btnStartGame);
        mBtnContinueGame = (Button) view.findViewById(R.id.btnContinueGame);
        mBtnExitGame = (Button) view.findViewById(R.id.btnExitGame);
        mTvDetail = (TextView) view.findViewById(R.id.tvDetail);

        mBtnStartGame.setOnClickListener(this);
        mBtnContinueGame.setOnClickListener(this);
        mBtnExitGame.setOnClickListener(this);
    }

    public void setListening(BackgroundListening backgroundListening) {
        mBackgroundListening = backgroundListening;
    }

    public void onStartGame() {
        type = ONSTART;
        mLnStartGame.setVisibility(View.VISIBLE);
        mLnDetailPlay.setVisibility(View.GONE);
    }

    public void onDetailGame(int score, int question, int sumQuestion) {
        type = ONPENDDING;
        mLnStartGame.setVisibility(View.GONE);
        mLnDetailPlay.setVisibility(View.VISIBLE);
        mBtnContinueGame.setVisibility(View.GONE);
        mBtnExitGame.setVisibility(View.GONE);
        mTvDetail.setText("Điểm: " + score + "\nCâu: " + question + "/" + sumQuestion);
    }

    public void onPauseGame(int score, int question, int sumQuestion) {
        type = ONPAUSE;
        mLnStartGame.setVisibility(View.GONE);
        mLnDetailPlay.setVisibility(View.VISIBLE);
        mBtnContinueGame.setVisibility(View.VISIBLE);
        mBtnExitGame.setVisibility(View.VISIBLE);
        mTvDetail.setText("Điểm: " + score + "\nCâu: " + question + "/" + sumQuestion);
    }

    public void onFinishGame(int score, int question, int sumQuestion) {
        type = ONFINISH;
        mLnStartGame.setVisibility(View.GONE);
        mLnDetailPlay.setVisibility(View.VISIBLE);
        mBtnContinueGame.setVisibility(View.GONE);
        mBtnExitGame.setVisibility(View.VISIBLE);
        mTvDetail.setText("Điểm: " + score + "\nCâu: " + question + "/" + sumQuestion);
    }

    public int getType() {
        return type;
    }

    @Override
    public void onClick(View view) {
        if (view == mBtnStartGame) {
            mBackgroundListening.onStartGame();
        } else if (view == mBtnContinueGame) {
            type = ONPENDDING;
            mBackgroundListening.onContinueGame();
        } else {
            mBackgroundListening.onExitGame();
        }
    }

    public interface BackgroundListening {
        void onStartGame();

        void onContinueGame();

        void onExitGame();
    }

}
