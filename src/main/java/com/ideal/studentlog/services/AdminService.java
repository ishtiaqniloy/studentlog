package com.ideal.studentlog.services;

import com.ideal.studentlog.database.models.Admin;
import com.ideal.studentlog.database.repositories.AdminRepository;
import com.ideal.studentlog.helpers.dtos.AdminDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;

    public List<Admin> getAll(){
        return adminRepository.findAll();
    }

    public void create(AdminDTO dto){
        Admin admin = new Admin();
        admin.setAdminId(dto.getAdminId());
        admin.setName(dto.getName());
        admin.setDesignation(dto.getDesignation());
        admin.setDateOfBirth(dto.getDateOfBirth());
        admin.setBloodGroup(dto.getBloodGroup());
        admin.setHighestEducationLevel(dto.getHighestEducationLevel());
        admin.setJoiningDate(dto.getJoiningDate());
        admin.setResignationDate(dto.getResignationDate());
        admin.setContactNumber(dto.getContactNumber());
        admin.setPresentAddress(dto.getPresentAddress());
        admin.setPermanentAddress(dto.getPermanentAddress());

        adminRepository.save(admin);
    }

    public AdminDTO getById(Integer id){
        Admin admin = adminRepository.findById(id).orElseThrow();

        return new AdminDTO(
                admin.getAdminId(),
                admin.getName(),
                admin.getDesignation(),
                admin.getDateOfBirth(),
                admin.getBloodGroup(),
                admin.getHighestEducationLevel(),
                admin.getJoiningDate(),
                admin.getResignationDate(),
                admin.getContactNumber(),
                admin.getPresentAddress(),
                admin.getPermanentAddress()
        );
    }

    public void update(Integer id, AdminDTO dto){
        Admin admin = adminRepository.findById(id).orElseThrow();

        admin.setAdminId(dto.getAdminId());
        admin.setName(dto.getName());
        admin.setDesignation(dto.getDesignation());
        admin.setDateOfBirth(dto.getDateOfBirth());
        admin.setBloodGroup(dto.getBloodGroup());
        admin.setHighestEducationLevel(dto.getHighestEducationLevel());
        admin.setJoiningDate(dto.getJoiningDate());
        admin.setResignationDate(dto.getResignationDate());
        admin.setContactNumber(dto.getContactNumber());
        admin.setPresentAddress(dto.getPresentAddress());
        admin.setPermanentAddress(dto.getPermanentAddress());

        adminRepository.save(admin);
    }

    public void delete(Integer id){
        adminRepository.deleteById(id);
    }
}
