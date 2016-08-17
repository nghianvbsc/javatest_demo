package vinaedu.javatest.ui.intalizes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by bscenter on 11/08/2016
 */
public abstract class BaseFragment extends Fragment {

    protected View view;

    protected abstract int getLayout();

    protected abstract void initViews();

    protected abstract void main();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(getLayout(), container, false);
        initViews();
        main();
        return view;
    }
}
