package com.mobile.eseo.neweseoapp;

import android.content.Intent;
import android.app.TabActivity;
import android.os.Bundle;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {

    TabHost TabHostWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign id to Tabhost.
        TabHostWindow = (TabHost) findViewById(android.R.id.tabhost);

        //Creating tab menu.
        TabHost.TabSpec TabMenu1 = TabHostWindow.newTabSpec("First tab");
        TabHost.TabSpec TabMenu2 = TabHostWindow.newTabSpec("Second Tab");
        TabHost.TabSpec TabMenu3 = TabHostWindow.newTabSpec("Third Tab");

        //Setting up tab 1 name.
        TabMenu1.setIndicator(null, getResources().getDrawable(R.drawable.ic_newspaper));
        //Set tab 1 activity to tab 1 menu.
        TabMenu1.setContent(new Intent(this, TabActivity_1.class));

        //Setting up tab 2 name.
        TabMenu2.setIndicator("Tab2");
        //Set tab 3 activity to tab 1 menu.
        TabMenu2.setContent(new Intent(this, TabActivity_2.class));

        //Setting up tab 2 name.
        TabMenu3.setIndicator(null, getResources().getDrawable(R.drawable.ic_menu));
        //Set tab 3 activity to tab 3 menu.
        TabMenu3.setContent(new Intent(this, TabActivity_3.class));

        //Adding tab1, tab2, tab3 to tabhost view.

        TabHostWindow.addTab(TabMenu1);
        TabHostWindow.addTab(TabMenu2);
        TabHostWindow.addTab(TabMenu3);

    }
}