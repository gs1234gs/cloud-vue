<script setup lang="ts">
// import { usePermission } from '@/hooks/use-permission'
import type { ElForm } from 'element-plus'
import { ref,reactive } from 'vue'

//自定义事件
const emit = defineEmits(['resetFormClick','submitFormClick'])
interface IProps {

  searchConfig: {
    isPage?:boolean
    pageName: string
    formItem: any[]
    labelWidth?: string // 允许 labelWidth 为可选
  }
}

const props = defineProps<IProps>()
const initFormData: any = {}
//功能不全，暂时注释，
// const isQuery = usePermission(`${props.searchConfig.pageName}:query`)
let isQuery = true
for (const item of props.searchConfig.formItem) {
  initFormData[item.prop] = item.initValue ?? ''
}

const searchForm = reactive(initFormData)
// 绑定 el-form 的引用
const formRef = ref<InstanceType<typeof ElForm>>()
// 定义重置表单的方法
const resetFormClick = () => {
  // 调用 resetFields 方法来重置表单
  formRef.value?.resetFields()
  // console.log('重置表单数据')
  //2. 触发重置事件，通知父组件重置数据
  emit('resetFormClick')
}
// 提交表单的方法（只触发事件，不处理逻辑）
const submitFormClick = () => {
  // console.log('0000000', isPage)
  if(props.searchConfig.isPage){
    const queryInfo ={
      currentPage:1,
      pageSize:10,
      sortField:'desc',
      sortOrder:'createTime',
      pageInfo:searchForm
    }
    // console.log('0000000', props.searchConfig.isPage)
    // 触发提交事件，将表单数据传递给父组件
  queryInfo.pageInfo.startTime = searchForm.createTime[0]
  queryInfo.pageInfo.endTime = searchForm.createTime[1]
    // console.log('0000000', queryInfo,searchForm.createTime)
    emit('submitFormClick', queryInfo)
    return
  }
  //  console.log('提交表单数据--------------------------', searchForm)

  searchForm.startTime = searchForm.createTime[0]
  searchForm.endTime = searchForm.createTime[1]
  emit('submitFormClick', searchForm)
}
</script>

<template>

<div class="search-container" v-if="isQuery">
<el-form ref="formRef" :model="searchForm" :label-width="searchConfig.labelWidth ?? '80px'" size="large" >
  <el-row :gutter="20">
    <template v-for="item in searchConfig.formItem" :key="item.prop">
      <el-col :span="8">
        <el-form-item :label="item.label" :prop="item.prop">
         <template v-if="item.type === 'input'">
          <el-input v-model="searchForm[item.prop]" :placeholder="item.placeholder" />
        </template>
        <template v-if="item.type === 'date-picker'">
          <el-date-picker  v-model="searchForm[item.prop]"

            placeholder="选择创建时间范围"
            type="daterange"
            range-separator="-"
            start-placeholder="开始时间"
            end-placeholder="结束时间"
            value-format="YYYY-MM-DDTHH:mm:ss"
            />
        </template>
        </el-form-item>
      </el-col>
    </template>
  </el-row>
  </el-form>
 <!-- 新增按钮行 -->
  <div class="action-btns">

 <el-row :gutter="20">
      <el-col :span="24">
          <el-button @click ='submitFormClick' type="primary" icon="Search">查询</el-button>
          <el-button @click="resetFormClick" icon="RefreshRight" >重置</el-button>
      </el-col>
    </el-row>
  </div>
</div>
</template>

<style lang="scss" scoped>
   .search-container {
    flex-shrink: 0;
    background: #ffffff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
    margin-bottom: 16px;
  }

  .action-btns {
    display: flex;
    justify-content: flex-end;
    gap: 12px;
    padding: 8px 16px;
    margin-top: 16px;
    border-top: 1px solid #ebeef5;

    .el-button {
      min-width: 100px;
    }
  }

  // 保持原有表单项样式
  .el-form-item {
    margin-bottom: 0;
    padding: 12px;
  }
</style>