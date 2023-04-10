package com.example.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.dao.PresentacionDao;
import com.example.dao.ProductoDao;
import com.example.entities.Presentacion;
import com.example.entities.Producto;

// para seguir el enfoque de BDD con Mockito
import static org.mockito.BDDMockito.given;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ProductoServiceTests {

    @Mock
    private ProductoDao productoDao;

    @Mock
    private PresentacionDao presentacionDao;

    @InjectMocks
    private ProductoServiceImpl productoService;

    private Producto producto;

    @BeforeEach
    void setUp() {
        Presentacion presentacion = Presentacion.builder()
                .descripcion("estos es")
                .nombre("blaba")
                .build();

        producto = Producto.builder()
            .id(1)
            .nombre("Google Pixel 7")
            .precio(350)
            .descripcion("Telefono de Google")
            .stock(100)
            .imagenProducto(null)
            .presentacion(presentacion)
            .build();
    }

    @Test
    @DisplayName("Test para guardar un producto")
    public void testGuardarProducto() {
        // given
        given(productoDao.save(producto)).willReturn(producto);

        // When
        Producto productoGuardado = productoService.save(producto);

        // Then
        assertThat(productoGuardado).isNotNull();

    }

    @Test
    @DisplayName("Recupera una lista vacia de productos")
    public void testEmptyProductList() {

        // Given
        given(productoDao.findAll()).willReturn(Collections.emptyList());
        
        // When
        List<Producto> productos = productoDao.findAll();

        // Them
        assertThat(productos).isEmpty();
    }
    
    
}
