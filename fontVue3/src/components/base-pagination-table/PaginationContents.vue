<script setup lang="ts">
import useSystemStore from '@/stores/modules/system/system';
import { Delete, Edit } from '@element-plus/icons-vue'
import { formatDate } from '@/utils/format'
import { computed, onMounted, ref } from 'vue';
import { usePageConfigModule } from '@/components/base-module-form/config-module/config-module'
import type { PaginationQueryUser } from '@/api/system/system-type/system-pagenation-user';

interface IProps {
  contentConfig: {
    pageName: string
    header?: {
      title?: string
      btnTitle?: string
    }
    propsList: any[]
    childrenTree?: any
  }
}

const emit = defineEmits(['handle-edit', 'handle-delete'])
const pageConfigModule = usePageConfigModule()
const props = defineProps<IProps>()

const systemStores = useSystemStore()

// 权限控制（根据实际情况启用）
// const isCreate = usePermission(`${props.contentConfig.pageName}:create`)
// const isDelete = usePermission(`${props.contentConfig.pageName}:delete`)
// const isModify = usePermission(`${props.contentConfig.pageName}:modify`)
// const isQuery = usePermission(`${props.contentConfig.pageName}:query`)
const isCreate = true
const isDelete = true
const isModify = true
const isQuery = true

// 分页相关
const currentPage = ref(1)
const pageSize = ref(10)
const total = computed(() => systemStores.paginationLists.pageTotal)

// 列表数据
const pageList = computed(() => systemStores.paginationLists.pageRows)
const {pageTotal,pageRows} = systemStores.paginationLists
// console.log('pageTotal',pageTotal);
// console.log('pageRows',pageRows);
// 状态映射
const statusFormatter = (status: number) => status === 0 ? '启用' : '停用'

// 操作处理
const handleEdit = (row: any) => {
  if (!isModify) return
  emit('handle-edit', row)
  pageConfigModule.setpageConfigModuleStatus(true)
}

const handleDelete = (row: any) => {
  if (!isDelete) return
  const idField = `${props.contentConfig.pageName}Id`
  const targetId = row[idField]
  if (!targetId) {
    console.error('未找到对应 ID 字段:', idField)
    return
  }
  systemStores.deletePageAction(props.contentConfig.pageName, targetId)
    .then(() => handlePageList(true)) // 删除后刷新列表
}

// 新增操作
const addNewPage = () => {
  pageConfigModule.setpageConfigModuleStatus(true)
}

// 获取分页数据
const handlePageList = (isReset: boolean) => {
  if (!isQuery) return;
    if (isReset) {
    currentPage.value = 1
    pageSize.value = 10
    }
  // 补充 PaginationQueryUser 所需的字段
  const queryParams: PaginationQueryUser = {
    currentPage: currentPage.value  || 1,
    pageSize: pageSize.value || 10,
    sortField: 'createTime', // 默认排序字段，可根据需求调整
    sortOrder: 'desc',   // 默认排序顺序，可根据需求调整
    pageInfo: {},
  };


  systemStores.postPaginateListAction(props.contentConfig.pageName, queryParams);
};

// 处理查询表单
const handleQueryForm = (formDataSearch: any) => {
  if (!isQuery) return
  const queryParams = {
    page: currentPage.value,
    pageSize: pageSize.value,
    sortField: 'createTime',
    sortOrder: 'desc',
    ...formDataSearch,
  }
  systemStores.postPaginateListAction(props.contentConfig.pageName, queryParams)
}

// 处理分页变化
const handleCurrentChange = (newPage: number) => {
  currentPage.value = newPage
  handlePageList(false)
}

const handleSizeChange = (newSize: number) => {
  pageSize.value = newSize
  handlePageList(false)
}

// 初始化加载
onMounted(() => {
  handlePageList(false)
})

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
        {{ contentConfig?.header?.title ?? '列表' }}
      </h3>
      <el-button
        v-if="isCreate"
        type="primary"
        icon="Plus"
        @click="addNewPage"
      >
        {{ props.contentConfig?.header?.btnTitle ?? '新增' }}
      </el-button>
    </div>

    <div class="table-container">
      <el-table
        :data="pageList"
        stripe
        style="width: 100%"
        v-bind="contentConfig?.childrenTree"
      >
        <template v-for="item in contentConfig?.propsList" :key="item.prop">
          <template v-if="item.type === 'timer'">
            <el-table-column align="center" v-bind="item">
              <template #default="{ row }">
                {{ row.createTime ? formatDate(row.createTime, 'YYYY-MM-DD HH:mm:ss') : '-' }}
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
            <el-table-column align="center" v-bind="item" />
          </template>
        </template>
      </el-table>

      <!-- 分页组件 -->
      <div class="pagination-container">
        <el-pagination
          v-model:current-page="currentPage"
          v-model:page-size="pageSize"
          :page-sizes="[10, 20, 30, 50]"
          layout="total, sizes, prev, pager, next, jumper"
          :total="Number(total)"
          @current-change="handleCurrentChange"
          @size-change="handleSizeChange"
        />
      </div>
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

.pagination-container {
  margin-top: 20px;
  display: flex;
  justify-content: flex-end;
}
</style>