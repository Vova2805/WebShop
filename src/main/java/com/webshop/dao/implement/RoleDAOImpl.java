package com.webshop.dao.implement;

import com.webshop.dao.generic.GenericDAOImpl;
import com.webshop.dao.interfaces.IRoleDAO;
import com.webshop.model.classes.Role;
import com.webshop.model.enums.UserRole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDAOImpl extends GenericDAOImpl<Role, Integer> implements IRoleDAO {

    @Override
    public Role addRole(UserRole userRole) {
        if (!alreadyExist(userRole)) {
            add(new Role(userRole.toString()));
        }
        return loadRoleByTitle(userRole);
    }

    @Override
    public void deleteRoleByTitle(UserRole userRole) {
        super.delete(getRoleId(userRole));
    }

    @Override
    public Role loadRoleByID(int id) {
        return (Role) super.getSession().get(super.getType(), id);
    }

    @Override
    public Role loadRoleByTitle(UserRole userRole) {
        return loadRoleByID(getRoleId(userRole));
    }

    @Override
    public int getRoleId(UserRole userRole) {
        List<Role> roles = getAll();
        if (roles == null) super.add(new Role(userRole.toString()));
        else {
            for (Role role : roles) {
                if (role.getRole().equals(userRole.toString())) {
                    return role.getRoleId();
                }
            }
            super.add(new Role(userRole.toString()));
        }
        return loadRoleByTitle(userRole).getRoleId();
    }

    private boolean alreadyExist(UserRole userRole) {
        List<Role> roles = getAll();
        if (roles == null) return false;

        for (Role role : roles) {
            if (role.getRole().equals(userRole.toString())) {
                return true;
            }
        }
        return false;
    }
}
