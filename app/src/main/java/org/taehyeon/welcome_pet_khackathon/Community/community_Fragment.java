package org.taehyeon.welcome_pet_khackathon.Community;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import org.taehyeon.welcome_pet_khackathon.Experience.Experience;
import org.taehyeon.welcome_pet_khackathon.R;


public class community_Fragment extends Fragment {

    ImageButton button_add;
    WritePostFragment writePostFragment = new WritePostFragment();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_community_, container, false);

        button_add = v.findViewById(R.id.Button_add);
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();

                Bundle bundle = new Bundle();
                FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
                writePostFragment.setArguments(bundle);
                transaction.replace(R.id.frameLayout_main, writePostFragment);
                transaction.commit();

            }
        });
























        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}