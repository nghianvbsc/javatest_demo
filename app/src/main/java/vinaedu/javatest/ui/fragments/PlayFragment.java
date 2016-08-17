package vinaedu.javatest.ui.fragments;

import android.view.View;
import android.widget.Button;

import vinaedu.javatest.R;
import vinaedu.javatest.common.interfaces.LevelListening;
import vinaedu.javatest.common.objects.Level;
import vinaedu.javatest.ui.intalizes.BaseFragment;

/**
 * Created by bscenter on 11/08/2016
 */
public class PlayFragment extends BaseFragment implements View.OnClickListener {

    private Button mBtnE;
    private Button mBtnM;
    private Button mBtnD;

    @Override
    protected int getLayout() {
        return R.layout.fragment_play;
    }

    @Override
    protected void initViews() {
        mBtnE = (Button) view.findViewById(R.id.btnE);
        mBtnM = (Button) view.findViewById(R.id.btnM);
        mBtnD = (Button) view.findViewById(R.id.btnD);
    }

    @Override
    protected void main() {
        mBtnE.setOnClickListener(this);
        mBtnM.setOnClickListener(this);
        mBtnD.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Level level;
        if (view == mBtnE) {
            level = Level.easy;
        } else if (view == mBtnM) {
            level = Level.medium;
        } else {
            level = Level.difficult;
        }

        if (getActivity() instanceof LevelListening) {
            ((LevelListening) getActivity()).onChoiceLevel(level);
        }
    }
}
