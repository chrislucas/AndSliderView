package appcode.xploreviewpager;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import appcode.xploreviewpager.viewpager.adapter.changelistener.AdapterChangeListenerImpl;
import appcode.xploreviewpager.viewpager.transformer.impl.ScaleViewPageTransformer;
import appcode.xploreviewpager.viewpager.adapter.pageadapter.ImageViewPageAdapter;
import appcode.xploreviewpager.viewpager.listener.OnPageChangeListenerImpl;
import appcode.xploreviewpager.utils.ImageReaderFromAssetsFolder;
import appcode.xploreviewpager.utils.UtilsView;
import appcode.xploreviewpager.viewpager.indicator.SimpleTextDotIndicatorViewPager;

public class XplorePageTransformerListener extends AppCompatActivity
        implements OnPageChangeListenerImpl.CallbackPageChangeListener {

    private PagerAdapter pagerAdapter;
    private ViewPager.OnPageChangeListener onPageChangeListener;
    private ViewPager.PageTransformer pageTransformer;
    private ViewPager.OnAdapterChangeListener onAdapterChangeListener;
    private List<Bitmap> bitmaps;
    private ViewGroup root;
    private ViewPager viewPager;

    private SimpleTextDotIndicatorViewPager simpleTextDotIndicatorViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xplore_page_transformer);
        root = (ViewGroup) findViewById(R.id.wrapper_layout_viewpager);
        initialize();
        viewPager = UtilsView.find(root, R.id.view_pager_imageview);
        viewPager.setPageTransformer(true, pageTransformer);
        viewPager.addOnAdapterChangeListener(onAdapterChangeListener);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(onPageChangeListener);
    }

    public List<Bitmap> getImages(String pathfile) throws IOException {
        List<Bitmap> bitmaps = new ArrayList<>();
        AssetManager manager = getAssets();
        String [] array = manager.list(pathfile);
        for(String file : array) {
            String regex = "^.*\\.(jpg|png)";
            if( file.matches(regex) ) {
                InputStream inputStream = manager.open(String.format("%s/%s", pathfile, file));
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                inputStream.close();
                bitmaps.add(bitmap);
            }
        }
        return bitmaps;
    }

    private void initialize() {
        try {
            Context context = this;
            bitmaps         = getImages("pics");
            pagerAdapter    = new ImageViewPageAdapter(context, (ArrayList<Bitmap>) bitmaps
                    ,  R.layout.layout_imageview, R.id.image_on_slider);
            pageTransformer         = new ScaleViewPageTransformer();
            onAdapterChangeListener = new AdapterChangeListenerImpl();

            int qPages = pagerAdapter.getCount();
            simpleTextDotIndicatorViewPager = new SimpleTextDotIndicatorViewPager(this, qPages);
            View wrapperIndicators = UtilsView.find(root, R.id.wrapper_indicator_viewpager);
            simpleTextDotIndicatorViewPager.addDotIndicator(wrapperIndicators);
            onPageChangeListener    = new OnPageChangeListenerImpl(this, simpleTextDotIndicatorViewPager);
        }
        catch (IOException ioex) {
            Log.e("IOEX_READ_BITMAPS", ioex.getMessage());
        }
    }

    @Override
    public void callback(int pos) {
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


    public void redirect(View view) {

    }
}
