package vinaedu.javatest.ui.custom_views;

import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import vinaedu.javatest.R;

/**
 * Created by bscenter on 11/08/2016
 */
public class QuestionView extends RelativeLayout {

    private TextView mTvTitle;
    private TextView mTvContent;

    public QuestionView(Context context) {
        this(context, null);
    }

    public QuestionView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QuestionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.cusom_question_view, null);
        initView(view);
        addView(view);
    }

    private void initView(View view) {
        mTvTitle = (TextView) view.findViewById(R.id.tvTitleQuestion);
        mTvContent = (TextView) view.findViewById(R.id.tvContenQuestion);
    }

    public void setData(int titleQuestion, String contentQuestion) {
        mTvTitle.setText("Câu " + titleQuestion);
        mTvContent.setText(Html.fromHtml(contentQuestion));
    }
}
