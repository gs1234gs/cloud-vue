i<script setup lang="ts">
import PageModule from '@/components/base-module-form/PageModule.vue';
import PageSearch from '@/components/base-search-form/PageSearch.vue';
import searchConfig from './config/search-config';
import moduleConfig from './config/module-config';
import DeptContent from './components/DeptContent.vue';
import contentConfig from './config/content-config'
import PaginationContents from '@/components/base-pagination-table/PaginationContents.vue';
import { usePageContent } from '@/hooks/use-pagination'
import { usePageModule } from '@/hooks/usePageModule';
const { resetFormClick, submitFormClick, contentRef, moduleRef,handleEditRow } = usePageContent()
const {submitFormModuleClick } = usePageModule()




</script>

<template>  <!-- 左侧部门列表 -->
  <div class="main-user">

    <div class="dept-box">
      <DeptContent />
    </div>

<div class="right-box">

  <PageSearch
    :searchConfig="searchConfig"
    @resetFormClick="resetFormClick"
    @submitFormClick="submitFormClick"
  />

   <PaginationContents
    ref="contentRef"
    @handle-edit="handleEditRow"
    :content-config="contentConfig" />

    <PageModule
      :moduleConfig="moduleConfig"
      ref="moduleRef"
      @submit-module-form="submitFormModuleClick"
    />
    </div>
  </div>
</template>

<style lang="scss" scoped>
.main-user {
  display: flex; // 使用 Flexbox 布局
  background: #f5f7fa;
  overflow: hidden;
  height: 100%;

  // 部门列表侧边栏
  .dept-box {
    width: 188px;
    flex-shrink: 0; // 防止侧边栏收缩
    background: #ffffff;
    box-shadow: 2px 0 8px rgba(0, 0, 0, 0.05);
    padding: 16px;
    overflow-y: auto;
    z-index: 1;
    border-radius: 7px;
    margin-right: 16px; // 添加间距
  }

  // 右侧内容区域
  .right-box {
    flex: 1; // 占据剩余空间
    display: flex;
    flex-direction: column; // 如果需要垂直排列内容
    overflow-y: auto;
  }
  .content-box {
    flex: 1; // 占据剩余空间
    overflow-y: auto;
    height: 100%;
  }
}
</style>
