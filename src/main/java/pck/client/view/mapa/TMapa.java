package pck.client.view.mapa;

import com.google.gwt.core.client.Callback;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.ScriptElement;

public class TMapa {

	//private Element contenedorMap;
	
	public interface MapsHandler {
		  void mapsInitialized();
		}
	public interface MapsDragStartHandler {
		  void mapsDragStart();
		}
	public interface MapsIdleHandler {
		  void mapsIdle();
		}
	public interface MapsTilesLoadedHandler {
		  void mapsTilesLoaded();
		}
	private static TMapa instance=null;
 
	public interface MapsEventsHandler extends MapsDragStartHandler,MapsIdleHandler,MapsTilesLoadedHandler {
		
		
	}
	
	private TMapa(){
		
	}///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//public SimplePanel getContenedor() {
	//	return contenedorMap;
	//}///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//public static TMapa getInstance(final Element contenedor){
		
		//return getInstance(contenedor,-18.0065,-70.2462);
	//}///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static TMapa getInstance(final Element contenedor,final double latitud,final double longitud,final MapsEventsHandler handler){
		if(instance==null){
			instance=new TMapa();
			instance.loadMaps();
			instance.createJsniCallbackFunction(new MapsHandler() {
				
				@Override
				public void mapsInitialized() {
					// TODO Auto-generated method stub
					instance.drawMap(contenedor,latitud,longitud);
					instance.addDragStart(handler);
					instance.addIdle(handler);
					instance.addTilesLoaded(handler);
				}
			});
		}
		else{
			instance.drawMap(contenedor,latitud,longitud);
			instance.addDragStart(handler);
			instance.addIdle(handler);
			instance.addTilesLoaded(handler);
		}
		return instance;
	}///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private boolean loadMaps() {
		boolean estado=false;
		
		Document doc = Document.get();
		if(doc.getBody().getAttribute("cargado")==""){
		
		    String key = "AIzaSyCaZ-kIbEOu5dfOtBlaiPg1_y-DEZb3_XE";
		    ScriptElement script = doc.createScriptElement();
		    script.setSrc("https://maps.googleapis.com/maps/api/js?v=3.exp&callback=mapsInitialized&key=" + key);
		    script.setType("text/javascript");
		    doc.getBody().appendChild(script);
		    doc.getBody().setAttribute("cargado", "1");
		    estado=true;
		}
		return estado;
	}///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 private native void createJsniCallbackFunction(MapsHandler handler) /*-{
	    $wnd.mapsInitialized = function mapsCallback() {
	      // TODO if copying, change the package naming to this class accordingly!
	      $entry(@pck.client.view.mapa.TMapa::mapsInitialized(*)(handler));
	    }
	  }-*/;////////////////////////////////////////////////////////////////////////////////////////////////////////
	  public static void mapsInitialized(MapsHandler handler) {
	    handler.mapsInitialized();
	  }///////////////////////////////////////////////////////////////////////////////////////////////////////////
	  public static void mapsDragStart(MapsDragStartHandler handler) {
		    handler.mapsDragStart();
	  }///////////////////////////////////////////////////////////////////////////////////////////////////////////  
	  public static void mapsIdle(MapsIdleHandler handler) {
		    handler.mapsIdle();
	  }///////////////////////////////////////////////////////////////////////////////////////////////////////////  
	  public static void mapsTilesLoaded(MapsTilesLoadedHandler handler) {
		    handler.mapsTilesLoaded();
	  }///////////////////////////////////////////////////////////////////////////////////////////////////////////  
	  public native void drawMap(Element element,double latitud,double longitud) /*-{
	  	
	    var myLatlng = new $wnd.google.maps.LatLng(latitud,longitud);
	    
		var mapProp = {
			center:myLatlng,
			zoom:16,
			zoomControl: true,
			zoomControlOptions: {
     		//style: google.maps.ZoomControlStyle.LARGE,
     		position: $wnd.google.maps.ControlPosition.TOP_LEFT
 		},
			streetViewControl:false,
			mapTypeId:$wnd.google.maps.MapTypeId.ROADMAP
		};
	    var map = new $wnd.google.maps.Map(element, mapProp);
	    
	    $wnd.GlobalMap=map;
	    $wnd.jQuery('<div/>').addClass('centerMarker').appendTo(map.getDiv());//Marker al centro
	    $wnd.jQuery(".centerMarker").hide();
	    
	  		$wnd.google.maps.event.addListener(map, 'tilesloaded', function() {
  				$wnd.jQuery(".centerMarker").show();
  			});
	  }-*/;////////////////////////////////////////////////////////////////////////////////////////////////////////
	  public native double getPositionLatitud()/*-{
		return $wnd.GlobalMap.getCenter().lat();
		
		}-*/;/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
		public native double getPositionLongitud()/*-{
			return $wnd.GlobalMap.getCenter().lng();
		
		}-*/;/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	  public native void moveToLocation(double latitud,double longitud) /*-{
	  	
	  	var center = new $wnd.google.maps.LatLng(latitud, longitud);
 		$wnd.GlobalMap.panTo(center);
	    
	  }-*/;////////////////////////////////////////////////////////////////////////////////////////////////////////
	  public native void addDragStart(MapsDragStartHandler handler) /*-{
	  	var map=$wnd.GlobalMap;
	  	$wnd.google.maps.event.addListener(map, 'dragstart', function() {
    		$entry(@pck.client.view.mapa.TMapa::mapsDragStart(*)(handler));
    	});
    	
	  }-*/;////////////////////////////////////////////////////////////////////////////////////////////////////////
	  public native void addIdle(MapsIdleHandler handler) /*-{
	  	var map=$wnd.GlobalMap;
	  		$wnd.google.maps.event.addListener(map, 'idle', function() {
  			$entry(@pck.client.view.mapa.TMapa::mapsIdle(*)(handler));
  		});
  	
	  }-*/;////////////////////////////////////////////////////////////////////////////////////////////////////////
	  public native void addTilesLoaded(MapsTilesLoadedHandler handler) /*-{
	  	var map=$wnd.GlobalMap;
	  		$wnd.google.maps.event.addListener(map, 'tilesloaded', function() {
			$entry(@pck.client.view.mapa.TMapa::mapsTilesLoaded(*)(handler));
		});
	
	  }-*/;////////////////////////////////////////////////////////////////////////////////////////////////////////
	  public native void obtenerGeoCode(Callback<String,Void> callback) /*-{
	  	var map=$wnd.GlobalMap;
	  	var geocoder = new $wnd.google.maps.Geocoder();
    	geocoder.geocode({ 'latLng': map.getCenter() },function(results, status){
    	
    		if (status == $wnd.google.maps.GeocoderStatus.OK) {
				if (results[0]) {
						
						var dir="";
						if(results[0].address_components[0].types[0]=="route"){
						  dir=results[0].address_components[0].long_name;
						}
						if(results[0].address_components[1].types[0]=="route"){
						  dir=results[0].address_components[1].long_name;
						}
						callback.@com.google.gwt.core.client.Callback::onSuccess(*)(dir);
				} else {
						callback.@com.google.gwt.core.client.Callback::onFailure(*)(null);	
		
				}
			} else {
				callback.@com.google.gwt.core.client.Callback::onFailure(*)(null);
			    
			}
    	
    	});
	  	
	  }-*/;////////////////////////////////////////////////////////////////////////////////////////////////////////
	  
}
