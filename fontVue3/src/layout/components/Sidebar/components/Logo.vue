<script lang="ts" setup name="Logo">
  // 引入 Vue 的 `computed` 函数，用于创建计算属性
  import { computed } from 'vue'
  // 引入自定义状态管理模块 `collapse` 的 store
  import { useCollapseStore } from '@/stores/modules/collapse/collapse'

  // 获取 `collapse` 状态管理实例
  const collapseStore = useCollapseStore()
  // ✅ 正确：通过计算属性依赖 store 的 getter（确保数据响应式）
  const isCollapsed = computed(() => collapseStore.getIsCollapse);
</script>

<template>
  <!-- 侧边栏 Logo 容器 -->
  <div class="sidebar-logo-container">
    <!-- 使用 Vue 的 transition 组件，为侧边栏 Logo 添加淡入淡出的过渡效果 -->
    <transition name="sidebarLogoFadeCl">
      <!-- 如果 `isCollapsed` 为 true，显示简化的 Logo（仅图片） -->
      <router-link v-if="isCollapsed" key="collapse" class="sidebar-logo-link" to="/">
        <!-- 侧边栏 Logo 图片 -->
        <img src="@/assets/images/login/feather.png" class="sidebar-logo" />
      </router-link>
      <!-- 如果 `isCollapsed` 为 false，显示完整的 Logo（图片 + 标题） -->
      <router-link v-else key="expand" class="sidebar-logo-link" to="/">
        <!-- 侧边栏 Logo 图片 -->
        <img src="@/assets/images/login/feather.png" class="sidebar-logo" />
        <!-- 侧边栏标题 -->
        <h1 class="sidebar-title" v-show="!isCollapsed">天畅风华科技</h1>
      </router-link>
    </transition>
  </div>
</template>

<style lang="scss" scoped>
 @use "./logo.scss"
</style>