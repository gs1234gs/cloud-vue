
<script setup lang="ts">
import { useMainStore } from '@/stores/modules/system/main/main';
import { computed, ref } from 'vue';
// 引用 el-tree 的实例
const treeRef = ref();
const otherInfo = ref({})
const mainStore = useMainStore()
const emit = defineEmits(['update:modelValue'])
const defaultProps = {
  children: 'children',
  label: 'menuName',
}
const data : any = computed(() => {
  return mainStore.entireMenus
})

// 处理节点选中变化
const handleCheckChange = () => {
  if (!treeRef.value) return;

  // 获取选中的节点键值
  const checkedKeys = treeRef.value.getCheckedKeys();
  const halfCheckedKeys = treeRef.value.getHalfCheckedKeys();

  // 合并选中和半选中的键值
  const menuListId = [...checkedKeys, ...halfCheckedKeys];
  otherInfo.value = menuListId
  // console.log('选中的菜单键值:', otherInfo);
  emit('update:modelValue', otherInfo.value)
};

</script>

<template>
  <div>
    <el-tree
    ref="treeRef"
    style="max-width: 600px"
    :data="data"
    show-checkbox
    node-key="menuId"
    :props="defaultProps"
    @check="handleCheckChange"
  />
  </div>
</template>
