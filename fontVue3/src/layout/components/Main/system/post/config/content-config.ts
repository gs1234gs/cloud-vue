const contentConfig = {
 pageName: 'post',
  header: {
    title: '岗位列表',
    btnTitle: '新增岗位'
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
      label: '岗位名称',
      prop: 'postName',
       width: '150px' ,
       initValue: ''
      },
       {
        type: 'normal',
        label: '岗位代号',
        prop: 'postCode',
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