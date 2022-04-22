import request from '@/utils/request'

export function getConfig() {
  return request({
    url: '/system/sms/get',
    method: 'get'
  })
}

export function updateConfig(data) {
  return request({
    url: '/system/sms/editSubmit',
    method: 'post',
		data
  })
}
