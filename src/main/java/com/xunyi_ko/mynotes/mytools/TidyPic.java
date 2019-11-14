package com.xunyi_ko.mynotes.mytools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class TidyPic {
	public static void main(String[] args) throws IOException {
		//ԭͼƬ�ļ���ַ
		String readDirect = "D:\\BaiduNetdisk\\ͯ������";
		//Ҫ����ļ��ĵ�ַ
		String writeDirect = "D:\\BaiduNetdisk\\ͯ��������Ʒ";
		
		restorePic(readDirect,writeDirect);
	}
	
	public static void restorePic(String readDirect,String writeDirect) {
		File readFile = new File(readDirect);
		int readFileLength = readFile.getAbsolutePath().length();
		// �����ļ��У���ȡһ���ļ����������ļ������ļ���File�����б�
		List<File> returnFiles = traversalFiles(readFile);
		
		File kid = null;
		File pic = null;
		byte[] chs = null;
		FileInputStream fis = null;
        FileOutputStream fos = null;
		for(File file:returnFiles) {
			String direct = file.getAbsolutePath();
			String path = direct.substring(readFileLength);
			String[] folders = path.split("\\\\");

			String course = folders[folders.length-2];
			String[] s = folders[folders.length-1].split("\\.");
			String name = s[0];
			StringBuilder writeFile = new StringBuilder(writeDirect).append("\\");
			for(int i=0;i<folders.length-2;i++) {
				writeFile.append(folders[i]);
			}
			writeFile.append("\\");
			writeFile.append(name);
			kid = new File(writeFile.toString());
			if (!kid.exists()) {
				kid.mkdirs();
			}
			writeFile.append("\\");
			pic = new File(writeFile + course + ".jpg");
			if (!pic.exists()) {
				try {
					fis = new FileInputStream(file);
					fos = new FileOutputStream(pic);
					chs = new byte[1024];
					int len = 0;
					while ((len = fis.read(chs)) != -1) {
						fos.write(chs, 0, len);
					}
					System.out.println(writeFile + course + ".jpg");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					try {
						fos.close();
						fis.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			
		}
		System.out.println("over");
	}
	
	/**
	 * ��ȡһ���ļ����������ļ������ļ���File�����б�
	 * @param readDirect ��ȡ�ļ����ļ��е�ַ
	 * @return
	 */
	public static List<File> traversalFiles(File readDirect) {
		File[] files = readDirect.listFiles();
		List<File> list = new LinkedList<>();
		if(files == null)
			return null;
		for(File file:files) {
			if(file.isDirectory()) {
				list.addAll(traversalFiles(file));
			}else {
				list.add(file);
			}
		}
		return list;
	}
}
