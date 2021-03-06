package pers.fancy.frame4j.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Fancy
 */
public final class ReflectionUtil {

	private static final Logger log = LoggerFactory.getLogger(ReflectionUtil.class);

	@SuppressWarnings("deprecation")
	public static Object newInstance(Class<?> cls) {
		Object instance;
		try {
			instance = cls.newInstance();
		} catch (Exception e) {
			log.error("new instance failure", e);
			throw new RuntimeException(e);
		}
		return instance;
	}

	public static Object newInstance(String className) {
		Class<?> cls = ClassUtil.loadClass(className);
		return newInstance(cls);
	}

	public static Object invokeMethod(Object obj, Method method, Object... args) {
		Object result;
		try {
			method.setAccessible(true);
			result = method.invoke(obj, args);
		} catch (Exception e) {
			log.error("invoke method failure", e);
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 设置成员变量的值
	 */
	public static void setField(Object obj, Field field, Object value) {
		try {
			field.setAccessible(true);
			field.set(obj, value);
		} catch (Exception e) {
			log.error("set field failure", e);
			throw new RuntimeException(e);
		}
	}
}
