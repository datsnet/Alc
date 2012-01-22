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
	private static final String DB_NAME   = "Alc";
	private static final int DB_VERSION   = 1;
	public static final String TABLE_NAME = "favorite";
	public static final String COL_Name   = "word";
	public static final String COL_Date   = "Date";
	private static final String STRING_CREATE =
			"CREATE TABLE "+TABLE_NAME+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
			+COL_Name+" TEXT,"+COL_Date+ " DATE);";

	public MyDBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		// TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// DB�e�[�u���̍쐬
		db.execSQL(STRING_CREATE);
		
		// �����l�����[�h
		ContentValues cv = new ContentValues();
		cv.put(COL_Name, "teacher");
		// SQL�̓��t�t�H�[�}�b�g�̂��߂̃t�H�[�}�b�^���쐬
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// ���t��}��
		cv.put(COL_Date, dateFormat.format(new Date()));
		db.insert(TABLE_NAME, null, cv);
		
	}
	
	public void insert(SQLiteDatabase db, String word) {
		// �f�[�^�}��
		// �����l�����[�h
		ContentValues cv = new ContentValues();
		cv.put(COL_Name, word);
		// SQL�̓��t�t�H�[�}�b�g�̂��߂̃t�H�[�}�b�^���쐬
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// ���t��}��
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
