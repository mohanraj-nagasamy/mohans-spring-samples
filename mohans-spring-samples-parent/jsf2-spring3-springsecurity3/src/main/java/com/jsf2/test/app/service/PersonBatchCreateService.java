package com.jsf2.test.app.service;

import javax.annotation.security.RolesAllowed;

/**
 *
 * @author Dimitar Makariev
 */
public interface PersonBatchCreateService {

    @RolesAllowed(value = {"ROLE_ADMIN"})
    void batchCreateRecords(int numberOfRecords);

    @RolesAllowed(value = {"ROLE_ADMIN"})
    int deleteAll();
}
