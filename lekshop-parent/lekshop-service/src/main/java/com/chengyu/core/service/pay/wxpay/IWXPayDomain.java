package com.chengyu.core.service.pay.wxpay;

import com.github.wxpay.sdk.WXPayConfig;

public interface IWXPayDomain {
	/**
     * 上报域名网络状况
     * @param domain 域名。 比如：api.mch.weixin.qq.com
     * @param elapsedTimeMillis 耗时
     * @param ex 网络请求中出现的异常。
     *           null表示没有异常
     *           ConnectTimeoutException，表示建立网络连接异常
     *           UnknownHostException， 表示dns解析异常
     */
     void report(final String domain, long elapsedTimeMillis, final Exception ex);

    /**
     * 获取域名
     * @param config 配置
     * @return 域名
     */
     DomainInfo getDomain(final WXPayConfig config);

     class DomainInfo{
         /**
          * 域名
          */
        public String domain;
         /**
          * 该域名是否为主域名。例如:api.mch.weixin.qq.com为主域名
          */
        public boolean primaryDomain;
        public DomainInfo(String domain, boolean primaryDomain) {
            this.domain = domain;
            this.primaryDomain = primaryDomain;
        }

        @Override
        public String toString() {
            return "DomainInfo{" +
                    "domain='" + domain + '\'' +
                    ", primaryDomain=" + primaryDomain +
                    '}';
        }
    }
}
