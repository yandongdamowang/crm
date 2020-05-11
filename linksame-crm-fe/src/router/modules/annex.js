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
      component: () => import('@/views/annexManagement/netdisk/index'),
      meta: {
        title: '网盘',
        icon: 'product'
      }
    },

    {
      path: 'type',
      //   hidden: true,
      component: () => import('@/views/annexManagement/type/index'),
      meta: {
        title: '附件类型',
        icon: 'tag'
      }
    },

    {
      path: 'recycle',
      //   hidden: true,
      component: () => import('@/views/annexManagement/recycle/index'),
      meta: {
        title: '回收站',
        icon: 'recycle'
      }
    }
  ]
}

export default annexRouter
