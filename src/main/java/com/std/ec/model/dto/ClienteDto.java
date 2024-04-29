package com.std.ec.model.dto;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;


@Data
//@AllArgsConstructor//SE ELIMINO PORQUE SALE YA LO REEMPLAZA @Builder en la construccion de cosntructores
//@NoArgsConstructor//SE ELIMINO PORQUE SALE YA LO REEMPLAZA @Builder en la construccion de cosntructores
@ToString
//@Builder: Patron de diseño que nos permite enviar datos sin la necesidad de utilizar constructores.
@Builder
public class ClienteDto implements Serializable{
    //Variables
    private Integer idCliente;
    private String nombre;
    private String apellido;
    private String correo;
    private Date fechaRegistro;
}
