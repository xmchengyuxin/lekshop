import request from '@/utils/request'

export function getShopCouponList(data) {
  return request({
    url: 'system/coupon/getList',
    method: 'get',
		params: data
  })
}
