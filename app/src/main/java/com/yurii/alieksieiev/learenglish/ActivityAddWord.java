package com.yurii.alieksieiev.learenglish;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import Alert.delete.item.AlertDelete;
import Alert.delete.item.AlertDeleteWord;
import CheckingWords.CheckWord;
import CheckingWords.ConvertStringToStandart;
import DbManager.DbManager;
import custom.adapter.ListAdapter;
import word.Word;

public class ActivityAddWord extends AppCompatActivity implements AlertDeleteWord{
    private DbManager dbManager;
    private ListAdapter listAdapter;

    private ArrayList<Word> arrayListWords;
    private EditText edtWord;
    private EditText edtTranslation;
    private Button btnAdd;
    private ListView listViewWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);


        dbManager = new DbManager(this);

        edtWord = findViewById(R.id.edt_word);
        edtTranslation = findViewById(R.id.edt_translate);
        btnAdd = findViewById(R.id.btn_add_to_base);
        listViewWords = findViewById(R.id.lst_words);

        btnAdd.setOnClickListener(new ButtonListener());

        arrayListWords = dbManager.getArrayListWords();
        listAdapter = new ListAdapter(this, arrayListWords);

        listViewWords.setAdapter(listAdapter);
        listViewWords.setOnItemLongClickListener(new LongClickOnItemListener());

    }


    private void addConvertWordToDataBaseAndListView() {
        String[] word_translation = getWordAndTranslation();

        word_translation = ConvertStringToStandart.convertToStandart(word_translation);//Standart: First symbol must be Uppercase but other lowercase

        if (checkWordAndPrintError(word_translation)) {

            if (dbManager.addWord(word_translation[0], word_translation[1]))//If false, this word is already in base
                addToListView(word_translation);
            else Toast.makeText(this, "This word already is in dictionary", Toast.LENGTH_SHORT).show();

            clearAdtWordAndTranslation();
        }
    }

    private void addToListView(String[] word_translate) {
        arrayListWords.add(new Word(word_translate[0], word_translate[1]));
        listAdapter.notifyDataSetChanged();
    }

    private void clearAdtWordAndTranslation() {
        edtWord.setText("");
        edtTranslation.setText("");
    }

    private boolean checkWordAndPrintError(String[] word_translation) {
        if (CheckWord.checkText(word_translation[0]) == CheckWord.NOT_ERROR
                && CheckWord.checkText(word_translation[1]) == CheckWord.NOT_ERROR)
            return true;

        else if (CheckWord.checkText(word_translation[0]) == CheckWord.ERROR_WRONG_SYMBOLS) {
            Toast.makeText(this, "\"" + word_translation[0] + "\" is wrong!", Toast.LENGTH_SHORT).show();
            return false;

        } else if (CheckWord.checkText(word_translation[1]) == CheckWord.ERROR_WRONG_SYMBOLS) {
            Toast.makeText(this, "\"" + word_translation[1] + "\" is wrong!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return false;
    }

    private String[] getWordAndTranslation() {
        String[] word_translation = new String[2];

        word_translation[0] = edtWord.getText().toString();
        word_translation[1] = edtTranslation.getText().toString();

        return word_translation;
    }

    private void removeFromDataBase(String word) {
        dbManager.deleteWord(word);
    }

    public void removeFromListView(int id) {
        arrayListWords.remove(id);
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void deleteWord(int position) {
        removeFromDataBase(arrayListWords.get(position).getWord());
        removeFromListView(position);
    }

    private class ButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (v == btnAdd)
                addConvertWordToDataBaseAndListView();
        }
    }

    private class LongClickOnItemListener implements AdapterView.OnItemLongClickListener {

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

            AlertDelete.showAlert(ActivityAddWord.this,position);

            return true;
        }
    }
}
