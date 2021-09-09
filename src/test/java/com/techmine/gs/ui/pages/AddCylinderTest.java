/*
 * Copyright 2021 Cedric-Pemberton.
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
package com.techmine.gs.ui.pages;

import com.techmine.gs.WicketApplication;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Cedric-Pemberton
 */
public class AddCylinderTest {

    private WicketTester tester;
    private AddCylinder instance;

    @BeforeEach
    protected void setUp() throws Exception {
        tester = new WicketTester(new WicketApplication());
        instance = tester.startPage(AddCylinder.class);

    }

    @AfterEach
    protected void tearDown() throws Exception {

    }

    /**
     * Test of onInitialize method, of class AddCylinder.
     */
    @Test
    public void addCylinerRenders() {
        tester.assertRenderedPage(AddCylinder.class);
    }

    @Test
    public void serialNumberRenders() {
        tester.assertExists("form:serialNumber");
    }

    @Test
    public void formRenders() {
        tester.assertExists("form");
    }

    @Test
    public void cylinderGasTypeRenders() {
        tester.assertExists("form:cylinderGasType");
    }

    @Test
    public void cylinderCapacityRenders() {
        tester.assertExists("form:cylinderGasType");
    }

    @Test
    public void cylinderOwnerRenders() {
        tester.assertExists("form:cylinderGasType");
    }
    
    @Test
    public void addRenders() {
        tester.assertExists("form:add");
    }
}
