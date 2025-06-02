<script setup lang="ts">
import PageSearch from '@/components/base-search-form/PageSearch.vue';
import searchConfig from './config/search-config';
import PageContents from '@/components/base-table/PageContents.vue';
import contentConfig from './config/content-config';
import PageModule from '@/components/base-module-form/PageModule.vue';
import { moduleConfig } from './config/module-config';
import { usePageContent } from '@/hooks/usePageConten';
import { usePageModule } from '@/hooks/usePageModule';

const { resetFormClick, submitFormClick, contentRef, moduleRef ,handleEditRow} = usePageContent()
const { submitFormModuleClick } = usePageModule()

</script>

<template>

<div class="app-role">
<PageSearch
  :searchConfig="searchConfig"
  @resetFormClick="resetFormClick"
  @submitFormClick="submitFormClick"
/>
<PageContents
@handle-edit="handleEditRow"
 :contentConfig="contentConfig"
 ref="contentRef"
  />
<PageModule
 :moduleConfig="moduleConfig"
 ref="moduleRef"
 @submit-module-form="submitFormModuleClick"
  >
</PageModule>
</div>

</template>