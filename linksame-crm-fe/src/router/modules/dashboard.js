/** 客户管理路由 */
import Layout from '@/views/layout/dashboardLayout'

const annexRouter = {
  path: '/dashboard',
  component: Layout,
  redirect: '/dashboard/index',
  name: 'dashboard',
  hidden: true,
  meta: {
    // requiresAuth: false,
    title: '总 览'
  },
  children: [
    {
      path: 'index', // 工作台
      //   hidden: true,
      component: () => import('@/views/dashboard/index/index'),
      meta: {
        title: '总 览',
        icon: 'product'
      }
    }
  ]
}

export default annexRouter
