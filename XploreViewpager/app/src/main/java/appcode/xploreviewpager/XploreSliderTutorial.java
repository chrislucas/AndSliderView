package appcode.xploreviewpager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.LayoutDirection;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import appcode.xploreviewpager.animation.transformation.adapter.ImageViewPageAdapter;
import appcode.xploreviewpager.utils.ImageReaderFromAssetsFolder;
import me.relex.circleindicator.CircleIndicator;

public class XploreSliderTutorial extends AppCompatActivity {

    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_xplore_viewpager);

        viewPager = (ViewPager) findViewById(R.id.view_pager_xplore);
        circleIndicator = (CircleIndicator) findViewById(R.id.view_page_indicator);

        final List<String> strs = new ArrayList<>(Arrays.asList(new String[] {
             "pics/aviso_1.png"
            ,"pics/aviso_2.png"
            ,"pics/aviso_3.png"
        }));
        final Context context = this;
        new Handler(getMainLooper()).post(new Runnable() {
            @IdRes
            public static final int idButton = 0xff0000;

            @Override
            public void run() {
                ImageViewPageAdapter.Callback callback = new ImageViewPageAdapter.Callback() {
                    @Override
                    public void lastPageListener(View view) {
                        Button button = (Button) view.findViewById(idButton);
                        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.component_slider);
                        if(button == null && relativeLayout != null) {
                            button = new Button(context);
                            button.setId(idButton);
                            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                                     ViewGroup.LayoutParams.WRAP_CONTENT
                                    ,ViewGroup.LayoutParams.WRAP_CONTENT);
                            layoutParams.addRule(RelativeLayout.ABOVE, R.id.image_on_slider);
                            layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
                            button.setLayoutParams(layoutParams);
                            button.setText("Ok Entendi");
                            button.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if(v != null) {

                                    }
                                }
                            });
                            //button.setBackgroundColor(ContextCompat.getColor(context, R.color.avocado));
                            //button.setTextColor(ContextCompat.getColor(context, R.color.white));
                            relativeLayout.addView(button);
                        }
                    }
                };

                List<Bitmap> bitmaps = null;
                try {
                    bitmaps = ImageReaderFromAssetsFolder.getImages(context, strs);
                }
                catch (IOException ioex) {
                    Log.e("IOEX_READ_BITMAPS", ioex.getMessage());
                }
                finally {
                    if(bitmaps != null) {
                        PagerAdapter pagerAdapter = new ImageViewPageAdapter(context
                                ,(ArrayList<Bitmap>) bitmaps
                                ,callback
                                ,R.layout.layout_imageview);
                        viewPager.setAdapter(pagerAdapter);
                        circleIndicator.setViewPager(viewPager);
                        circleIndicator.setBackgroundColor(Color.BLACK);
                    }
                }
            }
        });
    }
}
