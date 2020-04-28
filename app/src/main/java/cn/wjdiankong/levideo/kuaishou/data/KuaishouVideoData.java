package cn.wjdiankong.levideo.kuaishou.data;

import org.json.JSONArray;
import org.json.JSONObject;

import android.text.TextUtils;
import cn.wjdiankong.levideo.data.LevideoData;
import cn.wjdiankong.levideo.net.Utils;

public class KuaishouVideoData extends LevideoData{
	
	public static KuaishouVideoData fromJSONData(String jsonStr){
		KuaishouVideoData data = new KuaishouVideoData();
		try{
			JSONObject json = new JSONObject(jsonStr);
			data.title = json.optString("caption");
			data.createTime = json.optLong("timestamp");
			JSONArray jsonAry = json.optJSONArray("main_mv_urls");
			if(jsonAry != null && jsonAry.length() > 0){
				if(jsonAry.getJSONObject(0) != null){
					data.videoPlayUrl = jsonAry.getJSONObject(0).optString("url");
				}
			}else{
				jsonAry = json.optJSONArray("main_mv_urls_h265");
				if(jsonAry != null && jsonAry.length() > 0){
					if(jsonAry.getJSONObject(0) != null){
						data.videoPlayUrl = jsonAry.getJSONObject(0).optString("url");
					}
				}
			}
			jsonAry = json.optJSONArray("headurls");
			if(jsonAry != null && jsonAry.length() > 0){
				if(jsonAry.getJSONObject(0) != null){
					data.authorImgUrl = jsonAry.getJSONObject(0).optString("url");
				}
			}
			data.authorName = json.optString("user_name");
			data.authorSex = "M".equals(json.optString("user_sex")) ? 1 : 0;
			data.videoDownloadUrl = data.videoPlayUrl;
			data.coverImgUrl = json.getJSONArray("cover_thumbnail_urls").getJSONObject(0).optString("url");
			
			JSONObject extJson = json.optJSONObject("ext_params");
			if(extJson != null){
				data.videoWidth = extJson.optInt("w");
				data.videoHeight = extJson.optInt("h");
			}
			
			data.playCount = json.optInt("view_count");
			data.likeCount = json.optInt("like_count");
			
			JSONObject musicJson = json.optJSONObject("music");
			if(musicJson != null){
				data.musicName = musicJson.optString("artist");
				jsonAry = musicJson.optJSONArray("audioUrls");
				if(jsonAry != null && jsonAry.length() > 0){
					if(jsonAry.optJSONObject(0) != null){
						data.musicImgUrl = jsonAry.optJSONObject(0).optString("avatarUrl");
					}
				}
			}
			if(data.createTime > 0){
				data.formatTimeStr = Utils.formatTimeStr(data.createTime);
			}else{
				data.formatTimeStr = json.optString("time");
			}
			data.formatPlayCountStr = Utils.formatNumber(data.playCount);
			data.formatLikeCountStr = Utils.formatNumber(data.likeCount);
			if(!TextUtils.isEmpty(data.authorName)){
				if(data.authorName.length() > 7){
					data.filterUserNameStr = Utils.filterStrBlank(data.authorName.substring(0, 7)+"...");
				}else{
					data.filterUserNameStr = Utils.filterStrBlank(data.authorName);
				}
			}
		}catch(Exception e){
		}
		if(TextUtils.isEmpty(data.videoPlayUrl)){
			return null;
		}
		return data;
	}

	@Override
	public String toString(){
		return "playurl:"+videoPlayUrl+",thumburl:"+coverImgUrl+",title:"+title+",authorname:"+authorName+",authorurl:"+authorImgUrl
				+",playcount:"+playCount+",videowidth:"+videoWidth+",videoHeight:"+videoHeight;
	}
	
}
