package com.oo2.grupo13.helpers;

public class ViewRouteHelper {

    //HOME
    public static final String HOME = "home/home"; 

    // TAREA
    public static final String TAREA_LISTA = "tareas/lista";
    public static final String TAREA_NUEVA = "tareas/nueva";
    public static final String TAREA_EDITAR = "tareas/editar";
    public static final String TAREA_ELIMINAR = "tareas/eliminar";  

    //VALORACION 
    public static final String VALORACION_LISTA = "valoraciones/lista";
    public static final String VALORACION_NUEVA = "valoraciones/nueva";
    public static final String VALORACION_EDITAR = "valoraciones/editar";
    public static final String VALORACION_ELIMINAR = "valoraciones/eliminar";
    
    //Redirect 
    public static final String TAREA_REDIRECT_LISTA = "redirect:/tareas/lista";
    public static final String VALORACION_REDIRECT_LISTA = "redirect:/valoraciones/lista";
    
    //TICKETS
    public static final String VER_TICKETS_CLIENTE = "verTicketsCliente";
    public static final String VER_TICKETS_SOPORTE = "verTicketsSoporte";
	
	// USUARIO
	public final static String USUARIO_INDEX = "usuario/index";
	
	// CLIENTE
	public final static String CLIENTE_CREAR_FORM = "cliente/crear-form";
	public final static String CLIENTE_EDITAR_FORM = "cliente/editar-form";
	
	// ERRORES
	public final static String EMAIL_EXISTE_ERROR = "error/email-ya-existe";
    public final static String TAREA_NO_ENCONTRADA_ERROR ="error/errorTarea";
    public final static String VALORACION_INCORRECTA_ERROR ="error/errorValoracion"; 

}
