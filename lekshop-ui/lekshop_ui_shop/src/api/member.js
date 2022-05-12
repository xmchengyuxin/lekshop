import request from '@/utils/request'

export function getMemberGroupList(query) {
  return request({
    url: 'member/memberGroup/getList',
    method: 'get',
    params: query
  })
}

export function getMemberGroupSelector(query) {
  return request({
    url: 'member/memberGroup/getSelector',
    method: 'get',
    params: query
  })
}


export function updateMemberGroup(data) {
  return request({
    url: 'member/memberGroup/editSubmit',
    method: 'post',
    data
  })
}

export function updateMemberGroupStatus(data) {
  return request({
    url: 'member/memberGroup/updateStatus',
    method: 'post',
    data
  })
}


export function deleteMemberGroup(data) {
  return request({
    url: 'member/memberGroup/delete',
    method: 'post',
    params: {ids:data}
  })
}

export function getMemberList(query) {
  return request({
    url: 'member/member/getList',
    method: 'get',
    params: query
  })
}

export function getMember(query) {
  return request({
    url: 'member/member/get',
    method: 'get',
    params: query
  })
}

export function getMemberDetail(query) {
  return request({
    url: 'member/member/getDetail',
    method: 'get',
    params: query
  })
}

export function updateMember(data) {
  return request({
    url: 'member/member/editSubmit',
    method: 'post',
    data
  })
}

export function updateMemberAccount(data) {
  return request({
    url: 'member/member/updateAccount',
    method: 'post',
    data
  })
}

export function getMemberRealnameList(query) {
  return request({
    url: 'member/memberRealname/getList',
    method: 'get',
    params: query
  })
}

export function verifyMemberRealname(data) {
  return request({
    url: 'member/memberRealname/verify',
    method: 'post',
    data
  })
}

export function updateMemberRealname(data) {
  return request({
    url: 'member/memberRealname/editSubmit',
    method: 'post',
    data
  })
}

export function deleteMemberRealname(data) {
  return request({
    url: 'member/memberRealname/delete',
    method: 'post',
    params: {ids:data}
  })
}

export function getMemberBankList(query) {
  return request({
    url: 'member/memberBank/getList',
    method: 'get',
    params: query
  })
}

export function verifyMemberBank(data) {
  return request({
    url: 'member/memberBank/verify',
    method: 'post',
    data
  })
}

export function updateMemberBank(data) {
  return request({
    url: 'member/memberBank/editSubmit',
    method: 'post',
    data
  })
}

export function deleteMemberBank(data) {
  return request({
    url: 'member/memberBank/delete',
    method: 'post',
    params: {ids:data}
  })
}

export function getMemberBlockList(query) {
  return request({
    url: 'member/memberBlock/getList',
    method: 'get',
    params: query
  })
}

export function addMemberBlock(data) {
  return request({
    url: 'member/memberBlock/blockin',
    method: 'post',
    data
  })
}

export function deleteMemberBlock(data) {
  return request({
    url: 'member/memberBlock/delete',
    method: 'post',
    params: {ids:data}
  })
}

export function getSystemBlockList(query) {
  return request({
    url: 'membermemberBlock/getList',
    method: 'get',
    params: query
  })
}

export function addSystemBlock(data) {
  return request({
    url: 'membermemberBlock/add',
    method: 'post',
    data
  })
}

export function removeSystemBlock(data) {
  return request({
    url: 'membermemberBlock/remove',
    method: 'post',
    data
  })
}

export function deleteSystemBlock(data) {
  return request({
    url: 'membermemberBlock/delete',
    method: 'post',
    params: {ids:data}
  })
}

export function importBlock(data) {
  return request({
    url: 'membermemberBlock/importByExcel',
    method: 'post',
    data
  })
}

export function getOperationLogList(query) {
  return request({
    url: 'member/memberOperation/getList',
    method: 'get',
    params: query
  })
}

export function deleteOperationLog(data) {
  return request({
    url: 'member/memberOperation/delete',
    method: 'post',
    data
  })
}

export function getMemberPunishmentList(query) {
  return request({
    url: 'member/memberPunishment/getList',
    method: 'get',
    params: query
  })
}

export function addMemberPunishment(data) {
  return request({
    url: 'member/memberPunishment/add',
    method: 'post',
    data
  })
}

export function deleteMemberPunishment(data) {
  return request({
    url: 'member/memberPunishment/delete',
    method: 'post',
    params: {ids:data}
  })
}

export function getZtList(query) {
  return request({
    url: 'member/member/getZtList',
    method: 'get',
    params: query
  })
}

export function getSjList(query) {
  return request({
    url: 'member/member/getSjList',
    method: 'get',
    params: query
  })
}

export function updateInvite(data) {
  return request({
    url: 'member/member/updateInvite',
    method: 'post',
    data
  })
}

export function transferAmount(data) {
  return request({
    url: 'member/member/transferAmount',
    method: 'post',
    data
  })
}

export function sendMsg(data) {
  return request({
    url: 'member/member/sendMsg',
    method: 'post',
    data
  })
}

export function limitWithdraw(data) {
  return request({
    url: 'member/member/limitWithdraw',
    method: 'post',
    data
  })
}

export function getMemberAccount(query) {
  return request({
    url: 'member/getAccount',
    method: 'get',
    params: query
  })
}