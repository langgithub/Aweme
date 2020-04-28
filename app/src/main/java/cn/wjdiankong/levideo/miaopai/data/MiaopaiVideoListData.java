package cn.wjdiankong.levideo.miaopai.data;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import cn.wjdiankong.levideo.data.LevideoData;

public class MiaopaiVideoListData {
	
	public List<LevideoData> videoDataList;
	
	public static MiaopaiVideoListData fromJSONData(String str){
		MiaopaiVideoListData listData = new MiaopaiVideoListData();
		try{
			JSONObject json = new JSONObject(str);
			JSONArray jsonAry = json.getJSONObject("result").getJSONArray("list");
			listData.videoDataList = new ArrayList<LevideoData>(jsonAry.length());
			for(int i=0;i<jsonAry.length();i++){
				String channelJsonStr = jsonAry.getJSONObject(i).getJSONObject("channel").toString(); 
				MiaopaiVideoData data = MiaopaiVideoData.fromJSONData(channelJsonStr);
				if(data != null){
					listData.videoDataList.add(data);
				}
			}
		}catch(Exception e){
		}
		return listData;
	}
	
	@Override
	public String toString(){
		return videoDataList.toString();
	}

}
