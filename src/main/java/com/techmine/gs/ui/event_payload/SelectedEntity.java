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
package com.techmine.gs.ui.event_payload;

import com.techmine.gs.domain.BaseEntity;
import java.util.Optional;
import org.apache.wicket.ajax.AjaxRequestTarget;

/**
 *
 * @author Cedric-Pemberton
 */
public class SelectedEntity<T> {

    private AjaxRequestTarget target;
    private CRUDEventActions action;
    private String entityId;
    private Class<T> clazz;
    /**
     * accepts only sub-classes of the base entity. holds the selected entity.
     */
    private BaseEntity entity;

    public Optional<AjaxRequestTarget> getTarget() {
        return Optional.ofNullable(target);
    }

    public void setTarget(AjaxRequestTarget target) {
        this.target = target;
    }

    public CRUDEventActions getAction() {
        return action;
    }

    public void setAction(CRUDEventActions action) {
        this.action = action;
    }

    public BaseEntity getEntity() {
        return entity;
    }

    public void setEntity(BaseEntity entity) {
        this.entity = entity;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    public SelectedEntity action(CRUDEventActions action) {
        this.action = action;
        return this;
    }

    public SelectedEntity target(AjaxRequestTarget target) {
        this.target = target;
        return this;
    }

    public SelectedEntity entity(BaseEntity entity) {
        this.entity = entity;
        return this;
    }

    public SelectedEntity clazz(String entityId) {
        this.entityId = entityId;
        return this;
    }

    public SelectedEntity clazz(Class<T> clazz) {
        this.clazz = clazz;
        return this;
    }

    /*public enum Action {
    DELETE, EDIT, UPDATE
    }*/
}
