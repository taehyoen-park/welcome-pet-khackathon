package org.taehyeon.welcome_pet_khackathon.Userinfo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.taehyeon.welcome_pet_khackathon.Auth.Login;
import org.taehyeon.welcome_pet_khackathon.Auth.UserAccount;
import org.taehyeon.welcome_pet_khackathon.MainActivity;
import org.taehyeon.welcome_pet_khackathon.R;
import org.taehyeon.welcome_pet_khackathon.Start_survey.survey;

import java.util.HashMap;


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

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Auth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), Login.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        btn_certi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dig = new AlertDialog.Builder(getContext());
                dig.setTitle("??????????????????");
                dig.setMessage("??????????????? ???????????????????\n(?????????????????? ?????? : ?????????????????????, ?????????????????????, ?????????????????? ????????? ?????????).");
                dig.setIcon(R.drawable.ic_launcher_foreground).setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(getContext(),"????????? ????????? ??? ????????????.",Toast.LENGTH_SHORT).show();

                        HashMap<String,Object> hashMap = new HashMap<>();
                        hashMap.put("job","pro");

                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("WelcomePet");
                        ref.child("UserAccount").child(user.getUid()).updateChildren(hashMap)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(getActivity(), "??????...", Toast.LENGTH_SHORT).show();
                                        //userjob.setText("??????: ?????????");
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                    }
                                });

                    }
                });
                dig.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(),"?????? ???????????????.",Toast.LENGTH_SHORT).show();


                        //displayToast("no");
                    }
                });//no?????? ????????? ????????? _???????????? ?????? ??????.//????????? ???????????? ??????.~.~; .~;
                dig.show();
            }
        });

        User = Auth.getCurrentUser();
        id = User.getUid();
        dataRef = FirebaseDatabase.getInstance().getReference("WelcomePet");
        dataRef.child("UserAccount").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue(UserAccount.class) != null){
                    UserAccount account = snapshot.getValue(UserAccount.class);
                    useremail.setText("?????????: "+account.getEmail());
                    username.setText("??????: "+account.getName());
                    userphone.setText("????????????: "+account.getPhone());
                    if(account.getJob().equals("pro"))
                        userjob.setText("??????: ?????????");
                    else
                        userjob.setText("??????: ?????????");
                } else {
                    Toast.makeText(getContext(), "????????? ??????...", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return v;
    }
}