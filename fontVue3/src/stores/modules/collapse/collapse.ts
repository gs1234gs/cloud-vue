// src/stores/collapse.ts
import { defineStore } from 'pinia'
import { SIDEBAR_COLLAPSE } from '@/global/constants'
import { ref, computed } from 'vue'

export const useCollapseStore = defineStore(SIDEBAR_COLLAPSE, () => {
  // 状态
  const isCollapse = ref(false)

  // getter (计算属性)
  const getIsCollapse = computed(() => isCollapse.value)

  // action (操作方法)
  const toggleCollapse = () => {
    isCollapse.value = !isCollapse.value
  }
  // 持久化
  const setCollapse = (value: boolean) => {
    isCollapse.value = value
  }

  return {
    isCollapse,
    getIsCollapse,
    toggleCollapse,
    setCollapse
  }
}, {
  persist: true
})