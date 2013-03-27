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
		// �r���[���󂯎��
		View view = convertView;
		if (view == null) {
			// �󂯎�����r���[��null�Ȃ�V�����r���[�𐶐�
			view = inflater.inflate(R.layout.favorite_row, null);
			// �w�i�摜���Z�b�g����
//			view.setBackgroundResource(R.drawable.back);
		}
		// �\�����ׂ��f�[�^�̎擾
		FavoriteDto item = (FavoriteDto) items.get(position);
		if (item != null) {
			TextView word = (TextView) view.findViewById(R.id.word);
			word.setTypeface(Typeface.DEFAULT_BOLD);
			TextView time = (TextView) view.findViewById(R.id.time);
			

			// �P����r���[�ɃZ�b�g
			if (word != null) {
				word.setText(item.getWord());
			}
			time.setText(item.getDate());

		}
		return view;
	}
}
