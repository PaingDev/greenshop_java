package com.greenlight.shop.util;

import java.io.File;
import java.nio.file.FileSystems;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentPath {
	public static String userDirectory;
	static {
		userDirectory = FileSystems.getDefault()
		        .getPath("")
		        .toAbsolutePath()
		        .toString();
		 String fileDirectory = userDirectory+"/items";
		 File file = new File(fileDirectory);
		 if(!file.exists()) {
			 file.mkdir();
		 }
		 
	}
	
	/**
	 * 
	 * @param extName 
	 * @param extType , extension file type
	 * @return array size 2. first is generatedFileName, second is full file path
	 */
	public static String[] generateNameAndPath(String extName, String extType) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy_MM_dd_hh_mm_sss");
		String date = df.format(new Date());
		String fileName = date+extName+extType;
		String filePath = userDirectory+ "/items/"+fileName;
		return new String[] {fileName, filePath};
	}

}
