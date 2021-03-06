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
package com.techmine.gs.ui.panels.views.user_view;

import com.techmine.gs.domain.Subject;
import com.techmine.gs.service.UserService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author Cedric-Pemberton
 */
@ExtendWith(MockitoExtension.class)
public class UserEditorTest {

    WicketTester tester;

    @Mock
    UserService userService;

    UserEditor instance;

    UserEditor userEditor;
    private AutoCloseable closure;

    public UserEditorTest() {
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
        instance = new UserEditor("editor", Model.of(new Subject()));
        instance = tester.startComponentInPage(instance);
        closure = MockitoAnnotations.openMocks(instance);
    }

    @AfterEach
    public void tearDown() {
        try {
            closure.close();
        } catch (Exception ex) {
            Logger.getLogger(UserEditorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        tester.destroy();
    }

    /**
     * Test of onInitialize method, of class UserEditor.
     */
    @Test
    public void testUserEditorRenders() {
        tester.assertComponent("editor", UserEditor.class);
    }

    @Test
    void testFormExist() {
        tester.assertExists("editor:editForm");

    }

    @ParameterizedTest
    @ValueSource(strings = {"userName", "firstName", "otherName", "password", "email", "telephone1", "telephone2"})
    public void testInputComponentsRender(String wicketId) {
        String path = "editor:editForm:sectionContainer:" + wicketId;
        tester.assertExists(path);
    }

    @ParameterizedTest
    @ValueSource(strings = {"save", "cancel", "new", "delete"})
    public void testComandComponentsRender(String wicketId) {
        String path = "editor:editForm:" + wicketId;
        tester.assertExists(path);
    }

    @Test
    public void testSaveCalled() {
        FormTester formTester = tester.newFormTester("editor:editForm");

        UserEditor editor = (UserEditor) tester.getComponentFromLastRenderedPage("editor");
        Subject subject = (Subject) editor.getDefaultModelObject();

        tester.assertExists("editor:editForm:sectionContainer:userName");
        populateSubject(formTester);

        assertNotNull(this.userService);
        instance.setUserService(userService);
        // click the save button.
        tester.executeAjaxEvent("editor:editForm:save", "click");
        //Method cant be called as there is not mode
//      assertTrue("Administrator".equals(subject.getUserName()));
        verify(userService, times(1)).persisteUser(subject);
    }

    @Test
    public void testDeleteMethodCalled() {
        FormTester formTester = tester.newFormTester("editor:editForm");
        UserEditor editor = (UserEditor) tester.getComponentFromLastRenderedPage("editor");
        Subject subject = (Subject) editor.getDefaultModelObject();
        populateSubject(formTester);
        instance.setUserService(userService);
        tester.executeAjaxEvent("editor:editForm:delete", "click");

        verify(userService, times(1)).deleteUser(subject);
    }

    /**
     * Populating the form tester is setting the value to the FormComponentPanel
     * not the Inner form.
     *
     * @param formTester
     */
    private void populateSubject(FormTester formTester) {
        FormComponent component = (FormComponent) tester.getComponentFromLastRenderedPage("editor:editForm:sectionContainer:userName:inputComponent");
        formTester.setValue(component, "Administrator");
        component = (FormComponent) tester.getComponentFromLastRenderedPage("editor:editForm:sectionContainer:password:inputComponent");
        formTester.setValue(component, "Password");
        component = (FormComponent) tester.getComponentFromLastRenderedPage("editor:editForm:sectionContainer:firstName:inputComponent");
        formTester.setValue(component, "Jason");
        component = (FormComponent) tester.getComponentFromLastRenderedPage("editor:editForm:sectionContainer:familyName:inputComponent");
        formTester.setValue(component, "David");
        component = (FormComponent) tester.getComponentFromLastRenderedPage("editor:editForm:sectionContainer:otherName:inputComponent");
        formTester.setValue(component, "Smith");
        component = (FormComponent) tester.getComponentFromLastRenderedPage("editor:editForm:sectionContainer:email:inputComponent");
        formTester.setValue(component, "jason@smith.com");
        component = (FormComponent) tester.getComponentFromLastRenderedPage("editor:editForm:sectionContainer:telephone1:inputComponent");
        formTester.setValue(component, "128-265-5698");
        component = (FormComponent) tester.getComponentFromLastRenderedPage("editor:editForm:sectionContainer:telephone2:inputComponent");
        formTester.setValue(component, "128-265-8965");
    }
}
