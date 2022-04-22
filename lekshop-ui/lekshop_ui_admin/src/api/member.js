import request from '@/utils/request'

export function getMemberGroupList(query) {
  return request({
    url: '/system/memberGroup/getList',
    method: 'get',
    params: query
  })
}

export function getMemberGroupSelector(query) {
  return request({
    url: '/system/memberGroup/getSelector',
    method: 'get',
    params: query
  })
}


export function updateMemberGroup(data) {
  return request({
    url: '/system/memberGroup/editSubmit',
    method: 'post',
    data
  })
}

export function updateMemberGroupStatus(data) {
  return request({
    url: '/system/memberGroup/updateStatus',
    method: 'post',
    data
  })
}


export function deleteMemberGroup(data) {
  return request({
    url: '/system/memberGroup/delete',
    method: 'post',
    params: {ids:data}
  })
}

export function getMemberList(query) {
  return request({
    url: '/system/member/getList',
    method: 'get',
    params: query
  })
}

export function getMember(query) {
  return request({
    url: '/system/member/get',
    method: 'get',
    params: query
  })
}

export function getMemberDetail(query) {
  return request({
    url: '/system/member/getDetail',
    method: 'get',
    params: query
  })
}

export function updateMember(data) {
  return request({
    url: '/system/member/editSubmit',
    method: 'post',
    data
  })
}

export function updateMemberAccount(data) {
  return request({
    url: '/system/member/updateAccount',
    method: 'post',
    data
  })
}

export function getMemberRealnameList(query) {
  return request({
    url: '/system/memberRealname/getList',
    method: 'get',
    params: query
  })
}

export function verifyMemberRealname(data) {
  return request({
    url: '/system/memberRealname/verify',
    method: 'post',
    data
  })
}

export function updateMemberRealname(data) {
  return request({
    url: '/system/memberRealname/editSubmit',
    method: 'post',
    data
  })
}

export function deleteMemberRealname(data) {
  return request({
    url: '/system/memberRealname/delete',
    method: 'post',
    params: {ids:data}
  })
}

export function getMemberBankList(query) {
  return request({
    url: '/system/memberBank/getList',
    method: 'get',
    params: query
  })
}

export function verifyMemberBank(data) {
  return request({
    url: '/system/memberBank/verify',
    method: 'post',
    data
  })
}

export function updateMemberBank(data) {
  return request({
    url: '/system/memberBank/editSubmit',
    method: 'post',
    data
  })
}

export function deleteMemberBank(data) {
  return request({
    url: '/system/memberBank/delete',
    method: 'post',
    params: {ids:data}
  })
}

export function getMemberBlockList(query) {
  return request({
    url: '/system/memberBlock/getList',
    method: 'get',
    params: query
  })
}

export function addMemberBlock(data) {
  return request({
    url: '/system/memberBlock/blockin',
    method: 'post',
    data
  })
}

export function deleteMemberBlock(data) {
  return request({
    url: '/system/memberBlock/delete',
    method: 'post',
    params: {ids:data}
  })
}

export function getSystemBlockList(query) {
  return request({
    url: '/system/systemBlock/getList',
    method: 'get',
    params: query
  })
}

export function addSystemBlock(data) {
  return request({
    url: '/system/systemBlock/add',
    method: 'post',
    data
  })
}

export function removeSystemBlock(data) {
  return request({
    url: '/system/systemBlock/remove',
    method: 'post',
    data
  })
}

export function deleteSystemBlock(data) {
  return request({
    url: '/system/systemBlock/delete',
    method: 'post',
    params: {ids:data}
  })
}

export function importBlock(data) {
  return request({
    url: '/system/systemBlock/importByExcel',
    method: 'post',
    data
  })
}

export function getOperationLogList(query) {
  return request({
    url: '/system/memberOperation/getList',
    method: 'get',
    params: query
  })
}

export function deleteOperationLog(data) {
  return request({
    url: '/system/memberOperation/delete',
    method: 'post',
    data
  })
}

export function getMemberPunishmentList(query) {
  return request({
    url: '/system/memberPunishment/getList',
    method: 'get',
    params: query
  })
}

export function addMemberPunishment(data) {
  return request({
    url: '/system/memberPunishment/add',
    method: 'post',
    data
  })
}

export function deleteMemberPunishment(data) {
  return request({
    url: '/system/memberPunishment/delete',
    method: 'post',
    params: {ids:data}
  })
}

export function getZtList(query) {
  return request({
    url: '/system/member/getZtList',
    method: 'get',
    params: query
  })
}

export function getSjList(query) {
  return request({
    url: '/system/member/getSjList',
    method: 'get',
    params: query
  })
}

export function updateInvite(data) {
  return request({
    url: '/system/member/updateInvite',
    method: 'post',
    data
  })
}

export function transferAmount(data) {
  return request({
    url: '/system/member/transferAmount',
    method: 'post',
    data
  })
}

export function sendMsg(data) {
  return request({
    url: '/system/member/sendMsg',
    method: 'post',
    data
  })
}

export function limitWithdraw(data) {
  return request({
    url: '/system/member/limitWithdraw',
    method: 'post',
    data
  })
}
