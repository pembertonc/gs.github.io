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
package com.techmine.gs.testutils;

import com.techmine.gs.domain.Contact;
import com.techmine.gs.domain.Person;
import com.techmine.gs.domain.Subject;

/**
 *
 * @author Cedric-Pemberton
 */
public class TestObjects {

    /* returns a subject with Person and contact information */
    public static Subject getSubject() {

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
