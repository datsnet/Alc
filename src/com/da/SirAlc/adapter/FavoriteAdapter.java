package com.da.SirAlc.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.da.SirAlc.R;
import com.da.SirAlc.dto.FavoriteDto;

public class FavoriteAdapter extends ArrayAdapter {

	private ArrayList items;
	private LayoutInflater inflater;

	public FavoriteAdapter(Context context, int resourceId,
			ArrayList items) {
		super(context, resourceId, items);
		this.items = items;
		this.inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// ビューを受け取る
		View view = convertView;
		if (view == null) {
			// 受け取ったビューがnullなら新しくビューを生成
			view = inflater.inflate(R.layout.favorite_row, null);
			// 背景画像をセットする
//			view.setBackgroundResource(R.drawable.back);
		}
		// 表示すべきデータの取得
		FavoriteDto item = (FavoriteDto) items.get(position);
		if (item != null) {
			TextView word = (TextView) view.findViewById(R.id.word);
			word.setTypeface(Typeface.DEFAULT_BOLD);
			TextView time = (TextView) view.findViewById(R.id.time);
			

			// 単語をビューにセット
			if (word != null) {
				word.setText(item.getWord());
			}
			time.setText(item.getDate());

		}
		return view;
	}
}
