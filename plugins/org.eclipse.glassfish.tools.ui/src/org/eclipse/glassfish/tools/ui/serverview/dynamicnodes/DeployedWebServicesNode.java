/****************************************************************************** * Copyright (c) 2018 Oracle * All rights reserved. This program and the accompanying materials * are made available under the terms of the Eclipse Public License v2.0 * which accompanies this distribution, and is available at * http://www.eclipse.org/legal/epl-v20.html
 * SPDX-License-Identifier: EPL-2.0 ******************************************************************************/package org.eclipse.glassfish.tools.ui.serverview.dynamicnodes;import static org.eclipse.glassfish.tools.utils.NodesUtils.getWebServices;import java.util.ArrayList;import java.util.List;import org.eclipse.glassfish.tools.GlassfishToolsPlugin;import org.eclipse.glassfish.tools.server.GlassFishServer;import org.eclipse.glassfish.tools.serverview.WSDesc;/** * This node shows is the root node below which * the dynamically retrieved web-services * reside. *  * <p> * The following depicts this element in the "Servers" view: * </p> * <p> * <pre> * Payara 5 [domain1] * |- GlassFish Management *     |-Resources *     |-Deployed Applications  *     |-Deployed Web Services * * |- [WTP managed application] * </pre> * </p> *  * <p> * Payara / GlassFish is dynamically queried for this list, hence * it can only be retrieved for a running server. * </> * */public class DeployedWebServicesNode extends TreeNode {	GlassFishServer server;	WebServiceNode[] deployedapps;	public DeployedWebServicesNode(GlassFishServer server) {		super("Deployed Web Services", null, null);		this.server = server;	}	public GlassFishServer getServer() {		return server;	}	public Object[] getChildren() {		ArrayList<WebServiceNode> appsList = new ArrayList<WebServiceNode>();		if (deployedapps == null) {			try {				if (server == null) {					deployedapps = appsList.toArray(new WebServiceNode[appsList.size()]);					return deployedapps;				}				try {					List<WSDesc> wss = getWebServices(server);					for (WSDesc app : wss) {						WebServiceNode t = new WebServiceNode(this, server,								app);						appsList.add(t);					}				} catch (Exception ex) {					GlassfishToolsPlugin.logError("get Applications is failing=", ex); //$NON-NLS-1$				}			} catch (Exception e) {			}			this.deployedapps = appsList					.toArray(new WebServiceNode[appsList.size()]);		}		return this.deployedapps;	}	public void refresh() {		this.deployedapps = null;	}}