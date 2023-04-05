package org.taehyeon.welcome_pet_khackathon.AR;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import org.taehyeon.welcome_pet_khackathon.R;

public class InfoArFragment extends Fragment {

    public Boolean info_ar_checked = false;
    public Boolean info_ar2_checked = false;

    Button btn1;
    RadioGroup radioGroup;
    TextView textView;
    UnityPlayerView unityPlayerView;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        unityPlayerView= (UnityPlayerView) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();

        unityPlayerView = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_info_ar, container, false);

        btn1 = v.findViewById(R.id.button_info_select);
        radioGroup=v.findViewById(R.id.radiogroup_info);
        textView = v.findViewById(R.id.textview_info_waring);

        //프래그먼트 실행되면 버튼 2개 비활성화 하고싶음,
        //btn1.setVisibility(v.INVISIBLE);
//        btn1.setEnabled(false);
//        btn2.setEnabled(false);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.alarm_radioButton_info1)
                {
                    // 오답
                    info_ar_checked = true;
                    info_ar2_checked = false;
                }
                else if(i == R.id.alarm_radioButton_info2)
                {
                    info_ar2_checked = true;
                    info_ar_checked = false;
                }
                else if(i == R.id.alarm_radioButton_info3)
                {
                    info_ar_checked = true;
                    info_ar2_checked = false;
                }
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(unityPlayerView, "select", Toast.LENGTH_SHORT).show();
                if(info_ar_checked == true)
                {
                    textView.setText("오답입니다. 다시 선택하세요");
                }
                else if (info_ar2_checked == true)
                {
                    unityPlayerView.fragmentChange(2);
                }
            }
        });
        return v;
        //return inflater.inflate(R.layout.fragment_info_ar, container, false);
    }
}