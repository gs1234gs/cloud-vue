<script setup lang="ts">
import indexAsideMenu from '../components/Sidebar/indexAsideMenu.vue';
import { computed } from 'vue';
import ToolRight from '../components/Header/ToolRight.vue';
import ToolLeft from '../components/Header/ToolLeft.vue';
import { useCollapseStore } from '@/stores/modules/collapse/collapse';

const collapseStore = useCollapseStore();
// ✅ 正确：依赖 getter（确保响应式）
const isCollapsed = computed(() => collapseStore.getIsCollapse);

// 动态计算侧边栏宽度
const asideWidth = computed(() => isCollapsed.value ? '64px' : '222px');
</script>

<template>
  <div class="app-main">
    <el-container>
      <!-- 左侧侧边栏组件 -->
      <el-aside :width="asideWidth">
        <index-aside-menu />
      </el-aside>
      <!-- 右侧主内容组件 -->
      <el-container>
        <el-header>
          <!-- 左侧工具栏组件 -->
          <div class="tool-left">
            <ToolLeft />
          </div>
          <!-- 右侧工具栏组件 -->
          <div class="tool-right">
            <ToolRight />
          </div>
        </el-header>
        <el-main>
          <!-- 主内容组件 -->
          <!-- <Main /> -->
            <router-view />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<style lang="scss" scoped>
@use "./layoutColumn/layoutColumn.scss"
</style>