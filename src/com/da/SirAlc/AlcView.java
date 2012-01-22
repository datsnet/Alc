package com.da.SirAlc;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;

public class AlcView extends Activity {
	private WebView webview;	
	private String searchWord;	// 検索語句
	
	MyDBHelper mHelper;
	SQLiteDatabase mDb;
	Cursor mCursor;
	SimpleCursorAdapter mAdapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);
		
		mHelper = new MyDBHelper(this);
		
		Intent intent = getIntent();
		searchWord = intent.getStringExtra("search_word");
		
		webview =  (WebView) findViewById(R.id.webView1);
		
		WebSettings settings = webview.getSettings();
		settings.setJavaScriptEnabled(true);
		settings.setPluginsEnabled(true);
		settings.setSupportZoom(true);
		settings.setBuiltInZoomControls(true);
		settings.setUseWideViewPort(true);
		
		webview.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				return super.shouldOverrideUrlLoading(view, url);
			}
		});
		
		webview.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				View pBarWrapper = findViewById(R.id.progress_bar_wrapper);
				ProgressBar pBar = (ProgressBar) findViewById(R.id.progress_bar);
				pBar.setProgress(newProgress);
				
				if (newProgress == 100) {
					pBarWrapper.setVisibility(View.GONE);
				} else {
					pBarWrapper.setVisibility(View.VISIBLE);
				}
			}
		});
		
		// お気に入りボタン
		Button favoriteBtn = (Button) findViewById(R.id.favorite_btn);
		favoriteBtn.setOnClickListener(
           	new View.OnClickListener() {
    			@Override
            	public void onClick(View v) {
    				insertFavoriteWord();
   	        	}
    	});				
		
		
		// アルク読み込み開始
		webview.loadUrl("http://eow.alc.co.jp/"+searchWord+"/UTF-8/");

	}
	
	@Override
	public void onResume() {
		super.onResume();
		// データベースの接続をオープン
		mDb = mHelper.getWritableDatabase();

	}
	
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ( keyCode == KeyEvent.KEYCODE_BACK  && webview.canGoBack() == true) {
            webview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    // 検索したワードをDBに入れる
	private void insertFavoriteWord() {
		ContentValues cv = new ContentValues(2);
		cv.put(MyDBHelper.COL_Name, searchWord);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		cv.put(MyDBHelper.COL_Date, dateFormat.format(new Date()));
		mDb.insert(MyDBHelper.TABLE_NAME, null, cv);
	}
}
