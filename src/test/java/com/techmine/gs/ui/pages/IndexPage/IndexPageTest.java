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
package com.techmine.gs.ui.pages.IndexPage;

import com.techmine.gs.CustomAuthenticatedWebSession;
import com.techmine.gs.WicketApplication;
import com.techmine.gs.producer.EntityManagerProducer;
import com.techmine.gs.repository.SubjectRepository;
import com.techmine.gs.service.AuthenticationService;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.jboss.weld.junit5.auto.ActivateScopes;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.WeldJunit5AutoExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author Cedric-Pemberton
 */
@ExtendWith(WeldJunit5AutoExtension.class)
@AddBeanClasses(
        value = {
            WicketApplication.class,
            CustomAuthenticatedWebSession.class,
            AuthenticationService.class,
            SubjectRepository.class,
            EntityManagerProducer.class
        })
@ExtendWith(MockitoExtension.class)
@ActivateScopes(value = {RequestScoped.class})
public class IndexPageTest {

    WicketTester tester;
    IndexPage instance;

    @Inject
    WicketApplication wicketApplication;

    public IndexPageTest() {
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
        instance = tester.startPage(IndexPage.class);
    }

    @AfterEach
    public void tearDown() {
        tester.destroy();
    }

    @Test
    public void testInitialSignPageIsStateLess() {

        // tester.assertBehavior("body:signInform:signIn", AjaxFormSubmitBehavior.class);
        FormTester formTester = tester.newFormTester("body:signInForm");
        formTester.setValue("userName", "Administrator");
        formTester.setValue("password", "Password");
        Button btn = (Button) tester.getComponentFromLastRenderedPage("body:signInForm:signIn");
        assertTrue(instance.isStateless(), "IndexPage Should be stateless on startup");

        assertTrue(instance.isBookmarkable());
        assertTrue(instance.isStateless(), "IndexPage Should be stateless on startup");

        //tester.assertAjaxLocation();  the application shows ajax behavior but the header is absent.
    }
}
