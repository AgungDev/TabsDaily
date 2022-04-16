package fun5i.module.tabsdaily;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.DateFormatSymbols;
import java.util.Date;

import fun5i.module.dailytabsweekly.TabsNICHawkawkawk;
import fun5i.module.dailytabsweekly.function.TanggalBre;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private TextView hari, tgl, bln, thn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hari = findViewById(R.id.tv);
        tgl = findViewById(R.id.tgl);
        bln = findViewById(R.id.bln);
        thn = findViewById(R.id.tahun);
        TabsNICHawkawkawk tabsNICHawkawkawk = findViewById(R.id.opop);
        setText(
                tabsNICHawkawkawk.getHari(),
                tabsNICHawkawkawk.getTanggal(),
                tabsNICHawkawkawk.getBulanText(),
                tabsNICHawkawkawk.getTahun()
        );
        //tabsNICHawkawkawk.setFontSize(10); // bugs
        tabsNICHawkawkawk.aturLatar(Color.parseColor("#ff8C8C8C"));
        tabsNICHawkawkawk.onClickItem(new TabsNICHawkawkawk.OnTabSelection() {
            @Override
            public void Hasil(Date date, int day, int month, int years) {
                // senin ;  if select position is 0 of rows array 0 - 6
                //setText(TanggalBre.HARI_DALAM_MINGGU[date.getDay()]);
                Log.d(TAG, "Hasil: "+(date.getDay()-1));
                setText(
                        tabsNICHawkawkawk.getHari(date.getDay()),
                        tabsNICHawkawkawk.getTanggal(date),
                        tabsNICHawkawkawk.getBulanText(date),
                        tabsNICHawkawkawk.getTahun(date)

                );
            }
        });
    }

    private void setText(String value, int t, String b, int th){
        hari.setText(value);
        tgl.setText(String.valueOf(t));
        bln.setText(b);
        thn.setText(String.valueOf(th));
    }

}