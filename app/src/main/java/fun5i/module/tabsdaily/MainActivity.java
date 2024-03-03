package fun5i.module.tabsdaily;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormatSymbols;
import java.util.Date;

import fun5i.module.dailytabsweekly.TabsNICHawkawkawk;
import fun5i.module.dailytabsweekly.TabsWeek2;
import fun5i.module.dailytabsweekly.function.TanggalBre;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView hari, tgl, bln, thn;
    private TabsNICHawkawkawk tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hari = findViewById(R.id.tv);
        tgl = findViewById(R.id.tgl);
        bln = findViewById(R.id.bln);
        thn = findViewById(R.id.tahun);
        tabs = findViewById(R.id.tabs);

        versi103();
        versi104();
    }

    private void versi104(){
        int[] icons = new int[] {R.drawable.yesterday_icn, R.drawable.today_icn, R.drawable.tomorrow_icn};
        String[] fontColors = new String[] {"#FF018786", "#F40303", "#FF03DAC5"};
        tabs.aturLatar(Color.argb(0, 0, 0, 0)); // transparant
        tabs.customTabsLayout(this , icons, 10, fontColors);
    }

    private void versi103(){
        setText(
                tabs.getHari(),
                tabs.getTanggal(),
                tabs.getBulanText(),
                tabs.getKondisiToText(tabs.KONDISI_ACTIVE)
        );
        tabs.aturLatar(Color.parseColor("#ff8C8C8C"));
        tabs.onClickItem(new TabsNICHawkawkawk.OnTabSelection() {
            @Override
            public void Hasil(int kondisi, Date date, int day, int month, int years) {
                // senin ;  if select position is 0 of rows array 0 - 6
                setText(
                        tabs.getHari(date.getDay()),
                        tabs.getTanggal(date),
                        tabs.getBulanText(date),
                        tabs.getKondisiToText(kondisi)

                );
            }
        });
    }

    private void setText(String value, int t, String b, String kondis){
        hari.setText(value);
        tgl.setText(String.valueOf(t));
        bln.setText(b);
        thn.setText(kondis);
    }

}