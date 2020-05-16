import request from '@/utils/request'

// 合同列表
export function prepaymentBillList(data) {
  return request({
    url: 'pmpContractPayment/queryAdvanceList',
    method: 'post',
    data: data
    // headers: {
    //   'Content-Type': 'application/json;charset=UTF-8'
    // }
  })
}

// 合同详情
export function prepaymentBillDetail(data) {
  return request({
    url: 'pmpContractPayment/queryAdvanceBybillId',
    method: 'post',
    data: data
  })
}

// 修改优先级
export function prepaymentSetPriority(data) {
  return request({
    url: 'pmpContractPayment/setPriority',
    method: 'post',
    data: data
  })
}
