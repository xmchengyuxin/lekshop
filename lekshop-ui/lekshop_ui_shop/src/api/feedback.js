import request from '@/utils/request'

export function getFeedbackList(data) {
  return request({
    url: '/system/feedback/getList',
    method: 'get',
		params: data
  })
}




