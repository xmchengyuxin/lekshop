import request from '@/utils/request'

export function getConfig() {
  return request({
    url: '/system/email/get', 
    method: 'get'
  })
}

export function updateConfig(data) {
  return request({
    url: '/system/email/editSubmit',
    method: 'post',
		data
  })
}
