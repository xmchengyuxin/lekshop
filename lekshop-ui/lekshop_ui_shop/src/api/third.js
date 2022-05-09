import request from '@/utils/request'

export function getKuaidiConfig() {
  return request({
    url: '/system/kuaidi/get',
    method: 'get'
  })
}

export function updateKuaidiConfig(data) {
  return request({
    url: '/system/kuaidi/editSubmit',
    method: 'post',
		data
  })
}

export function getQiniuConfig() {
  return request({
    url: '/system/qiniu/get',
    method: 'get'
  })
}

export function updateQiniuConfig(data) {
  return request({
    url: '/system/qiniu/editSubmit',
    method: 'post',
		data
  })
}

export function getSmsConfig() {
  return request({
    url: '/system/sms/get',
    method: 'get'
  })
}

export function updateSmsConfig(data) {
  return request({
    url: '/system/sms/editSubmit',
    method: 'post',
		data
  })
}

export function getWeixinConfig() {
  return request({
    url: '/system/weixin/get',
    method: 'get'
  })
}

export function updateWeixinConfig(data) {
  return request({
    url: '/system/weixin/editSubmit',
    method: 'post',
		data
  })
}

export function getZfbConfig() {
  return request({
    url: '/system/zfb/get',
    method: 'get'
  })
}

export function updateZfbConfig(data) {
  return request({
    url: '/system/zfb/editSubmit',
    method: 'post',
		data
  })
}

export function getDeliveryConfig() {
  return request({
    url: '/system/deliveryConfig/get',
    method: 'get'
  })
}

export function updateDeliveryConfig(data) {
  return request({
    url: '/system/deliveryConfig/editSubmit',
    method: 'post',
		data
  })
}

export function getWeixinTemplate(data) {
  return request({
    url: '/system/weixinTemplate/getList',
    method: 'get',
    params: data
  })
}

export function updateWeixinTemplate(data) {
  return request({
    url: '/system/weixinTemplate/editSubmit',
    method: 'post',
		data
  })
}

export function getApproveConfig() {
  return request({
    url: '/system/approveConfig/get',
    method: 'get'
  })
}

export function updateApproveConfig(data) {
  return request({
    url: '/system/approveConfig/editSubmit',
    method: 'post',
		data
  })
}

export function getTaofakeConfig() {
  return request({
    url: '/system/taofakeConfig/get',
    method: 'get'
  })
}

export function updateTaofakeConfig(data) {
  return request({
    url: '/system/taofakeConfig/editSubmit',
    method: 'post',
		data
  })
}

export function getTaofakeLeft() {
  return request({
    url: '/system/taofakeConfig/getLeftNum',
    method: 'get'
  })
}

export function getVrabbitConfig() {
  return request({
    url: '/system/vrabbitConfig/get',
    method: 'get'
  })
}

export function updateVrabbitConfig(data) {
  return request({
    url: '/system/vrabbitConfig/editSubmit',
    method: 'post',
		data
  })
}

export function getVrabbitLeft() {
  return request({
    url: '/system/vrabbitConfig/getLeftNum',
    method: 'get'
  })
}


export function getXiaofeixiangConfig() {
  return request({
    url: '/system/xiaofeixiangConfig/get',
    method: 'get'
  })
}

export function updateXiaofeixiangConfig(data) {
  return request({
    url: '/system/xiaofeixiangConfig/editSubmit',
    method: 'post',
		data
  })
}

export function getXiaofeixiangLeft() {
  return request({
    url: '/system/xiaofeixiangConfig/getLeftNum',
    method: 'get'
  })
}

export function getGiftConfig() {
  return request({
    url: '/system/giftConfig/get',
    method: 'get'
  })
}

export function updateGiftConfig(data) {
  return request({
    url: '/system/giftConfig/editSubmit',
    method: 'post',
		data
  })
}

export function getGiftLeft() {
  return request({
    url: '/system/giftConfig/getLeftNum',
    method: 'get'
  })
}

export function getTbAssistantConfig() {
  return request({
    url: '/system/tbAssistantConfig/get',
    method: 'get'
  })
}

export function updateTbAssistantConfig(data) {
  return request({
    url: '/system/tbAssistantConfig/editSubmit',
    method: 'post',
		data
  })
}

export function getTbAssistantLeft() {
  return request({
    url: '/system/tbAssistantConfig/getLeftNum',
    method: 'get'
  })
}
