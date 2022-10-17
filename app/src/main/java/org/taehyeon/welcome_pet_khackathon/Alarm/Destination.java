package org.taehyeon.welcome_pet_khackathon.Alarm;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.taehyeon.welcome_pet_khackathon.MainActivity;
import org.taehyeon.welcome_pet_khackathon.R;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

public class Destination extends AppCompatActivity {

    String[] question = {
        "당신의 반려견이 아파서 병원에 갔습니다. 총 비용이 64만원 이라면?", // 강아지 죽는 모습
        "반려견의 사료가 다 떨어졌습니다. 사료 하나의 가격은 평균 15,000원 입니다.",
        "당신의 반려견이 산책을 나갈 시간입니다. 산책으로 스트레스를 풀어줘야 합니다.", //
        "당신의 장시간 외출로 인해 강아지가 많은 외로움을 겪고 있습니다.", // 자는 모습 o
        "일을 끝나고 집에 도착하니 당신의 반려견이 집을 어질러 놓았습니다. " // 집을 어질러 놓음 o
    };

    String[][] answer = {
            {"충분히 지불할 수 있어요!","잘 모르겠어요..","부담이 되고, 일상생활에 지장이 가요."},
            {"충분히 지불할 수 있어요!","잘 모르겠어요..","부담이 되고, 일상생활에 지장이 가요."},
            {"지금 반려견을 훈련 시킬 수 있어요!","지금은 힘들지만 오늘 안에 가능해요.","감당하기 힘들거 같아요.."},
            {"지금 당장 집에 들어가야겠어요!","지금은 힘들고 이따가 돌볼래요.","생각치 못한 일이에요. 돌보기 힘들 것 같아요.."},
            {"지금 해결할 수 있어요!","지금은 너무 힘들어서 나중에 치울래요.","생각치 못한 일이에요. 감당하기 버거워요.."}
    };



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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);

        ch_btn = findViewById(R.id.buttonClick);
        rb1 = findViewById(R.id.alarm_radioButton1);
        rb2 = findViewById(R.id.alarm_radioButton2);
        rb3 = findViewById(R.id.alarm_radioButton3);
        group = findViewById(R.id.alarm_radiogroup);
        qus = (TextView) findViewById(R.id.qus);

        group.setOnCheckedChangeListener(radioGroupButtonChangeListener);
        qus.setText(str);
        rb1.setText(an1);
        rb2.setText(an2);
        rb3.setText(an3);


        ch_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent11 = new Intent(Destination.this,MainActivity.class);
                intent11.putExtra("val","increse");
                startActivity(intent11);
                finish();
//                if(rb1.isChecked() == true || rb2.isChecked() == true || rb3.isChecked() == true) {
//
//
//                }
//                else{
//                    Toast.makeText(getApplicationContext(),"1개 이상 선택해주세요",Toast.LENGTH_SHORT).show();
//                }
            }
        });


        if(c >= 4) c = 0;
        else c++;
    }

    RadioGroup.OnCheckedChangeListener radioGroupButtonChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
            if(i == R.id.alarm_radioButton1){

            }
            else if(i == R.id.alarm_radioButton2){

            }
            else{

            }
        }
    };
}