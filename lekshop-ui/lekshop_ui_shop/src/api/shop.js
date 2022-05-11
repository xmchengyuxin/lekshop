import request from '@/utils/request'

export function getShopList(data) {
  return request({
    url: '/system/shop/getList',
    method: 'get',
		params: data
  })
}

export function updateShop(data) {
  return request({
    url: 'system/shop/editSubmit',
    method: 'post',
    data
  })
}

export function deleteShop(data) {
  return request({
    url: 'system/shop/delete',
    method: 'post',
    params: {ids:data}
  })
}

export function getShopServiceSelector(data) {
  return request({
    url: '/system/shopService/getSelector',
    method: 'get',
		params: data
  })
}

export function getShopServiceList(data) {
  return request({
    url: '/system/shopService/getList',
    method: 'get',
		params: data
  })
}

export function updateShopService(data) {
  return request({
    url: 'system/shopService/editSubmit',
    method: 'post',
    data
  })
}

export function deleteShopService(data) {
  return request({
    url: 'system/shopService/delete',
    method: 'post',
    params: {ids:data}
  })
}

export function getShopFreightList(data) {
  return request({
    url: '/system/shopFreight/getList',
    method: 'get',
		params: data
  })
}

export function deleteShopFreight(data) {
  return request({
    url: 'system/shopFreight/delete',
    method: 'post',
    params: {ids:data}
  })
}

export function getShopInfoList(data) {
  return request({
    url: '/system/shopInfo/getList',
    method: 'get',
		params: data
  })
}

export function verifyShopInfo(data) {
  return request({
    url: 'system/shopInfo/verify',
    method: 'post',
    data
  })
}

export function deleteShopInfo(data) {
  return request({
    url: 'system/shopInfo/delete',
    method: 'post',
    params: {ids:data}
  })
}

export function getShopInfo() {
  return request({
    url: '/member/shopInfo/get',
    method: 'get',
  })
}
export function submitShopCert(data) {
  return request({
    url: '/member/shopInfo/applyShopCert',
    method: 'post',
    data
  })
}
export function submitShopDetail(data) {
  if(data.addTime) {
    data.addTime = '';
    data.updTime = '';
  }
  return request({
    url: '/member/shopInfo/applyShopDetail',
    method: 'post',
    data
  })
}
export function submitShop(data) {
  if(data.addTime) {
    data.addTime = '';
    data.updTime = '';
  }
  return request({
    url: '/member/shopInfo/submitShopInfo',
    method: 'post',
    data
  })
}
