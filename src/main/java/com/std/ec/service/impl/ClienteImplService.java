package com.std.ec.service.impl;

import com.std.ec.model.dao.ClienteDao;
import com.std.ec.model.dto.ClienteDto;
import com.std.ec.model.entity.Cliente;
import com.std.ec.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*@Service: Se usa para construir una clase Servicio que habitualmente
se conecta a varios repositorios y agrupa su funcionalidad.*/
@Service
public class ClienteImplService implements IClienteService {
    /*Aquí es donde voy a hacer el match con lo que yo tengo en mi Dao
    para que la información que yo reciba se procese y se envíe de aquí hacia Dao.*/

    @Autowired//Inyección de Dependencias
    private ClienteDao clienteDao;

    @Override
    public List<Cliente> listAlll() {
        return (List) clienteDao.findAll();
    }

    @Transactional//Indica que este método (o clase) está involucrado en una transacción de BBDD.
    @Override
    public Cliente save(ClienteDto clienteDto) {
        //Creación de un objeto Cliente utilizando el patrón Builder en Java
        Cliente cliente = Cliente.builder()
                .idCliente(clienteDto.getIdCliente()) //Establece el ID del cliente
                .nombre(clienteDto.getNombre()) //Establece el nombre del cliente
                .apellido(clienteDto.getApellido()) //Establece el apellido del cliente
                .correo(clienteDto.getCorreo()) //Establece el correo del cliente
                .fechaRegistro(clienteDto.getFechaRegistro()) //Establece la fecha de registro del cliente
                .build(); //Construye y devuelve el objeto Cliente completado
        return clienteDao.save(cliente);
    }

    @Transactional(readOnly = true)//Transacción para operaciones de solo lectura en la BBDD.
    @Override
    public Cliente findById(Integer id) {
        //.orElse(null) → En caso no se encuentre el dato que sea null
        return clienteDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Cliente cliente) {
        clienteDao.delete(cliente);
    }

    @Override
    public boolean existsById(Integer id) {
        return clienteDao.existsById(id);
    }
}
