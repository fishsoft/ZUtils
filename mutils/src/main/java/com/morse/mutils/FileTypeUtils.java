package com.morse.mutils;

import android.annotation.SuppressLint;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @author Morse
 * 
 * @date 2015-12-23 下午5:59:42
 */
public class FileTypeUtils {
	public static String sFileExtensions;

	// Audio file types
	public static final int FILE_TYPE_MP3 = 101;
	public static final int FILE_TYPE_M4A = 102;
	public static final int FILE_TYPE_WAV = 103;
	public static final int FILE_TYPE_AMR = 104;
	public static final int FILE_TYPE_AWB = 105;
	public static final int FILE_TYPE_WMA = 106;

	// Video file types
	public static final int FILE_TYPE_AVI = 107;
	public static final int FILE_TYPE_MP4 = 108;
	public static final int FILE_TYPE_M4V = 109;
	public static final int FILE_TYPE_3GPP = 110;
	public static final int FILE_TYPE_RMVB = 111;
	public static final int FILE_TYPE_WMV = 112;

	// Image file types
	public static final int FILE_TYPE_JPEG = 201;
	public static final int FILE_TYPE_GIF = 202;
	public static final int FILE_TYPE_PNG = 203;
	public static final int FILE_TYPE_BMP = 204;
	public static final int FILE_TYPE_WBMP = 205;

	// apk
	public static final int FILE_TYPE_APK = 301;

	// 文本
	public static final int FILE_TYPE_C = 401;
	public static final int FILE_TYPE_CONF = 402;
	public static final int FILE_TYPE_CPP = 403;
	public static final int FILE_TYPE_DOC = 404;
	public static final int FILE_TYPE_H = 405;
	public static final int FILE_TYPE_HTML = 406;
	public static final int FILE_TYPE_JAVA = 407;
	public static final int FILE_TYPE_LOG = 408;
	public static final int FILE_TYPE_PDF = 409;
	public static final int FILE_TYPE_TXT = 410;
	public static final int FILE_TYPE_XML = 411;
	public static final int FILE_TYPE_ZIP = 412;

	// 静态内部类
	static class MediaFileType {

		int fileType;
		String mimeType;

		MediaFileType(int fileType, String mimeType) {
			this.fileType = fileType;
			this.mimeType = mimeType;
		}
	}

	private static HashMap<String, MediaFileType> sFileTypeMap = new HashMap<String, MediaFileType>();
	public static HashMap<String, Integer> sMimeTypeMap = new HashMap<String, Integer>();

	@SuppressLint("UseValueOf")
	static void addFileType(String extension, int fileType, String mimeType) {
		sFileTypeMap.put(extension, new MediaFileType(fileType, mimeType));
		sMimeTypeMap.put(mimeType, new Integer(fileType));
	}

	static {
		addFileType("MP3", FILE_TYPE_MP3, "audio/mpeg");
		addFileType("M4A", FILE_TYPE_M4A, "audio/mp4");
		addFileType("WAV", FILE_TYPE_WAV, "audio/x-wav");
		addFileType("AMR", FILE_TYPE_AMR, "audio/amr");
		addFileType("AWB", FILE_TYPE_AWB, "audio/amr-wb");
		addFileType("WMA", FILE_TYPE_WMA, "audio/x-ms-wma");

		addFileType("AVI", FILE_TYPE_AVI, "audio/avi");
		addFileType("MP4", FILE_TYPE_MP4, "video/mp4");
		addFileType("M4V", FILE_TYPE_M4V, "video/mp4");
		addFileType("3GP", FILE_TYPE_3GPP, "video/3gpp");
		addFileType("3GPP", FILE_TYPE_3GPP, "video/3gpp");
		addFileType("RMVB", FILE_TYPE_RMVB, "video/rmvb");
		addFileType("WMV", FILE_TYPE_WMV, "video/x-ms-wmv");

		addFileType("JPG", FILE_TYPE_JPEG, "image/jpeg");
		addFileType("JPEG", FILE_TYPE_JPEG, "image/jpeg");
		addFileType("GIF", FILE_TYPE_GIF, "image/gif");
		addFileType("PNG", FILE_TYPE_PNG, "image/png");
		addFileType("BMP", FILE_TYPE_BMP, "image/x-ms-bmp");
		addFileType("WBMP", FILE_TYPE_WBMP, "image/vnd.wap.wbmp");

		addFileType("APK", FILE_TYPE_APK, "application/vnd.android.package-archive");

		addFileType("C", FILE_TYPE_C, "text/plain");
		addFileType("CONF", FILE_TYPE_CONF, "text/plain");
		addFileType("CPP", FILE_TYPE_CPP, "text/plain");
		addFileType("DOC", FILE_TYPE_DOC, "application/msword");
		addFileType("H", FILE_TYPE_H, "text/plain");
		addFileType("HTML", FILE_TYPE_HTML, "text/html");
		addFileType("JAVA", FILE_TYPE_JAVA, "text/plain");
		addFileType("LOG", FILE_TYPE_LOG, "text/plain");
		addFileType("PDF", FILE_TYPE_PDF, "application/pdf");
		addFileType("TXT", FILE_TYPE_TXT, "text/plain");
		addFileType("XML", FILE_TYPE_XML, "text/plain");
		addFileType("XML", FILE_TYPE_ZIP, "application/zip");

		// compute file extensions list for native Media Scanner
		StringBuilder builder = new StringBuilder();
		Iterator<String> iterator = sFileTypeMap.keySet().iterator();

		while (iterator.hasNext()) {
			if (builder.length() > 0) {
				builder.append(',');
			}
			builder.append(iterator.next());
		}
		sFileExtensions = builder.toString();
	}

	@SuppressLint("DefaultLocale")
	public static int getFileType(String path) {
		int lastDot = path.lastIndexOf(".");
		if (lastDot < 0)
			return 0;
		if (null == sFileTypeMap.get(path.substring(lastDot + 1).toUpperCase()))
			return 0;
		return sFileTypeMap.get(path.substring(lastDot + 1).toUpperCase()).fileType;
	}

	@SuppressLint("DefaultLocale")
	public static String getMimeType(String path) {
		int lastDot = path.lastIndexOf(".");
		if (lastDot < 0)
			return null;
		if (null == sFileTypeMap.get(path.substring(lastDot + 1).toUpperCase()))
			return null;
		return sFileTypeMap.get(path.substring(lastDot + 1).toUpperCase()).mimeType;
	}
}
