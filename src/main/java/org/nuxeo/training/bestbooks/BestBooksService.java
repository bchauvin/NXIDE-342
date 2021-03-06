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

import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.runtime.model.ComponentContext;
import org.nuxeo.runtime.model.DefaultComponent;
import org.osgi.framework.Bundle;

/**
 *
 */
public class BestBooksService extends DefaultComponent {

  protected Bundle bundle;

  public Bundle getBundle() {
    return bundle;
  }

  /**
   * Component activated notification. Called when the component is activated.
   * All component dependencies are resolved at that moment. Use this method to
   * initialize the component.
   * <p>
   * The default implementation of this method is storing the Bundle owning that
   * component in a class field. You can use the bundle object to lookup for
   * bundle resources:
   * <code>URL url = bundle.getEntry("META-INF/some.resource");</code>, load
   * classes or to interact with OSGi framework.
   * <p>
   * Note that you must always use the Bundle to lookup for resources in the
   * bundle. Do not use the classloader for this.
   *
   * @param context
   *          the component context. Use it to get the current bundle context
   */
  @Override
  public void activate(ComponentContext context) {
    bundle = context.getRuntimeContext().getBundle();
  }

  /**
   * Component deactivated notification. Called before a component is
   * unregistered. Use this method to do cleanup if any and free any resources
   * held by the component.
   *
   * @param context
   *          the component context. Use it to get the current bundle context
   */
  @Override
  public void deactivate(ComponentContext context) {
    bundle = null;
  }

  /**
   * Application started notification. Called after the application started. You
   * can do here any initialization that requires a working application (all
   * resolved bundles and components are active at that moment)
   *
   * @param context
   *          the component context. Use it to get the current bundle context
   * @throws Exception
   */
  @Override
  public void applicationStarted(ComponentContext context) throws Exception {
    // do nothing by default. You can remove this method if not used.
  }

  public int getChildrenNumber(DocumentModel doc) {
    CoreSession session = doc.getCoreSession();
    return session.getChildren(doc.getRef()).size();
  }

  public boolean isNumeric(String str) {
    for (char c : str.toCharArray()) {
      if (!Character.isDigit(c)) {
        return false;
      }
    }
    return true;
  }

}
