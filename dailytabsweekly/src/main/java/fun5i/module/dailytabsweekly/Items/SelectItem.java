package fun5i.module.dailytabsweekly.Items;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.LinearLayout;

import fun5i.module.dailytabsweekly.R;

import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

public class SelectItem extends LinearLayout {

    private static final String TAG = "SelectItem";

    // TODO ; Component
    private LinearLayout.LayoutParams params, belakangParams, bawahParams;
    private ImageView bawah;
    private ImageView belakang;

    // TODO: Component Value
    public boolean SHOW_ICON = false;
    public String TITLE = "TITLE";
    public int SIZE_X = 250;
    public int SIZE_Y = 250;
    public int[] MARGIN_ICON = {10,10,10,10};
    public int[] MARGIN_TEXT = {10,10,10,10};

    public SelectItem(Context context) {
        super(context);
        setOrientation(LinearLayout.VERTICAL);
        belakang = new ImageView(getContext());
        belakang.setId(generateViewId());
        bawah = new ImageView(getContext());
        bawah.setId(generateViewId());

        //init
        /*SIZE_X = 150;
        SIZE_Y = 200;
        create();*/
    }

    // TODO ; Setter
    private LinearLayout.LayoutParams paramss(int w, int h){return new LinearLayout.LayoutParams(w,h); }

    public void create(){
        params = paramss(SIZE_X, SIZE_Y);
        setLayoutParams(params);

        //addComponent

        belakang.setImageResource(R.drawable.icn_bg);
        belakang.setScaleType(ImageView.ScaleType.CENTER_CROP);
        belakang.setBackgroundColor(Color.TRANSPARENT);
        belakangParams = paramss(SIZE_X, SIZE_Y);
        belakang.setLayoutParams(belakangParams);

        addView(belakang);



        int bawahX = SIZE_X/2;
        int bawahY = 10;
        bawahParams = paramss(bawahX, bawahY);
        bawah.setLayoutParams(bawahParams);
        bawah.setBackgroundColor(Color.TRANSPARENT);
        bawah.setImageResource(R.drawable.icn_dsbl_b);
        bawahParams.setMargins(SIZE_X/4,-bawahY - 2,SIZE_X/4,0);

        addView(bawah);
    }

    public void setLebarDanTinggi(int w, int h){
        params = paramss(w, h);
        setLayoutParams(params);

    }

    public void selectBawahResource(int re){
        bawah.setImageResource(re);
        Drawable d =bawah.getDrawable();

        if (d instanceof AnimatedVectorDrawableCompat){
            AnimatedVectorDrawableCompat avd = (AnimatedVectorDrawableCompat) d;
            avd.start();
        }else if(d instanceof AnimatedVectorDrawable){
            AnimatedVectorDrawable avd = (AnimatedVectorDrawable) d;
            avd.start();
        }
    }

}
