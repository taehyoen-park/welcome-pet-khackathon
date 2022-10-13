package org.taehyeon.welcome_pet_khackathon.Alarm;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import org.taehyeon.welcome_pet_khackathon.R;

public class AlarmReciver extends BroadcastReceiver {

    static int count = 0;
    @Override
    public void onReceive(Context context, Intent intent) {

        Intent i = new Intent(context,Destination.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,i,PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"WelcomePet")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("어서와,반려견은 처음이지?")
                .setContentText(count+"번째 타이틀")
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent);

        if(count > 8) count = 0;
        else count++;
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(123,builder.build());
    }
}
