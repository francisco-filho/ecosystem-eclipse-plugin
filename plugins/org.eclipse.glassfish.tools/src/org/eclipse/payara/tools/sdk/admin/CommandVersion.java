/******************************************************************************
 * Copyright (c) 2018 Oracle
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/

package org.eclipse.payara.tools.sdk.admin;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.eclipse.payara.tools.sdk.GlassFishIdeException;
import org.eclipse.payara.tools.sdk.logging.Logger;
import org.eclipse.payara.tools.sdk.utils.ServerUtils;
import org.eclipse.payara.tools.server.GlassFishServer;
import org.eclipse.sapphire.Version;

/**
 * GlassFish Server Version Command Entity.
 * <p/>
 * Holds data for command. Objects of this class are created by API user.
 * <p/>
 * 
 * @author Tomas Kraus, Peter Benedikovic
 */
@RunnerHttpClass
@RunnerRestClass
public class CommandVersion extends Command {

    ////////////////////////////////////////////////////////////////////////////
    // Class attributes //
    ////////////////////////////////////////////////////////////////////////////

    /** Logger instance for this class. */
    private static final Logger LOGGER = new Logger(CommandVersion.class);

    /** Command string for version command. */
    private static final String COMMAND = "version";

    ////////////////////////////////////////////////////////////////////////////
    // Static methods //
    ////////////////////////////////////////////////////////////////////////////

    /**
     * Retrieve version from server.
     * <p/>
     * 
     * @param server GlassFish server entity.
     * @return GlassFish command result containing version string returned by server.
     * @throws GlassFishIdeException When error occurred during administration command execution.
     */
    public static ResultString getVersion(final GlassFishServer server)
            throws GlassFishIdeException {
        final String METHOD = "getVersion";
        Future<ResultString> future = ServerAdmin.<ResultString>exec(server, new CommandVersion());
        try {
            return future.get();
        } catch (ExecutionException | InterruptedException
                | CancellationException ee) {
            throw new CommandException(LOGGER.excMsg(METHOD, "exception"),
                    ee.getLocalizedMessage());
        }
    }

    /**
     * Retrieve version from server.
     * <p/>
     * 
     * @param server GlassFish server entity.
     * @return GlassFish command result containing {@link GlassFishVersion} object retrieved from server
     * or <code>null</code> if no version was returned.
     * @throws GlassFishIdeException When error occurred during administration command execution.
     */
    public static Version getGlassFishVersion(
            final GlassFishServer server) {
        ResultString result;
        try {
            result = getVersion(server);
        } catch (CommandException ce) {
            return null;
        }
        String value = result != null
                ? ServerUtils.getVersionString(result.getValue())
                : null;
        if (value != null) {
            return new Version(value);
        } else {
            return null;
        }
    }

    /**
     * Verifies if domain directory returned by version command result matches domain directory of
     * provided GlassFish server entity.
     * <p/>
     * 
     * @param result Version command result.
     * @param server GlassFish server entity.
     * @return For local server value of <code>true</code> means that server major and minor version
     * value matches values returned by version command and value of <code>false</code> that they
     * differs.
     */
    public static boolean verifyResult(
            final ResultString result, final GlassFishServer server) {
        boolean verifyResult = false;
        String value = ServerUtils.getVersionString(result.getValue());
        if (value != null) {
            Version valueVersion = new Version(value);
            Version serverVersion = server.getVersion();
            if (valueVersion != null && serverVersion != null) {
                verifyResult = serverVersion.equals(valueVersion);
            }
        }
        return verifyResult;
    }

    ////////////////////////////////////////////////////////////////////////////
    // Constructors //
    ////////////////////////////////////////////////////////////////////////////

    /**
     * Constructs an instance of GlassFish server version command entity.
     */
    public CommandVersion() {
        super(COMMAND);
    }

}
