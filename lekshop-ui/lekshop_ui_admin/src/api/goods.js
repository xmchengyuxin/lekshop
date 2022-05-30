import request from '@/utils/request'

export function getGoodsCateList(data) {
  return request({
    url: 'system/goodsCate/getList',
    method: 'get',
		params: data
  })
}

export function addGoodsCate(data) {
  return request({
    url: 'system/goodsCate/add',
    method: 'post',
    data
  })
}

export function updateGoodsCate(data) {
  return request({
    url: 'system/goodsCate/editSubmit',
    method: 'post',
    data
  })
}

export function deleteGoodsCate(data) {
  return request({
    url: 'system/goodsCate/delete',
    method: 'post',
    params: {ids:data}
  })
}

export function getGoodsList(data) {
  return request({
    url: 'system/goods/getList',
    method: 'get',
		params: data
  })
}

export function getGoods(data) {
  return request({
    url: 'system/goods/get',
    method: 'get',
		params: data
  })
}

export function updateGoods(data) {
  return request({
    url: 'system/goods/editSubmit',
    method: 'post',
    data
  })
}

export function shangjiaGoods(data) {
  return request({
    url: 'system/goods/shangjia',
    method: 'post',
    params: {ids:data}
  })
}

export function xiajiaGoods(data) {
  return request({
    url: 'system/goods/xiajia',
    method: 'post',
    params: {ids:data}
  })
}

export function deleteGoods(data) {
  return request({
    url: 'system/goods/delete',
    method: 'post',
    params: {ids:data}
  })
}

export function getGoodsQualityList(data) {
  return request({
    url: 'system/goodsQuality/query',
    method: 'get',
		params: {goodsId : data}
  })
}
