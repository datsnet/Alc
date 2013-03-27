package com.da.SirAlc;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {
	private static final String DB_NAME   = "Alc.db";
	private static final int DB_VERSION   = 1;
	public static final String TABLE_NAME = "favorite";
	public static final String COL_Name   = "word";
	public static final String COL_Date   = "Date";
	private static final String STRING_CREATE =
			"CREATE TABLE "+TABLE_NAME+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
			+COL_Name+" TEXT,"+COL_Date+ " DATE);";

	public MyDBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// DBテーブルの作成
		db.execSQL(STRING_CREATE);
		
		// 初期値をロード
		ContentValues cv = new ContentValues();
		cv.put(COL_Name, "teacher");
		// SQLの日付フォーマットのためのフォーマッタを作成
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 日付を挿入
		cv.put(COL_Date, dateFormat.format(new Date()));
		db.insert(TABLE_NAME, null, cv);
		
	}
	
	public void insert(SQLiteDatabase db, String word) {
		// データ挿入
		// 初期値をロード
		ContentValues cv = new ContentValues();
		cv.put(COL_Name, word);
		// SQLの日付フォーマットのためのフォーマッタを作成
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 日付を挿入
		cv.put(COL_Date, dateFormat.format(new Date()));
		db.insert(TABLE_NAME, null, cv);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		if (oldVersion <= 1) {
			db.execSQL("ALTER TABLE "+TABLE_NAME+" ADD COLUMN phone_number INTEGER;");
		}
		
		if (oldVersion <= 2) {
			db.execSQL("ALTER TABLE "+TABLE_NAME+" ADD COLUMN entry_date DATE;");
		}
	}

}
