package vinaedu.javatest.ui.fragments;

import android.support.v4.view.ViewPager;

import vinaedu.javatest.R;
import vinaedu.javatest.ui.adapters.GuideAdapter;
import vinaedu.javatest.ui.custom_views.CirclePageIndicator;
import vinaedu.javatest.ui.intalizes.BaseFragment;

/**
 * Created by bscenter on 11/08/2016
 */
public class GuideFragment extends BaseFragment {

    private ViewPager mVpGuide;
    private CirclePageIndicator mCircleIn;
    private GuideAdapter mGuideAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_guide;
    }

    @Override
    protected void initViews() {
        mVpGuide = (ViewPager) view.findViewById(R.id.vpGuide);
        mCircleIn = (CirclePageIndicator) view.findViewById(R.id.circleIn);
    }

    @Override
    protected void main() {
        mGuideAdapter = new GuideAdapter(getFragmentManager());
        mVpGuide.setAdapter(mGuideAdapter);
        mCircleIn.setViewPager(mVpGuide);
    }
}
