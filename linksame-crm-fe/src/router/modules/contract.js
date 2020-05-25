/** 客户管理路由 */
import Layout from '@/views/layout/contractLayout'

const contractRouter = {
  path: '/contract',
  component: Layout,
  redirect: '/contract/contract',
  name: 'contract',
  hidden: true,
  meta: {
    // requiresAuth: false,
    title: '合同管理'
  },
  children: [
    // {
    //   path: 'workbench',
    //   component: () => import('@/views/contractManagement/workbench/Main'),
    //   meta: {
    //     requiresAuth: false,
    //     title: '仪表盘',
    //     icon: 'dashboard'
    //   }
    // },
    {
      path: 'contract',

      component: () => import('@/views/contractManagement/contract/index'),
      meta: {
        title: '合同',
        icon: 'schedule'
      }
    },
    {
      path: 'prepayment',
      hidden: true,
      component: () => import('@/views/contractManagement/prepaymentBill/index'),
      meta: {
        title: '预付款账单',
        icon: 'log'
      }
    },
    {
      path: 'payment',
      hidden: true,
      component: () => import('@/views/contractManagement/paymentBill/index'),
      meta: {
        title: '付款账单',
        icon: 'log'
      }
    },
    {
      path: 'rebate',
      hidden: true,
      component: () => import('@/views/contractManagement/rebateBill/index'),
      meta: {
        title: '回款账单',
        icon: 'log'
      }
    },

    {
      path: 'ledger',
      meta: {
        title: '账单',
        icon: 'examine'
      },
      children: [
        {
          path: 'prepayment',
          meta: {
            title: '预付款账单'
          }
        },
        {
          path: 'payment',
          meta: {
            title: '付款账单'
          }
        },
        {
          path: 'rebate',
          meta: {
            title: '回款账单'
          }
        }
      ]
    }

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

export default contractRouter
