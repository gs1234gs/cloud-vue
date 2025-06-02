<script setup lang="ts">
import { RouterView } from 'vue-router'
import zhCn from 'element-plus/es/locale/lang/zh-cn'

</script>

<template >
  <div class="app">
    <el-config-provider :locale="zhCn">
    <RouterView />
  </el-config-provider>

  </div>
</template>

<style lang="scss">
::-webkit-scrollbar {
  // width: 0 !important;
  height: 0 !important;

}
</style>
