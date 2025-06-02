<template>
  <div class="m-screenful" @click="toggleFullScreen">
    <el-tooltip :content="isFullscreen ? '退出全屏' : '进入全屏'" placement="bottom">
      <el-icon>
        <component :is="isFullscreen ? Aim : FullScreen" />
      </el-icon>
    </el-tooltip>
  </div>
</template>

<script lang="ts" setup>
import { ref, onMounted, onUnmounted } from 'vue'
import { FullScreen, Aim } from '@element-plus/icons-vue'

const isFullscreen = ref(false)

const toggleFullScreen = () => {
  if (!document.fullscreenElement) {
    document.documentElement.requestFullscreen()
      .then(() => isFullscreen.value = true)
      .catch(err => console.error('全屏错误:', err))
  } else {
    document.exitFullscreen()
    isFullscreen.value = false
  }
}

const handleFullscreenChange = () => {
  isFullscreen.value = !!document.fullscreenElement
}

onMounted(() => {
  document.addEventListener('fullscreenchange', handleFullscreenChange)
})

onUnmounted(() => {
  document.removeEventListener('fullscreenchange', handleFullscreenChange)
})
</script>

<style lang="scss" scoped>
.m-screenful {
  display: flex;
  align-items: center;
  padding-right: 0;
  justify-content: center;
  cursor: pointer;
  transition: all 0.3s;

  .el-icon {
    padding: 5px;
    transition: all 0.2s;

    &:hover {
      color: var(--el-color-primary);
      transform: scale(1.1);
    }

    &:active {
      transform: scale(0.95);
    }
  }
}
</style>