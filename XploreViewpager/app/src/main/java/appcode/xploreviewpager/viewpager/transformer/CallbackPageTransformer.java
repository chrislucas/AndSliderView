package appcode.xploreviewpager.viewpager.transformer;

import android.view.View;

/**
 * Created by r028367 on 11/09/2017.
 */

public interface CallbackPageTransformer {

    public void preAnimation(View page, float position);
    public void onAnimation(View page, float position);
    public void posAnimation(View page, float position);

}
