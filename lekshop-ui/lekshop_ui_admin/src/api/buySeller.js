import request from '@/utils/request'

export function getBuyerList(data) {
  return request({
    url: '/system/buyer/getList',
    method: 'get',
		params: data
  })
}

export function getBuyerListByAreaCode(data) {
  return request({
    url: '/system/buyer/getListByAreaCode',
    method: 'get',
		params: data
  })
}


export function editBuyer(data) {
  return request({
    url: 'system/buyer/editSubmit',
    method: 'post',
    data
  })
}

export function stopBuyer(data) {
  return request({
    url: 'system/buyer/stop',
    method: 'post',
     params: {ids:data}
  })
}

export function startBuyer(data) {
  return request({
    url: 'system/buyer/start',
    method: 'post',
     params: {ids:data}
  })
}

export function lockBuyer(data) {
  return request({
    url: 'system/buyer/lock',
    method: 'post',
     params: {ids:data}
  })
}

export function unlockBuyer(data) {
  return request({
    url: 'system/buyer/unlock',
    method: 'post',
     params: {ids:data}
  })
}

export function verifyBuyer(data) {
  return request({
    url: 'system/buyer/verify',
    method: 'post',
    data
  })
}

export function verifyHuabei(data) {
  return request({
    url: 'system/buyer/verifyHuabei',
    method: 'post',
    data
  })
}


export function verifyAddress(data) {
  return request({
    url: 'system/buyer/verifyAddress',
    method: 'post',
    data
  })
}

export function deleteBuyer(data) {
  return request({
    url: 'system/buyer/delete',
    method: 'post',
    params: {ids:data}
  })
}

export function getSellerList(data) {
  return request({
    url: '/system/seller/getList',
    method: 'get',
		params: data
  })
}

export function stopSeller(data) {
  return request({
    url: 'system/seller/stop',
    method: 'post',
     params: {ids:data}
  })
}

export function startSeller(data) {
  return request({
    url: 'system/seller/start',
    method: 'post',
     params: {ids:data}
  })
}

export function lockSeller(data) {
  return request({
    url: 'system/seller/lock',
    method: 'post',
    params: {ids:data}
  })
}

export function unlockSeller(data) {
  return request({
    url: 'system/seller/unlock',
    method: 'post',
     params: {ids:data}
  })
}

export function verifySeller(data) {
  return request({
    url: 'system/seller/verify',
    method: 'post',
    data
  })
}

export function deleteSeller(data) {
  return request({
    url: 'system/seller/delete',
    method: 'post',
    params: {ids:data}
  })
}

export function editSeller(data) {
  return request({
    url: 'system/seller/editSubmit',
    method: 'post',
    data
  })
}
