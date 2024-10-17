package com.hexaware.insurancemgmt.dao;

import com.hexaware.insurancemgmt.entity.Policy;
import com.hexaware.insurancemgmt.exception.PolicyNotFoundException;

import java.util.List;

public interface IPolicyDao {
	
    boolean createPolicy(Policy policy) throws PolicyNotFoundException;
    List<Policy> getAllPolicies() throws PolicyNotFoundException;
    boolean updatePolicy(Policy policy) throws PolicyNotFoundException;
    boolean deletePolicy(int policyId) throws PolicyNotFoundException;
    Policy getPolicy(int policyId) throws PolicyNotFoundException;
}
