package cn.wjdiankong.levideo.kuaishou.data;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import cn.wjdiankong.levideo.data.LevideoData;

public class KuaishouVideoListData {
	
	public List<LevideoData> videoDataList;
	public int pursor;
	
	public static KuaishouVideoListData fromJSON(String jsonStr){
		KuaishouVideoListData dataList = new KuaishouVideoListData();
		try{
			JSONObject json = new JSONObject(jsonStr);
			JSONArray jsonAry = json.getJSONArray("feeds");
			dataList.videoDataList = new ArrayList<LevideoData>(jsonAry.length());
			for(int i=0;i<jsonAry.length();i++){
				KuaishouVideoData data = KuaishouVideoData.fromJSONData(jsonAry.getJSONObject(i).toString());
				if(data != null){
					dataList.videoDataList.add(data);
				}
			}
		}catch(Exception e){
		}
		return dataList;
	}
	
	@Override
	public String toString(){
		return "pursor:" + pursor + ",videolist:" + videoDataList;
	}

}
