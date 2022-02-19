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
package com.techmine.gs.ui.panels.Menu;

import com.github.cschabl.cdiunit.junit5.CdiUnitExtension;
import com.techmine.gs.WicketApplication;
import com.techmine.gs.producer.EntityManagerProducer;
import com.techmine.gs.repository.AbstractRepository;
import com.techmine.gs.repository.SubjectRepository;
import com.techmine.gs.service.AuthenticationService;
import com.techmine.gs.ui.pages.IndexPage.IndexPage;
import com.techmine.gs.ui.panels.SignIn.SignInPanel;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.deltaspike.SupportDeltaspikeCore;
import org.jglue.cdiunit.deltaspike.SupportDeltaspikeJpa;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author Cedric-Pemberton
 */
@ExtendWith(CdiUnitExtension.class)
@SupportDeltaspikeCore
@SupportDeltaspikeJpa
@AdditionalClasses(value = {EntityManagerProducer.class, SignInPanel.class, WicketApplication.class, AuthenticationService.class, AbstractRepository.class, SubjectRepository.class})
@ExtendWith(MockitoExtension.class)
public class MenuPanelTest {

    WicketTester tester1; // = new WicketTester(new WicketApplication());

    IndexPage instance;

    @Mock
    private AuthenticationService authenticationService;

    //@InjectMocks
    private IndexPage indexPage;// = tester1.startPage(IndexPage.class);

    public MenuPanelTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        tester1 = new WicketTester(new WicketApplication());

    }

    @AfterEach
    public void tearDown() {

    }

    /**
     * Test of onInitialize method, of class MenuPanel.
     */
    @Test
    public void testPageRenders() {
        tester1.startComponentInPage(new MenuPanel("menu"));
        tester1.assertComponent("menu", MenuPanel.class);

        //Invisible components are rendered as hidden span tags.
        tester1.assertInvisible("menu:logout");

    }

    @Test
    public void testLogOutButtonHidenWhenSinInPanelDisplayed() {
        tester1.startPage(IndexPage.class);
        AjaxFallbackLink link = (AjaxFallbackLink) tester1.getComponentFromLastRenderedPage("menu:logout", true);

        tester1.assertInvisible("menu:logout");

    }

    @Test
    public void testLogOutButtonShownOnSingIn() {
        //SignInPage page = tester1.startPage(SignInPage.class);

        FormTester formTester = tester1.newFormTester("body:SingInForm");

        formTester.setValue("userName", "Administrator")
                .setValue("password", "Password");

    }
}
