package pck.client;

import pck.client.event.login.TAddBackLoginEvent;
import pck.client.event.login.TAddBackLoginEventHandler;
import pck.client.event.login.TAddBackMapaEvent;
import pck.client.event.login.TAddBackMapaEventHandler;
import pck.client.event.login.TAddLoginEvent;
import pck.client.event.login.TAddLoginEventHandler;
import pck.client.event.login.TAddServicioEvent;
import pck.client.event.login.TAddServicioEventHandler;
import pck.client.event.login.TAddSolicitudEvent;
import pck.client.event.login.TAddSolicitudEventHandler;
import pck.client.presenter.PresenterMGWT;
import pck.client.presenter.login.TLoginPresenter;
import pck.client.presenter.mapa.TMapaPresenter;
import pck.client.presenter.servicio.TServicioPresenter;
import pck.client.presenter.solicitud.TSolicitudPresenter;
import pck.client.view.login.TLoginView;
import pck.client.view.mapa.TMapaView;
import pck.client.view.servicio.TServicioView;
import pck.client.view.solicitud.TSolicitudView;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.googlecode.mgwt.ui.client.animation.AnimationHelper;
import com.googlecode.mgwt.ui.client.widget.animation.Animation;
import com.googlecode.mgwt.ui.client.widget.animation.Animations;

public class AppController implements PresenterMGWT, ValueChangeHandler<String>{
	
	public class CIMapView{
		public String direccion;
		public double latitud;
		public double longitud;
	}
	private CIMapView ciMapView=new CIMapView();
	
	public class CIServicioView{
		public String direccion;
		public double latitud;
		public double longitud;
	}
	private CIServicioView ciServicioView=new CIServicioView();
	
	private HandlerManager eventBus;
	private HasWidgets container;
	private AnimationHelper animationHelper;
	
	private Animation animation=Animations.SLIDE;
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public AppController(HandlerManager eventBus){

		this.eventBus = eventBus;
		bind();
	}//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void bind(){
		History.addValueChangeHandler(this);
		
		eventBus.addHandler(TAddLoginEvent.TYPE,new TAddLoginEventHandler() {
			
			@Override
			public void onAddLogin(TAddLoginEvent event) {
				// TODO Auto-generated method stub
				ciMapView.direccion="";
				ciMapView.latitud=0;
				ciMapView.longitud=0;  //siempre calculamos x GPS
				History.newItem("mapa");
			}
		});
		eventBus.addHandler(TAddSolicitudEvent.TYPE,new TAddSolicitudEventHandler() {
			
			@Override
			public void onAddSolicitud(TAddSolicitudEvent event,String direccion,double latitud,double longitud) {
				// TODO Auto-generated method stub
				
				History.newItem("solicitud",false);
				TSolicitudView view =new TSolicitudView(); 
				TSolicitudPresenter presenter=new TSolicitudPresenter(eventBus, view); 
			    presenter.setDireccion(direccion);
			    presenter.setLatitud(latitud);
			    presenter.setLongitud(longitud);
				presenter.go(animationHelper,animation,container);
				
			}
		}); 
		eventBus.addHandler(TAddBackMapaEvent.TYPE,new TAddBackMapaEventHandler() {
			
			@Override
			public void onAddBackMapa(TAddBackMapaEvent event,String direccion,double latitud,double longitud) {
				// TODO Auto-generated method stub
				ciMapView.direccion=direccion;
				ciMapView.latitud=latitud;
				ciMapView.longitud=longitud;
				animation=Animations.SLIDE_REVERSE;	
				History.back();
		
			}
		});
		eventBus.addHandler(TAddServicioEvent.TYPE,new TAddServicioEventHandler() {
			
			@Override
			public void onAddServicio(TAddServicioEvent event,String direccion,double latitud,double longitud) {
				// TODO Auto-generated method stub
		
				ciServicioView.direccion=direccion;
				ciServicioView.latitud=latitud;
				ciServicioView.longitud=longitud;
				
				History.newItem("servicio");
			}
		});
		eventBus.addHandler(TAddBackLoginEvent.TYPE,new TAddBackLoginEventHandler() {
			
			@Override
			public void onAddBackLogin(TAddBackLoginEvent event) {
				// TODO Auto-generated method stub
				animation=Animations.SLIDE_REVERSE;	
				History.back();
			}
		});
		
	}//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		// TODO Auto-generated method stub
		
		 String token = event.getValue();
		    
		    if (token != null) {
		      PresenterMGWT presenter = null;

		      if (token.equals("login")) {
		    	
		    	TLoginView view=new TLoginView();
		        presenter = new TLoginPresenter(eventBus,view);
		        
		      }
		      else if (token.equals("mapa")) {
		        
		    	TMapaView view =new TMapaView();
		    	
				presenter=new TMapaPresenter(eventBus,view,ciMapView);

		      }
		      else if (token.equals("servicio")) {
			        
			    	TServicioView view =new TServicioView();
			    	
					presenter=new TServicioPresenter(eventBus,view,ciServicioView);

			      }
		      else if (token.equals("solicitud")) {
		    	 TSolicitudView view =new TSolicitudView(); 
		    	 presenter=new TSolicitudPresenter(eventBus, view); 
		        
		      }
		      
		      if (presenter != null) {
		    	  
		    	  presenter.go(animationHelper,animation,container);
		    	  animation=Animations.SLIDE; //por defecto SLIDE para las VISTAS
		      }
		    }
		
	}

	@Override
	public void go(AnimationHelper animationHelper,Animation animation,HasWidgets container) {
		// TODO Auto-generated method stub
		this.container = container;
		this.animationHelper=animationHelper;
		
		if ("".equals(History.getToken())) {
			//Window.alert("bbb"); 
			History.newItem("login");
		    }
	    else {
		    //Window.alert("aaa");  
	    	History.fireCurrentHistoryState();//call onValueChange
	    }
	}
}
