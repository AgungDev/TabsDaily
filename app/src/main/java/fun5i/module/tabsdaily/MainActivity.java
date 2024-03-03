package fun5i.module.tabsdaily;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import fun5i.module.dailytabsweekly.TabsNICHawkawkawk;

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
        versi200();
    }

    private void versi200(){
        int[] icons = new int[] {R.drawable.yesterday_icn, R.drawable.today_icn, R.drawable.tomorrow_icn};
        String[] fontColors = new String[] {"#FF018786", "#F40303", "#FF03DAC5"};
        tabs.aturLatar(Color.argb(0, 0, 0, 0)); // transparant
        tabs.customTabsLayout(this , RelativeLayout.CENTER_HORIZONTAL, icons, 10, fontColors);
        thn.setOnClickListener((View view) -> {
            String kondisi = tabs.getKondisiToText(tabs.getKondisi());
            Toast.makeText(MainActivity.this, kondisi, Toast.LENGTH_SHORT).show();
        });
    }

    private void versi103(){
        setText(
                tabs.getHari(),
                tabs.getTanggal(),
                tabs.getBulanText(),
                tabs.getKondisiToText(tabs.KONDISI_TODAY)
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