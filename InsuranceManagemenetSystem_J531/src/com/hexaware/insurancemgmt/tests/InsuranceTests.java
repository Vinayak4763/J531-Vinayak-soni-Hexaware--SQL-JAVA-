package com.hexaware.insurancemgmt.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;


class InsuranceTests {

    @Test
    void testPolicyNameEquality() {
        String policyName = "Health Plus";
        assertEquals("Health Star", policyName, "Policy names should match");
    }

    // Test 2: Test to check inequality using assertNotEquals
    @Test
    void testCoverageMismatch() {
        double coverageAmount = 50000.0;
        assertNotEquals(100000.0, coverageAmount, "Coverage amounts should not be equal");
    }

    

    @Disabled("This test is under development")
    @Test
    void testPremiumCalculation() {
        fail("Not yet implemented");
    }
}