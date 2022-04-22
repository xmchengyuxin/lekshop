package com.chengyu.core.util.ip;


import cn.hutool.json.JSONUtil;
import com.chengyu.core.utils.StringUtils;
import com.qiniu.util.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * 最新一代文件结构 高性能解析IP数据库 qqzeng-ip.dat
 * 编码：UTF8 字节序：Little-Endian
 * For detailed information and guide: http://qqzeng.com/
 * @author qqzeng-ip 于 2015-08-01
 */
public class IpSearch {

    private int[] prefStart = new int[256];
    private int[] prefEnd = new int[256];
    private long[] endArr;
    private String[] addrArr;

    private static IpSearch instance = null;

    private IpSearch() {
        InputStream in = this.getClass().getResourceAsStream("/qqzeng-ip-3.0-ultimate.dat");
        byte[] data = null;
        try {
            data = IOUtils.toByteArray(in);
//            data = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(null==data) {
            return ;
        }
        for (int k = 0; k < 256; k++) {
            int i = k * 8 + 4;
            prefStart[k] = (int) BytesToLong(data[i], data[i + 1], data[i + 2], data[i + 3]);
            prefEnd[k] = (int) BytesToLong(data[i + 4], data[i + 5], data[i + 6], data[i + 7]);

        }

        int RecordSize = (int) BytesToLong(data[0], data[1], data[2], data[3]);
        endArr = new long[RecordSize];
        addrArr = new String[RecordSize];
        for (int i = 0; i < RecordSize; i++) {
            int p = 2052 + (i * 8);
            long endipnum = BytesToLong(data[p], data[1 + p], data[2 + p], data[3 + p]);

            int offset = (int)BytesToLong3(data[4 + p], data[5 + p], data[6 + p]);
            int length = data[7 + p] & 0xff;

            endArr[i] = endipnum;
            addrArr[i] = new String(Arrays.copyOfRange(data,  offset, (offset + length)));
        }
    }

    public static synchronized IpSearch getInstance() {
        if (instance == null) {
            instance = new IpSearch();
        }
        return instance;
    }
    public static synchronized IpSearch getInstance(String url) {
        if (instance == null) {
            instance = new IpSearch();
        }
        if(null==instance.endArr||null==instance.addrArr) {
            instance=null;
        }
        return instance;
    }
    public static String  getArea(String ip) {
        String area ="CN";
        if(null==instance) {
            return area;
        }
        String result=instance.Get(ip);
        String[] split = result.split("\\|");
        if(8<split.length) {
            area=split[8];
        }
        return area;
    }

    public String Get(String ip) {
        String[] ips = ip.split("\\.");
        int pref = Integer.valueOf(ips[0]);
        long val = ipToLong(ip);
        int low = prefStart[pref], high = prefEnd[pref];
        long cur = low == high ? low : BinarySearch(low, high, val);
        return addrArr[(int) cur];

    }

    private int BinarySearch(int low, int high, long k) {
        int M = 0;
        while (low <= high) {
            int mid = (low + high) / 2;

            long endipNum = endArr[mid];
            if (endipNum >= k) {
                M = mid;
                if (mid == 0) {
                    break;
                }
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return M;
    }

    private long BytesToLong(byte a, byte b, byte c, byte d) {
        return (a & 0xFFL) | ((b << 8) & 0xFF00L) | ((c << 16) & 0xFF0000L) | ((d << 24) & 0xFF000000L);

    }

    private long BytesToLong3(byte a, byte b, byte c) {
        return (a & 0xFFL) | ((b << 8) & 0xFF00L) | ((c << 16) & 0xFF0000L);

    }

    private long ipToLong(String ip) {
        long result = 0;
        String[] d = ip.split("\\.");
        for (String b : d) {
            result <<= 8;
            result |= Long.parseLong(b) & 0xff;
        }
        return result;
    }

    public String getIpAddress(String ip){
        if(StringUtils.isBlank(ip)){
            return null;
        }
        try {
            String result = Get(ip);
            String[] split = result.split("\\|");
            return split[2]+"-"+split[3]+"-"+split[5];
        }catch (Exception e){
            return null;
        }
    }

    public static void main(String[] args) {

        IpSearch finder = IpSearch.getInstance();

        String ip = "112.5.70.47";
        String result = finder.Get(ip);

        System.out.println(ip);
        System.out.println(result);
        String[] split = result.split("\\|");
        System.out.println(Arrays.toString(split));
		/*String guojia=split[7];
		String code=split[8];
		String area=split[2];
		String city=split[3];*/
        System.out.println("国家==>  "+split[7]+"   代码 ==>   "+ split[8]);
        System.out.println("省份==>  "+split[2]+"   城市 ==>   "+ split[3]);

        /*
         * 1.197.224.9 亚洲|中国|河南|周口|商水|电信|411623|China|CN|114.60604|33.53912
         */

    }
}
