package com.std.ec.model.payload;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
//@AllArgsConstructor//SE ELIMINO PORQUE SALE YA LO REEMPLAZA @Builder en la construccion de cosntructores
//@NoArgsConstructor//SE ELIMINO PORQUE SALE YA LO REEMPLAZA @Builder en la construccion de cosntructores
@ToString
//@Builder: Patron de diseño que nos permite enviar datos sin la necesidad de utilizar constructores.
@Builder
public class MensajeResponse implements Serializable {
    //Atributos
    private String mensaje;
    private Object object;
}
