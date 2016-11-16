package com.mobile.eseo.neweseoapp.service;



import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.PowerManager;

import com.mobile.eseo.neweseoapp.MainActivity;
import com.mobile.eseo.neweseoapp.R;
import com.mobile.eseo.neweseoapp.bdd.DeadLineBDD;
import com.mobile.eseo.neweseoapp.model.DeadLine;

import java.text.ParseException;
import java.util.ArrayList;

import static android.app.Notification.FLAG_AUTO_CANCEL;


/*This service is called once per day and creates notifications for each deadline corresponding to the 24 hours following.*/
public class DeadlineService extends Service {


    private ArrayList<DeadLine> deadlines = new ArrayList<>();
    private PowerManager.WakeLock mWakeLock;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void handleIntent(Intent intent) {

        PowerManager pm = (PowerManager) getSystemService(POWER_SERVICE);
        mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "My Tag");
        mWakeLock.acquire();


        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        if (!cm.getBackgroundDataSetting()) {
            stopSelf();
            return;
        }


        new PollTask().execute();
    }

    private class PollTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            DeadLineBDD deadlinebdd = new DeadLineBDD(getApplicationContext());
            deadlinebdd.open();
            try {
                deadlines = deadlinebdd.getDeadLineOfDay();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            deadlinebdd.close();
            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            Notification notication;
            int icon = R.drawable.ic_deadlines;
            CharSequence contentText;

            Resources res = getResources();
            String titleText = String.format(res.getString(R.string.title_notification));
            String tickerText = String.format(res.getString(R.string.ticker_notification));

            NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);


            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);



            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 1, intent, 0);

            Notification.Builder builder = new Notification.Builder(getApplicationContext());

            builder.setAutoCancel(true);
            builder.setTicker(tickerText);
            builder.setContentTitle(titleText);
            builder.setSmallIcon(R.drawable.ic_deadlines);
            builder.setContentIntent(pendingIntent);
            builder.setOngoing(true);




            for(int i=0; i<deadlines.size(); i++){
                contentText = (CharSequence) deadlines.get(i).getMotif();
                builder.setContentText(contentText);
                builder.build();

                notication = builder.getNotification();
                notication.flags = FLAG_AUTO_CANCEL;
                notificationManager.notify(100+i*10, notication);
            }

            stopSelf();
        }
    }

    @Override
    public void onStart(Intent intent, int startId) {
        handleIntent(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handleIntent(intent);
        return START_NOT_STICKY;
    }

    public void onDestroy() {
        super.onDestroy();
        mWakeLock.release();
    }
}
