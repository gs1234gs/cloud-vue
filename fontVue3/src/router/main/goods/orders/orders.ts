// 路由配置文件
export default
  // 字典管理路由
  {
    path: '/main/goods/orders',
    name: 'orders',
    component: () => import('@/layout/components/Main/goods/orders/Orders.vue')
  }
