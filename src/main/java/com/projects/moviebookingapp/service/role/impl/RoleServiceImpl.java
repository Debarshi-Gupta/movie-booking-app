package com.projects.moviebookingapp.service.role.impl;

import com.projects.moviebookingapp.exception.CustomRoleException;
import com.projects.moviebookingapp.model.entity.Role;
import com.projects.moviebookingapp.repository.RoleRepository;
import com.projects.moviebookingapp.service.role.RoleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByName(String name) {

        Role role = null;

        try{
            log.info("Attempting to fetch Role..");
            role = roleRepository.findByName(name);
            log.info("Role fetched successfully: {}", role.toString());
        }
        catch (Exception e)
        {
            log.error("Error while fetching role with name: {}", name);
            throw new CustomRoleException("Error while fetching role");
        }

        return role;
    }
}
