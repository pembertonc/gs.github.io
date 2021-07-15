/*
 * Copyright 2021 CodeCamp4.
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
package com.techmine.ui.pages;

import com.techmine.gs.ui.pages.CylinderDescriptor;
import com.techmine.gs.WicketApplication;
import org.apache.wicket.util.tester.TagTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 *
 * @author CodeCamp4
 */
public class CylinderDescriptorTest {

    private WicketTester tester;

    public CylinderDescriptorTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        tester = new WicketTester(new WicketApplication());

    }

    @AfterEach
    public void tearDown() {
        tester.destroy();
    }

    @Test
    public void testCylinderCheckInPageRenders() {
        tester.startPage(CylinderDescriptor.class);
        tester.assertRenderedPage(CylinderDescriptor.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"serialNo", "dateReturned", "owner", "gasType", "virificationPin", "cylinderCapcity", "telephoneNo"})
    public void testFieldReturned(String input) {
        tester.startPage(CylinderDescriptor.class);

        TagTester tagTester = tester.getTagById("serialNo");

        assertNotNull(tagTester);
        tester.destroy();
    }
    
    
  
}
