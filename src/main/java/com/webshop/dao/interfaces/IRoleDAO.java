package com.webshop.dao.interfaces;

import com.webshop.dao.generic.IGenericDAO;
import com.webshop.model.classes.Role;
import com.webshop.model.enums.UserRole;


public interface IRoleDAO extends IGenericDAO<Role, Integer> {

    Role addRole(UserRole userRole);

    void deleteRoleByTitle(UserRole userRole);

    Role loadRoleByID(int id);

    Role loadRoleByTitle(UserRole userRole);

    int getRoleId(UserRole userRole);

}
