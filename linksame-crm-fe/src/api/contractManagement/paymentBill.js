import request from '@/utils/request'

// 合同列表
export function paymentBillList(data) {
  return request({
    url: 'pmpContractPaymentRecord/queryList',
    method: 'post',
    data: data
    // headers: {
    //   'Content-Type': 'application/json;charset=UTF-8'
    // }
  })
}

export function paymentBillDetail(data) {
  return request({
    url: 'pmpContractPaymentRecord/queryById',
    method: 'post',
    data: data
    // headers: {
    //   'Content-Type': 'application/json;charset=UTF-8'
    // }
  })
}
