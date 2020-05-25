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
    title: '网 盘'
  },
  children: [
    {
      path: 'netdisk', // 工作台
      //   hidden: true,
      component: () => import('@/views/annexManagement/netdisk/index'),
      meta: {
        title: '网 盘',
        icon: 'product'
      }
    },
    {
      path: 'mark', // 工作台
      //   hidden: true,
      component: () => import('@/views/annexManagement/mark/index'),
      meta: {
        title: '标 签',
        icon: 'tag'
      }
    },

    {
      path: 'type',
      //   hidden: true,
      component: () => import('@/views/annexManagement/type/index'),
      meta: {
        title: '附件类型',
        icon: 'leads'
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
