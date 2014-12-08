/*
 * (C) Copyright 2014 Nuxeo SA (http://nuxeo.com/) and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-2.1.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * Contributors:
 *     Bertrand Chauvin (bchauvin@nuxeo.com)
 */
package org.nuxeo.training.bestbooks;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.runtime.api.Framework;

/**
 *
 *
 * @since TODO
 */

@Name("bestBooksLibrary")
@Scope(ScopeType.EVENT)
public class BestBooksLibraryBean {

  public int getChildrenNumber(DocumentModel doc) {
    BestBooksService bbsce = Framework.getLocalService(BestBooksService.class);
    return bbsce.getChildrenNumber(doc);
  }
}
