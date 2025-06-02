import { nextTick, ref } from 'vue';
import PageModule from '@/components/base-module-form/PageModule.vue';
import type PaginationContents from '@/components/base-pagination-table/PaginationContents.vue';
export const usePageContent = ()=>{
const contentRef = ref<InstanceType<typeof PaginationContents>>()
const moduleRef = ref<InstanceType<typeof PageModule> | null>(null)
    // 重置按钮点击事件
    const resetFormClick = () => {
      // console.log('Dept.vue 重置表单数据wwwwww');
      if (contentRef.value) {
        contentRef.value.handlePageList(true);
      } else {
        console.error('contentRef is null');
      }
    };
    const submitFormClick = (formData: any) => {
      // console.log('Dept.vue 提交表单数据', formData);
      if (contentRef.value) {
        contentRef.value.handleQueryForm(formData);
      } else {
        console.error('contentRef is null');
      }
    };
    // 处理编辑事件
const handleEditRow = async (row: any) => {
  await nextTick()
// 调用 PageModule 的方法
// console.log('handleEditRow', row.menuList);
moduleRef.value?.loadEditData(row)
}

    return {
      contentRef,
      moduleRef,
      resetFormClick,
      submitFormClick,
      handleEditRow
    }
}