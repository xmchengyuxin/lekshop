/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/layout'

const orgRouter = {
  path: '/org',
  component: Layout,
  name: 'org',
	alwaysShow: true,
  meta: {
    title: '组织管理',
    icon: 'shop-list',
    roles: ['org']
  },
  children: [
    {
      path: 'dept',
      component: () => import('@/views/org/dept/index'),
      name: 'deptList',
      meta: { title: '部门管理',  icon:'list', roles: ['org:dept'] }
    },
    {
      path: 'role',
      component: () => import('@/views/org/role/index'),
      name: 'roleList',
      meta: { title: '角色管理',  icon:'list', roles: ['org:role'] }
    },
    {
      path: 'account',
      component: () => import('@/views/org/account/index'),
      name: 'accountList',
      meta: { title: '子账号管理', icon:'list',  roles: ['org:account'] }
    },
    {
      path: 'accountDetail',
      component: () => import('@/views/org/account/detailInfo'),
      name: 'accountDetail',
      meta: { title: '子账号详情', activeMenu: '/org/account'},
      hidden: true
    },
    // {
    //   path: 'sales',
    //   component: () => import('@/views/member/sales/index'),
    //   name: 'sales',
    //   meta: { title: '业务员管理',  roles: ['org:sales'] }
    // },
  ]
}
export default orgRouter
