package cn.wjdiankong.levideo.kuaishou.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.kuaishou.android.security.mainplugin.JNICLibrary;
import com.ss.android.common.applog.GlobalContext;
import com.yxcorp.gifshow.util.CPU;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;

import cn.wjdiankong.levideo.net.NetworkUtil;
import cn.wjdiankong.levideo.net.Utils;

public class KuaishouUtils {
	
	public static String appVersionCode = "5.3";
	public static String appVersionName = "5.3.4.5093";
	
	public static final String PKGNAME = "com.smile.gifmaker";
	
	public final static String HOST_NAME = "http://api.gifshow.com";
	public final static String HOST_NAME2 = "https://apis2.ksapisrv.com";
	public final static String GETDATA_JSON_URL = "/rest/n/feed/hot";
	public final static String SEARCH_NEW = "/rest/n/search/new";
	
	public static String getUrl(){
		String urlStr = null;
		try{
			Map<String,String> paramsMap = getCommonParams();
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

	public static String getSearchNewUrl(){
		String urlStr = null;
		try{
			Map<String,String> paramsMap = getCommonParams2();
			StringBuilder paramsSb = new StringBuilder();
			for(String key : paramsMap.keySet()){
				paramsSb.append(key+"="+paramsMap.get(key)+"&");
			}
			urlStr = HOST_NAME2 + SEARCH_NEW +"?" + paramsSb.toString();
			if(urlStr.endsWith("&")){
				urlStr = urlStr.substring(0, urlStr.length()-1);
			}
		}catch(Exception e){
		}
		return urlStr;
	}
	
	private static String getSmallVer(String versionName){
		try{
			String[] strAry = appVersionName.split(".");
			return strAry[0]+"."+strAry[1];
		}catch(Exception e){
		}
		return appVersionName;
	}
	
	/**
	 * 公共参数
	 * @return
	 */
	@SuppressLint("DefaultLocale")
	public static Map<String, String> getCommonParams(){
		Map<String, String> params = new HashMap<String,String>();
		params.put("app", "0");
		params.put("lon", "");
		params.put("lat", "");
		params.put("c", "360APP");
		params.put("sys", "ANDROID_4.4.4");
		params.put("mod", Utils.getDeviceFactory()+"("+Utils.getDeviceName()+")");
		params.put("did", "ANDROID_b39d9675ee6af5b2");
		params.put("ver", getSmallVer(appVersionName));
		params.put("net", NetworkUtil.getNetworkType(GlobalContext.getContext()).toUpperCase());
		params.put("country_code", "CN");
		params.put("iuid", "");
		params.put("appver", appVersionName);
		params.put("oc", "360APP");
		params.put("ftt", "");
		params.put("ud", "0");
		params.put("language", "zh-cn");
		return params;
	}

	/**
	 * 公共参数2
	 * @return
	 */
	@SuppressLint("DefaultLocale")
	public static Map<String, String> getCommonParams2(){
		Map<String, String> params = new HashMap<String,String>();
		params.put("mod", "Meizu(MX4)");
		params.put("lon", "116.295474");
		params.put("country_code", "CN");
		params.put("kpn", "KUAISHOU");
		params.put("oc", "ALI_CPD,9");
		params.put("egid", "DFP07E53E773AC24319F9C90A985062D60DBB5062FF4751244877A91440FC989");
		params.put("sbh","66");
		params.put("hotfix_ver", "");
		params.put("sh", "1920");
		params.put("appver", "7.3.10.13314");
		params.put("nbh","0");
		params.put("socName", ": MediaTek MT6595");
		params.put("max_memory", "256");
		params.put("isp", "");
		params.put("kcv", "188");
		params.put("browseType", "1");
		params.put("kpf", "ANDROID_PHONE");
		params.put("ddpi","480");
		params.put("did", "ANDROID_26332057d592614a");
		params.put("net", "WIFI");
		params.put("app", "0");
		params.put("ud", "1537788138");
		params.put("c", "ALI_CPD,9");
		params.put("sys", "ANDROID_5.1");
		params.put("sw", "1152");
		params.put("ftt", "");
		params.put("ll", "CUW6n1OQ6UNAEV+0xwvpEl1A");
		params.put("language", "zh-cn");
		params.put("iuid", "");
		params.put("lat", "39.824717");
		params.put("did_gt", "1587367630144");
		params.put("ver", "7.3");
		return params;
	}
	
	/**
	 * 上拉数据规律：
	 * page=refreshTimes始终比id小1
	 * 
	 * 下拉数据规律：
	 * page=1，refreshTimes和id递增
	 * 
	 * 总结：全局refreshTimes和id值都在递增id=1,refreshTimes=0，并且id值始终比refreshTimes多1，对于page从1开始下拉增1，上拉归1，下拉从1开始从新计算
	 */
	public static Map<String, String> getBodyParams(int page, int refreshTimes){
		Map<String, String> bodyMap = new HashMap<String, String>();
		bodyMap.put("type", "7");
		bodyMap.put("coldStart", "false");
		bodyMap.put("count", "20");
		bodyMap.put("pcursor", "1");
		bodyMap.put("os", "android");
		bodyMap.put("client_key", "3c2cd3f3");
		bodyMap.put("pv", "false");
		bodyMap.put("page", page+"");//变动
		bodyMap.put("id", (refreshTimes+1)+"");//变动
		bodyMap.put("refreshTimes", refreshTimes+"");//变动
		return bodyMap;
	}

	// {"relatedTab":"","fromPage":"13","isRecoRequest":"false",
	// "kuaishou.api_st":"Cg9rdWFpc2hvdS5hcGkuc3QSoAHujDCBaSak8WGZE5fWwpZ8MNg8F9Jv2YI0c6nTvalW4u6LxlVOtqNioytk-22MDC2LfbJhVX3YM7dTGF3SEfbDdhhWcTqbFtVFST_BiSITYHtRmmNgupsCwegTk44Fq4hcH0hZqHicGuSkZheakCXDVuad7J6VOThc8a_ceFGm90ewlo9Z2ZGnoLngZwzSL3YHO9b2MnNv0Ukf8WebfTjpGhLmHaQkwpVHXJtSDkMe1YHGH_QiIEDCHrGzOGgIf2ap12SRl5nNd4daZk1jKfs2Qbr1UI9lKAUwAQ",
	// "requestId":"","token":"afe301c85f034279b2d4e2c63595bc3a-1537880538",
	// "keyword":"13岁男孩1小时内2次跳楼","client_key":"3c2cd3f3",
	// "fromPageSessionId":"MzAxXzE1Mzc4ODA1MzhfMTU4NTgxNTAyMDU2OV9fMzY5NQ",
	// "requestTabId":"","os":"android",}
	public static Map<String, String> getBodyParams2(){
		Map<String, String> bodyMap = new HashMap<String, String>();
		bodyMap.put("relatedTab", "");
		bodyMap.put("fromPage", "13");
		bodyMap.put("isRecoRequest", "false");
		bodyMap.put("kuaishou.api_st", "Cg9rdWFpc2hvdS5hcGkuc3QSoAFnHEZWhI9_Rm4Tx-2W60WY-WBxcyqZtFU9Jn46Q4gbOYB17H9LA_WDTrCM1ot58qIax29GhsFpCWtmwO0GGr3545xM6fau3cVTpfMjTOZUHRQfmm5w7goql3YZzw3xFiPCr-u1dMJ97l16sclF21YS54NGwmvNE1U4RuQindJYh8SqoEbvprs06IqZlzJVZwg2pAmk0W6DE0k-0FzDV9yEGhJThZNu-rRPH4Mw3KnOATqUKJgiIF6vMy4HmI7w0CtXzbucW6uxHUTx7qfwGFmj5mEtUqBGKAUwAQ");
		bodyMap.put("token", "5d64ac4af1fe4a12bb1248b954a06343-1537788138");
		bodyMap.put("keyword", "欧阳娜娜 惊雷");
		bodyMap.put("client_key", "3c2cd3f3");
		bodyMap.put("fromPageSessionId", "MzAxXzE1Mzc3ODgxMzhfMTU4NzM3NTEwNzU0NV9fMzkzMw");//变动
		bodyMap.put("requestTabId", "");
		bodyMap.put("os", "android");
		return bodyMap;
	}

	public static String __NStokensig(String sig,String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		byte[] digest = MessageDigest.getInstance("SHA-256").digest((sig + str).getBytes("utf-8"));
		String s = C2285d.m3889a(digest);
		return s;
	}

	public static String __NS_sig3(String sig) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		///rest/n/clc/cover/showf4ff99ea28c42eead7fa8cf439395222
		Object object=mo7211a(10405,new String[]{"/rest/n/clc/cover/showf4ff99ea28c42eead7fa8cf439395222"}
		,"d7b7d042-d4f2-4012-be60-d97ff2429c17",-1, false,GlobalContext.getContext(),0,false);
		return (String) object;
	}

	public static Object mo7211a(int i, Object... objArr) {
		try {
			return JNICLibrary.doCommandNative(i, objArr);
		} catch (Throwable th) {
			th.printStackTrace();
			return null;
		}
	}


	@SuppressLint("NewApi")
	public static String getSign(Context ctx, Map<String, String> map, Map<String, String> map2) {
		List<String> arrayList = new ArrayList<String>();
        for (Entry<String,String> entry : map.entrySet()) {
            arrayList.add(entry.getKey() + "=" + (entry.getValue() == null ? "" : entry.getValue()));
        }
        for (Entry<String, String> entry : map2.entrySet()) {
            arrayList.add(((String) entry.getKey()) + "=" + (entry.getValue() == null ? "" : (String) entry.getValue()));
        }
        try {
            Collections.sort(arrayList);
        } catch (Exception e) {
        }
        
        StringBuilder sb = new StringBuilder();
        for(String str : arrayList){
        	sb.append(str);
        }
		System.out.println(TextUtils.join("", arrayList));
        System.out.println(sb.toString());
        String sign= CPU.getClock(ctx, TextUtils.join("", arrayList).getBytes(Charset.forName("UTF-8")), 22);
        return sign;
    }


}
