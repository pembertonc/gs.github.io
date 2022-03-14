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

import com.techmine.gs.domain.BaseEntity;
import java.util.Optional;
import org.apache.wicket.ajax.AjaxRequestTarget;

/**
 *
 * @author Cedric-Pemberton
 */
public class NotificationEvent<T extends BaseEntity> {

    private CRUDEventActions action;
    private Class<T> entityType;
    private AjaxRequestTarget target;

    public CRUDEventActions getAction() {
        return action;
    }

    public void setAction(CRUDEventActions action) {
        this.action = action;
    }

    public Optional<AjaxRequestTarget> getTarget() {
        return Optional.ofNullable(target);
    }

    public void setTarget(AjaxRequestTarget target) {
        this.target = target;
    }

    public Class<T> getEntityType() {
        return entityType;
    }

    public void setEntityType(Class<T> entityType) {
        this.entityType = entityType;
    }

}
