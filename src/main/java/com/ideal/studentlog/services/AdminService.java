package com.ideal.studentlog.services;

import com.ideal.studentlog.database.models.Admin;
import com.ideal.studentlog.database.repositories.AdminRepository;
import com.ideal.studentlog.helpers.dataclass.AdminDTO;
import com.ideal.studentlog.helpers.exceptions.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;

    public List<AdminDTO> getAll(){
        return adminRepository
                .findAll()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    public AdminDTO create(AdminDTO dto){
        Admin admin = new Admin();
        map(dto, admin);

        return map(adminRepository.save(admin));
    }

    public AdminDTO getById(Integer id) throws ServiceException{
        return map(getAdmin(id));
    }

    public AdminDTO update(Integer id, AdminDTO dto) throws ServiceException{
        Admin admin = getAdmin(id);
        map(dto, admin);

        return map(adminRepository.save(admin));
    }

    public void delete(Integer id){
        adminRepository.deleteById(id);
    }

    private Admin getAdmin(Integer id) throws ServiceException {
        return adminRepository.findById(id).orElseThrow(() -> new ServiceException(
                "Admin not found with ID: " + id,
                HttpStatus.NOT_FOUND
        ));
    }

    private void map(AdminDTO dto, Admin admin){
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
    }

    private AdminDTO map(Admin admin){
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
}
