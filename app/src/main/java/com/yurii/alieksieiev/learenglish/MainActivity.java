package com.yurii.alieksieiev.learenglish;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
    private Button btnAddWord;
    private Button btnMakeTest;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddWord = findViewById(R.id.btn_add_words);
        btnMakeTest = findViewById(R.id.btn_make_test);

        CustomButtonListener listener = new CustomButtonListener();
        btnMakeTest.setOnClickListener(listener);
        btnAddWord.setOnClickListener(listener);

        loading();
    }

    private void loading()
    {

    }

    private void goActivityAddWord()
    {
        Intent intent = new Intent("activity.add.word");
        startActivity(intent);
        Log.i(getClass().getName(),"Activity add word!");
    }

    private void goActivityMakeTest()
    {
        Intent intent = new Intent("activity.make.test");
        startActivity(intent);
        Log.i(getClass().getName(),"Activity make test!");
    }

    private class CustomButtonListener implements View.OnClickListener
    {

        @Override
        public void onClick(View v)
        {
            if(v == btnAddWord)
                goActivityAddWord();
            else if(v == btnMakeTest)
                goActivityMakeTest();
        }
    }
}
