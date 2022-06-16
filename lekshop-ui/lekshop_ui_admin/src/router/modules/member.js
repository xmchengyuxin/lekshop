/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/layout'

const memberRouter = {
  path: '/member',
  component: Layout,
  redirect: '/member/list',
  name: 'member',
	alwaysShow: true,
  meta: {
    title: '会员',
    icon: 'user'
  },
  children: [
    {
      path: 'list',
      component: () => import('@/views/member/index/index'),
      name: 'memberList',
      meta: { title: '会员列表',  role: ['admin'] }
    },
    {
      path: 'detail',
      component: () => import('@/views/member/index/detailInfo'),
      name: 'memberDetailInfo',
      meta: { title: '会员详情',  role: ['admin'] }
    },
    {
    	path: 'detail/:id(\\d+)',
    	component: () => import('@/views/member/index/detail'),
    	name: 'editMember',
    	meta: { title: '编辑会员详情', noCache: true, role: [] },
    	hidden: true
    },
    {
      path: 'group',
      component: () => import('@/views/member/group/index'),
      name: 'group',
      meta: { title: '会员组别',  role: ['admin'] }
    },
    {
      path: 'realname',
      component: () => import('@/views/member/realname/index'),
      name: 'realnameVerify',
      meta: { title: '实名认证',  role: ['admin'] }
    },
    {
      path: 'bank',
      component: () => import('@/views/member/bank/index'),
      name: 'bankVerify',
      meta: { title: '银行认证',  role: ['admin'] }
    },
	{
	  path: 'punishment',
	  component: () => import('@/views/member/punishment/index'),
	  name: 'memberPunishment',
	  meta: { title: '用户处罚',  role: ['admin'] }
	},
  {
    path: 'phonebook',
    component: () => import('@/views/phonebook/index/index'),
    name: 'phonebook',
    meta: { title: '手机通讯录', role: ['admin'] }
  },
  {
  	path: 'phonebook/viewLog/:id(\\d+)',
  	component: () => import('@/views/phonebook/log/index'),
  	name: 'viewPhonebookLog',
  	meta: { title: '查看通讯录', noCache: true, role: [] },
  	hidden: true
  },
  {
  	path: 'phonebook/viewMsg/:id(\\d+)',
  	component: () => import('@/views/phonebook/message/index'),
  	name: 'viewPhonebookMsg',
  	meta: { title: '查看短信', noCache: true, role: [] },
  	hidden: true
  },
    {
      path: 'log',
      component: () => import('@/views/member/log/index'),
      name: 'memberLog',
      meta: { title: '会员日志',  role: ['admin'] }
    },
  ]
}
export default memberRouter
