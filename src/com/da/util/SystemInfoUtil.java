package com.da.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;


public class SystemInfoUtil {

	public SystemInfoUtil() {
		
    }
	
	// �o�b�e���[���擾
	public static int getBatteryInfo(Context context) {
        //�o�b�e���[���̎�M�J�n
		Intent bat = context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
		int level = bat.getIntExtra("level", 0);
		int scale = bat.getIntExtra("scale", 100);
		return level * 100 / scale;
	}
	
	// ���������擾(���O�ɏo��)
	public static void getMemoryInfo(Object obj) {
		// �����������擾
		ActivityManager activityManager = (ActivityManager) obj;
		ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
		activityManager.getMemoryInfo(memoryInfo);

		// �V�X�e���̗��p�\�ȋ󂫃�����
		Log.v("MemoryInfo", "memoryInfo.availMem[MB] = " + (int)(memoryInfo.availMem/1024/1024));
		// �Ⴡ����(LowMemory)��Ԃ�臒l
		Log.v("MemoryInfo", "memoryInfo.threshold[MB] = " + (int)(memoryInfo.threshold/1024/1024));
		// �Ⴡ������Ԃ������t���O(true�Ń������s�����)
		Log.v("MemoryInfo", "memoryInfo.lowMemory = " + memoryInfo.lowMemory);
	}

}
