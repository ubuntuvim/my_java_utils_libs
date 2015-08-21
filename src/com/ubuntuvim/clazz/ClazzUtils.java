package com.ubuntuvim.clazz;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

/**
 * Related Java Class tools.
 * @author ubuntuvim
 * @Email 1527254027@qq.com
 * @2015年8月22日 上午12:32:03
 */
public class ClazzUtils {

	/**
	 * Get all of the implementation classess for the interface,
	 * but the interface and implementation class must be in the same package,
	 * and they are no package as jar.
	 * 
	 * @param clazz interface
	 * @return class array
	 */
	public static List<Class<?>> getClassByInterface(Class<?> clazz) {
		List<Class<?>> classList = new ArrayList<Class<?>>();
		//  If the argument is not an interface, it does not do the processing and throws the exception
		try {
			if (null != clazz && clazz.isInterface()) {
				String packageName = clazz.getPackage().getName();
				
				//Get all class in the package.
				List<Class<?>> tmpList = getClassess(packageName);
				//  Except the interface itself
				for (int i = 0; i < tmpList.size(); i++) {
					Class<?> c = tmpList.get(i);
					if (clazz.isAssignableFrom(c)) {
						if (!clazz.equals(c)) {
							classList.add(c);
						}
					}
				}
			} else {
				try {
					throw new Exception("The argument is not an interface.");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return classList;
	}

	/**
	 * Get all class in the package.
	 * @param packageName
	 * @return class array
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	private static List<Class<?>> getClassess(String packageName) throws IOException, ClassNotFoundException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		String path = packageName.replace(".", "/");
		Enumeration<URL> resources = classLoader.getResources(path);
		List<File> files = new ArrayList<File>();
		while (resources.hasMoreElements()) {
			URL url = resources.nextElement();
			files.add(new File(url.getFile()));
		}
		List<Class<?>> classList = new ArrayList<Class<?>>();
		for (File dir : files) {
			classList.addAll(findClassess(dir, packageName));
		}
		
		return classList;
	}

	/**
	 * Get all class in the directory
	 * @param dir
	 * @param packageName
	 * @return class array
	 * @throws ClassNotFoundException 
	 */
	private static Collection<? extends Class<?>> findClassess(File dir, String packageName) throws ClassNotFoundException {
		
		List<Class<?>> cl = new ArrayList<Class<?>>();
		if (!dir.isDirectory()) {
			return cl;  // empty array
		}
		File[] files = dir.listFiles();
		for (File f : files) {
			if (f.isDirectory()) {
				assert !f.getName().contains(".");
				// Get all class in the sub directory
				cl.addAll(findClassess(f, packageName + "." + f.getName()));  
			} else if (f.getName().endsWith(".class")) {
				cl.add(Class.forName(packageName+"."+f.getName().substring(0, f.getName().length()-6)));
			} else {
				//  other file...don't process
			}
		}
		
		return cl;
	}
}
