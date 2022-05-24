import request from '@/utils/request'

export function getMemberAccountLog(data) {
  return request({
    url: '/system/memberAccount/getList',
    method: 'get',
		params: data
  })
}

export function getMemberRechargeLog(data) {
  return request({
    url: '/system/memberRecharge/getList',
    method: 'get',
		params: data
  })
}

export function doRefundBail(data) {
  return request({
    url: 'system/riderBail/doRefund',
    method: 'post',
    data
  })
}
