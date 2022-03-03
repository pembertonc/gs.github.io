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

import com.techmine.gs.domain.Subject;
import com.techmine.gs.testutils.TestObjects;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Cedric-Pemberton
 */
public class TextFieldWFeedbackTest {

    private WicketTester tester;

    private final Subject subject = TestObjects.getSubject();  // used as data model for testing.
    private TextFieldWFeedback instance;

    public TextFieldWFeedbackTest() {
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

        IModel<String> model = LambdaModel.of(subject::getUserName, subject::setUserName);
        TextFieldWFeedback textFieldWFeedback = new TextFieldWFeedback("enhancedComponent", model, "User Name");
        textFieldWFeedback.internalSetRequired(true);
        this.instance = tester.startComponentInPage(textFieldWFeedback);
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of internalAdd method, of class TextFieldWFeedback.
     */
    @Test
    public void testComponentRenders() {
        tester.assertComponent("enhancedComponent", TextFieldWFeedback.class);
    }

    @Test
    public void testBlurOnInputField() {

        tester.assertFeedback("enhancedComponent:feedback");
        tester.executeAjaxEvent("enhancedComponent:inputComponent", "blue");
        tester.assertFeedback("enhancedComponent:feedback");
    }

}
