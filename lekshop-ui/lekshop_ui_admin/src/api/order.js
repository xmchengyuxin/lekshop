import request from '@/utils/request'

export function getOrderList(data) {
  return request({
    url: 'system/order/getList',
    method: 'get',
		params: data
  })
}

export function getOrder(data) {
  return request({
    url: 'system/order/get',
    method: 'get',
		params: data
  })
}

export function delivery(data) {
  return request({
    url: 'system/order/delivery',
    method: 'post',
    data
  })
}

export function getFreightList(data) {
  return request({
    url: 'system/order/getFreightList',
    method: 'get',
		params: data
  })
}

export function getCommentList(data) {
  return request({
    url: 'system/comment/getList',
    method: 'get',
		params: data
  })
}

export function getLeftCommentList(data) {
  return request({
    url: 'system/comment/getLeftList',
    method: 'get',
		params: data
  })
}

export function answerComment(data) {
  return request({
    url: 'system/comment/answer',
    method: 'post',
    data
  })
}

export function deleteLeftComment(data) {
  return request({
    url: 'system/comment/deleteLeftComment',
    method: 'post',
    data
  })
}

export function deleteComment(data) {
  return request({
    url: 'system/comment/delete',
    method: 'post',
    params: {ids:data}
  })
}




export function getRefundList(data) {
  return request({
    url: 'system/refund/getList',
    method: 'get',
		params: data
  })
}

export function getRefund(data) {
  return request({
    url: 'system/refund/get',
    method: 'get',
		params: data
  })
}

export function agreeRefund(data) {
  return request({
    url: 'system/refund/agree',
    method: 'post',
    data
  })
}

export function refuseRefund(data) {
  return request({
    url: 'system/refund/refuse',
    method: 'post',
    data
  })
}

export function confirmReceiveByShop(data) {
  return request({
    url: 'system/refund/confirmReceiveByShop',
    method: 'post',
    data
  })
}

export function getGroupList(data) {
  return request({
    url: 'system/assemble/getList',
    method: 'get',
		params: data
  })
}

export function getGroupsystemList(data) {
  return request({
    url: 'system/assemble/getsystemList',
    method: 'get',
		params: data
  })
}

export function editPrice(data) {
  return request({
    url: 'system/order/editPriceSubmit',
    method: 'post',
    data
  })
}

export function getPriceList(data) {
  return request({
    url: 'system/order/getPriceLog',
    method: 'get',
		params: data
  })
}
