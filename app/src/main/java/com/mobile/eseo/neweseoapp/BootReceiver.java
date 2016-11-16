package com.mobile.eseo.neweseoapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.mobile.eseo.neweseoapp.service.DeadlineService;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;


/*
* This class allows automatically to reprogram alarm for the notifications after a restarting of the device.
* */
public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean activeNotif = true;

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 7);

        AlarmManager am = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        Intent i = new Intent(context, DeadlineService.class);
        PendingIntent pi = PendingIntent.getService(context, 0, i, 0);
        am.cancel(pi);
        if (activeNotif) {
            am.setRepeating(AlarmManager.RTC_WAKEUP,
                    calendar.getTimeInMillis(),
                    24*60*1000, pi);
        }
    }

}
