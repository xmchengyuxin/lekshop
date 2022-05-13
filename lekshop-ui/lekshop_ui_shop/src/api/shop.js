import request from '@/utils/request'

export function getShopConfig(data) {
  return request({
    url: 'member/shopConfig/get',
    method: 'get',
		params: data
  })
}

export function updateShopConfig(data) {
  return request({
    url: 'member/shopConfig/editSubmit',
    method: 'post',
    data
  })
}

export function getShopCateList(data) {
  return request({
    url: 'member/shopCate/getList',
    method: 'get',
		params: data
  })
}

export function getAllShopCate(data) {
  return request({
    url: 'member/shopCate/getAll',
    method: 'get',
		params: data
  })
}

export function addShopCate(data) {
  return request({
    url: 'member/shopCate/add',
    method: 'post',
    data
  })
}

export function updateShopCate(data) {
  return request({
    url: 'member/shopCate/editSubmit',
    method: 'post',
    data
  })
}

export function deleteShopCate(data) {
  return request({
    url: 'member/shopCate/delete',
    method: 'post',
    params: {ids:data}
  })
}

export function getShop(data) {
  return request({
    url: 'member/shop/get',
    method: 'get',
		params: data
  })
}

export function updateShop(data) {
  return request({
    url: 'member/shop/update',
    method: 'post',
    data
  })
}

export function getShopServiceSelector(data) {
  return request({
    url: 'member/shopService/getSelector',
    method: 'get',
		params: data
  })
}

export function getShopFreightList(data) {
  return request({
    url: 'member/shopFreight/getList',
    method: 'get',
		params: data
  })
}

export function getFreight(data) {
  return request({
    url: 'member/shopFreight/get',
    method: 'get',
		params: data
  })
}

export function updateFreight(data) {
  return request({
    url: 'member/shopFreight/editSubmit',
    method: 'post',
    data
  })
}

export function deleteShopFreight(data) {
  return request({
    url: 'member/shopFreight/delete',
    method: 'post',
    params: {ids:data}
  })
}

export function getShopInfo() {
  return request({
    url: 'member/shopInfo/get',
    method: 'get',
  })
}
export function submitShopCert(data) {
  return request({
    url: 'member/shopInfo/applyShopCert',
    method: 'post',
    data
  })
}
export function submitShopDetail(data) {
  if(data.addTime) {
    data.addTime = null;
    data.updTime = null;
  }
  return request({
    url: 'member/shopInfo/applyShopDetail',
    method: 'post',
    data
  })
}
export function submitShop(data) {
  if(data.addTime) {
    data.addTime = null;
    data.updTime = null;
  }
  return request({
    url: '/member/shopInfo/submitShopInfo',
    method: 'post',
    data
  })
}

export function getShopCouponList(data) {
  return request({
    url: 'member/coupon/getList',
    method: 'get',
		params: data
  })
}

export function updateCoupon(data) {
  return request({
    url: 'member/coupon/editSubmit',
    method: 'post',
    data
  })
}

export function deleteShopCoupon(data) {
  return request({
    url: 'member/coupon/delete',
    method: 'post',
    params: {ids:data}
  })
}
