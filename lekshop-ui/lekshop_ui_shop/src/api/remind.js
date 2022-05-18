import request from '@/utils/request'

export function getRemindList(data) {
  return request({
    url: 'member/remind/getList',
    method: 'get',
		params: data
  })
}

export function count(data) {
  return request({
    url: 'member/remind/count',
    method: 'get',
		params: data
  })
}

export function readAll() {
  return request({
    url: 'member/remind/readAll',
    method: 'post'
  })
}
