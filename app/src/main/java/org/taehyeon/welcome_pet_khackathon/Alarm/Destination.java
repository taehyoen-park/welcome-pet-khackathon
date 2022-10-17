package org.taehyeon.welcome_pet_khackathon.Alarm;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.taehyeon.welcome_pet_khackathon.Home.progress_Fragment;
import org.taehyeon.welcome_pet_khackathon.MainActivity;
import org.taehyeon.welcome_pet_khackathon.R;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Destination extends AppCompatActivity {

    progress_Fragment progress_fragment = new progress_Fragment();
    public Boolean ar_checked = false;
    public int increase_val = 0;

    String[] question = {
        "당신의 반려견이 아파서 병원에 갔습니다. 총 비용이 64만원 이라면?", // death
        "반려견의 사료가 다 떨어졌습니다. 사료 하나의 가격은 평균 15,000원 입니다.", // injured
        "당신의 반려견이 산책을 나갈 시간입니다. 산책으로 스트레스를 풀어줘야 합니다.", // walk
        "당신의 장시간 외출로 인해 강아지가 많은 외로움을 겪고 있습니다.", // sleep
        "일을 끝나고 집에 도착하니 당신의 반려견이 집을 어질러 놓았습니다. " // default (None)
    };

    String[][] answer = {
            {"충분히 지불할 수 있어요!","잘 모르겠어요..","부담이 되고, 일상생활에 지장이 가요."},
            {"충분히 지불할 수 있어요!","잘 모르겠어요..","부담이 되고, 일상생활에 지장이 가요."},
            {"지금 반려견을 산책 시킬 수 있어요!","지금은 힘들지만 오늘 안에 가능해요.","감당하기 힘들거 같아요.."},
            {"지금 당장 집에 들어가야겠어요!","지금은 힘들고 이따가 돌볼래요.","생각치 못한 일이에요. 돌보기 힘들 것 같아요.."},
            {"지금 해결할 수 있어요!","지금은 너무 힘들어서 나중에 치울래요.","생각치 못한 일이에요. 감당하기 버거워요.."}
    };

    int[] imgs = {R.drawable.sick_dog, R.drawable.money_dog, R.drawable.jogging_dog,R.drawable.lonely_dog, R.drawable.messy_dog};



    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("WelcomePet");
    static  int c = 0;

    String str = question[c];
    String an1 = answer[c][0];
    String an2 = answer[c][1];
    String an3 = answer[c][2];
    RadioButton rb1,rb2,rb3;
    RadioGroup group;
    TextView qus;
    Button ch_btn;
    ImageView alarm_img;
    ImageButton imageButton_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);

        ch_btn = findViewById(R.id.button_alarm_Click);
        TextView text_time = findViewById(R.id.text_now_time);
        rb1 = findViewById(R.id.alarm_radioButton1);
        rb2 = findViewById(R.id.alarm_radioButton2);
        rb3 = findViewById(R.id.alarm_radioButton3);
        group = findViewById(R.id.alarm_radiogroup);
        qus = (TextView) findViewById(R.id.qus);
        alarm_img = findViewById(R.id.imageView_alarm);
        imageButton_home = findViewById(R.id.imageButton_home);

        qus.setText(str);
        rb1.setText(an1);
        rb2.setText(an2);
        rb3.setText(an3);

        text_time.setText("현재 시각 : " +getTime());

        imageButton_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // 라디오 버튼 선택시 실행
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedID) {
                if (checkedID == R.id.alarm_radioButton1)
                {
                    // 첫번째 : 지금 바로 해결할 수 있어요. (AR실행!) 진척도 많이 올라감.
                    ar_checked = true;
                    increase_val = 20;

                }
                else if(checkedID == R.id.alarm_radioButton2)
                {
                    // 두번째 : 가족/ 친구가 해결 가능해요. (AR실행 X) 진척도 조금 올라감.
                    ar_checked = false;
                    increase_val = 10;

                }
                else if(checkedID == R.id.alarm_radioButton3)
                {
                    // 세번째 : 지금 해결 불가능해요. (AR실행 X) 진척도 안올라감.
                    ar_checked = false;
                    increase_val = 5;

                }
            }
        });

        ch_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ar_checked == true)
                {
                    Intent intent = new Intent(Destination.this, MainActivity.class);
                    // 메인이 아니라 유니티로 가야함. 알람 종류에 따라 다름.
                    startActivity(intent);
                }
                else
                {
                    // 번들로 넘겨야햐
                    Bundle bundle = new Bundle();
                    bundle.putInt("val",increase_val);
                    progress_fragment.setArguments(bundle);

                    Intent intent11 = new Intent(Destination.this,MainActivity.class);
                    //intent11.putInt("val",increase_val);
                    startActivity(intent11);
                    finish();
                }


            }
        });



        if(c >= 4) c = 0;
        else c++;
    }

    private String getTime() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd hh:mm");
        String getTime = dateFormat.format(date);

        return getTime;
    }
}