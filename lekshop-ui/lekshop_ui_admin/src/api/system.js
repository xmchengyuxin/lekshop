import request from '@/utils/request'

export function getWebInfo() {
  return request({
    url: '/system/getWebInfo',
    method: 'get'
  })
}

export function getBankSelector() {
  return request({
    url: '/system/getBankSelector',
    method: 'get'
  })
}


export function getDeliveryTypeSelector() {
  return request({
    url: '/system/deliveryType/getSelector',
    method: 'get'
  })
}


export function upload(data) {
  return request({
    url: '/system/upload',
    method: 'post',
    data
  })
}
