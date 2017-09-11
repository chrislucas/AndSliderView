package appcode.xploreviewpager.transformer;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by r028367 on 11/09/2017.
 */

public class BackgroundToForegroundTransformer implements ViewPager.PageTransformer {

    /**
     * Apply a property transformation to the given page.
     *
     * @param page     Apply the transformation to this page
     * @param position Position of page relative to the current front-and-center
     *                 position of the pager. 0 is front and center. 1 is one full
     */
    @Override
    public void transformPage(View page, float position) {
        final float height = page.getHeight();
        final float width = page.getWidth();
        final float scale = Math.min(position < 0 ? 1f : Math.abs(1f - position), 0.5f);
        page.setScaleX(scale);
        page.setScaleY(scale);
        page.setPivotX(width * 0.5f);
        page.setPivotY(height * 0.5f);
        page.setTranslationX(position < 0 ? width * position : -width * position * 0.25f);
    }
}
