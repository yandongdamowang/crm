import Layout from '@/views/layout/projectLayout'
// import { children } from './project'

const workbenchRouter = {
  path: '/project',
  component: Layout,
  redirect: '/project/my-task',
  name: 'project',
  meta: {
    icon: 'workbench',
    title: '项目管理'
  },
  children: [
    {
      name: 'my-task',
      path: 'my-task',
      component: () => import('@/views/projectManagement/task/index'),
      meta: {
        title: '我的任务'
      }
    },
    {
      path: 'task-calendars',
      component: () => import('@/views/projectManagement/calendars/index'),
      meta: {
        title: '任务日历'
      }
    },

    {
      name: 'project',
      path: 'project',
      component: () => import('@/views/dashboard/project/index'),
      meta: {
        title: '全部项目'
      }
    },
    {
      path: 'participate',
      component: () => import('@/views/dashboard/participate/index'),
      meta: {
        title: '我参与的'
      }
    },

    {
      path: 'archive-project',
      component: () => import('@/views/projectManagement/archive/index'),
      meta: {
        icon: 'product',
        title: '归档项目'
      }
    },
    {
      path: 'statistics',
      component: () => import('@/views/projectManagement/statistics/index'),
      meta: {
        icon: 'statistics',
        title: '统计分析'
      }
    },
    {
      path: 'recycle',
      component: () => import('@/views/projectManagement/recycle/index'),
      meta: {
        icon: 'recycle',
        title: '回收站',
        fontSize: '18px'
      }
    },

    {
      path: 'tag/:id',
      component: () => import('@/views/projectManagement/tag/index'),
      meta: {
        title: 'tag/:id'
      }
    },
    {
      name: 'project-list',
      path: 'list/:id',
      component: () => import('@/views/projectManagement/project/index')
    }
  ]
}

export default workbenchRouter

export const childrenMenu = [
  {
    path: 'workbench',
    meta: {
      icon: 'workbench',
      title: '工作台'
    },
    children: [
      {
        name: 'my-task',
        path: 'my-task',
        component: () => import('@/views/projectManagement/task/index'),
        meta: {
          title: '我的任务'
        }
      },
      {
        path: 'task-calendars',
        component: () => import('@/views/projectManagement/calendars/index'),
        meta: {
          title: '任务日历'
        }
      }
    ]
  },
  {
    path: 'dashboard',
    meta: {
      icon: 'workbench',
      title: '大屏'
    },
    children: [
      {
        name: 'project',
        path: 'project',
        meta: {
          title: '全部项目'
        }
      },
      {
        path: 'participate',
        meta: {
          title: '我参与的'
        }
      }
    ]
  },

  {
    path: 'statistics',
    component: () => import('@/views/projectManagement/statistics/index'),
    meta: {
      icon: 'statistics',
      title: '统计分析'
    }
  },
  {
    path: 'archive-project',
    component: () => import('@/views/projectManagement/archive/index'),
    meta: {
      icon: 'product',
      title: '归档项目'
    }
  },
  {
    path: 'recycle',
    component: () => import('@/views/projectManagement/recycle/index'),
    meta: {
      icon: 'recycle',
      title: '回收站',
      fontSize: '18px'
    }
  },
  {
    path: 'project',
    meta: {
      icon: 'project',
      title: '项目'
    },
    children: []
  },
  {
    path: 'tag',
    meta: {
      icon: 'tag',
      title: '标签',
      fontSize: '18px'
    },
    children: []
  }
]
