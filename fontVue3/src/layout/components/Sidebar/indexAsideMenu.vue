<script lang="ts" setup>
import { computed, ref } from 'vue';
import Logo from './components/Logo.vue';
import { cacheLocal } from '@/utils/cache';
import { USER_MENUS } from '@/global/constants';
import { useCollapseStore } from '@/stores/modules/collapse/collapse'
import { useRoute, useRouter } from 'vue-router'
import { mapMenusPaths } from '@/utils/map-menus';
//动态获取菜单
const userMenus = cacheLocal.getCache(USER_MENUS);
const router = useRouter()

// const systemStore = useSystemStore()
// 点击菜单项
const handleItemClick = (item : any) => {
  // console.log(item.path)
  //添加点击事件触发
  // systemStore.getUsersListAction(1,10)
  const path = item.path;
  router.push(path)
}

const collapseStore = useCollapseStore()
// ✅ 正确：依赖 getter（确保响应式）
const isCollapsed = computed(() => collapseStore.getIsCollapse);
// console.log(isCollapsed)

//el-memu默认选中菜单
const route = useRoute()

const defaultActive =  computed(()=>{
  const pathsMenu = mapMenusPaths(route.path,userMenus)
  return pathsMenu.menuId + ''
})

</script>

<template>
  <!-- 侧边栏容器 -->
  <div class="sidebar-container">
    <!-- logo -->
    <div>
      <Logo :isCollapse="isCollapsed" />
    </div>

    <!-- 菜单 --><!-- 菜单背景色 --><!-- 菜单文字颜色 --><!-- 激活项文字颜色 -->
    <div class="app-menu">
    <!-- 修改后的菜单结构 -->
  <el-menu
    :default-active="defaultActive"
    class="el-menu-vertical-demo"
    :collapse="isCollapsed"
    background-color="#001529"
    text-color="#b7bdc3"
    active-text-color="#fff"
  >
    <!-- 首页菜单项 -->
    <el-menu-item index="1" @click="$router.push('/')">
      <el-icon><House /></el-icon>
      <template #title>首页</template>
    </el-menu-item>

    <!-- 遍历项菜单项 -->
    <template v-for="item in userMenus" :key="item.menuId">
      <el-sub-menu :index="item.menuId + ''">
        <template #title>
        <!-- 图标 -->
          <el-icon><component :is="item.icon.split('-icon-')[1]" /></el-icon>
          <span>{{ item.menuName }}</span>
        </template>
        <template v-for="subItem in item.children" :key="subItem.menuId">
          <el-menu-item :index = "subItem.menuId + ''" @click="handleItemClick(subItem)">
            <el-icon><component :is="subItem.icon.split('-icon-')[1]" /></el-icon>
            {{ subItem.menuName }}
          </el-menu-item>
        </template>
      </el-sub-menu>
    </template>

  </el-menu>
    </div>
  </div>
</template>

<style lang="scss" scoped>
@use "./indexAsideMenu/indexAsideMenu.scss"
</style>