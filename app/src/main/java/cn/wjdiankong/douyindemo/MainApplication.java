package cn.wjdiankong.douyindemo;

import com.ss.android.common.applog.GlobalContext;
import com.ss.android.common.applog.UserInfo;

import android.app.Application;
import android.util.Log;
import cn.wjdiankong.hookpms.ServiceManagerWraper;

public class MainApplication extends Application{
	@Override
	public void onCreate() {
		super.onCreate();
		
		Log.i("jw", "onCreate");
		
		ServiceManagerWraper.hookPMS(this.getApplicationContext());
		GlobalContext.setContext(getApplicationContext());
		
		Log.i("jw", "2222");
		try{
			System.loadLibrary("hook");
//			System.loadLibrary("userinfo");//抖音&火山
			System.loadLibrary("core");//快手
//			System.loadLibrary("c++_shared");//快手
			System.loadLibrary("kwsgmain");//快手
//			System.loadLibrary("te");//秒拍
		}catch(Exception e){
			Log.i("jw", "load so err:"+Log.getStackTraceString(e));
		}
		
		Log.i("jw", "1111");
		//抖音初始化操作
//		UserInfo.setAppId(2);
//		int result = UserInfo.initUser("a3668f0afac72ca3f6c1697d29e0e1bb1fef4ab0285319b95ac39fa42c38d05f");
//		Log.i("jw", "result:"+result);
		
	}
}
