package org.taehyeon.welcome_pet_khackathon.Home;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ComponentActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.taehyeon.welcome_pet_khackathon.Alarm.Destination;
import org.taehyeon.welcome_pet_khackathon.R;

import java.util.HashMap;

public class progress_Fragment extends Fragment {

    static int p = 0;
    ProgressBar progress;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_progress, container, false);
        progress = (ProgressBar) v.findViewById(R.id.progressBar);

        Bundle bundle = getArguments();
        if(p == 4)
        {
            HashMap<String,Object> hashMap = new HashMap<>();
            hashMap.put("check","chx");

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("WelcomePet").child("UserAccount");
            ref.child(user.getUid()).updateChildren(hashMap)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });
        }
        else
        {
            if(bundle == null)
                continueProgress();
            else
            {
                String a = bundle.getString("val");
                if(a != null){
                    increseProgress();
                }
                else{
                    continueProgress();
                }
            }
        }


        return v;
    }

    private void continueProgress() {
        progress.setProgress(p);
    }

    private void increseProgress() {
        p++;
        progress.setProgress(p);
    }


}