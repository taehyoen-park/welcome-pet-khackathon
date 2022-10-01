package org.taehyeon.welcome_pet_khackathon.Start_survey;

import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.taehyeon.welcome_pet_khackathon.R;

public class survey extends AppCompatActivity {

    int situation[] = {0,1,2,3,4}; // cost,time_investment,emergency_situation,Management,limit_free;
    int value[] = {0,0,0,0,0};
    int result[] = {2,2,2,1,1};
    Button nextbtn;
    SeekBar seekbar1;
    SeekBar seekbar2;
    SeekBar seekbar3;
    SeekBar seekbar4;
    SeekBar seekbar5;
    SeekBar seekbar6;
    SeekBar seekbar7;
    SeekBar seekbar8;
    SeekBar seekbar9;
    SeekBar seekbar10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);


        seekbar1 = findViewById(R.id.seekBar1);
        seekbar2 = findViewById(R.id.seekBar2);
        seekbar3 = findViewById(R.id.seekBar3);
        seekbar4 = findViewById(R.id.seekBar4);
        seekbar5 = findViewById(R.id.seekBar5);
        seekbar6 = findViewById(R.id.seekBar6);
        seekbar7 = findViewById(R.id.seekBar7);
        seekbar8 = findViewById(R.id.seekBar8);
        seekbar9 = findViewById(R.id.seekBar9);
        seekbar10 = findViewById(R.id.seekBar10);

        seekbar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                value[0] = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        seekbar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                value[1] = i;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        seekbar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                value[2] = i;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        seekbar4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                value[3] = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        seekbar5.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                value[4] = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        seekbar6.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        seekbar7.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        seekbar8.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        seekbar9.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        seekbar10.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        nextbtn = findViewById(R.id.Next);
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult();
                Intent intent = new Intent(survey.this,survey2.class);
                intent.putExtra("result0",result[0]);
                intent.putExtra("result1",result[1]);
                intent.putExtra("result2",result[2]);
                intent.putExtra("result3",result[3]);
                intent.putExtra("result4",result[4]);
                finish();
                startActivity(intent);
            }
        });
    }

    public void setResult()
    {
        int sum = 0;
        for(int i = 0; i < 5; i++)
            sum += value[i];

        for(int i = 0; i < 5; i++){
            for(int j = i+1; j < 4; j++){
                if(value[situation[i]] < value[situation[j]])
                {
                    int temp = value[situation[i]];
                    value[situation[i]] = value[situation[j]];
                    value[situation[j]] = temp;

                    temp = situation[i];
                    situation[i] = situation[j];
                    situation[j] = temp;
                }
                else continue;
            }
        }

        if(sum > 90) return;
        else if(sum > 80) result[situation[0]]++;
        else if(sum > 70)
        {
            for(int i = 0; i < 2; i++)
                result[situation[i]]++;
        }
        else if(sum > 60)
        {
            for(int i = 0; i < 3; i++)
                result[situation[i]]++;
        }
        else if(sum > 50)
        {
            for(int i = 0; i < 4; i++)
                result[situation[i]]++;
        }
        else
        {
            for(int i = 0; i < 5; i++)
                result[situation[i]]++;
        }
    }
}