/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/layout'

const stockRouter = {
  path: '/stock',
  component: Layout,
  name: 'stock',
  meta: {
    title: '仓储管理',
    icon: 'member-point',
    roles: ['stock']
  },
  children: [
    {
      path: 'supplier',
      component: () => import('@/views/trade/supplier/index'),
      name: 'supplier',
      meta: { title: '供应商管理',  icon: 'list', roles: ['config:supplier'] }
    },
    {
      path: 'goodsStock',
      component: () => import('@/views/goods/stock/index'),
      name: 'goodsStock',
      meta: { title: '商城库存',  icon: 'list', roles: ['stock:shopStock'] }
    },
    {
      path: 'storage',
      component: () => import('@/views/trade/storage/index/index'),
      name: 'storage',
      meta: { title: '仓库管理',  icon: 'list', roles: ['stock:storage'] }
    },
    {
      path: 'storageStock',
      component: () => import('@/views/trade/storage/stock/index'),
      name: 'storageStock',
      meta: { title: '仓库库存',  icon: 'list', roles: ['stock:storageStock'] }
    },
    {
      path: 'inStock',
      component: () => import('@/views/trade/storage/apply/index'),
      name: 'inStock',
      meta: { title: '商品采购',  icon: 'list', roles: ['stock:buy'] }
    },
    {
      path: 'editStock',
      component: () => import('@/views/trade/storage/edit/index'),
      name: 'editStock',
      meta: { title: '修改库存',  icon: 'list', roles: ['stock:editStock'] }
    },
    {
      path: 'log',
      component: () => import('@/views/trade/storage/stockLog/index'),
      name: 'stockLog',
      meta: { title: '进出库记录', icon: 'list', noCache: true, roles: ['stock:log'] },
    },
    {
      path: 'merge',
      component: () => import('@/views/order/merge/merged'),
      name: 'merge',
      meta: { title: '配货分拣',  icon: 'list', roles: ['stock:merge'] }
    },
    {
      path: 'waitMerge',
      component: () => import('@/views/order/merge/unMerge'),
      name: 'waitMerge',
      meta: { title: '待配货订单', icon: 'list', noCache: true},
      hidden: true
    },
    {
      path: 'counting',
      component: () => import('@/views/trade/storage/counting/index'),
      name: 'counting',
      meta: { title: '盲盘',  icon: 'list', roles: ['stock:counting'] }
    },
  ]
}
export default stockRouter
