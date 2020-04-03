/** 客户管理路由 */
import Layout from '@/views/layout/contractLayout'

const contractRouter = {
  path: '/contract',
  component: Layout,
  redirect: '/contract/workbench',
  name: 'contract',
  hidden: true,
  meta: {
    requiresAuth: false,
    title: '合同管理',
    index: 0,
    type: 'contract'
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
      path: 'contract',
      component: () => import('@/views/contractManagement/contract/Main'),
      meta: {
        requiresAuth: false,
        title: '合同合账',
        icon: 'contract',
        index: 1,
        type: 'contract',
        subType: 'contract'
      },
      children: [
        {
          path: 'contract', // 工作台
          component: () => import('@/views/contractManagement/contract/Main'),
          meta: {
            requiresAuth: false,
            title: '合同列表',
            icon: 'dashboard',
            index: 1,
            type: 'contract',
            subType: 'contract'
          }
        }
      ]
    }

    // {
    //   path: 'contract',
    //   component: () => import('@/views/contractManagement/contract/ContractIndex'),
    //   meta: {
    //     requiresAuth: true,
    //     title: '合同合账',
    //     icon: 'contract',
    //     subType: 'contract'
    //   }
    //   children: [
    //     {
    //       path: 'contract', // 工作台
    //       component: () => import('@/views/contractManagement/workbench/workbench'),
    //       meta: {
    //         requiresAuth: false,
    //         title: '合同管理',
    //         icon: 'dashboard',
    //         type: 'contract'
    //       }
    //     },
    //   //     {
    //   //       path: 'receivable', // 工作台
    //   //       component: () => import('@/views/contractManagement/workbench/workbench'),
    //   //       meta: {
    //   //         requiresAuth: false,
    //   //         title: '回款管理',
    //   //         icon: 'dashboard',
    //   //         type: 'contract'
    //   //       }
    //   //     }
    //   //   ]
    // }

    // {
    //   path: 'approval',
    //   component: () => import('@/views/contractManagement/contract/ContractIndex'),
    //   meta: {
    //     requiresAuth: true,
    //     title: '合同审批',
    //     icon: 'approval',
    //     type: 'contract'
    //   },
    //   children: [
    //     {
    //       path: 'pendingContract', // 工作台
    //       component: () => import('@/views/contractManagement/workbench/workbench'),
    //       meta: {
    //         requiresAuth: false,
    //         title: '待审批合同',
    //         icon: 'dashboard',
    //         type: 'contract'
    //       }
    //     },
    //     {
    //       path: 'pendingApproval', // 工作台
    //       component: () => import('@/views/contractManagement/workbench/workbench'),
    //       meta: {
    //         requiresAuth: false,
    //         title: '待审批回款',
    //         icon: 'dashboard',
    //         type: 'contract'
    //       }
    //     }
    //   ]
    // },

    // {
    //   path: 'template',
    //   component: () => import('@/views/contractManagement/contract/ContractIndex'),
    //   meta: {
    //     requiresAuth: true,
    //     title: '合同模板',
    //     icon: 'template',
    //     type: 'template'
    //   },
    //   children: []
    // }
  ]
}

export default contractRouter
