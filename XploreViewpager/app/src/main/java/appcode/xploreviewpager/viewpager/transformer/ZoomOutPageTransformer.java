package appcode.xploreviewpager.viewpager.transformer;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

/**
 * Created by r028367 on 11/09/2017.
 *
 * {@link android.support.v4.view.ViewPager.PageTransformer}
 *  // https://developer.android.com/reference/android/support/v4/view/ViewPager.PageTransformer.html
 *
 *  Uma implementacao de PageTransformer e invocada quando uma Page de um ViewPager for arrastado
 *  para algum lado. Atraves dela podemos criar um efeito personalizado quando o usuario arrasta
 *  um componente que esta dentro de um viewpager
 */

public class ZoomOutPageTransformer implements ViewPager.PageTransformer {

    private static final float MIN_SCALE = 0.85f;
    private static final float MIN_ALPHA = 0.2f;

    /**
     * Apply a property transformation to the given page.
     *
     * @param page     Apply the transformation to this page
     * @param position Position of page relative to the current front-and-center
     *                 position of the pager. 0 is front and center. 1 is one full
     */
    @Override
    public void transformPage(View page, float position) {
        int h = page.getHeight(), w = page.getWidth();
        if(position < -1) {
            page.setAlpha(0.0f);
        }

        else if(position <= 1) {
            float v = 1 - Math.abs(position);
            float scaleFactor = MIN_SCALE < v  ? MIN_SCALE : v;
            float verticalMargin = h * (1-scaleFactor) / 2;
            float horizontalMargin = w * (1-scaleFactor) / 2;
            if(position < 0) {
                page.setTranslationX(horizontalMargin - verticalMargin / 2);
            }
            else {
                page.setTranslationX(-horizontalMargin + verticalMargin / 2);

            }
            // define o valor que sera utilizado para aplicar a
            // operacao de Scale na view em relacao ao um ponto pivo no eixo 'X' (largura da tela)
            // Esse ponto pivo pode ser pego atraves do metodo
            /**
             * {@link View#getPivotX()} e {@link View#getPivotY()}
             * */
            page.setScaleX(scaleFactor);
            Log.i("PivotPoint", String.format("P(%f, %f)", page.getPivotX(), page.getPivotY()));
            // o mesmo que setScaleY porem para a operacao de escala e realizada em relacao
            // ao eixo Y
            page.setScaleY(scaleFactor);
            //page.setAlpha(0.99f/*MIN_ALPHA + (scaleFactor - MIN_SCALE)/(1-MIN_SCALE)*(1-MIN_ALPHA)*/);
        }

        else {
            page.setAlpha(0.0f);
        }

    }
}
