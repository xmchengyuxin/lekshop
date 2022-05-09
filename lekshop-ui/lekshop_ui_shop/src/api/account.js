import request from '@/utils/request'

export function getRiderAccountLog(data) {
  return request({
    url: '/system/riderAccount/getList',
    method: 'get',
		params: data
  })
}

export function countRiderAccount(data) {
  return request({
    url: '/system/riderAccount/count',
    method: 'get',
		params: data
  })
}

export function getRiderBailLog(data) {
  return request({
    url: '/system/riderBail/getList',
    method: 'get',
		params: data
  })
}

export function getRiderBailDetailLog(data) {
  return request({
    url: '/system/riderBail/getDetailList',
    method: 'get',
		params: data
  })
}

export function countRiderBail(data) {
  return request({
    url: '/system/riderBail/count',
    method: 'get',
		params: data
  })
}

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

export function getRiderWithdrawList(data) {
  return request({
    url: '/system/riderWithdraw/getList',
    method: 'get',
		params: data
  })
}

export function countRiderWithdraw(data) {
  return request({
    url: '/system/riderWithdraw/count',
    method: 'get',
		params: data
  })
}

export function verifyRiderWithdraw(data) {
  return request({
    url: 'system/riderWithdraw/verifyEditSubmit',
    method: 'post',
    data
  })
}

export function doRefundBail(data) {
  return request({
    url: 'system/riderBail/doRefund',
    method: 'post',
    data
  })
}