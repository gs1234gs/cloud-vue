// 路由配置文件
export default
  // 岗位管理路由
  {
    path: '/main/system/post',
    name: 'post',
    component: () => import('@/layout/components/Main/system/post/Post.vue')
  }
