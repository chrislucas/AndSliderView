package appcode.xploreviewpager.viewpager.indicator;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import appcode.xploreviewpager.R;

/**
 * Created by r028367 on 21/09/2017.
 */

public class SimpleTextDotIndicatorViewPager implements IndicatorPageChangeListener{

    private static final String DOT_HTML = "\u2022";
    private static final int MAX_DOTS = 5;
    private int pages;
    private Activity activity;
    private TextView [] dots;

    @ColorRes
    private static final int SELECTED_DOT = R.color.avocado;
    @ColorRes
    private static final int UNSELECTED_DOT = R.color.white;

    public SimpleTextDotIndicatorViewPager(Activity activity, int qDotsIndicator) {
        this.activity = activity;
        this.pages    = qDotsIndicator > MAX_DOTS ? MAX_DOTS : qDotsIndicator;
        this.dots     = new TextView[pages];
    }

    public  void addDotIndicator(View rootLayout) {
        ViewGroup viewGroup = (ViewGroup) rootLayout;
        LinearLayout linearLayout = new LinearLayout(activity);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT
                , ViewGroup.LayoutParams.WRAP_CONTENT);

        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setGravity(Gravity.CENTER);
        // ContextCompat.getColor(activity, R.color.translucent)
        // ((ColorDrawable)rootLayout.getBackground()).getColor()
        linearLayout.setBackgroundColor( ContextCompat.getColor(activity, R.color.translucent) );

        for(int i=0; i<pages; i++) {
            TextView dot = new TextView(activity);
            dots[i] = dot;
            Spanned text;
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                text = Html.fromHtml(DOT_HTML, Html.FROM_HTML_MODE_LEGACY);
            }
            else {
                text = Html.fromHtml(DOT_HTML);
            }
            dot.setText(text);
            dot.setTextSize(30);
            dot.setTextColor(ContextCompat.getColor(activity, UNSELECTED_DOT));
            linearLayout.addView(dots[i]);
        }
        viewGroup.addView(linearLayout);
        setSelecteced(0);
    }

    @Override
    public void execute(int positionPage) {
        positionPage %= pages;
        setSelecteced(positionPage);
        Log.i("EXEC", String.format("%d", positionPage));
        for(int i=0; i<dots.length; i++) {
            if(i != positionPage)
                setUnselecteced(i);
        }
    }

    private void setSelecteced(int position) {
        dots[position].setTextColor(ContextCompat.getColor(activity, SELECTED_DOT));
    }

    private void setUnselecteced(int position) {
        dots[position].setTextColor(ContextCompat.getColor(activity, UNSELECTED_DOT));
    }
}
