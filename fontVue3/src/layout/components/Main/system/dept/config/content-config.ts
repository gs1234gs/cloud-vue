const contentConfig = {
  pageName: 'dept',
  header: {
    title: '部门列表',
    btnTitle: '新建部门'
  },
  propsList: [
    {  label: '', width: '55' },
    {  label: '序号', width: '55', align: 'center' },
    { label: '部门名称', prop: 'deptName', width: '150px' , slot: 'deptName',initValue: '' },
    {  label: '联系方式', prop: 'phoneNumber', width: '150px' },
    { label: '部门负责人', prop: 'leader', width: '100px' },
    {  label: '上级部门', prop: 'parentId', width: '100px' },
    {  label: '创建时间', prop: 'createTime', width: '180px' },
    {  label: '更新时间', prop: 'updateTime', width: '180px' },
    { type: 'handler', label: '操作' , fixed:"right"},
    {
      type: "button",
      label: "部门状态",
      prop: "status",
      width: "100px",
      slot: "status",
    },

    // { type: 'handler', label: '操作', width: '180px' }
  ],
  childrenTree:{
    rowKey: 'deptId',
    treeProps: {
    children: 'children',
    hasChildren: 'hasChildren'
  }
}

}

export default contentConfig