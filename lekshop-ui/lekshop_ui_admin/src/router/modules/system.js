/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/layout'

//系统管理路由配置
const systemRouter = {
  path: '/system',
  component: Layout,
  redirect: '/system/admin',
  name: 'system',
  meta: {
    title: '系统',
    icon: 'system'
  },
  children: [
    {
      path: 'admin',
      component: () => import('@/views/system/admin/index'),
      name: 'adminTable',
      meta: { title: '管理员',  role: ['admin'] }
    },
    {
      path: 'adminType',
      component: () => import('@/views/system/adminType/index'),
      name: 'adminTypeTable',
      meta: { title: '角色管理',  role: ['admin'] }
    },
    {
      path: 'params',
      component: () => import('@/views/system/params/index'),
      name: 'params',
      meta: { title: '系统参数',  role: ['admin'] }
    },
    {
      path: 'modParams',
      component: () => import('@/views/system/params/modParams'),
      name: 'modParams',
      meta: { title: 'APP更新',  role: ['admin'] }
    },
		 {
		  path: 'menuList',
		  component: () => import('@/views/system/menu/index'),
		  name: 'menuList',
		  meta: { title: '后台菜单',  role: ['admin'] }
		},
    {
      path: 'shopMenuList',
      component: () => import('@/views/system/shopMenu/index'),
      name: 'shopMenuList',
      meta: { title: '商家菜单',  role: ['admin'] }
    },
    {
    	path: 'center',
    	component: () => import('@/views/system/center/index'),
    	name: 'adminCenter',
    	meta: { title: '个人中心', noCache: true},
    	hidden: true
    },
  ]
}
export default systemRouter
