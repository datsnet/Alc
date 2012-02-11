package com.da.SirAlc;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

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
		// データベースの接続をオープン
		mDb = mHelper.getWritableDatabase();
		String[] columns = new String[] {"_id", MyDBHelper.COL_Name, MyDBHelper.COL_Date};
		mCursor = mDb.query(MyDBHelper.TABLE_NAME, columns, null, null, null, null, null);
		// リストをリフレッシュする
		String[] headers = new String[] {MyDBHelper.COL_Name, MyDBHelper.COL_Date};
		mAdapter = new SimpleCursorAdapter (this, android.R.layout.two_line_list_item,
				mCursor, headers, new int[] {android.R.id.text1, android.R.id.text2});
		mList.setAdapter(mAdapter);
	}
	
	public void onPause() {
		super.onPause();
		// すべての接続をクローズする
		mDb.close();
		mCursor.close();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// クリックしたらそのワードを持って遷移させる
		
		Log.i("parent", (String) parent.getItemAtPosition(position));
		
		ListView listView = (ListView) parent;
		String str = (String) listView.getItemAtPosition(position);
		  // ダイアログ表示のため
        Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Selected");
        dialog.setMessage(str);
        dialog.show();
	}


	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
}
