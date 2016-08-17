package vinaedu.javatest.ui.activities;

import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vinaedu.javatest.R;
import vinaedu.javatest.common.SharedPreference;
import vinaedu.javatest.common.objects.Level;
import vinaedu.javatest.model.database.DatabaseAccess;
import vinaedu.javatest.model.objects.Question;
import vinaedu.javatest.model.objects.TopScore;
import vinaedu.javatest.ui.custom_views.AnswerView;
import vinaedu.javatest.ui.custom_views.BackgroundView;
import vinaedu.javatest.ui.custom_views.QuestionView;
import vinaedu.javatest.ui.intalizes.BaseActivity;

/**
 * Created by bscenter on 11/08/2016
 */
public class PlayActivity extends BaseActivity implements BackgroundView.BackgroundListening, AnswerView.AnswerCallBack {

    private View mImgBack;
    private TextView mTvTime;

    private BackgroundView mBackgroundView;
    private QuestionView mQuestionView;
    private AnswerView mAnswerViewA;
    private AnswerView mAnswerViewB;
    private AnswerView mAnswerViewC;
    private AnswerView mAnswerViewD;

    private List<Question> questionList;

    private int score = 0;
    private int currentQuestion = 0;
    private int scoreForAnswer = 10;
    private int countTime = 0;

    private CountDownTimer mCountDownTimer;
    private DatabaseAccess mDbDatabaseAccess;
    private Level mLevel;

    @Override
    protected int getLayout() {
        return R.layout.activity_play;
    }

    @Override
    protected void initViews() {
        mImgBack = findViewById(R.id.imgBack);
        mTvTime = (TextView) findViewById(R.id.tvTime);
        mBackgroundView = (BackgroundView) findViewById(R.id.backgroundView);
        mQuestionView = (QuestionView) findViewById(R.id.questionView);
        mAnswerViewA = (AnswerView) findViewById(R.id.answerA);
        mAnswerViewB = (AnswerView) findViewById(R.id.answerB);
        mAnswerViewC = (AnswerView) findViewById(R.id.answerC);
        mAnswerViewD = (AnswerView) findViewById(R.id.answerD);

        mBackgroundView.setListening(this);
        mAnswerViewA.setListening(this);
        mAnswerViewB.setListening(this);
        mAnswerViewC.setListening(this);
        mAnswerViewD.setListening(this);

        mImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void main() {
        mDbDatabaseAccess = DatabaseAccess.getInstance(this);
        getQuestionList();
        setupTimer();
        setVisibleQuestion(false);
        mBackgroundView.onStartGame();
    }

    private void setupTimer() {
        mLevel = Level.getType(getIntent().getExtras().getInt("LEVEL"));
        int time = mLevel.getValue() == 1 ? 200 : mLevel.getValue() == 2 ? 150 : 100;
        mTvTime.setText(String.valueOf(time));
        mCountDownTimer = new CountDownTimer(time * 1000, 1000) {
            @Override
            public void onTick(long l) {
                mTvTime.setText(String.valueOf(l / 1000));
            }

            @Override
            public void onFinish() {
                mTvTime.setText("0");
                saveScore();
                hideQuestion();
                mBackgroundView.onFinishGame(score, currentQuestion, questionList.size());
            }
        };
    }

    private void resetCountTimer() {
        int time = Integer.parseInt(mTvTime.getText().toString());
        mTvTime.setText(String.valueOf(time));
        mCountDownTimer = new CountDownTimer(time * 1000, 1000) {
            @Override
            public void onTick(long l) {
                mTvTime.setText(String.valueOf(l / 1000));
            }

            @Override
            public void onFinish() {
                saveScore();
                mTvTime.setText("0");
                hideQuestion();
                mBackgroundView.onFinishGame(score, currentQuestion, questionList.size());
            }
        };
        mCountDownTimer.start();
    }

    private void saveScore() {
        if (SharedPreference.getInstance(this).isPlayed()) {
            int bestScore = SharedPreference.getInstance(this).getBestScore();
            if (bestScore < score) {
                SharedPreference.getInstance(this).setBestScore(score);
            }
        } else {
            SharedPreference.getInstance(this).setBestScore(score);
        }
        TopScore topScore = new TopScore(score, mLevel.getValue());
        SharedPreference.getInstance(this).setTopScore(topScore);
        SharedPreference.getInstance(this).setPlayed();

    }

    private void showQuestion() {
        Question question = questionList.get(currentQuestion - 1);
        mAnswerViewA.setAnswer("A", question.getAnswerA(), question.getTrueAnswer().equalsIgnoreCase("A"));
        mAnswerViewB.setAnswer("B", question.getAnswerB(), question.getTrueAnswer().equalsIgnoreCase("B"));
        mAnswerViewC.setAnswer("C", question.getAnswerC(), question.getTrueAnswer().equalsIgnoreCase("C"));
        mAnswerViewD.setAnswer("D", question.getAnswerD(), question.getTrueAnswer().equalsIgnoreCase("D"));
        mQuestionView.setData(currentQuestion, question.getQuestion());

        setVisibleQuestion(true);
        TranslateAnimation animShowQuestion = (TranslateAnimation) AnimationUtils.loadAnimation(this, R.anim.anim_top_in);
        mQuestionView.startAnimation(animShowQuestion);

        TranslateAnimation animShowAnswerLeft = (TranslateAnimation) AnimationUtils.loadAnimation(this, R.anim.anim_left_in);
        mAnswerViewA.startAnimation(animShowAnswerLeft);
        mAnswerViewC.startAnimation(animShowAnswerLeft);

        TranslateAnimation animShowAnswerRigth = (TranslateAnimation) AnimationUtils.loadAnimation(this, R.anim.anim_right_in);
        mAnswerViewB.startAnimation(animShowAnswerRigth);
        mAnswerViewD.startAnimation(animShowAnswerRigth);
    }

    private void hideQuestion() {
        TranslateAnimation animShowQuestion = (TranslateAnimation) AnimationUtils.loadAnimation(this, R.anim.anim_top_out);
        mQuestionView.startAnimation(animShowQuestion);

        TranslateAnimation animShowAnswerLeft = (TranslateAnimation) AnimationUtils.loadAnimation(this, R.anim.anim_left_out);
        mAnswerViewA.startAnimation(animShowAnswerLeft);
        mAnswerViewC.startAnimation(animShowAnswerLeft);

        TranslateAnimation animShowAnswerRigth = (TranslateAnimation) AnimationUtils.loadAnimation(this, R.anim.anim_right_out);
        mAnswerViewB.startAnimation(animShowAnswerRigth);
        mAnswerViewD.startAnimation(animShowAnswerRigth);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setVisibleQuestion(false);
            }
        }, 400);
    }

    private void setVisibleQuestion(boolean isVisible) {
        mQuestionView.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        mAnswerViewA.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        mAnswerViewB.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        mAnswerViewC.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        mAnswerViewD.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onStartGame() {
        currentQuestion++;
        showQuestion();
        mCountDownTimer.start();
    }

    @Override
    public void onContinueGame() {
        resetCountTimer();
        showQuestion();
    }

    @Override
    public void onExitGame() {
        close();
    }

    @Override
    public void onAnswer(boolean isTrueAnswer) {
        if (isTrueAnswer) {
            score += scoreForAnswer;
        }
        hideQuestion();

        if (currentQuestion == questionList.size()) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
            saveScore();
            mBackgroundView.onFinishGame(score, questionList.size(), questionList.size());
        } else {
            mBackgroundView.onDetailGame(score, currentQuestion, questionList.size());
            currentQuestion++;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    showQuestion();
                }
            }, 500);
        }

    }

    @Override
    public void onBackPressed() {
        if (mBackgroundView.getType() == BackgroundView.ONPENDDING) {
            mBackgroundView.onPauseGame(score, questionList.size(), questionList.size());
            mCountDownTimer.cancel();
            mCountDownTimer = null;
            hideQuestion();
        } else if (mBackgroundView.getType() == BackgroundView.ONSTART) {
            close();
        }
    }

    public void getQuestionList() {
        questionList = new ArrayList<>();
        mDbDatabaseAccess.open();
        questionList.addAll(mDbDatabaseAccess.getGetQuestion());
    }
}
