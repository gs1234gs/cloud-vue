<script setup lang="ts">
import PageContents from '@/components/base-table/PageContents.vue';
import {contentConfig} from './config/content-config.ts'
import moduleConfig from './config/module-config.ts'
import searchConfig from './config/search-config.ts';
import PageSearch from '@/components/base-search-form/PageSearch.vue';
import PageModule from '@/components/base-module-form/PageModule.vue';
import { usePageContent } from '@/hooks/usePageConten.ts';
import { usePageModule } from '@/hooks/usePageModule.ts';
const { resetFormClick, submitFormClick, contentRef, moduleRef,handleEditRow } = usePageContent()
const {submitFormModuleClick } = usePageModule()
</script>

<template>

<div class="app-role">

 <page-search
  @resetFormClick="resetFormClick"
   @submitFormClick="submitFormClick"
 :searchConfig="searchConfig" />
<page-contents
@handle-edit="handleEditRow"
:contentConfig="contentConfig"
ref="contentRef"/>
<page-module :moduleConfig="moduleConfig"
ref="moduleRef"
@submit-module-form="submitFormModuleClick"/>
</div>

</template>
