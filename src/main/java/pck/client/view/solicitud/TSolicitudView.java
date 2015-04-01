package pck.client.view.solicitud;

import pck.client.dao.TSTicket;
import pck.client.ds.TRequest;
import pck.client.ds.TRequestWS;
import pck.client.ds.TSMessageError;
import pck.client.presenter.solicitud.TSolicitudPresenter;
import pck.client.utis.TDialogs;
import pck.client.utis.TDialogs.AlertCallback;
import pck.client.utis.TGlobalUsuario;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.googlecode.mgwt.ui.client.widget.dialog.overlay.PopinDialogOverlay;
import com.googlecode.mgwt.ui.client.widget.dialog.panel.DialogPanel;
import com.googlecode.mgwt.ui.client.widget.header.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.header.HeaderTitle;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FixedSpacer;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPanel;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexSpacer;
import com.googlecode.mgwt.ui.client.widget.panel.flex.RootFlexPanel;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollPanel;

public class TSolicitudView implements TSolicitudPresenter.Display{

	private RootFlexPanel rootFlexPanel = new RootFlexPanel();
	private TFormSolicitud form=new TFormSolicitud();
	//private ScrollPanel scrollPanel=new ScrollPanel();
	private Button button = new Button("CONTINUAR");
	
	private PopinDialogOverlay dialog;
	private TextBox textoDialog=new TextBox();
	
	private HTML backButton=new HTML("<i class='small mdi-hardware-keyboard-arrow-left'></i>");
	
	private class ButtonClickEvent extends ClickEvent{
        /*To call click() function for Programmatic equivalent of the user clicking the button.*/
    }
	
	private double latitud;
	private double longitud;
	
	private String direccion;
	
	public TSolicitudView(){
		
			//PreviousitemImageButton headerBackButton=new PreviousitemImageButton();
		
		   HeaderPanel headerPanel = new HeaderPanel();
		 
	       HeaderTitle title = new HeaderTitle();
	       title.setText("PEDIR TAXI");
	       
	       //Image img=new Image(ImageHolder.get().previousItem());
	       headerPanel.add(backButton);
	       headerPanel.add(new FlexSpacer());
	       headerPanel.add(title);
	       headerPanel.add(new FlexSpacer());
	       headerPanel.add(new FixedSpacer(40));
	          
	    
		button.getElement().setId("botoncito");
		button.setWidth(Window.getClientWidth()-10+"px");
		 
	    
	    //if(Window.getClientHeight()<375){
	    //		form.txtFavorito.setVisible(false);
	    //}
	    
	    //Window.alert(Window.getClientHeight()+"");
	    final ScrollPanel formScroll=new ScrollPanel();
	    formScroll.setUsePos(MGWT.getOsDetection().isAndroid2x());
	    formScroll.add(form);
	    formScroll.setSize("100%", Window.getClientHeight()-95+"px");
		//sp.setSize("100%","300px");
		
	    
		//final ScrollPanel scroll=new ScrollPanel();
		
		FlexPanel wlist=new FlexPanel();
		wlist.add(formScroll);
		wlist.add(button);

		
		rootFlexPanel.add(headerPanel);
		rootFlexPanel.add(wlist);
		button.setImportant(true);
		button.addTapHandler(new  TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
				// TODO Auto-generated method stub
			
				enviarDataServerSocket(new Callback<TSTicket, TSMessageError>() {
				
				@Override
				public void onSuccess(TSTicket result) {
					// TODO Auto-generaed method stub
					
					SafeHtmlBuilder builder = new SafeHtmlBuilder();
					
					builder.appendEscaped("Su Codigo de Operacion es:");
					builder.appendHtmlConstant("<BR>");
					builder.appendHtmlConstant("<B>");
					builder.appendEscaped("TK "+result.getOperacion());
					builder.appendHtmlConstant("</B>");
					
					TDialogs.alert("TICKET", builder.toSafeHtml(),false,new AlertCallback() {
						
						@Override
						public void onButtonPressed() {
							// TODO Auto-generated method stub
							
							backButton.fireEvent(new ButtonClickEvent());
							
						}
					});
					
				}
				
				@Override
				public void onFailure(TSMessageError reason) {
					// TODO Auto-generated method stub
					Window.alert(reason.getMessage());
				}
			});
				
			}
		});
		
		form.addAttachHandler(new AttachEvent.Handler() {
			
			@Override
			public void onAttachOrDetach(AttachEvent event) {
				// TODO Auto-generated method stub
				if(event.isAttached()){
					InicializarSelect();
					
				}
			}
		});
		
		dialog=crearDialogo();
		
	}///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public PopinDialogOverlay crearDialogo(){
		
		DialogPanel dialogPanel = new DialogPanel();
	    dialogPanel.showCancelButton(false);
	    dialogPanel.showOkButton(true);
	    final PopinDialogOverlay popinDialog = new PopinDialogOverlay();
	    popinDialog.add(dialogPanel);
	    
	    dialogPanel.getContent().add(new Label("Ingresa Nombre Favorito"));
	    //final TextBox texto=new TextBox();
	    
	    dialogPanel.getContent().add(textoDialog);
	    dialogPanel.content.getElement().getStyle().setColor("black");
	    dialogPanel.okButton.getElement().getStyle().setHeight(40, Unit.PX);
	    dialogPanel.getOkButton().addTapHandler(new TapHandler() {

		      @Override
		      public void onTap(TapEvent event) {
		        popinDialog.hide();
		        setFavorito(textoDialog.getText());
		        //button.set
		      }
		    });
		return popinDialog;
	}
	
	public void Men(){
	 
		dialog.center();
	    textoDialog.setFocus(true);
	}
	private native void setFavorito(String text)/*-{
	
	  $wnd.jQuery("#favorito").val(text);
	  $wnd.jQuery("#favorito").focus();
	  $wnd.jQuery('#favorito').blur();
	  
	}-*/;/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	

	private native void InicializarSelect()/*-{
	
	var tmp=this;
	$wnd.jQuery('#checkFavorito').click(function () {
			
			    if(this.checked){
			    	
			    	tmp.@pck.client.view.solicitud.TSolicitudView::Men()();
			    	
 				}
			    
	});
	 
	}-*/;/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	private native void dibujar()/*-{
	
    //var tmp = {"codPhone":"SP40633367","latitud":"-18.01295","longitud":"-70.25644"}
	var tmp={};
	tmp.codPhone="SP40633367";
	tmp.latitud=-18.01295;
	tmp.longitud=-70.25644;
	
	var data="data="+JSON.stringify(tmp);
	
	$wnd.jQuery.ajax({
            type : 'POST',
            url : 'http://192.168.1.33:9093/newPedidoPhone',
            crossDomain : true,
            data : data,
            dataType : 'json',
            //contentType: "application/json",
            success : function(data){
               // $('#feedbackForm').unmask();
               // $('#feedbackForm')[0].reset();
               // showNotification('Received your feedback', 'Info');
               // homeView();
               alert("ok");
            },
            error : function(XMLHttpRequest,textStatus, errorThrown) {     
              //$('#feedbackForm').unmask();
              alert("Error status :"+textStatus);  
              alert("Error type :"+errorThrown);  

            }
    });


}-*/;/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public void enviarDataServer(final Callback<TSTicket, TSMessageError> callback){
	
	JSONObject tmp=new JSONObject();
	 	
	tmp.put("codPhone", new JSONString("SP40633367"));
	tmp.put("latitud", new JSONNumber(getLatitud()));
	tmp.put("longitud", new JSONNumber(getLongitud()));
	
	String parametros=JsonUtils.stringify(tmp.getJavaScriptObject());
	//http://190.117.132.98:9093/
	//TRequest.sendRequestObject("http://192.168.1.33:9093/newPedidoPhone", parametros, new Callback<JavaScriptObject, TSMessageError>() {
	TRequest.sendRequestObject("http://190.117.132.98:9093/newPedidoPhone", parametros, new Callback<JavaScriptObject, TSMessageError>() {
				
		@Override
		public void onSuccess(JavaScriptObject result) {
			// TODO Auto-generaed method stub
			TSTicket res=result.cast();
			callback.onSuccess(res);
			//Window.alert(res.getOperacion());
		}
		
		@Override
		public void onFailure(TSMessageError reason) {
			// TODO Auto-generated method stub
			//Window.alert(reason.getMessage());
			callback.onFailure(reason);
		}
	});
	
}/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public void enviarDataServerSocket(final Callback<TSTicket, TSMessageError> callback){
	
	JSONObject tmp=new JSONObject();
 	
	//tmp.put("codPhone", new JSONString("SP40633367"));
	tmp.put("codPhone", new JSONString(TGlobalUsuario.getCodPhone()));
	tmp.put("latitud", new JSONNumber(getLatitud()));
	tmp.put("longitud", new JSONNumber(getLongitud()));
	tmp.put("direccion", new JSONString(form.gwtDireccion.getText()+", "+form.gwtNumero.getText()));
	tmp.put("referencia", new JSONString(form.gwtReferencia.getText()));
	tmp.put("telefono", new JSONString(form.gwtTelefono.getText()));
	
	String parametros=JsonUtils.stringify(tmp.getJavaScriptObject());
	
	TRequestWS.sendRequestObject("newPedidoPhone", parametros, new Callback<JavaScriptObject, TSMessageError>() {
				
		@Override
		public void onSuccess(JavaScriptObject result) {
			// TODO Auto-generaed method stub
			TSTicket res=result.cast();
			callback.onSuccess(res);
			//Window.alert(res.getOperacion());
		}
		
		@Override
		public void onFailure(TSMessageError reason) {
			// TODO Auto-generated method stub
			//Window.alert(reason.getMessage());
			callback.onFailure(reason);
		}
	});
}
	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		//FlexPanel fp=new  FlexPanel();
		//fp.add(scrollPanel);
		return rootFlexPanel;
	}///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void setDireccion(String direccion) {
		// TODO Auto-generated method stub
		this.direccion=direccion;
		
		form.gwtTelefono.setText(TGlobalUsuario.getTelefono());
		form.gwtTelefono.setFocus(true);
		
		form.gwtDireccion.setText(direccion);
		form.gwtDireccion.setFocus(true);
	}
	@Override
	public HasClickHandlers getAddBackMapa() {
		// TODO Auto-generated method stub
		return backButton;
	}

	@Override
	public void setLatitud(double latitud) {
		// TODO Auto-generated method stub
		this.latitud=latitud;
	}

	@Override
	public void setLongitud(double longitud) {
		// TODO Auto-generated method stub
		this.longitud=longitud;
	}

	public double getLatitud() {
		return latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	@Override
	public String getDireccion() {
		// TODO Auto-generated method stub
		return direccion;
	}
	
}
