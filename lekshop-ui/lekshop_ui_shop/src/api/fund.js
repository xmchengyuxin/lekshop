import request from '@/utils/request'

export function getAccountLog(data) {
  return request({
    url: 'member/account/getList',
    method: 'get',
	params: data
  })
}

export function getRechargeLog(data) {
  return request({
    url: 'member/recharge/getList',
    method: 'get',
		params: data
  })
}

export function verifyRecharge(data) {
  return request({
    url: 'member/recharge/verifyEditSubmit',
    method: 'post',
    data
  })
}

export function getWithdrawList(data) {
  return request({
    url: 'member/withdraw/getList',
    method: 'get',
		params: data
  })
}

export function countWithdraw(data) {
  return request({
    url: 'member/withdraw/count',
    method: 'get',
		params: data
  })
}

export function applyWithdraw(data) {
  return request({
    url: 'member/withdraw/apply',
    method: 'post',
    data
  })
}

export function getLastWithdraw(data) {
  return request({
    url: 'member/withdraw/getLast',
    method: 'get',
		params: data
  })
}


export function getPublishPointList(data) {
  return request({
    url: 'member/publishPoint/getList',
    method: 'get',
		params: data
  })
}


export function getSpmissionLogList(data) {
  return request({
    url: 'member/spmissionLog/getList',
    method: 'get',
		params: data
  })
}

export function getAccountTypeSelector(data) {
  return request({
    url: 'member/accountType/getSelector',
    method: 'get',
		params: data
  })
}

export function getPointLogList(data) {
  return request({
    url: 'member/point/getList',
    method: 'get',
		params: data
  })
}
