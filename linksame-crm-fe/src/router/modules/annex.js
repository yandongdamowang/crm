/** 客户管理路由 */
import Layout from '@/views/layout/annexLayout'

const annexRouter = {
  path: '/annex',
  component: Layout,
  redirect: '/annex/netdisk',
  name: 'annex',
  hidden: true,
  meta: {
    // requiresAuth: false,
    title: '网盘'
  },
  children: [
    {
      path: 'netdisk', // 工作台
      //   hidden: true,
      component: () => import('@/views/annexManagement/netdisk/Main'),
      meta: {
        title: '网盘',
        icon: 'schedule'
      }
    },

    {
      path: 'recycle',
      hidden: true,
      component: () => import('@/views/contractManagement/rebateBill/Main'),
      meta: {
        title: '回收站',
        icon: 'log'
      }
    }

    // {
    //   path: 'ledger',
    //   meta: {
    //     title: '附件',
    //     icon: 'examine'
    //   },
    //   children: [
    //     {
    //       path: 'prepayment',
    //       meta: {
    //         title: '预付款账单'
    //       }
    //     },
    //     {
    //       path: 'payment',
    //       meta: {
    //         title: '付款账单'
    //       }
    //     },
    //     {
    //       path: 'rebate',
    //       meta: {
    //         title: '回款账单'
    //       }
    //     }
    //   ]
    // }

    // {
    //   path: 'approval',
    //   meta: {
    //     title: '审批',
    //     icon: 'examine'
    //   },
    //   children: [
    //     {
    //       path: 'approveContract',
    //       meta: {
    //         title: '待审批合同'
    //       }
    //     },
    //     {
    //       path: 'approveRebate',
    //       meta: {
    //         title: '待审批回款'
    //       }
    //     }
    //   ]
    // },

    // {
    //   path: 'template',
    //   meta: {
    //     title: '合同模板',
    //     icon: 'log'
    //   },
    //   component: () => import('@/views/contractManagement/template/Main')
    // },

    // {
    //   path: 'approveContract',
    //   //   hidden: true,
    //   component: () => import('@/views/contractManagement/approveContract/Main'),
    //   meta: {
    //     title: '付款账单',
    //     icon: 'schedule'
    //   }
    // },

    // {
    //   path: 'approveRebate',
    //   //   hidden: true,
    //   component: () => import('@/views/contractManagement/approveRebate/Main'),
    //   meta: {
    //     title: '预付款账单',
    //     icon: 'schedule'
    //   }
    // }
  ]
}

export default annexRouter