package com.da.SirAlc;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class TabMain extends TabActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab); // main.xmlをセット

		/* タブ設定 */
		Resources res = getResources();
		TabHost tabHost = getTabHost();
		TabHost.TabSpec spec;
		Intent intent;
		
		intent = new Intent().setClass(this, SirAlcActivity.class);
		spec = tabHost.newTabSpec("tab1")					  
				.setIndicator("HOME",   
                res.getDrawable(R.drawable.ic_tab_home_unselected))  
					  .setContent(intent);
		tabHost.addTab(spec);
//	    intent = new Intent().setClass(this, AlcView.class);  
//	    spec = tabHost.newTabSpec("tab2")  
//	                  .setIndicator("Camera",   
//	                   res.getDrawable(R.drawable.ic_launcher))  
//	                  .setContent(intent);  
//	    tabHost.addTab(spec);  	  
	    intent = new Intent().setClass(this, Favorite.class);  
	    spec = tabHost.newTabSpec("tab2")  
                .setIndicator("お気に入り",   
                res.getDrawable(R.drawable.ic_tab_favorite_unselected))  
	                  .setContent(intent);  
	    tabHost.addTab(spec);  
	  
	    tabHost.setCurrentTab(0);  
		/* タブ設定終了 */

	}
}
