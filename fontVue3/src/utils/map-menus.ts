import { ref } from 'vue';
import type { RouteRecordRaw } from 'vue-router';

//本地路由加载
const loadLocalRoutes = () => {
  // 函数逻辑
       //动态添加路由
       const localRoutes : RouteRecordRaw[] = []
       //1. 动态添加路由,需要读取ts文件
     const files : Record<string,any> = import.meta.glob(
       '@/router/main/**/*.ts',
       {
          eager: true
         }
       )
//  console.log('files', files)
 //1.2 遍历路由配置，动态添加路由
     for (const key in files){
       const module = files[key]
      //  console.log('module', module.default)
       localRoutes.push(module.default)
     }
     return localRoutes
}
// 记录首个菜单
export let firstMenu = ref<any | null>(null); // 使用 ref 并初始化为 null
// 声明函数：将用户菜单映射到本地路由配置
const mapMenusRouts = (userMenus: any[]) => {
  // 加载本地预定义的路由配置
  const localRoutes = loadLocalRoutes()

  // 初始化最终需要动态添加的路由数组
  const routes: RouteRecordRaw[] = []


  // 遍历用户菜单数据（假设用户菜单为二级嵌套结构）
  for (const menu of userMenus) {
    // 遍历每个主菜单的子菜单项
    for (const subMenu of menu.children) {

      // 核心匹配逻辑：在本地路由中查找与子菜单路径匹配的路由配置
      const route = localRoutes.find(
        (item) => item.path === subMenu.path // 精确匹配路径
      )

      // 如果找到匹配的路由配置，则加入待添加路由数组
      if (route) {
         // 动态生成完整的路由对象
         const fullPathRoute: RouteRecordRaw = {
          path: menu.path,
          redirect: subMenu.path, // 设置重定向路径
          children: [route], // 将匹配的子路由作为子节点
        };
        routes.push(fullPathRoute)
      }
      // 如果 firstMenu 尚未设置且找到匹配的路由，则记录第一个菜单
      if (!firstMenu.value && route) {
        firstMenu.value = subMenu; // 使用 .value 访问和修改 ref 的值
        // console.log('firstMenu', firstMenu.value);
      }
    }
  }

  // 返回需要动态添加的路由配置数组
  return routes
}

/**
 *跟据路径映射路由
 * @param path ,需要映射的路径
 * @param menus，需要映射的路由
 */
export const mapMenusPaths = (path : string,menus : any[]) => {
    for (const menu of menus) {
      for (const subMenu of menu.children) {
        if (subMenu.path === path) {
          return subMenu;
        }
      }
    }
};

interface IHamburger {
  menuName: string;
  path ?: string;
}

export const mapHamburger = (path : string ,userMenus: any[]) => {
  //定义面包屑
  const mapHamburgers : IHamburger []= []
  // 遍历用户菜单数据
  for (const menu of userMenus) {
    for (const subMenu of menu.children) {
      if (subMenu.path === path) {
        mapHamburgers.push({menuName:menu.menuName,path:menu.path})
        mapHamburgers.push({menuName:subMenu.menuName,path:subMenu.path})
      }
    }
  }
  return mapHamburgers

};

// 将菜单映射到权限列表
export const mapMenusToPermissions = (userMenus: any[]) => {
  const permissions: string[] = [];

  const _recurseGetPermission = (menus: any[]) => {
    for (const menu of menus) {
      if (menu.menuType === 'F') {
        permissions.push(menu.perms);
        // console.log('permissions', menu.perms);
      } else {
        _recurseGetPermission(menu.children ?? []);
     }
    }
  }
  _recurseGetPermission(userMenus);
  return permissions;
}


export default mapMenusRouts;
