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

//import com.github.cschabl.cdiunit.junit5.CdiUnitExtension;
import com.techmine.gs.WicketApplication;
import com.techmine.gs.producer.EntityManagerProducer;
import com.techmine.gs.repository.SubjectRepository;
import com.techmine.gs.service.AuthenticationService;
import com.techmine.gs.ui.pages.IndexPage.IndexPage;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.jboss.weld.junit5.auto.ActivateScopes;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.WeldJunit5AutoExtension;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author Cedric-Pemberton
 */
@ExtendWith(WeldJunit5AutoExtension.class)
@AddBeanClasses(value = {WicketApplication.class, AuthenticationService.class, SubjectRepository.class, EntityManagerProducer.class})
@ExtendWith(MockitoExtension.class)
@ActivateScopes(RequestScoped.class)
public class MenuPanelTest {

    @Inject
    WicketApplication wicketApplication;

    WicketTester tester;
    IndexPage indexPage;

    @Mock
    private AuthenticationService authenticationService;

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
        tester = new WicketTester(wicketApplication);
        this.indexPage = tester.startPage(IndexPage.class);

    }

    @AfterEach
    public void tearDown() {

    }

    @Test
    public void testWicketApplicationInjected() {
        assertNotNull(this.wicketApplication, "wicket Application is not correctly injected");
        assertNotNull(this.indexPage, "Index Page is not Created");
    }

    /**
     * Test of onInitialize method, of class MenuPanel.
     */
    @Test
    public void testPageRenders() {
        //tester.startComponentInPage(new MenuPanel("menu"));
        tester.assertComponent(":menubar", MenuPanel.class);

        //Invisible components are rendered as hidden span tags.
        tester.assertInvisible("menubar:logout");

    }

    @Test
    @Disabled
    public void testLogOutButtonHidenWhenSinInPanelDisplayed() {
        tester.startPage(IndexPage.class);
        AjaxFallbackLink link = (AjaxFallbackLink) tester.getComponentFromLastRenderedPage("menu:logout", true);

        tester.assertInvisible("menu:logout");

    }

    @Test
    @Disabled
    public void testLogOutButtonShownOnSingIn() {
        //SignInPage page = tester.startPage(SignInPage.class);

        FormTester formTester = tester.newFormTester("body:SingInForm");

        formTester.setValue("userName", "Administrator")
                .setValue("password", "Password");

    }
}
