package pck.client.view.solicitud;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class TFormSolicitud extends Composite{

	private static TFormSolicitudUiBinder uiBinder = GWT
			.create(TFormSolicitudUiBinder.class);

	interface TFormSolicitudUiBinder extends UiBinder<Widget, TFormSolicitud> {
		
	}
	@UiField
	HTMLPanel txtFavorito;
	
	@UiField
	TextBox gwtDireccion;
	
	@UiField
	TextBox gwtTelefono;
	
	@UiField
	TextBox gwtNumero;
	
	@UiField
	TextBox gwtReferencia;
	
	public TFormSolicitud() {
		initWidget(uiBinder.createAndBindUi(this));
		//contenedor.add(uiBinder.createAndBindUi(this));
		
		this.addAttachHandler(new AttachEvent.Handler() {
			
			@Override
			public void onAttachOrDetach(AttachEvent event) {
				// TODO Auto-generated method stub
				if(event.isAttached()){
					InicializarSelect();
					//Window.alert("");
					
				}
			}
		});
		
		txtFavorito.setVisible(false);
		
		gwtDireccion.getElement().setId("first_name"); //x compatibilidad con materialize css
		gwtNumero.getElement().setId("last_name");
		gwtReferencia.getElement().setId("referencia");
		
		
		gwtTelefono.getElement().setId("telRef");
		gwtTelefono.getElement().setAttribute("type", "number");
		
	}
	public void setVisibleTxtFavorito(boolean estado){
		txtFavorito.setVisible(estado);
	}
	private native void InicializarSelect()/*-{
	
		var tmp=this;
		//$wnd.jQuery('select').material_select();
		
		$wnd.jQuery('#checkFavorito').click(function () {
    			//$("#txtAge").toggle(this.checked);  
  			    //alert("");
  			    if(this.checked){
  			    	tmp.@pck.client.view.solicitud.TFormSolicitud::setVisibleTxtFavorito(*)(true);
  			    	//$wnd.jQuery("#favorito").val("unjo");
  			    	//$wnd.jQuery("#favorito").val("1");
	  				//$wnd.jQuery("#favorito").focus();
	  				//$wnd.jQuery("#favorito").val("1");
	  				
  			    }
  			    else{
  			    	tmp.@pck.client.view.solicitud.TFormSolicitud::setVisibleTxtFavorito(*)(false);
  			    }
		});
		
		//$wnd.jQuery("#opcionesTelefonoCss").change(function() {
  			
  		//	$wnd.jQuery("#opcionesTelefono .select-wrapper input.select-dropdown").css("border-bottom", "2px solid #4caf50");
		//});

	}-*/;/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	

	//public Widget getWidget(){
		
	//	return contenedor;
	//}
}
