package com.std.ec.controller;

import com.std.ec.model.dto.ClienteDto;
import com.std.ec.model.entity.Cliente;
import com.std.ec.model.payload.MensajeResponse;
import com.std.ec.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController//Inica que esto es un controlador.
@RequestMapping("/api/v1")
public class ClienteController {
    @Autowired//Inyección de Dependencies
    private IClienteService clienteService;

    //Creamos los métodos
    @GetMapping("clientes")//Listar - Búsqueda
    public ResponseEntity<?> showAll (){
        //Una llamada a un servicio que devuelve una lista de objetos Cliente.
        List<Cliente> getList = clienteService.listAlll();

        //Validación: Sentencia if else
        if(getList == null){
            //Error 400 .NOT_FOUND No encuentra el registro en la BBDD
            //Error 500 .INTERNAL_SERVER_ERROR Indica que el servidor encontró una condición inesperada que le impidió cumplir con la solicitud
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("No hay registros")
                            .object(null)
                            .build()
                    , HttpStatus.OK);//Me devuelto el mensaje que la lista esta vacia por eso el 200 OK
        }

        return new ResponseEntity<>(
                MensajeResponse.builder()
                        .mensaje("Consulta Exitosa!!")
                        .object(getList)//Me envia el getList en caso si exista una información
                        .build()
                , HttpStatus.OK);
    }


    @PostMapping("cliente")//@PostMapping: Mapeo e Indico que es un método de tipo Post
    /*@ResponseStatus: Personaliza las respuestas HTTP para excepciones específicas,
    controlando errores con códigos de estado y mensajes personalizados.*/
    @ResponseStatus(HttpStatus.CREATED)//Código de estado 201 (CREATED)
    /*@RequestBody: Para vincular automáticamente un parámetro del controlador al body de una solicitud HTTP,
    convirtiendo los datos del body en el tipo de objeto especificado.*/
    public ResponseEntity<?> create (@RequestBody ClienteDto clienteDto){
        //Como valor nulo
        Cliente clienteSave = null;

        //try catch
        try{
            clienteSave = clienteService.save(clienteDto);

            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Guardado Correctamente")
                    .object(ClienteDto.builder()
                            .idCliente(clienteSave.getIdCliente()) //Establece el ID del cliente guardado
                            .nombre(clienteSave.getNombre()) //Establece el nombre del cliente guardado
                            .apellido(clienteSave.getApellido()) //Establece el apellido del cliente guardado
                            .correo(clienteSave.getCorreo()) //Establece el correo del cliente guardado
                            .fechaRegistro(clienteSave.getFechaRegistro()) //Establece la fecha de registro del cliente guardado
                            .build())//Construye y devuelve el nuevo objeto Cliente
                    .build()
                    ,HttpStatus.CREATED);
        }catch (DataAccessException exDt){
            //Error 400 .NOT_FOUND No encuentra el registro en la BBDD
            //Error 500 .INTERNAL_SERVER_ERROR Indica que el servidor encontró una condición inesperada que le impidió cumplir con la solicitud
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);//Error el servidor no esta funcionado
        }

    }

    @PutMapping("cliente/{id}")//Actualizar - Modificar
    public ResponseEntity<?> update (@RequestBody ClienteDto clienteDto, @PathVariable Integer id){
        //Como valor nulo
        Cliente clienteUpdate = null;

        //try catch
        try{
            //Sentencia if else
            if(clienteService.existsById(id)){
                clienteDto.setIdCliente(id);
                clienteUpdate = clienteService.save(clienteDto);

                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("Guardado Correctamente")
                        .object(ClienteDto.builder()
                                .idCliente(clienteUpdate.getIdCliente()) //Establece el ID del cliente guardado
                                .nombre(clienteUpdate.getNombre()) //Establece el nombre del cliente guardado
                                .apellido(clienteUpdate.getApellido()) //Establece el apellido del cliente guardado
                                .correo(clienteUpdate.getCorreo()) //Establece el correo del cliente guardado
                                .fechaRegistro(clienteUpdate.getFechaRegistro()) //Establece la fecha de registro del cliente guardado
                                .build())//Construye y devuelve el nuevo objeto Cliente
                        .build()
                        ,HttpStatus.CREATED);
            }else{
                //Error 400 .NOT_FOUND No encuentra el registro en la BBDD
                //Error 500 .INTERNAL_SERVER_ERROR Indica que el servidor encontró una condición inesperada que le impidió cumplir con la solicitud
                return new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mensaje("El registro que intenta actualizar no se encuentra en la Base de Datos.")
                                .object(null)
                                .build()
                        , HttpStatus.NOT_FOUND);//Error el servidor no esta funcionado
            }

        }catch (DataAccessException exDt){
            //Error 400 .NOT_FOUND No encuentra el registro en la BBDD
            //Error 500 .INTERNAL_SERVER_ERROR Indica que el servidor encontró una condición inesperada que le impidió cumplir con la solicitud
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);//Error el servidor no esta funcionado
        }
    }

    @DeleteMapping("cliente/{id}")//Eliminar
    /*@PathVariable: Vincula variables de la URL a parámetros de métodos en un controlador.*/
    public ResponseEntity<?> delete (@PathVariable Integer id){
        //try catch
        try{
            Cliente clienteDelete = clienteService.findById(id);
            clienteService.delete(clienteDelete);
            return new ResponseEntity<>(clienteDelete, HttpStatus.NO_CONTENT);
        }catch (DataAccessException exDt) {
            //Error 400 .NOT_FOUND No encuentra el registro en la BBDD
            //Error 500 .INTERNAL_SERVER_ERROR Indica que el servidor encontró una condición inesperada que le impidió cumplir con la solicitud
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);//Error el servidor no esta funcionado
        }
    }

    @GetMapping("cliente/{id}")//Listar - Búsqueda
    public ResponseEntity<?> showById (@PathVariable Integer id){
        //Busca un cliente por su ID utilizando el servicio
        Cliente cliente = clienteService.findById(id);

        //Sentencia if else
        if(cliente == null){
            //Error 400 .NOT_FOUND No encuentra el registro en la BBDD
            //Error 500 .INTERNAL_SERVER_ERROR Indica que el servidor encontró una condición inesperada que le impidió cumplir con la solicitud
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("El registro que intenta buscar, no existe!!")
                            .object(null)
                            .build()
                    , HttpStatus.NOT_FOUND);
        }
        
        //Error 400 .NOT_FOUND No encuentra el registro en la BBDD
        //Error 500 .INTERNAL_SERVER_ERROR Indica que el servidor encontró una condición inesperada que le impidió cumplir con la solicitud
        return new ResponseEntity<>(
                MensajeResponse.builder()
                        .mensaje("Consulta Exitosa!!")
                        .object(ClienteDto.builder()
                                .idCliente(cliente.getIdCliente()) //Establece el ID del cliente encontrado
                                .nombre(cliente.getNombre()) //Establece el nombre del cliente encontrado
                                .apellido(cliente.getApellido()) //Establece el apellido del cliente encontrado
                                .correo(cliente.getCorreo()) //Establece el correo del cliente encontrado
                                .fechaRegistro(cliente.getFechaRegistro()) //Establece la fecha de registro del cliente encontrado
                                .build())//Construye y devuelve el objeto ClienteDto
                        .build()
                , HttpStatus.OK);
    }
}
