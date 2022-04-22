import request from '@/utils/request'

export function getTaskAdvancesList(data) {
  return request({
    url: '/system/taskAdvances/getList',
    method: 'get',
	params: data
  })
}

export function getWaitDeliveryList(data) {
  return request({
    url: '/system/taskAdvances/getWaitDeliveryList',
    method: 'get',
	params: data
  })
}

export function getTaskAdvancesCancelList(data) {
  return request({
    url: '/system/taskAdvances/getCancelList',
    method: 'get',
	params: data
  })
}

export function getTaskAdvances(data) {
  return request({
    url: '/system/taskAdvances/get',
    method: 'get',
    params: data
  })
}

export function changeRebates(data) {
  return request({
    url: 'system/taskAdvances/changeRebates',
    method: 'post',
    data
  })
}

export function lockTaskAdvances(data) {
  return request({
    url: 'system/taskAdvances/lock',
    method: 'post',
     params: {ids:data}
  })
}

export function unlockTaskAdvances(data) {
  return request({
    url: 'system/taskAdvances/unlock',
    method: 'post',
     params: {ids:data}
  })
}

export function deleteTaskAdvances(data) {
  return request({
    url: 'system/taskAdvances/delete',
    method: 'post',
    data
  })
}

export function updateTask(data) {
  return request({
    url: 'system/taskAdvances/update',
    method: 'post',
    data
  })
}

export function verifyBuyer(data) {
  return request({
    url: 'system/taskAdvances/verifyBuyer',
    method: 'post',
    data
  })
}

export function setWaitBuyer(data) {
  return request({
    url: 'system/taskAdvances/setWaitBuyer',
    method: 'post',
    data
  })
}


export function setBuyer(data) {
  return request({
    url: 'system/taskAdvances/setBuyer',
    method: 'post',
    data
  })
}


export function payTaskByBuyer(data) {
  return request({
    url: 'system/taskAdvances/payTaskByBuyer',
    method: 'post',
    data
  })
}

export function rejectByUnpay(data) {
  return request({
    url: 'system/taskAdvances/rejectByUnpay',
    method: 'post',
    data
  })
}

export function rejectByUnreceive(data) {
  return request({
    url: 'system/taskAdvances/rejectByUnreceive',
    method: 'post',
    data
  })
}


export function delivery(data) {
  return request({
    url: 'system/taskAdvances/delivery',
    method: 'post',
    data
  })
}

export function updatePublishTime(data) {
  return request({
    url: 'system/taskAdvances/updatePublishTime',
    method: 'post',
    data
  })
}

export function delayPayTime(data) {
  return request({
    url: 'system/taskAdvances/delayPayTime',
    method: 'post',
    data
  })
}

export function signAndGoodsCommentByBuyer(data) {
  return request({
    url: 'system/taskAdvances/signAndGoodsCommentByBuyer',
    method: 'post',
    data
  })
}


export function verifySusAndFinishTask(data) {
  return request({
    url: 'system/taskAdvances/verifySusAndFinishTask',
    method: 'post',
    data
  })
}


export function addGoodsComment(data) {
  return request({
    url: 'system/taskAdvances/addGoodsComment',
    method: 'post',
    data
  })
}


export function verifySusAddGoodsComment(data) {
  return request({
    url: 'system/taskAdvances/verifySusAddGoodsComment',
    method: 'post',
    data
  })
}

export function commentEachother(data) {
  return request({
    url: 'system/taskAdvances/commentEachother',
    method: 'post',
    data
  })
}

export function getTaskComplaintList(data) {
  return request({
    url: '/system/taskComplaint/getList',
    method: 'get',
	params: data
  })
}

export function getTaskComplaint(data) {
  return request({
    url: '/system/taskComplaint/get',
    method: 'get',
	params: data
  })
}

export function dealwithComplaint(data) {
  return request({
    url: 'system/taskComplaint/dealwithBySystem',
    method: 'post',
    data
  })
}

export function finishComplaint(data) {
  return request({
    url: 'system/taskComplaint/finish',
    method: 'post',
    data
  })
}

export function deleteTaskComplaint(data) {
  return request({
    url: 'system/taskComplaint/delete',
    method: 'post',
    params: {ids:data}
  })
}

export function getTaskCommentList(data) {
  return request({
    url: '/system/taskComment/getList',
    method: 'get',
	params: data
  })
}

export function deleteTaskComment(data) {
  return request({
    url: 'system/taskComment/delete',
    method: 'post',
    params: {ids:data}
  })
}

export function getTaskAdvancesTemplateList(data) {
  return request({
    url: '/system/taskAdvancesTemplate/getList',
    method: 'get',
	params: data
  })
}

export function deleteTaskAdvancesTemplate(data) {
  return request({
    url: 'system/taskAdvancesTemplate/delete',
    method: 'post',
    params: {ids:data}
  })
}

export function getTaskTrafficTemplateList(data) {
  return request({
    url: '/system/taskTrafficTemplate/getList',
    method: 'get',
	params: data
  })
}

export function deleteTaskTrafficTemplate(data) {
  return request({
    url: 'system/taskTrafficTemplate/delete',
    method: 'post',
    params: {ids:data}
  })
}



export function getTaskTrafficList(data) {
  return request({
    url: '/system/taskTraffic/getList',
    method: 'get',
	params: data
  })
}

export function getTaskTraffic(data) {
  return request({
    url: '/system/taskTraffic/get',
    method: 'get',
    params: data
  })
}

export function lockTaskTraffic(data) {
  return request({
    url: 'system/taskTraffic/lock',
    method: 'post',
     params: {ids:data}
  })
}

export function unlockTaskTraffic(data) {
  return request({
    url: 'system/taskTraffic/unlock',
    method: 'post',
     params: {ids:data}
  })
}

export function deleteTaskTraffic(data) {
  return request({
    url: 'system/taskTraffic/delete',
    method: 'post',
    params: {ids:data}
  })
}

export function updateTaskTraffic(data) {
  return request({
    url: 'system/taskTraffic/update',
    method: 'post',
    data
  })
}

export function shangjiaTaskTraffic(data) {
  return request({
    url: 'system/taskTraffic/shangjia',
    method: 'post',
     params: {ids:data}
  })
}

export function xiajiaTaskTraffic(data) {
  return request({
    url: 'system/taskTraffic/xiajia',
    method: 'post',
    params: {ids:data}
  })
}

export function delayTaskTrafficExpiredTime(data) {
  return request({
    url: 'system/taskTraffic/delayExpiredTime',
    method: 'post',
    data
  })
}

export function getTaskTrafficOrderList(data) {
  return request({
    url: '/system/taskTrafficOrder/getList',
    method: 'get',
    params: data
  })
}

export function deleteTaskTrafficOrder(data) {
  return request({
    url: 'system/taskTrafficOrder/delete',
    method: 'post',
    params: {ids:data}
  })
}

export function closeTaskTrafficOrder(data) {
  return request({
    url: 'system/taskTrafficOrder/close',
    method: 'post',
    params: {ids:data}
  })
}

export function resumeTaskTrafficOrder(data) {
  return request({
    url: 'system/taskTrafficOrder/resume',
    method: 'post',
    params: {ids:data}
  })
}

export function submitTaskBuyer(data) {
  return request({
    url: 'system/taskTrafficOrder/submitTaskBuyer',
    method: 'post',
    data
  })
}

export function verifySusTaskBuyer(data) {
  return request({
    url: 'system/taskTrafficOrder/verifySusTaskBuyer',
    method: 'post',
    data
  })
}

export function batchVerifySusAndFinishTask(data) {
  return request({
    url: 'system/taskAdvances/batchVerifySusAndFinishTask',
    method: 'post',
    params: {ids:data}
  })
}

export function removeMakeComment(data) {
  return request({
    url: 'system/taskAdvances/removeMakeComment',
    method: 'post',
    data
  })
}

export function getTaskAdvancesParentList(data) {
  return request({
    url: '/system/taskAdvancesParent/getList',
    method: 'get',
	params: data
  })
}

export function getTaskAdvancesParent(data) {
  return request({
    url: '/system/taskAdvancesParent/get',
    method: 'get',
    params: data
  })
}

export function verifyTaskAdvancesParent(data) {
  return request({
    url: 'system/taskAdvancesParent/verify',
    method: 'post',
    data
  })
}


export function validateTask(data) {
  return request({
    url: '/system/taskAdvances/validate',
    method: 'get',
	params: data
  })
}

export function updateTaskParent(data) {
  return request({
    url: 'system/taskAdvancesParent/update',
    method: 'post',
    data
  })
}

export function getTaskSendLogList(query) {
  return request({
    url: '/system/taskSensLog/getList',
    method: 'get',
    params: query
  })
}

export function deleteTaskSendLog(data) {
  return request({
    url: '/system/taskSensLog/delete',
    method: 'post',
    data
  })
}

export function updatePlanTime(data) {
  return request({
    url: 'system/taskAdvancesParent/updatePlanTime',
    method: 'post',
    data
  })
}
