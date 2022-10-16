package org.taehyeon.welcome_pet_khackathon.Home;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import org.taehyeon.welcome_pet_khackathon.R;

public class progress_Fragment extends Fragment {
    ProgressBar progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_progress, container, false);
        progress = v.findViewById(R.id.progressBar);


        int num = progress.getProgress();
        if(progress.getMax() != num)
        {
            num++;
            progress.setProgress(num);
        }
        else
        {
            progress.setProgress(num);
        }



        return v;
    }
}