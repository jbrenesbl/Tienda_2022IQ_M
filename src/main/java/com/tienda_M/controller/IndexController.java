package com.tienda_M.controller;

import com.tienda_M.domain.Cliente;
import com.tienda_M.service.ClienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Asus VivoBook
 */
@Controller
@Slf4j
public class IndexController {
    
    @Autowired
    private ClienteService clienteService;
    
    @GetMapping("/")
    public String inicio(Model model) {
        
        log.info("Ahora utilizando la arquitectura MVC");
        
        //Mensaje
        model.addAttribute("Mensaje", "Mensaje desde el controllador");
        model.addAttribute("Nombre", "Jonathan Brenes");
        
        Cliente cliente = new Cliente("Jonathan", "Brenes Blanco", "jbrenesbl@gmail.com", "8820-2655"); 
        model.addAttribute(cliente);
        
        Cliente cliente2 = new Cliente("Juan", "Bogantes Prendas", "jbogantes@gmail.com", "8844-7799");
        var clientes = Arrays.asList(cliente, cliente2);
        model.addAttribute("clientes", clientes);
        
        /* Lista de cliente de base datos*/
        var clientesDB = clienteService.getClientes();
        model.addAttribute("clientesDB", clientesDB); 
        
        return "index";
    }
    
    @GetMapping("/nuevoCliente")
    public String nuevoCliente(Cliente cliente) {
        return "modificarCliente";
    }
    
    @PostMapping("/guardarCliente")
    public String guardarCliente(Cliente cliente) {
        clienteService.save(cliente);
        return "redirect:/";
    }
    
    @GetMapping("/modificarCliente/{idcliente}")
    public String modificarCliente(Cliente cliente, Model model) {
        Cliente respuesta = clienteService.getCliente(cliente);
        model.addAttribute("cliente", respuesta);
        return "modificarCliente";
    }
    
    @GetMapping("/eliminarCliente/{idcliente}")
    public String eliminarCliente(Cliente cliente) {
        clienteService.delete(cliente);
        return "redirect:/";
    }
    
}
