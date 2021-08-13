package com.nvt.mangaslayer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class DataTaiKhoan extends SQLiteOpenHelper {
    public DataTaiKhoan(Context context) {
        super(context, "manga.db", null, 1);
    }

    public static final String TABLE_NAME = "TaiKhoan";

    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("+COLUMN_USERNAME+" VARCHAR PRIMARY KEY, "+COLUMN_EMAIL+" VARCHAR, "+COLUMN_PASSWORD+" VARCHAR)";

        Log.e("SQL",CREATE_TABLE);
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertTaiKhoan(TaiKhoan taiKhoan){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_USERNAME,taiKhoan.getUsername());
        contentValues.put(COLUMN_EMAIL,taiKhoan.getEmail());
        contentValues.put(COLUMN_PASSWORD,taiKhoan.getPassword());

        long result = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);

        return result;
    }

//    public long updateTaiKhoan(TaiKhoan taiKhoan){
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//
//        contentValues.put(COLUMN_USERNAME,taiKhoan.getUsername());
//        contentValues.put(COLUMN_EMAIL,taiKhoan.getEmail());
//        contentValues.put(COLUMN_PASSWORD,taiKhoan.getPassword());
//
//        long result = sqLiteDatabase.update(TABLE_NAME,contentValues,COLUMN_USERNAME +"=?",new String[]{taiKhoan.getUsername()});
//
//        return result;
//    }
//
//    public long deleteTaiKhoan(String user){
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        long result = sqLiteDatabase.delete(TABLE_NAME,COLUMN_USERNAME +"=?",new String[]{user});
//
//        return result;
//    }

    public List<TaiKhoan> getAllTaiKhoan() {

        List<TaiKhoan> taiKhoans = new ArrayList<>();

        String select = "SELECT * from " + TABLE_NAME;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(select, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                String email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
                String tendn = cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME));
                String mkhau = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));

                TaiKhoan taiKhoan = new TaiKhoan();
                taiKhoan.setEmail(email);
                taiKhoan.setUsername(tendn);
                taiKhoan.setPassword(mkhau);

                taiKhoans.add(taiKhoan);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return taiKhoans;
    }

    public boolean checkLogin(String ten, String mk) {
        List<TaiKhoan> taiKhoans = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+COLUMN_USERNAME+" =? AND "+COLUMN_PASSWORD+" =?", new String[]{ten, mk});
        boolean check = false ;
        if(cursor.getCount() > 0){
            check = true;
        }
        else {
            check = false;
        }
        cursor.close();
        return check;
    }
}
