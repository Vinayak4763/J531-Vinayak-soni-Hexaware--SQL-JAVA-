package com.hexaware.insurancemgmt.service;

import com.hexaware.insurancemgmt.entity.Policy;
import java.util.List;
import com.hexaware.insurancemgmt.exception.PolicyNotFoundException;


public interface IPolicyService {
    boolean addPolicy(Policy policy) throws PolicyNotFoundException;
    Policy getPolicyById(int policyId) throws PolicyNotFoundException;
    List<Policy> getAllPolicies() throws PolicyNotFoundException;
    boolean updatePolicy(Policy policy) throws PolicyNotFoundException;
    boolean removePolicy(int policyId) throws PolicyNotFoundException;
    
}
