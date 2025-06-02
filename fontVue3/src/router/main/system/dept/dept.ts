// 路由配置文件
export default
  // 部门管理
  {
    path: '/main/system/dept',
    name: 'dept',
    component: () => import('@/layout/components/Main/system/dept/Dept.vue')
  }
