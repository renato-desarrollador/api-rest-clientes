package com.std.ec.model.dao;

import com.std.ec.model.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

/*
 Esta interfaz representa un DAO (Data Access Object) para la entidad Cliente
 en la aplicación Spring Boot.
 Extiende CrudRepository, proporcionando métodos CRUD (Create, Read, Update, Delete)
 genéricos para interactuar con la base de datos.

 @param <Cliente> El tipo de entidad Cliente.
 @param <Integer> El tipo del identificador único de Cliente.
 */
public interface ClienteDao extends CrudRepository<Cliente,Integer> {

}
