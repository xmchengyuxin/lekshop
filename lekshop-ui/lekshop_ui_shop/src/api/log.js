import request from '@/utils/request'

export function getAdminLoginLog(data) {
  return request({
    url: '/system/adminLogin/getList',
    method: 'get',
		params: data
  })
}

export function getAdminOperationLog(data) {
  return request({
    url: '/system/adminOperation/getList',
    method: 'get',
		params: data
  })
}

export function getverifyCodeLog(data) {
  return request({
    url: '/system/verifyCode/getList',
    method: 'get',
		params: data
  })
}
