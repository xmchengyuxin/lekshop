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

export function getThirdDetail(data) {
  return request({
    url: 'member/goods/getThirdDetail',
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


export function getGoodsQualityList(data) {
  return request({
    url: 'member/goodsQuality/query',
    method: 'get',
		params: {goodsId : data}
  })
}

export function addGoodsQuality(data) {
  return request({
    url: 'member/goodsQuality/add',
    method: 'post',
    data
  })
}

export function deleteGoodsQuality(data) {
  return request({
    url: 'member/goodsQuality/delete',
    method: 'post',
    params: {ids:data}
  })
}

export function getGoodsRelateList(data) {
  return request({
    url: 'member/goodsRelate/getList',
    method: 'get',
		params: data
  })
}

export function addGoodsRelate(data) {
  return request({
    url: 'member/goodsRelate/add',
    method: 'post',
    data
  })
}

export function updateRelateSort(data) {
  return request({
    url: 'member/goodsRelate/updateSort',
    method: 'post',
    data
  })
}

export function deleteGoodsRelate(data) {
  return request({
    url: 'member/goodsRelate/delete',
    method: 'post',
    params: {ids:data}
  })
}


export function updateSeckill(data) {
  return request({
    url: 'member/seckill/editSubmit',
    method: 'post',
    data
  })
}

export function deleteSeckill(data) {
  return request({
    url: 'member/seckill/delete',
    method: 'post',
    params: {ids:data}
  })
}

export function updateGroup(data) {
  return request({
    url: 'member/group/editSubmit',
    method: 'post',
    data
  })
}

export function deleteGroup(data) {
  return request({
    url: 'member/group/delete',
    method: 'post',
    params: {ids:data}
  })
}

export function getSkuList(data) {
  return request({
    url: 'member/goods/getSkuList',
    method: 'get',
		params: data
  })
}


export function getExportGoodsList(data) {
  return request({
    url: 'member/goods/getExportList',
    method: 'get',
		params: data
  })
}