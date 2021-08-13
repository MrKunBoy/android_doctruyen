package com.nvt.mangaslayer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DataTruyen extends SQLiteOpenHelper {
    public static final String DB_NAME = "manga0.db";
    public static final int DB_VERSION = 1;

    public DataTruyen(Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }

    public static final String TABLE_NAME = "Truyen";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TEN = "ten";
    public static final String COLUMN_MOTA = "mota";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_YEUTHICH = "yeuthich";
    public static final String COLUMN_NOIDUNG = "noidung";
    public static final String COLUMN_TACGIA = "tacgia";
    public static final String COLUMN_THELOAI = "theloai";
    public static final String COLUMN_TINHTRANG = "tinhtrang";
    public static final String COLUMN_LUOTXEM = "luotxem";

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+"("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COLUMN_TEN
                    +" VARCHAR(100), "+COLUMN_MOTA+" TEXT, "+COLUMN_IMAGE+" BLOB, "+COLUMN_YEUTHICH +" INTEGER, "+COLUMN_NOIDUNG+" TEXT, "
                    +COLUMN_TACGIA+" VARCHAR(100), "+COLUMN_THELOAI+" VARCHAR(100), "+COLUMN_TINHTRANG+" VARCHAR(100), "+COLUMN_LUOTXEM+" INTEGER )";

            Log.e("SQL",CREATE_TABLE);
            db.execSQL(CREATE_TABLE);
        }catch(Exception er){
            Log.e("Error","exceptioin");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public long insertTruyen(Truyen truyen){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TEN,truyen.getTen());
        contentValues.put(COLUMN_MOTA,truyen.getMoTa());
        contentValues.put(COLUMN_IMAGE,truyen.getImage());
        contentValues.put(COLUMN_YEUTHICH,truyen.getYeuThich());
        contentValues.put(COLUMN_NOIDUNG,truyen.getNoiDung());
        contentValues.put(COLUMN_TACGIA,truyen.getTacGia());
        contentValues.put(COLUMN_THELOAI,truyen.getTheLoai());
        contentValues.put(COLUMN_TINHTRANG,truyen.getTinhTrang());
        contentValues.put(COLUMN_LUOTXEM,truyen.getLuotXem());

        long result = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);

        return result;
    }

    public long updateTruyen(Truyen truyen){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID,truyen.getID());
        contentValues.put(COLUMN_TEN,truyen.getTen());
        contentValues.put(COLUMN_MOTA,truyen.getMoTa());
        contentValues.put(COLUMN_IMAGE,truyen.getImage());
        contentValues.put(COLUMN_YEUTHICH,truyen.getYeuThich());
        contentValues.put(COLUMN_NOIDUNG,truyen.getNoiDung());
        contentValues.put(COLUMN_TACGIA,truyen.getTacGia());
        contentValues.put(COLUMN_THELOAI,truyen.getTheLoai());
        contentValues.put(COLUMN_TINHTRANG,truyen.getTinhTrang());
        contentValues.put(COLUMN_LUOTXEM,truyen.getLuotXem());

        long result = sqLiteDatabase.update(TABLE_NAME,contentValues,COLUMN_ID +"=?",new String[]{String.valueOf(truyen.getID())});

        return result;
    }

    public long deleteTruyen(int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        long result = sqLiteDatabase.delete(TABLE_NAME,COLUMN_ID +"=?",new String[]{String.valueOf(id)});

        return result;
    }

    public List<Truyen> getAllTruyen() {

        List<Truyen> truyens = new ArrayList<>();

        String select = "SELECT * from " + TABLE_NAME;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(select, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                String idsp = cursor.getString(cursor.getColumnIndex(COLUMN_ID));
                String tensp = cursor.getString(cursor.getColumnIndex(COLUMN_TEN));
                String motasp = cursor.getString(cursor.getColumnIndex(COLUMN_MOTA));
                byte[] imagesp = cursor.getBlob(cursor.getColumnIndex(COLUMN_IMAGE));
                String yeuthichsp = cursor.getString(cursor.getColumnIndex(COLUMN_YEUTHICH));
                String noidungsp = cursor.getString(cursor.getColumnIndex(COLUMN_NOIDUNG));
                String tacgiasp = cursor.getString(cursor.getColumnIndex(COLUMN_TACGIA));
                String theloaisp = cursor.getString(cursor.getColumnIndex(COLUMN_THELOAI));
                String tinhtrangsp = cursor.getString(cursor.getColumnIndex(COLUMN_TINHTRANG));
                String luotxemsp = cursor.getString(cursor.getColumnIndex(COLUMN_LUOTXEM));

                Truyen truyen = new Truyen();
                truyen.setID(Integer.valueOf(idsp));
                truyen.setTen(tensp);
                truyen.setMoTa(motasp);
                truyen.setImage(imagesp);
                truyen.setYeuThich(Integer.valueOf(yeuthichsp));
                truyen.setNoiDung(noidungsp);
                truyen.setTacGia(tacgiasp);
                truyen.setTheLoai(theloaisp);
                truyen.setTinhTrang(tinhtrangsp);
                truyen.setLuotXem(Integer.valueOf(luotxemsp));

                truyens.add(truyen);

                cursor.moveToNext();
            }
            cursor.close();
        }

        return truyens;
    }

    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM "+TABLE_NAME+" WHERE "+COLUMN_ID+" =?", new String[]{String.valueOf(id)} );
        return res;
    }
}
