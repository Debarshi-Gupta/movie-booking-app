package com.projects.moviebookingapp.service.role;

import com.projects.moviebookingapp.model.entity.Role;

public interface RoleService {

    Role findByName(String name);
}
