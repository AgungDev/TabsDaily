package fun5i.module.dailytabsweekly;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import java.text.DateFormatSymbols;
import java.util.Date;

import fun5i.module.dailytabsweekly.Items.BaseItem;
import fun5i.module.dailytabsweekly.Items.SelectItem;
import fun5i.module.dailytabsweekly.function.TanggalBre;

public class TabsNICHawkawkawk extends RelativeLayout {

    public interface OnTabSelection {
        void Hasil(int kondisi, Date date, int day, int month, int years);
    }

    public static final int KONDISI_DESABLE = 1;
    public static final int KONDISI_ACTIVE = 2;
    public static final int KONDISI_PANDING = 3;
    private static final String[] KONDISI_TEXT = {
            "Yesterday",
            "Today",
            "Tomorrow"
    };

    public String getKondisiToText(int kondisi) {
        return KONDISI_TEXT[kondisi - 1];
    }


    private OnTabSelection onTabSelection;

    public void onClickItem(OnTabSelection interfaces) {
        onTabSelection = interfaces;
    }

    private void setHasil(int kondisi, Date date, int day, int month, int years) {
        onTabSelection.Hasil(kondisi, date, day, month, years);
    }

    private void itemsClick(int i) {

        items[i].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animX = ObjectAnimator.ofFloat(selectItem, "x", items[i].getX());
                animX.start();
                Date currentDate = tanggalBre.getWeek()[i];
                setHasil(
                        getKondisi(i),
                        currentDate,
                        tanggalBre.getTanggalFromDate(currentDate),
                        tanggalBre.getCurrentMonth(),
                        tanggalBre.getCurrentYear());
            }
        });
    }

    private static final String TAG = "TabsNICHawkawkawk";

    private TanggalBre tanggalBre;

    private LinearLayout rootLay;
    private RelativeLayout.LayoutParams selectParams;
    private LinearLayout.LayoutParams rootParams, HHHParams;
    private BaseItem senin, selasa, rabu, kamis, jumat, sabut, minggu;
    private SelectItem selectItem;
    private int ACTIVE_ITEM;
    private int MARGIN_ALL_X;
    private int MARGIN_ALL_Y;
    private int ITEM_SIZE = 120;


    private BaseItem[] items = {
            senin,
            selasa,
            rabu,
            kamis,
            jumat,
            sabut,
            minggu
    };
    private String[] ITEMS_TITLE = {
            "Minggu",
            "Senin",
            "Selasa",
            "Rabu",
            "Kamis",
            "Jumat",
            "Sabtu"
    };

    public int getKondisi(int i) {
        int kon = -1;

        if (i < ACTIVE_ITEM) {
            kon = KONDISI_DESABLE;
        } else if (i == ACTIVE_ITEM) {
            kon = KONDISI_ACTIVE;
        } else {
            kon = KONDISI_PANDING;
        }

        return kon;
    }

    public TabsNICHawkawkawk(Context context) {
        super(context);
    }

    public TabsNICHawkawkawk(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        tanggalBre = new TanggalBre(context);
        initsial(context);
    }

    public void customTabsLayout(Context context, int[] icons, int fontSize, String[] fontColors) {
        //removeView(rootLay);
        removeAllViews();

        ACTIVE_ITEM = tanggalBre.getWeekNumber(); // int 0-6
        setLayoutParams(new RelativeLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
        ));
        rootLay = new LinearLayout(getContext());
        rootLay.setOrientation(LinearLayout.HORIZONTAL);

        rootParams = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
        );
        rootLay.setLayoutParams(rootParams);
        init();
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels;
        setDispalySize(width);

        create(icons, fontSize, fontColors);
    }


    public void initsial(Context context) {
        ACTIVE_ITEM = tanggalBre.getWeekNumber(); // int 0-6
        setLayoutParams(new RelativeLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
        ));
        rootLay = new LinearLayout(getContext());
        rootLay.setOrientation(LinearLayout.HORIZONTAL);

        rootParams = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
        );
        rootLay.setLayoutParams(rootParams);
        init();
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels;
        setDispalySize(width);
        create(null, -1, null); // texting layout
    }

    private void setFontSize(int size) {
        for (int i = 0; i < count(); i++) {
            items[i].setFontSize(size);
        }
    }

    public void aturLatar(int c) {
        setBackgroundColor(c);
    }

    private void create(int[] icons, int fontSize, String[] fontColors) {
        iconShow(true);
        sizeItems(ITEM_SIZE);
        marginItems(
                MARGIN_ALL_X,
                MARGIN_ALL_Y,
                0,
                0
        );

        //Finish
        HHHParams = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
        );
        //propertiySet(R.drawable.icn_dsbl, R.drawable.icn_act, R.drawable.icn_pndg);
        propertiySet(icons, fontSize, fontColors);
        addView(rootLay);
        for (int i = 0; i < count(); i++) {
            rootLay.addView(items[i]);
        }
        rootLay.setGravity(Gravity.CENTER);
        rootLay.setPadding(0, MARGIN_ALL_X + MARGIN_ALL_X, 0, 0);

        for (int i = 0; i < count(); i++) {
            itemsClick(i);
        }

        pointers(); // create pointer
        setCurrentPositionPointer();
    }

    public void propertiySet(int[] icons, int fontSize, String[] fontColors) {
        if (icons == null || icons.length != 3){
            icons = new int[]{R.drawable.icn_dsbl, R.drawable.icn_act, R.drawable.icn_pndg};
        }
        if (fontColors == null || fontColors.length != 3){
            fontColors = new String[] {"#ffB4B4B4", "#ff444444", "#ffFFFFFF"};
        }


        for (int i = 0; i < count(); i++) {
            items[i].TITLE = ITEMS_TITLE[i];
            items[i].create();
            if (fontSize > 1) {
                items[i].setFontSize(fontSize);
            }
            if (i < ACTIVE_ITEM) {
                activeITEM(i, fontColors[0], icons[0]);
            } else if (i == ACTIVE_ITEM) {
                activeITEM(i, fontColors[1], icons[1]);
            } else {
                activeITEM(i, fontColors[2], icons[2]);
            }
            items[i].setLayoutParams(HHHParams);
        }
    }


    private void pointers() {
        selectParams = new RelativeLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
        );

        selectItem = new SelectItem(getContext());
        selectItem.setLayoutParams(selectParams);
        selectItem.setId(generateViewId());

        selectItem.SIZE_X = ITEM_SIZE + MARGIN_ALL_X + MARGIN_ALL_X;
        selectItem.SIZE_Y = ITEM_SIZE + (MARGIN_ALL_Y / 2) +
                (MARGIN_ALL_Y / 2) + (MARGIN_ALL_Y / 2) + (MARGIN_ALL_Y / 2) + (MARGIN_ALL_Y / 2) +
                (MARGIN_ALL_Y / 2) + (MARGIN_ALL_Y / 2) + (MARGIN_ALL_Y / 2) +
                (ITEM_SIZE / MARGIN_ALL_X) + (MARGIN_ALL_Y / 2) + MARGIN_ALL_X + MARGIN_ALL_X +
                (ITEM_SIZE / MARGIN_ALL_X) + (ITEM_SIZE / MARGIN_ALL_X) + MARGIN_ALL_X;


        selectItem.create();

        addView(selectItem);

        selectParams.addRule(RelativeLayout.CENTER_VERTICAL);
    }

    private void setDispalySize(int width) {
        int widthITEM = width / 9;
        this.ITEM_SIZE = widthITEM;
        this.MARGIN_ALL_X = (width - (widthITEM * 8)) / 7; // sudah benar
        this.MARGIN_ALL_Y = (width - (widthITEM * 8)) / 7;
    }


    private void setCurrentPositionPointer() {
        selectItem.post(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator animX = ObjectAnimator.ofFloat(selectItem, "x", items[ACTIVE_ITEM].getX());
                animX.start();
            }
        });
    }

    private void marginItems(int left, int top, int right, int bottom) {
        for (int i = 0; i < count(); i++) {
            items[i].MARGIN_X = left;
            items[i].MARGIN_Y = top;
        }
    }

    private void activeITEM(int position, String color, int Ress) {
        items[position].tv.setTextColor(Color.parseColor(color));
        items[position].iconItem.setImageResource(Ress);
    }

    private void init() {
        for (int i = 0; i < count(); i++) {
            items[i] = new BaseItem(getContext());
            items[i].setId(i + 100);
        }
    }

    public void iconShow(boolean showing) {
        for (int i = 0; i < count(); i++) {
            items[i].SHOW_ICON = showing;
        }
    }

    private void sizeItems(int size) {
        for (int i = 0; i < count(); i++) {
            items[i].BASE_SIZE = size;
        }
    }


    private int count() {
        return 7;
    }

    public String getHari() {
        return TanggalBre.HARI_DALAM_MINGGU[ACTIVE_ITEM];
    }

    public String getHari(int posisiWeek) {
        return TanggalBre.HARI_DALAM_MINGGU[posisiWeek];
    }

    public int getTanggal() {
        return tanggalBre.getTanggalFromDate();
    }

    public int getTanggal(Date hari) {
        return tanggalBre.getTanggalFromDate(hari);
    }

    public int getBulan() {
        return tanggalBre.getCurrentMonth();
    }

    public int getBulan(Date buln) {
        return tanggalBre.getCurrentMonth(buln);
    }

    public String getBulanText() {
        return new DateFormatSymbols().getMonths()[tanggalBre.getCurrentMonth()];
    }

    public String getBulanText(Date buln) {
        return new DateFormatSymbols().getMonths()[tanggalBre.getCurrentMonth(buln)];
    }

    public int getTahun(Date tahn) {
        return tanggalBre.getCurrentYear(tahn);
    }

    public int getTahun() {
        return tanggalBre.getCurrentYear();
    }


}
