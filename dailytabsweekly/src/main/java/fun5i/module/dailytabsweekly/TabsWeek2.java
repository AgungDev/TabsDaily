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
import androidx.constraintlayout.widget.ConstraintLayout;

import java.text.DateFormatSymbols;
import java.util.Date;

import fun5i.module.dailytabsweekly.Items.BaseItem;
import fun5i.module.dailytabsweekly.Items.SelectItem;
import fun5i.module.dailytabsweekly.function.TanggalBre;

public class TabsWeek2 extends ConstraintLayout {
    public interface OnTabSelection {
        void Hasil(int kondisi, Date date, int day, int month, int years);
    }

    private TanggalBre tanggalBre;

    public static final int KONDISI_DESABLE = 1;
    public static final int KONDISI_ACTIVE = 2;
    public static final int KONDISI_PANDING = 3;
    private static final String[] KONDISI_TEXT = { "Disable", "Active", "Pending" };
    private OnTabSelection onTabSelection;
    private SelectItem selectItem;
    private LinearLayout rootLay;
    private RelativeLayout.LayoutParams selectParams;
    private LinearLayout.LayoutParams rootParams, HHHParams;
    private BaseItem[] items = new BaseItem[7];
    private int ACTIVE_ITEM;
    private int MARGIN_ALL_X;
    private int MARGIN_ALL_Y;
    private int ITEM_SIZE= 120;

    public TabsWeek2(Context context) {
        super(context);
        initialize(context);
    }

    public TabsWeek2(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    private void initialize(Context context) {
        tanggalBre = new TanggalBre(context);
        ACTIVE_ITEM = tanggalBre.getWeekNumber();
        setLayoutParams(new RelativeLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
        ));
        rootLay = new LinearLayout(context);
        rootLay.setOrientation(LinearLayout.HORIZONTAL);
        rootParams = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
        );
        rootLay.setLayoutParams(rootParams);
        init();
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels;
        setDisplaySize(width);
        createLayout();

        // Menambahkan item ke dalam root layout
        for (int i = 0; i < count(); i++) {
            rootLay.addView(items[i]);
        }

        // Menambahkan root layout ke dalam komponen
        addView(rootLay);

        // Membuat dan menetapkan pointer
        pointers();

        // Menetapkan posisi pointer saat ini
        setCurrentPositionPointer();
    }



    private void createLayout() {
        // Membuat layout
    }

    private void setHasil(int kondisi, Date date, int day, int month, int years) {
        if (onTabSelection != null)
            onTabSelection.Hasil(kondisi, date, day, month, years);
    }

    private void itemsClick(int i) {
        ObjectAnimator animX = ObjectAnimator.ofFloat(selectItem, "x", items[i].getX());
        animX.start();
        Date currentDate = tanggalBre.getWeek()[i];
        setHasil(getKondisi(i), currentDate, tanggalBre.getTanggalFromDate(currentDate),
                tanggalBre.getCurrentMonth(), tanggalBre.getCurrentYear());
    }

    private int getKondisi(int i) {
        if (i < ACTIVE_ITEM)
            return KONDISI_DESABLE;
        else if (i == ACTIVE_ITEM)
            return KONDISI_ACTIVE;
        else
            return KONDISI_PANDING;
    }

    private void propertiySet() {
        // Mengatur properti
    }

    private void pointers() {
        // Membuat pointer
        selectParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        selectItem = new SelectItem(getContext());
        selectItem.setLayoutParams(selectParams);
        selectItem.setId(generateViewId());

        selectItem.SIZE_X = ITEM_SIZE+MARGIN_ALL_X+MARGIN_ALL_X;
        selectItem.SIZE_Y = ITEM_SIZE+(MARGIN_ALL_Y/2)+
                (MARGIN_ALL_Y/2)+(MARGIN_ALL_Y/2)+(MARGIN_ALL_Y/2)+(MARGIN_ALL_Y/2)+
                (MARGIN_ALL_Y/2)+(MARGIN_ALL_Y/2)+(MARGIN_ALL_Y/2)+
                (ITEM_SIZE/MARGIN_ALL_X)+(MARGIN_ALL_Y/2)+MARGIN_ALL_X+MARGIN_ALL_X+
                (ITEM_SIZE/MARGIN_ALL_X)+(ITEM_SIZE/MARGIN_ALL_X)+MARGIN_ALL_X;


        selectItem.create();

        addView(selectItem);

        selectParams.addRule(RelativeLayout.CENTER_VERTICAL);
    }

    private void setDisplaySize(int width) {
        // Mengatur ukuran layar
    }

    private void setCurrentPositionPointer() {
        // Mengatur posisi pointer saat ini
    }

    private void init() {
        // Inisialisasi
    }

    private void activeITEM(int position, String color, int Ress) {
        // Mengatur item aktif
    }

    private void iconShow(boolean showing) {
        // Menampilkan ikon
    }

    private void sizeItems(int size) {
        // Mengatur ukuran item
    }

    private int count() {
        return 7;
    }

    public String getHari() {
        return TanggalBre.HARI_DALAM_MINGGU[ACTIVE_ITEM];
    }

    // Metode getter lainnya

    public void setIconColor(int position, String color) {
        if (position >= 0 && position < items.length) {
            items[position].tv.setTextColor(Color.parseColor(color));
        }
    }

    public void setFontSize(int size) {
        for (BaseItem item : items) {
            item.setFontSize(size);
        }
    }
}
