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
package com.techmine.gs.ui.panels.views.userView;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 *
 * @author Cedric-Pemberton
 */
public class UserEditorTest {

    WicketTester tester;
    UserEditor instance;

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
        instance = new UserEditor("editor");
        tester.startComponentInPage(instance);
    }

    @AfterEach
    public void tearDown() {
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
    @ValueSource(strings = {"userName", "firstName", "otherName", "password", "email", "telephone1", "telephone2", "save", "cancel", "new"})
    public void testFieldsRender(String wicketId) {
        String path = "editor:editForm:" + wicketId;
        tester.assertExists(path);

    }

}
