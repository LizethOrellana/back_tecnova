package com.apiweb.pagina.DTOs;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class CarritoDto {
    public Long usuarioSecuencial;
    public LocalDateTime fechaCreacion;
    public List<ProductoItem> productos;

    public static class ProductoItem {
        public Long productoId;
        public Integer cantidad;
        public BigDecimal precio;
    }
}
