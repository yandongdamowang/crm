/** 客户管理路由 */
import Layout from '@/views/layout/contractLayout'

const contractRouter = {
  path: '/contract',
  component: Layout,
  redirect: '/contract/workbench',
  name: 'contract',
  hidden: true,
  meta: {
    // requiresAuth: false,
    title: '合同管理'
  },
  children: [
    {
      path: 'workbench', // 工作台
      component: () => import('@/views/contractManagement/workbench/Main'),
      meta: {
        requiresAuth: false,
        title: '仪表盘',
        icon: 'dashboard'
      }
    },
    {
      path: 'contract', // 工作台
      //   hidden: true,
      component: () => import('@/views/contractManagement/contract/Main'),
      meta: {
        title: '合同',
        icon: 'schedule'
      }
    },
    {
      path: 'prepaymentBill',
      hidden: true,
      component: () => import('@/views/contractManagement/prepaymentBill/Main'),
      meta: {
        title: '预付款账单',
        icon: 'log'
      }
    },
    {
      path: 'paymentBill',
      hidden: true,
      component: () => import('@/views/contractManagement/paymentBill/Main'),
      meta: {
        title: '付款账单',
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
          path: 'prepaymentBill',
          meta: {
            title: '预付款账单'
          }
        },
        {
          path: 'paymentBill',
          meta: {
            title: '付款账单'
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
