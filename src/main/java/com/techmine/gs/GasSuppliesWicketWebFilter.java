/*
 * Copyright 2021 Cedric Pemberton.
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
package com.techmine.gs;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import org.apache.wicket.protocol.http.WicketFilter;

/**
 *
 * @author Cedric Pemberton
 */
@WebFilter(value = "/*", displayName = "gs", initParams = {
    @WebInitParam(name = "applicationClassName", value = "com.techmine.gs.WicketApplication"),
    @WebInitParam(name = "configuration", value = "development")})
public class GasSuppliesWicketWebFilter extends WicketFilter {

}
