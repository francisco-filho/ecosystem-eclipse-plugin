/******************************************************************************
 * Copyright (c) 2018 Oracle
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/

package org.eclipse.glassfish.tools.ui.preferences;

import static org.eclipse.glassfish.tools.preferences.PreferenceConstants.ENABLE_COLORS_CONSOLE;
import static org.eclipse.glassfish.tools.preferences.PreferenceConstants.ENABLE_LOG;
import static org.eclipse.glassfish.tools.preferences.PreferenceConstants.ENABLE_START_VERBOSE;

import org.eclipse.glassfish.tools.GlassfishToolsPlugin;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;



/**
 * This class represents a preference page that is contributed to the
 * Preferences dialog. By subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows us to create a page
 * that is small and knows how to save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They are stored in the
 * preference store that belongs to the main plug-in class. That way,
 * preferences can be accessed directly via the preference store.
 * 
 * @author Ludovic Champenois
 */
public class GlassFishPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public GlassFishPreferencePage() {
		super(GRID);
		setPreferenceStore(GlassfishToolsPlugin.getInstance().getPreferenceStore());
		setDescription("You can configure Payara Enterprise Server Plugin global preferences");
	}
	
	/**
	 * Creates the field editors. Field editors are abstractions of the common GUI
	 * blocks needed to manipulate various types of preferences. Each field editor
	 * knows how to save and restore itself.
	 */
	public void createFieldEditors() {
		addField(
				new BooleanFieldEditor(
					ENABLE_LOG,
					"&Enable Payara Plugin Log information in the IDE log file",
					getFieldEditorParent()));
		addField(
				new BooleanFieldEditor(
					ENABLE_START_VERBOSE,
					"&Start Payara Server in verbose mode (the Eclipse console can be used)",
					getFieldEditorParent()));

		addField(
				new BooleanFieldEditor(
					ENABLE_COLORS_CONSOLE,
					"Enable colored rendering in the Payara Log Viewer",
					getFieldEditorParent()));
		}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}
	
}