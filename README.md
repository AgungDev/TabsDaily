# TabsDaily
![alt hayuuk](https://i.ibb.co/ggDZmcZ/ezgif-com-gif-maker.gif)


### Gridle Setup
```java
pluginManagement {
    repositories {
    ...
        maven { url 'https://jitpack.io' }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
    ...
        maven { url 'https://jitpack.io' }
    }
}
dependencies {
      // check version releases in https://jitpack.io/#AgungDev/TabsDaily 
      implementation 'com.github.AgungDev:TabsDaily:$VERSION_RELEASES'
}
```

### XML
```xml
<fun5i.module.dailytabsweekly.TabsNICHawkawkawk
    android:id="@+id/opop"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"/>
```

### JAVA Implementation
Masih Banyak bug Modul tanggal, static layout, font bla bla bla, perbaiki lagi sebelum lupa
```java
TabsNICHawkawkawk tabsNICHawkawkawk = findViewById(R.id.opop);
setText(
        tabsNICHawkawkawk.getHari(),
        tabsNICHawkawkawk.getTanggal(),
        tabsNICHawkawkawk.getBulanText(),
        tabsNICHawkawkawk.getKondisiToText(tabsNICHawkawkawk.KONDISI_ACTIVE)
);
//tabsNICHawkawkawk.setFontSize(10); // bugs
tabsNICHawkawkawk.aturLatar(Color.parseColor("#ff8C8C8C"));
tabsNICHawkawkawk.onClickItem(new TabsNICHawkawkawk.OnTabSelection() {
    @Override
    public void Hasil(int kondisi, Date date, int day, int month, int years) {
        // senin ;  if select position is 0 of rows array 0 - 6
        setText(
                tabsNICHawkawkawk.getHari(date.getDay()),
                tabsNICHawkawkawk.getTanggal(date),
                tabsNICHawkawkawk.getBulanText(date),
                tabsNICHawkawkawk.getKondisiToText(kondisi)

        );
    }
});
```
