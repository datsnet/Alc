package com.da.SirAlc;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.da.SirAlc.adapter.FavoriteAdapter;
import com.da.SirAlc.dao.FavoriteDao;
import com.da.SirAlc.dto.FavoriteDto;

public class Favorite extends Activity implements View.OnClickListener,
		AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

	ListView mList;
	MyDBHelper mHelper;
	SQLiteDatabase mDb;
	Cursor mCursor;
	SimpleCursorAdapter mAdapter;
	Context mContext;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.favorite);

		mList = (ListView) findViewById(R.id.listView1);
		mList.setOnItemClickListener(this);
		mList.setOnItemLongClickListener(this);
		mHelper = new MyDBHelper(this);
		mContext = this;
	}

	@Override
	public void onResume() {
		super.onResume();
		// データベースの接続をオープン
		mDb = mHelper.getWritableDatabase();
		FavoriteDao dao = new FavoriteDao(mDb);
		ArrayList<FavoriteDto> list = new ArrayList<FavoriteDto>();
		list = dao.findAll();

		FavoriteAdapter adapter = new FavoriteAdapter(getApplicationContext(),
				R.layout.favorite, list);

		mList.setAdapter(adapter);
	}

	public void onPause() {
		super.onPause();
		// すべての接続をクローズする
		mDb.close();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// クリックしたらそのワードを持って遷移させる
		ListView listView = (ListView) parent;
		FavoriteDto dto = (FavoriteDto) listView.getItemAtPosition(position);

		Intent intent = new Intent(getApplicationContext(), AlcView.class);
		intent.putExtra("search_word", dto.getWord());
		startActivity(intent);
	}

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, final View view,
			final int position, long id) {
		ListView listView = (ListView) parent;
		final FavoriteDto dto = (FavoriteDto) listView
				.getItemAtPosition(position);
		final FavoriteDao dao = new FavoriteDao(mDb);
		new AlertDialog.Builder(this)
				.setMessage(R.string.delete_dialog_message)
				.setNegativeButton(getString(R.string.dialog_cancel), null)
				.setPositiveButton(getString(R.string.dialog_ok),
						new OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								long result = dao.deleteSeletectedWord(dto
										.getWord());
								if (result == 1) {
									mList.removeViewInLayout(view);
								}
								dialog.dismiss();
							}
						}).show();

		return false;
	}
}
