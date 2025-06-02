// 路由配置文件
export default
  // 商品种类管理路由
  {
    path: '/main/goods/category',
    name: 'category',
    component: () => import('@/layout/components/Main/goods/category/Category.vue')
  }
