<script setup lang="ts">
import PageContents from '@/components/base-table/PageContents.vue';
import contentConfig from './config/content-config.ts';
import {moduleConfig} from './config/module-config.ts';
import searchConfig from './config/search-config.ts';
import PageSearch from '@/components/base-search-form/PageSearch.vue';
import PageModule from '@/components/base-module-form/PageModule.vue';


</script>

<template>

<div class="app-role">

 <page-search :searchConfig="searchConfig" />
<page-contents :contentConfig="contentConfig" />
<page-module :moduleConfig="moduleConfig" />
</div>

</template>
