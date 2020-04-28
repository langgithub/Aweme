package cn.wjdiankong.douyindemo;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import com.liqy.aweme.R;
import com.ss.android.common.applog.GlobalContext;
import com.yixia.utils.TinyEncode;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cn.wjdiankong.levideo.douyin.data.DouyinVideoListData;
import cn.wjdiankong.levideo.douyin.utils.DouyinUtils;
import cn.wjdiankong.levideo.hotsoon.data.HotsoonVideoListData;
import cn.wjdiankong.levideo.hotsoon.utils.HotsoonUtils;
import cn.wjdiankong.levideo.kuaishou.data.KuaishouVideoListData;
import cn.wjdiankong.levideo.kuaishou.utils.C2285d;
import cn.wjdiankong.levideo.kuaishou.utils.KuaishouUtils;
import cn.wjdiankong.levideo.miaopai.data.MiaopaiVideoListData;
import cn.wjdiankong.levideo.miaopai.utils.MiaopaiUtils;
import cn.wjdiankong.levideo.net.HttpClientUtils;
import cn.wjdiankong.levideo.net.HttpClientUtils.ResponseCallback;

public class MainActivity extends Activity {

	private Button bt;
	private TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bt=findViewById(R.id.bt1);
		tv=findViewById(R.id.tv1);
		Log.i("jw", String.valueOf(Build.VERSION.SDK_INT));
//		getDouyinListData();
//		getHuoshanListData();
//		getMiaopaiListData();
//		getApplicationContext().getPackageManager()
		bt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				try {
					getKuaishouListData2();
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void getKuaishouListData(){
		Map<String, String> bodyMap = KuaishouUtils.getBodyParams(1, 0);
		Map<String, String> paramsMap = KuaishouUtils.getCommonParams();
		String sig = KuaishouUtils.getSign(GlobalContext.getContext(), paramsMap, bodyMap);
		if(!TextUtils.isEmpty(sig)){
			bodyMap.put("sig", sig);
		}
		String url = KuaishouUtils.getUrl();
		HttpClientUtils httpClient = new HttpClientUtils();
		httpClient.setUrl(url).setParams(bodyMap).setResponseCallback(new ResponseCallback(){
			@Override
			public void responseFinish(final String result) {
				KuaishouVideoListData listData = KuaishouVideoListData.fromJSON(result);
				Log.i("jw", "kuaishou data:"+listData);
			}

			@Override
			public void responseFinish(byte[] result) {
			}

			@Override
			public void reponseFail() {
			}

		}).execPost();
	}

	public void getKuaishouListData2() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String str="709c61a16287c47a5bda7aff9680877b";
		String str2="af4a5a9bac316a349a055d106d96590f";
		Map<String, String> bodyMap = KuaishouUtils.getBodyParams2();
		Map<String, String> paramsMap = KuaishouUtils.getCommonParams2();
		String sig = KuaishouUtils.getSign(GlobalContext.getContext(), paramsMap, bodyMap);
		this.tv.setText("sign= "+sig);
		Log.i("jw", "sign1"+sig);
		if(!TextUtils.isEmpty(sig)){
			bodyMap.put("sig", sig);
			bodyMap.put("__NStokensig",KuaishouUtils.__NStokensig(sig,str));
//			System.out.println(new App().getPackageName());
			String sign3=KuaishouUtils.__NS_sig3(KuaishouUtils.SEARCH_NEW+str2);
			System.out.println(sign3);
//			bodyMap.put("__NS_sig3",sign3);
			bodyMap.put("__NS_sig3","2203148449d858ce0d442d12c8d25edfe8572802f0");
		}
		String url = KuaishouUtils.getSearchNewUrl();
		HttpClientUtils httpClient = new HttpClientUtils();
		httpClient.setUrl(url).setParams(bodyMap).setResponseCallback(new ResponseCallback(){
			@Override
			public void responseFinish(final String result) {
				System.out.println(result);
				KuaishouVideoListData listData = KuaishouVideoListData.fromJSON(result);
				Log.i("jw", "kuaishou data:"+listData);
			}

			@Override
			public void responseFinish(byte[] result) {
			}

			@Override
			public void reponseFail() {
				Log.i("jw", "kuaishou error");
			}

		}).execPost();
	}
	
	public void getMiaopaiListData(){
		long time = System.currentTimeMillis();
		String url = MiaopaiUtils.getUrl(this, 1, time, time);
		HttpClientUtils httpClient = new HttpClientUtils();
		httpClient.setUrl(url).setResponseByte(true).setHeaderParams(
				MiaopaiUtils.getHeaderParams(time)).setResponseCallback(
				new ResponseCallback() {
			@Override
			public void responseFinish(final byte[] result) {
				String resultStr = TinyEncode.DecodeResult(result);
				MiaopaiVideoListData listData = MiaopaiVideoListData.fromJSONData(resultStr);
				Log.i("jw", "miaopai data:"+listData);
			}
			
			@Override
			public void responseFinish(String result) {
			}
			
			@Override
			public void reponseFail() {
				Log.i("jw", "get data err!");
			}
		}).execGet();
	}
	
	public void getDouyinListData(){
		String url = DouyinUtils.getEncryptUrl(this, 0, 0);
		HttpClientUtils httpClient = new HttpClientUtils();
		httpClient.setUrl(url).setResponseCallback(new ResponseCallback() {
			@Override
			public void responseFinish(final String result) {
				DouyinVideoListData listData = DouyinVideoListData.fromJSONData(result);
				Log.i("jw", "douyin data:"+listData);
			}
			
			@Override
			public void reponseFail() {
			}

			@Override
			public void responseFinish(byte[] result) {
				
			}
		}).execGet();
	}
	
	public void getHuoshanListData(){
		String url = HotsoonUtils.getEncryptUrl(this, 0, -1);
		HttpClientUtils httpClient = new HttpClientUtils();
		httpClient.setUrl(url).setResponseCallback(new ResponseCallback() {
			@Override
			public void responseFinish(final String result) {
				HotsoonVideoListData listData = HotsoonVideoListData.fromJSONData(result);
				Log.i("jw", "huoshan data:"+listData);
			}
			
			@Override
			public void reponseFail() {
			}

			@Override
			public void responseFinish(byte[] result) {
				
			}
		}).execGet();
	}

}
