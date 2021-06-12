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
package com.techmineinc.pages;

import com.techmineinc.WicketApplication;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Spy;

/**
 *
 * @author CodeCamp4
 */
public class LandingPageTest {

    private WicketTester tester;
    
    @Spy
    private LandingPage spyLandingPage;

    public LandingPageTest() {
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

    /**
     * Test of MainPage Renders.
     */
    @Test
    public void testPageRenders() {
        tester.startPage(LandingPage.class);
        tester.assertRenderedPage(LandingPage.class);
    }
    
    /* 
    @Test
    public void testInitializerCalled(){
    spyLandingPage  = new LandingPage();
    spyLandingPage.onInitialize();
    //Mockito.verify(spyLandingPage).initializeUserName();
    }
    */

    @Test
    @DisplayName(value = "Test Contains signInForm")
    public void testContainsSignInFormComponent() {
        tester.startPage(LandingPage.class);
        tester.assertComponent("signInForm", Form.class);

    }

    @Test
    public void testUserNameComponentRenders() {
        tester.startPage(LandingPage.class);
        tester.assertComponent("signInForm:userName", TextField.class);
    }

    @Test
    public void testPasswordComponentRenders() {
        tester.startPage(LandingPage.class);
        tester.assertComponent("signInForm:password", TextField.class);
    }

    @Test
    public void testSignInButtonRenders() {
        tester.startPage(LandingPage.class);
        tester.assertComponent("signInForm:signIn", AjaxFallbackButton.class);
    }
    
    

}
