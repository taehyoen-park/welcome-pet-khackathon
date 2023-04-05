package org.taehyeon.welcome_pet_khackathon.AR;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.unity3d.player.UnityPlayer;

import org.taehyeon.welcome_pet_khackathon.MainActivity;
import org.taehyeon.welcome_pet_khackathon.R;

public class UnityPlayerView extends AppCompatActivity  {

    protected UnityPlayer mUnityPlayer;
    InfoArFragment fragment = new InfoArFragment();
    InfoAr2Fragment fragment2 = new InfoAr2Fragment();

    private ImageButton btn_home;
    private ImageButton btn_next;

    protected String updateUnityCommandLineArguments(String cmdLine)
    {
        return cmdLine;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unity_player);

        mUnityPlayer = new UnityPlayer(this);
        int glesMode = mUnityPlayer.getSettings().getInt("gles_mode",1);
        mUnityPlayer.init(glesMode, false);

        FrameLayout layout = (FrameLayout) findViewById(R.id.con_layout);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT);


        Intent intent = getIntent();
        String data = intent.getStringExtra("model");


        if(data.equals("None")){
            mUnityPlayer.UnitySendMessage("DogSpawner","GetAndroidValue","None");
        }
        else if (data.equals("OnInjured")){
            mUnityPlayer.UnitySendMessage("DogSpawner","GetAndroidValue","OnInjured");
        }
        else if (data.equals("OnSleep")){
            mUnityPlayer.UnitySendMessage("DogSpawner","GetAndroidValue","OnSleep");
        }
        else if (data.equals( "OnWalk")){
            mUnityPlayer.UnitySendMessage("DogSpawner","GetAndroidValue","OnWalk");
        }
        else if (data.equals("OnDeath")){
            mUnityPlayer.UnitySendMessage("DogSpawner","GetAndroidValue","OnDeath");
        }
        else if (data.equals("OnRoar")){
            mUnityPlayer.UnitySendMessage("DogSpawner","GetAndroidValue","OnRoar");
        }
        else if (data.equals("OnAttack")){
            mUnityPlayer.UnitySendMessage("DogSpawner","GetAndroidValue","OnAttack");
        }


        layout.addView(mUnityPlayer.getView(),0,lp);


        btn_home = findViewById(R.id.test_btn);
        btn_next = findViewById(R.id.next_btn);



    }


    // Resume Unity
    @Override protected void onResume()
    {
        super.onResume();

        mUnityPlayer.resume();

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentChange(1);
            }
        });

        btn_home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(UnityPlayerView.this, MainActivity.class);
                mUnityPlayer.destroy();
                startActivity(intent);
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        mUnityPlayer.windowFocusChanged(hasFocus);
    }


    public void fragmentChange(int index){
        if(index == 1){
            getSupportFragmentManager().beginTransaction().replace(R.id.ar_question,fragment).commit();
        }
        else if(index == 2){
            getSupportFragmentManager().beginTransaction().replace(R.id.ar_question,fragment2).commit();
        }
    }

}