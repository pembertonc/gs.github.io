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
package com.techmine.gs.ui.pages;

import com.techmine.gs.WicketApplication;
        
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.util.tester.TagTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author CodeCamp4
 */
public class BasePageTest {

    private WicketTester tester;
    
    public BasePageTest() {
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

    @Test
    public void testSomeMethod() {
       // create and render the page under test.
       
       tester.startPage(BasePage.class);
       
       
       tester.assertRenderedPage(BasePage.class);
    }
    
    @Test
    public void testHasGassSupplies(){
        tester.startPage(BasePage.class);
        String string = tester.getLastResponseAsString();
        //tester.getLastResponseAsString().contains("Gas Supplies");   
        tester.assertContains("Gas Supplies");
    }
    
    @Test
    public void testPageHeaderRendered(){
        tester.startPage(BasePage.class);
        
        tester.assertComponent("pageHeader", WebMarkupContainer.class);
        
    }
    
    @Test
    public void testHeaderTextRendered(){
        tester.startPage(BasePage.class);
        tester.assertComponent("pageHeader:headerText", Label.class);
    }
    
    @Test
    public void testHeaderTextHasCssClasses(){
        tester.startPage(BasePage.class);
        TagTester tagTester = tester.getTagByWicketId("headerText");
        
        assertEquals("w3-wide w3-xlarge", tagTester.getAttribute("class"));
        
        
    }
    
    @Test
    public void testPageHeaderCssClassess(){
        tester.startPage(BasePage.class);
        TagTester tagTester = tester.getTagByWicketId("pageHeader");
        
        assertEquals("w3-container w3-light-green w3-center", tagTester.getAttribute("class"));
    }

    @Test
    public void testwww_w3schools_com_w3css_4_w3Loaded() {
        tester.startPage(BasePage.class);
        tester.assertContains("www.w3schools.com_w3css_4_w3");

    }
    @Test
    public void testStyleLoaded() {
        tester.startPage(BasePage.class);
        tester.assertContains("style");

    }

    
}
