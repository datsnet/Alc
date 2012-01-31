package com.da.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;


public class SystemInfoUtil {

	public SystemInfoUtil() {
		
    }
	
	// バッテリー情報取得
	public static int getBatteryInfo(Context context) {
        //バッテリー情報の受信開始
		Intent bat = context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
		int level = bat.getIntExtra("level", 0);
		int scale = bat.getIntExtra("scale", 100);
		return level * 100 / scale;
	}
	
	// メモリ情報取得(ログに出力)
	public static void getMemoryInfo(Object obj) {
		// メモリ情報を取得
		ActivityManager activityManager = (ActivityManager) obj;
		ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
		activityManager.getMemoryInfo(memoryInfo);

		// システムの利用可能な空きメモリ
		Log.v("MemoryInfo", "memoryInfo.availMem[MB] = " + (int)(memoryInfo.availMem/1024/1024));
		// 低メモリ(LowMemory)状態の閾値
		Log.v("MemoryInfo", "memoryInfo.threshold[MB] = " + (int)(memoryInfo.threshold/1024/1024));
		// 低メモリ状態を示すフラグ(trueでメモリ不足状態)
		Log.v("MemoryInfo", "memoryInfo.lowMemory = " + memoryInfo.lowMemory);
	}

}
