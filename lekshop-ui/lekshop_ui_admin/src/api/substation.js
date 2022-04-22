import request from '@/utils/request'

export function getStationList(data) {
  return request({
    url: '/system/station/getList',
    method: 'get',
		params: data
  })
}

export function updateStation(data) {
  return request({
    url: 'system/station/editSubmit',
    method: 'post',
    data
  })
}

export function deleteStation(data) {
  return request({
    url: 'system/station/delete',
    method: 'post',
    params: {ids:data}
  })
}

export function synMission(data) {
  return request({
    url: 'system/station/synMission',
    method: 'post',
    data
  })
}

export function getSubWithdrawList(data) {
  return request({
    url: '/system/subWithdraw/getList',
    method: 'get',
		params: data
  })
}

export function countSubWithdraw(data) {
  return request({
    url: '/system/subWithdraw/count',
    method: 'get',
		params: data
  })
}

export function verifySubWithdraw(data) {
  return request({
    url: 'system/subWithdraw/verifyEditSubmit',
    method: 'post',
    data
  })
}

export function setDoingSubWithdraw(data) {
  return request({
    url: 'system/subWithdraw/setDoing',
    method: 'post',
    data
  })
}

export function getAgentWithdrawList(data) {
  return request({
    url: '/system/agentWithdraw/getList',
    method: 'get',
		params: data
  })
}

export function countAgentWithdraw(data) {
  return request({
    url: '/system/agentWithdraw/count',
    method: 'get',
		params: data
  })
}

export function verifyAgentWithdraw(data) {
  return request({
    url: 'system/agentWithdraw/verifyEditSubmit',
    method: 'post',
    data
  })
}

export function setDoingAgentWithdraw(data) {
  return request({
    url: 'system/agentWithdraw/setDoing',
    method: 'post',
    data
  })
}

export function getSubBaseFee(data) {
  return request({
    url: '/system/subFee/get',
    method: 'get',
		params: data
  })
}

export function updateSubFee(data) {
  return request({
    url: 'system/subFee/editSubmit',
    method: 'post',
    data
  })
}

export function getSubReportList(data) {
  return request({
    url: '/system/subReport/getList',
    method: 'get',
		params: data
  })
}
