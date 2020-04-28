package cn.wjdiankong.levideo.miaopai.data;

import org.json.JSONObject;

import android.text.TextUtils;
import cn.wjdiankong.levideo.data.LevideoData;
import cn.wjdiankong.levideo.net.Utils;

public class MiaopaiVideoData extends LevideoData{
	
	public static MiaopaiVideoData fromJSONData(String jsonStr){
		MiaopaiVideoData data = new MiaopaiVideoData();
		if(TextUtils.isEmpty(jsonStr)){
			return data;
		}
		try{
			JSONObject json = new JSONObject(jsonStr);
			data.videoPlayUrl = json.optString("play_url");
			data.videoDownloadUrl = data.videoPlayUrl;
			data.coverImgUrl = json.optString("pic");
			data.title = json.optString("title");
			data.playCount = json.optInt("views_count");
			JSONObject uploadJson = json.optJSONObject("upload");
			if(uploadJson != null){
				data.createTime = uploadJson.optLong("finish_time_org");
				data.videoDuration = uploadJson.optString("length_nice");
				data.videoWidth = uploadJson.optInt("width");
				data.videoHeight = uploadJson.optInt("height");
			}
			
			JSONObject authorJson = json.optJSONObject("user");
			if(authorJson != null){
				data.authorName = authorJson.optString("nick");
				data.authorImgUrl = authorJson.optString("icon");
			}
			
			data.formatTimeStr = Utils.formatTimeStr(data.createTime);
			data.formatPlayCountStr = Utils.formatNumber(data.playCount);
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
		return "playurl:"+videoPlayUrl+",thumburl:"+coverImgUrl+",title:"+title
				+",time:"+createTime+",count:"+playCount+",authorName:"+authorName
				+",authorpic:"+authorImgUrl;
	}

}
