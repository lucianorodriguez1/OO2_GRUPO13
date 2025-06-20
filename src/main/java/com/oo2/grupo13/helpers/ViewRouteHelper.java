package com.oo2.grupo13.helpers;

public class ViewRouteHelper {
    // TAREA
    public static final String TAREA_LISTA = "tareas/lista";
    public static final String TAREA_NUEVA = "tareas/nueva";
    public static final String TAREA_CREAR = "tareas/crear";
    public static final String TAREA_EDITAR = "tareas/editar";
    public static final String TAREA_ELIMINAR = "tareas/eliminar";  
    public static final String TAREA_TICKET = "tareas/verTareasTicket/";

    //VALORACION 
    public static final String VALORACION_NUEVA = "valoraciones/nueva";
    public static final String VALORACION_VER = "valoraciones/verValoracion";
    public static final String VALORACION_ELIMINAR = "valoraciones/eliminar";
    
    //Redirect 
    public static final String TAREA_REDIRECT_LISTA = "/tareas/lista";

    
    
    //TICKETS
    public static final String INICIO_CLIENTE = "ticket/verTicketsCliente";
    public static final String INICIO_SOPORTE = "ticket/verTicketsSoporte";
    public static final String VER_TICKETS_CLIENTE = "verTicketsCliente";
	public static final String VER_TICKETS_SOPORTE = "verTicketsSoporte";
	public static final String LISTA_TICKETS = "ticket/tickets";
	// USUARIO
	public final static String USUARIO_INDEX = "usuario/index";
	
	// CLIENTE
	public final static String CLIENTE_CREAR_FORM = "cliente/crear-form";
	public final static String CLIENTE_EDITAR_FORM = "cliente/editar-form";
	public static final String VER_CLIENTE = "cliente/cliente"; 
	
	// SOPORTE 
	public final static String SOPORTE_EDITAR_FORM = "soporte/editar-form";
	public static final String VER_SOPORTE = "soporte/soporte"; 
	
	// ERRORES
	public final static String EMAIL_EXISTE_ERROR = "error/email-ya-existe";
    public final static String TAREA_NO_ENCONTRADA_ERROR ="error/errorTarea";
    public final static String VALORACION_INCORRECTA_ERROR ="error/errorValoracion";

}
