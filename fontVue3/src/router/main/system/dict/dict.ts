// 路由配置文件
export default
  // 字典管理路由
  {
    path: '/main/system/dict',
    name: 'dict',
    component: () => import('@/layout/components/Main/system/dict/Dict.vue')
  }
