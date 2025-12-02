# Physical boot 后台服务API接口文档


**简介**:Physical boot 后台服务API接口文档


**HOST**:http://physical-launcher/physical


**联系人**:YGG


**Version**:1.0


**接口路径**:/physical/v3/api-docs/default


[TOC]


# 系统表白名单


## 系统表白名单-编辑


**接口地址**:`/api/sys/tableWhiteList/edit`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sysTableWhiteList|sys_table_white_list对象|body|true|SysTableWhiteList|SysTableWhiteList|
|id|主键id||false|string||
|tableName|允许的表名||false|string||
|fieldName|允许的字段名||false|string||
|status|状态||false|string||


## 系统表白名单-编辑


**接口地址**:`/api/sys/tableWhiteList/edit`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sysTableWhiteList|sys_table_white_list对象|body|true|SysTableWhiteList|SysTableWhiteList|
|id|主键id||false|string||
|tableName|允许的表名||false|string||
|fieldName|允许的字段名||false|string||
|status|状态||false|string||


## 系统表白名单-添加


**接口地址**:`/api/sys/tableWhiteList/add`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sysTableWhiteList|sys_table_white_list对象|body|true|SysTableWhiteList|SysTableWhiteList|
|id|主键id||false|string||
|tableName|允许的表名||false|string||
|fieldName|允许的字段名||false|string||
|status|状态||false|string||


## 系统表白名单-通过id查询


**接口地址**:`/api/sys/tableWhiteList/queryById`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 系统表白名单-通过id删除


**接口地址**:`/api/sys/tableWhiteList/delete`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 系统表白名单-批量删除


**接口地址**:`/api/sys/tableWhiteList/deleteBatch`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ids||query|true|string||


# 角色首页配置


## 角色首页配置-编辑


**接口地址**:`/api/sys/sysRoleIndex/edit`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sysRoleIndex|sys_role_index对象|body|true|SysRoleIndex|SysRoleIndex|
|id|id||false|string||
|roleCode|角色编码||false|string||
|url|路由地址||false|string||
|component|组件||false|string||
|route|是否路由菜单||false|boolean||
|priority|优先级||false|integer(int32)||
|status|状态||false|string||


## 角色首页配置-编辑


**接口地址**:`/api/sys/sysRoleIndex/edit`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sysRoleIndex|sys_role_index对象|body|true|SysRoleIndex|SysRoleIndex|
|id|id||false|string||
|roleCode|角色编码||false|string||
|url|路由地址||false|string||
|component|组件||false|string||
|route|是否路由菜单||false|boolean||
|priority|优先级||false|integer(int32)||
|status|状态||false|string||


## 角色首页配置-添加


**接口地址**:`/api/sys/sysRoleIndex/add`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sysRoleIndex|sys_role_index对象|body|true|SysRoleIndex|SysRoleIndex|
|id|id||false|string||
|roleCode|角色编码||false|string||
|url|路由地址||false|string||
|component|组件||false|string||
|route|是否路由菜单||false|boolean||
|priority|优先级||false|integer(int32)||
|status|状态||false|string||


## 角色首页配置-通过id查询


**接口地址**:`/api/sys/sysRoleIndex/queryById`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 角色首页配置-通过code查询


**接口地址**:`/api/sys/sysRoleIndex/queryByCode`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|roleCode||query|true|string||


## 角色首页配置-分页列表查询


**接口地址**:`/api/sys/sysRoleIndex/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sysRoleIndex|sys_role_index对象|query|true|SysRoleIndex|SysRoleIndex|
|id|id||false|string||
|roleCode|角色编码||false|string||
|url|路由地址||false|string||
|component|组件||false|string||
|route|是否路由菜单||false|boolean||
|priority|优先级||false|integer(int32)||
|status|状态||false|string||
|pageNo||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||


## 角色首页配置-通过id删除


**接口地址**:`/api/sys/sysRoleIndex/delete`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 角色首页配置-批量删除


**接口地址**:`/api/sys/sysRoleIndex/deleteBatch`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ids||query|true|string||


# 部门角色


## 部门角色-编辑


**接口地址**:`/api/sys/sysDepartRole/edit`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sysDepartRole|sys_depart_role对象|body|true|SysDepartRole|SysDepartRole|
|id|id||false|string||
|departId|部门id||false|string||
|roleName|部门角色名称||false|string||
|roleCode|部门角色编码||false|string||
|description|描述||false|string||


## 部门角色-编辑


**接口地址**:`/api/sys/sysDepartRole/edit`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sysDepartRole|sys_depart_role对象|body|true|SysDepartRole|SysDepartRole|
|id|id||false|string||
|departId|部门id||false|string||
|roleName|部门角色名称||false|string||
|roleCode|部门角色编码||false|string||
|description|描述||false|string||


## 部门角色-添加


**接口地址**:`/api/sys/sysDepartRole/add`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sysDepartRole|sys_depart_role对象|body|true|SysDepartRole|SysDepartRole|
|id|id||false|string||
|departId|部门id||false|string||
|roleName|部门角色名称||false|string||
|roleCode|部门角色编码||false|string||
|description|描述||false|string||


## 部门角色-通过id查询


**接口地址**:`/api/sys/sysDepartRole/queryById`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 部门角色-分页列表查询


**接口地址**:`/api/sys/sysDepartRole/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sysDepartRole|sys_depart_role对象|query|true|SysDepartRole|SysDepartRole|
|id|id||false|string||
|departId|部门id||false|string||
|roleName|部门角色名称||false|string||
|roleCode|部门角色编码||false|string||
|description|描述||false|string||
|pageNo||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||
|deptId||query|false|string||


## 部门角色-通过id删除


**接口地址**:`/api/sys/sysDepartRole/delete`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 部门角色-批量删除


**接口地址**:`/api/sys/sysDepartRole/deleteBatch`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ids||query|true|string||


# 部门权限表


## 部门权限表-编辑


**接口地址**:`/api/sys/sysDepartPermission/edit`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sysDepartPermission|部门权限表|body|true|SysDepartPermission|SysDepartPermission|
|id|id||false|string||
|departId|部门id||false|string||
|permissionId|权限id||false|string||
|dataRuleIds|数据规则id||false|string||


## 部门权限表-编辑


**接口地址**:`/api/sys/sysDepartPermission/edit`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sysDepartPermission|部门权限表|body|true|SysDepartPermission|SysDepartPermission|
|id|id||false|string||
|departId|部门id||false|string||
|permissionId|权限id||false|string||
|dataRuleIds|数据规则id||false|string||


## 部门权限表-添加


**接口地址**:`/api/sys/sysDepartPermission/add`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sysDepartPermission|部门权限表|body|true|SysDepartPermission|SysDepartPermission|
|id|id||false|string||
|departId|部门id||false|string||
|permissionId|权限id||false|string||
|dataRuleIds|数据规则id||false|string||


## 部门权限表-通过id查询


**接口地址**:`/api/sys/sysDepartPermission/queryById`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 部门权限表-分页列表查询


**接口地址**:`/api/sys/sysDepartPermission/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sysDepartPermission|部门权限表|query|true|SysDepartPermission|SysDepartPermission|
|id|id||false|string||
|departId|部门id||false|string||
|permissionId|权限id||false|string||
|dataRuleIds|数据规则id||false|string||
|pageNo||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||


## 部门权限表-通过id删除


**接口地址**:`/api/sys/sysDepartPermission/delete`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 部门权限表-批量删除


**接口地址**:`/api/sys/sysDepartPermission/deleteBatch`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ids||query|true|string||


# 职务表


## 职务表-编辑


**接口地址**:`/api/sys/position/edit`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sysPosition|sys_position对象|body|true|SysPosition|SysPosition|
|id|id||false|string||
|code|职务编码||false|string||
|name|职务名称||false|string||
|postRank|职级||false|string||
|companyId|公司id||false|string||
|tenantId|租户ID||false|integer(int32)||


## 职务表-编辑


**接口地址**:`/api/sys/position/edit`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sysPosition|sys_position对象|body|true|SysPosition|SysPosition|
|id|id||false|string||
|code|职务编码||false|string||
|name|职务名称||false|string||
|postRank|职级||false|string||
|companyId|公司id||false|string||
|tenantId|租户ID||false|integer(int32)||


## 职务表-添加


**接口地址**:`/api/sys/position/add`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sysPosition|sys_position对象|body|true|SysPosition|SysPosition|
|id|id||false|string||
|code|职务编码||false|string||
|name|职务名称||false|string||
|postRank|职级||false|string||
|companyId|公司id||false|string||
|tenantId|租户ID||false|integer(int32)||


## 职务表-通过多个id查询


**接口地址**:`/api/sys/position/queryByIds`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ids||query|true|string||


## 职务表-通过id查询


**接口地址**:`/api/sys/position/queryById`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 职务表-通过code查询


**接口地址**:`/api/sys/position/queryByCode`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|code||query|true|string||


## 职务表-分页列表查询


**接口地址**:`/api/sys/position/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sysPosition|sys_position对象|query|true|SysPosition|SysPosition|
|id|id||false|string||
|code|职务编码||false|string||
|name|职务名称||false|string||
|postRank|职级||false|string||
|companyId|公司id||false|string||
|tenantId|租户ID||false|integer(int32)||
|pageNo||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||


## 职务表-通过id删除


**接口地址**:`/api/sys/position/delete`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 职务表-批量删除


**接口地址**:`/api/sys/position/deleteBatch`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ids||query|true|string||


# 表单评论文件


## 表单评论文件-编辑


**接口地址**:`/api/sys/formFile/edit`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sysFormFile|sys_form_file对象|body|true|SysFormFile|SysFormFile|
|id|id||false|string||
|tableName|表名||false|string||
|tableDataId|数据id||false|string||
|fileId|关联文件id||false|string||
|fileType|文档类型（folder:文件夹 excel:excel doc:word pp:ppt image:图片  archive:其他文档 video:视频）||false|string||


## 表单评论文件-编辑


**接口地址**:`/api/sys/formFile/edit`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sysFormFile|sys_form_file对象|body|true|SysFormFile|SysFormFile|
|id|id||false|string||
|tableName|表名||false|string||
|tableDataId|数据id||false|string||
|fileId|关联文件id||false|string||
|fileType|文档类型（folder:文件夹 excel:excel doc:word pp:ppt image:图片  archive:其他文档 video:视频）||false|string||


## 表单评论文件-添加


**接口地址**:`/api/sys/formFile/add`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sysFormFile|sys_form_file对象|body|true|SysFormFile|SysFormFile|
|id|id||false|string||
|tableName|表名||false|string||
|tableDataId|数据id||false|string||
|fileId|关联文件id||false|string||
|fileType|文档类型（folder:文件夹 excel:excel doc:word pp:ppt image:图片  archive:其他文档 video:视频）||false|string||


## 表单评论文件-通过id查询


**接口地址**:`/api/sys/formFile/queryById`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 表单评论文件-分页列表查询


**接口地址**:`/api/sys/formFile/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sysFormFile|sys_form_file对象|query|true|SysFormFile|SysFormFile|
|id|id||false|string||
|tableName|表名||false|string||
|tableDataId|数据id||false|string||
|fileId|关联文件id||false|string||
|fileType|文档类型（folder:文件夹 excel:excel doc:word pp:ppt image:图片  archive:其他文档 video:视频）||false|string||
|pageNo||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||


## 表单评论文件-通过id删除


**接口地址**:`/api/sys/formFile/delete`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 表单评论文件-批量删除


**接口地址**:`/api/sys/formFile/deleteBatch`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ids||query|true|string||


# 填值规则


## 填值规则-编辑


**接口地址**:`/api/sys/fillRule/edit`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sysFillRule|填值规则|body|true|SysFillRule|SysFillRule|
|id|主键ID||false|string||
|ruleName|规则名称||false|string||
|ruleCode|规则Code||false|string||
|ruleClass|规则实现类||false|string||
|ruleParams|规则参数||false|string||


## 填值规则-编辑


**接口地址**:`/api/sys/fillRule/edit`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sysFillRule|填值规则|body|true|SysFillRule|SysFillRule|
|id|主键ID||false|string||
|ruleName|规则名称||false|string||
|ruleCode|规则Code||false|string||
|ruleClass|规则实现类||false|string||
|ruleParams|规则参数||false|string||


## 填值规则-添加


**接口地址**:`/api/sys/fillRule/add`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sysFillRule|填值规则|body|true|SysFillRule|SysFillRule|
|id|主键ID||false|string||
|ruleName|规则名称||false|string||
|ruleCode|规则Code||false|string||
|ruleClass|规则实现类||false|string||
|ruleParams|规则参数||false|string||


## 填值规则-通过id查询


**接口地址**:`/api/sys/fillRule/queryById`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 填值规则-分页列表查询


**接口地址**:`/api/sys/fillRule/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sysFillRule|填值规则|query|true|SysFillRule|SysFillRule|
|id|主键ID||false|string||
|ruleName|规则名称||false|string||
|ruleCode|规则Code||false|string||
|ruleClass|规则实现类||false|string||
|ruleParams|规则参数||false|string||
|pageNo||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||


## 填值规则-通过id删除


**接口地址**:`/api/sys/fillRule/delete`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 填值规则-批量删除


**接口地址**:`/api/sys/fillRule/deleteBatch`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ids||query|true|string||


# 多数据源管理


## 多数据源管理-编辑


**接口地址**:`/api/sys/dataSource/edit`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sysDataSource|sys_data_source对象|body|true|SysDataSource|SysDataSource|
|id|id||false|string||
|code|数据源编码||false|string||
|name|数据源名称||false|string||
|remark|备注||false|string||
|dbType|数据库类型||false|string||
|dbDriver|驱动类||false|string||
|dbUrl|数据源地址||false|string||
|dbName|数据库名称||false|string||
|dbUsername|用户名||false|string||
|dbPassword|密码||false|string||
|tenantId|租户ID||false|integer(int32)||


## 多数据源管理-编辑


**接口地址**:`/api/sys/dataSource/edit`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sysDataSource|sys_data_source对象|body|true|SysDataSource|SysDataSource|
|id|id||false|string||
|code|数据源编码||false|string||
|name|数据源名称||false|string||
|remark|备注||false|string||
|dbType|数据库类型||false|string||
|dbDriver|驱动类||false|string||
|dbUrl|数据源地址||false|string||
|dbName|数据库名称||false|string||
|dbUsername|用户名||false|string||
|dbPassword|密码||false|string||
|tenantId|租户ID||false|integer(int32)||


## 多数据源管理-添加


**接口地址**:`/api/sys/dataSource/add`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sysDataSource|sys_data_source对象|body|true|SysDataSource|SysDataSource|
|id|id||false|string||
|code|数据源编码||false|string||
|name|数据源名称||false|string||
|remark|备注||false|string||
|dbType|数据库类型||false|string||
|dbDriver|驱动类||false|string||
|dbUrl|数据源地址||false|string||
|dbName|数据库名称||false|string||
|dbUsername|用户名||false|string||
|dbPassword|密码||false|string||
|tenantId|租户ID||false|integer(int32)||


## 多数据源管理-通过id查询


**接口地址**:`/api/sys/dataSource/queryById`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 多数据源管理-分页列表查询


**接口地址**:`/api/sys/dataSource/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sysDataSource|sys_data_source对象|query|true|SysDataSource|SysDataSource|
|id|id||false|string||
|code|数据源编码||false|string||
|name|数据源名称||false|string||
|remark|备注||false|string||
|dbType|数据库类型||false|string||
|dbDriver|驱动类||false|string||
|dbUrl|数据源地址||false|string||
|dbName|数据库名称||false|string||
|dbUsername|用户名||false|string||
|dbPassword|密码||false|string||
|tenantId|租户ID||false|integer(int32)||
|pageNo||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||


## 多数据源管理-通过id删除


**接口地址**:`/api/sys/dataSource/delete`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 多数据源管理-批量删除


**接口地址**:`/api/sys/dataSource/deleteBatch`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ids||query|true|string||


# 系统评论回复表


## 系统评论回复表-编辑


**接口地址**:`/api/sys/comment/edit`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sysComment|sys_comment对象|body|true|SysComment|SysComment|
|id|id||false|string||
|tableName|表名||false|string||
|tableDataId|数据id||false|string||
|fromUserId|来源用户id||false|string||
|toUserId|发送给用户id(允许为空)||false|string||
|commentId|评论id(允许为空，不为空时，则为回复)||false|string||
|commentContent|回复内容||false|string||


## 系统评论回复表-编辑


**接口地址**:`/api/sys/comment/edit`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sysComment|sys_comment对象|body|true|SysComment|SysComment|
|id|id||false|string||
|tableName|表名||false|string||
|tableDataId|数据id||false|string||
|fromUserId|来源用户id||false|string||
|toUserId|发送给用户id(允许为空)||false|string||
|commentId|评论id(允许为空，不为空时，则为回复)||false|string||
|commentContent|回复内容||false|string||


## 系统评论表-添加文件


**接口地址**:`/api/sys/comment/appAddFile`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


暂无


## 系统评论回复表-添加


**接口地址**:`/api/sys/comment/add`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sysComment|sys_comment对象|body|true|SysComment|SysComment|
|id|id||false|string||
|tableName|表名||false|string||
|tableDataId|数据id||false|string||
|fromUserId|来源用户id||false|string||
|toUserId|发送给用户id(允许为空)||false|string||
|commentId|评论id(允许为空，不为空时，则为回复)||false|string||
|commentContent|回复内容||false|string||


## 系统评论表-添加文本


**接口地址**:`/api/sys/comment/addText`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sysComment|sys_comment对象|body|true|SysComment|SysComment|
|id|id||false|string||
|tableName|表名||false|string||
|tableDataId|数据id||false|string||
|fromUserId|来源用户id||false|string||
|toUserId|发送给用户id(允许为空)||false|string||
|commentId|评论id(允许为空，不为空时，则为回复)||false|string||
|commentContent|回复内容||false|string||


## 系统评论表-添加文件


**接口地址**:`/api/sys/comment/addFile`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


暂无


## 系统评论回复表-通过id查询


**接口地址**:`/api/sys/comment/queryById`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 系统评论回复表-分页列表查询


**接口地址**:`/api/sys/comment/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sysComment|sys_comment对象|query|true|SysComment|SysComment|
|id|id||false|string||
|tableName|表名||false|string||
|tableDataId|数据id||false|string||
|fromUserId|来源用户id||false|string||
|toUserId|发送给用户id(允许为空)||false|string||
|commentId|评论id(允许为空，不为空时，则为回复)||false|string||
|commentContent|回复内容||false|string||
|pageNo||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||


## 系统评论回复表-列表查询


**接口地址**:`/api/sys/comment/listByForm`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sysComment|sys_comment对象|query|true|SysComment|SysComment|
|id|id||false|string||
|tableName|表名||false|string||
|tableDataId|数据id||false|string||
|fromUserId|来源用户id||false|string||
|toUserId|发送给用户id(允许为空)||false|string||
|commentId|评论id(允许为空，不为空时，则为回复)||false|string||
|commentContent|回复内容||false|string||


## 系统评论回复表-列表查询


**接口地址**:`/api/sys/comment/fileList`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sysComment|sys_comment对象|query|true|SysComment|SysComment|
|id|id||false|string||
|tableName|表名||false|string||
|tableDataId|数据id||false|string||
|fromUserId|来源用户id||false|string||
|toUserId|发送给用户id(允许为空)||false|string||
|commentId|评论id(允许为空，不为空时，则为回复)||false|string||
|commentContent|回复内容||false|string||


## 系统评论回复表-通过id删除


**接口地址**:`/api/sys/comment/delete`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 系统评论回复表-通过id删除


**接口地址**:`/api/sys/comment/deleteOne`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 系统评论回复表-批量删除


**接口地址**:`/api/sys/comment/deleteBatch`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ids||query|true|string||


# 编码校验规则


## 编码校验规则-编辑


**接口地址**:`/api/sys/checkRule/edit`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sysCheckRule|sys_check_rule对象|body|true|SysCheckRule|SysCheckRule|
|id|主键id||false|string||
|ruleName|规则名称||false|string||
|ruleCode|规则Code||false|string||
|ruleJson|规则JSON||false|string||
|ruleDescription|规则描述||false|string||


## 编码校验规则-编辑


**接口地址**:`/api/sys/checkRule/edit`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sysCheckRule|sys_check_rule对象|body|true|SysCheckRule|SysCheckRule|
|id|主键id||false|string||
|ruleName|规则名称||false|string||
|ruleCode|规则Code||false|string||
|ruleJson|规则JSON||false|string||
|ruleDescription|规则描述||false|string||


## 编码校验规则-添加


**接口地址**:`/api/sys/checkRule/add`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sysCheckRule|sys_check_rule对象|body|true|SysCheckRule|SysCheckRule|
|id|主键id||false|string||
|ruleName|规则名称||false|string||
|ruleCode|规则Code||false|string||
|ruleJson|规则JSON||false|string||
|ruleDescription|规则描述||false|string||


## 编码校验规则-通过id查询


**接口地址**:`/api/sys/checkRule/queryById`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 编码校验规则-分页列表查询


**接口地址**:`/api/sys/checkRule/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sysCheckRule|sys_check_rule对象|query|true|SysCheckRule|SysCheckRule|
|id|主键id||false|string||
|ruleName|规则名称||false|string||
|ruleCode|规则Code||false|string||
|ruleJson|规则JSON||false|string||
|ruleDescription|规则描述||false|string||
|pageNo||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||


## 编码校验规则-通过Code校验传入的值


**接口地址**:`/api/sys/checkRule/checkByCode`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ruleCode||query|true|string||
|value||query|true|string||


## 编码校验规则-通过id删除


**接口地址**:`/api/sys/checkRule/delete`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 编码校验规则-批量删除


**接口地址**:`/api/sys/checkRule/deleteBatch`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ids||query|true|string||


# 文档访问历史


## 文档访问历史-编辑


**接口地址**:`/api/library/documentVisitHistory/edit`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|documentVisitHistory|文档访问历史|body|true|DocumentVisitHistory|DocumentVisitHistory|
|id|主键||false|string||
|documentId|文档ID||false|string||
|userId|用户ID||false|string||
|documentTitle|文档标题||false|string||
|visitCount|访问次数||false|integer(int32)||


## 文档访问历史-编辑


**接口地址**:`/api/library/documentVisitHistory/edit`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|documentVisitHistory|文档访问历史|body|true|DocumentVisitHistory|DocumentVisitHistory|
|id|主键||false|string||
|documentId|文档ID||false|string||
|userId|用户ID||false|string||
|documentTitle|文档标题||false|string||
|visitCount|访问次数||false|integer(int32)||


## 文档访问历史-添加


**接口地址**:`/api/library/documentVisitHistory/add`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|documentVisitHistory|文档访问历史|body|true|DocumentVisitHistory|DocumentVisitHistory|
|id|主键||false|string||
|documentId|文档ID||false|string||
|userId|用户ID||false|string||
|documentTitle|文档标题||false|string||
|visitCount|访问次数||false|integer(int32)||


## 文档访问历史-通过id查询


**接口地址**:`/api/library/documentVisitHistory/queryById`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 文档访问历史-分页列表查询


**接口地址**:`/api/library/documentVisitHistory/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|documentVisitHistory|文档访问历史|query|true|DocumentVisitHistory|DocumentVisitHistory|
|id|主键||false|string||
|documentId|文档ID||false|string||
|userId|用户ID||false|string||
|documentTitle|文档标题||false|string||
|visitCount|访问次数||false|integer(int32)||
|pageNo||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||


## 文档访问历史-通过id删除


**接口地址**:`/api/library/documentVisitHistory/delete`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 文档访问历史-批量删除


**接口地址**:`/api/library/documentVisitHistory/deleteBatch`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ids||query|true|string||


# 知识库


## 知识库-编辑


**接口地址**:`/api/library/documentLibrary/edit`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|documentLibrary|知识库|body|true|DocumentLibrary|DocumentLibrary|
|id|主键||false|string||
|title|文档标题||false|string||
|tags|文档标签||false|string||
|content|文档内容||false|string||
|parentId|父级ID||false|string||
|type|类型(DOCUMENT/FOLDER)||false|string||
|fileList|附件||false|string||


## 知识库-编辑


**接口地址**:`/api/library/documentLibrary/edit`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|documentLibrary|知识库|body|true|DocumentLibrary|DocumentLibrary|
|id|主键||false|string||
|title|文档标题||false|string||
|tags|文档标签||false|string||
|content|文档内容||false|string||
|parentId|父级ID||false|string||
|type|类型(DOCUMENT/FOLDER)||false|string||
|fileList|附件||false|string||


## 知识库-添加


**接口地址**:`/api/library/documentLibrary/add`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|documentLibrary|知识库|body|true|DocumentLibrary|DocumentLibrary|
|id|主键||false|string||
|title|文档标题||false|string||
|tags|文档标签||false|string||
|content|文档内容||false|string||
|parentId|父级ID||false|string||
|type|类型(DOCUMENT/FOLDER)||false|string||
|fileList|附件||false|string||


## 知识库-文档树查询


**接口地址**:`/api/library/documentLibrary/tree`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|parentId||query|true|string||


## 知识库-首页搜索


**接口地址**:`/api/library/documentLibrary/search`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|keyWord||query|true|string||


## 知识库-通过id查询


**接口地址**:`/api/library/documentLibrary/queryById`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 知识库-分页列表查询


**接口地址**:`/api/library/documentLibrary/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|documentLibrary|知识库|query|true|DocumentLibrary|DocumentLibrary|
|id|主键||false|string||
|title|文档标题||false|string||
|tags|文档标签||false|string||
|content|文档内容||false|string||
|parentId|父级ID||false|string||
|type|类型(DOCUMENT/FOLDER)||false|string||
|fileList|附件||false|string||
|pageNo||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||


## 知识库-首页查询


**接口地址**:`/api/library/documentLibrary/home`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


暂无


## 知识库-通过id删除


**接口地址**:`/api/library/documentLibrary/delete`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 知识库-批量删除


**接口地址**:`/api/library/documentLibrary/deleteBatch`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ids||query|true|string||


# 测试标准


## 测试标准-编辑


**接口地址**:`/api/database/testStandards/edit`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|testStandards|测试标准|body|true|TestStandards|TestStandards|
|id|主键||false|string||
|name|标准名称||false|string||
|code|标准代码||false|string||
|content|内容||false|string||
|attachment|附件||false|string||
|temperatureRequirements|温度要求||false|string||
|humidityRequirements|湿度要求||false|string||


## 测试标准-编辑


**接口地址**:`/api/database/testStandards/edit`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|testStandards|测试标准|body|true|TestStandards|TestStandards|
|id|主键||false|string||
|name|标准名称||false|string||
|code|标准代码||false|string||
|content|内容||false|string||
|attachment|附件||false|string||
|temperatureRequirements|温度要求||false|string||
|humidityRequirements|湿度要求||false|string||


## 测试标准-添加


**接口地址**:`/api/database/testStandards/add`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|testStandards|测试标准|body|true|TestStandards|TestStandards|
|id|主键||false|string||
|name|标准名称||false|string||
|code|标准代码||false|string||
|content|内容||false|string||
|attachment|附件||false|string||
|temperatureRequirements|温度要求||false|string||
|humidityRequirements|湿度要求||false|string||


## 测试标准-通过id查询


**接口地址**:`/api/database/testStandards/queryById`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 测试标准-分页列表查询


**接口地址**:`/api/database/testStandards/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|testStandards|测试标准|query|true|TestStandards|TestStandards|
|id|主键||false|string||
|name|标准名称||false|string||
|code|标准代码||false|string||
|content|内容||false|string||
|attachment|附件||false|string||
|temperatureRequirements|温度要求||false|string||
|humidityRequirements|湿度要求||false|string||
|pageNo||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||


## 测试标准-通过id删除


**接口地址**:`/api/database/testStandards/delete`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 测试标准-批量删除


**接口地址**:`/api/database/testStandards/deleteBatch`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ids||query|true|string||


# 辐射源类型


## 辐射源类型-编辑


**接口地址**:`/api/database/radiateType/edit`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|radiateType|辐射源类型|body|true|RadiateType|RadiateType|
|id|主键||false|string||
|name|名称||false|string||
|model|型号||false|string||
|managementNo|管理编号||false|string||
|expireDate|有效期||false|string||
|roomNo|房间号||false|string||
|attachment|附件||false|string||


## 辐射源类型-编辑


**接口地址**:`/api/database/radiateType/edit`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|radiateType|辐射源类型|body|true|RadiateType|RadiateType|
|id|主键||false|string||
|name|名称||false|string||
|model|型号||false|string||
|managementNo|管理编号||false|string||
|expireDate|有效期||false|string||
|roomNo|房间号||false|string||
|attachment|附件||false|string||


## 辐射源类型-添加


**接口地址**:`/api/database/radiateType/add`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|radiateType|辐射源类型|body|true|RadiateType|RadiateType|
|id|主键||false|string||
|name|名称||false|string||
|model|型号||false|string||
|managementNo|管理编号||false|string||
|expireDate|有效期||false|string||
|roomNo|房间号||false|string||
|attachment|附件||false|string||


## 辐射源类型-通过id查询


**接口地址**:`/api/database/radiateType/queryById`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 辐射源类型-分页列表查询


**接口地址**:`/api/database/radiateType/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|radiateType|辐射源类型|query|true|RadiateType|RadiateType|
|id|主键||false|string||
|name|名称||false|string||
|model|型号||false|string||
|managementNo|管理编号||false|string||
|expireDate|有效期||false|string||
|roomNo|房间号||false|string||
|attachment|附件||false|string||
|pageNo||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||


## 辐射源类型-通过id删除


**接口地址**:`/api/database/radiateType/delete`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 辐射源类型-批量删除


**接口地址**:`/api/database/radiateType/deleteBatch`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ids||query|true|string||


# NASA数据管理


## NASA数据管理-编辑


**接口地址**:`/api/database/nasaDataRecord/edit`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|nasaDataRecord|NASA数据管理|body|true|NasaDataRecord|NasaDataRecord|
|id|主键||false|string||
|dataType|||false|string||
|deviceType|器件类型||false|string||
|deviceName|器件名称||false|string||
|deviceMode|器件型号||false|string||
|deviceFunction|器件功能||false|string||
|deviceBatch|器件批次||false|string||
|manufacturer|生产厂家||false|string||
|experimentDate|试验时间||false|string||
|dataSource|数据来源||false|string||
|experimentUser|试验人员||false|string||
|totalCountNasa|西核院统计||false|integer(int64)||
|totalCountLhs|理化所统计||false|integer(int64)||
|fileList|附件IDs||false|string||
|fileMap|||false|object||
|originData|原始数据||false|string||


## NASA数据管理-编辑


**接口地址**:`/api/database/nasaDataRecord/edit`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|nasaDataRecord|NASA数据管理|body|true|NasaDataRecord|NasaDataRecord|
|id|主键||false|string||
|dataType|||false|string||
|deviceType|器件类型||false|string||
|deviceName|器件名称||false|string||
|deviceMode|器件型号||false|string||
|deviceFunction|器件功能||false|string||
|deviceBatch|器件批次||false|string||
|manufacturer|生产厂家||false|string||
|experimentDate|试验时间||false|string||
|dataSource|数据来源||false|string||
|experimentUser|试验人员||false|string||
|totalCountNasa|西核院统计||false|integer(int64)||
|totalCountLhs|理化所统计||false|integer(int64)||
|fileList|附件IDs||false|string||
|fileMap|||false|object||
|originData|原始数据||false|string||


## NASA数据管理-添加


**接口地址**:`/api/database/nasaDataRecord/add`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|nasaDataRecord|NASA数据管理|body|true|NasaDataRecord|NasaDataRecord|
|id|主键||false|string||
|dataType|||false|string||
|deviceType|器件类型||false|string||
|deviceName|器件名称||false|string||
|deviceMode|器件型号||false|string||
|deviceFunction|器件功能||false|string||
|deviceBatch|器件批次||false|string||
|manufacturer|生产厂家||false|string||
|experimentDate|试验时间||false|string||
|dataSource|数据来源||false|string||
|experimentUser|试验人员||false|string||
|totalCountNasa|西核院统计||false|integer(int64)||
|totalCountLhs|理化所统计||false|integer(int64)||
|fileList|附件IDs||false|string||
|fileMap|||false|object||
|originData|原始数据||false|string||


## 外网数据统计


**接口地址**:`/api/database/nasaDataRecord/statistics`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


暂无


## NASA数据管理-通过id查询


**接口地址**:`/api/database/nasaDataRecord/queryById`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## NASA数据管理-分页列表查询


**接口地址**:`/api/database/nasaDataRecord/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|nasaDataRecord|NASA数据管理|query|true|NasaDataRecord|NasaDataRecord|
|id|主键||false|string||
|dataType|||false|string||
|deviceType|器件类型||false|string||
|deviceName|器件名称||false|string||
|deviceMode|器件型号||false|string||
|deviceFunction|器件功能||false|string||
|deviceBatch|器件批次||false|string||
|manufacturer|生产厂家||false|string||
|experimentDate|试验时间||false|string||
|dataSource|数据来源||false|string||
|experimentUser|试验人员||false|string||
|totalCountNasa|西核院统计||false|integer(int64)||
|totalCountLhs|理化所统计||false|integer(int64)||
|fileList|附件IDs||false|string||
|fileMap|||false|object||
|originData|原始数据||false|string||
|pageNo||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||


## NASA数据管理-通过id删除


**接口地址**:`/api/database/nasaDataRecord/delete`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## NASA数据管理-批量删除


**接口地址**:`/api/database/nasaDataRecord/deleteBatch`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ids||query|true|string||


# 当期数据库记录


## 当期数据库记录-编辑


**接口地址**:`/api/database/localDatabase/edit`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|localDatabase|当期数据库记录|body|true|LocalDatabase|LocalDatabase|
|id|主键||false|string||
|dataType|||false|string||
|experimentId|试验ID||false|string||
|experimentNo|试验编号||false|string||
|type|类型||false|string||
|name|名称||false|string||
|clientName|委托方||false|string||
|startDate|试验开始时间||false|string||
|endDate|试验结束时间||false|string||
|dataSource|数据来源||false|string||
|experimentUser|试验人员||false|string||
|totalCountXhy|西核院统计||false|integer(int32)||
|totalCountLhs|理化所统计||false|integer(int32)||
|status|状态||false|string||
|sampleModel|样品型号||false|string||
|description|描述||false|string||
|experimentReview|试验评定结果||false|ExperimentReview|ExperimentReview|
|id|id||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|experimentName|||false|string||
|sampleId|样品ID||false|string||
|sampleName|||false|string||
|processType|试验流程标准,可用值:GJB548C,ESCC229000,ESCC22500,QJ10004||false|string||
|needAnneal|是否需要退火,可用值:Y,N||false|string||
|targetedAnneal|是否进行了针对性的退火表征试验,可用值:Y,N||false|string||
|needRadiation|是否需要辐照,可用值:Y,N||false|string||
|needBurnin|是否需要老练,可用值:Y,N||false|string||
|result|评定结果||false|string||
|reviewStatus|评定状态||false|string||
|matchedRuleList|试验评定规则||false|array|ExperimentReviewRule|
|id|id||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|processType|试验流程标准||false|string||
|title|建议标题||false|string||
|result|评定结果||false|string||
|impactDesc|损伤机理||false|string||
|impactScope|适用范围||false|string||
|expression|规则表达式||false|string||
|priority|规则优先级||false|integer||
|experimentReviewList|||false|array|ExperimentReviewDTO|
|experimentId|||false|string||
|sampleName|||false|string||
|sampleId|||false|string||
|processType|||false|string||
|reviewResult|||false|string||


## 当期数据库记录-编辑


**接口地址**:`/api/database/localDatabase/edit`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|localDatabase|当期数据库记录|body|true|LocalDatabase|LocalDatabase|
|id|主键||false|string||
|dataType|||false|string||
|experimentId|试验ID||false|string||
|experimentNo|试验编号||false|string||
|type|类型||false|string||
|name|名称||false|string||
|clientName|委托方||false|string||
|startDate|试验开始时间||false|string||
|endDate|试验结束时间||false|string||
|dataSource|数据来源||false|string||
|experimentUser|试验人员||false|string||
|totalCountXhy|西核院统计||false|integer(int32)||
|totalCountLhs|理化所统计||false|integer(int32)||
|status|状态||false|string||
|sampleModel|样品型号||false|string||
|description|描述||false|string||
|experimentReview|试验评定结果||false|ExperimentReview|ExperimentReview|
|id|id||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|experimentName|||false|string||
|sampleId|样品ID||false|string||
|sampleName|||false|string||
|processType|试验流程标准,可用值:GJB548C,ESCC229000,ESCC22500,QJ10004||false|string||
|needAnneal|是否需要退火,可用值:Y,N||false|string||
|targetedAnneal|是否进行了针对性的退火表征试验,可用值:Y,N||false|string||
|needRadiation|是否需要辐照,可用值:Y,N||false|string||
|needBurnin|是否需要老练,可用值:Y,N||false|string||
|result|评定结果||false|string||
|reviewStatus|评定状态||false|string||
|matchedRuleList|试验评定规则||false|array|ExperimentReviewRule|
|id|id||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|processType|试验流程标准||false|string||
|title|建议标题||false|string||
|result|评定结果||false|string||
|impactDesc|损伤机理||false|string||
|impactScope|适用范围||false|string||
|expression|规则表达式||false|string||
|priority|规则优先级||false|integer||
|experimentReviewList|||false|array|ExperimentReviewDTO|
|experimentId|||false|string||
|sampleName|||false|string||
|sampleId|||false|string||
|processType|||false|string||
|reviewResult|||false|string||


## 当期数据库记录-添加


**接口地址**:`/api/database/localDatabase/add`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|localDatabase|当期数据库记录|body|true|LocalDatabase|LocalDatabase|
|id|主键||false|string||
|dataType|||false|string||
|experimentId|试验ID||false|string||
|experimentNo|试验编号||false|string||
|type|类型||false|string||
|name|名称||false|string||
|clientName|委托方||false|string||
|startDate|试验开始时间||false|string||
|endDate|试验结束时间||false|string||
|dataSource|数据来源||false|string||
|experimentUser|试验人员||false|string||
|totalCountXhy|西核院统计||false|integer(int32)||
|totalCountLhs|理化所统计||false|integer(int32)||
|status|状态||false|string||
|sampleModel|样品型号||false|string||
|description|描述||false|string||
|experimentReview|试验评定结果||false|ExperimentReview|ExperimentReview|
|id|id||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|experimentName|||false|string||
|sampleId|样品ID||false|string||
|sampleName|||false|string||
|processType|试验流程标准,可用值:GJB548C,ESCC229000,ESCC22500,QJ10004||false|string||
|needAnneal|是否需要退火,可用值:Y,N||false|string||
|targetedAnneal|是否进行了针对性的退火表征试验,可用值:Y,N||false|string||
|needRadiation|是否需要辐照,可用值:Y,N||false|string||
|needBurnin|是否需要老练,可用值:Y,N||false|string||
|result|评定结果||false|string||
|reviewStatus|评定状态||false|string||
|matchedRuleList|试验评定规则||false|array|ExperimentReviewRule|
|id|id||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|processType|试验流程标准||false|string||
|title|建议标题||false|string||
|result|评定结果||false|string||
|impactDesc|损伤机理||false|string||
|impactScope|适用范围||false|string||
|expression|规则表达式||false|string||
|priority|规则优先级||false|integer||
|experimentReviewList|||false|array|ExperimentReviewDTO|
|experimentId|||false|string||
|sampleName|||false|string||
|sampleId|||false|string||
|processType|||false|string||
|reviewResult|||false|string||


## 当期数据统计


**接口地址**:`/api/database/localDatabase/statistics`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


暂无


## 数据库全局搜索


**接口地址**:`/api/database/localDatabase/search`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|kw||query|true|string||


## 当期数据库记录-通过id查询


**接口地址**:`/api/database/localDatabase/queryById`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 当期数据库记录-分页列表查询


**接口地址**:`/api/database/localDatabase/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|localDatabase|当期数据库记录|query|true|LocalDatabase|LocalDatabase|
|id|主键||false|string||
|dataType|||false|string||
|experimentId|试验ID||false|string||
|experimentNo|试验编号||false|string||
|type|类型||false|string||
|name|名称||false|string||
|clientName|委托方||false|string||
|startDate|试验开始时间||false|string||
|endDate|试验结束时间||false|string||
|dataSource|数据来源||false|string||
|experimentUser|试验人员||false|string||
|totalCountXhy|西核院统计||false|integer(int32)||
|totalCountLhs|理化所统计||false|integer(int32)||
|status|状态||false|string||
|sampleModel|样品型号||false|string||
|description|描述||false|string||
|experimentReview|试验评定结果||false|ExperimentReview|ExperimentReview|
|id|id||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|experimentName|||false|string||
|sampleId|样品ID||false|string||
|sampleName|||false|string||
|processType|试验流程标准,可用值:GJB548C,ESCC229000,ESCC22500,QJ10004||false|string||
|needAnneal|是否需要退火,可用值:Y,N||false|string||
|targetedAnneal|是否进行了针对性的退火表征试验,可用值:Y,N||false|string||
|needRadiation|是否需要辐照,可用值:Y,N||false|string||
|needBurnin|是否需要老练,可用值:Y,N||false|string||
|result|评定结果||false|string||
|reviewStatus|评定状态||false|string||
|matchedRuleList|试验评定规则||false|array|ExperimentReviewRule|
|id|id||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|processType|试验流程标准||false|string||
|title|建议标题||false|string||
|result|评定结果||false|string||
|impactDesc|损伤机理||false|string||
|impactScope|适用范围||false|string||
|expression|规则表达式||false|string||
|priority|规则优先级||false|integer||
|experimentReviewList|||false|array|ExperimentReviewDTO|
|experimentId|||false|string||
|sampleName|||false|string||
|sampleId|||false|string||
|processType|||false|string||
|reviewResult|||false|string||
|pageNo||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||


## 当期数据库记录-通过id删除


**接口地址**:`/api/database/localDatabase/delete`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 当期数据库记录-批量删除


**接口地址**:`/api/database/localDatabase/deleteBatch`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ids||query|true|string||


# 辐照标准


## 辐照标准-编辑


**接口地址**:`/api/database/irradiationStandards/edit`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|irradiationStandards|辐照标准|body|true|IrradiationStandards|IrradiationStandards|
|id|主键||false|string||
|name|标准名称||false|string||
|code|标准代码||false|string||
|environmentalTemperature|辐照环境温度||false|string||
|annealTimes|退火时长||false|string||
|roomAnnealTemperature|室温退火温度||false|string||
|acceleratedAnnealTemperature|加速退火温度||false|string||
|radiationSource|辐射源||false|string||
|conditionA|条件A||false|string||
|conditionB|条件B||false|string||
|conditionC|条件C||false|string||
|conditionD|条件D||false|string||
|conditionE|条件E||false|string||
|conditionF|条件F||false|string||


## 辐照标准-编辑


**接口地址**:`/api/database/irradiationStandards/edit`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|irradiationStandards|辐照标准|body|true|IrradiationStandards|IrradiationStandards|
|id|主键||false|string||
|name|标准名称||false|string||
|code|标准代码||false|string||
|environmentalTemperature|辐照环境温度||false|string||
|annealTimes|退火时长||false|string||
|roomAnnealTemperature|室温退火温度||false|string||
|acceleratedAnnealTemperature|加速退火温度||false|string||
|radiationSource|辐射源||false|string||
|conditionA|条件A||false|string||
|conditionB|条件B||false|string||
|conditionC|条件C||false|string||
|conditionD|条件D||false|string||
|conditionE|条件E||false|string||
|conditionF|条件F||false|string||


## 辐照标准-添加


**接口地址**:`/api/database/irradiationStandards/add`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|irradiationStandards|辐照标准|body|true|IrradiationStandards|IrradiationStandards|
|id|主键||false|string||
|name|标准名称||false|string||
|code|标准代码||false|string||
|environmentalTemperature|辐照环境温度||false|string||
|annealTimes|退火时长||false|string||
|roomAnnealTemperature|室温退火温度||false|string||
|acceleratedAnnealTemperature|加速退火温度||false|string||
|radiationSource|辐射源||false|string||
|conditionA|条件A||false|string||
|conditionB|条件B||false|string||
|conditionC|条件C||false|string||
|conditionD|条件D||false|string||
|conditionE|条件E||false|string||
|conditionF|条件F||false|string||


## 辐照标准-通过id查询


**接口地址**:`/api/database/irradiationStandards/queryById`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 辐照标准-分页列表查询


**接口地址**:`/api/database/irradiationStandards/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|irradiationStandards|辐照标准|query|true|IrradiationStandards|IrradiationStandards|
|id|主键||false|string||
|name|标准名称||false|string||
|code|标准代码||false|string||
|environmentalTemperature|辐照环境温度||false|string||
|annealTimes|退火时长||false|string||
|roomAnnealTemperature|室温退火温度||false|string||
|acceleratedAnnealTemperature|加速退火温度||false|string||
|radiationSource|辐射源||false|string||
|conditionA|条件A||false|string||
|conditionB|条件B||false|string||
|conditionC|条件C||false|string||
|conditionD|条件D||false|string||
|conditionE|条件E||false|string||
|conditionF|条件F||false|string||
|pageNo||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||


## 辐照标准-通过id删除


**接口地址**:`/api/database/irradiationStandards/delete`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 辐照标准-批量删除


**接口地址**:`/api/database/irradiationStandards/deleteBatch`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ids||query|true|string||


# 历史一期记录


## 历史一期记录-编辑


**接口地址**:`/api/database/historicalDatabase/edit`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|historicalDatabase|历史一期记录|body|true|HistoricalDatabase|HistoricalDatabase|
|id|主键||false|string||
|dataType|||false|string||
|type|类型||false|string||
|name|名称||false|string||
|clientName|委托方||false|string||
|manufacturer|生产厂家||false|string||
|experimentDate|试验时间||false|string||
|dataSource|数据来源||false|string||
|experimentUser|试验人员||false|string||
|totalCountXhy|西核院统计||false|integer(int32)||
|totalCountLhs|理化所统计||false|integer(int32)||
|dataProvider|数据提供者||false|string||
|dataEntry|数据录入者||false|string||
|deviceMode|器件型号||false|string||
|fileUrl|附件||false|string||
|description|描述||false|string||


## 历史一期记录-编辑


**接口地址**:`/api/database/historicalDatabase/edit`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|historicalDatabase|历史一期记录|body|true|HistoricalDatabase|HistoricalDatabase|
|id|主键||false|string||
|dataType|||false|string||
|type|类型||false|string||
|name|名称||false|string||
|clientName|委托方||false|string||
|manufacturer|生产厂家||false|string||
|experimentDate|试验时间||false|string||
|dataSource|数据来源||false|string||
|experimentUser|试验人员||false|string||
|totalCountXhy|西核院统计||false|integer(int32)||
|totalCountLhs|理化所统计||false|integer(int32)||
|dataProvider|数据提供者||false|string||
|dataEntry|数据录入者||false|string||
|deviceMode|器件型号||false|string||
|fileUrl|附件||false|string||
|description|描述||false|string||


## 历史一期记录-添加


**接口地址**:`/api/database/historicalDatabase/add`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|historicalDatabase|历史一期记录|body|true|HistoricalDatabase|HistoricalDatabase|
|id|主键||false|string||
|dataType|||false|string||
|type|类型||false|string||
|name|名称||false|string||
|clientName|委托方||false|string||
|manufacturer|生产厂家||false|string||
|experimentDate|试验时间||false|string||
|dataSource|数据来源||false|string||
|experimentUser|试验人员||false|string||
|totalCountXhy|西核院统计||false|integer(int32)||
|totalCountLhs|理化所统计||false|integer(int32)||
|dataProvider|数据提供者||false|string||
|dataEntry|数据录入者||false|string||
|deviceMode|器件型号||false|string||
|fileUrl|附件||false|string||
|description|描述||false|string||


## 历史数据统计


**接口地址**:`/api/database/historicalDatabase/statistics`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


暂无


## 历史一期记录-通过id查询


**接口地址**:`/api/database/historicalDatabase/queryById`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 历史一期记录-分页列表查询


**接口地址**:`/api/database/historicalDatabase/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|historicalDatabase|历史一期记录|query|true|HistoricalDatabase|HistoricalDatabase|
|id|主键||false|string||
|dataType|||false|string||
|type|类型||false|string||
|name|名称||false|string||
|clientName|委托方||false|string||
|manufacturer|生产厂家||false|string||
|experimentDate|试验时间||false|string||
|dataSource|数据来源||false|string||
|experimentUser|试验人员||false|string||
|totalCountXhy|西核院统计||false|integer(int32)||
|totalCountLhs|理化所统计||false|integer(int32)||
|dataProvider|数据提供者||false|string||
|dataEntry|数据录入者||false|string||
|deviceMode|器件型号||false|string||
|fileUrl|附件||false|string||
|description|描述||false|string||
|pageNo||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||


## 历史一期记录-通过id删除


**接口地址**:`/api/database/historicalDatabase/delete`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 历史一期记录-批量删除


**接口地址**:`/api/database/historicalDatabase/deleteBatch`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ids||query|true|string||


# 试验人员


## 试验人员-编辑


**接口地址**:`/api/database/experimentUser/edit`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentUser|试验人员|body|true|ExperimentUser|ExperimentUser|
|id|主键||false|string||
|experimentId|试验ID||false|string||
|userId|人员||false|string||
|userName|||false|string||
|type|类型||false|string||


## 试验人员-编辑


**接口地址**:`/api/database/experimentUser/edit`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentUser|试验人员|body|true|ExperimentUser|ExperimentUser|
|id|主键||false|string||
|experimentId|试验ID||false|string||
|userId|人员||false|string||
|userName|||false|string||
|type|类型||false|string||


## 试验人员-批量添加


**接口地址**:`/api/database/experimentUser/batchAdd`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentUsers|试验人员|body|true|array|ExperimentUser|
|id|主键||false|string||
|experimentId|试验ID||false|string||
|userId|人员||false|string||
|userName|||false|string||
|type|类型||false|string||


## 试验人员-添加


**接口地址**:`/api/database/experimentUser/add`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentUser|试验人员|body|true|ExperimentUser|ExperimentUser|
|id|主键||false|string||
|experimentId|试验ID||false|string||
|userId|人员||false|string||
|userName|||false|string||
|type|类型||false|string||


## 试验人员-通过id查询


**接口地址**:`/api/database/experimentUser/queryById`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 测试过程-通过实验ID查询


**接口地址**:`/api/database/experimentUser/queryByExperimentId`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentId||query|true|string||


## 试验人员-分页列表查询


**接口地址**:`/api/database/experimentUser/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentUser|试验人员|query|true|ExperimentUser|ExperimentUser|
|id|主键||false|string||
|experimentId|试验ID||false|string||
|userId|人员||false|string||
|userName|||false|string||
|type|类型||false|string||
|pageNo||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||


## 试验人员-通过id删除


**接口地址**:`/api/database/experimentUser/delete`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 试验人员-批量删除


**接口地址**:`/api/database/experimentUser/deleteBatch`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ids||query|true|string||


# 测试过程


## 测试过程-编辑


**接口地址**:`/api/database/experimentTestProcess/edit`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentTestProcess|测试过程|body|true|ExperimentTestProcess|ExperimentTestProcess|
|id|主键||false|string||
|radiationSource| 辐照源||false|string||
|measurementRate|计量率||false|string||
|radiationStandard|辐照标准||false|string||
|environmentalTemperature|环境温度||false|string||
|comment|备注||false|string||
|exceptionRecord|异常记录||false|string||
|radiationDetail|辐照详情（json存储）||false|string||
|experimentId|试验ID||false|string||
|testStartTime|测试开始时间||false|string(date-time)||
|testEndTime|测试结束时间||false|string(date-time)||
|environmentalHumidity|环境湿度||false|string||
|testParameters|测试参数(json 大字段)||false|string||
|testEquipment|测试设备(json 大字段)||false|string||
|testEquipmentList|||false|array|TestEquipmentDTO|
|sampleModel|||false|string||
|equipmentModel|||false|string||
|managementNo|||false|string||
|lifespan|||false|string||
|equipmentName|||false|string||
|roomNo|||false|string||
|testMeteringPoint|测试计量点||false|string||
|testMeteringPointList|||false|array|TestMeteringPointDTO|
|point|||false|string||
|startTime|||false|string||
|endTime|||false|string||
|result|||false|string||
|testFluencePoint|测试注量点||false|string||
|testFluencePointList|||false|array|TestFluencePointDTO|
|point|||false|string||
|startTime|||false|string||
|endTime|||false|string||
|result|||false|string||
|annealingDuration|退火时长||false|string||
|annealingDurationList|||false|array|TestAnnealingDurationDTO|
|point|||false|string||
|startTime|||false|string||
|endTime|||false|string||
|result|||false|string||
|testData|测试数据(json 大字段)||false|string||
|testResult|测试结果||false|string||
|sampleInfo|样品信息||false|string||
|sampleInfoList|试验样品信息||false|array|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|testStandardsId|测试标准||false|string||
|testStandards|测试标准||false|TestStandards|TestStandards|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|name|标准名称||false|string||
|code|标准代码||false|string||
|content|内容||false|string||
|attachment|附件||false|string||
|temperatureRequirements|温度要求||false|string||
|humidityRequirements|湿度要求||false|string||


## 测试过程-编辑


**接口地址**:`/api/database/experimentTestProcess/edit`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentTestProcess|测试过程|body|true|ExperimentTestProcess|ExperimentTestProcess|
|id|主键||false|string||
|radiationSource| 辐照源||false|string||
|measurementRate|计量率||false|string||
|radiationStandard|辐照标准||false|string||
|environmentalTemperature|环境温度||false|string||
|comment|备注||false|string||
|exceptionRecord|异常记录||false|string||
|radiationDetail|辐照详情（json存储）||false|string||
|experimentId|试验ID||false|string||
|testStartTime|测试开始时间||false|string(date-time)||
|testEndTime|测试结束时间||false|string(date-time)||
|environmentalHumidity|环境湿度||false|string||
|testParameters|测试参数(json 大字段)||false|string||
|testEquipment|测试设备(json 大字段)||false|string||
|testEquipmentList|||false|array|TestEquipmentDTO|
|sampleModel|||false|string||
|equipmentModel|||false|string||
|managementNo|||false|string||
|lifespan|||false|string||
|equipmentName|||false|string||
|roomNo|||false|string||
|testMeteringPoint|测试计量点||false|string||
|testMeteringPointList|||false|array|TestMeteringPointDTO|
|point|||false|string||
|startTime|||false|string||
|endTime|||false|string||
|result|||false|string||
|testFluencePoint|测试注量点||false|string||
|testFluencePointList|||false|array|TestFluencePointDTO|
|point|||false|string||
|startTime|||false|string||
|endTime|||false|string||
|result|||false|string||
|annealingDuration|退火时长||false|string||
|annealingDurationList|||false|array|TestAnnealingDurationDTO|
|point|||false|string||
|startTime|||false|string||
|endTime|||false|string||
|result|||false|string||
|testData|测试数据(json 大字段)||false|string||
|testResult|测试结果||false|string||
|sampleInfo|样品信息||false|string||
|sampleInfoList|试验样品信息||false|array|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|testStandardsId|测试标准||false|string||
|testStandards|测试标准||false|TestStandards|TestStandards|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|name|标准名称||false|string||
|code|标准代码||false|string||
|content|内容||false|string||
|attachment|附件||false|string||
|temperatureRequirements|温度要求||false|string||
|humidityRequirements|湿度要求||false|string||


## 测试过程-添加


**接口地址**:`/api/database/experimentTestProcess/add`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentTestProcess|测试过程|body|true|ExperimentTestProcess|ExperimentTestProcess|
|id|主键||false|string||
|radiationSource| 辐照源||false|string||
|measurementRate|计量率||false|string||
|radiationStandard|辐照标准||false|string||
|environmentalTemperature|环境温度||false|string||
|comment|备注||false|string||
|exceptionRecord|异常记录||false|string||
|radiationDetail|辐照详情（json存储）||false|string||
|experimentId|试验ID||false|string||
|testStartTime|测试开始时间||false|string(date-time)||
|testEndTime|测试结束时间||false|string(date-time)||
|environmentalHumidity|环境湿度||false|string||
|testParameters|测试参数(json 大字段)||false|string||
|testEquipment|测试设备(json 大字段)||false|string||
|testEquipmentList|||false|array|TestEquipmentDTO|
|sampleModel|||false|string||
|equipmentModel|||false|string||
|managementNo|||false|string||
|lifespan|||false|string||
|equipmentName|||false|string||
|roomNo|||false|string||
|testMeteringPoint|测试计量点||false|string||
|testMeteringPointList|||false|array|TestMeteringPointDTO|
|point|||false|string||
|startTime|||false|string||
|endTime|||false|string||
|result|||false|string||
|testFluencePoint|测试注量点||false|string||
|testFluencePointList|||false|array|TestFluencePointDTO|
|point|||false|string||
|startTime|||false|string||
|endTime|||false|string||
|result|||false|string||
|annealingDuration|退火时长||false|string||
|annealingDurationList|||false|array|TestAnnealingDurationDTO|
|point|||false|string||
|startTime|||false|string||
|endTime|||false|string||
|result|||false|string||
|testData|测试数据(json 大字段)||false|string||
|testResult|测试结果||false|string||
|sampleInfo|样品信息||false|string||
|sampleInfoList|试验样品信息||false|array|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|testStandardsId|测试标准||false|string||
|testStandards|测试标准||false|TestStandards|TestStandards|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|name|标准名称||false|string||
|code|标准代码||false|string||
|content|内容||false|string||
|attachment|附件||false|string||
|temperatureRequirements|温度要求||false|string||
|humidityRequirements|湿度要求||false|string||


## 测试过程-通过id查询


**接口地址**:`/api/database/experimentTestProcess/queryById`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 测试过程-通过实验ID查询


**接口地址**:`/api/database/experimentTestProcess/queryByExperimentId`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentId||query|true|string||


## 测试过程-分页列表查询


**接口地址**:`/api/database/experimentTestProcess/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentTestProcess|测试过程|query|true|ExperimentTestProcess|ExperimentTestProcess|
|id|主键||false|string||
|radiationSource| 辐照源||false|string||
|measurementRate|计量率||false|string||
|radiationStandard|辐照标准||false|string||
|environmentalTemperature|环境温度||false|string||
|comment|备注||false|string||
|exceptionRecord|异常记录||false|string||
|radiationDetail|辐照详情（json存储）||false|string||
|experimentId|试验ID||false|string||
|testStartTime|测试开始时间||false|string(date-time)||
|testEndTime|测试结束时间||false|string(date-time)||
|environmentalHumidity|环境湿度||false|string||
|testParameters|测试参数(json 大字段)||false|string||
|testEquipment|测试设备(json 大字段)||false|string||
|testEquipmentList|||false|array|TestEquipmentDTO|
|sampleModel|||false|string||
|equipmentModel|||false|string||
|managementNo|||false|string||
|lifespan|||false|string||
|equipmentName|||false|string||
|roomNo|||false|string||
|testMeteringPoint|测试计量点||false|string||
|testMeteringPointList|||false|array|TestMeteringPointDTO|
|point|||false|string||
|startTime|||false|string||
|endTime|||false|string||
|result|||false|string||
|testFluencePoint|测试注量点||false|string||
|testFluencePointList|||false|array|TestFluencePointDTO|
|point|||false|string||
|startTime|||false|string||
|endTime|||false|string||
|result|||false|string||
|annealingDuration|退火时长||false|string||
|annealingDurationList|||false|array|TestAnnealingDurationDTO|
|point|||false|string||
|startTime|||false|string||
|endTime|||false|string||
|result|||false|string||
|testData|测试数据(json 大字段)||false|string||
|testResult|测试结果||false|string||
|sampleInfo|样品信息||false|string||
|sampleInfoList|试验样品信息||false|array|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|testStandardsId|测试标准||false|string||
|testStandards|测试标准||false|TestStandards|TestStandards|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|name|标准名称||false|string||
|code|标准代码||false|string||
|content|内容||false|string||
|attachment|附件||false|string||
|temperatureRequirements|温度要求||false|string||
|humidityRequirements|湿度要求||false|string||
|pageNo||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||


## 测试过程-通过id删除


**接口地址**:`/api/database/experimentTestProcess/delete`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 测试过程-批量删除


**接口地址**:`/api/database/experimentTestProcess/deleteBatch`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ids||query|true|string||


# 试验样品信息


## 试验样品信息-编辑


**接口地址**:`/api/database/experimentSampleInfo/edit`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentSampleInfo|试验样品信息|body|true|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||


## 试验样品信息-编辑


**接口地址**:`/api/database/experimentSampleInfo/edit`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentSampleInfo|试验样品信息|body|true|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||


## 试验样品信息-添加


**接口地址**:`/api/database/experimentSampleInfo/add`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentSampleInfo|试验样品信息|body|true|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||


## 试验样品信息-列表查询


**接口地址**:`/api/database/experimentSampleInfo/search`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentSampleInfo|试验样品信息|query|true|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||


## 试验样品信息-通过id查询


**接口地址**:`/api/database/experimentSampleInfo/queryById`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 试验样品信息-分页列表查询


**接口地址**:`/api/database/experimentSampleInfo/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentSampleInfo|试验样品信息|query|true|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|pageNo||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||


## 试验样品信息-分页列表名称型号


**接口地址**:`/api/database/experimentSampleInfo/listSampleName`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


暂无


## 试验样品信息-列表查询样品型号


**接口地址**:`/api/database/experimentSampleInfo/listSampleModel`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


暂无


## 试验样品信息-列表查询样品批次


**接口地址**:`/api/database/experimentSampleInfo/listSampleBatch`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


暂无


## 试验样品信息-通过id删除


**接口地址**:`/api/database/experimentSampleInfo/delete`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 试验样品信息-批量删除


**接口地址**:`/api/database/experimentSampleInfo/deleteBatch`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ids||query|true|string||


# 试验评定规则


## 试验评定规则-编辑


**接口地址**:`/api/database/experimentReviewRule/edit`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentReviewRule|试验评定规则|body|true|ExperimentReviewRule|ExperimentReviewRule|
|id|id||false|string||
|processType|试验流程标准||false|string||
|title|建议标题||false|string||
|result|评定结果||false|string||
|impactDesc|损伤机理||false|string||
|impactScope|适用范围||false|string||
|expression|规则表达式||false|string||
|priority|规则优先级||false|integer(int32)||


## 试验评定规则-编辑


**接口地址**:`/api/database/experimentReviewRule/edit`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentReviewRule|试验评定规则|body|true|ExperimentReviewRule|ExperimentReviewRule|
|id|id||false|string||
|processType|试验流程标准||false|string||
|title|建议标题||false|string||
|result|评定结果||false|string||
|impactDesc|损伤机理||false|string||
|impactScope|适用范围||false|string||
|expression|规则表达式||false|string||
|priority|规则优先级||false|integer(int32)||


## 试验评定规则-添加


**接口地址**:`/api/database/experimentReviewRule/add`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentReviewRule|试验评定规则|body|true|ExperimentReviewRule|ExperimentReviewRule|
|id|id||false|string||
|processType|试验流程标准||false|string||
|title|建议标题||false|string||
|result|评定结果||false|string||
|impactDesc|损伤机理||false|string||
|impactScope|适用范围||false|string||
|expression|规则表达式||false|string||
|priority|规则优先级||false|integer(int32)||


## 试验评定规则-通过id查询


**接口地址**:`/api/database/experimentReviewRule/queryById`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 试验评定规则-分页列表查询


**接口地址**:`/api/database/experimentReviewRule/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentReviewRule|试验评定规则|query|true|ExperimentReviewRule|ExperimentReviewRule|
|id|id||false|string||
|processType|试验流程标准||false|string||
|title|建议标题||false|string||
|result|评定结果||false|string||
|impactDesc|损伤机理||false|string||
|impactScope|适用范围||false|string||
|expression|规则表达式||false|string||
|priority|规则优先级||false|integer(int32)||
|pageNo||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||


## 试验评定规则-通过id删除


**接口地址**:`/api/database/experimentReviewRule/delete`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 试验评定规则-批量删除


**接口地址**:`/api/database/experimentReviewRule/deleteBatch`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ids||query|true|string||


# 试验评定真值表


## 试验评定真值表-编辑


**接口地址**:`/api/database/experimentReviewDetail/edit`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentReviewDetail|试验评定真值表|body|true|ExperimentReviewDetail|ExperimentReviewDetail|
|id|id||false|string||
|experimentId|试验ID||false|string||
|experimentName|||false|string||
|sampleId|样品ID||false|string||
|sampleName|||false|string||
|processType|试验流程标准||false|string||
|ruleId|规则ID||false|string||
|priority|规则优先级||false|integer(int32)||
|result|评定结果||false|string||
|reviewStatus|评定状态||false|string||


## 试验评定真值表-编辑


**接口地址**:`/api/database/experimentReviewDetail/edit`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentReviewDetail|试验评定真值表|body|true|ExperimentReviewDetail|ExperimentReviewDetail|
|id|id||false|string||
|experimentId|试验ID||false|string||
|experimentName|||false|string||
|sampleId|样品ID||false|string||
|sampleName|||false|string||
|processType|试验流程标准||false|string||
|ruleId|规则ID||false|string||
|priority|规则优先级||false|integer(int32)||
|result|评定结果||false|string||
|reviewStatus|评定状态||false|string||


## 试验评定真值表-添加


**接口地址**:`/api/database/experimentReviewDetail/add`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentReviewDetail|试验评定真值表|body|true|ExperimentReviewDetail|ExperimentReviewDetail|
|id|id||false|string||
|experimentId|试验ID||false|string||
|experimentName|||false|string||
|sampleId|样品ID||false|string||
|sampleName|||false|string||
|processType|试验流程标准||false|string||
|ruleId|规则ID||false|string||
|priority|规则优先级||false|integer(int32)||
|result|评定结果||false|string||
|reviewStatus|评定状态||false|string||


## 试验评定真值表-通过id查询


**接口地址**:`/api/database/experimentReviewDetail/queryById`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 试验评定真值表-分页列表查询


**接口地址**:`/api/database/experimentReviewDetail/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentReviewDetail|试验评定真值表|query|true|ExperimentReviewDetail|ExperimentReviewDetail|
|id|id||false|string||
|experimentId|试验ID||false|string||
|experimentName|||false|string||
|sampleId|样品ID||false|string||
|sampleName|||false|string||
|processType|试验流程标准||false|string||
|ruleId|规则ID||false|string||
|priority|规则优先级||false|integer(int32)||
|result|评定结果||false|string||
|reviewStatus|评定状态||false|string||
|pageNo||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||


## 试验评定真值表-通过id删除


**接口地址**:`/api/database/experimentReviewDetail/delete`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 试验评定真值表-批量删除


**接口地址**:`/api/database/experimentReviewDetail/deleteBatch`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ids||query|true|string||


# 试验评定结果


## 试验评定结果-编辑


**接口地址**:`/api/database/experimentReview/edit`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentReview|试验评定结果|body|true|ExperimentReview|ExperimentReview|
|id|id||false|string||
|experimentId|试验ID||false|string||
|experimentName|||false|string||
|sampleId|样品ID||false|string||
|sampleName|||false|string||
|processType|试验流程标准,可用值:GJB548C,ESCC229000,ESCC22500,QJ10004||false|string||
|needAnneal|是否需要退火,可用值:Y,N||false|string||
|targetedAnneal|是否进行了针对性的退火表征试验,可用值:Y,N||false|string||
|needRadiation|是否需要辐照,可用值:Y,N||false|string||
|needBurnin|是否需要老练,可用值:Y,N||false|string||
|result|评定结果||false|string||
|reviewStatus|评定状态||false|string||
|matchedRuleList|试验评定规则||false|array|ExperimentReviewRule|
|id|id||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|processType|试验流程标准||false|string||
|title|建议标题||false|string||
|result|评定结果||false|string||
|impactDesc|损伤机理||false|string||
|impactScope|适用范围||false|string||
|expression|规则表达式||false|string||
|priority|规则优先级||false|integer||


## 试验评定结果-编辑


**接口地址**:`/api/database/experimentReview/edit`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentReview|试验评定结果|body|true|ExperimentReview|ExperimentReview|
|id|id||false|string||
|experimentId|试验ID||false|string||
|experimentName|||false|string||
|sampleId|样品ID||false|string||
|sampleName|||false|string||
|processType|试验流程标准,可用值:GJB548C,ESCC229000,ESCC22500,QJ10004||false|string||
|needAnneal|是否需要退火,可用值:Y,N||false|string||
|targetedAnneal|是否进行了针对性的退火表征试验,可用值:Y,N||false|string||
|needRadiation|是否需要辐照,可用值:Y,N||false|string||
|needBurnin|是否需要老练,可用值:Y,N||false|string||
|result|评定结果||false|string||
|reviewStatus|评定状态||false|string||
|matchedRuleList|试验评定规则||false|array|ExperimentReviewRule|
|id|id||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|processType|试验流程标准||false|string||
|title|建议标题||false|string||
|result|评定结果||false|string||
|impactDesc|损伤机理||false|string||
|impactScope|适用范围||false|string||
|expression|规则表达式||false|string||
|priority|规则优先级||false|integer||


## 试验评定结果-添加


**接口地址**:`/api/database/experimentReview/add`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentReview|试验评定结果|body|true|ExperimentReview|ExperimentReview|
|id|id||false|string||
|experimentId|试验ID||false|string||
|experimentName|||false|string||
|sampleId|样品ID||false|string||
|sampleName|||false|string||
|processType|试验流程标准,可用值:GJB548C,ESCC229000,ESCC22500,QJ10004||false|string||
|needAnneal|是否需要退火,可用值:Y,N||false|string||
|targetedAnneal|是否进行了针对性的退火表征试验,可用值:Y,N||false|string||
|needRadiation|是否需要辐照,可用值:Y,N||false|string||
|needBurnin|是否需要老练,可用值:Y,N||false|string||
|result|评定结果||false|string||
|reviewStatus|评定状态||false|string||
|matchedRuleList|试验评定规则||false|array|ExperimentReviewRule|
|id|id||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|processType|试验流程标准||false|string||
|title|建议标题||false|string||
|result|评定结果||false|string||
|impactDesc|损伤机理||false|string||
|impactScope|适用范围||false|string||
|expression|规则表达式||false|string||
|priority|规则优先级||false|integer||


## 试验评定结果-通过id查询


**接口地址**:`/api/database/experimentReview/queryById`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 试验评定结果-通过试验id查询


**接口地址**:`/api/database/experimentReview/queryByExperimentId`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentId||query|true|string||
|sampleId||query|false|string||


## 试验评定列表-分页列表查询


**接口地址**:`/api/database/experimentReview/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experiment|试验管理|query|true|Experiment|Experiment|
|id|主键||false|string||
|indexNo|序号||false|integer(int32)||
|experimentNo|试验编号||false|string||
|name|名称||false|string||
|clientName|委托方名称||false|string||
|type|试验类型||false|string||
|startDate|试验开始日期||false|string||
|endDate|试验结束日期||false|string||
|radiationSourceType|辐射源类型||false|string||
|supervisor|||false|string||
|supervisorName|||false|string||
|status|状态||false|string||
|statusList|||false|array|string|
|sampleInfoList|试验样品信息||false|array|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|irradiationBoardList|试验辐照板信息||false|array|ExperimentIrradiationBoard|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleId|样品ID||false|string||
|sampleInfo|试验样品信息||false|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|sampleName|样品名称||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|irradiationBoardNumber|辐照板数量||false|string||
|irradiationBoardCode|辐照板编号||false|string||
|measurementValidity|计量有效期||false|string||
|sourceArea|占源面积||false|string||
|irradiationBoardImage|辐照板图片||false|string||
|deviationConditionList|试验偏置条件信息||false|array|ExperimentDeviationCondition|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleId|样品ID||false|string||
|sampleInfo|试验样品信息||false|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|sampleName|样品名称||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleNumber|样品编号||false|string||
|offsetCondition|偏置条件||false|string||
|offsetVoltage|偏置电压||false|string||
|offsetCurrent|偏置电流||false|string||
|loadCapacity|负载||false|string||
|otherCondition|其它条件||false|string||
|offsetPowerPhoto|偏置电源显示界面照片||false|string||
|inputSignal|输入信号||false|string||
|signalPhoto|信号显示界面源照片||false|string||
|pinType|管脚接入方式||false|string||
|signalType|信号类型||false|string||
|signalFrequency|信号频率||false|string||
|signalAmplitude|信号幅值||false|string||
|dutyCycle|占空比||false|string||
|deviationEquipmentList|试验加偏设备信息||false|array|ExperimentDeviationEquipment|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleId|样品ID||false|string||
|sampleInfo|试验样品信息||false|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|sampleName|样品名称||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|equipmentModel|设备型号||false|string||
|equipmentId|设备名称||false|string||
|equipmentName|设备名称||false|string||
|equipment|设备||false|Equipment|Equipment|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|name|名称||false|string||
|model|型号||false|string||
|factoryNo|出厂编号||false|string||
|managementNo|管理编号||false|string||
|expireDate|有效期||false|string||
|roomNo|房间号||false|string||
|images|图片||false|string||
|equipmentType|设备类型||false|string||
|equipmentImage|设备图片||false|string||
|measurementValidity|计量有效期||false|string||
|roomNo|房间号||false|string||
|factoryNo|出厂编号||false|string||
|managementNo|管理编号||false|string||
|copyCount|复制次数||false|integer(int32)||
|fileList|试验文件||false|array|ExperimentFile|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|fileName|文件名称||false|string||
|fileType|类型||false|string||
|fileUrl|文件链接||false|string||
|experimentUser|||false|string||
|experimentUserList|试验人员||false|array|ExperimentUser|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|userId|人员||false|string||
|userName|||false|string||
|type|类型||false|string||
|experimentReportList|试验报告||false|array|ExperimentReport|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleInfo|样品信息||false|string||
|imgUrls|偏置原理图||false|string||
|description|偏置原理说明||false|string||
|auditor|审核员||false|string||
|proofreader|校对员||false|string||
|confirmer|确认员||false|string||
|reportName|报告名称||false|string||
|status|状态(申请中待校对:INIT,不合格:REJECT ,校对通过待审核：PROOFREAD_PASS,审核通过待批准：AUDIT_PASS，批准通过：PASSED)||false|string||
|auditorMemo|审批意见||false|string||
|proofreaderMemo|校对意见||false|string||
|confirmerMemo|批准意见||false|string||
|auditorTime|审批时间||false|string||
|proofreaderTime|校对时间||false|string||
|confirmerTime|批准时间||false|string||
|fileUrl|生成的文件路径||false|string||
|memo|||false|string||
|experimentRatingList|试验评分||false|array|ExperimentRating|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|实验ID||false|string||
|rateUserId|评分人||false|string||
|totalScore|总分数||false|string||
|baseScore|基础分||false|string||
|completionScore|完成分||false|string||
|difficultyScore|难度分||false|string||
|managementScore|管理支撑分||false|string||
|experimentName|||false|string||
|experimentNo|||false|string||
|rateUserName|||false|string||
|rateUserList|试验评分||false|array|ExperimentRatingUserDTO|
|rateUserId|评分人||false|string||
|totalScore|总分数||false|string||
|baseScore|基础分||false|string||
|completionScore|完成分||false|string||
|difficultyScore|难度分||false|string||
|managementScore|管理支撑分||false|string||
|sampleType|||false|string||
|sampleName|||false|string||
|equipmentModel|||false|string||
|sampleModel|||false|string||
|totalCountXhy|西核院统计||false|integer(int32)||
|totalCountLhs|理化所统计||false|integer(int32)||
|reportStatus|理化所统计||false|string||
|pageName|试验页面||false|string||
|radiationStandard|辐照标准||false|string||
|experimentReview|试验评定结果||false|ExperimentReview|ExperimentReview|
|id|id||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|experimentName|||false|string||
|sampleId|样品ID||false|string||
|sampleName|||false|string||
|processType|试验流程标准,可用值:GJB548C,ESCC229000,ESCC22500,QJ10004||false|string||
|needAnneal|是否需要退火,可用值:Y,N||false|string||
|targetedAnneal|是否进行了针对性的退火表征试验,可用值:Y,N||false|string||
|needRadiation|是否需要辐照,可用值:Y,N||false|string||
|needBurnin|是否需要老练,可用值:Y,N||false|string||
|result|评定结果||false|string||
|reviewStatus|评定状态||false|string||
|matchedRuleList|试验评定规则||false|array|ExperimentReviewRule|
|id|id||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|processType|试验流程标准||false|string||
|title|建议标题||false|string||
|result|评定结果||false|string||
|impactDesc|损伤机理||false|string||
|impactScope|适用范围||false|string||
|expression|规则表达式||false|string||
|priority|规则优先级||false|integer||
|experimentReviewList|||false|array|ExperimentReviewDTO|
|experimentId|||false|string||
|sampleName|||false|string||
|sampleId|||false|string||
|processType|||false|string||
|reviewResult|||false|string||
|pageNo||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||


## 试验评定结果-通过id删除


**接口地址**:`/api/database/experimentReview/delete`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 试验评定结果-批量删除


**接口地址**:`/api/database/experimentReview/deleteBatch`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ids||query|true|string||


# 试验报告


## 试验报告-更新文件


**接口地址**:`/api/database/experimentReport/updateFile`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentReport|试验报告|body|true|ExperimentReport|ExperimentReport|
|id|主键||false|string||
|experimentId|试验ID||false|string||
|sampleInfo|样品信息||false|string||
|imgUrls|偏置原理图||false|string||
|description|偏置原理说明||false|string||
|auditor|审核员||false|string||
|proofreader|校对员||false|string||
|confirmer|确认员||false|string||
|reportName|报告名称||false|string||
|status|状态(申请中待校对:INIT,不合格:REJECT ,校对通过待审核：PROOFREAD_PASS,审核通过待批准：AUDIT_PASS，批准通过：PASSED)||false|string||
|auditorMemo|审批意见||false|string||
|proofreaderMemo|校对意见||false|string||
|confirmerMemo|批准意见||false|string||
|auditorTime|审批时间||false|string||
|proofreaderTime|校对时间||false|string||
|confirmerTime|批准时间||false|string||
|fileUrl|生成的文件路径||false|string||
|memo|||false|string||


## 试验报告-更新文件


**接口地址**:`/api/database/experimentReport/updateFile`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentReport|试验报告|body|true|ExperimentReport|ExperimentReport|
|id|主键||false|string||
|experimentId|试验ID||false|string||
|sampleInfo|样品信息||false|string||
|imgUrls|偏置原理图||false|string||
|description|偏置原理说明||false|string||
|auditor|审核员||false|string||
|proofreader|校对员||false|string||
|confirmer|确认员||false|string||
|reportName|报告名称||false|string||
|status|状态(申请中待校对:INIT,不合格:REJECT ,校对通过待审核：PROOFREAD_PASS,审核通过待批准：AUDIT_PASS，批准通过：PASSED)||false|string||
|auditorMemo|审批意见||false|string||
|proofreaderMemo|校对意见||false|string||
|confirmerMemo|批准意见||false|string||
|auditorTime|审批时间||false|string||
|proofreaderTime|校对时间||false|string||
|confirmerTime|批准时间||false|string||
|fileUrl|生成的文件路径||false|string||
|memo|||false|string||


## 试验报告-编辑


**接口地址**:`/api/database/experimentReport/edit`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentReport|试验报告|body|true|ExperimentReport|ExperimentReport|
|id|主键||false|string||
|experimentId|试验ID||false|string||
|sampleInfo|样品信息||false|string||
|imgUrls|偏置原理图||false|string||
|description|偏置原理说明||false|string||
|auditor|审核员||false|string||
|proofreader|校对员||false|string||
|confirmer|确认员||false|string||
|reportName|报告名称||false|string||
|status|状态(申请中待校对:INIT,不合格:REJECT ,校对通过待审核：PROOFREAD_PASS,审核通过待批准：AUDIT_PASS，批准通过：PASSED)||false|string||
|auditorMemo|审批意见||false|string||
|proofreaderMemo|校对意见||false|string||
|confirmerMemo|批准意见||false|string||
|auditorTime|审批时间||false|string||
|proofreaderTime|校对时间||false|string||
|confirmerTime|批准时间||false|string||
|fileUrl|生成的文件路径||false|string||
|memo|||false|string||


## 试验报告-编辑


**接口地址**:`/api/database/experimentReport/edit`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentReport|试验报告|body|true|ExperimentReport|ExperimentReport|
|id|主键||false|string||
|experimentId|试验ID||false|string||
|sampleInfo|样品信息||false|string||
|imgUrls|偏置原理图||false|string||
|description|偏置原理说明||false|string||
|auditor|审核员||false|string||
|proofreader|校对员||false|string||
|confirmer|确认员||false|string||
|reportName|报告名称||false|string||
|status|状态(申请中待校对:INIT,不合格:REJECT ,校对通过待审核：PROOFREAD_PASS,审核通过待批准：AUDIT_PASS，批准通过：PASSED)||false|string||
|auditorMemo|审批意见||false|string||
|proofreaderMemo|校对意见||false|string||
|confirmerMemo|批准意见||false|string||
|auditorTime|审批时间||false|string||
|proofreaderTime|校对时间||false|string||
|confirmerTime|批准时间||false|string||
|fileUrl|生成的文件路径||false|string||
|memo|||false|string||


## 试验报告-撤回


**接口地址**:`/api/database/experimentReport/cancel`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentReport|试验报告|body|true|ExperimentReport|ExperimentReport|
|id|主键||false|string||
|experimentId|试验ID||false|string||
|sampleInfo|样品信息||false|string||
|imgUrls|偏置原理图||false|string||
|description|偏置原理说明||false|string||
|auditor|审核员||false|string||
|proofreader|校对员||false|string||
|confirmer|确认员||false|string||
|reportName|报告名称||false|string||
|status|状态(申请中待校对:INIT,不合格:REJECT ,校对通过待审核：PROOFREAD_PASS,审核通过待批准：AUDIT_PASS，批准通过：PASSED)||false|string||
|auditorMemo|审批意见||false|string||
|proofreaderMemo|校对意见||false|string||
|confirmerMemo|批准意见||false|string||
|auditorTime|审批时间||false|string||
|proofreaderTime|校对时间||false|string||
|confirmerTime|批准时间||false|string||
|fileUrl|生成的文件路径||false|string||
|memo|||false|string||


## 试验报告-撤回


**接口地址**:`/api/database/experimentReport/cancel`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentReport|试验报告|body|true|ExperimentReport|ExperimentReport|
|id|主键||false|string||
|experimentId|试验ID||false|string||
|sampleInfo|样品信息||false|string||
|imgUrls|偏置原理图||false|string||
|description|偏置原理说明||false|string||
|auditor|审核员||false|string||
|proofreader|校对员||false|string||
|confirmer|确认员||false|string||
|reportName|报告名称||false|string||
|status|状态(申请中待校对:INIT,不合格:REJECT ,校对通过待审核：PROOFREAD_PASS,审核通过待批准：AUDIT_PASS，批准通过：PASSED)||false|string||
|auditorMemo|审批意见||false|string||
|proofreaderMemo|校对意见||false|string||
|confirmerMemo|批准意见||false|string||
|auditorTime|审批时间||false|string||
|proofreaderTime|校对时间||false|string||
|confirmerTime|批准时间||false|string||
|fileUrl|生成的文件路径||false|string||
|memo|||false|string||


## 试验报告-审核


**接口地址**:`/api/database/experimentReport/audit`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentReport|试验报告|body|true|ExperimentReport|ExperimentReport|
|id|主键||false|string||
|experimentId|试验ID||false|string||
|sampleInfo|样品信息||false|string||
|imgUrls|偏置原理图||false|string||
|description|偏置原理说明||false|string||
|auditor|审核员||false|string||
|proofreader|校对员||false|string||
|confirmer|确认员||false|string||
|reportName|报告名称||false|string||
|status|状态(申请中待校对:INIT,不合格:REJECT ,校对通过待审核：PROOFREAD_PASS,审核通过待批准：AUDIT_PASS，批准通过：PASSED)||false|string||
|auditorMemo|审批意见||false|string||
|proofreaderMemo|校对意见||false|string||
|confirmerMemo|批准意见||false|string||
|auditorTime|审批时间||false|string||
|proofreaderTime|校对时间||false|string||
|confirmerTime|批准时间||false|string||
|fileUrl|生成的文件路径||false|string||
|memo|||false|string||


## 试验报告-审核


**接口地址**:`/api/database/experimentReport/audit`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentReport|试验报告|body|true|ExperimentReport|ExperimentReport|
|id|主键||false|string||
|experimentId|试验ID||false|string||
|sampleInfo|样品信息||false|string||
|imgUrls|偏置原理图||false|string||
|description|偏置原理说明||false|string||
|auditor|审核员||false|string||
|proofreader|校对员||false|string||
|confirmer|确认员||false|string||
|reportName|报告名称||false|string||
|status|状态(申请中待校对:INIT,不合格:REJECT ,校对通过待审核：PROOFREAD_PASS,审核通过待批准：AUDIT_PASS，批准通过：PASSED)||false|string||
|auditorMemo|审批意见||false|string||
|proofreaderMemo|校对意见||false|string||
|confirmerMemo|批准意见||false|string||
|auditorTime|审批时间||false|string||
|proofreaderTime|校对时间||false|string||
|confirmerTime|批准时间||false|string||
|fileUrl|生成的文件路径||false|string||
|memo|||false|string||


## 试验报告-添加


**接口地址**:`/api/database/experimentReport/add`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentReport|试验报告|body|true|ExperimentReport|ExperimentReport|
|id|主键||false|string||
|experimentId|试验ID||false|string||
|sampleInfo|样品信息||false|string||
|imgUrls|偏置原理图||false|string||
|description|偏置原理说明||false|string||
|auditor|审核员||false|string||
|proofreader|校对员||false|string||
|confirmer|确认员||false|string||
|reportName|报告名称||false|string||
|status|状态(申请中待校对:INIT,不合格:REJECT ,校对通过待审核：PROOFREAD_PASS,审核通过待批准：AUDIT_PASS，批准通过：PASSED)||false|string||
|auditorMemo|审批意见||false|string||
|proofreaderMemo|校对意见||false|string||
|confirmerMemo|批准意见||false|string||
|auditorTime|审批时间||false|string||
|proofreaderTime|校对时间||false|string||
|confirmerTime|批准时间||false|string||
|fileUrl|生成的文件路径||false|string||
|memo|||false|string||


## 试验报告-通过id查询


**接口地址**:`/api/database/experimentReport/queryById`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 试验报告-通过实验ID查询


**接口地址**:`/api/database/experimentReport/queryByExperimentId`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentId||query|true|string||


## 试验报告-分页列表查询


**接口地址**:`/api/database/experimentReport/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentReport|试验报告|query|true|ExperimentReport|ExperimentReport|
|id|主键||false|string||
|experimentId|试验ID||false|string||
|sampleInfo|样品信息||false|string||
|imgUrls|偏置原理图||false|string||
|description|偏置原理说明||false|string||
|auditor|审核员||false|string||
|proofreader|校对员||false|string||
|confirmer|确认员||false|string||
|reportName|报告名称||false|string||
|status|状态(申请中待校对:INIT,不合格:REJECT ,校对通过待审核：PROOFREAD_PASS,审核通过待批准：AUDIT_PASS，批准通过：PASSED)||false|string||
|auditorMemo|审批意见||false|string||
|proofreaderMemo|校对意见||false|string||
|confirmerMemo|批准意见||false|string||
|auditorTime|审批时间||false|string||
|proofreaderTime|校对时间||false|string||
|confirmerTime|批准时间||false|string||
|fileUrl|生成的文件路径||false|string||
|memo|||false|string||
|pageNo||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||


## 试验报告-通过id删除


**接口地址**:`/api/database/experimentReport/delete`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 试验报告-批量删除


**接口地址**:`/api/database/experimentReport/deleteBatch`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ids||query|true|string||


# 试验评分


## 试验评分-编辑


**接口地址**:`/api/database/experimentRating/edit`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentRating|试验评分|body|true|ExperimentRating|ExperimentRating|
|id|主键||false|string||
|experimentId|实验ID||false|string||
|rateUserId|评分人||false|string||
|totalScore|总分数||false|string||
|baseScore|基础分||false|string||
|completionScore|完成分||false|string||
|difficultyScore|难度分||false|string||
|managementScore|管理支撑分||false|string||
|experimentName|||false|string||
|experimentNo|||false|string||
|rateUserName|||false|string||
|rateUserList|试验评分||false|array|ExperimentRatingUserDTO|
|rateUserId|评分人||false|string||
|totalScore|总分数||false|string||
|baseScore|基础分||false|string||
|completionScore|完成分||false|string||
|difficultyScore|难度分||false|string||
|managementScore|管理支撑分||false|string||


## 试验评分-编辑


**接口地址**:`/api/database/experimentRating/edit`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentRating|试验评分|body|true|ExperimentRating|ExperimentRating|
|id|主键||false|string||
|experimentId|实验ID||false|string||
|rateUserId|评分人||false|string||
|totalScore|总分数||false|string||
|baseScore|基础分||false|string||
|completionScore|完成分||false|string||
|difficultyScore|难度分||false|string||
|managementScore|管理支撑分||false|string||
|experimentName|||false|string||
|experimentNo|||false|string||
|rateUserName|||false|string||
|rateUserList|试验评分||false|array|ExperimentRatingUserDTO|
|rateUserId|评分人||false|string||
|totalScore|总分数||false|string||
|baseScore|基础分||false|string||
|completionScore|完成分||false|string||
|difficultyScore|难度分||false|string||
|managementScore|管理支撑分||false|string||


## 试验评分-添加


**接口地址**:`/api/database/experimentRating/add`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentRating|试验评分|body|true|ExperimentRating|ExperimentRating|
|id|主键||false|string||
|experimentId|实验ID||false|string||
|rateUserId|评分人||false|string||
|totalScore|总分数||false|string||
|baseScore|基础分||false|string||
|completionScore|完成分||false|string||
|difficultyScore|难度分||false|string||
|managementScore|管理支撑分||false|string||
|experimentName|||false|string||
|experimentNo|||false|string||
|rateUserName|||false|string||
|rateUserList|试验评分||false|array|ExperimentRatingUserDTO|
|rateUserId|评分人||false|string||
|totalScore|总分数||false|string||
|baseScore|基础分||false|string||
|completionScore|完成分||false|string||
|difficultyScore|难度分||false|string||
|managementScore|管理支撑分||false|string||


## 试验评分-批量添加


**接口地址**:`/api/database/experimentRating/addAll`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentRating|试验评分|body|true|ExperimentRating|ExperimentRating|
|id|主键||false|string||
|experimentId|实验ID||false|string||
|rateUserId|评分人||false|string||
|totalScore|总分数||false|string||
|baseScore|基础分||false|string||
|completionScore|完成分||false|string||
|difficultyScore|难度分||false|string||
|managementScore|管理支撑分||false|string||
|experimentName|||false|string||
|experimentNo|||false|string||
|rateUserName|||false|string||
|rateUserList|试验评分||false|array|ExperimentRatingUserDTO|
|rateUserId|评分人||false|string||
|totalScore|总分数||false|string||
|baseScore|基础分||false|string||
|completionScore|完成分||false|string||
|difficultyScore|难度分||false|string||
|managementScore|管理支撑分||false|string||


## 试验评分-通过id查询


**接口地址**:`/api/database/experimentRating/queryById`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 试验评分-分页列表查询


**接口地址**:`/api/database/experimentRating/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentRating|试验评分|query|true|ExperimentRating|ExperimentRating|
|id|主键||false|string||
|experimentId|实验ID||false|string||
|rateUserId|评分人||false|string||
|totalScore|总分数||false|string||
|baseScore|基础分||false|string||
|completionScore|完成分||false|string||
|difficultyScore|难度分||false|string||
|managementScore|管理支撑分||false|string||
|experimentName|||false|string||
|experimentNo|||false|string||
|rateUserName|||false|string||
|rateUserList|试验评分||false|array|ExperimentRatingUserDTO|
|rateUserId|评分人||false|string||
|totalScore|总分数||false|string||
|baseScore|基础分||false|string||
|completionScore|完成分||false|string||
|difficultyScore|难度分||false|string||
|managementScore|管理支撑分||false|string||
|pageNo||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||


## 试验评分-通过id删除


**接口地址**:`/api/database/experimentRating/delete`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 试验评分-批量删除


**接口地址**:`/api/database/experimentRating/deleteBatch`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ids||query|true|string||


# 辐照过程


## 辐照过程-编辑


**接口地址**:`/api/database/experimentRadiationProcess/edit`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentRadiationProcess|辐照过程|body|true|ExperimentRadiationProcess|ExperimentRadiationProcess|
|id|主键||false|string||
|radiationSource| 辐照源||false|string||
|measurementRate|剂量率||false|string||
|radiationStandard|辐照标准||false|string||
|environmentalTemperature|环境温度||false|string||
|comment|备注||false|string||
|exceptionRecord|异常记录||false|string||
|radiationDetail|辐照详情（json存储）||false|string||
|experimentId|试验ID||false|string||
|radiationStartTime|辐照开始时间||false|string(date-time)||
|radiationEndTime|辐照结束时间||false|string(date-time)||
|sampleInfo|样品信息||false|string||
|sampleInfoList|试验样品信息||false|array|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||


## 辐照过程-编辑


**接口地址**:`/api/database/experimentRadiationProcess/edit`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentRadiationProcess|辐照过程|body|true|ExperimentRadiationProcess|ExperimentRadiationProcess|
|id|主键||false|string||
|radiationSource| 辐照源||false|string||
|measurementRate|剂量率||false|string||
|radiationStandard|辐照标准||false|string||
|environmentalTemperature|环境温度||false|string||
|comment|备注||false|string||
|exceptionRecord|异常记录||false|string||
|radiationDetail|辐照详情（json存储）||false|string||
|experimentId|试验ID||false|string||
|radiationStartTime|辐照开始时间||false|string(date-time)||
|radiationEndTime|辐照结束时间||false|string(date-time)||
|sampleInfo|样品信息||false|string||
|sampleInfoList|试验样品信息||false|array|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||


## 辐照过程-添加


**接口地址**:`/api/database/experimentRadiationProcess/add`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentRadiationProcess|辐照过程|body|true|ExperimentRadiationProcess|ExperimentRadiationProcess|
|id|主键||false|string||
|radiationSource| 辐照源||false|string||
|measurementRate|剂量率||false|string||
|radiationStandard|辐照标准||false|string||
|environmentalTemperature|环境温度||false|string||
|comment|备注||false|string||
|exceptionRecord|异常记录||false|string||
|radiationDetail|辐照详情（json存储）||false|string||
|experimentId|试验ID||false|string||
|radiationStartTime|辐照开始时间||false|string(date-time)||
|radiationEndTime|辐照结束时间||false|string(date-time)||
|sampleInfo|样品信息||false|string||
|sampleInfoList|试验样品信息||false|array|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||


## 辐照过程-通过id查询


**接口地址**:`/api/database/experimentRadiationProcess/queryById`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 辐照过程-通过实验ID查询


**接口地址**:`/api/database/experimentRadiationProcess/queryByExperimentId`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentId||query|true|string||


## 辐照过程-分页列表查询


**接口地址**:`/api/database/experimentRadiationProcess/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentRadiationProcess|辐照过程|query|true|ExperimentRadiationProcess|ExperimentRadiationProcess|
|id|主键||false|string||
|radiationSource| 辐照源||false|string||
|measurementRate|剂量率||false|string||
|radiationStandard|辐照标准||false|string||
|environmentalTemperature|环境温度||false|string||
|comment|备注||false|string||
|exceptionRecord|异常记录||false|string||
|radiationDetail|辐照详情（json存储）||false|string||
|experimentId|试验ID||false|string||
|radiationStartTime|辐照开始时间||false|string(date-time)||
|radiationEndTime|辐照结束时间||false|string(date-time)||
|sampleInfo|样品信息||false|string||
|sampleInfoList|试验样品信息||false|array|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|pageNo||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||


## 辐照过程-通过id删除


**接口地址**:`/api/database/experimentRadiationProcess/delete`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 辐照过程-批量删除


**接口地址**:`/api/database/experimentRadiationProcess/deleteBatch`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ids||query|true|string||


# 试验日志


## 试验日志-编辑


**接口地址**:`/api/database/experimentLog/edit`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentLog|试验日志|body|true|ExperimentLog|ExperimentLog|
|id|主键||false|string||
|experimentId|试验ID||false|string||
|logContent|日志内容||false|string||


## 试验日志-编辑


**接口地址**:`/api/database/experimentLog/edit`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentLog|试验日志|body|true|ExperimentLog|ExperimentLog|
|id|主键||false|string||
|experimentId|试验ID||false|string||
|logContent|日志内容||false|string||


## 试验日志-添加


**接口地址**:`/api/database/experimentLog/add`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentLog|试验日志|body|true|ExperimentLog|ExperimentLog|
|id|主键||false|string||
|experimentId|试验ID||false|string||
|logContent|日志内容||false|string||


## 试验日志-通过id查询


**接口地址**:`/api/database/experimentLog/queryById`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 试验日志-分页列表查询


**接口地址**:`/api/database/experimentLog/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentLog|试验日志|query|true|ExperimentLog|ExperimentLog|
|id|主键||false|string||
|experimentId|试验ID||false|string||
|logContent|日志内容||false|string||
|pageNo||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||


## 试验日志-通过id删除


**接口地址**:`/api/database/experimentLog/delete`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 试验日志-批量删除


**接口地址**:`/api/database/experimentLog/deleteBatch`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ids||query|true|string||


# 试验辐照板信息


## 试验辐照板信息-编辑


**接口地址**:`/api/database/experimentIrradiationBoard/edit`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentIrradiationBoard|试验辐照板信息|body|true|ExperimentIrradiationBoard|ExperimentIrradiationBoard|
|id|主键||false|string||
|experimentId|试验ID||false|string||
|sampleId|样品ID||false|string||
|sampleInfo|试验样品信息||false|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|sampleName|样品名称||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|irradiationBoardNumber|辐照板数量||false|string||
|irradiationBoardCode|辐照板编号||false|string||
|measurementValidity|计量有效期||false|string||
|sourceArea|占源面积||false|string||
|irradiationBoardImage|辐照板图片||false|string||


## 试验辐照板信息-编辑


**接口地址**:`/api/database/experimentIrradiationBoard/edit`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentIrradiationBoard|试验辐照板信息|body|true|ExperimentIrradiationBoard|ExperimentIrradiationBoard|
|id|主键||false|string||
|experimentId|试验ID||false|string||
|sampleId|样品ID||false|string||
|sampleInfo|试验样品信息||false|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|sampleName|样品名称||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|irradiationBoardNumber|辐照板数量||false|string||
|irradiationBoardCode|辐照板编号||false|string||
|measurementValidity|计量有效期||false|string||
|sourceArea|占源面积||false|string||
|irradiationBoardImage|辐照板图片||false|string||


## 试验辐照板信息-添加


**接口地址**:`/api/database/experimentIrradiationBoard/add`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentIrradiationBoard|试验辐照板信息|body|true|ExperimentIrradiationBoard|ExperimentIrradiationBoard|
|id|主键||false|string||
|experimentId|试验ID||false|string||
|sampleId|样品ID||false|string||
|sampleInfo|试验样品信息||false|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|sampleName|样品名称||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|irradiationBoardNumber|辐照板数量||false|string||
|irradiationBoardCode|辐照板编号||false|string||
|measurementValidity|计量有效期||false|string||
|sourceArea|占源面积||false|string||
|irradiationBoardImage|辐照板图片||false|string||


## 试验辐照板信息-通过id查询


**接口地址**:`/api/database/experimentIrradiationBoard/queryById`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 试验辐照板信息-分页列表查询


**接口地址**:`/api/database/experimentIrradiationBoard/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentIrradiationBoard|试验辐照板信息|query|true|ExperimentIrradiationBoard|ExperimentIrradiationBoard|
|id|主键||false|string||
|experimentId|试验ID||false|string||
|sampleId|样品ID||false|string||
|sampleInfo|试验样品信息||false|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|sampleName|样品名称||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|irradiationBoardNumber|辐照板数量||false|string||
|irradiationBoardCode|辐照板编号||false|string||
|measurementValidity|计量有效期||false|string||
|sourceArea|占源面积||false|string||
|irradiationBoardImage|辐照板图片||false|string||
|pageNo||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||


## 试验辐照板信息-通过id删除


**接口地址**:`/api/database/experimentIrradiationBoard/delete`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 试验辐照板信息-批量删除


**接口地址**:`/api/database/experimentIrradiationBoard/deleteBatch`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ids||query|true|string||


# 试验文件


## 试验文件-编辑


**接口地址**:`/api/database/experimentFile/edit`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentFile|试验文件|body|true|ExperimentFile|ExperimentFile|
|id|主键||false|string||
|experimentId|试验ID||false|string||
|fileName|文件名称||false|string||
|fileType|类型||false|string||
|fileUrl|文件链接||false|string||


## 试验文件-编辑


**接口地址**:`/api/database/experimentFile/edit`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentFile|试验文件|body|true|ExperimentFile|ExperimentFile|
|id|主键||false|string||
|experimentId|试验ID||false|string||
|fileName|文件名称||false|string||
|fileType|类型||false|string||
|fileUrl|文件链接||false|string||


## 试验管理-上传试验文件


**接口地址**:`/api/database/experimentFile/upload`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>fileType 选传, 值为 [&#39;辐照试验委托书&#39;,&#39;试验大纲&#39;,&#39;沟通记录表&#39;,&#39;合同/委托书评审表&#39;,&#39;试验大纲评审表&#39;,&#39;试验流程检查单&#39;,&#39;辐照试验更改申请表&#39;,&#39;满意度调查表&#39;,&#39;其他文件&#39;],不传默认 &#39;其他文件&#39;,</br> experimentId 选传,上传完成后自动保存到试验管理,不传则不保存</p>


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentId||query|false|string||
|fileType||query|false|string||


## 试验文件-添加


**接口地址**:`/api/database/experimentFile/add`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentFile|试验文件|body|true|ExperimentFile|ExperimentFile|
|id|主键||false|string||
|experimentId|试验ID||false|string||
|fileName|文件名称||false|string||
|fileType|类型||false|string||
|fileUrl|文件链接||false|string||


## 试验报告-通过实验ID分类查询


**接口地址**:`/api/database/experimentFile/queryByType`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据分类查询,返回Map结构</p>


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentId||query|true|string||


## 试验文件-通过id查询


**接口地址**:`/api/database/experimentFile/queryById`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 试验报告-通过实验ID查询


**接口地址**:`/api/database/experimentFile/queryByExperimentId`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据实验ID查询,返回List结构</p>


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentId||query|true|string||


## 试验文件-分页列表查询


**接口地址**:`/api/database/experimentFile/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentFile|试验文件|query|true|ExperimentFile|ExperimentFile|
|id|主键||false|string||
|experimentId|试验ID||false|string||
|fileName|文件名称||false|string||
|fileType|类型||false|string||
|fileUrl|文件链接||false|string||
|pageNo||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||


## 试验文件-通过id删除


**接口地址**:`/api/database/experimentFile/delete`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 试验文件-批量删除


**接口地址**:`/api/database/experimentFile/deleteBatch`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ids||query|true|string||


# 试验文档


## 试验文档-编辑


**接口地址**:`/api/database/experimentDoc/edit`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentDoc|试验文档|body|true|ExperimentDoc|ExperimentDoc|
|id|主键||false|string||
|experimentId|试验ID||false|string||
|docType|文档类型,可用值:辐照试验计划表,试验报告评审表,设备使用记录表,试验报告,辐照试验总结单||false|string||
|filePath|文件地址||false|string||
|version|版本号||false|string||


## 试验文档-编辑


**接口地址**:`/api/database/experimentDoc/edit`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentDoc|试验文档|body|true|ExperimentDoc|ExperimentDoc|
|id|主键||false|string||
|experimentId|试验ID||false|string||
|docType|文档类型,可用值:辐照试验计划表,试验报告评审表,设备使用记录表,试验报告,辐照试验总结单||false|string||
|filePath|文件地址||false|string||
|version|版本号||false|string||


## 试验文档-添加


**接口地址**:`/api/database/experimentDoc/add`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentDoc|试验文档|body|true|ExperimentDoc|ExperimentDoc|
|id|主键||false|string||
|experimentId|试验ID||false|string||
|docType|文档类型,可用值:辐照试验计划表,试验报告评审表,设备使用记录表,试验报告,辐照试验总结单||false|string||
|filePath|文件地址||false|string||
|version|版本号||false|string||


## 试验报告-通过实验ID查询所有版本文档


**接口地址**:`/api/database/experimentDoc/queryHistoryByExperimentId`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentId||query|true|string||


## 试验文档-通过id查询


**接口地址**:`/api/database/experimentDoc/queryById`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 试验报告-通过实验ID查询


**接口地址**:`/api/database/experimentDoc/queryByExperimentId`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentId||query|true|string||


## 试验文档-分页列表查询


**接口地址**:`/api/database/experimentDoc/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentDoc|试验文档|query|true|ExperimentDoc|ExperimentDoc|
|id|主键||false|string||
|experimentId|试验ID||false|string||
|docType|文档类型,可用值:辐照试验计划表,试验报告评审表,设备使用记录表,试验报告,辐照试验总结单||false|string||
|filePath|文件地址||false|string||
|version|版本号||false|string||
|pageNo||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||


## 重新生成试验文档


**接口地址**:`/api/database/experimentDoc/generate`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentId|实验id|query|true|string||
|type|文档类型|query|true|string||


## 试验文档下载


**接口地址**:`/api/database/experimentDoc/download`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentId|实验id|query|true|string||
|type|文档类型(辐照试验计划表,设备使用记录表,试验报告评审表,试验报告,辐照试验总结单)|query|true|string||


## 试验文档-通过id删除


**接口地址**:`/api/database/experimentDoc/delete`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 试验文档-批量删除


**接口地址**:`/api/database/experimentDoc/deleteBatch`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ids||query|true|string||


# 试验加偏设备信息


## 试验加偏设备信息-编辑


**接口地址**:`/api/database/experimentDeviationEquipment/edit`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentDeviationEquipment|试验加偏设备信息|body|true|ExperimentDeviationEquipment|ExperimentDeviationEquipment|
|id|主键||false|string||
|experimentId|试验ID||false|string||
|sampleId|样品ID||false|string||
|sampleInfo|试验样品信息||false|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|sampleName|样品名称||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|equipmentModel|设备型号||false|string||
|equipmentId|设备名称||false|string||
|equipmentName|设备名称||false|string||
|equipment|设备||false|Equipment|Equipment|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|name|名称||false|string||
|model|型号||false|string||
|factoryNo|出厂编号||false|string||
|managementNo|管理编号||false|string||
|expireDate|有效期||false|string||
|roomNo|房间号||false|string||
|images|图片||false|string||
|equipmentType|设备类型||false|string||
|equipmentImage|设备图片||false|string||
|measurementValidity|计量有效期||false|string||
|roomNo|房间号||false|string||
|factoryNo|出厂编号||false|string||
|managementNo|管理编号||false|string||


## 试验加偏设备信息-编辑


**接口地址**:`/api/database/experimentDeviationEquipment/edit`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentDeviationEquipment|试验加偏设备信息|body|true|ExperimentDeviationEquipment|ExperimentDeviationEquipment|
|id|主键||false|string||
|experimentId|试验ID||false|string||
|sampleId|样品ID||false|string||
|sampleInfo|试验样品信息||false|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|sampleName|样品名称||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|equipmentModel|设备型号||false|string||
|equipmentId|设备名称||false|string||
|equipmentName|设备名称||false|string||
|equipment|设备||false|Equipment|Equipment|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|name|名称||false|string||
|model|型号||false|string||
|factoryNo|出厂编号||false|string||
|managementNo|管理编号||false|string||
|expireDate|有效期||false|string||
|roomNo|房间号||false|string||
|images|图片||false|string||
|equipmentType|设备类型||false|string||
|equipmentImage|设备图片||false|string||
|measurementValidity|计量有效期||false|string||
|roomNo|房间号||false|string||
|factoryNo|出厂编号||false|string||
|managementNo|管理编号||false|string||


## 试验加偏设备信息-添加


**接口地址**:`/api/database/experimentDeviationEquipment/add`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentDeviationEquipment|试验加偏设备信息|body|true|ExperimentDeviationEquipment|ExperimentDeviationEquipment|
|id|主键||false|string||
|experimentId|试验ID||false|string||
|sampleId|样品ID||false|string||
|sampleInfo|试验样品信息||false|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|sampleName|样品名称||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|equipmentModel|设备型号||false|string||
|equipmentId|设备名称||false|string||
|equipmentName|设备名称||false|string||
|equipment|设备||false|Equipment|Equipment|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|name|名称||false|string||
|model|型号||false|string||
|factoryNo|出厂编号||false|string||
|managementNo|管理编号||false|string||
|expireDate|有效期||false|string||
|roomNo|房间号||false|string||
|images|图片||false|string||
|equipmentType|设备类型||false|string||
|equipmentImage|设备图片||false|string||
|measurementValidity|计量有效期||false|string||
|roomNo|房间号||false|string||
|factoryNo|出厂编号||false|string||
|managementNo|管理编号||false|string||


## 试验加偏设备信息-通过id查询


**接口地址**:`/api/database/experimentDeviationEquipment/queryById`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 试验加偏设备信息-分页列表查询


**接口地址**:`/api/database/experimentDeviationEquipment/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentDeviationEquipment|试验加偏设备信息|query|true|ExperimentDeviationEquipment|ExperimentDeviationEquipment|
|id|主键||false|string||
|experimentId|试验ID||false|string||
|sampleId|样品ID||false|string||
|sampleInfo|试验样品信息||false|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|sampleName|样品名称||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|equipmentModel|设备型号||false|string||
|equipmentId|设备名称||false|string||
|equipmentName|设备名称||false|string||
|equipment|设备||false|Equipment|Equipment|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|name|名称||false|string||
|model|型号||false|string||
|factoryNo|出厂编号||false|string||
|managementNo|管理编号||false|string||
|expireDate|有效期||false|string||
|roomNo|房间号||false|string||
|images|图片||false|string||
|equipmentType|设备类型||false|string||
|equipmentImage|设备图片||false|string||
|measurementValidity|计量有效期||false|string||
|roomNo|房间号||false|string||
|factoryNo|出厂编号||false|string||
|managementNo|管理编号||false|string||
|pageNo||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||


## 试验加偏设备信息-通过id删除


**接口地址**:`/api/database/experimentDeviationEquipment/delete`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 试验加偏设备信息-批量删除


**接口地址**:`/api/database/experimentDeviationEquipment/deleteBatch`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ids||query|true|string||


# 试验偏置条件信息


## 试验偏置条件信息-编辑


**接口地址**:`/api/database/experimentDeviationCondition/edit`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentDeviationCondition|试验偏置条件信息|body|true|ExperimentDeviationCondition|ExperimentDeviationCondition|
|id|主键||false|string||
|experimentId|试验ID||false|string||
|sampleId|样品ID||false|string||
|sampleInfo|试验样品信息||false|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|sampleName|样品名称||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleNumber|样品编号||false|string||
|offsetCondition|偏置条件||false|string||
|offsetVoltage|偏置电压||false|string||
|offsetCurrent|偏置电流||false|string||
|loadCapacity|负载||false|string||
|otherCondition|其它条件||false|string||
|offsetPowerPhoto|偏置电源显示界面照片||false|string||
|inputSignal|输入信号||false|string||
|signalPhoto|信号显示界面源照片||false|string||
|pinType|管脚接入方式||false|string||
|signalType|信号类型||false|string||
|signalFrequency|信号频率||false|string||
|signalAmplitude|信号幅值||false|string||
|dutyCycle|占空比||false|string||


## 试验偏置条件信息-编辑


**接口地址**:`/api/database/experimentDeviationCondition/edit`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentDeviationCondition|试验偏置条件信息|body|true|ExperimentDeviationCondition|ExperimentDeviationCondition|
|id|主键||false|string||
|experimentId|试验ID||false|string||
|sampleId|样品ID||false|string||
|sampleInfo|试验样品信息||false|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|sampleName|样品名称||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleNumber|样品编号||false|string||
|offsetCondition|偏置条件||false|string||
|offsetVoltage|偏置电压||false|string||
|offsetCurrent|偏置电流||false|string||
|loadCapacity|负载||false|string||
|otherCondition|其它条件||false|string||
|offsetPowerPhoto|偏置电源显示界面照片||false|string||
|inputSignal|输入信号||false|string||
|signalPhoto|信号显示界面源照片||false|string||
|pinType|管脚接入方式||false|string||
|signalType|信号类型||false|string||
|signalFrequency|信号频率||false|string||
|signalAmplitude|信号幅值||false|string||
|dutyCycle|占空比||false|string||


## 试验偏置条件信息-添加


**接口地址**:`/api/database/experimentDeviationCondition/add`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentDeviationCondition|试验偏置条件信息|body|true|ExperimentDeviationCondition|ExperimentDeviationCondition|
|id|主键||false|string||
|experimentId|试验ID||false|string||
|sampleId|样品ID||false|string||
|sampleInfo|试验样品信息||false|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|sampleName|样品名称||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleNumber|样品编号||false|string||
|offsetCondition|偏置条件||false|string||
|offsetVoltage|偏置电压||false|string||
|offsetCurrent|偏置电流||false|string||
|loadCapacity|负载||false|string||
|otherCondition|其它条件||false|string||
|offsetPowerPhoto|偏置电源显示界面照片||false|string||
|inputSignal|输入信号||false|string||
|signalPhoto|信号显示界面源照片||false|string||
|pinType|管脚接入方式||false|string||
|signalType|信号类型||false|string||
|signalFrequency|信号频率||false|string||
|signalAmplitude|信号幅值||false|string||
|dutyCycle|占空比||false|string||


## 试验偏置条件信息-通过id查询


**接口地址**:`/api/database/experimentDeviationCondition/queryById`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 试验偏置条件信息-分页列表查询


**接口地址**:`/api/database/experimentDeviationCondition/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentDeviationCondition|试验偏置条件信息|query|true|ExperimentDeviationCondition|ExperimentDeviationCondition|
|id|主键||false|string||
|experimentId|试验ID||false|string||
|sampleId|样品ID||false|string||
|sampleInfo|试验样品信息||false|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|sampleName|样品名称||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleNumber|样品编号||false|string||
|offsetCondition|偏置条件||false|string||
|offsetVoltage|偏置电压||false|string||
|offsetCurrent|偏置电流||false|string||
|loadCapacity|负载||false|string||
|otherCondition|其它条件||false|string||
|offsetPowerPhoto|偏置电源显示界面照片||false|string||
|inputSignal|输入信号||false|string||
|signalPhoto|信号显示界面源照片||false|string||
|pinType|管脚接入方式||false|string||
|signalType|信号类型||false|string||
|signalFrequency|信号频率||false|string||
|signalAmplitude|信号幅值||false|string||
|dutyCycle|占空比||false|string||
|pageNo||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||


## 试验偏置条件信息-通过id删除


**接口地址**:`/api/database/experimentDeviationCondition/delete`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 试验偏置条件信息-批量删除


**接口地址**:`/api/database/experimentDeviationCondition/deleteBatch`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ids||query|true|string||


# 退火过程


## 退火过程-编辑


**接口地址**:`/api/database/experimentAnnealProcess/edit`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentAnnealProcess|退火过程|body|true|ExperimentAnnealProcess|ExperimentAnnealProcess|
|id|主键||false|string||
|radiationSource| 辐照源||false|string||
|measurementRate|计量率||false|string||
|radiationStandard|辐照标准||false|string||
|annealTemperature|退火温度||false|string||
|comment|备注||false|string||
|exceptionRecord|异常记录||false|string||
|radiationDetail|辐照详情（json存储）||false|string||
|experimentId|试验ID||false|string||
|annealStartTime|退火开始时间||false|string(date-time)||
|annealEndTime|退火结束时间||false|string(date-time)||
|deviationCondition|偏置条件||false|string||
|deviationEquipment|加偏设备||false|string||
|sampleInfo|样品信息||false|string||
|deviationConditionList|试验偏置条件信息||false|array|ExperimentDeviationCondition|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleId|样品ID||false|string||
|sampleInfo|试验样品信息||false|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|sampleName|样品名称||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleNumber|样品编号||false|string||
|offsetCondition|偏置条件||false|string||
|offsetVoltage|偏置电压||false|string||
|offsetCurrent|偏置电流||false|string||
|loadCapacity|负载||false|string||
|otherCondition|其它条件||false|string||
|offsetPowerPhoto|偏置电源显示界面照片||false|string||
|inputSignal|输入信号||false|string||
|signalPhoto|信号显示界面源照片||false|string||
|pinType|管脚接入方式||false|string||
|signalType|信号类型||false|string||
|signalFrequency|信号频率||false|string||
|signalAmplitude|信号幅值||false|string||
|dutyCycle|占空比||false|string||
|deviationEquipmentList|试验加偏设备信息||false|array|ExperimentDeviationEquipment|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleId|样品ID||false|string||
|sampleInfo|试验样品信息||false|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|sampleName|样品名称||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|equipmentModel|设备型号||false|string||
|equipmentId|设备名称||false|string||
|equipmentName|设备名称||false|string||
|equipment|设备||false|Equipment|Equipment|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|name|名称||false|string||
|model|型号||false|string||
|factoryNo|出厂编号||false|string||
|managementNo|管理编号||false|string||
|expireDate|有效期||false|string||
|roomNo|房间号||false|string||
|images|图片||false|string||
|equipmentType|设备类型||false|string||
|equipmentImage|设备图片||false|string||
|measurementValidity|计量有效期||false|string||
|roomNo|房间号||false|string||
|factoryNo|出厂编号||false|string||
|managementNo|管理编号||false|string||
|sampleInfoList|试验样品信息||false|array|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||


## 退火过程-编辑


**接口地址**:`/api/database/experimentAnnealProcess/edit`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentAnnealProcess|退火过程|body|true|ExperimentAnnealProcess|ExperimentAnnealProcess|
|id|主键||false|string||
|radiationSource| 辐照源||false|string||
|measurementRate|计量率||false|string||
|radiationStandard|辐照标准||false|string||
|annealTemperature|退火温度||false|string||
|comment|备注||false|string||
|exceptionRecord|异常记录||false|string||
|radiationDetail|辐照详情（json存储）||false|string||
|experimentId|试验ID||false|string||
|annealStartTime|退火开始时间||false|string(date-time)||
|annealEndTime|退火结束时间||false|string(date-time)||
|deviationCondition|偏置条件||false|string||
|deviationEquipment|加偏设备||false|string||
|sampleInfo|样品信息||false|string||
|deviationConditionList|试验偏置条件信息||false|array|ExperimentDeviationCondition|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleId|样品ID||false|string||
|sampleInfo|试验样品信息||false|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|sampleName|样品名称||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleNumber|样品编号||false|string||
|offsetCondition|偏置条件||false|string||
|offsetVoltage|偏置电压||false|string||
|offsetCurrent|偏置电流||false|string||
|loadCapacity|负载||false|string||
|otherCondition|其它条件||false|string||
|offsetPowerPhoto|偏置电源显示界面照片||false|string||
|inputSignal|输入信号||false|string||
|signalPhoto|信号显示界面源照片||false|string||
|pinType|管脚接入方式||false|string||
|signalType|信号类型||false|string||
|signalFrequency|信号频率||false|string||
|signalAmplitude|信号幅值||false|string||
|dutyCycle|占空比||false|string||
|deviationEquipmentList|试验加偏设备信息||false|array|ExperimentDeviationEquipment|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleId|样品ID||false|string||
|sampleInfo|试验样品信息||false|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|sampleName|样品名称||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|equipmentModel|设备型号||false|string||
|equipmentId|设备名称||false|string||
|equipmentName|设备名称||false|string||
|equipment|设备||false|Equipment|Equipment|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|name|名称||false|string||
|model|型号||false|string||
|factoryNo|出厂编号||false|string||
|managementNo|管理编号||false|string||
|expireDate|有效期||false|string||
|roomNo|房间号||false|string||
|images|图片||false|string||
|equipmentType|设备类型||false|string||
|equipmentImage|设备图片||false|string||
|measurementValidity|计量有效期||false|string||
|roomNo|房间号||false|string||
|factoryNo|出厂编号||false|string||
|managementNo|管理编号||false|string||
|sampleInfoList|试验样品信息||false|array|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||


## 退火过程-添加


**接口地址**:`/api/database/experimentAnnealProcess/add`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentAnnealProcess|退火过程|body|true|ExperimentAnnealProcess|ExperimentAnnealProcess|
|id|主键||false|string||
|radiationSource| 辐照源||false|string||
|measurementRate|计量率||false|string||
|radiationStandard|辐照标准||false|string||
|annealTemperature|退火温度||false|string||
|comment|备注||false|string||
|exceptionRecord|异常记录||false|string||
|radiationDetail|辐照详情（json存储）||false|string||
|experimentId|试验ID||false|string||
|annealStartTime|退火开始时间||false|string(date-time)||
|annealEndTime|退火结束时间||false|string(date-time)||
|deviationCondition|偏置条件||false|string||
|deviationEquipment|加偏设备||false|string||
|sampleInfo|样品信息||false|string||
|deviationConditionList|试验偏置条件信息||false|array|ExperimentDeviationCondition|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleId|样品ID||false|string||
|sampleInfo|试验样品信息||false|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|sampleName|样品名称||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleNumber|样品编号||false|string||
|offsetCondition|偏置条件||false|string||
|offsetVoltage|偏置电压||false|string||
|offsetCurrent|偏置电流||false|string||
|loadCapacity|负载||false|string||
|otherCondition|其它条件||false|string||
|offsetPowerPhoto|偏置电源显示界面照片||false|string||
|inputSignal|输入信号||false|string||
|signalPhoto|信号显示界面源照片||false|string||
|pinType|管脚接入方式||false|string||
|signalType|信号类型||false|string||
|signalFrequency|信号频率||false|string||
|signalAmplitude|信号幅值||false|string||
|dutyCycle|占空比||false|string||
|deviationEquipmentList|试验加偏设备信息||false|array|ExperimentDeviationEquipment|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleId|样品ID||false|string||
|sampleInfo|试验样品信息||false|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|sampleName|样品名称||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|equipmentModel|设备型号||false|string||
|equipmentId|设备名称||false|string||
|equipmentName|设备名称||false|string||
|equipment|设备||false|Equipment|Equipment|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|name|名称||false|string||
|model|型号||false|string||
|factoryNo|出厂编号||false|string||
|managementNo|管理编号||false|string||
|expireDate|有效期||false|string||
|roomNo|房间号||false|string||
|images|图片||false|string||
|equipmentType|设备类型||false|string||
|equipmentImage|设备图片||false|string||
|measurementValidity|计量有效期||false|string||
|roomNo|房间号||false|string||
|factoryNo|出厂编号||false|string||
|managementNo|管理编号||false|string||
|sampleInfoList|试验样品信息||false|array|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||


## 退火过程-通过id查询


**接口地址**:`/api/database/experimentAnnealProcess/queryById`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 退火过程-分页列表查询


**接口地址**:`/api/database/experimentAnnealProcess/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experimentAnnealProcess|退火过程|query|true|ExperimentAnnealProcess|ExperimentAnnealProcess|
|id|主键||false|string||
|radiationSource| 辐照源||false|string||
|measurementRate|计量率||false|string||
|radiationStandard|辐照标准||false|string||
|annealTemperature|退火温度||false|string||
|comment|备注||false|string||
|exceptionRecord|异常记录||false|string||
|radiationDetail|辐照详情（json存储）||false|string||
|experimentId|试验ID||false|string||
|annealStartTime|退火开始时间||false|string(date-time)||
|annealEndTime|退火结束时间||false|string(date-time)||
|deviationCondition|偏置条件||false|string||
|deviationEquipment|加偏设备||false|string||
|sampleInfo|样品信息||false|string||
|deviationConditionList|试验偏置条件信息||false|array|ExperimentDeviationCondition|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleId|样品ID||false|string||
|sampleInfo|试验样品信息||false|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|sampleName|样品名称||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleNumber|样品编号||false|string||
|offsetCondition|偏置条件||false|string||
|offsetVoltage|偏置电压||false|string||
|offsetCurrent|偏置电流||false|string||
|loadCapacity|负载||false|string||
|otherCondition|其它条件||false|string||
|offsetPowerPhoto|偏置电源显示界面照片||false|string||
|inputSignal|输入信号||false|string||
|signalPhoto|信号显示界面源照片||false|string||
|pinType|管脚接入方式||false|string||
|signalType|信号类型||false|string||
|signalFrequency|信号频率||false|string||
|signalAmplitude|信号幅值||false|string||
|dutyCycle|占空比||false|string||
|deviationEquipmentList|试验加偏设备信息||false|array|ExperimentDeviationEquipment|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleId|样品ID||false|string||
|sampleInfo|试验样品信息||false|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|sampleName|样品名称||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|equipmentModel|设备型号||false|string||
|equipmentId|设备名称||false|string||
|equipmentName|设备名称||false|string||
|equipment|设备||false|Equipment|Equipment|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|name|名称||false|string||
|model|型号||false|string||
|factoryNo|出厂编号||false|string||
|managementNo|管理编号||false|string||
|expireDate|有效期||false|string||
|roomNo|房间号||false|string||
|images|图片||false|string||
|equipmentType|设备类型||false|string||
|equipmentImage|设备图片||false|string||
|measurementValidity|计量有效期||false|string||
|roomNo|房间号||false|string||
|factoryNo|出厂编号||false|string||
|managementNo|管理编号||false|string||
|sampleInfoList|试验样品信息||false|array|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|pageNo||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||


## 退火过程-通过id删除


**接口地址**:`/api/database/experimentAnnealProcess/delete`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 退火过程-批量删除


**接口地址**:`/api/database/experimentAnnealProcess/deleteBatch`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ids||query|true|string||


# 试验管理


## 试验管理-编辑


**接口地址**:`/api/database/experiment/edit`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>编辑试验,传sampleInfoList,deviationConditionList,deviationEquipmentList,irradiationBoardList,不用传fileList 
 experimentUser 用逗号隔开,无修改的字段可不传</p>


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experiment|试验管理|body|true|Experiment|Experiment|
|id|主键||false|string||
|indexNo|序号||false|integer(int32)||
|experimentNo|试验编号||false|string||
|name|名称||false|string||
|clientName|委托方名称||false|string||
|type|试验类型||false|string||
|startDate|试验开始日期||false|string||
|endDate|试验结束日期||false|string||
|radiationSourceType|辐射源类型||false|string||
|supervisor|||false|string||
|supervisorName|||false|string||
|status|状态||false|string||
|statusList|||false|array|string|
|sampleInfoList|试验样品信息||false|array|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|irradiationBoardList|试验辐照板信息||false|array|ExperimentIrradiationBoard|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleId|样品ID||false|string||
|sampleInfo|试验样品信息||false|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|sampleName|样品名称||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|irradiationBoardNumber|辐照板数量||false|string||
|irradiationBoardCode|辐照板编号||false|string||
|measurementValidity|计量有效期||false|string||
|sourceArea|占源面积||false|string||
|irradiationBoardImage|辐照板图片||false|string||
|deviationConditionList|试验偏置条件信息||false|array|ExperimentDeviationCondition|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleId|样品ID||false|string||
|sampleInfo|试验样品信息||false|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|sampleName|样品名称||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleNumber|样品编号||false|string||
|offsetCondition|偏置条件||false|string||
|offsetVoltage|偏置电压||false|string||
|offsetCurrent|偏置电流||false|string||
|loadCapacity|负载||false|string||
|otherCondition|其它条件||false|string||
|offsetPowerPhoto|偏置电源显示界面照片||false|string||
|inputSignal|输入信号||false|string||
|signalPhoto|信号显示界面源照片||false|string||
|pinType|管脚接入方式||false|string||
|signalType|信号类型||false|string||
|signalFrequency|信号频率||false|string||
|signalAmplitude|信号幅值||false|string||
|dutyCycle|占空比||false|string||
|deviationEquipmentList|试验加偏设备信息||false|array|ExperimentDeviationEquipment|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleId|样品ID||false|string||
|sampleInfo|试验样品信息||false|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|sampleName|样品名称||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|equipmentModel|设备型号||false|string||
|equipmentId|设备名称||false|string||
|equipmentName|设备名称||false|string||
|equipment|设备||false|Equipment|Equipment|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|name|名称||false|string||
|model|型号||false|string||
|factoryNo|出厂编号||false|string||
|managementNo|管理编号||false|string||
|expireDate|有效期||false|string||
|roomNo|房间号||false|string||
|images|图片||false|string||
|equipmentType|设备类型||false|string||
|equipmentImage|设备图片||false|string||
|measurementValidity|计量有效期||false|string||
|roomNo|房间号||false|string||
|factoryNo|出厂编号||false|string||
|managementNo|管理编号||false|string||
|copyCount|复制次数||false|integer(int32)||
|fileList|试验文件||false|array|ExperimentFile|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|fileName|文件名称||false|string||
|fileType|类型||false|string||
|fileUrl|文件链接||false|string||
|experimentUser|||false|string||
|experimentUserList|试验人员||false|array|ExperimentUser|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|userId|人员||false|string||
|userName|||false|string||
|type|类型||false|string||
|experimentReportList|试验报告||false|array|ExperimentReport|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleInfo|样品信息||false|string||
|imgUrls|偏置原理图||false|string||
|description|偏置原理说明||false|string||
|auditor|审核员||false|string||
|proofreader|校对员||false|string||
|confirmer|确认员||false|string||
|reportName|报告名称||false|string||
|status|状态(申请中待校对:INIT,不合格:REJECT ,校对通过待审核：PROOFREAD_PASS,审核通过待批准：AUDIT_PASS，批准通过：PASSED)||false|string||
|auditorMemo|审批意见||false|string||
|proofreaderMemo|校对意见||false|string||
|confirmerMemo|批准意见||false|string||
|auditorTime|审批时间||false|string||
|proofreaderTime|校对时间||false|string||
|confirmerTime|批准时间||false|string||
|fileUrl|生成的文件路径||false|string||
|memo|||false|string||
|experimentRatingList|试验评分||false|array|ExperimentRating|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|实验ID||false|string||
|rateUserId|评分人||false|string||
|totalScore|总分数||false|string||
|baseScore|基础分||false|string||
|completionScore|完成分||false|string||
|difficultyScore|难度分||false|string||
|managementScore|管理支撑分||false|string||
|experimentName|||false|string||
|experimentNo|||false|string||
|rateUserName|||false|string||
|rateUserList|试验评分||false|array|ExperimentRatingUserDTO|
|rateUserId|评分人||false|string||
|totalScore|总分数||false|string||
|baseScore|基础分||false|string||
|completionScore|完成分||false|string||
|difficultyScore|难度分||false|string||
|managementScore|管理支撑分||false|string||
|sampleType|||false|string||
|sampleName|||false|string||
|equipmentModel|||false|string||
|sampleModel|||false|string||
|totalCountXhy|西核院统计||false|integer(int32)||
|totalCountLhs|理化所统计||false|integer(int32)||
|reportStatus|理化所统计||false|string||
|pageName|试验页面||false|string||
|radiationStandard|辐照标准||false|string||
|experimentReview|试验评定结果||false|ExperimentReview|ExperimentReview|
|id|id||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|experimentName|||false|string||
|sampleId|样品ID||false|string||
|sampleName|||false|string||
|processType|试验流程标准,可用值:GJB548C,ESCC229000,ESCC22500,QJ10004||false|string||
|needAnneal|是否需要退火,可用值:Y,N||false|string||
|targetedAnneal|是否进行了针对性的退火表征试验,可用值:Y,N||false|string||
|needRadiation|是否需要辐照,可用值:Y,N||false|string||
|needBurnin|是否需要老练,可用值:Y,N||false|string||
|result|评定结果||false|string||
|reviewStatus|评定状态||false|string||
|matchedRuleList|试验评定规则||false|array|ExperimentReviewRule|
|id|id||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|processType|试验流程标准||false|string||
|title|建议标题||false|string||
|result|评定结果||false|string||
|impactDesc|损伤机理||false|string||
|impactScope|适用范围||false|string||
|expression|规则表达式||false|string||
|priority|规则优先级||false|integer||
|experimentReviewList|||false|array|ExperimentReviewDTO|
|experimentId|||false|string||
|sampleName|||false|string||
|sampleId|||false|string||
|processType|||false|string||
|reviewResult|||false|string||


## 试验管理-编辑


**接口地址**:`/api/database/experiment/edit`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>编辑试验,传sampleInfoList,deviationConditionList,deviationEquipmentList,irradiationBoardList,不用传fileList 
 experimentUser 用逗号隔开,无修改的字段可不传</p>


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experiment|试验管理|body|true|Experiment|Experiment|
|id|主键||false|string||
|indexNo|序号||false|integer(int32)||
|experimentNo|试验编号||false|string||
|name|名称||false|string||
|clientName|委托方名称||false|string||
|type|试验类型||false|string||
|startDate|试验开始日期||false|string||
|endDate|试验结束日期||false|string||
|radiationSourceType|辐射源类型||false|string||
|supervisor|||false|string||
|supervisorName|||false|string||
|status|状态||false|string||
|statusList|||false|array|string|
|sampleInfoList|试验样品信息||false|array|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|irradiationBoardList|试验辐照板信息||false|array|ExperimentIrradiationBoard|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleId|样品ID||false|string||
|sampleInfo|试验样品信息||false|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|sampleName|样品名称||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|irradiationBoardNumber|辐照板数量||false|string||
|irradiationBoardCode|辐照板编号||false|string||
|measurementValidity|计量有效期||false|string||
|sourceArea|占源面积||false|string||
|irradiationBoardImage|辐照板图片||false|string||
|deviationConditionList|试验偏置条件信息||false|array|ExperimentDeviationCondition|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleId|样品ID||false|string||
|sampleInfo|试验样品信息||false|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|sampleName|样品名称||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleNumber|样品编号||false|string||
|offsetCondition|偏置条件||false|string||
|offsetVoltage|偏置电压||false|string||
|offsetCurrent|偏置电流||false|string||
|loadCapacity|负载||false|string||
|otherCondition|其它条件||false|string||
|offsetPowerPhoto|偏置电源显示界面照片||false|string||
|inputSignal|输入信号||false|string||
|signalPhoto|信号显示界面源照片||false|string||
|pinType|管脚接入方式||false|string||
|signalType|信号类型||false|string||
|signalFrequency|信号频率||false|string||
|signalAmplitude|信号幅值||false|string||
|dutyCycle|占空比||false|string||
|deviationEquipmentList|试验加偏设备信息||false|array|ExperimentDeviationEquipment|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleId|样品ID||false|string||
|sampleInfo|试验样品信息||false|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|sampleName|样品名称||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|equipmentModel|设备型号||false|string||
|equipmentId|设备名称||false|string||
|equipmentName|设备名称||false|string||
|equipment|设备||false|Equipment|Equipment|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|name|名称||false|string||
|model|型号||false|string||
|factoryNo|出厂编号||false|string||
|managementNo|管理编号||false|string||
|expireDate|有效期||false|string||
|roomNo|房间号||false|string||
|images|图片||false|string||
|equipmentType|设备类型||false|string||
|equipmentImage|设备图片||false|string||
|measurementValidity|计量有效期||false|string||
|roomNo|房间号||false|string||
|factoryNo|出厂编号||false|string||
|managementNo|管理编号||false|string||
|copyCount|复制次数||false|integer(int32)||
|fileList|试验文件||false|array|ExperimentFile|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|fileName|文件名称||false|string||
|fileType|类型||false|string||
|fileUrl|文件链接||false|string||
|experimentUser|||false|string||
|experimentUserList|试验人员||false|array|ExperimentUser|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|userId|人员||false|string||
|userName|||false|string||
|type|类型||false|string||
|experimentReportList|试验报告||false|array|ExperimentReport|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleInfo|样品信息||false|string||
|imgUrls|偏置原理图||false|string||
|description|偏置原理说明||false|string||
|auditor|审核员||false|string||
|proofreader|校对员||false|string||
|confirmer|确认员||false|string||
|reportName|报告名称||false|string||
|status|状态(申请中待校对:INIT,不合格:REJECT ,校对通过待审核：PROOFREAD_PASS,审核通过待批准：AUDIT_PASS，批准通过：PASSED)||false|string||
|auditorMemo|审批意见||false|string||
|proofreaderMemo|校对意见||false|string||
|confirmerMemo|批准意见||false|string||
|auditorTime|审批时间||false|string||
|proofreaderTime|校对时间||false|string||
|confirmerTime|批准时间||false|string||
|fileUrl|生成的文件路径||false|string||
|memo|||false|string||
|experimentRatingList|试验评分||false|array|ExperimentRating|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|实验ID||false|string||
|rateUserId|评分人||false|string||
|totalScore|总分数||false|string||
|baseScore|基础分||false|string||
|completionScore|完成分||false|string||
|difficultyScore|难度分||false|string||
|managementScore|管理支撑分||false|string||
|experimentName|||false|string||
|experimentNo|||false|string||
|rateUserName|||false|string||
|rateUserList|试验评分||false|array|ExperimentRatingUserDTO|
|rateUserId|评分人||false|string||
|totalScore|总分数||false|string||
|baseScore|基础分||false|string||
|completionScore|完成分||false|string||
|difficultyScore|难度分||false|string||
|managementScore|管理支撑分||false|string||
|sampleType|||false|string||
|sampleName|||false|string||
|equipmentModel|||false|string||
|sampleModel|||false|string||
|totalCountXhy|西核院统计||false|integer(int32)||
|totalCountLhs|理化所统计||false|integer(int32)||
|reportStatus|理化所统计||false|string||
|pageName|试验页面||false|string||
|radiationStandard|辐照标准||false|string||
|experimentReview|试验评定结果||false|ExperimentReview|ExperimentReview|
|id|id||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|experimentName|||false|string||
|sampleId|样品ID||false|string||
|sampleName|||false|string||
|processType|试验流程标准,可用值:GJB548C,ESCC229000,ESCC22500,QJ10004||false|string||
|needAnneal|是否需要退火,可用值:Y,N||false|string||
|targetedAnneal|是否进行了针对性的退火表征试验,可用值:Y,N||false|string||
|needRadiation|是否需要辐照,可用值:Y,N||false|string||
|needBurnin|是否需要老练,可用值:Y,N||false|string||
|result|评定结果||false|string||
|reviewStatus|评定状态||false|string||
|matchedRuleList|试验评定规则||false|array|ExperimentReviewRule|
|id|id||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|processType|试验流程标准||false|string||
|title|建议标题||false|string||
|result|评定结果||false|string||
|impactDesc|损伤机理||false|string||
|impactScope|适用范围||false|string||
|expression|规则表达式||false|string||
|priority|规则优先级||false|integer||
|experimentReviewList|||false|array|ExperimentReviewDTO|
|experimentId|||false|string||
|sampleName|||false|string||
|sampleId|||false|string||
|processType|||false|string||
|reviewResult|||false|string||


## 试验管理-修改实验统计


**接口地址**:`/api/database/experiment/editCount`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>修改实验统计</p>


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experiment|试验管理|body|true|Experiment|Experiment|
|id|主键||false|string||
|indexNo|序号||false|integer(int32)||
|experimentNo|试验编号||false|string||
|name|名称||false|string||
|clientName|委托方名称||false|string||
|type|试验类型||false|string||
|startDate|试验开始日期||false|string||
|endDate|试验结束日期||false|string||
|radiationSourceType|辐射源类型||false|string||
|supervisor|||false|string||
|supervisorName|||false|string||
|status|状态||false|string||
|statusList|||false|array|string|
|sampleInfoList|试验样品信息||false|array|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|irradiationBoardList|试验辐照板信息||false|array|ExperimentIrradiationBoard|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleId|样品ID||false|string||
|sampleInfo|试验样品信息||false|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|sampleName|样品名称||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|irradiationBoardNumber|辐照板数量||false|string||
|irradiationBoardCode|辐照板编号||false|string||
|measurementValidity|计量有效期||false|string||
|sourceArea|占源面积||false|string||
|irradiationBoardImage|辐照板图片||false|string||
|deviationConditionList|试验偏置条件信息||false|array|ExperimentDeviationCondition|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleId|样品ID||false|string||
|sampleInfo|试验样品信息||false|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|sampleName|样品名称||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleNumber|样品编号||false|string||
|offsetCondition|偏置条件||false|string||
|offsetVoltage|偏置电压||false|string||
|offsetCurrent|偏置电流||false|string||
|loadCapacity|负载||false|string||
|otherCondition|其它条件||false|string||
|offsetPowerPhoto|偏置电源显示界面照片||false|string||
|inputSignal|输入信号||false|string||
|signalPhoto|信号显示界面源照片||false|string||
|pinType|管脚接入方式||false|string||
|signalType|信号类型||false|string||
|signalFrequency|信号频率||false|string||
|signalAmplitude|信号幅值||false|string||
|dutyCycle|占空比||false|string||
|deviationEquipmentList|试验加偏设备信息||false|array|ExperimentDeviationEquipment|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleId|样品ID||false|string||
|sampleInfo|试验样品信息||false|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|sampleName|样品名称||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|equipmentModel|设备型号||false|string||
|equipmentId|设备名称||false|string||
|equipmentName|设备名称||false|string||
|equipment|设备||false|Equipment|Equipment|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|name|名称||false|string||
|model|型号||false|string||
|factoryNo|出厂编号||false|string||
|managementNo|管理编号||false|string||
|expireDate|有效期||false|string||
|roomNo|房间号||false|string||
|images|图片||false|string||
|equipmentType|设备类型||false|string||
|equipmentImage|设备图片||false|string||
|measurementValidity|计量有效期||false|string||
|roomNo|房间号||false|string||
|factoryNo|出厂编号||false|string||
|managementNo|管理编号||false|string||
|copyCount|复制次数||false|integer(int32)||
|fileList|试验文件||false|array|ExperimentFile|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|fileName|文件名称||false|string||
|fileType|类型||false|string||
|fileUrl|文件链接||false|string||
|experimentUser|||false|string||
|experimentUserList|试验人员||false|array|ExperimentUser|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|userId|人员||false|string||
|userName|||false|string||
|type|类型||false|string||
|experimentReportList|试验报告||false|array|ExperimentReport|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleInfo|样品信息||false|string||
|imgUrls|偏置原理图||false|string||
|description|偏置原理说明||false|string||
|auditor|审核员||false|string||
|proofreader|校对员||false|string||
|confirmer|确认员||false|string||
|reportName|报告名称||false|string||
|status|状态(申请中待校对:INIT,不合格:REJECT ,校对通过待审核：PROOFREAD_PASS,审核通过待批准：AUDIT_PASS，批准通过：PASSED)||false|string||
|auditorMemo|审批意见||false|string||
|proofreaderMemo|校对意见||false|string||
|confirmerMemo|批准意见||false|string||
|auditorTime|审批时间||false|string||
|proofreaderTime|校对时间||false|string||
|confirmerTime|批准时间||false|string||
|fileUrl|生成的文件路径||false|string||
|memo|||false|string||
|experimentRatingList|试验评分||false|array|ExperimentRating|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|实验ID||false|string||
|rateUserId|评分人||false|string||
|totalScore|总分数||false|string||
|baseScore|基础分||false|string||
|completionScore|完成分||false|string||
|difficultyScore|难度分||false|string||
|managementScore|管理支撑分||false|string||
|experimentName|||false|string||
|experimentNo|||false|string||
|rateUserName|||false|string||
|rateUserList|试验评分||false|array|ExperimentRatingUserDTO|
|rateUserId|评分人||false|string||
|totalScore|总分数||false|string||
|baseScore|基础分||false|string||
|completionScore|完成分||false|string||
|difficultyScore|难度分||false|string||
|managementScore|管理支撑分||false|string||
|sampleType|||false|string||
|sampleName|||false|string||
|equipmentModel|||false|string||
|sampleModel|||false|string||
|totalCountXhy|西核院统计||false|integer(int32)||
|totalCountLhs|理化所统计||false|integer(int32)||
|reportStatus|理化所统计||false|string||
|pageName|试验页面||false|string||
|radiationStandard|辐照标准||false|string||
|experimentReview|试验评定结果||false|ExperimentReview|ExperimentReview|
|id|id||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|experimentName|||false|string||
|sampleId|样品ID||false|string||
|sampleName|||false|string||
|processType|试验流程标准,可用值:GJB548C,ESCC229000,ESCC22500,QJ10004||false|string||
|needAnneal|是否需要退火,可用值:Y,N||false|string||
|targetedAnneal|是否进行了针对性的退火表征试验,可用值:Y,N||false|string||
|needRadiation|是否需要辐照,可用值:Y,N||false|string||
|needBurnin|是否需要老练,可用值:Y,N||false|string||
|result|评定结果||false|string||
|reviewStatus|评定状态||false|string||
|matchedRuleList|试验评定规则||false|array|ExperimentReviewRule|
|id|id||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|processType|试验流程标准||false|string||
|title|建议标题||false|string||
|result|评定结果||false|string||
|impactDesc|损伤机理||false|string||
|impactScope|适用范围||false|string||
|expression|规则表达式||false|string||
|priority|规则优先级||false|integer||
|experimentReviewList|||false|array|ExperimentReviewDTO|
|experimentId|||false|string||
|sampleName|||false|string||
|sampleId|||false|string||
|processType|||false|string||
|reviewResult|||false|string||


## 试验管理-修改实验统计


**接口地址**:`/api/database/experiment/editCount`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>修改实验统计</p>


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experiment|试验管理|body|true|Experiment|Experiment|
|id|主键||false|string||
|indexNo|序号||false|integer(int32)||
|experimentNo|试验编号||false|string||
|name|名称||false|string||
|clientName|委托方名称||false|string||
|type|试验类型||false|string||
|startDate|试验开始日期||false|string||
|endDate|试验结束日期||false|string||
|radiationSourceType|辐射源类型||false|string||
|supervisor|||false|string||
|supervisorName|||false|string||
|status|状态||false|string||
|statusList|||false|array|string|
|sampleInfoList|试验样品信息||false|array|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|irradiationBoardList|试验辐照板信息||false|array|ExperimentIrradiationBoard|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleId|样品ID||false|string||
|sampleInfo|试验样品信息||false|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|sampleName|样品名称||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|irradiationBoardNumber|辐照板数量||false|string||
|irradiationBoardCode|辐照板编号||false|string||
|measurementValidity|计量有效期||false|string||
|sourceArea|占源面积||false|string||
|irradiationBoardImage|辐照板图片||false|string||
|deviationConditionList|试验偏置条件信息||false|array|ExperimentDeviationCondition|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleId|样品ID||false|string||
|sampleInfo|试验样品信息||false|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|sampleName|样品名称||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleNumber|样品编号||false|string||
|offsetCondition|偏置条件||false|string||
|offsetVoltage|偏置电压||false|string||
|offsetCurrent|偏置电流||false|string||
|loadCapacity|负载||false|string||
|otherCondition|其它条件||false|string||
|offsetPowerPhoto|偏置电源显示界面照片||false|string||
|inputSignal|输入信号||false|string||
|signalPhoto|信号显示界面源照片||false|string||
|pinType|管脚接入方式||false|string||
|signalType|信号类型||false|string||
|signalFrequency|信号频率||false|string||
|signalAmplitude|信号幅值||false|string||
|dutyCycle|占空比||false|string||
|deviationEquipmentList|试验加偏设备信息||false|array|ExperimentDeviationEquipment|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleId|样品ID||false|string||
|sampleInfo|试验样品信息||false|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|sampleName|样品名称||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|equipmentModel|设备型号||false|string||
|equipmentId|设备名称||false|string||
|equipmentName|设备名称||false|string||
|equipment|设备||false|Equipment|Equipment|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|name|名称||false|string||
|model|型号||false|string||
|factoryNo|出厂编号||false|string||
|managementNo|管理编号||false|string||
|expireDate|有效期||false|string||
|roomNo|房间号||false|string||
|images|图片||false|string||
|equipmentType|设备类型||false|string||
|equipmentImage|设备图片||false|string||
|measurementValidity|计量有效期||false|string||
|roomNo|房间号||false|string||
|factoryNo|出厂编号||false|string||
|managementNo|管理编号||false|string||
|copyCount|复制次数||false|integer(int32)||
|fileList|试验文件||false|array|ExperimentFile|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|fileName|文件名称||false|string||
|fileType|类型||false|string||
|fileUrl|文件链接||false|string||
|experimentUser|||false|string||
|experimentUserList|试验人员||false|array|ExperimentUser|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|userId|人员||false|string||
|userName|||false|string||
|type|类型||false|string||
|experimentReportList|试验报告||false|array|ExperimentReport|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleInfo|样品信息||false|string||
|imgUrls|偏置原理图||false|string||
|description|偏置原理说明||false|string||
|auditor|审核员||false|string||
|proofreader|校对员||false|string||
|confirmer|确认员||false|string||
|reportName|报告名称||false|string||
|status|状态(申请中待校对:INIT,不合格:REJECT ,校对通过待审核：PROOFREAD_PASS,审核通过待批准：AUDIT_PASS，批准通过：PASSED)||false|string||
|auditorMemo|审批意见||false|string||
|proofreaderMemo|校对意见||false|string||
|confirmerMemo|批准意见||false|string||
|auditorTime|审批时间||false|string||
|proofreaderTime|校对时间||false|string||
|confirmerTime|批准时间||false|string||
|fileUrl|生成的文件路径||false|string||
|memo|||false|string||
|experimentRatingList|试验评分||false|array|ExperimentRating|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|实验ID||false|string||
|rateUserId|评分人||false|string||
|totalScore|总分数||false|string||
|baseScore|基础分||false|string||
|completionScore|完成分||false|string||
|difficultyScore|难度分||false|string||
|managementScore|管理支撑分||false|string||
|experimentName|||false|string||
|experimentNo|||false|string||
|rateUserName|||false|string||
|rateUserList|试验评分||false|array|ExperimentRatingUserDTO|
|rateUserId|评分人||false|string||
|totalScore|总分数||false|string||
|baseScore|基础分||false|string||
|completionScore|完成分||false|string||
|difficultyScore|难度分||false|string||
|managementScore|管理支撑分||false|string||
|sampleType|||false|string||
|sampleName|||false|string||
|equipmentModel|||false|string||
|sampleModel|||false|string||
|totalCountXhy|西核院统计||false|integer(int32)||
|totalCountLhs|理化所统计||false|integer(int32)||
|reportStatus|理化所统计||false|string||
|pageName|试验页面||false|string||
|radiationStandard|辐照标准||false|string||
|experimentReview|试验评定结果||false|ExperimentReview|ExperimentReview|
|id|id||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|experimentName|||false|string||
|sampleId|样品ID||false|string||
|sampleName|||false|string||
|processType|试验流程标准,可用值:GJB548C,ESCC229000,ESCC22500,QJ10004||false|string||
|needAnneal|是否需要退火,可用值:Y,N||false|string||
|targetedAnneal|是否进行了针对性的退火表征试验,可用值:Y,N||false|string||
|needRadiation|是否需要辐照,可用值:Y,N||false|string||
|needBurnin|是否需要老练,可用值:Y,N||false|string||
|result|评定结果||false|string||
|reviewStatus|评定状态||false|string||
|matchedRuleList|试验评定规则||false|array|ExperimentReviewRule|
|id|id||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|processType|试验流程标准||false|string||
|title|建议标题||false|string||
|result|评定结果||false|string||
|impactDesc|损伤机理||false|string||
|impactScope|适用范围||false|string||
|expression|规则表达式||false|string||
|priority|规则优先级||false|integer||
|experimentReviewList|||false|array|ExperimentReviewDTO|
|experimentId|||false|string||
|sampleName|||false|string||
|sampleId|||false|string||
|processType|||false|string||
|reviewResult|||false|string||


## 试验管理-暂存


**接口地址**:`/api/database/experiment/staging`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experiment|试验管理|body|true|Experiment|Experiment|
|id|主键||false|string||
|indexNo|序号||false|integer(int32)||
|experimentNo|试验编号||false|string||
|name|名称||false|string||
|clientName|委托方名称||false|string||
|type|试验类型||false|string||
|startDate|试验开始日期||false|string||
|endDate|试验结束日期||false|string||
|radiationSourceType|辐射源类型||false|string||
|supervisor|||false|string||
|supervisorName|||false|string||
|status|状态||false|string||
|statusList|||false|array|string|
|sampleInfoList|试验样品信息||false|array|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|irradiationBoardList|试验辐照板信息||false|array|ExperimentIrradiationBoard|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleId|样品ID||false|string||
|sampleInfo|试验样品信息||false|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|sampleName|样品名称||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|irradiationBoardNumber|辐照板数量||false|string||
|irradiationBoardCode|辐照板编号||false|string||
|measurementValidity|计量有效期||false|string||
|sourceArea|占源面积||false|string||
|irradiationBoardImage|辐照板图片||false|string||
|deviationConditionList|试验偏置条件信息||false|array|ExperimentDeviationCondition|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleId|样品ID||false|string||
|sampleInfo|试验样品信息||false|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|sampleName|样品名称||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleNumber|样品编号||false|string||
|offsetCondition|偏置条件||false|string||
|offsetVoltage|偏置电压||false|string||
|offsetCurrent|偏置电流||false|string||
|loadCapacity|负载||false|string||
|otherCondition|其它条件||false|string||
|offsetPowerPhoto|偏置电源显示界面照片||false|string||
|inputSignal|输入信号||false|string||
|signalPhoto|信号显示界面源照片||false|string||
|pinType|管脚接入方式||false|string||
|signalType|信号类型||false|string||
|signalFrequency|信号频率||false|string||
|signalAmplitude|信号幅值||false|string||
|dutyCycle|占空比||false|string||
|deviationEquipmentList|试验加偏设备信息||false|array|ExperimentDeviationEquipment|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleId|样品ID||false|string||
|sampleInfo|试验样品信息||false|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|sampleName|样品名称||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|equipmentModel|设备型号||false|string||
|equipmentId|设备名称||false|string||
|equipmentName|设备名称||false|string||
|equipment|设备||false|Equipment|Equipment|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|name|名称||false|string||
|model|型号||false|string||
|factoryNo|出厂编号||false|string||
|managementNo|管理编号||false|string||
|expireDate|有效期||false|string||
|roomNo|房间号||false|string||
|images|图片||false|string||
|equipmentType|设备类型||false|string||
|equipmentImage|设备图片||false|string||
|measurementValidity|计量有效期||false|string||
|roomNo|房间号||false|string||
|factoryNo|出厂编号||false|string||
|managementNo|管理编号||false|string||
|copyCount|复制次数||false|integer(int32)||
|fileList|试验文件||false|array|ExperimentFile|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|fileName|文件名称||false|string||
|fileType|类型||false|string||
|fileUrl|文件链接||false|string||
|experimentUser|||false|string||
|experimentUserList|试验人员||false|array|ExperimentUser|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|userId|人员||false|string||
|userName|||false|string||
|type|类型||false|string||
|experimentReportList|试验报告||false|array|ExperimentReport|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleInfo|样品信息||false|string||
|imgUrls|偏置原理图||false|string||
|description|偏置原理说明||false|string||
|auditor|审核员||false|string||
|proofreader|校对员||false|string||
|confirmer|确认员||false|string||
|reportName|报告名称||false|string||
|status|状态(申请中待校对:INIT,不合格:REJECT ,校对通过待审核：PROOFREAD_PASS,审核通过待批准：AUDIT_PASS，批准通过：PASSED)||false|string||
|auditorMemo|审批意见||false|string||
|proofreaderMemo|校对意见||false|string||
|confirmerMemo|批准意见||false|string||
|auditorTime|审批时间||false|string||
|proofreaderTime|校对时间||false|string||
|confirmerTime|批准时间||false|string||
|fileUrl|生成的文件路径||false|string||
|memo|||false|string||
|experimentRatingList|试验评分||false|array|ExperimentRating|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|实验ID||false|string||
|rateUserId|评分人||false|string||
|totalScore|总分数||false|string||
|baseScore|基础分||false|string||
|completionScore|完成分||false|string||
|difficultyScore|难度分||false|string||
|managementScore|管理支撑分||false|string||
|experimentName|||false|string||
|experimentNo|||false|string||
|rateUserName|||false|string||
|rateUserList|试验评分||false|array|ExperimentRatingUserDTO|
|rateUserId|评分人||false|string||
|totalScore|总分数||false|string||
|baseScore|基础分||false|string||
|completionScore|完成分||false|string||
|difficultyScore|难度分||false|string||
|managementScore|管理支撑分||false|string||
|sampleType|||false|string||
|sampleName|||false|string||
|equipmentModel|||false|string||
|sampleModel|||false|string||
|totalCountXhy|西核院统计||false|integer(int32)||
|totalCountLhs|理化所统计||false|integer(int32)||
|reportStatus|理化所统计||false|string||
|pageName|试验页面||false|string||
|radiationStandard|辐照标准||false|string||
|experimentReview|试验评定结果||false|ExperimentReview|ExperimentReview|
|id|id||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|experimentName|||false|string||
|sampleId|样品ID||false|string||
|sampleName|||false|string||
|processType|试验流程标准,可用值:GJB548C,ESCC229000,ESCC22500,QJ10004||false|string||
|needAnneal|是否需要退火,可用值:Y,N||false|string||
|targetedAnneal|是否进行了针对性的退火表征试验,可用值:Y,N||false|string||
|needRadiation|是否需要辐照,可用值:Y,N||false|string||
|needBurnin|是否需要老练,可用值:Y,N||false|string||
|result|评定结果||false|string||
|reviewStatus|评定状态||false|string||
|matchedRuleList|试验评定规则||false|array|ExperimentReviewRule|
|id|id||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|processType|试验流程标准||false|string||
|title|建议标题||false|string||
|result|评定结果||false|string||
|impactDesc|损伤机理||false|string||
|impactScope|适用范围||false|string||
|expression|规则表达式||false|string||
|priority|规则优先级||false|integer||
|experimentReviewList|||false|array|ExperimentReviewDTO|
|experimentId|||false|string||
|sampleName|||false|string||
|sampleId|||false|string||
|processType|||false|string||
|reviewResult|||false|string||


## 试验管理-试验复制


**接口地址**:`/api/database/experiment/copy`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 试验管理-完成试验


**接口地址**:`/api/database/experiment/complete`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experiment|试验管理|body|true|Experiment|Experiment|
|id|主键||false|string||
|indexNo|序号||false|integer(int32)||
|experimentNo|试验编号||false|string||
|name|名称||false|string||
|clientName|委托方名称||false|string||
|type|试验类型||false|string||
|startDate|试验开始日期||false|string||
|endDate|试验结束日期||false|string||
|radiationSourceType|辐射源类型||false|string||
|supervisor|||false|string||
|supervisorName|||false|string||
|status|状态||false|string||
|statusList|||false|array|string|
|sampleInfoList|试验样品信息||false|array|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|irradiationBoardList|试验辐照板信息||false|array|ExperimentIrradiationBoard|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleId|样品ID||false|string||
|sampleInfo|试验样品信息||false|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|sampleName|样品名称||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|irradiationBoardNumber|辐照板数量||false|string||
|irradiationBoardCode|辐照板编号||false|string||
|measurementValidity|计量有效期||false|string||
|sourceArea|占源面积||false|string||
|irradiationBoardImage|辐照板图片||false|string||
|deviationConditionList|试验偏置条件信息||false|array|ExperimentDeviationCondition|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleId|样品ID||false|string||
|sampleInfo|试验样品信息||false|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|sampleName|样品名称||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleNumber|样品编号||false|string||
|offsetCondition|偏置条件||false|string||
|offsetVoltage|偏置电压||false|string||
|offsetCurrent|偏置电流||false|string||
|loadCapacity|负载||false|string||
|otherCondition|其它条件||false|string||
|offsetPowerPhoto|偏置电源显示界面照片||false|string||
|inputSignal|输入信号||false|string||
|signalPhoto|信号显示界面源照片||false|string||
|pinType|管脚接入方式||false|string||
|signalType|信号类型||false|string||
|signalFrequency|信号频率||false|string||
|signalAmplitude|信号幅值||false|string||
|dutyCycle|占空比||false|string||
|deviationEquipmentList|试验加偏设备信息||false|array|ExperimentDeviationEquipment|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleId|样品ID||false|string||
|sampleInfo|试验样品信息||false|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|sampleName|样品名称||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|equipmentModel|设备型号||false|string||
|equipmentId|设备名称||false|string||
|equipmentName|设备名称||false|string||
|equipment|设备||false|Equipment|Equipment|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|name|名称||false|string||
|model|型号||false|string||
|factoryNo|出厂编号||false|string||
|managementNo|管理编号||false|string||
|expireDate|有效期||false|string||
|roomNo|房间号||false|string||
|images|图片||false|string||
|equipmentType|设备类型||false|string||
|equipmentImage|设备图片||false|string||
|measurementValidity|计量有效期||false|string||
|roomNo|房间号||false|string||
|factoryNo|出厂编号||false|string||
|managementNo|管理编号||false|string||
|copyCount|复制次数||false|integer(int32)||
|fileList|试验文件||false|array|ExperimentFile|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|fileName|文件名称||false|string||
|fileType|类型||false|string||
|fileUrl|文件链接||false|string||
|experimentUser|||false|string||
|experimentUserList|试验人员||false|array|ExperimentUser|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|userId|人员||false|string||
|userName|||false|string||
|type|类型||false|string||
|experimentReportList|试验报告||false|array|ExperimentReport|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleInfo|样品信息||false|string||
|imgUrls|偏置原理图||false|string||
|description|偏置原理说明||false|string||
|auditor|审核员||false|string||
|proofreader|校对员||false|string||
|confirmer|确认员||false|string||
|reportName|报告名称||false|string||
|status|状态(申请中待校对:INIT,不合格:REJECT ,校对通过待审核：PROOFREAD_PASS,审核通过待批准：AUDIT_PASS，批准通过：PASSED)||false|string||
|auditorMemo|审批意见||false|string||
|proofreaderMemo|校对意见||false|string||
|confirmerMemo|批准意见||false|string||
|auditorTime|审批时间||false|string||
|proofreaderTime|校对时间||false|string||
|confirmerTime|批准时间||false|string||
|fileUrl|生成的文件路径||false|string||
|memo|||false|string||
|experimentRatingList|试验评分||false|array|ExperimentRating|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|实验ID||false|string||
|rateUserId|评分人||false|string||
|totalScore|总分数||false|string||
|baseScore|基础分||false|string||
|completionScore|完成分||false|string||
|difficultyScore|难度分||false|string||
|managementScore|管理支撑分||false|string||
|experimentName|||false|string||
|experimentNo|||false|string||
|rateUserName|||false|string||
|rateUserList|试验评分||false|array|ExperimentRatingUserDTO|
|rateUserId|评分人||false|string||
|totalScore|总分数||false|string||
|baseScore|基础分||false|string||
|completionScore|完成分||false|string||
|difficultyScore|难度分||false|string||
|managementScore|管理支撑分||false|string||
|sampleType|||false|string||
|sampleName|||false|string||
|equipmentModel|||false|string||
|sampleModel|||false|string||
|totalCountXhy|西核院统计||false|integer(int32)||
|totalCountLhs|理化所统计||false|integer(int32)||
|reportStatus|理化所统计||false|string||
|pageName|试验页面||false|string||
|radiationStandard|辐照标准||false|string||
|experimentReview|试验评定结果||false|ExperimentReview|ExperimentReview|
|id|id||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|experimentName|||false|string||
|sampleId|样品ID||false|string||
|sampleName|||false|string||
|processType|试验流程标准,可用值:GJB548C,ESCC229000,ESCC22500,QJ10004||false|string||
|needAnneal|是否需要退火,可用值:Y,N||false|string||
|targetedAnneal|是否进行了针对性的退火表征试验,可用值:Y,N||false|string||
|needRadiation|是否需要辐照,可用值:Y,N||false|string||
|needBurnin|是否需要老练,可用值:Y,N||false|string||
|result|评定结果||false|string||
|reviewStatus|评定状态||false|string||
|matchedRuleList|试验评定规则||false|array|ExperimentReviewRule|
|id|id||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|processType|试验流程标准||false|string||
|title|建议标题||false|string||
|result|评定结果||false|string||
|impactDesc|损伤机理||false|string||
|impactScope|适用范围||false|string||
|expression|规则表达式||false|string||
|priority|规则优先级||false|integer||
|experimentReviewList|||false|array|ExperimentReviewDTO|
|experimentId|||false|string||
|sampleName|||false|string||
|sampleId|||false|string||
|processType|||false|string||
|reviewResult|||false|string||


## 试验管理-添加


**接口地址**:`/api/database/experiment/add`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>添加试验,传sampleInfoList,deviationConditionList,deviationEquipmentList,irradiationBoardList,不用传fileList. </br> experimentUser 用,隔开</p>


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experiment|试验管理|body|true|Experiment|Experiment|
|id|主键||false|string||
|indexNo|序号||false|integer(int32)||
|experimentNo|试验编号||false|string||
|name|名称||false|string||
|clientName|委托方名称||false|string||
|type|试验类型||false|string||
|startDate|试验开始日期||false|string||
|endDate|试验结束日期||false|string||
|radiationSourceType|辐射源类型||false|string||
|supervisor|||false|string||
|supervisorName|||false|string||
|status|状态||false|string||
|statusList|||false|array|string|
|sampleInfoList|试验样品信息||false|array|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|irradiationBoardList|试验辐照板信息||false|array|ExperimentIrradiationBoard|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleId|样品ID||false|string||
|sampleInfo|试验样品信息||false|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|sampleName|样品名称||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|irradiationBoardNumber|辐照板数量||false|string||
|irradiationBoardCode|辐照板编号||false|string||
|measurementValidity|计量有效期||false|string||
|sourceArea|占源面积||false|string||
|irradiationBoardImage|辐照板图片||false|string||
|deviationConditionList|试验偏置条件信息||false|array|ExperimentDeviationCondition|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleId|样品ID||false|string||
|sampleInfo|试验样品信息||false|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|sampleName|样品名称||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleNumber|样品编号||false|string||
|offsetCondition|偏置条件||false|string||
|offsetVoltage|偏置电压||false|string||
|offsetCurrent|偏置电流||false|string||
|loadCapacity|负载||false|string||
|otherCondition|其它条件||false|string||
|offsetPowerPhoto|偏置电源显示界面照片||false|string||
|inputSignal|输入信号||false|string||
|signalPhoto|信号显示界面源照片||false|string||
|pinType|管脚接入方式||false|string||
|signalType|信号类型||false|string||
|signalFrequency|信号频率||false|string||
|signalAmplitude|信号幅值||false|string||
|dutyCycle|占空比||false|string||
|deviationEquipmentList|试验加偏设备信息||false|array|ExperimentDeviationEquipment|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleId|样品ID||false|string||
|sampleInfo|试验样品信息||false|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|sampleName|样品名称||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|equipmentModel|设备型号||false|string||
|equipmentId|设备名称||false|string||
|equipmentName|设备名称||false|string||
|equipment|设备||false|Equipment|Equipment|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|name|名称||false|string||
|model|型号||false|string||
|factoryNo|出厂编号||false|string||
|managementNo|管理编号||false|string||
|expireDate|有效期||false|string||
|roomNo|房间号||false|string||
|images|图片||false|string||
|equipmentType|设备类型||false|string||
|equipmentImage|设备图片||false|string||
|measurementValidity|计量有效期||false|string||
|roomNo|房间号||false|string||
|factoryNo|出厂编号||false|string||
|managementNo|管理编号||false|string||
|copyCount|复制次数||false|integer(int32)||
|fileList|试验文件||false|array|ExperimentFile|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|fileName|文件名称||false|string||
|fileType|类型||false|string||
|fileUrl|文件链接||false|string||
|experimentUser|||false|string||
|experimentUserList|试验人员||false|array|ExperimentUser|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|userId|人员||false|string||
|userName|||false|string||
|type|类型||false|string||
|experimentReportList|试验报告||false|array|ExperimentReport|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleInfo|样品信息||false|string||
|imgUrls|偏置原理图||false|string||
|description|偏置原理说明||false|string||
|auditor|审核员||false|string||
|proofreader|校对员||false|string||
|confirmer|确认员||false|string||
|reportName|报告名称||false|string||
|status|状态(申请中待校对:INIT,不合格:REJECT ,校对通过待审核：PROOFREAD_PASS,审核通过待批准：AUDIT_PASS，批准通过：PASSED)||false|string||
|auditorMemo|审批意见||false|string||
|proofreaderMemo|校对意见||false|string||
|confirmerMemo|批准意见||false|string||
|auditorTime|审批时间||false|string||
|proofreaderTime|校对时间||false|string||
|confirmerTime|批准时间||false|string||
|fileUrl|生成的文件路径||false|string||
|memo|||false|string||
|experimentRatingList|试验评分||false|array|ExperimentRating|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|实验ID||false|string||
|rateUserId|评分人||false|string||
|totalScore|总分数||false|string||
|baseScore|基础分||false|string||
|completionScore|完成分||false|string||
|difficultyScore|难度分||false|string||
|managementScore|管理支撑分||false|string||
|experimentName|||false|string||
|experimentNo|||false|string||
|rateUserName|||false|string||
|rateUserList|试验评分||false|array|ExperimentRatingUserDTO|
|rateUserId|评分人||false|string||
|totalScore|总分数||false|string||
|baseScore|基础分||false|string||
|completionScore|完成分||false|string||
|difficultyScore|难度分||false|string||
|managementScore|管理支撑分||false|string||
|sampleType|||false|string||
|sampleName|||false|string||
|equipmentModel|||false|string||
|sampleModel|||false|string||
|totalCountXhy|西核院统计||false|integer(int32)||
|totalCountLhs|理化所统计||false|integer(int32)||
|reportStatus|理化所统计||false|string||
|pageName|试验页面||false|string||
|radiationStandard|辐照标准||false|string||
|experimentReview|试验评定结果||false|ExperimentReview|ExperimentReview|
|id|id||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|experimentName|||false|string||
|sampleId|样品ID||false|string||
|sampleName|||false|string||
|processType|试验流程标准,可用值:GJB548C,ESCC229000,ESCC22500,QJ10004||false|string||
|needAnneal|是否需要退火,可用值:Y,N||false|string||
|targetedAnneal|是否进行了针对性的退火表征试验,可用值:Y,N||false|string||
|needRadiation|是否需要辐照,可用值:Y,N||false|string||
|needBurnin|是否需要老练,可用值:Y,N||false|string||
|result|评定结果||false|string||
|reviewStatus|评定状态||false|string||
|matchedRuleList|试验评定规则||false|array|ExperimentReviewRule|
|id|id||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|processType|试验流程标准||false|string||
|title|建议标题||false|string||
|result|评定结果||false|string||
|impactDesc|损伤机理||false|string||
|impactScope|适用范围||false|string||
|expression|规则表达式||false|string||
|priority|规则优先级||false|integer||
|experimentReviewList|||false|array|ExperimentReviewDTO|
|experimentId|||false|string||
|sampleName|||false|string||
|sampleId|||false|string||
|processType|||false|string||
|reviewResult|||false|string||


## 试验管理-通过id查询


**接口地址**:`/api/database/experiment/queryById`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 试验管理-分页列表查询


**接口地址**:`/api/database/experiment/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|experiment|试验管理|query|true|Experiment|Experiment|
|id|主键||false|string||
|indexNo|序号||false|integer(int32)||
|experimentNo|试验编号||false|string||
|name|名称||false|string||
|clientName|委托方名称||false|string||
|type|试验类型||false|string||
|startDate|试验开始日期||false|string||
|endDate|试验结束日期||false|string||
|radiationSourceType|辐射源类型||false|string||
|supervisor|||false|string||
|supervisorName|||false|string||
|status|状态||false|string||
|statusList|||false|array|string|
|sampleInfoList|试验样品信息||false|array|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|irradiationBoardList|试验辐照板信息||false|array|ExperimentIrradiationBoard|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleId|样品ID||false|string||
|sampleInfo|试验样品信息||false|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|sampleName|样品名称||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|irradiationBoardNumber|辐照板数量||false|string||
|irradiationBoardCode|辐照板编号||false|string||
|measurementValidity|计量有效期||false|string||
|sourceArea|占源面积||false|string||
|irradiationBoardImage|辐照板图片||false|string||
|deviationConditionList|试验偏置条件信息||false|array|ExperimentDeviationCondition|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleId|样品ID||false|string||
|sampleInfo|试验样品信息||false|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|sampleName|样品名称||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleNumber|样品编号||false|string||
|offsetCondition|偏置条件||false|string||
|offsetVoltage|偏置电压||false|string||
|offsetCurrent|偏置电流||false|string||
|loadCapacity|负载||false|string||
|otherCondition|其它条件||false|string||
|offsetPowerPhoto|偏置电源显示界面照片||false|string||
|inputSignal|输入信号||false|string||
|signalPhoto|信号显示界面源照片||false|string||
|pinType|管脚接入方式||false|string||
|signalType|信号类型||false|string||
|signalFrequency|信号频率||false|string||
|signalAmplitude|信号幅值||false|string||
|dutyCycle|占空比||false|string||
|deviationEquipmentList|试验加偏设备信息||false|array|ExperimentDeviationEquipment|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleId|样品ID||false|string||
|sampleInfo|试验样品信息||false|ExperimentSampleInfo|ExperimentSampleInfo|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|样品批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|sampleName|样品名称||false|string||
|sampleName|样品名称||false|string||
|sampleType|样品类型||false|string||
|sampleModel|样品型号||false|string||
|sampleBatch|批次||false|string||
|modelBatch|型号批次||false|string||
|sampleManufacturer|生产厂家||false|string||
|sampleImage|图片||false|string||
|equipmentModel|设备型号||false|string||
|equipmentId|设备名称||false|string||
|equipmentName|设备名称||false|string||
|equipment|设备||false|Equipment|Equipment|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|name|名称||false|string||
|model|型号||false|string||
|factoryNo|出厂编号||false|string||
|managementNo|管理编号||false|string||
|expireDate|有效期||false|string||
|roomNo|房间号||false|string||
|images|图片||false|string||
|equipmentType|设备类型||false|string||
|equipmentImage|设备图片||false|string||
|measurementValidity|计量有效期||false|string||
|roomNo|房间号||false|string||
|factoryNo|出厂编号||false|string||
|managementNo|管理编号||false|string||
|copyCount|复制次数||false|integer(int32)||
|fileList|试验文件||false|array|ExperimentFile|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|fileName|文件名称||false|string||
|fileType|类型||false|string||
|fileUrl|文件链接||false|string||
|experimentUser|||false|string||
|experimentUserList|试验人员||false|array|ExperimentUser|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|userId|人员||false|string||
|userName|||false|string||
|type|类型||false|string||
|experimentReportList|试验报告||false|array|ExperimentReport|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|sampleInfo|样品信息||false|string||
|imgUrls|偏置原理图||false|string||
|description|偏置原理说明||false|string||
|auditor|审核员||false|string||
|proofreader|校对员||false|string||
|confirmer|确认员||false|string||
|reportName|报告名称||false|string||
|status|状态(申请中待校对:INIT,不合格:REJECT ,校对通过待审核：PROOFREAD_PASS,审核通过待批准：AUDIT_PASS，批准通过：PASSED)||false|string||
|auditorMemo|审批意见||false|string||
|proofreaderMemo|校对意见||false|string||
|confirmerMemo|批准意见||false|string||
|auditorTime|审批时间||false|string||
|proofreaderTime|校对时间||false|string||
|confirmerTime|批准时间||false|string||
|fileUrl|生成的文件路径||false|string||
|memo|||false|string||
|experimentRatingList|试验评分||false|array|ExperimentRating|
|id|主键||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|实验ID||false|string||
|rateUserId|评分人||false|string||
|totalScore|总分数||false|string||
|baseScore|基础分||false|string||
|completionScore|完成分||false|string||
|difficultyScore|难度分||false|string||
|managementScore|管理支撑分||false|string||
|experimentName|||false|string||
|experimentNo|||false|string||
|rateUserName|||false|string||
|rateUserList|试验评分||false|array|ExperimentRatingUserDTO|
|rateUserId|评分人||false|string||
|totalScore|总分数||false|string||
|baseScore|基础分||false|string||
|completionScore|完成分||false|string||
|difficultyScore|难度分||false|string||
|managementScore|管理支撑分||false|string||
|sampleType|||false|string||
|sampleName|||false|string||
|equipmentModel|||false|string||
|sampleModel|||false|string||
|totalCountXhy|西核院统计||false|integer(int32)||
|totalCountLhs|理化所统计||false|integer(int32)||
|reportStatus|理化所统计||false|string||
|pageName|试验页面||false|string||
|radiationStandard|辐照标准||false|string||
|experimentReview|试验评定结果||false|ExperimentReview|ExperimentReview|
|id|id||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|experimentId|试验ID||false|string||
|experimentName|||false|string||
|sampleId|样品ID||false|string||
|sampleName|||false|string||
|processType|试验流程标准,可用值:GJB548C,ESCC229000,ESCC22500,QJ10004||false|string||
|needAnneal|是否需要退火,可用值:Y,N||false|string||
|targetedAnneal|是否进行了针对性的退火表征试验,可用值:Y,N||false|string||
|needRadiation|是否需要辐照,可用值:Y,N||false|string||
|needBurnin|是否需要老练,可用值:Y,N||false|string||
|result|评定结果||false|string||
|reviewStatus|评定状态||false|string||
|matchedRuleList|试验评定规则||false|array|ExperimentReviewRule|
|id|id||false|string||
|createBy|创建人||false|string||
|createTime|创建日期||false|string||
|updateBy|更新人||false|string||
|updateTime|更新日期||false|string||
|sysOrgCode|所属部门||false|string||
|processType|试验流程标准||false|string||
|title|建议标题||false|string||
|result|评定结果||false|string||
|impactDesc|损伤机理||false|string||
|impactScope|适用范围||false|string||
|expression|规则表达式||false|string||
|priority|规则优先级||false|integer||
|experimentReviewList|||false|array|ExperimentReviewDTO|
|experimentId|||false|string||
|sampleName|||false|string||
|sampleId|||false|string||
|processType|||false|string||
|reviewResult|||false|string||
|pageNo||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||
|pageName||query|false|string||


## 试验管理-检查能否生成报告


**接口地址**:`/api/database/experiment/checkCanReport`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 试验管理-通过id删除


**接口地址**:`/api/database/experiment/delete`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 试验管理-批量删除


**接口地址**:`/api/database/experiment/deleteBatch`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ids||query|true|string||


# 设备


## 设备-编辑


**接口地址**:`/api/database/equipment/edit`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|equipment|设备|body|true|Equipment|Equipment|
|id|主键||false|string||
|name|名称||false|string||
|model|型号||false|string||
|factoryNo|出厂编号||false|string||
|managementNo|管理编号||false|string||
|expireDate|有效期||false|string||
|roomNo|房间号||false|string||
|images|图片||false|string||


## 设备-编辑


**接口地址**:`/api/database/equipment/edit`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|equipment|设备|body|true|Equipment|Equipment|
|id|主键||false|string||
|name|名称||false|string||
|model|型号||false|string||
|factoryNo|出厂编号||false|string||
|managementNo|管理编号||false|string||
|expireDate|有效期||false|string||
|roomNo|房间号||false|string||
|images|图片||false|string||


## 设备-添加


**接口地址**:`/api/database/equipment/add`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|equipment|设备|body|true|Equipment|Equipment|
|id|主键||false|string||
|name|名称||false|string||
|model|型号||false|string||
|factoryNo|出厂编号||false|string||
|managementNo|管理编号||false|string||
|expireDate|有效期||false|string||
|roomNo|房间号||false|string||
|images|图片||false|string||


## 设备-通过id查询


**接口地址**:`/api/database/equipment/queryById`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 设备-分页列表查询


**接口地址**:`/api/database/equipment/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|equipment|设备|query|true|Equipment|Equipment|
|id|主键||false|string||
|name|名称||false|string||
|model|型号||false|string||
|factoryNo|出厂编号||false|string||
|managementNo|管理编号||false|string||
|expireDate|有效期||false|string||
|roomNo|房间号||false|string||
|images|图片||false|string||
|pageNo||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||


## 设备-通过id删除


**接口地址**:`/api/database/equipment/delete`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 设备-批量删除


**接口地址**:`/api/database/equipment/deleteBatch`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ids||query|true|string||


# 我的收藏文档


## 我的收藏文档-编辑


**接口地址**:`/api/database/documentFavorites/edit`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|documentFavorites|我的收藏文档|body|true|DocumentFavorites|DocumentFavorites|
|id|主键||false|string||
|documentId|文档ID||false|string||
|documentTitle|文档名称||false|string||
|userId|用户ID||false|string||


## 我的收藏文档-编辑


**接口地址**:`/api/database/documentFavorites/edit`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|documentFavorites|我的收藏文档|body|true|DocumentFavorites|DocumentFavorites|
|id|主键||false|string||
|documentId|文档ID||false|string||
|documentTitle|文档名称||false|string||
|userId|用户ID||false|string||


## 我的收藏文档-添加


**接口地址**:`/api/database/documentFavorites/add`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|documentFavorites|我的收藏文档|body|true|DocumentFavorites|DocumentFavorites|
|id|主键||false|string||
|documentId|文档ID||false|string||
|documentTitle|文档名称||false|string||
|userId|用户ID||false|string||


## 我的收藏文档-通过id查询


**接口地址**:`/api/database/documentFavorites/queryById`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 我的收藏文档-分页列表查询


**接口地址**:`/api/database/documentFavorites/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|documentFavorites|我的收藏文档|query|true|DocumentFavorites|DocumentFavorites|
|id|主键||false|string||
|documentId|文档ID||false|string||
|documentTitle|文档名称||false|string||
|userId|用户ID||false|string||
|pageNo||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||


## 我的收藏文档-通过id删除


**接口地址**:`/api/database/documentFavorites/delete`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 我的收藏文档-批量删除


**接口地址**:`/api/database/documentFavorites/deleteBatch`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ids||query|true|string||


# 样品元器件模糊预搜索API


## 器件搜索数据-编辑


**接口地址**:`/api/database/componentSearch/edit`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|searchResult|系统预搜索的信息模型|body|true|SearchResult|SearchResult|
|id|主键||false|string||
|dataType|数据类型||false|integer(int32)||
|content|搜索内容||false|string||
|extentInfo|扩展信息||false|string||


## 器件搜索数据-编辑


**接口地址**:`/api/database/componentSearch/edit`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|searchResult|系统预搜索的信息模型|body|true|SearchResult|SearchResult|
|id|主键||false|string||
|dataType|数据类型||false|integer(int32)||
|content|搜索内容||false|string||
|extentInfo|扩展信息||false|string||


## 器件搜索数据-添加


**接口地址**:`/api/database/componentSearch/add`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|searchResult|系统预搜索的信息模型|body|true|SearchResult|SearchResult|
|id|主键||false|string||
|dataType|数据类型||false|integer(int32)||
|content|搜索内容||false|string||
|extentInfo|扩展信息||false|string||


## 器件查询 - 分页查询API


**接口地址**:`/api/database/componentSearch/search`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|type||query|false|integer(int32)||
|content||query|false|string||


## 器件查询 - 分页查询API


**接口地址**:`/api/database/componentSearch/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|type||query|false|integer(int32)||
|content||query|false|string||
|pageNo||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||


## 器件搜索数据-通过id删除


**接口地址**:`/api/database/componentSearch/delete`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


# 元器件


## 元器件-编辑


**接口地址**:`/api/database/component/edit`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|component|元器件|body|true|Component|Component|
|id|主键||false|string||
|name|名称||false|string||
|model|型号||false|string||
|size|特征尺寸||false|string||
|material|器件材料||false|string||
|technology|器件工艺||false|string||
|manufacturer|生产厂家||false|string||
|batchNo|批次||false|string||
|oemLine|代工线||false|string||
|type|类型||false|string||
|attachment|附件||false|string||


## 元器件-编辑


**接口地址**:`/api/database/component/edit`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|component|元器件|body|true|Component|Component|
|id|主键||false|string||
|name|名称||false|string||
|model|型号||false|string||
|size|特征尺寸||false|string||
|material|器件材料||false|string||
|technology|器件工艺||false|string||
|manufacturer|生产厂家||false|string||
|batchNo|批次||false|string||
|oemLine|代工线||false|string||
|type|类型||false|string||
|attachment|附件||false|string||


## 元器件-添加


**接口地址**:`/api/database/component/add`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|component|元器件|body|true|Component|Component|
|id|主键||false|string||
|name|名称||false|string||
|model|型号||false|string||
|size|特征尺寸||false|string||
|material|器件材料||false|string||
|technology|器件工艺||false|string||
|manufacturer|生产厂家||false|string||
|batchNo|批次||false|string||
|oemLine|代工线||false|string||
|type|类型||false|string||
|attachment|附件||false|string||


## 元器件-通过id查询


**接口地址**:`/api/database/component/queryById`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 元器件-分页列表查询


**接口地址**:`/api/database/component/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|component|元器件|query|true|Component|Component|
|id|主键||false|string||
|name|名称||false|string||
|model|型号||false|string||
|size|特征尺寸||false|string||
|material|器件材料||false|string||
|technology|器件工艺||false|string||
|manufacturer|生产厂家||false|string||
|batchNo|批次||false|string||
|oemLine|代工线||false|string||
|type|类型||false|string||
|attachment|附件||false|string||
|pageNo||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||


## 元器件-通过id删除


**接口地址**:`/api/database/component/delete`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 元器件-批量删除


**接口地址**:`/api/database/component/deleteBatch`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ids||query|true|string||


# cnas测试参数列表


## cnas测试参数列表-编辑


**接口地址**:`/api/database/cnasTest/edit`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|cnasTest|cnas测试参数列表|body|true|CnasTest|CnasTest|
|id|主键||false|string||
|testTarget|检测对象||false|string||
|parameter|参数||false|string||
|testStandard|检测标准||false|string||
|time|创建时间||false|string(date-time)||


## cnas测试参数列表-编辑


**接口地址**:`/api/database/cnasTest/edit`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|cnasTest|cnas测试参数列表|body|true|CnasTest|CnasTest|
|id|主键||false|string||
|testTarget|检测对象||false|string||
|parameter|参数||false|string||
|testStandard|检测标准||false|string||
|time|创建时间||false|string(date-time)||


## cnas测试参数列表-添加


**接口地址**:`/api/database/cnasTest/add`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|cnasTest|cnas测试参数列表|body|true|CnasTest|CnasTest|
|id|主键||false|string||
|testTarget|检测对象||false|string||
|parameter|参数||false|string||
|testStandard|检测标准||false|string||
|time|创建时间||false|string(date-time)||


## cnas测试参数列表-通过id查询


**接口地址**:`/api/database/cnasTest/queryById`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## cnas测试参数列表-分页列表查询


**接口地址**:`/api/database/cnasTest/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|cnasTest|cnas测试参数列表|query|true|CnasTest|CnasTest|
|id|主键||false|string||
|testTarget|检测对象||false|string||
|parameter|参数||false|string||
|testStandard|检测标准||false|string||
|time|创建时间||false|string(date-time)||
|pageNo||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||


## cnas测试参数列表-通过id删除


**接口地址**:`/api/database/cnasTest/delete`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## cnas测试参数列表-批量删除


**接口地址**:`/api/database/cnasTest/deleteBatch`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ids||query|true|string||


# 委托方


## 委托方-编辑


**接口地址**:`/api/database/client/edit`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|client|委托方|body|true|Client|Client|
|id|主键||false|string||
|name|名称||false|string||
|address|地址||false|string||


## 委托方-编辑


**接口地址**:`/api/database/client/edit`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|client|委托方|body|true|Client|Client|
|id|主键||false|string||
|name|名称||false|string||
|address|地址||false|string||


## 委托方-添加


**接口地址**:`/api/database/client/add`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|client|委托方|body|true|Client|Client|
|id|主键||false|string||
|name|名称||false|string||
|address|地址||false|string||


## 委托方-通过id查询


**接口地址**:`/api/database/client/queryById`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 委托方-分页列表查询


**接口地址**:`/api/database/client/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|client|委托方|query|true|Client|Client|
|id|主键||false|string||
|name|名称||false|string||
|address|地址||false|string||
|pageNo||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||


## 委托方-通过id删除


**接口地址**:`/api/database/client/delete`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


## 委托方-批量删除


**接口地址**:`/api/database/client/deleteBatch`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ids||query|true|string||


# 用户登录


## 扫码登录二维码


**接口地址**:`/api/sys/scanLoginQrcode`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|qrcodeId||query|true|string||
|token||query|true|string||


## 手机号登录接口


**接口地址**:`/api/sys/phoneLogin`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|jSONObject|JSONObject|body|true|JSONObject|JSONObject|
|empty|||false|boolean||
|innerMap|||false|object||


## 登录接口


**接口地址**:`/api/sys/login`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sysLoginModel|登录对象|body|true|SysLoginModel|SysLoginModel|
|username|账号||false|string||
|password|密码||false|string||
|captcha|验证码||false|string||
|checkKey|验证码key||false|string||


## 获取验证码


**接口地址**:`/api/sys/randomImage/{key}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|key||path|true|string||


## 获取用户扫码后保存的token


**接口地址**:`/api/sys/getQrcodeToken`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|qrcodeId||query|true|string||


## 登录二维码


**接口地址**:`/api/sys/getLoginQrcode`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


暂无


# 试验实施暂存数据API


## 器件搜索数据-添加


**接口地址**:`/api/database/stageData/addOrModify`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|stageData|暂存的数据|body|true|StageData|StageData|
|id|主键||false|string||
|bizId|||false|string||
|dataType|数据类型||false|integer(int32)||
|content|暂存的数据||false|string||


## 器件搜索数据-通过bizId查询


**接口地址**:`/api/database/stageData/queryByBizId`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|bizId||query|true|string||
|dataType||query|true|integer(int32)||


# oss-file-controller


## 文件管理-通过多个id逗号分割批量查询查询


**接口地址**:`/api/sys/oss/file/queryByIds`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ids||query|true|string||


## 文件管理-通过id查询


**接口地址**:`/api/sys/oss/file/queryById`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||query|true|string||


# 重复校验


## 重复校验接口


**接口地址**:`/api/sys/duplicate/check`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|duplicateCheckVo|重复校验数据模型|query|true|DuplicateCheckVo|DuplicateCheckVo|
|tableName|表名||false|string||
|fieldName|字段名||false|string||
|fieldVal|字段值||false|string||
|dataId|数据ID||false|string||


# 数据字典


## 字典重复校验接口


**接口地址**:`/api/sys/dictItem/dictItemCheck`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sysDictItem||query|true|SysDictItem|SysDictItem|
|id|||false|string||
|dictId|||false|string||
|itemText|||false|string||
|itemValue|||false|string||
|description|||false|string||
|sortOrder|||false|integer(int32)||
|status|||false|integer(int32)||
|itemColor|||false|string||


# 试验序列


## 试验序列-获取序号


**接口地址**:`/api/database/experimentSequence/next`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**请求参数**:


暂无


