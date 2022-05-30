import request from '@/utils/request'

export function getTrendsList(data) {
  return request({
    url: 'system/trends/getList',
    method: 'get',
		params: data
  })
}

export function verifyTrends(data) {
  return request({
    url: 'system/trends/verify',
    method: 'post',
    data
  })
}

export function deleteTrends(data) {
  return request({
    url: 'system/trends/delete',
    method: 'post',
    params: {ids:data}
  })
}


export function getCommentList(data) {
  return request({
    url: 'system/trends/getCommentList',
    method: 'get',
		params: data,
     })
}

export function deleteComment(data) {
  return request({
    url: 'system/trends/deleteComment',
    method: 'post',
    data
  })
}