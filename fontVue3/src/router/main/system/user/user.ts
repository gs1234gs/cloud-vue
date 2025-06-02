// 路由配置文件
export default
  // 用户管理路由
  {
    path: '/main/system/user',
    name: 'user',
    component: () => import('@/layout/components/Main/system/user/User.vue')
  }
