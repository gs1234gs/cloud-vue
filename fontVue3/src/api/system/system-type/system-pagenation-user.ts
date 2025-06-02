export interface PaginationQueryUser {
    currentPage: number;//当前页
    pageSize: number;//每页显示条数
    sortField: string;//排序字段
    sortOrder: string;//排序方式(asc表示升序,desc表示降序)
    pageInfo:any;//查询条件
}