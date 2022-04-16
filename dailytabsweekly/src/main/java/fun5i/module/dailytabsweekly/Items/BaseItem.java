package fun5i.module.dailytabsweekly.Items;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import fun5i.module.dailytabsweekly.R;

public class BaseItem extends LinearLayout {

    public static final String TAG = "BaseItem";

    // TODO: Component
    private LinearLayout.LayoutParams baseParams, tvParams, iconParams;
    public TextView tv;
    public ImageView iconItem;


    // TODO: Component Value
    public boolean SHOW_ICON = false;
    public String TITLE = "TITLE";
    private String TEXT_COLOR = "#FF000000";
    public int BASE_SIZE =100;
    public int MARGIN_X  = 11;
    public int MARGIN_Y = 4;

    private int TINGGGI;
    private int FONT_HEIGHT;


    public BaseItem(Context context) {
        super(context);
        setOrientation(LinearLayout.VERTICAL);
        tv = new TextView(getContext());
        iconItem = new ImageView(getContext());

        //Example init
        /*SHOW_ICON = true;
        BASE_SIZE = 200;
        TITLE = "HARI";
        create();*/

    }

    // TODO: Setter
    private LinearLayout.LayoutParams paramss(int w, int h){return new LinearLayout.LayoutParams(w,h); }


    // TODO: INIT
    public void create(){
        baseParams = paramss(BASE_SIZE, BASE_SIZE);
        setLayoutParams(baseParams); //size
        if (SHOW_ICON){

            int icnX = LayoutParams.MATCH_PARENT;
            int icnY = BASE_SIZE;
            iconParams = paramss(icnX, icnY);
            iconItem.setLayoutParams(iconParams);
            iconItem.setImageResource(R.drawable.icn_dsbl);
            iconParams.setMargins(
                    MARGIN_X,
                    MARGIN_Y,
                    MARGIN_X,
                    MARGIN_Y
            );

        }

        int tvX = BASE_SIZE;
        int tvY = LayoutParams.WRAP_CONTENT;
        tvParams = paramss(tvX,tvY);
        tv.setText(TITLE);
        tv.setTextColor(Color.parseColor(TEXT_COLOR));
        tv.setLayoutParams(tvParams);
        tv.setTypeface(Typeface.DEFAULT_BOLD);
        tv.setTextSize(BASE_SIZE/MARGIN_X);
        tvParams.setMargins(
                MARGIN_X,
                MARGIN_Y, //top
                MARGIN_X,
                MARGIN_Y // bottom
        );
        tv.setGravity(Gravity.CENTER);
        //baseParams.gravity = Gravity.CENTER;
        tv.post(new Runnable() {
            @Override
            public void run() {
                FONT_HEIGHT = tv.getHeight();
            }
        });

        addView(iconItem);
        addView(tv);
    }

    public void setFontSize(int size){
        tv.setTextSize(size);
    }

    public int getTinggi(){
        TINGGGI = (MARGIN_Y*4)+BASE_SIZE+FONT_HEIGHT+(BASE_SIZE/MARGIN_X);
        return TINGGGI;
    }


}
