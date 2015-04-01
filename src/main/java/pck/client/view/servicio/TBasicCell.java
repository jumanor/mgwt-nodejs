/*
 * Copyright 2010 Daniel Kurka
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package pck.client.view.servicio;

import pck.client.view.servicio.TServicioView.Item;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.googlecode.mgwt.ui.client.widget.list.celllist.Cell;


/**
 * @author Daniel Kurka
 *
 */
public abstract class TBasicCell<T> implements Cell<T> {

	private static Template TEMPLATE = GWT.create(Template.class);

	public interface Template extends SafeHtmlTemplates {
		@SafeHtmlTemplates.Template("<div><div class=\"{0}\">TICKET:</div><div class=\"{1}\">{2}</div></div>"
				+ "<div><div class=\"{0}\">SERVICIO:</div><div class=\"{1}\">{3}</div></div>"
				+ "<div><div class=\"{0}\">DIRECCION:</div><div class=\"{1}\">{4}</div></div>"
				+ "<div><div class=\"{0}\">FECHA:</div><div class=\"{1}\">{5}</div></div>"
				+ "<div><div class=\"{0}\">ESTADO:</div><div class=\"{1}\">{6}</div></div>")
		SafeHtml content(String classes1,String classes2,String cellContents1,String cellContents2,String cellContent3,String cellContent4,String cellContent5);

		//@SafeHtmlTemplates.Template("JUMANOR{0}{1}{2}{3}")
		//SafeHtml content(String classes, String cellContents1,String cellContents2,String cellContent3);

	}

	@Override
	public void render(SafeHtmlBuilder safeHtmlBuilder, final T model) {
		
		
		
		String tcliente=SafeHtmlUtils.htmlEscape(((Item)model).ticketCliente);
		String tservicio=SafeHtmlUtils.htmlEscape(((Item)model).ticketServicio);
		String direccion=SafeHtmlUtils.htmlEscape(((Item)model).direccionOrigen);
		String fecha=SafeHtmlUtils.htmlEscape(((Item)model).fecha);
		String estado="PROCESANDO";
		
		safeHtmlBuilder.append(TEMPLATE.content("celdas","celdasint",tcliente,tservicio,direccion,fecha,estado));

	}
	//public abstract String getDisplayString(T model);

	@Override
	public boolean canBeSelected(T model) {
		return false;
	}

}
