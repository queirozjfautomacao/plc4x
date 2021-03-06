/*
Licensed to the Apache Software Foundation (ASF) under one
or more contributor license agreements.  See the NOTICE file
distributed with this work for additional information
regarding copyright ownership.  The ASF licenses this file
to you under the Apache License, Version 2.0 (the
"License"); you may not use this file except in compliance
with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
KIND, either express or implied.  See the License for the
specific language governing permissions and limitations
under the License.
*/
package org.apache.plc4x.camel;

import org.apache.camel.Endpoint;
import org.apache.camel.support.DefaultComponent;
import org.apache.camel.util.IntrospectionSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Plc4XComponent extends DefaultComponent {
    private static final Logger LOGGER = LoggerFactory.getLogger(Plc4XComponent.class);

    @Override
    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        Endpoint endpoint = new Plc4XEndpoint(uri, this);
        List<TagData>tags = getAndRemoveOrResolveReferenceParameter(parameters,"tags", List.class);
        if(tags!=null){
            ((Plc4XEndpoint)endpoint).setTags(tags);
        }
        setProperties(endpoint,parameters);
        return endpoint;
    }

    @Override
    protected void afterConfiguration(String uri, String remaining, Endpoint endpoint, Map<String, Object> parameters) {
        Plc4XEndpoint plc4XEndpoint = (Plc4XEndpoint) endpoint;
        plc4XEndpoint.setDriver(remaining.split(":")[0]);
    }

    @Override
    protected void validateParameters(String uri, Map<String, Object> parameters, String optionPrefix) {
        if (parameters != null && !parameters.isEmpty()) {
            Map<String, Object> param = parameters;
            if (optionPrefix != null) {
                param = IntrospectionSupport.extractProperties(parameters, optionPrefix);
            }

            if (parameters.size() > 0) {
                LOGGER.info("{} parameters will be passed to the PLC Driver",param);
            }
        }
    }

}