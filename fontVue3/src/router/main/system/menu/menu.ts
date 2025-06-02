// 路由配置文件
export default
  // 菜单管理路由
  {
    path: '/main/system/menu',
    name: 'menu',
    component: () => import('@/layout/components/Main/system/menu/Menu.vue')
  }
