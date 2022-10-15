package org.taehyeon.welcome_pet_khackathon.Userinfo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.taehyeon.welcome_pet_khackathon.Auth.UserAccount;
import org.taehyeon.welcome_pet_khackathon.MainActivity;
import org.taehyeon.welcome_pet_khackathon.R;


public class userinfo_Fragment extends Fragment {

    TextView username,userjob,useremail,userphone;
    Button btn_certi,logout_btn;

    private FirebaseAuth Auth = FirebaseAuth.getInstance();
    private FirebaseUser User;
    private DatabaseReference dataRef;
    String name,email,phone,id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_userinfo_, container, false);

        username = v.findViewById(R.id.UserName);
        useremail = v.findViewById(R.id.useremail);
        userjob = v.findViewById(R.id.userjob);
        userphone = v.findViewById(R.id.userphone);
        btn_certi = v.findViewById(R.id.certified);
        logout_btn = v.findViewById(R.id.logout_btn);

        Auth = FirebaseAuth.getInstance();

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Auth.signOut();
            }
        });

       // UserAccount account = new UserAccount();
        User = Auth.getCurrentUser();
        dataRef = FirebaseDatabase.getInstance().getReference("WelcomePet");

        id = User.getUid();
        dataRef.child("UserAccount").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue(UserAccount.class) != null){
                    UserAccount account = snapshot.getValue(UserAccount.class);
                    useremail.setText("이메일: "+account.getEmail());
                    username.setText("이름: "+account.getName());
                    userphone.setText("전화번호: "+account.getPhone());
                } else {
                    Toast.makeText(getContext(), "데이터 없음...", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return v;
    }
}