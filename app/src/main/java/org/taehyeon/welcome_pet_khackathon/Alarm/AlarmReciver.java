package org.taehyeon.welcome_pet_khackathon.Alarm;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.taehyeon.welcome_pet_khackathon.R;

public class AlarmReciver extends BroadcastReceiver {

    int count;

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent i = new Intent(context,Destination.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,i,PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("WelcomePet");
        ref.child("UserAccount").child(user.getUid()).child("count").get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (!task.isSuccessful()) {
                            Log.e("firebase", "Error getting data", task.getException());
                        }
                        else {
                            String job = "" + String.valueOf(task.getResult().getValue());
                            count = Integer.parseInt(job);
                            NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"WelcomePet")
                                    .setSmallIcon(R.mipmap.ic_launcher)
                                    .setContentTitle("어서와,반려견은 처음이지?")
                                    .setContentText(count+"번째 타이틀")
                                    .setAutoCancel(true)
                                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                                    .setContentIntent(pendingIntent);
                            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
                            notificationManagerCompat.notify(123,builder.build());
                        }
                    }
                });

//        if(count >= 4) count = 0;
//        else count++;

    }



}
