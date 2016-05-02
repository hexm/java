package com.icbc.hexm.music.kugou;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.icbc.hexm.message.resp.Music;
import com.icbc.hexm.util.HttpRequestUtil;
import com.icbc.hexm.util.Log4jUtil;

/**
 * 使用json-lib构造和解析Json数据
 * 
 * @author Alexia
 * @date 2013/5/23
 *
 */
public class KugouMusicService {

    /**
     * 构造Json数据
     * 
     * @return
     */
	@SuppressWarnings("unchecked")
	public static String BuildJson() {

        // JSON格式数据解析对象
        JSONObject jo = new JSONObject();

        // 下面构造两个map、一个list和一个Employee对象
        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("name", "Alexia");
        map1.put("sex", "female");
        map1.put("age", "23");

        Map<String, String> map2 = new HashMap<String, String>();
        map2.put("name", "Edward");
        map2.put("sex", "male");
        map2.put("age", "24");

        List<Map> list = new ArrayList<Map>();
        list.add(map1);
        list.add(map2);

        KugouMusic employee = new KugouMusic();

        // 将Map转换为JSONArray数据
        JSONArray ja1 = JSONArray.fromObject(map1);
        // 将List转换为JSONArray数据
        JSONArray ja2 = JSONArray.fromObject(list);
        // 将Bean转换为JSONArray数据
        JSONArray ja3 = JSONArray.fromObject(employee);

        System.out.println("JSONArray对象数据格式：");
        System.out.println(ja1.toString());
        System.out.println(ja2.toString());
        System.out.println(ja3.toString());

        // 构造Json数据，包括一个map和一个Employee对象
        jo.put("map", ja1);
        jo.put("employee", ja2);
        System.out.println("\n最终构造的JSON数据格式：");
        System.out.println(jo.toString());

        return jo.toString();

    }

    /**
     * 解析Json数据 歌曲列表
     * 
     * @param jsonString Json数据字符串
     */
    public static List<KugouMusic> parseKugouJson(String jsonString) {

        JSONObject jb = JSONObject.fromObject(jsonString);
//        JSONArray ja = jb.getJSONArray("employee");
        JSONArray ja = jb.getJSONObject("data").getJSONArray("info");

        List<KugouMusic> empList = new ArrayList<KugouMusic>();

        // 循环添加对象（可能有多个）
        for (int i = 0; i < ja.size(); i++) {
        	KugouMusic music = new KugouMusic();

        	music.setFilename(ja.getJSONObject(i).getString("filename"));
        	music.setExtname(ja.getJSONObject(i).getString("extname"));
        	music.setM4afilesize(ja.getJSONObject(i).getString("m4afilesize"));
        	music.setK320hash(ja.getJSONObject(i).getString("320hash"));
        	music.setMvhash(ja.getJSONObject(i).getString("mvhash"));
        	music.setPrivilege(ja.getJSONObject(i).getString("privilege"));
        	music.setFilesize(ja.getJSONObject(i).getString("filesize"));
        	music.setBitrate(ja.getJSONObject(i).getString("bitrate"));
        	music.setOwnercount(ja.getJSONObject(i).getString("ownercount"));
        	music.setTopic(ja.getJSONObject(i).getString("topic"));
        	music.setK320filesize(ja.getJSONObject(i).getString("320filesize"));
        	music.setIsnew(ja.getJSONObject(i).getString("isnew"));
        	music.setDuration(ja.getJSONObject(i).getString("duration"));
        	music.setSingername(ja.getJSONObject(i).getString("singername"));
        	music.setK320privilege(ja.getJSONObject(i).getString("320privilege"));
        	music.setHash(ja.getJSONObject(i).getString("hash"));
        	music.setSrctype(ja.getJSONObject(i).getString("srctype"));
        	music.setSqfilesize(ja.getJSONObject(i).getString("sqfilesize"));
        	music.setSqprivilege(ja.getJSONObject(i).getString("sqprivilege"));
        	music.setSqhash(ja.getJSONObject(i).getString("sqhash"));
        	music.setFeetype(ja.getJSONObject(i).getString("feetype"));

            empList.add(music);
        }

        System.out.println("\n将Json数据转换为对象：");
        for (int i = 0; i < empList.size(); i++) {
        	KugouMusic emp = empList.get(i);
            System.out.println(emp);
        }
        
        return empList;
    }
    
    /**
     * 解析歌曲信息
     * @param jsonString
     * @return 下载地址
     */
    public static String parseKugouSongJson(String jsonString) {
    	JSONObject jb = JSONObject.fromObject(jsonString);
    	return jb.getString("url");
    }
    
    /**
     * 根据歌名搜索歌曲，默认返回第一条歌曲的下载地址
     * @param songName
     * @return
     * @throws UnsupportedEncodingException 
     */
    private static String searchSong(String songName)  {
    	Log4jUtil.getLogger().debug("searchSong begin");
    	if(songName == null || "".equals(songName.trim())) {
    		return "";
    	}
    	String songNameEncode;
		try {
			songNameEncode = URLEncoder.encode(songName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}
        String listRequestUrl = "http://mobilecdn.kugou.com/api/v3/search/song?format=jsonp&keyword=" + songNameEncode + "&page=1&pagesize=10&showtype=1";
		String kugouJsonStr = HttpRequestUtil.httpRequest(listRequestUrl);
        List<KugouMusic> list = parseKugouJson(kugouJsonStr.substring(1, kugouJsonStr.length() - 1));
        String songRequestUrl = "http://m.kugou.com/app/i/getSongInfo.php?hash=" + list.get(0).getHash() + "&cmd=playInfo";
		String musicJsonStr = HttpRequestUtil.httpRequest(songRequestUrl);
        String songUrl = parseKugouSongJson(musicJsonStr);
        Log4jUtil.getLogger().debug("searchSong end. songUrl = " + songUrl);
		return songUrl;
    }
    
    /**
     * 搜索音乐
     * @param name
     * @return
     */
    public static Music searchMusic(String name) {
    	String downUrl = searchSong(name);
    	if(downUrl != null && !"".equals(downUrl)) {
    		Music music = new Music();
    		music.setHQMusicUrl(downUrl);
    		music.setMusicUrl(downUrl);
    		music.setTitle(name);
    		music.setDescription("来自KUGOU音乐");
    		return music;
    	}
    	return null;
    }

    /**
     * @param args
     * @throws UnsupportedEncodingException 
     */
    public static void main(String[] args) throws UnsupportedEncodingException {
        String buildJson = BuildJson();
        System.out.println(buildJson);
        String songNameEncode = URLEncoder.encode("今天", "UTF-8");
        //String requestUrl = "http://mobilecdn.kugou.com/api/v3/search/song?format=jsonp&keyword=%E4%BB%99%E5%89%91&page=1&pagesize=10&showtype=1";
        String listRequestUrl = "http://mobilecdn.kugou.com/api/v3/search/song?format=jsonp&keyword=" + songNameEncode + "&page=1&pagesize=10&showtype=1";
		String kugouJsonStr = HttpRequestUtil.httpRequest(listRequestUrl);
        List<KugouMusic> list = parseKugouJson(kugouJsonStr.substring(1, kugouJsonStr.length() - 1));
        //String songRequestUrl = "http://m.kugou.com/app/i/getSongInfo.php?hash=2b616f6ab9f8655210fd823b900085cc&cmd=playInfo";
        String songRequestUrl = "http://m.kugou.com/app/i/getSongInfo.php?hash=" + list.get(0).getHash() + "&cmd=playInfo";
		String musicJsonStr = HttpRequestUtil.httpRequest(songRequestUrl);
        System.out.println(musicJsonStr);
        System.out.println(parseKugouSongJson(musicJsonStr));
        
        System.out.println(searchSong("冷雨夜"));
    }

}