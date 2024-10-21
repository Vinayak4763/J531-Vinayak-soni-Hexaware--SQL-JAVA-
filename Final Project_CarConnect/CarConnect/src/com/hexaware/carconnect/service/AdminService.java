
/* Romi Kumar Singh and Vinayak Soni
 * CarConnect Admin Service
 * 21/10/2024
 */

package com.hexaware.carconnect.service;

import com.hexaware.carconnect.dao.AdminDao;

import com.hexaware.carconnect.dao.IAdminDao;
import com.hexaware.carconnect.entity.Admin;
import com.hexaware.carconnect.exceptions.AdminNotFoundException;
import com.hexaware.carconnect.exceptions.InputMismatchCustomException;
import com.hexaware.carconnect.exceptions.InvalidInputException;

public class AdminService implements IAdminService {

    private IAdminDao adminDao; 

    
    public AdminService() {
        this.adminDao = new AdminDao(); //object instantiated//
    }

    @Override
    public Admin getAdminById(int adminId) throws AdminNotFoundException,InputMismatchCustomException {
       
        return adminDao.getAdminById(adminId);
    }

    @Override
    public Admin getAdminByUsername(String username) throws AdminNotFoundException{
        return adminDao.getAdminByUsername(username);
    }

    @Override
    public void registerAdmin(Admin adminData) throws InvalidInputException {
       
        adminDao.registerAdmin(adminData);
    }

    @Override
    public void updateAdmin(Admin adminData) throws AdminNotFoundException, InvalidInputException {
       
        adminDao.updateAdmin(adminData);
    }

    @Override
    public void deleteAdmin(int adminId) throws AdminNotFoundException{
        
        adminDao.deleteAdmin(adminId);
    }
}

