package com.projects.moviebookingapp.service.role.impl;

import com.projects.moviebookingapp.model.entity.Role;
import com.projects.moviebookingapp.repository.RoleRepository;
import com.projects.moviebookingapp.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByName(String name) {

        return roleRepository.findByName(name);
    }
}
