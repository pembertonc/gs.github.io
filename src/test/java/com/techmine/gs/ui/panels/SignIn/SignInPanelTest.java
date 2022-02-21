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

import com.techmine.gs.service.AuthenticationService;
import com.techmine.gs.ui.pages.IndexPage.IndexPage;

import com.techmine.gs.ui.panels.Dashboard.Dashboard;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author Cedric-Pemberton
 */
@ExtendWith(MockitoExtension.class)
public class SignInPanelTest {

    // @Inject
    //  BeanManager manager;
    WicketTester tester1;

    public SignInPanelTest() {
    }

    @Mock
    AuthenticationService authenticationService;

    // @InjectMocks
    SignInPanel signInPanel;

    @BeforeAll
    public static void setUpClass() {

    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        tester1 = new WicketTester();
        this.signInPanel = new SignInPanel("body");
        MockitoAnnotations.openMocks(this.signInPanel);
        tester1.startComponentInPage(this.signInPanel);

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

        // assertNotNull(this.manager, "manager is null");
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
    public void testAjaxSubmitValuesReturned() {
        Mockito.when(authenticationService.login("SomeUserName", "password")).thenReturn(true);
        FormTester ft = tester1.newFormTester("body:signInForm");
        Button btn = (Button) tester1.getComponentFromLastRenderedPage("body:signInForm:signIn");
        ft.setValue("userName", "SomeUserName").setValue("password", "password");
        //this.signInPanel.setAuthenticationService(authenticationService);

        tester1.executeAjaxEvent(btn, "click");

        assertEquals("SomeUserName", this.signInPanel.getUser().getUserName(), "User Name is not set");

        // the password is cleared from the Model for security reasons on rendering so it is null by the time we access it here.
    }

    @Test
    void testSuccessfulLoginNavigation() {
        FormTester ft = tester1.newFormTester("body:signInForm");
        Button btn = (Button) tester1.getComponentFromLastRenderedPage("body:signInForm:signIn");
        Mockito.when(authenticationService.login("SomeUserName", "SomeUserName")).thenReturn(true);
        tester1.assertComponent("body", SignInPanel.class);
        ft.setValue("userName", "SomeUserName").setValue("password", "SomeUserName");
        //this.signInPanel.setAuthenticationService(authenticationService);

        tester1.executeAjaxEvent(btn, "click");

        tester1.assertComponent("body", Dashboard.class);

        assertFalse(tester1.getLastRenderedPage().isPageStateless(), "page must be now statefull");
    }

    @Test
    void testNavigationOnLoginFailure() {

        //  IndexPage page = tester1.startPage(IndexPage.class);
        //  ((SignInPanel) page.get("body")).setAuthenticationService(authenticationService);
        SignInPanel p = new SignInPanel("body");

        p = tester1.startComponentInPage(p);
        MockitoAnnotations.openMocks(p);
        FormTester ft = tester1.newFormTester("body:signInForm");
        Button btn = (Button) tester1.getComponentFromLastRenderedPage("body:signInForm:signIn");

        tester1.assertComponent("body", SignInPanel.class);

        ft.setValue("userName", "SomeUserName")
                .setValue("password", "SomeUserName");

        //  AuthenticationService ser = Mockito.mock(AuthenticationService.class);
        Mockito.when(authenticationService.login("SomeUserName", "SomeUserName")).thenReturn(false);

        //MockitoAnnotations.openMocks(p);.
        // p.setAuthenticationService(authenticationService);
        tester1.executeAjaxEvent(btn, "click");

        tester1.assertComponent("body", SignInPanel.class);

    }

    public void testIndexPageStatelessWhenContainsSignInPanel() {

        // create page default behaviour is to house the SignInPanel
        IndexPage page = tester1.startPage(IndexPage.class);
        //assert that the rendered page is an instance of IndexPage
        tester1.assertRenderedPage(IndexPage.class);
        // assert that a component exists with a wicket:id or body
        tester1.assertExists("body");
        // asset that the component with wicket:id body is an instance of SignInPanel
        tester1.assertComponent("body", SignInPanel.class);

        assertTrue(page.isStateless());

    }

}
