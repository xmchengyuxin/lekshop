import request from '@/utils/request'

export function getTrendsList(data) {
  return request({
    url: 'member/trends/getList',
    method: 'get',
		params: data
  })
}

export function publishTrends(data) {
  return request({
    url: 'member/trends/publish',
    method: 'post',
    data
  })
}

export function deleteTrends(data) {
  return request({
    url: 'member/trends/delete',
    method: 'post',
    params: {ids:data}
  })
}


export function getCommentList(data) {
  return request({
    url: 'member/trends/getCommentList',
    method: 'get',
		params: data,
     })
}

