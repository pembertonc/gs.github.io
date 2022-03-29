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

import com.techmine.gs.domain.Subject;
import com.techmine.gs.repository.SubjectRepository;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.inject.Inject;

/**
 *
 * @author Cedric-Pemberton
 */
public class AuthenticationService implements Serializable {

    @Inject
    SubjectRepository subjectRepository;
    @Inject
    UserService subjectService;

    public boolean login(String userName, String password) {

        Map<String, Object> parameters = new HashMap<>();
        parameters.clear();
        parameters.put("userName", userName);
        // Optional<Subject> subject = this.subjectRepository.findSingleByNamedQuery("Subject.findByUserName", parameters);
        Optional<Subject> subject = subjectService.findByUserName(userName);
        if (subject.isEmpty()) {
            return false;
        }
        return subject.get().getPassword().equals(password);

    }

    public Subject save(Subject subject) {
        throw new UnsupportedOperationException("Not yet implmented");
    }

}
