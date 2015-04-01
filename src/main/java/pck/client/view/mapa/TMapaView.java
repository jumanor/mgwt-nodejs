package pck.client.view.mapa;

import pck.client.presenter.mapa.TMapaPresenter;
import pck.client.utis.TGlobal;
import pck.client.utis.TfrmCargandoRequest;
import pck.client.view.mapa.TMapa.MapsEventsHandler;

import com.google.gwt.core.client.Callback;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.googlecode.mgwt.ui.client.widget.header.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.header.HeaderTitle;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPanel;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexSpacer;

public class TMapaView implements TMapaPresenter.Display,MapsEventsHandler{
	
	
	public interface GPSHandler {
		  void gpsInitialized(double latitud,double longitud);
		}
	
	private FlexPanel rootFlexPanel = new FlexPanel();
	
	private Image img=new Image("brujula.png");
	private Label direccionMap=new Label("");
	
	private  Button button = new Button("PEDIR TAXI");
	
	private HTML menuButton=new HTML("<i class='small mdi-action-view-headline'></i>");
	private HTML backButton=new HTML("<i class='small mdi-hardware-keyboard-arrow-left'></i>");
	
	private SimplePanel contenedorMapa=new SimplePanel();
	 	
	private double latitud=0;
	private double longitud=0;
	private String direccion;
	
	TMapa mapa=null;
	
	private boolean capturarEstados=false;
	public TMapaView(){
		
	      final HeaderPanel headerPanel = new HeaderPanel();
	 
	       HeaderTitle title = new HeaderTitle();
	       title.setText("UBICACIÓN");
	     
	       headerPanel.add(backButton);
	       headerPanel.add(new FlexSpacer());
	       headerPanel.add(title);
	       headerPanel.add(new FlexSpacer());
	       headerPanel.add(menuButton);
	       menuButton.getElement().getStyle().setMarginRight(5, Unit.PX);
	     
	       contenedorMapa.getElement().setId("mapa");
	       
	       contenedorMapa.setSize("100%", "100%");
	       
	       int AltoPantalla=TGlobal.getAltoPantalla();
	       
	       button.setImportant(true);
	       button.setWidth(Window.getClientWidth()-10+"px");
	       
	       final AbsolutePanel ap=new AbsolutePanel();
	       String tmp=ap.getElement().getAttribute("style");
	       tmp+=" border-top:1px solid #2d3642";
	       ap.getElement().setAttribute("style", tmp);
	       
	       ap.setSize("100%", AltoPantalla-(34+40)+"px");
	       ap.add(contenedorMapa,0,0);
	       ap.add(button,0,AltoPantalla-(90+45));
	       ap.add(img,Window.getClientWidth()-45,30);
	       
	       SimplePanel direccion=new SimplePanel(); 
	       direccion.setSize("100%", "40px");
	      
	       HTMLPanel html=new HTMLPanel("");
	       html.getElement().getStyle().setMarginLeft(5, Unit.PX);
	       html.add(new HTML("<b>TU DIRECCION ACTUAL ES:</b>"));
	       html.add(direccionMap);
	       
	       direccion.add(html);
	       
	       rootFlexPanel.add(headerPanel);
	       rootFlexPanel.add(direccion);
	       rootFlexPanel.add(ap);   
	       
	       img.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				final TfrmCargandoRequest cargando=new TfrmCargandoRequest();
		  		  cargando.Show();
		  		  button.setDisabled(true);
		  		  direccionMap.setText("Buscando ...");
		    	  loadGPS(new GPSHandler() {
		  	  			
		  	  			@Override
		  	  			public void gpsInitialized(double latitud, double longitud) {
		  	  				// TODO Auto-generated method stub
		  	  				cargando.Hide();
		  	  				mapa.moveToLocation(latitud,longitud);
		  	  			}
		  	  		});
			}
	       });
	       menuButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				//contenedorMapa.setVisible(true);
				//Window.alert("adadad");
			}
	       });
	       
	       img.setVisible(false);
	       //visibleMarker(false);
	       button.setDisabled(true);
	      
	}//////////////////////////////////////////////////////////////////////////////////////
	public native void loadGPS(GPSHandler handler) /*-{
    	
    	function onSuccess(position) {
      		
      		$entry(@pck.client.view.mapa.TMapaView::GPSInitialized(*)(handler,position.coords.latitude,position.coords.longitude));
      		
        	//$wnd.navigator.geolocation.clearWatch(watchID);
	
		}
		function onError(error) {
    			//alert('code: '    + error.code    + '\n' +
          		//		'message: ' + error.message + '\n');
          		alert('Fallo conexion GPS');
		}
		
    	var options = {maximumAge: 3000, timeout: 5000, enableHighAccuracy:true};
		//var watchID = $wnd.navigator.geolocation.watchPosition(onSuccess, onError, options);
    
    	$wnd.navigator.geolocation.getCurrentPosition(onSuccess,onError,options);
    
  	}-*/;////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void GPSInitialized(GPSHandler handler,double latitud,double longitud) {
	    handler.gpsInitialized(latitud, longitud);
	  }///////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return rootFlexPanel;
	}//////////////////////////////////////////////////////////////////////////////////////
	@Override
	public HasTapHandlers getAddSolicitud() {
		// TODO Auto-generated method stub
		return button;
	}
	@Override
	public HasClickHandlers getAddBackLogin() {
		// TODO Auto-generated method stub
		return backButton;
	}
	@Override
	public void onAnimationEnd() {
		// TODO Auto-generated method stub
		 
			final TMapaView tmp=this;
			direccionMap.setText("Buscando ...");
			
			if(latitud == 0 && longitud == 0){
				//Window.alert("CON GPS");	
				loadGPS(new GPSHandler() {
		  			
		  			@Override
		  			public void gpsInitialized(double latitud, double longitud) {
		  				// TODO Auto-generated method stub
		  			
		  				mapa=TMapa.getInstance(contenedorMapa.getElement(), latitud, longitud,tmp);
		  				//button.setDisabled(false);
		  			}
		  		});	
			}
			else{ //latitud, longitud y direccion enviados de otra vista
				direccionMap.setText(direccion);
				capturarEstados=true;
				mapa=TMapa.getInstance(contenedorMapa.getElement(), latitud, longitud,tmp);
  				//button.setDisabled(false);
				
			}
	}
	@Override
	public void mapsDragStart() {
		// TODO Auto-generated method stub
		direccionMap.setText("Buscando...");
		button.setDisabled(true);
	}
	@Override
	public void mapsIdle() {
		// TODO Auto-generated method stub
		if(capturarEstados==true){
			//direccionMap.setText(result);
			button.setDisabled(false);
		   capturarEstados=false;	
		}
		else{
		mapa.obtenerGeoCode(new Callback<String, Void>() {
			
			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				direccionMap.setText(result);
				button.setDisabled(false);
			}
			
			@Override
			public void onFailure(Void reason) {
				// TODO Auto-generated method stub
				direccionMap.setText("");
				button.setDisabled(false);
			}
		});
		}
	}
	@Override
	public void mapsTilesLoaded() {
		// TODO Auto-generated method stub
		//Window.alert("");
		img.setVisible(true);
		
	}
	@Override
	public String getDireccion() {
		// TODO Auto-generated method stub
		return direccionMap.getText();
	}
	@Override
	public double getLatitud() {
		// TODO Auto-generated method stub
		//latitud=mapa.getPositionLatitud();
		return mapa.getPositionLatitud(); 
	}
	@Override
	public double getLongiud() {
		// TODO Auto-generated method stub
		//longitud=mapa.getPositionLongitud();
		return mapa.getPositionLongitud();
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
	@Override
	public void setDireccion(String direccion) {
		// TODO Auto-generated method stub
		this.direccion=direccion;
		direccionMap.setText(direccion);
	}
	@Override
	public HasClickHandlers getAddServicio() {
		// TODO Auto-generated method stub
		return menuButton;
	}
	
}
