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
// Rewrite this test to use arquillian
package com.techmine.gs.service;

import com.techmine.gs.domain.Contact;
import com.techmine.gs.domain.Person;
import com.techmine.gs.domain.Subject;
import com.techmine.gs.repository.AbstractRepository;
import com.techmine.gs.repository.SubjectRepository;
import com.techmine.gs.testutils.TestEntityManagerProducer;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import javax.persistence.EntityManager;

import javax.transaction.Transactional;

import org.jboss.weld.junit5.auto.ActivateScopes;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.EnableAlternatives;
import org.jboss.weld.junit5.auto.WeldJunit5AutoExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 *
 * @author Cedric-Pemberton
 */
@ExtendWith(WeldJunit5AutoExtension.class)
@AddBeanClasses(value = {TestEntityManagerProducer.class, UserServiceTest.class, UserService.class, SubjectRepository.class, AbstractRepository.class})
@EnableAlternatives(value = {TestEntityManagerProducer.class})
@ActivateScopes(value = {RequestScoped.class, SessionScoped.class, ApplicationScoped.class})
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

        assertNotNull(this.instance);
    }

    @Test
    public void testInjected() {
        assertNotNull(this.em);

    }

    @Test
    @Transactional
    public void testcreateSubject() {
        System.out.println("createUser");
        Subject sub = createTestSubject();
        //this.em.getTransaction().begin();
        //assertTrue(this.instance.checkUserExistxByUserName("Administrator"));
        //instance.createUser(sub);
        this.em.getTransaction().begin();
        this.em.persist(sub);
        this.em.getTransaction().commit();
        //this.em.flush();
        // this.em.getTransaction().commit();
    }

    @Test
    @Disabled
    public void testPersistenceContextInjection() {
        //  assertNotNull(entityManager);
    }

    private Subject createTestSubject() {

        Contact contact = new Contact()
                .email("user1@gmail.com")
                .telephone1("767-245-2656")
                .telephone2("767-245-5455");
        Person person = new Person()
                .familyName("John")
                .firstName("Peter")
                .otherName("Derick")
                .contact(contact);

        Subject subject = new Subject();
        return subject.password("Password")
                .userName("User1")
                .person(person);
    }

}
