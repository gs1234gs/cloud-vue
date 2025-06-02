import { deletePageById, getPageListData, postAddPageInfo, postPageListData, putUpdatePage } from '@/api/system/System'
import type { PaginationQueryUser } from '@/api/system/system-type/system-pagenation-user'
import { USER_SYSTEM } from '@/global/constants'
import { cacheSession } from '@/utils/cache'
import {defineStore} from 'pinia'
import {  ref } from 'vue'
// 定义系统管理store
export const useSystemStore = defineStore(USER_SYSTEM,() => {
// 定义接收用户列表的变量
const paginationLists = ref<any>(
  cacheSession.getCache('paginationLists') || { pageTotal: 0, pageRows: [] }
);
  const pageLists = ref([])
   // 通用条件查询分页列表数据
   const postPageListAction = async (pageName?:string,queryInfo?:any) => {
    // console.log("获取",queryInfo)
      const pageListResult : any = await postPageListData(pageName, queryInfo)
      pageLists.value = pageListResult.data.data
      // console.log("pageListResult",pageLists.value)
   }

   // 获取页面树,通用获取分页列表数据
   const getPageListDataAction = async (pageName?:string) => {
      // console.log("获取")
      const pageListResult : any = await getPageListData(pageName)
      pageLists.value = pageListResult.data.data
      // console.log("pageListResult",pageLists.value)

   }
    // 删除页面信息，通过id
    const deletePageAction = async (pageName: string ,pageId : number) => {
      const deletePageResult : any = await deletePageById(pageName,pageId)
      getPageListDataAction(pageName);
    }
    //增加页面信息
    const postPageAddAction = async (pageName: string ,pageInfo : any) => {
      const addPageResult : any = await postAddPageInfo(pageName,pageInfo)
      // console.log("addPageResult",addPageResult)
      getPageListDataAction(pageName);
    }

    //修改页面信息，通过id
    const putPageupdateAction = async (pageName: string ,pageInfo : any) => {
      const deletePageResult : any = await putUpdatePage(pageName,pageInfo)
      // console.log("deletePageResult",deletePageResult)
      getPageListDataAction(pageName);
    }

    //分页条件合并查询专用，后期合并
    const postPaginateListAction = async (pageName?:string,queryInfo?:PaginationQueryUser) => {
      const pageListResult : any = await postPageListData(pageName, queryInfo)
      // console.log("pageListResult",pageListResult.data.data)
       paginationLists.value = pageListResult.data.data
      //  console.log("pageListResult----------------",paginationLists.value)
      cacheSession.setCache('paginationLists', paginationLists.value)
      // console.log("pageListResult---------------",pageListResult.pageRows)
   }


    return {
      paginationLists,
      pageLists,
      postPageListAction,
      getPageListDataAction,
      deletePageAction,
      postPageAddAction,
      putPageupdateAction,
      postPaginateListAction

    }


},{persist:true})
// 导出store
export default useSystemStore
