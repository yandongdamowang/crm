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
    title: '合同管理',
    index: 0,
    // type: 'contract',
  },
  children: [
    {
      path: 'workbench', // 工作台
      component: () => import('@/views/contractManagement/workbench/Main'),
      meta: {
        requiresAuth: false,
        title: '仪表盘',
        icon: 'dashboard',
      },
    },

    {
      path: 'test', // 工作台
      //   hidden: true,
      meta: {
        title: 'test',
        icon: 'dashboard',
        // type: 'contract',
      },
      children: [
        {
          //   name: '',
          path: '/contract/rebate',
          component: () => import('@/views/contractManagement/contract/Main'),
          meta: {
            title: 'test',
            icon: 'dashboard',
            // type: 'contract',
            // subType: 'rebate',
          },
        },
      ],
    },

    {
      path: 'contract', // 工作台
      component: () => import('@/views/contractManagement/contract/Main'),
      meta: {
        requiresAuth: false,
        title: '合同管理',
        icon: 'dashboard',
        // index: 1,
        // type: 'contract',
        // subType: 'contract',
      },
    },
    // {
    //   path: 'rebate', // 工作台
    //   component: () => import('@/views/contractManagement/contract/Main'),
    //   meta: {
    //     requiresAuth: false,
    //     title: '回款管理',
    //     icon: 'dashboard',
    //     // index: 1,
    //     // type: 'contract',
    //     // subType: 'rebate',
    //   },
    // },
  ],
}

export default contractRouter
