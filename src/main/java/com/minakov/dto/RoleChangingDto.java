package com.minakov.dto;

import java.util.Objects;

public class RoleChangingDto {

    private Long roleId;

    public RoleChangingDto() {
    }

    public RoleChangingDto(Long roleId) {
        this.roleId = roleId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleChangingDto that = (RoleChangingDto) o;
        return Objects.equals(roleId, that.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId);
    }
}
