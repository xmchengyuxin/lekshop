package com.chengyu.core.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CombinationUtil {
	private static long factorial(int n) {  
	    return (n > 1) ? n * factorial(n - 1) : 1;  
	}  
	
	public static long combination(int n, int m) {  
	    return (n >= m) ? factorial(n) / factorial(n - m) / factorial(m) : 0;  
	}
	
	public static List<int[]> combinationSelect(int[] datas, int n) {
		if(datas.length < n) return null;
		if(datas.length == n) return Collections.singletonList(datas);
		
		List<int[]> resultList = new ArrayList<int[]>();
	    combinationSelect(resultList, datas, 0, new int[n], 0);
	    
	    return resultList;
	}
	
	private static void combinationSelect(List<int[]> resultList, int[] datas, int dataIndex, int[] results, int resultIndex) {  
	    int resultLen = results.length;  
	    int resultCount = resultIndex + 1;  
	    if (resultCount > resultLen) {
	    	resultList.add(Arrays.copyOf(results, results.length));
	        return;  
	    }  
	  
	    for(int i = dataIndex; i < datas.length + resultCount - resultLen; i++) {  
	        results[resultIndex] = datas[i];  
	        combinationSelect(resultList, datas, i + 1, results, resultIndex + 1);  
	    }  
	}
	
	public static List<String[]> combinationSelect(String[] datas, int n) {
		if(datas.length < n) return null;
		if(datas.length == n) return Collections.singletonList(datas);
		
		List<String[]> resultList = new ArrayList<String[]>();
	    combinationSelect(resultList, datas, 0, new String[n], 0);
	    
	    return resultList;
	}
	
	private static void combinationSelect(List<String[]> resultList, String[] datas, int dataIndex, String[] results, int resultIndex) {  
	    int resultLen = results.length;  
	    int resultCount = resultIndex + 1;  
	    if (resultCount > resultLen) {
	    	resultList.add(Arrays.copyOf(results, results.length));
	        return;  
	    }  
	  
	    for(int i = dataIndex; i < datas.length + resultCount - resultLen; i++) {  
	        results[resultIndex] = datas[i];  
	        combinationSelect(resultList, datas, i + 1, results, resultIndex + 1);  
	    }  
	}
}