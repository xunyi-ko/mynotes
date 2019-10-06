import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class TidyPic {
	public static void main(String[] args) throws IOException {
		//原图片文件地址
		String readDirect = "D:\\BaiduNetdisk\\童画孩子";
		//要存放文件的地址
		String writeDirect = "D:\\BaiduNetdisk\\童画孩子作品";
		
		restorePic(readDirect,writeDirect);
	}
	
	public static void restorePic(String readDirect,String writeDirect) {
		File readFile = new File(readDirect);
		int readFileLength = readFile.getAbsolutePath().length();
		// 遍历文件夹，获取一个文件夹内所有文件类型文件的File对象列表
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
	 * 获取一个文件夹内所有文件类型文件的File对象列表
	 * @param readDirect 读取文件的文件夹地址
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
