/*
 * Copyright 2021 Cedric Pemberton.
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

import com.techmine.gs.domain.Cylinder;
import com.techmine.gs.domain.Institution;
import com.techmine.gs.repository.CylinderRepository;
import com.techmine.gs.repository.InstitutionRepository;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.inject.Inject;

/**
 *
 * @author Cedric Pemberton
 */
public class CylinderService implements Serializable {

    @Inject
    private CylinderRepository cylinderRepository;

    public List<Cylinder> institutions() {
        return this.cylinderRepository.findAll();
    }

    public void persisteCylinder(Cylinder subject) {
        if (cylinderExists(subject)) {
            cylinderRepository.edit(subject);
        } else {
            cylinderRepository.create(subject);
        }

    }

    public void deleteCylinder(Cylinder cylinder) {
        this.cylinderRepository.remove(cylinder);
    }

    public boolean cylinderExists(Cylinder cylinder) {
        return cylinderExists(cylinder.getSerialNumber());
    }

    /**
     * checks that the cylinder can be deleted.
     *
     * @param cylinder
     * @return
     */
    public boolean canDelete(Cylinder cylinder) {
        return true;
    }

    public boolean cylinderExists(String serialNo) {
        return Objects.nonNull(this.cylinderRepository.find(serialNo));
    }

}
