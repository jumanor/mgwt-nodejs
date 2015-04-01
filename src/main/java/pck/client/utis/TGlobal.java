package pck.client.utis;


public class TGlobal {

	private static int AltoPantalla;
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static int getAltoPantalla() {
		return AltoPantalla;
	}

	public static void setAltoPantalla(int altoPantalla) {
		AltoPantalla = altoPantalla;
	}
	public static String getRutaServerWebSocket() {
	
		return "http://localhost:9093";
	}
}	
