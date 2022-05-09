import request from '@/utils/request'

export function getMessageCodeLog(data) {
  return request({
    url: '/system/message/getList',
    method: 'get',
		params: data
  })
}


