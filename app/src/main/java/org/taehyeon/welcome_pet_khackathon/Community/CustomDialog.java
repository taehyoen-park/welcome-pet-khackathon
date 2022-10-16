package org.taehyeon.welcome_pet_khackathon.Community;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import org.taehyeon.welcome_pet_khackathon.MainActivity;
import org.taehyeon.welcome_pet_khackathon.R;

public class CustomDialog {

    private Context context;

    public CustomDialog(Context context)
    {
        this.context = context;
    }

    public void callDialog()
    {
        final Dialog dialog = new Dialog(context);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_expert_user);
        dialog.show();

        final Button ok = (Button) dialog.findViewById(R.id.button_expert_ok);

        // 확인 버튼
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(context, "앱을 종료합니다", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                ((MainActivity) context).finish();
            }
        });

    }

}