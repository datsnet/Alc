package com.da.SirAlc.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.da.SirAlc.MyDBHelper;
import com.da.SirAlc.dto.FavoriteDto;

public class FavoriteDao {

	private static final String TABLE_NAME = "favorite";
	private static final String COL_WORD   = "word";
	private static final String COL_DATE   = "Date";
	private Context context;
	private SQLiteDatabase db;

	public FavoriteDao(Context context) {
		this.context = context;
	}

	public FavoriteDao(SQLiteDatabase db) {
		this.db = db;
	}

	public ArrayList<String> findAllWord() {
		String columns[] = {COL_WORD};
		String selection = null;
		String selectionArgs[] = null;
		
		
		Cursor cursor = this.db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);

		ArrayList<String> list = new ArrayList<String>();
		while (cursor.moveToFirst()) {
			
			list.add(cursor.getString(0));
			if (cursor.isLast()) {
				break;
			}
			cursor.moveToNext();
			
		}
		cursor.close();
		return list;
	}
	
	public ArrayList<FavoriteDto> findAll() {
		String columns[] = {COL_WORD, COL_DATE};
		String selection = null;
		String selectionArgs[] = null;
		
		Cursor cursor = this.db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);

		ArrayList<FavoriteDto> list = new ArrayList<FavoriteDto>();
		
		if (!cursor.moveToFirst()) return null;
		
		while (cursor.moveToNext()) {
			FavoriteDto dto = new FavoriteDto();
			dto.setWord(cursor.getString(0));
			dto.setDate(cursor.getString(1));
			list.add(dto);
			if (cursor.isLast()) {
				break;
			}			
		}
		cursor.close();
		return list;
	}
	
	public long insertWord(String value) {		
		ContentValues values = new ContentValues();
		values.put(COL_WORD, value);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		values.put(COL_DATE, dateFormat.format(new Date()));
		return this.db.insert(TABLE_NAME, null, values);
		
	}
	
	public long deleteSeletectedWord(String word) {
		String whereClause = COL_WORD + "=" + "\"" + word + "\"";
		return this.db.delete(TABLE_NAME, whereClause, null);
	}
	
}
