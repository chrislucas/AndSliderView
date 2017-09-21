package appcode.xploreviewpager.viewpager.listener;

import android.support.v4.view.ViewPager;
import android.util.Log;

import appcode.xploreviewpager.viewpager.indicator.IndicatorPageChangeListener;

/**
 * Created by r028367 on 11/09/2017.
 */

public class OnPageChangeListenerImpl implements ViewPager.OnPageChangeListener {

    public interface CallbackPageChangeListener {
        void callback(int position);
    }

    private CallbackPageChangeListener callbackPageChangeListener;
    private IndicatorPageChangeListener indicatorPageChangeListener;

    public OnPageChangeListenerImpl(CallbackPageChangeListener callbackPageChangeListener) {
        this.callbackPageChangeListener = callbackPageChangeListener;
    }

    public OnPageChangeListenerImpl(CallbackPageChangeListener callbackPageChangeListener
            , IndicatorPageChangeListener indicatorPageChangeListener) {
        this.callbackPageChangeListener  = callbackPageChangeListener;
        this.indicatorPageChangeListener = indicatorPageChangeListener;
    }

    /**
     * This method will be invoked when the current page is scrolled, either as part
     * of a programmatically initiated smooth scroll or a user initiated touch scroll.
     *
     * @param position             Position index of the first page currently being displayed.
     *                             Page position+1 will be visible if positionOffset is nonzero.
     * @param positionOffset       Value from [0, 1) indicating the offset from the page at position.
     * @param positionOffsetPixels Value in pixels indicating the offset from position.
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * This method will be invoked when a new page becomes selected. Animation is not
     * necessarily complete.
     *
     * @param position Position index of the new selected page.
     */
    @Override
    public void onPageSelected(int position) {
        Log.i("ON_PAGE_SELECTED", String.valueOf(position));
        if(callbackPageChangeListener != null)
            callbackPageChangeListener.callback(position);
        if(indicatorPageChangeListener != null)
            indicatorPageChangeListener.execute(position);
    }

    /**
     * Called when the scroll state changes. Useful for discovering when the user
     * begins dragging, when the pager is automatically settling to the current page,
     * or when it is fully stopped/idle.
     *
     * @param state The new scroll state.
     * @see ViewPager#SCROLL_STATE_IDLE
     * @see ViewPager#SCROLL_STATE_DRAGGING
     * @see ViewPager#SCROLL_STATE_SETTLING
     */
    @Override
    public void onPageScrollStateChanged(int state) {
        String message = "";
        switch (state) {
            case ViewPager.SCROLL_STATE_IDLE:
                message = "ViewPager.SCROLL_STATE_IDLE";
                break;

            case ViewPager.SCROLL_STATE_DRAGGING:
                message = "ViewPager.SCROLL_STATE_DRAGGING";
                break;

            case ViewPager.SCROLL_STATE_SETTLING:
                message = "ViewPager.SCROLL_STATE_SETTLING";
                break;
        }

        Log.i("PAGE_SCR_STATE_CHANGED", message);
    }
}
