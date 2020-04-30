import request from '@/utils/request'

// 回款列表
export function rebateBillList(data) {
  return request({
    url: 'pmpReceivableRecords/queryList',
    method: 'post',
    data: data
    // headers: {
    //   'Content-Type': 'application/json;charset=UTF-8'
    // }
  })
}

export function rebateBillDetail(data) {
  return request({
    url: 'pmpReceivableRecords/queryById',
    method: 'post',
    data: data
    // headers: {
    //   'Content-Type': 'application/json;charset=UTF-8'
    // }
  })
}

// 新增回款
export function rebateBillAdd(data) {
  return request({
    url: 'pmpReceivableRecords/addOrUpdate',
    method: 'post',
    data: data,
    headers: {
      'Content-Type': 'application/json;charset=UTF-8'
    }
  })
}
