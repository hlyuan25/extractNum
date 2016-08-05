package com.mobile.extract;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhtoneNum {
	public static void main(String[] args) {
		String fileIn = "d:/test.txt";
		String fileOut = "d:/test.out";
		deduplite(fileIn, fileOut);
		// String str = "13290047619";
		// System.out.println(check(str));
		 extract(fileIn, fileOut);
		//System.out.println(include(str,"2"));
	}

	public static void deduplite(String fileIn, String fileOut){
		Set<String> set = new HashSet<String>();
		BufferedReader bufr = null;
		BufferedWriter bufw = null;
		try {
			bufr = new BufferedReader(new FileReader(fileIn));
			bufw = new BufferedWriter(new FileWriter(fileOut));

			String line = null;
			while ((line = bufr.readLine()) != null) {
				if (check(line)) {
					if(!set.contains(line)){
						set.add(line);
						bufw.write(line);
						bufw.newLine();
						bufw.flush();
					}
						
				}
			}
		} catch (IOException e) {
			System.out.println("读写错误");
			e.printStackTrace();
		} finally {
			try {
				if (bufr != null)
					bufr.close();
			} catch (IOException e) {
				System.out.println("读取关闭失败");
				e.printStackTrace();
			}
			try {
				if (bufw != null)
					bufr.close();
			} catch (IOException e) {
				System.out.println("写入关闭失败");
				e.printStackTrace();
			}
		}
	}
	public static String getNumInclude() throws IOException{
		PropertiesUtil pu = null;
		try {
			pu = new PropertiesUtil();
			
		} catch (IOException e) {
			System.out.println("读取配置文件失败！");
			e.printStackTrace();
		}
		return pu.readValue("include");
	}
	public static void extract(String fileIn, String fileOut) {
		BufferedReader bufr = null;
		BufferedWriter bufw = null;
		
		try {
			bufr = new BufferedReader(new FileReader(fileIn));
			bufw = new BufferedWriter(new FileWriter(fileOut));

			String line = null;
			while ((line = bufr.readLine()) != null) {
				if (check(line)) {
					if (include(line, "8")) {
						bufw.write(line);
						bufw.newLine();
						bufw.flush();
					}
				}
			}
		} catch (IOException e) {
			System.out.println("读写错误");
			e.printStackTrace();
		} finally {
			try {
				if (bufr != null)
					bufr.close();
			} catch (IOException e) {
				System.out.println("读取关闭失败");
				e.printStackTrace();
			}
			try {
				if (bufw != null)
					bufr.close();
			} catch (IOException e) {
				System.out.println("写入关闭失败");
				e.printStackTrace();
			}
		}
	}

	public static boolean check(String line) {
		String regExp = "^[1][0-9]{10}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(line);
		return m.matches();
	}

	public static boolean include(String line, String str) {
		String[] tmp = str.split(",");
		StringBuffer sb = new StringBuffer("\\d*[");
		for (int i = 0; i < tmp.length; i++) {
			sb.append(tmp[i]);
		}
		sb.append("]\\d*");
		return line.matches(sb.toString());
	}
}
