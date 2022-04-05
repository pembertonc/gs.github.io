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

import com.techmine.gs.domain.GasType;
import com.techmine.gs.repository.GasTypeRepository;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Cedric-Pemberton
 */
public class GasTypeService implements Serializable {

    @Inject
    private GasTypeRepository gasTypeRepository;

    /**
     *
     * @return List<GasType> containing all the instances of GasType yet
     * persisted.
     */
    public List<GasType> findAll() {
        return gasTypeRepository.findAll();
    }
}
