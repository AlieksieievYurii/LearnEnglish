package com.yurii.alieksieiev.learenglish;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import Alert.test.setting.AlertTestSetting;
import Alert.test.setting.ITestSetting;
import DbManager.DbManager;

public class ActivityMakeTest extends AppCompatActivity implements ITestSetting
{
    private RelativeLayout body;

    private TextView tvOvalOfres;
    private TextView tvWord;
    private EditText edtTranslation;
    private Button btnCheck;

    private AlertTestSetting alertTestSetting;
    private DbManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_test);

        tvOvalOfres = findViewById(R.id.activity_make_test_res);
        tvWord = findViewById(R.id.tv_word_activity_make_test);
        edtTranslation = findViewById(R.id.edt_traslation_activity_make_test);
        btnCheck = findViewById(R.id.btn_check);
        body = findViewById(R.id.activity_make_test_body);

        tvOvalOfres.setOnClickListener(new ClickListener());

        alertTestSetting = new AlertTestSetting(this);
        dbManager = new DbManager(this);
    }

    @Override
    public void startTest(int toCountWords, boolean angToRus)
    {
        body.setVisibility(View.VISIBLE);
        setTvOvalOfresAsReckord(toCountWords);
        Log.i("--------------",toCountWords+" "+ angToRus);
        //TODO I must make Class which does test
    }

    @SuppressLint("DefaultLocale")
    private void setTvOvalOfresAsReckord(int toCountWords)
    {
        tvOvalOfres.setText(String.format("1/%d",toCountWords));
    }

    private class ClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            alertTestSetting.show(dbManager.getCount());
            tvOvalOfres.setClickable(false);
        }
    }
}
