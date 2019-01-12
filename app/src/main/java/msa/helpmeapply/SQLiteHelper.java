package msa.helpmeapply;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void queryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public void insertData(String firstName, String lastName, String email, String phone, String age, String university, String domain, String experience, byte[] cv) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO ABOUTME VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, firstName);
        statement.bindString(2, lastName);
        statement.bindString(3, email);
        statement.bindString(4, phone);
        statement.bindString(5, age);
        statement.bindString(6, university);
        statement.bindString(7, domain);
        statement.bindString(8, experience);
        statement.bindBlob(9, cv);

        statement.executeInsert();
    }

    public Cursor getData(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
