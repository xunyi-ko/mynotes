package com.xunyi_ko.mynotes.mytools;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

public class TidyPic {
    private final static String FROM = "D:\\BaiduNetdisk\\童画孩子";
    private final static String TO = "D:\\BaiduNetdisk\\童画孩子作品";
    private final static File FROM_FILE = new File(FROM);
    
    private final static String FILE_EXT = ".jpg";
    
	public static void main(String[] args) throws IOException {
        restore(FROM_FILE);
	}
	
	/**
	 * 复制文件到新的位置
	 * @param readDirect 读取文件的地址
	 * @return
	 */
	public static void restore(File readDirect) {
		// 用stream遍历
	    if(readDirect == null) {
	        return;
	    }
	    File[] fileArray = readDirect.listFiles();
	    if(fileArray == null || fileArray.length == 0) {
	        return;
	    }
	    List<File> files = Arrays.asList(fileArray);
	    files.parallelStream().forEach(file -> {
	        if(file.isDirectory()) {
	            restore(file);
	        }else {
	            // 如果是文件，则复制到对应的地址
	            copyTo(file);
	        }
	    });
	}
	
	private static void copyTo(File file) {
	    if(!file.getAbsolutePath().startsWith(FROM)) {
	        return;
	    }
	    File readFile = new File(FROM);
        int readFileLength = readFile.getAbsolutePath().length();
	    
	    String direct = file.getAbsolutePath();
        String path = direct.substring(readFileLength);
        String[] folders = path.split("\\\\");

        String course = folders[folders.length-2];
        String[] s = folders[folders.length-1].split("\\.");
        String name = s[0];
        StringBuilder writeFile = new StringBuilder(TO).append("\\");
        for(int i=0;i<folders.length-2;i++) {
            writeFile.append(folders[i]);
        }
        writeFile.append("\\");
        writeFile.append(name);
        File kid = new File(writeFile.toString());
        if (!kid.exists()) {
            kid.mkdirs();
        }
        writeFile.append("\\");
        File pic = new File(writeFile + course + FILE_EXT);
        if (!pic.exists()) {
            try {
                Files.copy(file.toPath(), pic.toPath());
            } catch (IOException e) {
                System.out.println(file + " copy failed");
            }
//            try (
//                FileInputStream fis = new FileInputStream(file);
//                BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(pic))
//            ){
//                byte[] chs = new byte[1024];
//                int len = 0;
//                while ((len = fis.read(chs)) != -1) {
//                    fos.write(chs, 0, len);
//                }
//                System.out.println(writeFile + course + FILE_EXT);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
	}
	
	@Test
	public void testTraversalFiles() {
	    restore(new File("F:"));
	}
}
