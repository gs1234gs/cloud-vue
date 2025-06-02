// 路由配置文件
export default
  // 字典管理路由
  {
    path: '/main/goods/stock',
    name: 'stock',
    component: () => import('@/layout/components/Main/goods/stock/Stock.vue')
  }
