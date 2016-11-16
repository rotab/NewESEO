package com.mobile.eseo.neweseoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.eseo.neweseoapp.adaptater.DeadlineArrayAdaptater;
import com.mobile.eseo.neweseoapp.bdd.DeadLineBDD;
import com.mobile.eseo.neweseoapp.model.DeadLine;

import java.text.ParseException;
import java.util.ArrayList;

public class TabActivity_Deadlines extends AppCompatActivity {

    ListView listView;

    private ArrayList<DeadLine> deadlines = new ArrayList<>();
    private Switch updateSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_deadlines);

        DeadLineBDD deadlinebdd = new DeadLineBDD(this);
        deadlinebdd.open();
        try {
            deadlines = deadlinebdd.getDeadLineWithDate();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        deadlinebdd.close();

        updateSwitch = (Switch) findViewById(R.id.notifSwitch);

        updateSwitch.setChecked(false);

        updateSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                //changement dans les préférences

            }
        });


        listView = (ListView) findViewById(R.id.listDeadline);
        ArrayAdapter<DeadLine> adapter = new DeadlineArrayAdaptater(this, 0, deadlines);
        listView.setAdapter(adapter);


    }

}
