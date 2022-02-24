/*
 * Copyright 2022 Cedric-Pemberton.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.techmine.gs.service;

import com.techmine.gs.producer.EntityManagerProducer;
import com.techmine.gs.repository.SubjectRepository;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import org.jboss.weld.junit5.auto.ActivateScopes;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.WeldJunit5AutoExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 *
 * @author Cedric-Pemberton
 */
@ExtendWith(WeldJunit5AutoExtension.class)
@AddBeanClasses(value = {EntityManagerProducer.class, EntityManager.class, UserServiceTest.class, UserService.class, SubjectRepository.class})
@ActivateScopes(value = {RequestScoped.class})
public class UserServiceTest {

    @Inject
    UserService instance;

    @Inject
    EntityManager em;

    public UserServiceTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of createUser method, of class UserService.
     */
    @Test
    public void testUsersServiceInjectable() {
        System.out.println("createUser");

        assertNotNull(this.instance);
    }

    @Test
    public void testInjected() {
        assertNotNull(this.em);

    }

}
