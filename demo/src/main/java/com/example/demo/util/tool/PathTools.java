package com.example.demo.util.tool;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLDecoder;

import javax.swing.filechooser.FileSystemView;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 *  查询路径的工具类
 * @author sandy0823
 *
 */
public class PathTools {
    
	private static final Logger log = LoggerFactory.getLogger(PathTools.class);
	
	/**
	 * 相对类路径获取
	 * @param file   相对于类加载上下文件的根目录
	 * @return
	 */
    public static InputStream getFileInputStream(String file) {
    	return PathTools.class.getResourceAsStream(file);
    }

	/**
	 * 获取当前工程路径(setup目录)
	 * @return
	 */
	public static String getProjectPath() {
		String shell = System.getProperty("user.dir").replaceAll("\\\\", "/");
		return getParentPath(shell);
	}
	
	/**
	 * 将路径中"\\"转换为"/"
	 * @param path
	 * @return
	 */
	public static String formatPath(String path) {
		return path.replace("\\", "/");
	}
	
	/**
	 * 将路径中"/"转换为"\\"
	 * @param path
	 * @return
	 */
	public static String unFormatPath(String path) {
		return path.replace("/", "\\");
	}
	
	/**
	 * 路径连接
	 * @param pathFront
	 * @param pathBehind
	 * @return
	 */
	public static String pathAdd(String pathFront, String pathBehind) {
		if(StringUtils.isEmpty(pathFront) || StringUtils.isEmpty(pathBehind)){
			log.error("param is null or empty;pathFront={},pathBehind={}",pathFront,pathBehind);
			throw new IllegalArgumentException("param is null or empty");
		}
		String resultPath = "";
		pathFront = formatPath(pathFront);
		pathBehind = formatPath(pathBehind);
		if (pathFront.endsWith("/")) {
			resultPath = pathFront + filterPath(pathBehind);
		} else {
			resultPath = pathFront + "/" + filterPath(pathBehind);
		}
		
		return formatPath(resultPath);
	}
	
	/**
	 * 将路径开头的"/"删除
	 * @author panfangbo 2017年9月13日 上午10:26:35
	 * @param path
	 * @return
	 */
	private static String filterPath(String path) {
		if (StringUtils.isEmpty(path))
			return Strings.EMPTY;
		path = path.trim();
		if (path.startsWith("/")) {
			return path.substring(1);
		} else {
			return path;
		}
	}
	
	/**
	 * 获取父路径（路径分隔符必须为/）
	 * @author gutingshuai 2016-3-19 上午10:38:34
	 * @param path
	 * @return
	 */
	public static String getParentPath(String path) {
		if (StringUtils.isEmpty(path)) {
			return Strings.EMPTY;
		}
		String parentPath = path;
		if (path.endsWith("/")) {
			parentPath = path.substring(0, path.length() - 1);
		}
		return parentPath.substring(0, parentPath.lastIndexOf("/") + 1);
	}
	
	/**
	 * 获取打包过的Java可执行文件（jar，war）所处的系统目录名或非打包Java程序所处的目录
	 * @param cls 调用此方法所在的类
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String getClassPath(Class cls) {
		String basePath = null;
		basePath = getAppPath(cls);
		if (!StringUtils.isEmpty(basePath)) {
			basePath = basePath.substring(1);// 过滤第一个多余的"/"
		}
		return basePath;
	}
	
	/**
	 * 获取解压的资源文件所在的临时目录
	 * @param cls 调用此方法所在的类
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String getTempPath(Class cls) {
		String tempPath = PathTools.pathAdd(getClassPath(cls), "temp");
		File file = new File(tempPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		return file.getAbsolutePath();
	}
	
	/**
	 * 获取桌面路径
	 * @return
	 */
	public static String desktopDir() {
		File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
		return desktopDir.getAbsolutePath();
	}
	
	/**
	 * 获取打包过的Java可执行文件（jar，war）所处的系统目录名或非打包Java程序所处的目录
	 * @param cls
	 * @return 返回值为该类所在的Java程序运行的目录
	 */
	@SuppressWarnings("rawtypes")
	private static String getAppPath(Class cls) {
		// 检查用户传入的参数是否为空 cls:class com.util.PropertiesUtil
		if (cls == null) {
			throw new java.lang.IllegalArgumentException("参数不能为空！");
		}
		
		ClassLoader loader = cls.getClassLoader();
		// 获得类的全名，包括包名 //com.util.PropertiesUtil.class
		String clsName = cls.getName() + ".class";
		// 获得传入参数所在的包 package com.util
		Package pack = cls.getPackage();
		String path = "";// 包名相对应路径
		// 如果不是匿名包，将包名转化为路径
		if (pack != null) {
			String packName = pack.getName();// com.util
			// 此处简单判定是否是Java基础类库，防止用户传入JDK内置的类库
			if (packName.startsWith("java.") || packName.startsWith("javax.")) {
				throw new java.lang.IllegalArgumentException("请不要传送系统内置类！");
			}
			
			// 在类的名称中，去掉包名的部分，获得类的文件名 PropertiesUtil.class
			clsName = clsName.substring(packName.length() + 1);
			// 判定包名是否是简单包名，如果是，则直接将包名转换为路径
			if (packName.indexOf(".") < 0) {
				path = packName + "/";
			} else {// 否则按照包名的组成部分，将包名转换为路径
				int start = 0, end = 0;
				end = packName.indexOf(".");
				while (end != -1) {
					path = path + packName.substring(start, end) + "/";
					start = end + 1;
					end = packName.indexOf(".", start);
				}
				path = path + packName.substring(start) + "/"; // com/util/
			}
		}
		// 调用ClassLoader的getResource方法，传入包含路径信息的类文件名
		// file:/D:/Workspaces/springjdbc/bin/com/util/PropertiesUtil.class
		URL url = loader.getResource(path + clsName);
		if (url == null) {
			throw new IllegalArgumentException("class get url is null");
		}
		// 从URL对象中获取路径信息
		// /D:/Workspaces/springjdbc/bin/com/util/PropertiesUtil.class
		String realPath = url.getPath();
		// 去掉路径信息中的协议名"file:"
		int pos = realPath.indexOf("file:");
		if (pos > -1) {
			realPath = realPath.substring(pos + 5);
		}
		// 去掉路径信息最后包含类文件信息的部分，得到类所在的路径
		pos = realPath.indexOf(path + clsName);
		realPath = realPath.substring(0, pos - 1);// /D:/Workspaces/springjdbc/bin
		// 如果类文件被打包到JAR等文件中时，去掉对应的JAR等打包文件名
		if (realPath.endsWith("!")) {
			realPath = realPath.substring(0, realPath.lastIndexOf("/"));
		}
		
		/*------------------------------------------------------------ 
		 ClassLoader的getResource方法使用了utf-8对路径信息进行了编码，当路径 
		              中存在中文和空格时，他会对这些字符进行转换，这样，得到的往往不是我们想要 
		               的真实路径，在此，调用了URLDecoder的decode方法进行解码，以便得到原始的 
		               中文及空格路径 
		-------------------------------------------------------------*/
		try {
			realPath = URLDecoder.decode(realPath, "utf-8");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return realPath;
	}
}
