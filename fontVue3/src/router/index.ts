import LOGIN_TOKEN from '@/global/constants';
import { cacheLocal } from '@/utils/cache';
import { firstMenu } from '@/utils/map-menus';
import { createRouter, createWebHistory,createWebHashHistory} from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  // history: createWebHashHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      redirect: '/main',
    },
    {
      path: '/login',
      name: 'login',
      component:() => import('@/views/login/LoginIndex.vue')
    },
    {
      path: '/main',
      name: 'main',
      component:() => import('@/layout/MainIndex.vue'),
      children: []
    },
  // 添加404页面
  {
    path: '/:pathMatch(.*)*',
    name: 'not-found',
    component: () => import('@/views/error/404.vue')
  },
  // {
  //   path: '/home',
  //   name: 'home',
  //   component: () => import('@/views/home/index.vue')
  // }
  ],
})

// 路由导航守卫
router.beforeEach((to, from, next) => {
  const token = cacheLocal.getCache(LOGIN_TOKEN);

  if (to.path === '/main') {
    if (!token) {
      // 如果用户没有登录，则重定向到登录页面
      next('/login');
    } else {
      if (firstMenu.value && firstMenu.value.path) {
        // 如果用户已登录且 firstMenu 已设置，则导航到 firstMenu 的路径
        next(firstMenu.value.path);
      } else {
        // 如果 firstMenu 未设置，可以选择导航到一个默认页面
        console.warn('firstMenu is not set, navigating to default page');
        next('/login'); // 假设 '/default' 是你的默认页面
        // 或者你可以选择留在当前页面，但这在 beforeEach 中不太容易实现，因为 next() 必须被调用
      }
    }
  } else {
    // 对于其他路由，继续导航
    next();
  }
});

export default router
