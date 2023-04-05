package org.taehyeon.welcome_pet_khackathon.AR;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import org.taehyeon.welcome_pet_khackathon.R;

public class InfoAr2Fragment extends Fragment {

    Button button;
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
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_info_ar2, null);


        return v;
    }
}