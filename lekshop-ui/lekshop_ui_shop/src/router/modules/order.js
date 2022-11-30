/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/layout'

const orderRouter = {
  path: '/order',
  component: Layout,
  name: 'order',
	alwaysShow: true,
  meta: {
    title: '交易管理',
    icon: 'order-refund',
    roles: ['order']
  },
  children: [
    {
      path: 'list',
      component: () => import('@/views/order/index/index'),
      name: 'orderList',
      meta: { title: '订单管理', noCache: true, icon:'list', roles: ['order:list'] }
    },
    {
      path: 'group',
      component: () => import('@/views/order/group/index'),
      name: 'groupList',
      meta: { title: '拼团订单', icon:'list', roles: ['order:group'] }
    },
    {
      path: 'comment',
      component: () => import('@/views/order/comment/index'),
      name: 'commentList',
      meta: { title: '评论管理', icon:'list', roles: ['order:comment'] }
    },
    {
      path: 'refund',
      component: () => import('@/views/order/refund/index'),
      name: 'refundList',
      meta: { title: '退款管理', noCache: true, icon:'list', roles: ['order:refund'] }
    },
    {
      path: 'order',
      component: () => import('@/views/order/writeOff/index'),
      name: 'orderFinance',
      meta: { title: '订单核销',  icon:'list', roles: ['order:writeOff'] }
    },
  ]
}
export default orderRouter
