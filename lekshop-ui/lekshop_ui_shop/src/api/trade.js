import request from '@/utils/request'

//仓库列表
export function storageGet(query) {
  return request({
    url: '/member/storage/getList',
    method: 'get',
    params: query
  })
}

//添加仓库
export function storageAdd(query) {
  return request({
    url: '/member/storage/add',
    method: 'post',
    params: query
  })
}

//删除仓库
export function storageDel(query) {
  return request({
    url: '/member/storage/delete',
    method: 'post',
    params: query
  })
}

//仓库库存列表
export function storageStockGet(query) {
  return request({
    url: '/member/storageStock/getList',
    method: 'get',
    params: query
  })
}

export function storageStockGetLog(data) {
  return request({
    url: '/member/storageStock/getLogList',
    method: 'get',
    params: data
  })
}

//更新仓库预警库存
export function storageWarnStockEdit(query) {
  return request({
    url: '/member/storageStock/updateWarnStock',
    method: 'post',
    params: query
  })
}

//入库单列表
export function stockOrderGet(query) {
  return request({
    url: '/member/stockOrder/getList',
    method: 'get',
    params: query
  })
}

//入库单详情
export function stockOrderGetDetail(query) {
  return request({
    url: '/member/stockOrder/get',
    method: 'get',
    params: query
  })
}

//添加入库单
export function stockOrderAdd(data) {
  return request({
    url: '/member/stockOrder/add',
    method: 'post',
    data
  })
}

//库存修改申请单列表
export function stockEditGet(query) {
  return request({
    url: '/member/stockEdit/getList',
    method: 'get',
    params: query
  })
}

//库存修改申请单详情
export function stockEditGetDetail(query) {
  return request({
    url: '/member/stockEdit/get',
    method: 'get',
    params: query
  })
}

//添加库存修改申请单
export function stockEditAdd(query) {
  return request({
    url: '/member/stockEdit/add',
    method: 'post',
    params: query
  })
}

//库存修改申请单审核通过
export function stockEditVerifySus(query) {
  return request({
    url: '/member/stockEdit/verifySus',
    method: 'post',
    params: query
  })
}


//库存修改申请单审核驳回
export function stockEditReject(query) {
  return request({
    url: '/member/stockEdit/reject',
    method: 'post',
    params: query
  })
}

//盲盘列表
export function storageCountingGet(query) {
  return request({
    url: '/member/storageCounting/getList',
    method: 'get',
    params: query
  })
}

//盲盘详情
export function storageCountingGetDetail(query) {
  return request({
    url: '/member/storageCounting/getDetailList',
    method: 'get',
    params: query
  })
}

//添加盲盘
export function storageCountingAdd(data) {
  return request({
    url: '/member/storageCounting/add',
    method: 'post',
    data
  })
}

export function getSupplierList(data) {
  return request({
    url: '/member/supplier/getList',
    method: 'get',
		params: data
  })
}

export function updateSupplier(data) {
  return request({
    url: 'member/supplier/add',
    method: 'post',
    data
  })
}

export function deleteSupplier(data) {
  return request({
    url: 'member/supplier/delete',
    method: 'post',
    params: {ids:data}
  })
}
