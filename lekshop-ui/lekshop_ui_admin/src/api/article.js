
import request from '@/utils/request'

export function getArticleList(data) {
  return request({
    url: '/system/article/getList',
    method: 'get',
		params: data
  })
}

export function getArticle(id) {
  return request({
    url: '/system/article/get',
    method: 'get',
		params: {id:id}
  })
}

export function updateArticle(data) {
  return request({
    url: 'system/article/editSubmit',
    method: 'post',
    data
  })
}

export function deleteArticle(data) {
  return request({
    url: 'system/article/delete',
    method: 'post',
    params: {ids:data}
  })
}

export function getArticleCateList(data) {
  return request({
    url: '/system/articleCate/getList',
    method: 'get',
		params: data
  })
}

export function getArticleCateSelector(data) {
  return request({
    url: '/system/articleCate/getSelector',
    method: 'get',
		params: data
  })
}


export function updateArticleCate(data) {
  return request({
    url: 'system/articleCate/editSubmit',
    method: 'post',
    data
  })
}

export function deleteArticleCate(data) {
  return request({
    url: 'system/articleCate/delete',
    method: 'post',
    params: {ids:data}
  })
}

