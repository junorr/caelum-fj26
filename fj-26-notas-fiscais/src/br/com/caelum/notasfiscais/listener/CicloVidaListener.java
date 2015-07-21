package br.com.caelum.notasfiscais.listener;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class CicloVidaListener implements PhaseListener {

	public void afterPhase(PhaseEvent ev) {
		System.out.println("** Depois da fase: "+ ev.getPhaseId());
	}
	
	public void beforePhase(PhaseEvent ev) {
		System.out.println("** Antes da fase: "+ ev.getPhaseId());
	}
	
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}
	
}