package appcode.xploreviewpager;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import appcode.xploreviewpager.viewpager.transformer.ScaleViewPageTransformer;
import appcode.xploreviewpager.viewpager.transformer.adapter.ImageViewPageAdapter;
import appcode.xploreviewpager.viewpager.listener.OnPageChangeListenerImpl;
import appcode.xploreviewpager.utils.ImageReaderFromAssetsFolder;
import appcode.xploreviewpager.utils.UtilsView;

public class XplorePageTransformer extends AppCompatActivity implements OnPageChangeListenerImpl.CallbackPageChange {

    private PagerAdapter pagerAdapter;
    private ViewPager.OnPageChangeListener onPageChangeListener;
    private ViewPager.PageTransformer pageTransformer;
    private ViewPager.OnAdapterChangeListener onAdapterChangeListener;
    private List<Bitmap> bitmaps;

    private ViewGroup root;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xplore_page_transformer);
        root = (ViewGroup) findViewById(R.id.layout_root);

        initialize();
        viewPager = UtilsView.find(root, R.id.view_pager_imageview);
        viewPager.setPageTransformer(true, pageTransformer);
        viewPager.addOnAdapterChangeListener(onAdapterChangeListener);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(onPageChangeListener);
    }

    private void initialize() {
        final List<String> strs = new ArrayList<>(Arrays.asList(new String[] {
             "pics/aviso_1.png"
            ,"pics/aviso_2.png"
            ,"pics/aviso_3.png"
        }));
        try {
            Context context = this;
            bitmaps = ImageReaderFromAssetsFolder.getImages(context, strs);
            pagerAdapter = new ImageViewPageAdapter(context, (ArrayList<Bitmap>) bitmaps,  R.layout.layout_imageview, R.id.image_on_slider);
            pageTransformer = new ScaleViewPageTransformer();
            onAdapterChangeListener = new ViewPager.OnAdapterChangeListener() {
                @Override
                public void onAdapterChanged(@NonNull ViewPager viewPager, @Nullable PagerAdapter oldAdapter, @Nullable PagerAdapter newAdapter) {}
            };
            onPageChangeListener = new OnPageChangeListenerImpl(this);
        }
        catch (IOException ioex) {
            Log.e("IOEX_READ_BITMAPS", ioex.getMessage());
        }
    }

    @Override
    public void call(int pos) {
        Log.i("CallbackImgPagerAdapter", String.format("Call(%d)", pos));
        Button button = UtilsView.find(root, R.id.button_redirect);
        if(pos == bitmaps.size() - 1) {
            button.setVisibility(View.VISIBLE);
        }
        else {
            if(button.isShown()) {
                button.setVisibility(View.GONE);
            }
        }
    }


    private ViewPager.SimpleOnPageChangeListener getSimpleOnPageChangeListener() {
        return new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
    }

    public void redirect(View view) {

    }
}
