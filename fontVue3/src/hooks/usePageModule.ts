import { usePageConfigModule } from "@/components/base-module-form/config-module/config-module"
import { computed, defineAsyncComponent, type Component, type VNode } from "vue"
import useSystemStore from '@/stores/modules/system/system';
export const usePageModule = ()=>{
  const systemStore = useSystemStore()
  const pageConfigModule = usePageConfigModule()
// 使用计算属性实现双向绑定
const centerDialogVisible = computed({
  get: () => pageConfigModule.getpageConfigModuleStatus,
  set: (value) => pageConfigModule.setpageConfigModuleStatus(value)
})


// 提交表单，模态框编辑和新增表单zeng 的提交表单处理逻辑
const submitFormModuleClick = async ({ payload, isEditModes,pageName }:any)=>{
console.log(payload)
console.log("900990",isEditModes)
console.log("900990",pageName)
// 修改handleConfirm处理编辑和新增
  if (isEditModes) {
    await systemStore.putPageupdateAction(pageName,payload)
  }
  // 新增
  else {
    await systemStore.postPageAddAction(pageName,payload)
  }

  systemStore.getPageListDataAction(pageName)
}
const loadComponent = (loader: () => Promise<{ default: Component }> | VNode): any => {
  if (typeof loader === 'function') {
    // 确保 loader 返回的是一个 Promise
    return defineAsyncComponent(() =>
      Promise.resolve(loader()).then((mod) => {
        if (mod instanceof Promise) {
          // 如果 mod 是 Promise，等待其解析
          return mod.then((resolvedMod) => {
            if ('default' in resolvedMod && typeof resolvedMod.default === 'object') {
              return resolvedMod.default;
            }
            return resolvedMod; // 如果没有 default，直接返回
          });
        } else if ('default' in mod && typeof mod.default === 'object') {
          // 如果 mod 是对象且包含 default 属性
          return mod.default;
        } else if (isVNode(mod)) {
          // 如果是 VNode，直接返回
          return mod;
        }
        return mod; // 如果 mod 是其他类型，直接返回
      })
    );
  } else {
    // 如果 loader 不是函数，直接返回
    return loader;
  }
};
// 辅助方法：检查 slot 是否为合法的组件渲染对象
const isValidSlot = (slot: any): slot is { render: () => VNode } => {
  return typeof slot === 'object' && slot !== null && 'render' in slot && typeof slot.render === 'function';
};
// 辅助方法：检查是否为 VNode
const isVNode = (obj: any): obj is VNode => {
  return obj && typeof obj.type !== 'undefined';
};

// 新增函数：获取动态组件
const getDynamicComponent = (item: any) => {
  if (item.slot && isValidSlot(item.slot)) {
    return loadComponent(() => item.slot.render())
  }
  return null
}

return {
  centerDialogVisible,
  submitFormModuleClick,
  getDynamicComponent,
  isVNode,
  isValidSlot,
  loadComponent,
}
}

