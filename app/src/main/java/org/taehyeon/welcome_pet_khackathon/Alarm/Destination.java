package org.taehyeon.welcome_pet_khackathon.Alarm;

import androidx.appcompat.app.AppCompatActivity;

import org.taehyeon.welcome_pet_khackathon.MainActivity;
import org.taehyeon.welcome_pet_khackathon.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Destination extends AppCompatActivity {

    static int c = 0;

    String[] question = {
        "당신의 반려견이 아파서 병원에 갔습니다. 총 비용이 64만원 이라면?", // 강아지 죽는 모습
        "반려견의 사료가 다 떨어졌습니다. 사료 하나의 가격은 평균 15,000원 입니다.",
        "당신의 반려견이 산책을 나갈 시간입니다. 산책으로 스트레스를 풀어줘야 합니다.", //
        //"당신의 반려견이 \"앉아\", \"엎드려\"와 같은 훈련을 해야되는 시간입니다.", // 강아지가 무언가 무는 모습
        //"당신의 반려견이 씻어야 하는 시간입니다.",
        //"당신의 반려견이 털갈이 시즌입니다 털관리를 해야 되는 시간입니다.",
        "당신의 장시간 외출로 인해 강아지가 많은 외로움을 겪고 있습니다.", // 자는 모습 o
        "일을 끝나고 집에 도착하니 당신의 반려견이 집을 어질러 놓았습니다. " // 집을 어질러 놓음 o
    };

    String[][] answer = {
            {"충분히 지불할 수 있어요!","잘 모르겠어요..","부담이 되고, 일상생활에 지장이 가요."},
            {"충분히 지불할 수 있어요!","잘 모르겠어요..","부담이 되고, 일상생활에 지장이 가요."},
            {"지금 반려견을 훈련 시킬 수 있어요!","지금은 힘들지만 오늘 안에 가능해요.","감당하기 힘들거 같아요.."},
            //{"지금 반려견을 목욕 시킬 수 있어요!","지금은 힘들지만 오늘 안에 가능해요.","감당하기 힘들거 같아요.."},
            //{"꾸준히 털관리할 수 있을거 같아요","힘들긴 하지만 못 할 정도는 아니에요","감당하기 힘들거 같아요"},
            //{"앞으로 충분히 시간 조정이 가능해요","아주 가끔 장시간 외출해서 하루정돈 괜찮아요","앞으로도 이럴 거 같아요, 너무 힘들어요"},
            {"지금 당장 집에 들어가야겠어요!","지금은 힘들고 이따가 돌볼래요.","생각치 못한 일이에요. 돌보기 힘들 것 같아요.."},
            {"지금 해결할 수 있어요!","지금은 너무 힘들어서 나중에 치울래요.","생각치 못한 일이에요. 감당하기 버거워요.."}
    };

    String str = question[c];
    String an1 = answer[c][0];
    String an2 = answer[c][1];
    String an3 = answer[c][2];
    CheckBox ch1,ch2,ch3;
    TextView ans1,ans2,ans3;
    TextView qus;
    Button ch_btn;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);

        progress = findViewById(R.id.progressBar);
        ch_btn = findViewById(R.id.button_check);
        ch1 = findViewById(R.id.check1);
        ch2 = findViewById(R.id.check2);
        ch3 = findViewById(R.id.check3);
        ans1 = (TextView) findViewById(R.id.ans1);
        ans2 = (TextView) findViewById(R.id.ans2);
        ans3 = (TextView) findViewById(R.id.ans3);
        qus = (TextView) findViewById(R.id.qus);

        qus.setText(str);
        ans1.setText(an1);
        ans2.setText(an2);
        ans3.setText(an3);

        ch_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ch1.isChecked() || ch2.isChecked() || ch3.isChecked())
                {
                    progress.incrementProgressBy(1);
                    Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent2);
                    finish();
                }
            }
        });

        if(c >= 8) c = 0;
        else c++;

    }
}