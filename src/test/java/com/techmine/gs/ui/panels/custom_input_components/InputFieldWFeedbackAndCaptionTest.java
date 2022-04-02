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
package com.techmine.gs.ui.panels.custom_input_components;

import com.techmine.gs.ui.panels.custom_input_components.InputFieldWFeedbackAndCaption;
import com.techmine.gs.testutils.TestPage;
import com.techmine.gs.ui.panels.custom_input_components.InputFieldWFeedbackAndCaption;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Cedric-Pemberton
 */
public class InputFieldWFeedbackAndCaptionTest {

    private WicketTester tester;
    private InputFieldWFeedbackAndCaption instance;

    private String modelData;
    private IModel<String> model;

    public String getModelData() {
        return modelData;
    }

    public void setModelData(String model) {
        this.modelData = model;
    }

    public InputFieldWFeedbackAndCaptionTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        tester = new WicketTester();
        modelData = "Some Data";
        this.model = LambdaModel.of(this::getModelData, this::setModelData);

    }

    @AfterEach
    public void tearDown() {
        tester.destroy();
    }

    @Test
    @DisplayName("That the Panel renders.")
    public void testComponentRenders() {
        instance = new InputFieldWFeedbackAndCaption("input", model, "Caption", InputFieldWFeedbackAndCaption.FieldType.TEXT);
        tester.startComponentInPage(instance);
    }

    @Test
    public void testFeedbackErrorPresentOnRequiredViolation() {
        instance = new InputFieldWFeedbackAndCaption("input", model, "Caption", InputFieldWFeedbackAndCaption.FieldType.TEXT);
        instance.setRequired(true);
        tester.startComponentInPage(instance);

        tester.assertComponent("input:errorMessage", FeedbackPanel.class);

        tester.executeAjaxEvent("input:inputComponent", "blur");
        tester.assertErrorMessages("'Caption' is required.");
        tester.assertBehavior("input:inputComponent", AjaxFormComponentUpdatingBehavior.class);
    }

    @Test
    @DisplayName("That the Panel renders.")
    public void testAjaxFormComponentUpdaterPresent() {
        instance = new InputFieldWFeedbackAndCaption("input", model, "Caption", InputFieldWFeedbackAndCaption.FieldType.TEXT);
        tester.startComponentInPage(instance);
        tester.assertBehavior("input:inputComponent", AjaxFormComponentUpdatingBehavior.class);
    }

    @Test
    public void testInternalInputRenderedTextField() {
        instance = new InputFieldWFeedbackAndCaption("input", model, "Caption", InputFieldWFeedbackAndCaption.FieldType.TEXT);
        tester.startComponentInPage(instance);
        //TextField comp = (TextField) tester.getComponentFromLastRenderedPage("input:inputComponent");

        tester.assertComponent("input:inputComponent", TextField.class);
        //Assertions.assertEquals(comp.getClass().getName(), TextField.class.getName());

    }

    @Test
    public void testInternalInputRenderedPasswordTextField() {
        instance = new InputFieldWFeedbackAndCaption("input", model, "Caption", InputFieldWFeedbackAndCaption.FieldType.PASSWORD);
        tester.startComponentInPage(instance);
        Component comp = tester.getComponentFromLastRenderedPage("input:inputComponent");

        tester.assertComponent("input:inputComponent", PasswordTextField.class);
        //Assertions.assertEquals(comp.getClass().getName(), PasswordTextField.class.getName());
    }

    @Test
    public void assertThatComponentReturnsValue() {
        // WicketTester localTester = new WicketTester();
        InputFieldWFeedbackACaptionTestingComponent ainstance = new InputFieldWFeedbackACaptionTestingComponent("testComponent", PropertyModel.of(this, "modelData"));
        tester.startComponentInPage(ainstance);
        FormTester ft = tester.newFormTester("testComponent:testForm");
        ft.setValue("inputComponent", "J. Johana Jammerson");

        Assertions.assertTrue(modelData.equals(model.getObject()));
        Assertions.assertFalse("J. Johana Jammerson".equalsIgnoreCase(model.getObject()));

        tester.executeAjaxEvent("testComponent:testForm:submit", "click");

        Assertions.assertEquals("J. Johana Jammerson", model.getObject());

    }
// Class to used as containing component for InpuFiedlWXXX

    class InputFieldWFeedbackACaptionTestingComponent extends Panel {

        public InputFieldWFeedbackACaptionTestingComponent(String id, IModel<?> model) {
            super(id, model);
        }

        @Override
        protected void onInitialize() {
            super.onInitialize();
            Form<String> testForm = new Form<>("testForm", (IModel<String>) getDefaultModel());
            this.add(testForm);
            testForm.setOutputMarkupId(true);
            InputFieldWFeedbackAndCaption customComponent = new InputFieldWFeedbackAndCaption("customComponent", getDefaultModel(), "Custom Component", InputFieldWFeedbackAndCaption.FieldType.TEXT);
            testForm.add(customComponent);
            AjaxFallbackButton submitButton = new AjaxFallbackButton("submit", testForm) {
            };
            submitButton.add(new AjaxFormSubmitBehavior("click") {
            });
            testForm.add(submitButton);
        }

    }

}
