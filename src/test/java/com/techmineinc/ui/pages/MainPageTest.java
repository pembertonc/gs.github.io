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
package com.techmineinc.ui.pages;

import com.techmineinc.ui.pages.CylinderCheckInPage;
import com.techmineinc.ui.pages.MainPage;
import com.techmineinc.WicketApplication;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 *
 * @author CodeCamp4
 */
public class MainPageTest {
    
    
    private WicketTester tester;
    
    public MainPageTest() {
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
    }

    /**
     * Test of onInitialize method, of class MainPage.
     */
    @Test
    @DisplayName(value = "name name name")
    public void testOnInitialize() {
        System.out.println("onInitialize");
        MainPage instance = new MainPage();
        instance.onInitialize();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"actionForm:checkInCylinder","actionForm:checkOutCylinder","actionForm:addCylinder","actionForm:addCustomer","actionForm:viewInventory","actionForm:signOut"})
    public void testContainsCheckinCylinderComponent(String path) {
    tester.startPage(MainPage.class);
    tester.assertComponent(path, AjaxFallbackButton.class);
    tester.destroy();
    }
    
    @Test
    public void testNavigationToCheckInCylinderPage(){
       tester.startPage(MainPage.class) ;
       tester.executeAjaxEvent("actionForm:checkInCylinder", "click");
       tester.assertRenderedPage(CylinderCheckInPage.class);
    }
 
    
    /*
    @Test
    public void testClickCheckInCylinderButtonGoesToCylinderCheckinPage() {
    tester.startPage(MainPage.class);
    tester.executeAjaxEvent("actionForm:checkinCylinder", "click");
    tester.assertRenderedPage(CylinderDescriptor.class);
    }*/

    /**
     * Test of onInitialize method, of class LandingPage.
     */
    /*  @Test
    public void testOnInitialize() {
    System.out.println("onInitialize");
    LandingPage instance = new LandingPage();
    instance.onInitialize();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
    }*/

    
}
