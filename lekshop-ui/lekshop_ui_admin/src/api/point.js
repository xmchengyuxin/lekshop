import request from '@/utils/request'

export function getConfig() {
  return request({
    url: '/system/point/config/get',
    method: 'get'
  })
}

export function updateConfig(data) {
  return request({
    url: '/system/point/config/editSubmit',
    method: 'post',
		data
  })
}


export function getGoodsCateList(data) {
  return request({
    url: 'system/point/goodsCate/getList',
    method: 'get',
		params: data
  })
}

export function addGoodsCate(data) {
  return request({
    url: 'system/point/goodsCate/add',
    method: 'post',
    data
  })
}

export function updateGoodsCate(data) {
  return request({
    url: 'system/point/goodsCate/editSubmit',
    method: 'post',
    data
  })
}

export function deleteGoodsCate(data) {
  return request({
    url: 'system/point/goodsCate/delete',
    method: 'post',
    params: {ids:data}
  })
}

export function getGoodsList(data) {
  return request({
    url: 'system/point/goods/getList',
    method: 'get',
		params: data
  })
}

export function getGoods(data) {
  return request({
    url: 'system/point/goods/get',
    method: 'get',
		params: data
  })
}

export function updateGoods(data) {
  return request({
    url: 'system/point/goods/editSubmit',
    method: 'post',
    data
  })
}

export function shangjiaGoods(data) {
  return request({
    url: 'system/point/goods/shangjia',
    method: 'post',
    params: {ids:data}
  })
}

export function xiajiaGoods(data) {
  return request({
    url: 'system/point/goods/xiajia',
    method: 'post',
    params: {ids:data}
  })
}

export function deleteGoods(data) {
  return request({
    url: 'system/point/goods/delete',
    method: 'post',
    params: {ids:data}
  })
}


export function getOrderList(data) {
  return request({
    url: 'system/point/order/getList',
    method: 'get',
		params: data
  })
}

export function getOrder(data) {
  return request({
    url: 'system/point/order/get',
    method: 'get',
		params: data
  })
}

export function delivery(data) {
  return request({
    url: 'system/point/order/delivery',
    method: 'post',
    data
  })
}

export function getFreightList(data) {
  return request({
    url: 'system/point/order/getFreightList',
    method: 'get',
		params: data
  })
}
