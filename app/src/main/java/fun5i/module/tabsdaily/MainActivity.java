package fun5i.module.tabsdaily;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import java.util.Date;

import fun5i.module.dailytabsweekly.TabsNICHawkawkawk;
import fun5i.module.dailytabsweekly.function.TanggalBre;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    TanggalBre t;
    TabsNICHawkawkawk tabsNICHawkawkawk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabsNICHawkawkawk = findViewById(R.id.opop);
        //tabsNICHawkawkawk.setFontSize(10); // bugs
        tabsNICHawkawkawk.aturLatar(Color.parseColor("#ff8C8C8C"));
        tabsNICHawkawkawk.onClickItem(new TabsNICHawkawkawk.OnTabSelection() {
            @Override
            public void Hasil(Date date, String day, String month, String years) {
                Log.d(TAG, "position: "+ t.HARI_DALAM_MINGGU[date.getDay()]); // senin ;  if select position is 0 of rows array 0 - 6
                Log.d(TAG, "date: "+date.toString()); //date: Mon Apr 11 04:49:01 EDT 2022
                Log.d(TAG, "day: "+day); // 11
                Log.d(TAG, "month: "+month); //month: Apr
                Log.d(TAG, "years: "+years); //years: 2022
            }
        });
    }

}