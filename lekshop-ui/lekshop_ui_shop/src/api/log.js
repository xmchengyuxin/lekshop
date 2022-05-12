import request from '@/utils/request'

export function getLoginLoginLog(data) {
  return request({
    url: 'member/login/getList',
    method: 'get',
		params: data
  })
}

export function getMemberOperationLog(data) {
  return request({
    url: 'member/memberOperation/getList',
    method: 'get',
		params: data
  })
}
