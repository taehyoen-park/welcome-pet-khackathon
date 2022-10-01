package org.taehyeon.welcome_pet_khackathon.Experience;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.taehyeon.welcome_pet_khackathon.R;


public class experience_Fragment2 extends Fragment {

    private View view;
    private TextView textView_exp;
    private TextView textView;
    private String name;
    private int num;
    Button button_ar;
    Button button_exp;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        view = inflater.inflate(R.layout.fragment_experience_2, container, false);
        textView_exp = view.findViewById(R.id.textView_exp);

        button_ar = view.findViewById(R.id.button_ar);
        button_exp = view.findViewById(R.id.button_exp);
        textView = view.findViewById(R.id.textView_);

        if(getArguments()!=null)
        {
            name = getArguments().getString("name");
            num = getArguments().getInt("index");
            textView_exp.setText(name);
            Experience_Index(num);
            //Toast.makeText(getContext(),num+"값이 넘어옴", Toast.LENGTH_SHORT).show();
        }

        return view;
    }


    // 각 문제마다 인덱스 넘겨받아서 버튼 이벤트 다르게 띄우기
    public String Experience_Index(int input) {
        switch (input)
        {
            case 0:
                button_ar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getContext(),num+"번째 AR로 보기",Toast.LENGTH_SHORT).show();
                    }
                });

                button_exp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Toast.makeText(getContext(),num+"번째 설명 바로 보기",Toast.LENGTH_SHORT).show();
                        textView.setText("");
                    }
                });
                break;

            case 1:
                button_ar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getContext(),num+"번째 AR로 보기",Toast.LENGTH_SHORT).show();
                    }
                });

                button_exp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getContext(),num+"번째 설명 바로 보기",Toast.LENGTH_SHORT).show();
                    }
                });
                break;

            default:
                button_ar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getContext(),num+"번째 AR로 보기",Toast.LENGTH_SHORT).show();
                    }
                });

                button_exp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getContext(),num+"번째 설명 바로 보기",Toast.LENGTH_SHORT).show();
                    }
                });
                break;

        }

        return "okay";
    }

}