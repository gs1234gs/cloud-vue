<script setup lang="ts">

import PageSearch from '@/components/base-search-form/PageSearch.vue'
import searchConfig from './config/search-config';
import PageContents from '@/components/base-table/PageContents.vue';
import { computed } from 'vue';
import contentConfig from './config/content-config';
import PageModule from '@/components/base-module-form/PageModule.vue';
import moduleCinfig from './config/module-config'
import { useMainStore } from '@/stores/modules/system/main/main';
import { usePageContent } from '@/hooks/usePageConten';
import { usePageModule } from '@/hooks/usePageModule';

const { resetFormClick, submitFormClick, contentRef, moduleRef,handleEditRow } = usePageContent()
const {submitFormModuleClick } = usePageModule()
const moduleConfigRef = computed(() =>{
  const mainStore = useMainStore()
  const depts  = mainStore.entireDepts.map((item: any) => {
   return {label : item.deptName, value: item.deptId}
  })
 moduleCinfig.formItem.forEach((item: any) => {
  if (item.prop === 'parentId') {
    item.options.push(...depts)
  }
  })
  return moduleCinfig
})

</script>

<template>

<div class="dept-container">
<page-search
   @resetFormClick="resetFormClick"
   @submitFormClick="submitFormClick"
    :searchConfig="searchConfig" />

<page-contents
  @handle-edit="handleEditRow"
  ref="contentRef"
  :contentConfig="contentConfig"
 />
 <PageModule
    :moduleConfig="moduleConfigRef"
    ref="moduleRef"
     @submit-module-form="submitFormModuleClick"
    />
</div>

</template>