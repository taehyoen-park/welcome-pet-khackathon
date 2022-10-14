package org.taehyeon.welcome_pet_khackathon.Community;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.taehyeon.welcome_pet_khackathon.Auth.UserAccount;
import org.taehyeon.welcome_pet_khackathon.Auth.Userinput;
import org.taehyeon.welcome_pet_khackathon.R;
import org.taehyeon.welcome_pet_khackathon.Userinfo.userinfo_Fragment;


public class WritePostFragment extends Fragment {

    private static final String TAG = "WritePostFragment";
    UserAccount userAccount = new UserAccount();

    Button button_check;
    String name="default";
    FirebaseUser user;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_write_post, container, false);

        final String title = ((EditText) v.findViewById(R.id.title_editText)).getText().toString();
        final String contents = ((EditText) v.findViewById(R.id.contents_editText)).getText().toString();
        button_check = v.findViewById(R.id.button_check);

        if(getArguments()!=null)
        {
            Toast.makeText(getContext(),"화면이 넘어옴", Toast.LENGTH_SHORT).show();
        }

        button_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(title.length() > 0 && contents.length() > 0 ) {
                    user = FirebaseAuth.getInstance().getCurrentUser();
                    WriteInfo writeInfo = new WriteInfo(title, contents, userAccount.getEmail()); //유저 아이디가 들어가야함. 아이디 가져와주세요.
                    uploader(writeInfo);
                } else {
                    Toast.makeText(getContext(),"회원정보를 입력해",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }

    private void uploader(WriteInfo writeInfo){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        //part10 게시글 작성 화면 구현 25:52
        db.collection("users").add(writeInfo)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getContext(),"성공",Toast.LENGTH_SHORT).show();
                        Log.d(TAG,"DocumentSnapshot written with ID : "+documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG,"Error adding document",e);
                   }
        });
    }

}