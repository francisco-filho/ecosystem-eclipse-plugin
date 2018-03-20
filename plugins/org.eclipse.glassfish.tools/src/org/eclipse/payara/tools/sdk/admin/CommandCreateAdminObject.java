/******************************************************************************
 * Copyright (c) 2018 Oracle
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/

package org.eclipse.payara.tools.sdk.admin;

import java.util.Map;

/**
 * Command that creates administered object with the specified JNDI name and the interface
 * definition for a resource adapter on server.
 * <p/>
 * 
 * @author Tomas Kraus, Peter Benedikovic
 */
@RunnerHttpClass(runner = RunnerHttpCreateAdminObject.class)
public class CommandCreateAdminObject extends Command {

    ////////////////////////////////////////////////////////////////////////////
    // Class attributes //
    ////////////////////////////////////////////////////////////////////////////

    /** Command string for create administered object command. */
    private static final String COMMAND = "create-admin-object";

    ////////////////////////////////////////////////////////////////////////////
    // Instance attributes //
    ////////////////////////////////////////////////////////////////////////////

    /** The JNDI name of this JDBC resource. */
    final String jndiName;

    /** Resource type. */
    final String resType;

    /**
     * The name of the resource adapter associated with this administered object.
     */
    final String raName;

    /** Optional properties for configuring administered object. */
    final Map<String, String> properties;

    /** If this object is enabled. */
    final boolean enabled;

    ////////////////////////////////////////////////////////////////////////////
    // Constructors //
    ////////////////////////////////////////////////////////////////////////////

    /**
     * Constructs an instance of GlassFish server create administered object command entity.
     * <p/>
     * 
     * @param jndiName The JNDI name of this JDBC resource.
     * @param resType Resource type.
     * @param raName The name of the resource adapter associated with this administered object.
     * @param properties Optional properties for configuring the pool.
     * @param enabled If this object is enabled.
     */
    public CommandCreateAdminObject(final String jndiName,
            final String resType, final String raName,
            final Map<String, String> properties, final boolean enabled) {
        super(COMMAND);
        this.resType = resType;
        this.jndiName = jndiName;
        this.raName = raName;
        this.properties = properties;
        this.enabled = enabled;
    }

    /**
     * Constructs an instance of GlassFish server create administered object command entity.
     * <p/>
     * This object will be enabled on server by default.
     * <p/>
     * 
     * @param jndiName The JNDI name of this JDBC resource.
     * @param resType Resource type.
     * @param raName The name of the resource adapter associated with this administered object.
     * @param properties Optional properties for configuring the pool.
     */
    public CommandCreateAdminObject(final String jndiName,
            final String resType, final String raName,
            final Map<String, String> properties) {
        this(jndiName, resType, raName, properties, true);
    }

}
