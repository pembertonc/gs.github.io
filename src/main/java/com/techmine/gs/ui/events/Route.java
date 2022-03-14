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
package com.techmine.gs.ui.events;

import java.util.Optional;
import org.apache.wicket.ajax.AjaxRequestTarget;

/**
 *
 * @author Cedric-Pemberton
 */
public class Route {

    private final Actions action;

    private final Optional<AjaxRequestTarget> target;

    public Route(Actions action, Optional<AjaxRequestTarget> target) {
        this.action = action;
        this.target = target;
    }

    public Actions getAction() {
        return this.action;
    }

    public Optional<AjaxRequestTarget> getTarget() {
        return target;
    }

    public enum Actions {
        LOGIN, LOGOUT, USER
    }

}
