package cn.wjdiankong.levideo.miaopai.utils;

import java.util.HashMap;
import java.util.Map;

import com.ss.android.common.applog.GlobalContext;
import com.yixia.utils.TinyEncode;

import android.annotation.SuppressLint;
import android.app.Activity;
import cn.wjdiankong.levideo.net.NetworkUtil;
import cn.wjdiankong.levideo.net.Utils;

public class MiaopaiUtils {
	
	public static String appVersionCode = "6.7.20";
	public static String appVersionName = "6.7.20";
	
	public static final String PKGNAME = "com.yixia.videoeditor";
	
	public final static String HOST_NAME = "http://c.miaopai.com";
	public final static String GETDATA_JSON_URL = "/1/recommend/index.json";
	
	/**
	 * 第一次请求：lastUpdateTime为当前时间戳，page字段不传递
	 * 上拉数据：lastUpdateTime是上一次刷新时间，page始终是1
	 * 下拉数据：lastUpdateTime是上一次刷新时间，page递增
	 * 注意这里的时间戳都是10位，需要本地转化
	 * @param time
	 * @return
	 */
	public static String getUrl(Activity act, int page, long updateTime, long time){
		String urlStr = null;
		try{
			Map<String,String> paramsMap = getCommonParams(act, page, updateTime, time);
			StringBuilder paramsSb = new StringBuilder();
			for(String key : paramsMap.keySet()){
				paramsSb.append(key+"="+paramsMap.get(key)+"&");
			}
			urlStr = HOST_NAME + GETDATA_JSON_URL +"?" + paramsSb.toString();
			if(urlStr.endsWith("&")){
				urlStr = urlStr.substring(0, urlStr.length()-1);
			}
		}catch(Exception e){
		}
		return urlStr;
	}
	
	/**
	 * 公共参数
	 * @return
	 */
	@SuppressLint("DefaultLocale")
	public static Map<String, String> getCommonParams(Activity act, int page, long updateTime, long time){
		HashMap<String, String> params = new HashMap<String,String>();
		if(updateTime > 0){
			params.put("lastUpdateTime", updateTime+"");
		}
		if(page > 0){
			params.put("page", page+"");
		}
		params.put("timestamp", time+"");
		params.put("density", Utils.getDeviceDensity(act)+"");
		params.put("vApp", "64513");
		params.put("partnerId", "1");
		params.put("mac", Utils.getWifiMac(GlobalContext.getContext()));
		params.put("resolution", Utils.getDeviceWidth(act)+"x"+Utils.getDeviceHeight(act));
		params.put("net", "1");
		params.put("vName", appVersionName);
		params.put("type", "down");
		params.put("network", NetworkUtil.getNetworkType(GlobalContext.getContext()).toUpperCase());
		params.put("version", appVersionName);
		params.put("unique_id", "39ddd6be-0c13-3efa-beb7-e1355dcc0012");
		params.put("pName", "com.yixia.videoeditor");
		params.put("token", "");
		params.put("userId", "");
		params.put("carrier", "未知");
		params.put("dpi", Utils.getDeviceDpi(act)+"");
		params.put("abId", "70-103");
		params.put("refresh", "2");
		params.put("vOs", Utils.getOSRelease());
		params.put("os", "android");
		params.put("imei", Utils.getDeviceIMEI(GlobalContext.getContext()));
		params.put("cpu", "ARMv7");
		params.put("withExtend", "1");
		params.put("weiboUid", "normal");
		params.put("udid", "4651DBE63EB906B74D27E7CBF6E5ADB7");
		params.put("sessionid", "ee52f200ad980093490d5857195941e2");
		params.put("platformId", "1");
		params.put("ip", "218.30.116.183");
		params.put("appName", "秒拍");
		params.put("facturer", Utils.getDeviceFactory());
		params.put("pcId", "yx_web");
		params.put("channel_show", "0");
		params.put("vend", "miaopai");
		params.put("kg_udid", "55C884EBD34FA3CC60DC783EF253D218");
		params.put("brand", Utils.getDeviceFactory());
		params.put("devId", "C6B6D4ECD6FD3B266C78C62246F530A4");
		params.put("channel", "yx_web");
		params.put("first", "0");
		params.put("idfa", "");
		return params;
	}
	
	public static Map<String, String> getHeaderParams(long time){
		String signStr = TinyEncode.Sign(appVersionName, "39ddd6be-0c13-3efa-beb7-e1355dcc0012", time+"", MiaopaiUtils.GETDATA_JSON_URL);
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("sign", signStr);
		headerMap.put("udid", "4651DBE63EB906B74D27E7CBF6E5ADB7");
		headerMap.put("kg_udid", "55C884EBD34FA3CC60DC783EF253D218");
		headerMap.put("sessionid", "ee52f200ad980093490d5857195941e2");
		headerMap.put("appid", "428");
		headerMap.put("Accept-Language", "zh-Hans");
		headerMap.put("User-Agent", "Miaopai/6.7.20/64513/yx_web(Xiaomi_MI_3_19)");
		return headerMap;
	}

}
