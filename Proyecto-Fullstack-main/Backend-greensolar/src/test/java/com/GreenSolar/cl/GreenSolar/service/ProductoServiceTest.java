package com.GreenSolar.cl.GreenSolar.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.GreenSolar.cl.GreenSolar.model.Producto;
import com.GreenSolar.cl.GreenSolar.repository.ProductoRepository;

class ProductoServiceTest {

    private ProductoRepository productoRepository;
    private ProductoService productoService;

    @BeforeEach
    void setUp() {
        productoRepository = Mockito.mock(ProductoRepository.class);
        productoService = new ProductoServiceImpl(productoRepository);
    }

    @Test
    void testSaveProducto() {
        Producto producto = new Producto();
        producto.setNombre("Panel Solar");
        when(productoRepository.save(producto)).thenReturn(producto);

        Producto resultado = productoService.saveProducto(producto);

        assertNotNull(resultado);
        assertEquals("Panel Solar", resultado.getNombre());
        verify(productoRepository, times(1)).save(producto);
    }

    @Test
    void testGetProductoById() {
        Producto producto = new Producto();
        producto.setId(1L);
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        Optional<Producto> resultado = productoService.getProductoById(1L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getId());
        verify(productoRepository, times(1)).findById(1L);
    }

    @Test
    void testGetAllProductos() {
        Producto p1 = new Producto();
        Producto p2 = new Producto();
        when(productoRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<Producto> productos = productoService.getAllProductos();

        assertEquals(2, productos.size());
        verify(productoRepository, times(1)).findAll();
    }

    @Test
    void testDeleteProducto() {
        Producto producto = new Producto();
        producto.setId(1L);
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        boolean eliminado = productoService.deleteProducto(1L);

        assertTrue(eliminado);
        verify(productoRepository, times(1)).delete(producto);
    }

    @Test
    void testUpdateProducto() {
        Producto existente = new Producto();
        existente.setId(1L);
        existente.setNombre("Panel Original");
        existente.setPrecio(100.0);

        Producto actualizado = new Producto();
        actualizado.setNombre("Panel Actualizado");
        actualizado.setPrecio(150.0);

        when(productoRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(productoRepository.save(any(Producto.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Optional<Producto> resultado = productoService.updateProducto(1L, actualizado);

        assertTrue(resultado.isPresent());
        assertEquals("Panel Actualizado", resultado.get().getNombre());
        assertEquals(150.0, resultado.get().getPrecio());
        verify(productoRepository, times(1)).findById(1L);
        verify(productoRepository, times(1)).save(any(Producto.class));
    }
}
