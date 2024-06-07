/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/layout'

const configRouter = {
  path: '/config',
  component: Layout,
  redirect: 'noredirect',
  name: 'config',
  meta: {
    title: '参数',
    icon: 'order-setting'
  },
  children: [
    {
      path: 'member',
      component: () => import('@/views/config/memberGroup/index'),
      name: 'configMember',
      meta: { title: '会员组参数',  role: ['admin'] }
    },
    {
      path: 'mission',
      component: () => import('@/views/config/mission/index'),
      name: 'configMission',
      meta: { title: '佣金奖励设置',  role: ['admin'] }
    },
    {
    	path: 'mission/edit/:id(\\d+)',
    	component: () => import('@/views/config/mission/detail'),
    	name: 'editConfigMission',
    	meta: { title: '设置佣金组别比例', role: [], activeMenu: '/config/mission' },
    	hidden: true
    },
    {
      path: 'gzh',
      component: () => import('@/views/config/gzh/index'),
      name: 'configGzh',
      meta: { title: '微信短信通知',  role: ['admin'] }
    },
    {
      path: 'account',
      component: () => import('@/views/config/account/index'),
      name: 'configAccount',
      meta: { title: '系统收款账户',  role: ['admin'] }
    },
    {
      path: 'order',
      component: () => import('@/views/config/order/index'),
      name: 'configOrder',
      meta: { title: '订单配置',  role: ['admin'] }
    },
    {
      path: 'search',
      component: () => import('@/views/config/search/index'),
      name: 'configSearch',
      meta: { title: '搜索管理',  role: ['admin'] }
    },
  ]
}
export default configRouter
