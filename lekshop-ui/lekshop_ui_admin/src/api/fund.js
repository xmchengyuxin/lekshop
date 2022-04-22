import request from '@/utils/request'

export function getAccountLog(data) {
  return request({
    url: '/system/account/getList',
    method: 'get',
	params: data
  })
}

export function getRechargeLog(data) {
  return request({
    url: '/system/recharge/getList',
    method: 'get',
		params: data
  })
}

export function verifyRecharge(data) {
  return request({
    url: 'system/recharge/verifyEditSubmit',
    method: 'post',
    data
  })
}

export function getWithdrawList(data) {
  return request({
    url: '/system/withdraw/getList',
    method: 'get',
		params: data
  })
}

export function countWithdraw(data) {
  return request({
    url: '/system/withdraw/count',
    method: 'get',
		params: data
  })
}

export function verifyWithdraw(data) {
  return request({
    url: 'system/withdraw/verifyEditSubmit',
    method: 'post',
    data
  })
}

export function setDoingWithdraw(data) {
  return request({
    url: 'system/withdraw/setDoing',
    method: 'post',
    data
  })
}

export function getLastWithdraw(data) {
  return request({
    url: '/system/withdraw/getLast',
    method: 'get',
		params: data
  })
}

export function applyWithdraw(data) {
  return request({
    url: 'system/withdraw/apply',
    method: 'post',
    data
  })
}



export function getPublishPointList(data) {
  return request({
    url: '/system/publishPoint/getList',
    method: 'get',
		params: data
  })
}


export function getSpmissionLogList(data) {
  return request({
    url: '/system/spmissionLog/getList',
    method: 'get',
		params: data
  })
}

export function getAccountTypeSelector(data) {
  return request({
    url: '/system/accountType/getSelector',
    method: 'get',
		params: data
  })
}

export function getPointLogList(data) {
  return request({
    url: '/system/point/getList',
    method: 'get',
		params: data
  })
}
