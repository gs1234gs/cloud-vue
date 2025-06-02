<template>
  <div class="app-container">
    <div class="home-container">
      <h1>敬请期待——首页</h1>
    </div>
  </div>
</template>
<script setup lang="ts"></script>

<style scoped lang="scss">
  .flex {
    display: flex;
    display: -webkit-flex;
  }
  .home-container {
    @extend.flex;
    width: 100%;
    height: 100%;
    box-sizing: border-box;
  }
</style>
