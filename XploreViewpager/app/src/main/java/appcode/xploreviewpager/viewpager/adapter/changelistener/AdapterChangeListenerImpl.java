package appcode.xploreviewpager.viewpager.adapter.changelistener;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

/**
 * Created by r028367 on 21/09/2017.
 */

public class AdapterChangeListenerImpl implements ViewPager.OnAdapterChangeListener {

    public AdapterChangeListenerImpl() {
    }

    /**
     * Called when the adapter for the given view pager has changed.
     *
     * @param viewPager  ViewPager where the adapter change has happened
     * @param oldAdapter the previously set adapter
     * @param newAdapter the newly set adapter
     */
    @Override
    public void onAdapterChanged(@NonNull ViewPager viewPager
            , @Nullable PagerAdapter oldAdapter, @Nullable PagerAdapter newAdapter) {

    }
}
