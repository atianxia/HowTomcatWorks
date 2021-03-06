/* $Id: ConnectorCreateRule.java 303287 2004-09-29 09:55:39Z remm $
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.catalina.startup;

import org.apache.catalina.Connector;
import org.apache.commons.digester.Rule;
import org.xml.sax.Attributes;

/**
 * Rule implementation that creates a connector.
 */

public class ConnectorCreateRule extends Rule {

    // --------------------------------------------------------- Public Methods

    /**
     * Process the beginning of this element.
     * 
     * @param attributes
     *            The attribute list of this element
     */
    public void begin(Attributes attributes) throws Exception {
        Class clazz;
        Connector connector;

        String className = attributes.getValue("className");
        clazz = Class.forName(className);

        if (className.equals("org.apache.coyote.tomcat4.CoyoteConnector")) {
            Class argsClass[] = { String.class };
            Object argsObjs[] = { attributes
                    .getValue("protocolHandlerClassName") };
            connector = (Connector) clazz.getConstructor(argsClass)
                    .newInstance(argsObjs);
        } else {
            Class argsClass[] = {};
            Object argsObjs[] = {};
            connector = (Connector) clazz.getConstructor(argsClass)
                    .newInstance(argsObjs);
        }
        digester.push(connector);
    }

    /**
     * Process the end of this element.
     */
    public void end() throws Exception {
        Object top = digester.pop();
    }

}
