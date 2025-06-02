import { ref, computed } from "vue";
import { defineStore } from 'pinia';

export const usePageConfigModule = defineStore('pageConfigModule', () => {

  // 定义一个响应式变量 userAddUserModuleStatus，初始值为 false
  const pageConfigModuleStatus = ref(false);

  // 定义一个计算属性 getUserAddUserModuleStatus，用于获取 userAddUserModuleStatus 的值
  const getpageConfigModuleStatus = computed(() => pageConfigModuleStatus.value);

  // 定义一个方法 toggleUserAddUserModuleStatus，用于切换 userAddUserModuleStatus 的值
  const toggleUserAddUserModuleStatus = () => {
    pageConfigModuleStatus.value = !pageConfigModuleStatus.value;
    // 这里可以取消注释 console.log 以调试状态切换
    // console.log(userAddUserModuleStatus.value);
  };

  // 定义一个方法 setUserAddUserModuleStatus，用于设置 userAddUserModuleStatus 的值
  const setpageConfigModuleStatus = (value: boolean) => {
    pageConfigModuleStatus.value = value;
  };

  // 返回模块中定义的状态和方法，以便在组件中使用
  return {
    pageConfigModuleStatus,
    getpageConfigModuleStatus,
    toggleUserAddUserModuleStatus,
    setpageConfigModuleStatus,

  };

}, {
  // 配置选项，启用持久化存储
  persist: true // 当设置为 true 时，Pinia 将使用插件（如 pinia-plugin-persistedstate）来持久化状态
});