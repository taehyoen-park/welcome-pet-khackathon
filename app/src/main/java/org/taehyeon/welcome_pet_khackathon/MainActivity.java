package org.taehyeon.welcome_pet_khackathon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.taehyeon.welcome_pet_khackathon.Community.community_Fragment;
import org.taehyeon.welcome_pet_khackathon.Experience.experience_Fragment;
import org.taehyeon.welcome_pet_khackathon.Home.home_Fragment;
import org.taehyeon.welcome_pet_khackathon.Shop.shop_Fragment;
import org.taehyeon.welcome_pet_khackathon.Userinfo.userinfo_Fragment;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager = getSupportFragmentManager();
    home_Fragment fragment_home = new home_Fragment();
    userinfo_Fragment fragment_userinfo = new userinfo_Fragment();
    shop_Fragment fragment_shop = new shop_Fragment();
    community_Fragment fragment_community = new community_Fragment();
    experience_Fragment fragment_experience = new experience_Fragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout_main, fragment_home).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());
    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch(menuItem.getItemId())
            {
                case R.id.UserinfoItem:
                    transaction.replace(R.id.frameLayout_main, fragment_userinfo).commitAllowingStateLoss();
                    break;

                case R.id.experienceItem:
                    transaction.replace(R.id.frameLayout_main, fragment_experience).commitAllowingStateLoss();
                    break;
                case R.id.HomeItem:
                    transaction.replace(R.id.frameLayout_main, fragment_community).commitAllowingStateLoss();
                    break;
                case R.id.CommunityItem:
                    transaction.replace(R.id.frameLayout_main, fragment_community).commitAllowingStateLoss();
                    break;
                case R.id.ShopItem:
                    transaction.replace(R.id.frameLayout_main, fragment_shop).commitAllowingStateLoss();
                    break;
            }
            return true;
        }
    }
}