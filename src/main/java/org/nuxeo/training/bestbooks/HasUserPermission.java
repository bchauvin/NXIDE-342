/*
 * (C) Copyright ${year} Nuxeo SA (http://nuxeo.com/) and others.
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

import org.nuxeo.ecm.automation.OperationContext;
import org.nuxeo.ecm.automation.core.Constants;
import org.nuxeo.ecm.automation.core.annotations.Context;
import org.nuxeo.ecm.automation.core.annotations.Operation;
import org.nuxeo.ecm.automation.core.annotations.OperationMethod;
import org.nuxeo.ecm.automation.core.annotations.Param;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.IdRef;
import org.nuxeo.ecm.core.api.NuxeoPrincipal;
import org.nuxeo.ecm.platform.usermanager.UserManager;

/**
 *
 */
@Operation(id = HasUserPermission.ID, category = Constants.CAT_USERS_GROUPS, label = "Has User Permission",
    description = "Check if user has permission on given doc id and set the result into a context variable.")
public class HasUserPermission {

  @Context
  protected CoreSession session;

  @Context
  protected OperationContext ctx;

  @Context
  protected UserManager um;

  @Param(name = "Context variable name")
  protected String ctxVar;

  @Param(name = "Permission")
  protected String permission;

  @Param(name = "Username")
  protected String username;

  @Param(name = "Doc Id")
  protected String docId;

  public static final String ID = "HasUserPermission";

  @OperationMethod
  public void run() {
    IdRef docRef = new IdRef(docId);
    NuxeoPrincipal principal = um.getPrincipal(username);
    boolean hasPermission = session.hasPermission(principal, docRef, permission);
    ctx.put(ctxVar, hasPermission);
  }

}
