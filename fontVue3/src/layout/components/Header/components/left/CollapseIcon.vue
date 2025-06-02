<script lang="ts" setup>
import { Fold, Expand } from '@element-plus/icons-vue'
import {useCollapseStore} from '@/stores/modules/collapse/collapse'
import { computed } from 'vue'

const collapseStore = useCollapseStore()
// ✅ 正确：依赖 getter（确保响应式）
const isCollapsed = computed(() => collapseStore.getIsCollapse);
// 切换状态 - 直接调用store的action
const handleCollapse = () => {
collapseStore.toggleCollapse()
}

</script>

<template>
 <!-- 折叠按钮容器 -->
  <div class="hamburger-container" >
 <!-- 折叠按钮 -->
    <div @click="handleCollapse">
<!-- 折叠按钮图标 -->
     <el-icon class="icon">
<!-- 响应式设计 -->
      <component :is="isCollapsed ? Expand : Fold" />
     </el-icon>
    </div>
  </div>
</template>

<style lang="scss" scoped>
  .hamburger-container {
    padding: 0px 15px;
    height: 100%;
    display: flex;
    align-items: center;
    &:hover {
      background: rgba(0, 0, 0, 0.025);
    }
    .icon {
      font-size: 24px;
      cursor: pointer;
    }
  }
</style>
