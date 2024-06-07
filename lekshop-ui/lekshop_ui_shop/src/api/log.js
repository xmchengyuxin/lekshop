import request from '@/utils/request'

export function getLoginLoginLog(data) {
  return request({
    url: 'member/login/getList',
    method: 'get',
		params: data
  })
}

export function getOperationLogList(data) {
  return request({
    url: 'member/accountOperation/getList',
    method: 'get',
		params: data
  })
}

export function deleteOperationLog(data) {
  return request({
    url: 'member/accountOperation/delete',
    method: 'post',
    data
  })
}
