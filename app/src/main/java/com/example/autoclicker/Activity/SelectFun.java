package com.example.autoclicker.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.autoclicker.R;

public class SelectFun extends AppCompatActivity {

    private Window window;
    private RelativeLayout layout_ads, layout_spammess, layout_dropemoji, layout_share, layout_comment,
    layout_siginmail, layout_siginfb, layout_sigininsta;
    private TextView txt_deviceid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_fun);

        initView();
        GetInfoDevice();
        setStatusbar();
        Ads();
        Spammess();
        DropEmoji();
        Share();
        Comment();
        SiginMail();
        SiginFb();
        SiginInsta();
    }

    private void GetInfoDevice() {

        String m_androidId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        txt_deviceid.setText("Device Id: " + m_androidId);

    }

    private void SiginInsta() {
        layout_sigininsta.setOnClickListener(view -> {
            Toast.makeText(this, "Pending", Toast.LENGTH_SHORT).show();
        });
    }

    private void SiginFb() {
        layout_siginfb.setOnClickListener(view -> {
            Toast.makeText(this, "Pending", Toast.LENGTH_SHORT).show();

        });
    }

    private void SiginMail() {
        layout_siginmail.setOnClickListener(view -> {
            Toast.makeText(this, "Pending", Toast.LENGTH_SHORT).show();

        });
    }

    private void Comment() {
        layout_comment.setOnClickListener(view -> {
            Toast.makeText(this, "Pending", Toast.LENGTH_SHORT).show();

        });
    }

    private void Share() {
        layout_share.setOnClickListener(view -> {
            Toast.makeText(this, "Pending", Toast.LENGTH_SHORT).show();

        });
    }

    private void DropEmoji() {
        layout_dropemoji.setOnClickListener(view -> {
            Toast.makeText(this, "Pending", Toast.LENGTH_SHORT).show();

        });
    }

    private void Spammess() {
        layout_spammess.setOnClickListener(view -> {
            startActivity(new Intent(this, SpamMessage.class));
        });
    }

    private void Ads() {
        layout_ads.setOnClickListener(view -> {
            Toast.makeText(this, "Pending", Toast.LENGTH_SHORT).show();

        });
    }

    private void initView() {
        layout_ads = findViewById(R.id.layout_ads);
        layout_spammess = findViewById(R.id.layout_spammess);
        layout_dropemoji = findViewById(R.id.layout_dropemoji);
        layout_share = findViewById(R.id.layout_share);
        layout_dropemoji = findViewById(R.id.layout_dropemoji);
        layout_share = findViewById(R.id.layout_share);
        layout_comment = findViewById(R.id.layout_comment);
        layout_siginmail = findViewById(R.id.layout_siginmail);
        layout_siginfb = findViewById(R.id.layout_siginfb);
        layout_sigininsta = findViewById(R.id.layout_sigininsta);

        txt_deviceid = findViewById(R.id.txt_deviceid);
    }

    private void setStatusbar() {
        window = this.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.white));

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }
}