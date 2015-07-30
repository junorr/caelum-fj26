package br.com.caelum.notasfiscais.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.caelum.stella.format.CNPJFormatter;

@FacesConverter("cnpj")
public class CnpjConverter implements Converter {

	private CNPJFormatter fmt = new CNPJFormatter();
	
	@Override
	public Object getAsObject(FacesContext ctx, UIComponent cmp, String value) {
		return fmt.unformat(value);
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent cmp, Object obj) {
		return fmt.format(String.valueOf(obj));
	}
	
}
