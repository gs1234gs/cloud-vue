// 路由配置文件
export default
  // 角色管理路由
  {
    path: '/main/system/role',
    name: 'role',
    component: () => import('@/layout/components/Main/system/role/Role.vue')
  }
