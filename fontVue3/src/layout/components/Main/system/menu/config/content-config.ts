export const contentConfig = {
  header: {
    title: '菜单列表',
    btnTitle: '新建菜单'
  },
  pageName: 'menu',
  propsList: [
{

    label:'权限名称',
    prop:'menuName',
    width:'140',
    align:'center'
   },

   {

    label:'权限类型',
    prop:'menuType',
    width:'120px',
    align:'center'
   },
   {

    label:'权限路由',
    prop:'path',
    width:'190px',
    align:'center'
   },
   {

    label:'权限标识',
    prop:'perms',
    width:'120px',
    align:'center'
   },
   {
    label:'创建者',
    prop:'createBy',
    width:'120px',
    align:'center'
   },
   {
    label:'创建时间',
    prop:'createTime',
    width:'160px',
    align:'center'
   },
   {
    label:'修改者',
    prop:'updateBy',
    width:'120px',
    align:'center'
   },
   {
    label:'修改时间',
    prop:'updateTime',
    width:'160px',
    align:'center'
   },
   {
    type : 'handler',
    label:'操作',
    align:'center'
   }
  ],
  childrenTree:{
    rowKey: 'menuId',
      treeProps: {
      children: 'children',
      hasChildren: 'hasChildren'
    }
  }
}