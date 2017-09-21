package appcode.xploreviewpager.viewpager.listener;

import android.support.v4.view.ViewPager;

/**
 * Created by r028367 on 21/09/2017.
 */

public class SimpleOnPageChangeListenerrImpl extends ViewPager.SimpleOnPageChangeListener {

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        super.onPageScrolled(position, positionOffset, positionOffsetPixels);
    }

    @Override
    public void onPageSelected(int position) {
        super.onPageSelected(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        super.onPageScrollStateChanged(state);
    }
}
