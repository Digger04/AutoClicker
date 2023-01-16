package com.example.autoclicker.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.autoclicker.PackageName.Constants;
import com.example.autoclicker.R;
import com.example.autoclicker.Service.FloatingBarService;

public class SpamMessage extends AppCompatActivity {

    private ImageView img_back;
    private TextView txt_title;
    private Window window;
    private Button bt_star;
    private CheckBox checkbox_content_custom, checkbox_content_random;
    private LinearLayout layout_linecontent;
    private CheckBox checkbox_unlimit, checkbox_limit;
    private LinearLayout layout_numbermess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spam_message);

        initView();
        Back();
        setStatusbar();
        Star();
        setContentMessage();
        setNumberMessage();
    }

    private void setNumberMessage() {
        checkbox_unlimit.setOnClickListener(view -> {
            checkbox_unlimit.setChecked(true);
            checkbox_limit.setChecked(false);
            layout_numbermess.setVisibility(View.GONE);
        });

        checkbox_limit.setOnClickListener(view -> {
            checkbox_unlimit.setChecked(false);
            checkbox_limit.setChecked(true);
            layout_numbermess.setVisibility(View.VISIBLE);
        });
    }

    private void setContentMessage() {

        checkbox_content_custom.setOnClickListener(view -> {
            layout_linecontent.setVisibility(View.VISIBLE);
            checkbox_content_random.setChecked(false);
            checkbox_content_custom.setChecked(true);
        });

        checkbox_content_random.setOnClickListener(view -> {
            layout_linecontent.setVisibility(View.GONE);
            checkbox_content_random.setChecked(true);
            checkbox_content_custom.setChecked(false);
        });
    }

    private void Star() {

        bt_star.setOnClickListener(view -> {

            try {
                PackageManager pm = this.getPackageManager();
                Intent launchIntent = pm.getLaunchIntentForPackage(Constants.MESSENGER_BUNDLE_ID);
                startActivity(launchIntent);
            }catch (Exception e) {
                Log.d("weffw", "error open app: " + e);
            }

            try {
                Intent intentservice = new Intent(this, FloatingBarService.class);
                startService(intentservice);
            }catch (Exception e) {
                Log.d("weffw", "error open service: " + e);
            }

        });
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

    private void Back() {
        img_back.setOnClickListener(view -> {
            finish();
        });
    }

    private void initView() {
        img_back = findViewById(R.id.img_back);
        txt_title = findViewById(R.id.txt_title);
        txt_title.setSelected(true);
        bt_star = findViewById(R.id.bt_star);
        layout_linecontent = findViewById(R.id.layout_linecontent);

        checkbox_content_custom = findViewById(R.id.checkbox_content_custom);
        checkbox_content_random = findViewById(R.id.checkbox_content_random);

        checkbox_unlimit = findViewById(R.id.checkbox_unlimit);
        checkbox_limit = findViewById(R.id.checkbox_limit);

        layout_numbermess = findViewById(R.id.layout_numbermess);
    }
}