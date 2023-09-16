package com.utn.practico1;

import com.utn.practico1.entidades.*;
import com.utn.practico1.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class Practico1Application {

	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	RubroRepository rubroRepository;


	public static void main(String[] args) {

		SpringApplication.run(Practico1Application.class, args);
		System.out.println("Estoy funcionando");
	}

	@Bean
	CommandLineRunner init(ClienteRepository clienteRepository, DomicilioRepository domicilioRepository, DetallePedidoRepository detallePedidoRepository, FacturaRepository facturaRepository,
						   PedidoRepository pedidoRepository, ProductoRepository productoRepository, RubroRepository rubroRepository) {
		return args -> {
			System.out.println("-----------------ESTOY FUNCIONANDO---------");

/*El método builder() se genera automáticamente por Lombok
y te permite crear una instancia de Persona.Builder.
Luego, puedes encadenar llamadas a los métodos
setters generados automáticamente para establecer los
valores de los atributos de la clase.
Finalmente, build() crea la instancia
 de la clase Persona con los valores proporcionados.

 */


			Rubro rubro1 = Rubro.builder()
					.denominacion("pizzas")
					.build();


			Rubro rubro2 = Rubro.builder()
					.denominacion("bebidas")
					.build();

			Producto producto1 = Producto.builder()
					.tipo(Producto.Tipo.manufacturado)
					.tiempoEstimadoCocina(25)
					.denominacion("Pizza a la piedra")
					.precioVenta(1500)
					.precioCompra(900)
					.stockActual(10)
					.stockMinimo(5)
					.unidadMedida("unidad1")
					.receta("a")
					.build();


			Producto producto2 = Producto.builder()
					.tipo(Producto.Tipo.insumo)
					.tiempoEstimadoCocina(0)
					.denominacion("Coca Cola")
					.precioVenta(800)
					.precioCompra(500)
					.stockActual(30)
					.stockMinimo(15)
					.unidadMedida("unidad2")
					.receta("ninguna")
					.build();

			Producto producto3 = Producto.builder()
					.tipo(Producto.Tipo.manufacturado)
					.tiempoEstimadoCocina(30)
					.denominacion("Pizza cuatro quesos")
					.precioVenta(2000)
					.precioCompra(1300)
					.stockActual(20)
					.stockMinimo(12)
					.unidadMedida("unidad3")
					.receta("b")
					.build();

			rubro1.agregarProductos(producto1);
			rubro1.agregarProductos(producto3);
			rubro2.agregarProductos(producto2);

			rubroRepository.save(rubro1);
			rubroRepository.save(rubro2);

			Cliente cliente = Cliente.builder()
					.nombre("Juan")
					.apellido("Pérez")
					.telefono("2614567867")
					.email("juan@gmail.com")
					.build();

			Domicilio domicilio1 = Domicilio.builder()
					.calle("Calle1")
					.numero("30")
					.localidad("Guaymallén")
					.build();


			Domicilio domicilio2 = Domicilio.builder()
					.calle("Calle2")
					.numero("20")
					.localidad("Godoy Cruz")
					.build();

			cliente.agregarDomicilio(domicilio1);
			cliente.agregarDomicilio(domicilio2);

			SimpleDateFormat  formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
			String fecha1String = "2022-04-02";
			String fecha2String = "2022-11-30";
			String fecha3String = "2022-12-01";
			String fecha4String = "2022-12-03";
			Date fecha1 = formatoFecha.parse(fecha1String);
			Date fecha2 = formatoFecha.parse(fecha2String);
			Date fecha3 = formatoFecha.parse(fecha3String);
			Date fecha4 = formatoFecha.parse(fecha4String);

			DetallePedido detallePedido1 = DetallePedido.builder()
					.cantidad(2)
					.subtotal(3000)
					.build();


			DetallePedido detallePedido2 = DetallePedido.builder()
					.cantidad(1)
					.subtotal(800)
					.build();

			DetallePedido detallePedido3 = DetallePedido.builder()
					.cantidad(1)
					.subtotal(2000)
					.build();

			detallePedido1.setProducto(producto1);
			detallePedido2.setProducto(producto2);
			detallePedido3.setProducto(producto3);

			Pedido pedido1 = Pedido.builder()
					.estado(Pedido.Estado.iniciado)
					.fecha(fecha1)
					.tipoEnvio(Pedido.TipoEnvio.retira)
					.total(3800)
					.build();

			pedido1.agregarDetallesPedido(detallePedido1);
			pedido1.agregarDetallesPedido(detallePedido2);

			Pedido pedido2 = Pedido.builder()
					.estado(Pedido.Estado.entregado)
					.fecha(fecha2)
					.tipoEnvio(Pedido.TipoEnvio.delivery)
					.total(2000)
					.build();

			pedido2.agregarDetallesPedido(detallePedido3);

			Factura factura1 = Factura.builder()
					.numero(1)
					.fecha(fecha1)
					.descuento(20)
					.formaPago(Factura.FormaPago.efectivo)
					.total(3400)
					.build();


			Factura factura2 = Factura.builder()
					.numero(2)
					.fecha(fecha2)
					.descuento(0)
					.formaPago(Factura.FormaPago.etc)
					.total(2000)
					.build();

			pedido1.setFactura(factura1);
			pedido2.setFactura(factura2);

			cliente.agregarPedidos(pedido1);
			cliente.agregarPedidos(pedido2);

			clienteRepository.save(cliente);


			/*--------Uso de métodos para recuperar datos----------*/

			/* Mostrar los productos del rubro1 */

			Rubro rubroRecuperado = rubroRepository.findById(rubro1.getId()).orElse(null);

			if (rubroRecuperado != null) {
				rubroRecuperado.mostrarProductos();
			}

			/*Mostrar los pedidos y domicilios del cliente */
			Cliente clienteRecuperado = clienteRepository.findById(cliente.getId()).orElse(null);


			if (clienteRecuperado != null) {
				clienteRecuperado.mostrarPedidos();
				clienteRecuperado.mostrarDomicilios();
			}

			/* Cambiar el tipo de receta en producto2 */

			producto2.setReceta("c");
			productoRepository.save(producto2);

			Producto productoRecuperado = productoRepository.findById(producto2.getId()).orElse(null);
			if (productoRecuperado != null) {
				System.out.println("receta " + productoRecuperado.getReceta());
			}


			/* Eliminar un domicilio de cliente */

			domicilioRepository.delete(domicilio2);

			Cliente clienteRecuperado2 = clienteRepository.findById(cliente.getId()).orElse(null);


			if (clienteRecuperado2 != null) {
				clienteRecuperado2.mostrarDomicilios();
			}


		};
		}


	}