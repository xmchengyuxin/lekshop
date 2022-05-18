import request from '@/utils/request'

export function getWebInfo() {
  return request({
    url: 'common/getWebInfo',
    method: 'get'
  })
}

export function getBankSelector() {
  return request({
    url: 'common/getBankSelector',
    method: 'get'
  })
}

export function getDeliveryTypeSelector() {
  return request({
    url: 'common/deliveryType/getSelector',
    method: 'get'
  })
}
