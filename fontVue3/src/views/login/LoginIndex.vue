<script setup lang="ts" name="LoginIndex">
  import { defineAsyncComponent } from 'vue'
  // 异步加载组件
  const LoginForm = defineAsyncComponent(() => import('./components/LoginRegisterForm.vue'))
</script>

<template>

  <div class="login-container">

    <div class="login-box">
              <!-- 标题 -->
              <div class="login-title">


<img class="icon" src="@/assets/logo.svg" alt="logo" />
<h2 class="title">vue3后台模板</h2>
</div>
      <!-- <BaseSwitchDark class="login-dark" /> -->
      <div class="login-left">
        <!-- <img src="@/assets/images/login/1.png" /> -->
      </div>
      <div class="login-form">

        <LoginForm />
      </div>
    </div>
    <video class="video-bg" autoplay muted loop src="@/assets/images/login/water.mp4"></video>
  </div>
</template>

<style lang="scss" scoped>
  @use './login-index.scss';
  .video-bg {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    object-fit: cover;
    z-index: 0;
  }
</style>
