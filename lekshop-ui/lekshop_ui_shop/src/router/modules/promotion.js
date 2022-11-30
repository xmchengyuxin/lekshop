/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/layout'

const promotionRouter = {
  path: '/promotion',
  component: Layout,
  redirect: 'noredirect',
  name: 'promotion',
  alwaysShow: true,
  meta: {
    title: '营销活动',
    icon: 'lock',
    roles: ['promotion']
  },
  children: [
    {
      path: 'coupon',
      component: () => import('@/views/promotion/coupon/index'),
      name: 'coupon',
      meta: { title: '优惠折扣',  icon:'list', roles: ['promotion:coupon'] }
    },
    {
      path: 'seckill',
      component: () => import('@/views/promotion/seckill/index'),
      name: 'seckill',
      meta: { title: '秒杀活动',  icon:'list', roles: ['promotion:seckill'] }
    },
    {
      path: 'group',
      component: () => import('@/views/promotion/group/index'),
      name: 'goodsGroup',
      meta: { title: '拼团活动',  icon:'list', roles: ['promotion:group'] }
    },
    {
      path: 'relation',
      component: () => import('@/views/promotion/goodsRelate/index'),
      name: 'goodsRelation',
      meta: { title: '关联商品',  icon:'list', roles: ['promotion:relation'] }
    },
  ]
}
export default promotionRouter
