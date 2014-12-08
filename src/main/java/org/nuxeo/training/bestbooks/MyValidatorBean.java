package org.nuxeo.training.bestbooks;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.nuxeo.runtime.api.Framework;

/*
 * JSF / Seam validator sample, to be used in a Studio widget.
 * Use #{myValidator.checkNumeric} in the widget to call it
 */

@Name("myValidator")
@Scope(ScopeType.STATELESS)
public class MyValidatorBean implements Serializable {

  private static final long serialVersionUID = 1L;

  public void checkNumeric(FacesContext context, UIComponent component, Object value) {
    BestBooksService bbsce = Framework.getLocalService(BestBooksService.class);
    if (bbsce.isNumeric(value.toString())) {
      return;
    }

    String msg = "You shall not pass";
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
    throw new ValidatorException(message);
  }
}
