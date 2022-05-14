package com.zhm.drug.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhm.drug.entity.Permission;
import com.zhm.drug.entity.RolePermission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionMapper extends BaseMapper<Permission> {

    @Select("select * from role_permission where role_id = #{roleId}")
    List<RolePermission> getRolePermissionByRoleId(Integer roleId);

    @Delete("delete from role_permission where role_id = #{roleId}")
    int deletePermissionsByRoleId(Integer roleId);

    @Insert("insert into role_permission(role_id, permission_id) values(#{roleId}, #{permissionId})")
    int insertRoleAndPermission(@Param("roleId") Integer roleId, @Param("permissionId") Integer permissionId);

}
