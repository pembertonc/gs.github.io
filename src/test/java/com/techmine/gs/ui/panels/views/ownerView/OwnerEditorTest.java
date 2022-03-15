/*
 * Copyright 2022 bethy.
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
package com.techmine.gs.ui.panels.views.ownerView;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author bethy
 */
public class OwnerEditorTest {

    WicketTester tester;
    OwnerEditor instance;
    
    public OwnerEditorTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        this.tester = new WicketTester();
        instance = new OwnerEditor("editor");
        tester.startComponentInPage(instance);

    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of onInitialize method, of class OwnerEditor.
     */
    @Test
    public void testOwnerEditorRenders() {
        tester.assertComponent("editor", OwnerEditor.class);
    }
    @Test void testFormExist() {
    tester.assertEnabled("editor:form");
    }
        
}
