package com.std.ec.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

//Simplifica la creación de getters, setters, toString(), equals() y hashCode() en la clase.
@Data
//Genera un constructor que acepta todos los argumentos de la clase como parámetros.
@AllArgsConstructor
//Crea un constructor sin argumentos para la clase.
@NoArgsConstructor
//Genera un método toString() para la clase que muestra el estado actual de los objetos de la clase.
@ToString
@Builder
//Indica que la clase es una entidad JPA y se puede mapear a una tabla en la base de datos.
@Entity
//Especifica el nombre de la tabla en la base de datos a la que se mapeará la entidad Cliente.
@Table(name = "clientes")
public class Cliente implements Serializable{
    //Variables

    //Anota un campo como la clave primaria de la entidad.
    @Id
    //Asigna el nombre de la columna en la tabla de la base de datos para el campo "idCliente".
    @Column(name= "id_cliente")
    //Especifica que el valor del campo "idCliente" se generará automáticamente por la BBDD.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCliente;
    @Column(name= "nombre")
    private String nombre;
    @Column(name= "apellido")
    private String apellido;
    @Column(name= "correo")
    private String correo;
    @Column(name= "fecha_registro")
    private Date fechaRegistro;
}
