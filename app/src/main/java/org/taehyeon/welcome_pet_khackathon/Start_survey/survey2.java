package org.taehyeon.welcome_pet_khackathon.Start_survey;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.taehyeon.welcome_pet_khackathon.Alarm.AlarmReciver;
import org.taehyeon.welcome_pet_khackathon.Auth.Login;
import org.taehyeon.welcome_pet_khackathon.Experience.experience_Fragment;
import org.taehyeon.welcome_pet_khackathon.Home.home_Fragment;
import org.taehyeon.welcome_pet_khackathon.MainActivity;
import org.taehyeon.welcome_pet_khackathon.R;
import org.taehyeon.welcome_pet_khackathon.Shop.shop_Fragment;
import org.taehyeon.welcome_pet_khackathon.Userinfo.userinfo_Fragment;
import org.taehyeon.welcome_pet_khackathon.databinding.ActivitySurvey2Binding;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class survey2 extends AppCompatActivity {

    private MaterialTimePicker picker;
    private Calendar calendar;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private ActivitySurvey2Binding binding;

    FragmentManager fragmentManager = getSupportFragmentManager();
    home_Fragment fragment_home = new home_Fragment();


    TextView spname,dogt;
    ImageView dogim;

    int rv;

    String[] strspname = {
            "  추천 종:비글","  추천 종:도베르만","  추천 종:골든리트리버","  추천 종:시바견","  추천 종:포메라니안","  추천 종:비숑",
            "  추천 종:말티즈","  추천 종:시츄","  추천 종:요크셔테리어","  추천 종:시베리안 허스키"
    };

    String beag = "비글은 다른 반려동물이나 어린이들과 잘 어울리는 견종으로 널리 알려져 있다." +
            " 애착이 많은 활발하고 낙천적인 견종이다. ";

    String york = "요크셔 테리어는 활동적이고, 혈기왕성하며 권위적인 성격을 귀여운 소형견이다. " +
            "요크셔 테리어는 잘 짖는 습성이 있지만, 훈련을 고쳐줄 수 있다.";

    String dober = "도베르만은 사람을 좋아하고 정이 많아서 교감과 훈련을 잘 받을 경우 " +
            "사람들과 잘 어울릴 수 있다고 한다. 도베르만은 한 주인만을 섬기는 걸로 유명하다. ";

    String gold = "골든 리트리버는 매우 차분하고 지능적이며 애정 어린 견종이다." +
            "장난스럽고 다른 반려동물이나 낯선 사람들과 잘 어울리는 경향이 있다.";

    String sibar = "시바견은 활발하고 명랑하면서도 독립적인 성격을 가지고 있습니다. " +
            "특히 낯선 사람이나 다른 동물들을 낯설어하거나 공격적인 모습을 보이기도 합니다. 하지만 반려인에게는 충성스럽고 가정에서는 살가운 편입니다.";

    String pome = "포메라니안은 보통 활기차고 친밀한 작은 개다. " +
            "자기자신이 작은 체구라는 점을 인지하지 못하는 듯하며 간혹 큰 개를 공격하거나 조금 짖을 수 도 있다!";

    String bichon = "비숑은 전체적으로 좋은 반려동물이라는 의견이 많으며 온화하면서도 장난 끼가 많다. " +
            "비숑은 다른 반려동물들과도 잘 지낸다. 아이들과도 보통 잘 지낸다고 보여진다. ";

    String malt = "말티즈는 얌전하고 사랑스럽고 총명하고 반응이 좋으며 믿음이 강하다. " +
            "가정용 반려견으로 좋은 말티즈는 활기차고 놀기를 좋아하며 새로운 훈련을 받는 것을 즐긴다.";

    String schu = "사냥을 목적으로 탄생한 견종과 달리 가정견으로 탄생한 강아지라 공격성이 낮고,"+
            "사람자체를 좋아해서 처음 반려견을 접하시는 분에게 알맞은 견종이다";

    String husky = "시베리안 허스키는 대표적인 북부지방 견종입니다. 똑똑하지만 다소" +
            "독립심이 강하고 고집이 셉니다. 사람들과 함께 번창해 왔지만, 어릴 때부터 바로 확고하고 온화한 훈련이 필요합니다. ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySurvey2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        createNotificationChannel();

        Intent intent = getIntent();
        rv = intent.getIntExtra("dogresult",rv);
        dogt = findViewById(R.id.dogtext);
        spname = findViewById(R.id.spname);
        dogim = findViewById(R.id.dogim);

        spname.setText(strspname[rv]);

        if(rv == 0) {
            dogim.setImageResource(R.drawable.beagle);
            dogt.setText(beag);
        }

        if(rv == 1) {
            dogim.setImageResource(R.drawable.doberman);
            dogt.setText(dober);
        }

        if(rv == 2) {
            dogim.setImageResource(R.drawable.golden);
            dogt.setText(gold);
        }

        if(rv == 3) {
            dogim.setImageResource(R.drawable.shiba);
            dogt.setText(sibar);
        }

        if(rv == 4) {
            dogim.setImageResource(R.drawable.pomeranian);
            dogt.setText(pome);
        }

        if(rv == 5) {
            dogim.setImageResource(R.drawable.bichon);
            dogt.setText(bichon);
        }

        if(rv == 6) {
            dogim.setImageResource(R.drawable.maltese);
            dogt.setText(malt);
        }

        if(rv == 7) {
            dogim.setImageResource(R.drawable.chu);
            dogt.setText(schu);
        }

        if(rv == 8) {
            dogim.setImageResource(R.drawable.york);
            dogt.setText(york);
        }

        if(rv == 9) {
            dogim.setImageResource(R.drawable.huskey);
            dogt.setText(husky);
        }

//        binding.selectTimeBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //showTimePicker();
//            }
//        });

        binding.setAlarmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlarm();
            }
        });

        binding.cancelAlarmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAlarm();
            }
        });
    }

    private void cancelAlarm() {
        Intent intent = new Intent(this,AlarmReciver.class);
        pendingIntent = PendingIntent.getBroadcast(this,0,intent,PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);
        if (alarmManager == null){
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        }
        alarmManager.cancel(pendingIntent);
        Toast.makeText(this, "Alarm Cancelled", Toast.LENGTH_SHORT).show();

    }


    private void setAlarm()
    {

        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent4 = new Intent(survey2.this,AlarmReciver.class);
        pendingIntent = PendingIntent.getBroadcast(survey2.this,0,intent4,
                PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,(System.currentTimeMillis()+5000),
                5000/*AlarmManager.INTERVAL_DAY*/,pendingIntent);

        Toast.makeText(this, "Alarm set Successfully", Toast.LENGTH_SHORT).show();
        Intent intent2 = new Intent(survey2.this, MainActivity.class);

        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("check","ch");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("WelcomePet").child("UserAccount");
        ref.child(user.getUid()).updateChildren(hashMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                //Toast.makeText(getApplicationContext(), "성공...", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //Toast.makeText(getApplicationContext(), "실패...", Toast.LENGTH_SHORT).show();
            }
        });
        startActivity(intent2);
    }


    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "어서와,반려견은 처음이지?";
            String description = "Channel For Alarm Manager";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("WelcomePet",name,importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
        else
        {
            Toast.makeText(this, "실패", Toast.LENGTH_SHORT).show();
        }
    }
}