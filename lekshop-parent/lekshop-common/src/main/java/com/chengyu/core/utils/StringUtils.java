package com.chengyu.core.utils;

import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.math.RandomUtils;

public class StringUtils extends org.apache.commons.lang.StringUtils {
	private static final Pattern URL = Pattern.compile(
		"^((https|http|ftp|rtsp|mms)?://)" 
	     + "+(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" 
	     + "(([0-9]{1,3}\\.){3}[0-9]{1,3}" 
	     + "|" 
	     + "([0-9a-z_!~*'()-]+\\.)*" 
	     + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\." 
	     + "[a-z]{2,6})" 
	     + "(:[0-9]{1,4})?" 
	     + "((/?)|" 
	     + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$", Pattern.CASE_INSENSITIVE
	);
	
	private static final Pattern PHONE = Pattern.compile("^1[3|4|5|6|7|8|9]([0-9])\\d{8}$");
	
	private static final Pattern MONEY = Pattern.compile("^[0-9]+$|^[0-9]+\\.[0-9]{1,6}$");
	
	private static final Pattern TELPHONE = Pattern.compile("^(0[0-9]{2,4}-?[0-9]{7,8})|(1[3|4|5|7|8][0-9]{9})$");
	
	private static final Pattern IP = Pattern.compile("^((\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]|[*])\\.){3}(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]|[*])$");
	
	private static final Pattern EMAIL = Pattern.compile("^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$");

	private static final Pattern SUZI = Pattern.compile("^[0-9]*$");
	
	 public static boolean isContainChinese(String str) {
	        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
	        Matcher m = p.matcher(str);
	        if (m.find()) {
	            return true;
	        }
	        return false;
	    }
	
	public static boolean isEqual(Object obj1, Object obj2) {
		if(obj1 == null && obj2 == null) return true;
		if(obj1 == null || obj2 == null) return false;
		
		return obj1.equals(obj2);
	}
	
	public static boolean isNotEqual(Object obj1, Object obj2) {
		return !isEqual(obj1, obj2);
	}
	
	/**
	 * 六位数字验证码
	 * @return
	 */
	public static String getSixCode() {
		String result = "";
		for (int i = 0; i < 6; i++) {
			result += new Random().nextInt(10);
		}
		return result;
	}
	
	public static String getFourCode() {
		String result = "";
		for (int i = 0; i < 4; i++) {
			result += new Random().nextInt(10);
		}
		return result;
	}
	
	/**
	 * 数字字母验证码
	 */
    public static String getStringRandom(int length) {  
        String val = "";  
        Random random = new Random();  
          
        //参数length，表示生成几位随机数  
        for(int i = 0; i < length; i++) {  
              
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";  
            //输出字母还是数字  
            if( "char".equalsIgnoreCase(charOrNum) ) {  
                //输出是大写字母还是小写字母  
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;  
                val += (char)(random.nextInt(26) + temp);  
            } else if( "num".equalsIgnoreCase(charOrNum) ) {  
                val += String.valueOf(random.nextInt(10));  
            }  
        }  
        return val;  
    }
    
    public static boolean checkUrl(String url) {
		return URL.matcher(url).matches();
	}
    
	public static Boolean checkEmail(String email) {
        return EMAIL.matcher(email).matches();
	}
	
	public static Boolean checkPhone(String phone) {
        return PHONE.matcher(phone).matches();
	}
	
	public static Boolean checkTelPhone(String tel){
        return TELPHONE.matcher(tel).matches();
	}
	
	public static Boolean checkIp(String ip){
		return IP.matcher(ip).matches();
	}
	
	public static boolean isMoney(String str) {
		return MONEY.matcher(str).matches();
	}
	
	public static boolean isSuZi(String str) {
		return SUZI.matcher(str).matches();
	}
	
	public static String coverToUtf8(String str) {
		try {
			return new String(str.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return str;
		}
	}
	
	/**
	 * 生成20位订单号
	 */
	public static String genenrateInd(){
		Random random = new Random();
		Integer x = random.nextInt(899999);
		Integer y = x + 100000;
		return DateUtil.getShortCurrentTimeStr() + y.toString();
	}
	
	public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",  
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",  
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",  
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",  
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",  
            "W", "X", "Y", "Z" };  
  
  
	public static String generateShortUuid(int length) {  
	    StringBuffer shortBuffer = new StringBuffer();  
	    String uuid = UUID.randomUUID().toString().replace("-", "");  
	    for (int i = 0; i < length; i++) {  
	        String str = uuid.substring(i * 4, i * 4 + 4);  
	        int x = Integer.parseInt(str, 16);  
	        shortBuffer.append(chars[x % 0x3E]);  
	    }  
	    return shortBuffer.toString();  
	}

	public static String replaceStar(String content){
		if(StringUtils.isBlank(content)){
			return null;
		}
		StringBuilder newContent = new StringBuilder();
		for(int i = 0; i < content.length(); i++){
			char[] arr = content.toCharArray();
			newContent.append(i/2==0 ? arr[i] : "*");
		}
		return newContent.toString();
	}

	/**
	 * 生成32位的唯一序列号
	 * @return
	 */
	public static String genenrateUniqueInd() {
		return MD5Util.MD5Encode(UUID.randomUUID().toString(), "utf-8");
	}
	
	public static String genOrderNo(Integer memberId) {
		return (System.currentTimeMillis() + (memberId==null?0L:memberId)) + StringUtils.leftPad("" + RandomUtils.nextInt(1000), 3, "0");
	}

	public static String genTradeNo(Integer memberId) {
		String number = String.format("%04d", memberId % 10000);
		String randomCode = generateOrderNo();
		return randomCode.concat(number);
	}

	private static final int MIN_NUMBER = 1000;
	private static final int MAX_NUMBER = 9999;
	private static final AtomicInteger SEQUENCE = new AtomicInteger(MIN_NUMBER);

	/**
	 * 每台机器从 1000 开始自增到 9999，保证单独一台机器在高并发下生成的单号唯一
	 *
	 * @return
	 */
	private static String generateOrderNo() {
		if (SEQUENCE.intValue() > MAX_NUMBER) {
			SEQUENCE.getAndSet(MIN_NUMBER);
		}

		String time = String.valueOf(System.currentTimeMillis());
		return time + SEQUENCE.getAndIncrement();
	}
	
	public static void main(String[] args) {
		for(int i =0; i<5; i++){
			System.out.println(genenrateUniqueInd().substring(0,16));
		}
	}
	public static String get12Code() {
		String result = "";
		for (int i = 0; i < 12; i++) {
			result += new Random().nextInt(10);
		}
		return result;
	}
}