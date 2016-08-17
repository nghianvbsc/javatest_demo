package vinaedu.javatest.ui.fragments;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import vinaedu.javatest.R;
import vinaedu.javatest.common.SharedPreference;
import vinaedu.javatest.model.objects.TopScore;
import vinaedu.javatest.ui.intalizes.BaseFragment;

/**
 * Created by bscenter on 11/08/2016
 */
public class ScoreFragment extends BaseFragment {

    private TextView mTvEmptyData;
    private TextView mTvBestScore;
    private View mLnData;
    private ListView mLvNearScore;

    @Override
    protected int getLayout() {
        return R.layout.fragment_score;
    }

    @Override
    protected void initViews() {
        mTvEmptyData = (TextView) view.findViewById(R.id.tvEmptyData);
        mLnData = view.findViewById(R.id.lnData);
        mTvBestScore = (TextView) view.findViewById(R.id.tvBestScore);
        mLvNearScore = (ListView) view.findViewById(R.id.lvNearScore);
    }

    @Override
    protected void main() {
        if (!SharedPreference.getInstance(getActivity()).isPlayed()) {
            mTvEmptyData.setVisibility(View.VISIBLE);
            mLnData.setVisibility(View.GONE);
        } else {
            mTvEmptyData.setVisibility(View.GONE);
            mLnData.setVisibility(View.VISIBLE);
            mTvBestScore.setText(String.valueOf(SharedPreference.getInstance(getActivity()).getBestScore()));
            mLvNearScore.setAdapter(new TopScoreAdapter(SharedPreference.getInstance(getActivity()).getTopScore()));
        }

    }

    public class TopScoreAdapter extends BaseAdapter {
        private List<TopScore> mTopScoreList;

        public TopScoreAdapter(List<TopScore> topScoreList) {
            mTopScoreList = topScoreList;
        }

        @Override
        public int getCount() {
            return mTopScoreList == null ? 0 : mTopScoreList.size();
        }

        @Override
        public TopScore getItem(int i) {
            return mTopScoreList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            View newView = view;
            if (newView == null) {
                newView = LayoutInflater.from(getContext()).inflate(R.layout.item_top_score, viewGroup, false);
                viewHolder = new ViewHolder();
                viewHolder.textView = (TextView) newView.findViewById(R.id.tvTextView);
                newView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) newView.getTag();
            }

            String level = getItem(i).getLevel() == 1 ? "Dễ" : getItem(i).getLevel() == 2 ? "Trung bình" : "Khó";
            viewHolder.textView.setText("Điểm: " + getItem(i).getScore() + " --- Mức độ chơi: " + level);
            return newView;
        }

        private class ViewHolder {
            private TextView textView;
        }
    }
}
