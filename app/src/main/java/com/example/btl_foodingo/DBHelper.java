package com.example.btl_foodingo;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static  final String DBNAME = "sanpham.db";

    public DBHelper( Context context) {
        super(context, DBNAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists users(username text primary key, password text)");
        db.execSQL("create table if not exists sanpham(id Integer primary key autoincrement, title text, pic integer ,fee real, quantity integer)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists users");
        onCreate(db);
    }


    public Boolean insertUser(String username, String password){


        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put("username", username);
        values.put("password", password);
        long result = db.insert("users", null, values);
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }
    public Boolean checkUsername(String username){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username = ?", new String[] {username});
        if(cursor.getCount() > 0){
            return  true;
        }else{
            return false;
        }
    }
    public Boolean checkUserNamePassword(String username, String password){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username = ? and password = ?",new String[] {username, password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public String getName(String username, String password){
        String ten = "utwrthtrh";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor dataName = db.rawQuery("select username from users where username = ? and password = ?", new String[] {username,password});
        while(dataName.moveToNext()){
            ten = dataName.getString(0);
        }
        return ten;
    }

    // Thêm sản phẩm
    public void insertData(SanPham_GioHang sanPham_gioHang){

        // Mở kết nối với cơ sở dữ liệu
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title", sanPham_gioHang.getTieude());
        values.put("pic", sanPham_gioHang.getAnh());
        values.put("fee", sanPham_gioHang.getGia());
        values.put("quantity", sanPham_gioHang.getSoluong());
        db.insert("sanpham", null, values);
        db.close();
    }


    public void queryData(String sql){
        SQLiteDatabase dtb = getWritableDatabase();
        dtb.execSQL(sql);
        onCreate(dtb);
    }

    public Cursor getData(String sql){
        SQLiteDatabase dtb = getReadableDatabase();
        return dtb.rawQuery(sql,null);
    }



}
