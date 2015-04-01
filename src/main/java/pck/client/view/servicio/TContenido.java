package pck.client.view.servicio;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

import static com.google.gwt.query.client.GQuery.$;

public class TContenido{

	private static TContenidoUiBinder uiBinder = GWT
			.create(TContenidoUiBinder.class);

	interface TContenidoUiBinder extends UiBinder<Widget, TContenido> {
	}
	@UiField
	SimplePanel sp;
	
	public HTMLPanel parent=new HTMLPanel("");
	
	public TContenido() {
		//initWidget(uiBinder.createAndBindUi(this));
		
		parent.addAttachHandler(new AttachEvent.Handler() {
			
			@Override
			public void onAttachOrDetach(AttachEvent event) {
				// TODO Auto-generated method stub
				if(event.isAttached()){
								Inicializar();
				}
			}
		});
		
		parent.add(uiBinder.createAndBindUi(this));
		
		ScrollPanel ssp=new ScrollPanel();
		FlowPanel fp=new FlowPanel();
				
		for(int i=0;i<50;i++){
			
			fp.add(new Label("adadasdadasadasd"+i));
		}
		ssp.add(fp);
		fp.setHeight("100px");
		sp.add(ssp);
		
	}
	
	public native void Inicializar()/*-{
	
	 	$wnd.jQuery('.collapsible').collapsible({accordion : true});
	 
	}-*/;/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	


}
