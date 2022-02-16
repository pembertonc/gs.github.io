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
package com.techmine.gs.ui.panels.SignIn;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Cedric-Pemberton
 */
public class SignInPanelTest {

    public SignInPanelTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of onInitialize method, of class SignInPanel.
     */
    @Test
    public void testSignInPanelRenders() {
        WicketTester tester = new WicketTester();
        SignInPanel panel = tester.startComponentInPage(new SignInPanel("signInPanel"));

        assertNotNull(panel);

    }

    @Test
    public void testSubmitReturnsValues() {
        WicketTester tester = new WicketTester();
        SignInPanel panel = tester.startComponentInPage(new SignInPanel("signInPanel"));
        FormTester ft = tester.newFormTester("signInPanel:signInForm");

        ft.setValue("userName", "SomeUserName")
                .setValue("password", "somePassword");
        ft.submit();

        assertEquals("SomeUserName", panel.getUser().getUserName(), "User Name is not set");
    }

    @Test
    public void testAjaxSubmit() {
        WicketTester tester = new WicketTester();
        SignInPanel panel = tester.startComponentInPage(new SignInPanel("signInPanel"));
        FormTester ft = tester.newFormTester("signInPanel:signInForm");

        AjaxFallbackButton btn = (AjaxFallbackButton) tester.getComponentFromLastRenderedPage("signInPanel:signInForm:signIn");

        ft.setValue("userName", "SomeUserName")
                .setValue("password", "somePassword");

        tester.executeAjaxEvent(btn, "click");
        assertEquals("SomeUserName", panel.getUser().getUserName(), "User Name is not set");
    }

}
