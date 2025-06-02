export const searchConfig = {
  pageName: 'user',
  header: {
    title: "用户列表",
    btnTitle: "新建用户",
  },
  propsList: [
    {
      type:'selection',
      label : '选择',
      width : '40'
    },
    {
      type:'index',
      label : '序号',
      width : '40',
      align : 'center'
    }
    ,
    {
    type : 'input',
    label : '用户名',
    prop : 'userName',
    width: '120px',
    initValue : '',

  },{
    type : 'input',
    label : '昵称',
    prop : 'nickName',
    width: '100px',
    initValue : '',
  },
  {
    type : 'input',
    label:'性别',
    prop : 'gender',
    width: '60px',
    initValue : '',
  },{
    type : 'input',
    label : '状态',
    prop : 'status',
    width: '60px',
    initValue : '',

  },{
    type : 'input',
    label : '用户类型',
    prop : 'userType',
    width: '100px',
    initValue : '',
  },{
    type : 'input',
    label : '用户描述',
    prop : 'remark',
    width: '100px',
    initValue : '',
  },
  {
    type : 'input',
    label : '手机号',
    prop : 'phoneNumber',
    width: '150px',
    initValue : '',
  },
  // {
  //   type : 'input',
  //   label : '身份证',
  //   prop : 'idCard',
  //   width: '180px',
  //   initValue : '',

  // },
  {
    type : 'timer',
    label : '创建时间',
    prop :'createTime',
     width: '180px',
  },
  // {
  //   type : 'timer',
  //   label : '更新时间',
  //   prop :'updateTime',
  //    width: '180px',
  // }
  {
    type: 'handler',
    label: '操作',
    fixed: 'right'
  }
  ]
}

export default searchConfig