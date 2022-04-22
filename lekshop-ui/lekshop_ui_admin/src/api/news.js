import request from '@/utils/request'

export function getNewsTemplateList(data) {
  return request({
    url: '/system/newsTemplate/getList',
    method: 'get',
		params: data
  })
}

export function updateNewsTemplate(data) {
  return request({
    url: 'system/newsTemplate/editSubmit',
    method: 'post',
    data
  })
}

export function getNewsList(data) {
  return request({
    url: '/system/news/getList',
    method: 'get',
		params: data
  })
}

export function readNews(data) {
  return request({
    url: 'system/news/read',
    method: 'post',
    params:{id : data}
  })
}

export function readAll() {
  return request({
    url: 'system/news/readAll',
    method: 'post'
  })
}

export function deleteNews(data) {
  return request({
    url: 'system/news/delete',
    method: 'post',
    params: {id:data}
  })
}
