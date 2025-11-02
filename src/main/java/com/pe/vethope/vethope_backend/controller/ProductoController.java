package com.pe.vethope.vethope_backend.controller;

import com.pe.vethope.vethope_backend.dto.ProductoDTO;
import com.pe.vethope.vethope_backend.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/productos")
@RequiredArgsConstructor
@Tag(name = "Productos", description = "Operaciones relacionadas con la gestión de productos")
@PreAuthorize("hasAnyRole('ADMIN', 'RECEPCIONISTA')")
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping
    @Operation(summary = "Listar productos", description = "Obtiene la lista completa de productos registrados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de productos obtenida correctamente")
    })
    public ResponseEntity<List<ProductoDTO>> listar() {
        return ResponseEntity.ok(productoService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar producto por ID", description = "Obtiene la información de un producto según su identificador único")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto encontrado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ResponseEntity<ProductoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.buscarPorId(id));
    }

    @PostMapping
    @Operation(
            summary = "Crear un nuevo producto",
            description = "Permite registrar un nuevo producto en el sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error en los datos enviados")
    })
    public ResponseEntity<ProductoDTO> crear(@RequestBody ProductoDTO productoDTO) {
        ProductoDTO nuevoProducto = productoService.crear(productoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar producto existente",
            description = "Permite modificar los datos de un producto ya existente según su ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado"),
            @ApiResponse(responseCode = "400", description = "Error en los datos enviados")
    })
    public ResponseEntity<ProductoDTO> actualizar(@PathVariable Long id, @RequestBody   ProductoDTO productoDTO) {
        return ResponseEntity.ok(productoService.actualizar(id, productoDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar producto",
            description = "Elimina un producto específico del sistema mediante su ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Producto eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
