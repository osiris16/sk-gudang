package org.radot.utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.Scanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

/**
 * 
 * @author Iman
 * 
 */
public final class ClassUtils {

	private static ClassLoader CTX_CLS_LDR = Thread.currentThread().getContextClassLoader();
	private static final List<ClassLoader> CLASS_LOADERS = new ArrayList<ClassLoader>(0);

	static {
		synchronized (ClassUtils.class) {
			ClassUtils.CLASS_LOADERS.add(ClasspathHelper.contextClassLoader());
			ClassUtils.CLASS_LOADERS.add(ClasspathHelper.staticClassLoader());
			ClassUtils.CLASS_LOADERS.add(Thread.currentThread().getContextClassLoader());
			ClassUtils.CLASS_LOADERS.add(ClassLoader.getSystemClassLoader());
		}
	}

	private ClassUtils() { }
	
	/**
	 * Get all classes under a package specified by parameter.
	 * @param packageName : {@link String}
	 * @return {@link List} of {@link Class}
	 * @author Iman
	 */
	public static List<Class<?>> getClassesUnderPackage(final String packageName) {
		final List<Class<?>> _ret = new ArrayList<Class<?>>(0);
		if (null == packageName) {			
			return _ret;
		}

		final String _path = packageName.replace('.', '/');
		Enumeration<URL> _resources;
		try {
			_resources = ClassUtils.CTX_CLS_LDR.getResources(_path);
		} catch (final Throwable t) {
			return _ret;
		}

		final List<String> _dirs = new ArrayList<String>();
		while (_resources.hasMoreElements()) {
			final URL resource = _resources.nextElement();
			try {
				final String _filename = resource.getFile();
				_dirs.add(_filename);
			} catch (final Throwable t) {
				t.getMessage();
			}
		}

		final Set<String> _classes = new HashSet<String>();
		final Set<String> _reflection = ClassUtils.reflect(packageName);		
		_classes.addAll(_reflection);
		
		for (final String _dir : _dirs) {
			try { _classes.addAll(ClassUtils.findClasses(_dir, packageName)); } catch (final Throwable t) {
				t.getMessage();
			}
		}
		
		for (final String _class : new TreeSet<String>(_classes)) {
			try {
				final Class<?> _cls = Class.forName(_class, false, ClassUtils.CTX_CLS_LDR);
				_ret.add(_cls);
			} catch (final Throwable t) {
				t.getMessage();
			}
		}

		return _ret;
	}

	private static Set<String> findClasses(final String path, final String packageName) throws MalformedURLException, IOException {
		final Set<String> _ret = new HashSet<String>();
		if (null == path || null == packageName) {
			return _ret;
		}
		
		final String _regex = ".+/".concat(packageName.replace('.', '/').concat("/{0,1}$"));
		if (path.matches(_regex) && path.contains("!")) {
			final String[] _split = path.split("!");
			final String _filename = _split[0].startsWith("file:")? _split[0]: "file:".concat(_split[0]);
			System.out.println("\nloading file: [" + _filename + "]");

			final URL _jar = new URL(_filename);
			final ZipInputStream _zip = new ZipInputStream(_jar.openStream());
			ZipEntry _entry;
			while ((_entry = _zip.getNextEntry()) != null) {
				if (_entry.getName().endsWith(".class")) {
					final String _className = _entry.getName().replaceAll("\\.class$", "").replace('/', '.');
					if (_className.startsWith(packageName)) {
						try {
							Class.forName(_className, false, ClassUtils.CTX_CLS_LDR);
							_ret.add(_className);
							System.out.println("\t-> collecting class: [" + _className + "]");
						} catch (final Throwable t) {
							t.getMessage();
						}
					}
				}
			}
			
			_zip.closeEntry();
			_zip.close();
		}
		
		final File _dir = new File(path);
		if (!_dir.exists()) {
			return _ret;
		}

		final File[] _files = _dir.listFiles();
		System.out.println("\nlooping dir: [" + _dir.getAbsolutePath() + "]");
		for (final File _file : _files) {
			if (_file.isDirectory()) {
				/**
				 * include sub packages
				 */
				_ret.addAll(ClassUtils.findClasses(_file.getAbsolutePath(), packageName + "." + _file.getName()));
			} else if (_file.getName().endsWith(".class")) {
				final String _className = packageName + '.' + _file.getName().replaceAll("\\.class$", "");
				try {
					Class.forName(_className, false, ClassUtils.CTX_CLS_LDR);
					_ret.add(_className);
					System.out.println("\t-> collecting class: [" + _className + "]");
				} catch (final Throwable t) {
					t.getMessage();
				}
			}
		}
		
		return _ret;
	}

	public static Set<String> reflect(final String pkgname) {
		final Set<String> _ret = new HashSet<String>();
		final List<Class<?>> _list = new ArrayList<Class<?>>(0);
		if (null == pkgname) {
			return _ret;
		}

		try {
			final Scanner[] _scanners = new Scanner[]{new SubTypesScanner(false), new ResourcesScanner()};
			final ConfigurationBuilder _builder = new ConfigurationBuilder();
			_builder.setScanners(_scanners);
			_builder.setUrls(ClasspathHelper.forClassLoader(ClassUtils.CLASS_LOADERS.toArray(new ClassLoader[0])));
			_builder.filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(pkgname)));
			for (final ClassLoader _cl: ClassUtils.CLASS_LOADERS) {
				_builder.addClassLoader(_cl);
			}
			
			
			final Reflections reflections = _builder.build();
			_list.addAll(reflections.getSubTypesOf(Object.class));
		} catch (final Throwable t) {
			return _ret;
		}

		if (!_list.isEmpty()) {
			System.out.println("\nreflecting: [" + pkgname + "]");
			for (final Class<?> _class: _list) {
				if (null == _class || null == _class.getName()) {
					continue;
				}

				System.out.println("\t-> reflecting class: [" + _class.getName() + "]");
				_ret.add(_class.getName());
			}
		}

		return _ret;
	}

	public static Object getFieldValue(final Object obj, final String fieldname) {
		Object _ret = (Object) null;
		try {
			final Field _field = obj.getClass().getDeclaredField(fieldname);
			_field.setAccessible(true);
			_ret = _field.get(obj);
		} catch (final Throwable t) {
			t.getMessage();
		}
		
		return _ret;
	}
	
}
