/**
 * 
 */
package com.morse.mutils;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Morse
 * @date 2016-2-26
 * @Time 下午6:00:22
 */
public class MediaUtils {
	public static List<Mp3Info> getMp3Infos(Context context) {
		Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null,
				null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
		List<Mp3Info> mp3Infos = new ArrayList<Mp3Info>();
		for (int i = 0; i < cursor.getColumnCount(); i++) {
			cursor.moveToNext();
			long id = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media._ID)); // 音乐id
			String title = cursor.getString((cursor.getColumnIndex(MediaStore.Audio.Media.TITLE))); // 音乐标题
			String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)); // 艺术家
			String album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM)); // 专辑
			String displayName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
			long albumId = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
			long duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION)); // 时长
			long size = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE)); // 文件大小
			String url = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA)); // 文件路径
			int isMusic = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC)); // 是否为音乐
			if (isMusic != 0) { // 只把音乐添加到集合当中
				Mp3Info mp3Info = new Mp3Info();
				mp3Info.setId(id);
				mp3Info.setTitle(title);
				mp3Info.setArtist(artist);
				mp3Info.setAlbum(album);
				mp3Info.setDisplayName(displayName);
				mp3Info.setAlbumId(albumId);
				mp3Info.setDuration(duration);
				mp3Info.setSize(size);
				mp3Info.setUrl(url);
				mp3Infos.add(mp3Info);
			}
		}
		return mp3Infos;
	}

	/**
	 * 向List集合里添加Map对象，每个Map对象存放一首音乐的所有属性
	 * 
	 * @param mp3Infos
	 * @return
	 */
	public static List<Map<String, String>> getMusicMaps(List<Mp3Info> mp3Infos) {
		List<Map<String, String>> mp3Lists = new ArrayList<Map<String, String>>();
		Iterator<Mp3Info> iterator = mp3Infos.iterator();
		while (iterator.hasNext()) {
			Mp3Info mp3Info = (Mp3Info) iterator.next();
			Map<String, String> map = new HashMap<String, String>();
			map.put("title", mp3Info.getTitle());
			map.put("Artist", mp3Info.getArtist());
			map.put("album", mp3Info.getAlbum());
			map.put("displayName", mp3Info.getDisplayName());
			map.put("albumId", String.valueOf(mp3Info.getAlbumId()));
			map.put("duration", formaTime(mp3Info.getDuration()));
			map.put("size", String.valueOf(mp3Info.getSize()));
			map.put("url", mp3Info.getUrl());
			mp3Lists.add(map);
		}
		return mp3Lists;
	}

	/**
	 * 格式化时间，把毫秒转换成分：秒格式
	 * 
	 * @param time
	 * @return
	 */
	public static String formaTime(long time) {
		String min = time / (1000 * 60) + "";
		String sec = time % (1000 * 60) + "";
		if (min.length() < 2) {
			min = "0" + time / (1000 * 60) + "";
		} else {
			min = time / (1000 * 60) + "";
		}
		if (sec.length() == 4) {
			sec = "0" + (time % (1000 * 60)) + "";
		} else if (sec.length() == 3) {
			sec = "00" + (time % (1000 * 60)) + "";
		} else if (sec.length() == 2) {
			sec = "000" + (time % (1000 * 60)) + "";
		} else if (sec.length() == 1) {
			sec = "0000" + (time % (1000 * 60)) + "";
		}
		return min + ":" + sec.trim().substring(0, 2);
	}
}
