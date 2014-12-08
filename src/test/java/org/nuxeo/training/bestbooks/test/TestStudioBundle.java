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
package org.nuxeo.training.bestbooks.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nuxeo.ecm.automation.AutomationService;
import org.nuxeo.ecm.automation.InvalidChainException;
import org.nuxeo.ecm.automation.OperationContext;
import org.nuxeo.ecm.automation.OperationException;
import org.nuxeo.ecm.automation.test.AutomationFeature;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.DocumentRef;
import org.nuxeo.runtime.RuntimeService;
import org.nuxeo.runtime.api.Framework;
import org.nuxeo.runtime.test.runner.Deploy;
import org.nuxeo.runtime.test.runner.Features;
import org.nuxeo.runtime.test.runner.FeaturesRunner;

import com.google.inject.Inject;

@RunWith(FeaturesRunner.class)
@Features(AutomationFeature.class)
@Deploy({"studio.extensions.formation-maaf"})

/**
 *
 *
 * @since 6.0
 */
public class TestStudioBundle {
  @Inject CoreSession session;

  @Inject AutomationService automation;

  @Test
  public void shouldDeployRuntime() {
    RuntimeService runtime = Framework.getRuntime();
    Assert.assertNotNull(runtime);
  }

  @Test
  public void shouldHaveAPublicationDate() {
    DocumentModel doc = session.createDocumentModel("/", "myBook", "Book"); // Appelle empty doc created
    Assert.assertNotNull(doc.getPropertyValue("book:publicationDate"));
  }

  @Test
  public void shouldHaveCorrectTitle() {
    DocumentModel doc = session.createDocumentModel("/", "peuImporte", "Book");

    doc.setPropertyValue("dc:title", "Da Nuxeo Code");
    session.createDocument(doc); // Appelle about to create et doc created
    session.save();

    // Récupération directe
    DocumentRef docRef = doc.getRef();
    doc = session.getDocument(docRef);

    Assert.assertEquals("Da Nuxeo Code", doc.getTitle());
  }

  @Test
  public void shouldBeBorrowedBySomeone() throws InvalidChainException, OperationException, Exception {
    DocumentModel doc = session.createDocumentModel("/", "peuImporte", "Book");

    doc.setPropertyValue("dc:title", "Da Nuxeo Code");
    session.createDocument(doc); // Appelle about to create et doc created
    session.save();

    OperationContext ctx = new OperationContext();
    ctx.setCoreSession(session);
    ctx.setInput(doc);
    doc = (DocumentModel) automation.run(ctx, "borrowBookChain");

    Assert.assertEquals("borrowed", doc.getCurrentLifeCycleState());
    Assert.assertNotNull(doc.getPropertyValue("book:borrowedBy"));
  }


}
