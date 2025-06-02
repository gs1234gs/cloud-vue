<script lang="ts" setup>
  import CollapseIcon from './components/left/CollapseIcon.vue'
  import Hamburger from './components/left/Hamburger.vue'
</script>


<template>
  <div class="m-tool-left">
    <CollapseIcon />
    <Hamburger />
  </div>

</template>



<style lang="scss" scoped>
  .m-tool-left {
    display: flex;
    align-items: center;
    height: 100%;
  }
</style>