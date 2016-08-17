package vinaedu.javatest.ui.custom_views;

import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import vinaedu.javatest.R;

/**
 * Created by bscenter on 11/08/2016
 */
public class AnswerView extends RelativeLayout {
    private RelativeLayout mRlRoot;
    private TextView mTvAnswerTitle;
    private TextView mTvAnswerContent;

    private AnswerCallBack mAnswerCallBack;

    private boolean mIsTrueAnswer;

    public AnswerView(Context context) {
        this(context, null);
    }

    public AnswerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnswerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.custom_answer_view, null);
        initViews(view);
        addView(view);
    }

    private void initViews(View view) {
        mRlRoot = (RelativeLayout) view.findViewById(R.id.rlRoot);
        mTvAnswerTitle = (TextView) view.findViewById(R.id.tvAnswerTitle);
        mTvAnswerContent = (TextView) view.findViewById(R.id.tvAnswerContent);

        mRlRoot.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mAnswerCallBack.onAnswer(mIsTrueAnswer);
            }
        });
    }

    public void setAnswer(String title, String content, boolean isTrueAnswer) {
        mIsTrueAnswer = isTrueAnswer;
        mTvAnswerTitle.setText(title);
        mTvAnswerContent.setText(Html.fromHtml(content));
    }

    public void setListening(AnswerCallBack answerCallBack) {
        mAnswerCallBack = answerCallBack;
    }


    public interface AnswerCallBack {
        void onAnswer(boolean isTrueAnswer);
    }
}
