package com.soporte.controller;

import com.soporte.clientecorreo.ClientCorreo;
import com.soporte.dto.TicketDto;
import com.soporte.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ticket")
public class TicketController {

    private static final Logger LOGGER=Logger.getLogger(TicketController.class.getName());
    private final TicketService service;
    private final ClientCorreo clientCorreo;


    @PostMapping(path ="save",consumes = "application/json;charset=UTF-8",produces = "application/json;charset=UTF-8")
    public ResponseEntity<TicketDto> save(@RequestBody TicketDto dto)throws Exception{
        String session= UUID.randomUUID().toString();
        LOGGER.log(Level.INFO,"[{0}] request:{1}",new Object[]{session,dto});
        dto=service.create(session,dto);
        LOGGER.log(Level.INFO,"[{0}] response:{1}",new Object[]{session,dto});
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping(consumes = "application/json;charset=UTF-8",produces = "application/json;charset=UTF-8")
    public ResponseEntity<TicketDto> update(@RequestBody TicketDto dto)throws Exception{
        String session= UUID.randomUUID().toString();
        LOGGER.log(Level.INFO,"[{0}] update:{1}",new Object[]{session,dto});
        dto=service.update(session,dto);
        LOGGER.log(Level.INFO,"[{0}] update:{1}",new Object[]{session,dto});
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json;charset=UTF-8",produces = "application/json;charset=UTF-8")
    public ResponseEntity<TicketDto> findById(@RequestBody  Integer id)throws Exception{
        String session= UUID.randomUUID().toString();
        LOGGER.log(Level.INFO,"[{0}] findById:{1}",new Object[]{session,id});
        TicketDto dto=service.readById(session,id);
        LOGGER.log(Level.INFO,"[{0}] findById:{1}",new Object[]{session,dto});
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(path ="all",produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<TicketDto>> all(){
        String session= UUID.randomUUID().toString();
        LOGGER.log(Level.INFO,"[{0}]save all:{1}",new Object[]{session});
        List<TicketDto> dtoList=service.all(session);
        LOGGER.log(Level.INFO,"[{0}]all:{1}",new Object[]{session,dtoList});
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }
    @GetMapping(path ="test",produces = "application/json;charset=UTF-8")
    public ResponseEntity<TicketDto> test(){
        String session= UUID.randomUUID().toString();
        LOGGER.log(Level.INFO,"[{0}]save all:{1}",new Object[]{session});
        TicketDto dto=clientCorreo.testTicket();
        LOGGER.log(Level.INFO,"[{0}]all:{1}",new Object[]{session,dto});
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


}
