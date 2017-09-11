package appcode.xploreviewpager.transformer.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.LayoutRes;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import java.util.ArrayList;

import appcode.xploreviewpager.R;

/**
 * Created by r028367 on 08/09/2017.
 *
 *
 *  {@link PagerAdapter} eh a classe base que provê um
 *  adapter para popular uma {@link android.support.v4.view.ViewPager}
 *
 *
 *  Implementacoes especificas de PagerAdapter sao:
 *  {@link android.support.v4.app.FragmentPagerAdapter}
 *  {@link android.support.v4.app.FragmentStatePagerAdapter}
 *
 *
 */

public class ImageViewPageAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Bitmap> bitmaps;
    private View layoutPageViewer;

    public interface Callback {
        void lastPageListener(View viewGroup);
    }

    private ImageViewPageAdapter.Callback callback;

    @LayoutRes
    private int layoutRes;

    public ImageViewPageAdapter(Context context, ArrayList<Bitmap> bitmaps, int layoutRes) {
        this.context        = context;
        // o metodo abaixo encapsula  (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layoutInflater = LayoutInflater.from(context);
        this.bitmaps        = bitmaps;
        this.layoutRes      = layoutRes;
    }

    public ImageViewPageAdapter(Context context, ArrayList<Bitmap> bitmaps, ImageViewPageAdapter.Callback callback, int layoutRes) {
        this.context        = context;
        // o metodo abaixo encapsula  (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layoutInflater = LayoutInflater.from(context);
        this.bitmaps        = bitmaps;
        this.layoutRes      = layoutRes;
        this.callback       = callback;
    }

    public View getLayoutPageViewer() {
        return layoutPageViewer;
    }

    public Context getContext() {
        return context;
    }

    public LayoutInflater getLayoutInflater() {
        return layoutInflater;
    }

    public ArrayList<Bitmap> getBitmaps() {
        return bitmaps;
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return bitmaps.size();
    }

    /**
     * Determines whether a page View is associated with a specific key object
     * as returned by {@link PagerAdapter#instantiateItem(View, int)} . This method is
     * required for a PagerAdapter to function properly.
     *
     * @param view   Page View to check for association with <code>object</code>
     * @param object Object to check for association with <code>view</code>
     * @return true if <code>view</code> is associated with the key object <code>object</code>
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        boolean isEquals = view == ((View)object);
        return isEquals;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        if(callback != null && position == 0) {
            callback.lastPageListener(layoutPageViewer);
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutPageViewer    = layoutInflater.inflate(layoutRes, container, false);
        ImageView imageView = (ImageView) layoutPageViewer.findViewById(R.id.image_on_slider);
        imageView.setImageBitmap(bitmaps.get(position));
        ViewParent viewParent = imageView.getParent();
        if(viewParent != null) {
            ((ViewGroup) viewParent).removeView(imageView);
        }
        else {
            container.removeView(imageView);
        }
        container.addView(imageView);
        return imageView;
    }
}
