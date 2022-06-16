/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/layout'

const fundRouter = {
  path: '/fund',
  component: Layout,
  redirect: 'noredirect',
  name: 'fund',
  meta: {
    title: '财务',
    icon: 'money'
  },
  children: [
    {
      path: 'withdraw',
      component: () => import('@/views/fund/withdraw/index'),
      name: 'withdraw',
      meta: { title: '提现记录',  role: ['admin'] }
    },
		{
		  path: 'recharge',
		  component: () => import('@/views/fund/recharge/index'),
		  name: 'recharge',
		  meta: { title: '充值记录',  role: ['admin'] }
		},
    {
      path: 'account',
      component: () => import('@/views/fund/account/index'),
      name: 'account',
      meta: { title: '资金明细',  role: ['admin'] }
    },
    {
      path: 'point',
      component: () => import('@/views/fund/point/index'),
      name: 'point',
      meta: { title: '积分明细',  role: ['admin'] }
    },
    /* {
      path: 'publishPoint',
      component: () => import('@/views/fund/publishPoint/index'),
      name: 'publishPoint',
      meta: { title: '发布点明细',  role: ['admin'] }
    }, */
    {
      path: 'spmission',
      component: () => import('@/views/fund/spmission/index'),
      name: 'spmission',
      meta: { title: '推广佣金',  role: ['admin'] }
    },
  ]
}
export default fundRouter
