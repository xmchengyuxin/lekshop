/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/layout'

const fundRouter = {
  path: '/fund',
  component: Layout,
  redirect: 'noredirect',
  name: 'fund',
  meta: {
    title: '财务管理',
    icon: 'money',
    roles: ['finance']
  },
  children: [
    {
      path: 'bank',
      component: () => import('@/views/member/bank/index'),
      name: 'bank',
      meta: { title: '提现账户', icon:'list', roles: ['finance:account'] }
    },
    {
      path: 'withdraw',
      component: () => import('@/views/fund/withdraw/index'),
      name: 'withdraw',
      meta: { title: '商家提现', icon:'list', roles: ['finance:withdraw'] }
    },
		/* {
		  path: 'recharge',
		  component: () => import('@/views/fund/recharge/index'),
		  name: 'recharge',
		  meta: { title: '充值记录', icon:'list', role: ['admin'] }
		}, */
    {
      path: 'order',
      component: () => import('@/views/order/writeOff/index'),
      name: 'orderFinance',
      meta: { title: '订单核销',  icon:'list', roles: ['finance:writeOff'] }
    },
    {
      path: 'account',
      component: () => import('@/views/fund/account/index'),
      name: 'account',
      meta: { title: '商家对账单', icon:'list', roles: ['finance:accountLog'] }
    },
  ]
}
export default fundRouter
