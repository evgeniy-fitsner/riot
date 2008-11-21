/* ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 * 
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 * 
 * The Original Code is Riot.
 * 
 * The Initial Developer of the Original Code is
 * Neteye GmbH.
 * Portions created by the Initial Developer are Copyright (C) 2006
 * the Initial Developer. All Rights Reserved.
 * 
 * Contributor(s):
 *   Felix Gnass [fgnass at neteye dot de]
 * 
 * ***** END LICENSE BLOCK ***** */
package org.riotfamily.common.web.file;

import java.io.File;
import java.io.IOException;

public interface FileStore {

	/**
	 * Moves the given file into the store and returns an URI that can be
	 * used to request the file via HTTP.
	 * <p>
	 * Implementors are expected to move the given file to a new location.
	 * </p>
	 * @param file The file to move into the store
	 * @param fileName The desired target file name, or <code>null</code> if it
	 * 		  should be up to the store to choose a name 
	 * @return The URI to access the stored file
	 */
	public String store(File file, String fileName)	throws IOException;
	
	/**
	 * Retrieves a file from the store that was previously added via the
	 * {@link #store(File, String) store()} method. 
	 */
	public File retrieve(String uri);
	
	/**
	 * Deletes the file denoted by the given URI from the store.
	 */
	public void delete(String uri);
	
}