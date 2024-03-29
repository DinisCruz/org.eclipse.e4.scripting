package org.eclipse.ease.modules;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.PARAMETER })
@Documented
public @interface ScriptParameter {

	public static final String NULL = "[null]";

	public static final String UNDEFINED = "_-_-_undefined_-_-_";

	String name() default "";

	String defaultValue() default UNDEFINED;

	boolean optional() default false;

	public static class OptionalParameterHelper {

		protected static String getDefaultValue(ScriptParameter in) {
			String defaultValue = in.defaultValue();
			if (defaultValue == null) {
				try {
					Method method = ScriptParameter.class.getMethod("defaultValue", null);
					Object defaultV = method.getDefaultValue();
					if (defaultV instanceof String) {
						defaultValue = (String) defaultV;
					}
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				}
			}
			return defaultValue;
		}

		public static Object getDefaultValue(ScriptParameter in, Class<?> type) {
			String defaultValue = getDefaultValue(in);
			if ((defaultValue == null) || UNDEFINED.equals(defaultValue)) {
				return null;
			}
			if (Integer.class.isAssignableFrom(type)) {
				return Integer.getInteger(defaultValue);
			} else if (String.class.isAssignableFrom(type)) {
				return defaultValue;
			} else if (Boolean.class.isAssignableFrom(type)) {
				return Boolean.getBoolean(defaultValue);
			}
			return null;
		}
	}
}
