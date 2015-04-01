package pck.client.utis;

public class TGlobalUsuario {
	
	private static String nombres;
	private static String apellidos;
	private static String telefono;
	private static String codPhone;
	
	
	public static String getNombres() {
		return nombres;
	}
	public static void setNombres(String nombres) {
		TGlobalUsuario.nombres = nombres;
	}
	public static String getApellidos() {
		return apellidos;
	}
	public static void setApellidos(String apellidos) {
		TGlobalUsuario.apellidos = apellidos;
	}
	public static String getTelefono() {
		return telefono;
	}
	public static void setTelefono(String telefono) {
		TGlobalUsuario.telefono = telefono;
	}
	public static String getCodPhone() {
		return codPhone;
	}
	public static void setCodPhone(String codPhone) {
		TGlobalUsuario.codPhone = codPhone;
	}
	
}
