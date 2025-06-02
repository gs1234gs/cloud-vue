const contentConfig = {
 pageName: 'role',
  header: {
    title: '角色列表',
    btnTitle: '新建角色'
  },

  propsList: [
    { type: 'selection',
       label: '选择',
        width: '55'
      },
    { type: 'index',
      label: '序号',
      width: '55',
      align: 'center'
     },
    { type: 'normal',
      label: '角色名称',
      prop: 'roleName',
       width: '150px' ,
       initValue: ''
      },
       {
        type: 'normal',
        label: '权限字符',
        prop: 'roleKey',
        width: '150px' ,
        initValue: ''
      },
       {
        type: 'timer',
        label: '创建时间',
        prop: 'createTime',
        width: '180px',
        initValue: ''
       },
       {
        type: 'timer',
        label: '更新时间',
        prop: 'updateTime',
        width: '180px',
        initValue: ''
       },
       {
        type: 'normal',
        label: '状态',
        prop: 'status',
        width: '100px',
        initValue: ''
       },
       {
        type: 'handler',
        label: '操作',
        width: '200px',
        initValue: ''
       }

  ]
}

export default contentConfig