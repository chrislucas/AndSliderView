package appcode.xploreviewpager.utils;

import android.support.annotation.IdRes;
import android.view.View;

/**
 * Created by r028367 on 11/09/2017.
 */

public class UtilsView {

    public static<ViewCast extends View> ViewCast find(View viewRoot, @IdRes int id) {
        ViewCast view = (ViewCast) viewRoot.findViewById(id);
        return view;
    }
}
