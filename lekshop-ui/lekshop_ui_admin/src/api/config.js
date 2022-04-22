import request from '@/utils/request'

export function getParams(data) {
  return request({
    url: '/system/params/get',
    method: 'get',
		params: data
  })
}

export function updateParams(data) {
  return request({
    url: '/system/params/editSubmit',
    method: 'post',
    data
  })
}

export function getShopCateList(query) {
  return request({
    url: '/system/shopCate/getList',
    method: 'get',
    params: query
  })
}

export function getShopCateSelector(query) {
  return request({
    url: '/system/shopCate/getSelector',
    method: 'get',
    params: query
  })
}


export function updateShopCate(data) {
  return request({
    url: '/system/shopCate/editSubmit',
    method: 'post',
    data
  })
}

export function deleteShopCate(data) {
  return request({
    url: '/system/shopCate/delete',
    method: 'post',
    params: {ids:data}
  })
}

export function getRechargeCardList(query) {
  return request({
    url: '/system/rechargeCard/getList',
    method: 'get',
    params: query
  })
}

export function updateRechargeCard(data) {
  return request({
    url: '/system/rechargeCard/editSubmit',
    method: 'post',
    data
  })
}

export function deleteRechargeCard(data) {
  return request({
    url: '/system/rechargeCard/delete',
    method: 'post',
    params: {ids:data}
  })
}

export function getConfigMissionList(query) {
  return request({
    url: '/system/configMission/getList',
    method: 'get',
    params: query
  })
}

export function getConfigMissionSelector() {
  return request({
    url: '/system/configMission/getSelector',
    method: 'get'
  })
}

export function updateConfigMission(data) {
  return request({
    url: '/system/configMission/editSubmit',
    method: 'post',
    data
  })
}

export function updateConfigMissionParams(data) {
  return request({
    url: '/system/configMission/updateParams',
    method: 'post',
    data
  })
}

export function deleteConfigMission(data) {
  return request({
    url: '/system/configMission/delete',
    method: 'post',
    params: {ids:data}
  })
}

export function getConfigAgentList(query) {
  return request({
    url: '/system/generalAgent/getList',
    method: 'get',
    params: query
  })
}

export function updateConfigAgent(data) {
  return request({
    url: '/system/generalAgent/editSubmit',
    method: 'post',
    data
  })
}

export function deleteConfigAgent(data) {
  return request({
    url: '/system/generalAgent/delete',
    method: 'post',
    params: {ids:data}
  })
}

export function getTaskColumList(query) {
  return request({
    url: '/system/taskColum/getList',
    method: 'get',
    params: query
  })
}

export function getTaskColumSelector(query) {
  return request({
    url: '/system/taskColum/getSelector',
    method: 'get',
    params: query
  })
}


export function updateTaskColum(data) {
  return request({
    url: '/system/taskColum/editSubmit',
    method: 'post',
    data
  })
}

export function deleteTaskColum(data) {
  return request({
    url: '/system/taskColum/delete',
    method: 'post',
    params: {ids:data}
  })
}

export function getSecretSelector(query) {
  return request({
    url: '/system/secret/getList',
    method: 'get',
    params: query
  })
}

export function getConfigAccountList(query) {
  return request({
    url: '/system/configAccount/getList',
    method: 'get',
    params: query
  })
}

export function updateConfigAccount(data) {
  return request({
    url: '/system/configAccount/editSubmit',
    method: 'post',
    data
  })
}

export function deleteConfigAccount(data) {
  return request({
    url: '/system/configAccount/delete',
    method: 'post',
    params: {ids:data}
  })
}

export function getShopTargetList(query) {
  return request({
    url: '/system/shopTarget/getList',
    method: 'get',
    params: query
  })
}

export function getShopTargetSelector(query) {
  return request({
    url: '/system/shopTarget/getSelector',
    method: 'get',
    params: query
  })
}


export function updateShopTarget(data) {
  return request({
    url: '/system/shopTarget/editSubmit',
    method: 'post',
    data
  })
}

export function deleteShopTarget(data) {
  return request({
    url: '/system/shopTarget/delete',
    method: 'post',
    params: {ids:data}
  })
}

export function getAddressLimitList(query) {
  return request({
    url: '/system/addressLimit/getList',
    method: 'get',
    params: query
  })
}

export function updateAddressLimit(data) {
  return request({
    url: '/system/addressLimit/editSubmit',
    method: 'post',
    data
  })
}

export function deleteAddressLimit(data) {
  return request({
    url: '/system/addressLimit/delete',
    method: 'post',
    params: {ids:data}
  })
}

export function getConfigScanBlack(query) {
  return request({
    url: '/system/configScanBlack/get',
    method: 'get',
    params: query
  })
}

export function updateConfigScanBlack(data) {
  return request({
    url: '/system/configScanBlack/editSubmit',
    method: 'post',
    data
  })
}

export function scanBlack(data) {
  return request({
    url: '/system/configScanBlack/scanBlack',
    method: 'post',
    data
  })
}

export function getConfigScanBlackList(query) {
  return request({
    url: '/system/configScanBlack/getList',
    method: 'get',
    params: query
  })
}

export function getConfigPointList(query) {
  return request({
    url: '/system/configPoint/getList',
    method: 'get',
    params: query
  })
}

export function updateConfigPoint(data) {
  return request({
    url: '/system/configPoint/editSubmit',
    method: 'post',
    data
  })
}

export function deleteConfigPoint(data) {
  return request({
    url: '/system/configPoint/delete',
    method: 'post',
    params: {ids:data}
  })
}
