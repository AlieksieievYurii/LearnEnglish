package DbManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import word.Word;

public class DbManager extends SQLiteOpenHelper {
    private static final String LOG_TAG = "DbManager";

    private static final String NAME_DB = "Words";
    private static final String NAME_TABlE = "Words_table";
    private static final String COLUM_WORD = "_word";
    private static final String COLUM_TRASLATION = "translation";

    private static final int VERSION_DB = 1;

    public DbManager(Context context) {
        super(context, NAME_DB, null, VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format("CREATE TABLE %s(%s TEXT PRIMARY KEY, %s TEXT)", NAME_TABlE, COLUM_WORD, COLUM_TRASLATION));
        Log.i(LOG_TAG, "---> DataBase is created!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(String.format("DROP TABLE %s", NAME_TABlE));
        Log.i(LOG_TAG, "---> Table is droped!");
        onCreate(db);
    }

    public boolean addWord(String word, String translation) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues wordTraslation = new ContentValues();
        wordTraslation.put(COLUM_WORD, word);
        wordTraslation.put(COLUM_TRASLATION, translation);

        long resualtInsterting = sqLiteDatabase.insert(NAME_TABlE, null, wordTraslation);

        return resualtInsterting != -1;
    }

    public ArrayList<Word> getArrayListWords() {
        ArrayList<Word> arrayList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + NAME_TABlE, null);

        while (cursor.moveToNext()) {
            String word = cursor.getString(cursor.getColumnIndex(COLUM_WORD));
            String traslation = cursor.getString(cursor.getColumnIndex(COLUM_TRASLATION));
            arrayList.add(new Word(word, traslation));
        }

        cursor.close();

        return arrayList;
    }

    public void deleteWord(String id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Log.i(LOG_TAG, this.getDatabaseName());
        sqLiteDatabase.delete(NAME_TABlE,COLUM_WORD+" = ?",new String[]{id});
    }

    public int getCount()
    {
        Cursor cursor = null;
        try {
            SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
            cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + NAME_TABlE, null);
            return cursor.getCount();
        }finally
        {
            cursor.close();
        }
    }

    public void printDatasToLog() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + NAME_TABlE, null);

        if (cursor.getCount() == 0) {
            Log.i("RES:::", "DataBase is empty!!!");
            return;
        }

        while (cursor.moveToNext()) {
            Log.i("RES:::", cursor.getString(cursor.getColumnIndex(COLUM_WORD)) + " -- " + cursor.getString(cursor.getColumnIndex(COLUM_TRASLATION)));
        }

        cursor.close();
    }

}
