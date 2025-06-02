<script setup lang="ts">
import {reactive, ref, watch } from 'vue'
import type { ElForm } from 'element-plus'
import  type {IModuleIProps}  from './config-module/type'
import { useFormRules } from '@/hooks/formRules'
import { usePageModule } from '@/hooks/usePageModule'
import CustomComponent from '@/layout/components/Main/system/role/custom/CustomComponent.vue'
import { el } from 'element-plus/es/locales.mjs'
const isEditMode = ref(false)
const props = defineProps<IModuleIProps>()
const initialFormState : any = {}
// 定义 emits，用于向父组件发送事件
const emit = defineEmits(['submitModuleForm'])
for(const item of props.moduleConfig.formItem){
  initialFormState[item.prop] = item.initValue?? ''
}
const { formRules } = useFormRules()
const formData  = reactive<any>(initialFormState)
// 添加表单引用
const toFormRef = ref<InstanceType<typeof ElForm>>()
const { centerDialogVisible,getDynamicComponent,isValidSlot } = usePageModule()

// 表单提交处理逻辑
const handleConfirm = async () => {
  const valid = await toFormRef.value?.validate().catch(() => false)
  if (!valid) return
  // console.log('提交表单数据：', formData)
  // debugger
  // 将表单数据和编辑模式状态传递给父组件
  // console.log('提交表单数据--------------------------', isEditMode.value)
  emit('submitModuleForm', { payload: { ...formData }, isEditModes: isEditMode.value ,pageName: props.moduleConfig.pageName })
// 延迟关闭对话框，确保提交完成后再重置
setTimeout(() => {
    centerDialogVisible.value = false;
  }, 0);
}
//loadEditData方法
const loadEditData = (currentData: any) => {
  // console.log('编辑用户：',currentData);
  resetForm()
  isEditMode.value = true
  // 合并数据，确保 customProp 不被覆盖为 undefined
  Object.assign(formData, currentData);
  // console.log('编辑：', isEditMode.value)
  //  // 填充表单数据
   for (const key in currentData) {
    if (formData.hasOwnProperty(key)) {
      formData[key] = currentData[key]
    }
  }
}
// resetForm方法
const resetForm = () => {
  // 1. 重置Element表单验证状态
  toFormRef.value?.resetFields()
  // 2. 重置编辑模式相关状态
  isEditMode.value = false
  // 3. 强制清空表单引用（可选）
  if (toFormRef.value) {
    toFormRef.value.clearValidate()
  }
}

// 监听对话框关闭事件
watch(centerDialogVisible, (visible) => {

  if (!visible) {
    // 添加延迟确保动画完成
    setTimeout(resetForm, 300)
  }
})
const receivedValue = (value: any) => {
  // console.log('接收到的值：', value);
  formData.menuListId = value;
  // console.log('formData.menuListId:', formData.menuListId); // 修正日志输出
}


defineExpose({
  loadEditData
})
</script>

<template>
  <el-dialog v-model="centerDialogVisible"
   :title="isEditMode ? props.moduleConfig.header.editTitle : props.moduleConfig.header.newTitle"
   width="500" center>
    <div class="dialog-content">
      <el-form
      ref="toFormRef"
        :model="formData"
        label-width="100px"
        label-position="left"
        :rules="formRules"
      >
      <template v-for="item in props.moduleConfig.formItem" :key="item.prop">
      <el-form-item :label="item.label" :prop="item.prop">
         <template v-if="item.type === 'input'">
          <el-input v-model="formData[item.prop]" :placeholder="item.placeholder" />
        </template>
        <template v-else-if="item.type === 'date-picker'">
          <el-date-picker  v-model="formData[item.prop]"

            placeholder="选择创建时间范围"
            type="daterange"
            range-separator="-"
            start-placeholder="开始时间"
            end-placeholder="结束时间"
            />
        </template>
        <template v-else-if="item.type === 'select'">
          <el-select v-model="formData[item.prop]" :placeholder="item.placeholder" class="form-input">
            <el-option
              v-for="(option,index) in item.options"
              :key="index"
              :label="option.label"
              :value="option.value"
            />
          </el-select>
        </template>
        <template v-else-if="item.slot && isValidSlot(item.slot)">
          <el-form-item>
            <custom-component
              :is="getDynamicComponent(item)"
              @update:model-value="receivedValue"
            />
          </el-form-item>
        </template>

        </el-form-item>
      </template>
      </el-form>
    </div>

    <template #footer>
      <div class="dialog-footer">
        <el-button @click="centerDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleConfirm">确认</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<style scoped>
.dialog-content {
  padding: 20px 30px;
}

:deep(.el-form-item__label) {
  font-weight: 500;
  color: #606266;
}

.form-input {
  width: 100%;
}

.dialog-footer {
  display: flex;
  justify-content: flex-end;
  gap: 12px;
  padding: 16px 30px 20px;
}

.dialog-footer .el-button {
  min-width: 80px;
}
</style>