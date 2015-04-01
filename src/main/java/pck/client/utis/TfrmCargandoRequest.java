package pck.client.utis;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;

public class TfrmCargandoRequest {

	public PopupPanel contenedor=new PopupPanel();
	
	public TfrmCargandoRequest() {
		
		contenedor.getElement().getStyle().setZIndex(10000);
		//contenedor.setStyleName("gwtPopupPanelModificado");
		
		int w=Window.getClientWidth();
		
		int y=Window.getScrollTop()+95;
		contenedor.setPopupPosition((w-133)/2, y);
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		contenedor.setWidget(absolutePanel);
		
		absolutePanel.setSize("133px", "37px");
		
		HTML htmlCargando = new HTML("Procesando...", true);
		absolutePanel.add(htmlCargando, 49, 10);
		
		Image image = new Image("load.gif");
		absolutePanel.add(image,13, 3);
		image.setSize("30px", "30px");
		
	}///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void Show(){
		contenedor.show();
		
	}////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void Hide(){
		
		contenedor.hide();
	}////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
}//end class
