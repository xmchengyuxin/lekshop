/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/layout'

const fundRouter = {
  path: '/fund',
  component: Layout,
  redirect: 'noredirect',
  name: 'fund',
  meta: {
    title: '财务管理',
    icon: 'money'
  },
  children: [
    {
      path: 'withdraw',
      component: () => import('@/views/fund/withdraw/index'),
      name: 'withdraw',
      meta: { title: '提现记录', icon:'list', role: ['admin'] }
    },
		/* {
		  path: 'recharge',
		  component: () => import('@/views/fund/recharge/index'),
		  name: 'recharge',
		  meta: { title: '充值记录', icon:'list', role: ['admin'] }
		}, */
    {
      path: 'account',
      component: () => import('@/views/fund/account/index'),
      name: 'account',
      meta: { title: '资金明细', icon:'list', role: ['admin'] }
    },
  ]
}
export default fundRouter
