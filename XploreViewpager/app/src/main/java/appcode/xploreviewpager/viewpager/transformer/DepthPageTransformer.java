package appcode.xploreviewpager.viewpager.transformer;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by r028367 on 11/09/2017.
 */

public class DepthPageTransformer implements ViewPager.PageTransformer {

    public static final float MIN_SCALE = 0.85f;

    /**
     * Apply a property transformation to the given page.
     *
     * @param page     Apply the transformation to this page
     * @param position Position of page relative to the current front-and-center
     *                 position of the pager. 0 is front and center. 1 is one full
     */
    @Override
    public void transformPage(View page, float position) {
        if (position <= 0f) {
            page.setTranslationX(0f);
            page.setScaleX(1f);
            page.setScaleY(1f);
        }
        else if (position <= 1f) {
            float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
            page.setAlpha(1 - position);
            page.setPivotY(0.5f * page.getHeight());
            page.setTranslationX(page.getWidth() * -position);
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
        }
    }
}
