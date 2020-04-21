import request from '@/utils/request'

// 合同列表
export function contactList(data) {
  return request({
    url: 'pmpContract/queryList',
    method: 'post',
    data: data,
    headers: {
      'Content-Type': 'application/json;charset=UTF-8'
    }
  })
}

// 合同详情
export function contactDetail(data) {
  return request({
    url: 'pmpContract/queryById',
    method: 'post',
    data: data
  })
}

// 合同删除
export function contactDelete(data) {
  return request({
    url: 'pmpContract/delete',
    method: 'post',
    data: data
  })
}
// 合同增加
export function contactAdd(data) {
  return request({
    url: 'pmpContract/add',
    method: 'post',
    data: data,
    headers: {
      'Content-Type': 'application/json;charset=UTF-8'
    }
  })
}

// 支付比例
export function paymentList(data) {
  return request({
    url: 'pmpContractPayment/queryPaymentByMonthly',
    method: 'post',
    data: data
  })
}

// 合同仪表盘
export function contractDashboard(data) {
  return request({
    url: 'pmpContract/contractDashboard',
    method: 'post',
    data: data
    // headers: {
    //   'Content-Type': 'application/json;charset=UTF-8'
    // }
  })
}
