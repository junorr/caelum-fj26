package br.com.caelum.notasfiscais.listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;

import br.com.caelum.notasfiscais.bean.UsuarioLogado;
import br.com.caelum.notasfiscais.bean.AppPage;

public class LoginListener implements PhaseListener {

	private static final long serialVersionUID = -4127548010174187980L;
	
	private static final String VIEW_ID_LOGIN = "/login.xhtml";
	
	private static final String VIEW_ID_INDEX = "/index.xhtml";
	
	@Inject private UsuarioLogado logged;

	public void beforePhase(PhaseEvent ev) {
		//System.out.println("** Depois da fase: "+ ev.getPhaseId());
	}
	
	public void afterPhase(PhaseEvent ev) {
		FacesContext cxt = ev.getFacesContext();
		String view = cxt.getViewRoot().getViewId();
		if(view.startsWith(VIEW_ID_LOGIN)
				|| view.startsWith(VIEW_ID_INDEX)) {
			return;
		}
		if(!logged.isLogged()) {
			NavigationHandler handler = cxt.getApplication().getNavigationHandler();
			handler.handleNavigation(cxt, null, AppPage.LOGIN.toString());
			cxt.renderResponse();
		}
	}
	
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}
	
}
