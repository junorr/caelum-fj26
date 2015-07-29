package br.com.caelum.notasfiscais.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class JSFUtil {
	
	private FacesContext context = FacesContext.getCurrentInstance();
	
	
	public void addMessage(String message) {
		context.addMessage(null, new FacesMessage(message));
	}

	public void addMessage(FacesMessage.Severity svr, String message) {
		context.addMessage(null, new FacesMessage(svr, message, null));
	}

}