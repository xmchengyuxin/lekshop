package com.chengyu.core.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ReflectUtils {
    public static Object getFieldValue(Object obj, String fieldName) {
    	Field field = ReflectUtils.getField(obj, fieldName);
    	if(field == null) return null;
    	
    	Object result = null;
    	field.setAccessible(true);
    	try {
    		result = field.get(obj);
    	} catch (IllegalArgumentException e) {
    		e.printStackTrace();
    	} catch (IllegalAccessException e) {
    		e.printStackTrace();
    	}
    	return result;
    }
    
    public static Object getFieldValue(Object obj, Field field) {
    	if(field == null) return null;
    	
    	Object result = null;
    	field.setAccessible(true);
    	try {
    		result = field.get(obj);
    	} catch (IllegalArgumentException e) {
    		e.printStackTrace();
    	} catch (IllegalAccessException e) {
    		e.printStackTrace();
    	}
    	return result;
    }

    public static void setFieldValue(Object obj, String fieldName, Object fieldValue) {
    	Field field = getField(obj, fieldName);
    	if(field != null) {
    		try {
    			field.setAccessible(true);
    			field.set(obj, fieldValue);
    		} catch (IllegalArgumentException e) {
    			e.printStackTrace();
    		} catch (IllegalAccessException e) {
    			e.printStackTrace();
    		}
    	}
    }
    
    public static <T> List<Field> getFields(Class<T> clazz) {
    	return getFields(clazz, new String[]{"serialVersionUID"});
    }
    
    public static <T> List<Field> getFields(Class<T> clazz, String[] excludeFields) {
    	List<Field> fieldList = new ArrayList<Field>();
    	for(Class<?> supperClazz=clazz; supperClazz != Object.class; supperClazz=supperClazz.getSuperclass()) {
    		for(Field field : supperClazz.getDeclaredFields()) {
    			boolean isExclude = false;
    			for(String fieldName : excludeFields) {
    				if(fieldName.equals(field.getName())) {
    					isExclude = true;
    					break;
    				}
    			}
    			
    			if(!isExclude) fieldList.add(field);
    		}
    	}
    	return fieldList;
    }
    
    public static List<Field> getFields(Object obj) {
    	return getFields(obj, new String[]{"serialVersionUID"});
    }
    
    public static List<Field> getFields(Object obj, String[] excludeFields) {
    	List<Field> fieldList = new ArrayList<Field>();
    	for(Class<?> clazz=obj.getClass(); clazz != Object.class; clazz=clazz.getSuperclass()) {
    		for(Field field : clazz.getDeclaredFields()) {
    			boolean isExclude = false;
    			for(String fieldName : excludeFields) {
    				if(fieldName.equals(field.getName())) {
    					isExclude = true;
    					break;
    				}
    			}
    			
    			if(!isExclude) fieldList.add(field);
    		}
    	}
    	return fieldList;
    }
    
    public static Field getField(Object obj, String fieldName) {
    	Field field = null;
    	for(Class<?> clazz=obj.getClass(); clazz != Object.class; clazz=clazz.getSuperclass()) {
    		try {
    			field = clazz.getDeclaredField(fieldName);
    			break;
    		} catch (NoSuchFieldException e) {
    		}
    	}
    	return field;
    }
    
    public static Field getField(Class<?> clazz, String fieldName) {
    	Field field = null;
    	for(Class<?> supperClazz=clazz; supperClazz != Object.class; supperClazz=supperClazz.getSuperclass()) {
    		try {
    			field = supperClazz.getDeclaredField(fieldName);
    			break;
    		} catch (NoSuchFieldException e) {
    		}
    	}
    	return field;
    }
}