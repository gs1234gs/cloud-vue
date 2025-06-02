<template>
  <div class="clover-loader" v-show="isLoading">
    <div class="clover">
      <div class="leaf leaf-1"></div>
      <div class="leaf leaf-2"></div>
      <div class="leaf leaf-3"></div>
      <div class="leaf leaf-4"></div>
    </div>
    <div class="loading-text">系统加载中...</div>
  </div>
</template>

<script lang="ts" setup>
defineProps({
  isLoading: {
    type: Boolean,
    default: true
  }
});
</script>

<style lang="scss" scoped>
.clover-loader {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background: rgba(255, 255, 255, 0.9);
  z-index: 9999;
  transition: opacity 0.5s ease-out;

  .clover {
    position: relative;
    width: 80px;
    height: 80px;
    margin-bottom: 20px;

    .leaf {
      position: absolute;
      width: 40px;
      height: 40px;
      border-radius: 0 40px 0 40px;
      background: #67c23a;
      opacity: 0.8;
      animation: cloverSpin 2.4s infinite ease-in-out;

      &.leaf-1 {
        transform: rotate(0deg) translateY(-30px);
        animation-delay: 0s;
      }
      &.leaf-2 {
        transform: rotate(90deg) translateY(-30px);
        animation-delay: 0.6s;
      }
      &.leaf-3 {
        transform: rotate(180deg) translateY(-30px);
        animation-delay: 1.2s;
      }
      &.leaf-4 {
        transform: rotate(270deg) translateY(-30px);
        animation-delay: 1.8s;
      }
    }
  }

  .loading-text {
    color: #67c23a;
    font-size: 16px;
    letter-spacing: 2px;
    animation: pulse 1.5s infinite;
  }
}

@keyframes cloverSpin {
  0%, 100% { transform: rotate(0deg) translateY(-30px) scale(1); }
  50% { transform: rotate(180deg) translateY(-30px) scale(0.8); }
}

@keyframes pulse {
  0%, 100% { opacity: 1; }
  50% { opacity: 0.5; }
}
</style>