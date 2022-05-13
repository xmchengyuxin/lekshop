import request from '@/utils/request'

export function getGoodsCateList(data) {
  return request({
    url: 'member/goodsCate/getList',
    method: 'get',
		params: data
  })
}

export function getGoodsList(data) {
  return request({
    url: 'member/goods/getList',
    method: 'get',
		params: data
  })
}

export function getGoods(data) {
  return request({
    url: 'member/goods/get',
    method: 'get',
		params: data
  })
}

export function updateGoods(data) {
  return request({
    url: 'member/goods/editSubmit',
    method: 'post',
    data
  })
}

export function shangjiaGoods(data) {
  return request({
    url: 'member/goods/shangjia',
    method: 'post',
    params: {ids:data}
  })
}

export function xiajiaGoods(data) {
  return request({
    url: 'member/goods/xiajia',
    method: 'post',
    params: {ids:data}
  })
}

export function deleteGoods(data) {
  return request({
    url: 'member/goods/delete',
    method: 'post',
    params: {ids:data}
  })
}
