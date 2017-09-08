package appcode.xploreviewpager;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import appcode.xploreviewpager.activities.CardFlipActivity;
import appcode.xploreviewpager.activities.CrossfadeActivity;
import appcode.xploreviewpager.activities.LayoutChangesActivity;
import appcode.xploreviewpager.activities.ScreenSlideActivity;
import appcode.xploreviewpager.activities.ZoomActivity;


/**
 * Estudo sobre ViewPage a partir do exemplo google em :
 * https://developer.android.com/training/animation/screen-slide.html
 * */

public class GoogleSlider extends ListActivity {


    private class Sample {
        private CharSequence title;
        private Class<? extends Activity> activityClass;

        public Sample(int titleResId, Class<? extends Activity> activityClass) {
            this.activityClass = activityClass;
            this.title = getResources().getString(titleResId);
        }

        @Override
        public String toString() {
            return title.toString();
        }
    }

    private Sample [] mSamples;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xplore_slider);

        mSamples = new Sample[]{
                new Sample(R.string.title_crossfade, CrossfadeActivity.class),
                new Sample(R.string.title_card_flip, CardFlipActivity.class),
                new Sample(R.string.title_screen_slide, ScreenSlideActivity.class),
                new Sample(R.string.title_zoom, ZoomActivity.class),
                new Sample(R.string.title_layout_changes, LayoutChangesActivity.class),
        };

        setListAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                mSamples));
    }

    @Override
    protected void onListItemClick(ListView listView, View view, int position, long id) {
        // Launch the sample associated with this list position.
        startActivity(new Intent(this, mSamples[position].activityClass));
    }
}
