package com.da.SirAlc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.text.Editable;
import android.text.InputType;


public class SirAlcActivity extends Activity {
	
	private EditText editText;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main); // main.xml‚ðƒZƒbƒg
		
		this.getWindow().setSoftInputMode(LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);  
		
		
		editText = (EditText)findViewById(R.id.editText1);
    	editText.setInputType(InputType.TYPE_CLASS_TEXT);
    	editText.setMaxLines(1);
    	
        Button btn = (Button)this.findViewById(R.id.button1);
        btn.setOnClickListener(
            	new View.OnClickListener() {
    				@Override
    	        	public void onClick(View v) {
    					doSearch();
    	        	}
    	});
        editText.setOnEditorActionListener(new OnEditorActionListener() {
        	  public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        	    if (event == null || event.getAction() == KeyEvent.ACTION_UP) {
        	      doSearch();
        	    }
        	    return true;
        	  }
        });
	}
	private void doSearch() {
    	Editable str = editText.getText();
    	Intent intent = new Intent(getApplicationContext(), AlcView.class);
    	intent.putExtra("search_word", str.toString());
    	startActivity(intent);	 
	}
}
