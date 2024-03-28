package Parcial1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProyectoFinal {
	public static final byte TIPO_DOCUMENTO_IDENTIFICACION = 0;
	public static final byte DOCUMENTO_IDENTIFICACION = 1;
	public static final byte NOMBRES = 2;
	public static final byte APELLIDOS = 3;
	public static final byte CORREO_ELECTRONICO = 4;
	public static final byte DIRECCION_RESIDENCIA = 5;
	public static final byte CIUDAD_RESIDENCIA = 6;
	public static final byte TELEFONO_CONTACTO = 7;
	public static final byte CONTRASEÑA = 8;
	public static final byte CONFIRMAR_CONTRASEÑA = 9;
	public static List<String[]> usuarios = new ArrayList<> () ;
	
	public static Scanner leerDatoTeclado = new Scanner(System.in);
	
	
	public static void registrarUsuario(
	 String tipoDocumentoIdentificacion, String documentoIdentifacion, String nombres,
	 String apellidos, String correoElectronico, String direccionResidencia, String ciudadResidencia, String telefonoContacto,
	 String contraseña, String confirmarContraseña) {
		
		String [] usuario = new String [10];
		
		usuario[TIPO_DOCUMENTO_IDENTIFICACION] = tipoDocumentoIdentificacion;
		usuario[DOCUMENTO_IDENTIFICACION] = documentoIdentifacion;
		usuario[NOMBRES] = nombres;
		usuario[APELLIDOS] = apellidos;
		usuario[CORREO_ELECTRONICO] = correoElectronico;
		usuario[DIRECCION_RESIDENCIA] = direccionResidencia;
		usuario[CIUDAD_RESIDENCIA] = ciudadResidencia;
		usuario[TELEFONO_CONTACTO] = telefonoContacto;
		usuario[CONTRASEÑA] = contraseña;
		usuario[CONFIRMAR_CONTRASEÑA] = confirmarContraseña;
		
		usuarios.add(usuario);
		
		System.out.println("Registro Exitoso!");
	}
	
	public static void solicitarDatosDeRegistro () {
		String tipoIdentificacion,documentoIdentificacion,nombres,apellidos,correoElectronico; 
		String direccionResidencia,ciudadResidencia,telefonoContacto,contraseña,confirmarContraseña;
		
		leerDatoTeclado.nextLine();
		System.out.println("Solicitando Datos...");
		System.out.println("Tipo de Identificacion: ");
		tipoIdentificacion = leerDatoTeclado.nextLine();
		System.out.println("Documento de Identificacion: ");
		documentoIdentificacion = leerDatoTeclado.nextLine();
		System.out.println("Nombres: ");
		nombres = leerDatoTeclado.nextLine();
		System.out.println("Apellidos: ");
		apellidos = leerDatoTeclado.nextLine();
		System.out.println("Correo Electronico: ");
		correoElectronico = leerDatoTeclado.nextLine();
		System.out.println("Direccion de Residencia: ");
		direccionResidencia = leerDatoTeclado.nextLine();
		System.out.println("Ciudad de Residencia: ");
		ciudadResidencia = leerDatoTeclado.nextLine();
		System.out.println("Telefono de Contacto: ");
		telefonoContacto = leerDatoTeclado.nextLine();
		System.out.println("Contraseña: ");
		contraseña = leerDatoTeclado.nextLine();
		System.out.println("Confirmar Contraseña: ");
		confirmarContraseña = leerDatoTeclado.nextLine();
		
		if(confirmarContraseña.equals(contraseña) == false){
			boolean confirmar = true;
			while(confirmar) {
				System.out.println("La Contraseña no coincide, intente de nuevo : ");
				System.out.println("Contraseña : ");
				contraseña = leerDatoTeclado.nextLine();
				System.out.println("Confirmar Contraseña: ");
				confirmarContraseña = leerDatoTeclado.nextLine();
				if(confirmarContraseña.equals(contraseña)) {
					confirmar = false;
				}
			}	
		}
		
		registrarUsuario(tipoIdentificacion,documentoIdentificacion,nombres,apellidos,correoElectronico,
						direccionResidencia,ciudadResidencia,telefonoContacto,contraseña,confirmarContraseña);
	}
	
	public static int iniciarSesion (String correoElectronico, String contraseña, int intentosFallidos) {
		boolean usuarioEncontrado = false;
		
		for(String[] usuario : usuarios) {
			if(usuario[CORREO_ELECTRONICO].equals(correoElectronico) && usuario[CONTRASEÑA].equals(contraseña)) {
				System.out.println("Usuario logueado correctamente");
				usuarioEncontrado = true;
				break;
			}
			
		}
		
		if (!usuarioEncontrado) {
	        System.out.println("Usuario incorrecto, intente una vez mas.");
	        intentosFallidos++;
	    }
		
		return intentosFallidos;
	}
		
	
	
	public static void mostrarMenuLoginRegistro () {
		boolean showMenu = true;
		int intentosFallidos = 0;
		
		do {
			System.out.println("Bienvenido a MyHotel...");
			System.out.println("Mas que un lugar para descansar.");
			System.out.println("------------------------------------------------");
			System.out.println("Ingrese la opcion deseada.");
			System.out.println("1. Registrase como cliente.");
			System.out.println("2. Iniciar Sesion.");
			System.out.println("3. Salir");
			
			int opcionUsuario = leerDatoTeclado.nextInt();
			
			switch (opcionUsuario) {
			
				case 1:
					solicitarDatosDeRegistro();
					break;
					
				case 2:
					leerDatoTeclado.nextLine();
					System.out.println("Correo Electronico: ");
					String correoElectronico = leerDatoTeclado.nextLine();
					System.out.println("Contraseña: ");
					String contraseña = leerDatoTeclado.nextLine();
					intentosFallidos = iniciarSesion(correoElectronico,contraseña,intentosFallidos);
					if (intentosFallidos >= 3) {
	                    System.out.println("Demasiados intentos fallidos. Cerrando el programa.");
	                    showMenu = false;
	                }
					break;
					
				case 3:
					System.out.println("¡Hasta Pronto!");
					showMenu = false;
					break;
					
				default:
					System.out.println("Opcion Incorrecta");
					System.out.println("Digite de nuevo");
					System.out.println();
					break;
			}
			
		} while(showMenu);
		
		leerDatoTeclado.close();
	}
	

	public static void main(String[] args) {
		mostrarMenuLoginRegistro();
	}
}
