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
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.inject.Inject;

/**
 *
 *
 * @author Cedric-Pemberton
 */
public class UserService implements Serializable {

    @Inject
    SubjectRepository subjectRepository;

    public SubjectRepository getSubjectRepository() {
        return subjectRepository;
    }

    public void createUser(Subject subject) {
        subjectRepository.create(subject);
    }

    public boolean checkUserExistxByUserName(String userName) {

        return findByUserName(userName).isPresent();
    }

    public Optional<Subject> findByUserName(String userName) {
        Map<String, Object> properties = new HashMap<>();

        properties.put("userName", userName);
        return subjectRepository.findSingleByNamedQuery("Subject.findByUserName", properties);

    }

    public List<Subject> findLikeUserName(String userName) {
        Map<String, Object> properties = new HashMap<>();

        properties.put("userName", "%" + userName + "%");
        return subjectRepository.findByNamedQuery("Subject.findLikeUserName", properties);

    }

    public Subject findById(String subjectId) {
        return subjectRepository.find(subjectId);
    }

    public void updateUser(Subject subject) {
        subjectRepository.edit(subject);
    }
}
