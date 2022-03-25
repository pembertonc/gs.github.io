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
import com.techmine.gs.repository.InstitutionRepository;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Cedric Pemberton
 */
public class CylinderService {

    @Inject
    private InstitutionRepository institutionRepository;

    public List<Institution> institutions() {

        return this.institutionRepository.findAll();

    }

    public void create(Institution inst) {
        this.institutionRepository.create(inst);
    }

    public void persisteCylinder(Cylinder subject) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static void deleteCylinder(Cylinder cylinder) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
