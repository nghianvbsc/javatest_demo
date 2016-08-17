package vinaedu.javatest.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import vinaedu.javatest.ui.fragments.GuideLevel;
import vinaedu.javatest.ui.fragments.GuideOverview;
import vinaedu.javatest.ui.fragments.GuidePauseAndExit;
import vinaedu.javatest.ui.fragments.GuideScoreAndTime;

/**
 * Created by bscenter on 11/08/2016
 */
public class GuideAdapter extends FragmentPagerAdapter {

    public GuideAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0) {
            fragment = new GuideOverview();
        } else if (position == 1) {
            fragment = new GuideLevel();
        } else if (position == 2) {
            fragment = new GuideScoreAndTime();
        } else if (position == 3) {
            fragment = new GuidePauseAndExit();
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
