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
package com.techmine.gs.ui.panels.custom_input_components.TextFieldWithMessage;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Cedric-Pemberton
 */
public class TextFieldsWFeedbackAndMessageTest {

    private String modelData;

    WicketTester tester;
    IModel<String> model;

    public TextFieldsWFeedbackAndMessageTest() {
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
        model = LambdaModel.of(this::getModelData, this::setModelData);
    }

    @AfterEach
    public void tearDown() {
        tester.destroy();
    }

    public String getModelData() {
        return modelData;
    }

    public void setModelData(String modelData) {
        this.modelData = modelData;
    }

    @Test
    public void testPasswordTextFieldWFeedbackAndMessageHasCorrectUnderlyingTextField() {

        PasswordTextFieldWFeedbackAndMessage component = new PasswordTextFieldWFeedbackAndMessage("input", model, "Model Data");
        tester.startComponentInPage(component);
        Component inputComponent = tester.getComponentFromLastRenderedPage("input:inputComponent");
        tester.assertComponent("input:inputComponent", TextField.class);  // assert Component is sub-class of TextField.
        tester.assertComponent("input:inputComponent", PasswordTextField.class);  // assert Component is sub-class of TextField.
        assertTrue(PasswordTextField.class.getSimpleName().equals(inputComponent.getClass().getSimpleName()), "inputComponent is not an instance of PasswordTextField");
    }

    @Test
    public void testTextFieldWFeedbackAndMessageHasCorrectUnderlyingTextField() {

        Component component = new TextFieldWFeedbackAndMessage("input", model, "Model Data");
        tester.startComponentInPage(component);
        Component inputComponent = tester.getComponentFromLastRenderedPage("input:inputComponent");
        assertNotNull(inputComponent, "Input component is null");
        tester.assertComponent("input:inputComponent", TextField.class);  // assert Component is sub-class of TextField.
        assertTrue(TextField.class.getSimpleName().equals(inputComponent.getClass().getSimpleName()), "inputComponent is not an instance of TextField");
    }
}
