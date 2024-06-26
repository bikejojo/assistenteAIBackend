package com.soporte.dto;

import lombok.Data;

@Data
public class DetalleTicketDto {
    private Integer id;
    private String asunto;
    private String descripcion;
    private String estado;
    private AgenteDto responsable;
    private TicketDto ticket;
}
