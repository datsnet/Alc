package com.da.SirAlc;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.AdapterView;

public class Favorite extends Activity implements View.OnClickListener,
	AdapterView.OnItemClickListener {
	
	ListView mList;
	MyDBHelper mHelper;
	SQLiteDatabase mDb;
	Cursor mCursor;
	SimpleCursorAdapter mAdapter;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.favorite);
		
		mList = (ListView) findViewById(R.id.listView1);
		mList.setOnItemClickListener(this);
		mHelper = new MyDBHelper(this);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		// �f�[�^�x�[�X�̐ڑ����I�[�v��
		mDb = mHelper.getWritableDatabase();
		String[] columns = new String[] {"_id", MyDBHelper.COL_Name, MyDBHelper.COL_Date};
		mCursor = mDb.query(MyDBHelper.TABLE_NAME, columns, null, null, null, null, null);
		// ���X�g�����t���b�V������
		String[] headers = new String[] {MyDBHelper.COL_Name, MyDBHelper.COL_Date};
		mAdapter = new SimpleCursorAdapter (this, android.R.layout.two_line_list_item,
				mCursor, headers, new int[] {android.R.id.text1, android.R.id.text2});
		mList.setAdapter(mAdapter);
	}
	
	public void onPause() {
		super.onPause();
		// ���ׂĂ̐ڑ����N���[�Y����
		mDb.close();
		mCursor.close();
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u		
	}


	@Override
	public void onClick(View v) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		
	}
}
