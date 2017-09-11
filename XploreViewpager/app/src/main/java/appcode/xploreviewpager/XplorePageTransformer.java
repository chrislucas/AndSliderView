package appcode.xploreviewpager;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import appcode.xploreviewpager.transformer.BackgroundToForegroundTransformer;
import appcode.xploreviewpager.transformer.ScaleViewPageTransformer;
import appcode.xploreviewpager.transformer.adapter.ImageViewPageAdapter;
import appcode.xploreviewpager.listener.OnPageChangeListenerImpl;
import appcode.xploreviewpager.utils.ImageReaderFromAssetsFolder;
import appcode.xploreviewpager.utils.UtilsView;

public class XplorePageTransformer extends AppCompatActivity implements OnPageChangeListenerImpl.CallbackPageChange {

    private PagerAdapter pagerAdapter;
    private ViewPager viewPager;
    private ViewPager.OnPageChangeListener onPageChangeListener;
    private ViewPager.SimpleOnPageChangeListener simpleOnPageChangeListener;
    private ViewPager.PageTransformer pageTransformer;
    private ViewPager.OnAdapterChangeListener onAdapterChangeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xplore_page_transformer);
        initialize();
        viewPager = UtilsView.find(findViewById(R.id.layout_viewpager2), R.id.view_pager_xplore2);
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
            List<Bitmap> bitmaps = ImageReaderFromAssetsFolder.getImages(this, strs);
            pagerAdapter = new ImageViewPageAdapter(this, (ArrayList<Bitmap>) bitmaps, R.layout.layout_imageview);
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
    public void call() {

    }

    private void setSimpleOnPageChangeListener() {
        simpleOnPageChangeListener = new ViewPager.SimpleOnPageChangeListener();
    }
}
