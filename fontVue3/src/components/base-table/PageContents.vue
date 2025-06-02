<script setup lang="ts">
import useSystemStore from '@/stores/modules/system/system';
import { Delete,Edit } from '@element-plus/icons-vue'
import { formatDate } from '@/utils/format'
import { computed,   onMounted, ref } from 'vue';
import { usePageConfigModule } from '../base-module-form/config-module/config-module';
import { usePermission } from '@/hooks/use-permission';
interface IProps{
  contentConfig:{
    pageName:string
    header?: {
   title?: string
   btnTitle?:string
  }
  propsList:any[]
  childrenTree ?:any
  }
}
const emit = defineEmits(['handle-edit',"handle-delete"])
const pageConfigModule= usePageConfigModule()
// debugger
const props = defineProps<IProps>()

const systemStores = useSystemStore()
//功能补全，暂时注释，临时定义为true
// const isCreate = usePermission(`${props.contentConfig.pageName}:increase`)
// const isDelete =usePermission(`${props.contentConfig.pageName}:delete`)
// const isModify = usePermission(`${props.contentConfig.pageName}:edit`)
// const isQuery = usePermission(`${props.contentConfig.pageName}:query`)
let isCreate = true
let isDelete = true
let isModify = true
let isQuery = true
// console.log('isCreate',isCreate);
// console.log('isModify',isModify);
// console.log('isQuery',isQuery);
// console.log('isdelete',isDelete);
// 列表数据
const pageList = computed(() => systemStores.pageLists);

// 状态映射
const statusFormatter = (status: number) => status === 0 ? '启用' : '停用'

// 操作处理
const handleEdit = (row : any) => {
  if(!isModify){
    return
  }
  // console.log('编辑用户：',row);
    // 确保对话框打开
    emit('handle-edit', row)
  pageConfigModule.setpageConfigModuleStatus(true)

};

const handleDelete = (row: any) => {
  if(!isModify){
    return
  }
  // 动态生成 id 字段名（pageName 首字母小写 + "id"）
   // 方案1：自动适配小驼峰命名（推荐）
   const idField = `${props.contentConfig.pageName}Id` // 直接拼接 pageName + "Id

  // 获取对应 ID 值
  const targetId = row[idField]
  // console.log('targetId', targetId);

  if (!targetId) {
    console.error('未找到对应 ID 字段:', idField)
    return
  }

  systemStores.deletePageAction(props.contentConfig.pageName,targetId);
};

const addNewPage = () => {
  pageConfigModule.setpageConfigModuleStatus(true) // 直接修改存储状态
  // console.log('Dialog status:', pageConfigModule.getpageConfigModuleStatus);
};

const handlePageList : any = () => {
  if(!isModify){
    return
  }
  // console.log('重置表单数据44444')
  systemStores.getPageListDataAction(props.contentConfig.pageName);
}

//查询表单数据
const handleQueryForm = (formDataSearch: any) => {
  if(!isQuery){
    return
  }
  systemStores.postPageListAction(props.contentConfig.pageName,formDataSearch)
  // console.log('查询表单数据',props.contentConfig.pageName,formDataSearch)
}


// 初始化加载
onMounted(() => {
    handlePageList()
});

defineExpose({
  handlePageList,
  handleQueryForm
})

</script>

<template>
  <div class="content-box">
    <div class="header">
      <h3 class="title">
        <el-icon><OfficeBuilding /></el-icon>
        {{ contentConfig?.header?.title ?? '列表'}}
      </h3>
      <el-button
      v-if="isCreate"
      type="primary"
      icon="Plus"
      @click="addNewPage">{{ props.contentConfig?.header?.btnTitle ?? '新增' }}
    </el-button>
    </div>

    <div class="table-container">
      <el-table
        :data="pageList"
        stripe
        style="width: 100%"
        v-bind="props.contentConfig?.childrenTree"
      >
      <template v-for="item in contentConfig?.propsList" :key="item.prop">

        <template v-if="item.type === 'timer'">
          <el-table-column align="center" v-bind="item">
          <template #default="{ row }">
            {{ row.createTime ? formatDate(row.createTime,'YYYY-MM-DD-HH:mm:ss') : '-' }}
          </template>
        </el-table-column>
        </template>
        <template v-else-if="item.type === 'handler'">
          <el-table-column v-bind="item">
          <template #default="{ row }">
            <el-button
            v-if="isModify"
              type="primary"
              link
              :icon="Edit"
              @click.stop="handleEdit(row)"
            >
              编辑
            </el-button>
            <el-button
              v-if="isDelete"
              type="danger"
              link
              :icon="Delete"
              @click.stop="handleDelete(row)"
            >
              删除
            </el-button>
          </template>
        </el-table-column>
        </template>
        <template v-else>
          <el-table-column align="center" v-bind="item">
        </el-table-column>
        </template>
      </template>

      </el-table>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.content-box {
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 12px 0 rgba(0,0,0,0.1);
  padding: 16px;
}

.header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 20px;
  padding: 0 8px;

  .title {
    display: flex;
    align-items: center;
    gap: 8px;
    font-size: 16px;
    color: #303133;

    .el-icon {
      font-size: 20px;
      color: var(--el-color-primary);
    }
  }
}

.table-container {
  :deep(.el-table__row) {
    .dept-tree-node {
      display: flex;
      align-items: center;
      gap: 8px;
      transition: padding 0.3s ease;

      .root-dept-icon {
        display: inline-flex;
        color: var(--el-color-primary);
      }

      .root-tag {
        color: var(--el-color-primary);
        font-size: 12px;
      }
    }

    .contact-info {
      line-height: 1.5;
      > div {
        display: flex;
        align-items: center;
        gap: 4px;
        .el-icon {
          font-size: 14px;
        }
      }
    }
  }

  :deep(.el-table__expand-icon) {
    font-size: 16px;
  }
}
</style>