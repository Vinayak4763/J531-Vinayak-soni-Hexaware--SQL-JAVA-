package com.hexaware.insurancemgmt.service;

import com.hexaware.insurancemgmt.dao.PolicyDaoImpl;
import com.hexaware.insurancemgmt.entity.Policy;
import com.hexaware.insurancemgmt.exception.PolicyNotFoundException;

import java.util.List;

public class PolicyServiceImpl implements IPolicyService {

    private PolicyDaoImpl policyDao;

    // Constructor to initialize PolicyDao
    public PolicyServiceImpl(PolicyDaoImpl policyDao) {
        this.policyDao = policyDao;
    }

    @Override
    public boolean addPolicy(Policy policy) {
        if (policy.getPremiumAmount() <= 0) {
            System.out.println("Premium amount must be positive.");
            return false;
        }
        return policyDao.createPolicy(policy);
    }

    @Override
    public Policy getPolicyById(int policyId) throws PolicyNotFoundException {
        Policy policy = policyDao.getPolicy(policyId);
        if (policy == null) {
            System.out.println("Policy with ID " + policyId + " not found.");
        }
        return policy;
    }

    @Override
    public List<Policy> getAllPolicies() {
        return policyDao.getAllPolicies();
    }

    @Override
    public boolean updatePolicy(Policy policy) throws PolicyNotFoundException {
        Policy existingPolicy = policyDao.getPolicy(policy.getPolicyId());
        if (existingPolicy == null) {
            System.out.println("Cannot update: Policy not found.");
            return false;
        }
        return policyDao.updatePolicy(policy);
    }

    @Override
    public boolean removePolicy(int policyId) {
        return policyDao.deletePolicy(policyId);
    }
}
