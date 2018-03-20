/******************************************************************************
 * Copyright (c) 2018 Oracle
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/

package org.eclipse.glassfish.tools.handlers;

import static org.eclipse.glassfish.tools.utils.WtpUtil.load;

import java.io.File;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.glassfish.tools.server.GlassFishServer;
import org.eclipse.wst.server.core.IServer;

public class GlassFishVersionTester extends PropertyTester {

	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		IServer server = (IServer) receiver;
		
		return new File(load(server, GlassFishServer.class).getServerInstallationDirectory() + "/modules").exists();
	}

}
