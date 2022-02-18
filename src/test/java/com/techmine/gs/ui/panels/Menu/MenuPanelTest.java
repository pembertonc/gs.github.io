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
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 *
 * @author Cedric-Pemberton
 */
@ExtendWith(CdiUnitExtension.class)
public class MenuPanelTest {

    WicketTester tester1;

    MenuPanel instance;

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
        this.instance = this.tester1.startComponentInPage(new MenuPanel("menu"));
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of onInitialize method, of class MenuPanel.
     */
    @Test
    public void testPageRenders() {
        tester1.assertComponent("menu", MenuPanel.class);

        tester1.assertExists("menu:logout");
        tester1.assertComponent("menu:logout", AjaxFallbackLink.class);

    }

    @Test
    public void testLogOutButtonHidenWhenSinInPanelDisplayed() {

        AjaxFallbackLink link = (AjaxFallbackLink) tester1.getComponentFromLastRenderedPage("menu:logout", true);
        //assertFalse(link.isVisible(), "Link Should not be visible when SingInPanel is displayed");
        tester1.assertInvisible("menu:logout");

    }

}
