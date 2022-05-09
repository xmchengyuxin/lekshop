import request from '@/utils/request'

export function getNoticeList(data) {
  return request({
    url: '/system/notice/getList',
    method: 'get',
		params: data
  })
}

export function getNotice(id) {
  return request({
    url: '/system/notice/get',
    method: 'get',
		params: {id:id}
  })
}

export function updateNotice(data) {
  return request({
    url: 'system/notice/editSubmit',
    method: 'post',
    data
  })
}
