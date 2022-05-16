package com.chengyu.core.service.pay;

/**
 * @类描述   	支付接口
 * @作者   		LeGreen
 * @创建时间  	2020年3月21日
 * @版本   		1.00
 * @修改记录
 * <pre>
 *     版本          修改人    		修改日期 			修改内容描述
 *     -----------------------------------------------------------
 *     1.00   	LeGreen    	2020年3月21日             
 *     -----------------------------------------------------------
 * </pre>
 */
public interface PayService {
	
	//支付方式
	public static final String PAY_TYPE_ALI = "aliPay";
	public static final String PAY_TYPE_WX = "wxPay";
	public static final String PAY_TYPE_BALANCE = "balance";
	public static final String PAY_TYPE_CASH = "cash";
	
	//支付渠道
	public static final String APPLICATION_TYPE_FIVE_XCX = "fiveXcx";
	public static final String APPLICATION_TYPE_XCX = "xcx";
	public static final String APPLICATION_TYPE_GZH = "gzh";
	public static final String APPLICATION_TYPE_APP = "app";
	
	/**礼品卡支付回调**/
    public static String ALIPAY_NOTIFY_URL_GIFT_CARD_ORDER = "/common/giftCard/aliPay/paySus";
    public static String WXPAY_NOTIFY_URL_GIFT_CARD_ORDER = "/common/giftCard/wxPay/paySus";
    
    /**早餐券支付回调**/
    public static String ALIPAY_NOTIFY_URL_VOUCHER_ORDER = "/common/voucher/aliPay/paySus";
    public static String WXPAY_NOTIFY_URL_VOUCHER_ORDER = "/common/voucher/wxPay/paySus";
    
    /**订单支付回调**/
    public static String ALIPAY_NOTIFY_URL_FOOD_ORDER = "/common/foodOrder/aliPay/paySus";
    public static String WXPAY_NOTIFY_URL_FOOD_ORDER = "/common/foodOrder/wxPay/paySus";
    
    /**原订单原路退款回调**/
    public static String ALIPAY_NOTIFY_URL_HANDLE_REFUND = "/common/handleRefund/aliPay/callback";
    public static String WXPAY_NOTIFY_URL_HANDLE_REFUND = "/common/handleRefund/wxPay/callback";
    
    /**主动申请退款回调**/
    public static String ALIPAY_NOTIFY_URL_REFUND = "/common/refund/aliPay/callback";
    public static String WXPAY_NOTIFY_URL_REFUND = "/common/refund/wxPay/callback";
    
    /**换货订单支付回调**/
    public static String ALIPAY_NOTIFY_URL_FOOD_ORDER_CHANGE = "/common/foodOrderChange/aliPay/paySus";
    public static String WXPAY_NOTIFY_URL_FOOD_ORDER_CHANGE = "/common/foodOrderChange/wxPay/paySus";
	
}