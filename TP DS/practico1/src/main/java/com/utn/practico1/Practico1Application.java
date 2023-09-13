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
	DetallePedidoRepository detallePedidoRepository;
	@Autowired
	DomicilioRepository domicilioRepository;
	@Autowired
	FacturaRepository facturaRepository;
	@Autowired
	PedidoRepository pedidoRepository;
	@Autowired
	ProductoRepository productoRepository;
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

			Domicilio domicilio1 = Domicilio.builder()
					.calle("Calle1")
					.numero("30")
					.localidad("Gllen")
					.build();

			Domicilio domicilio2 = Domicilio.builder()
					.calle("Calle2")
					.numero("30")
					.localidad("GC")
					.build();

			Cliente cliente = Cliente.builder()
					.nombre("Juan")
					.apellido("Pérez")
					.telefono("111")
					.email("juan@gmail.com")
					.build();

			SimpleDateFormat  formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
			String fecha1String = "2022-04-02";
			String fecha2String = "2022-11-30";
			String fecha3String = "2022-12-01";
			String fecha4String = "2022-12-03";
			Date fecha1 = formatoFecha.parse(fecha1String);
			Date fecha2 = formatoFecha.parse(fecha2String);
			Date fecha3 = formatoFecha.parse(fecha3String);
			Date fecha4 = formatoFecha.parse(fecha4String);

			Pedido pedido1 = Pedido.builder()
					.estado(Pedido.Estado.iniciado)
					.fecha(fecha1)
					.tipoEnvio(Pedido.TipoEnvio.retira)
					.total(23)
					.build();

			Pedido pedido2 = Pedido.builder()
					.estado(Pedido.Estado.entregado)
					.fecha(fecha2)
					.tipoEnvio(Pedido.TipoEnvio.delivery)
					.total(29)
					.build();



			Factura factura1 = Factura.builder()
					.numero(01)
					.fecha(fecha3)
					.descuento(90)
					.formaPago(Factura.FormaPago.efectivo)
					.total(800)
					.build();

			Factura factura2 = Factura.builder()
					.numero(02)
					.fecha(fecha4)
					.descuento(50)
					.formaPago(Factura.FormaPago.etc)
					.total(500)
					.build();

			DetallePedido detallePedido1 = DetallePedido.builder()
					.cantidad(400)
					.subtotal(2000)
					.build();

			DetallePedido detallePedido2 = DetallePedido.builder()
					.cantidad(200)
					.subtotal(3000)
					.build();

			Producto producto1 = Producto.builder()
					.tipo(Producto.Tipo.manufacturado)
					.tiempoEstimadoCocina(20)
					.denominacion("A")
					.precioVenta(200)
					.precioCompra(100)
					.stockActual(40)
					.stockMinimo(10)
					.unidadMedida("cm")
					.receta("a")
					.build();

			Producto producto2 = Producto.builder()
					.tipo(Producto.Tipo.insumo)
					.tiempoEstimadoCocina(25)
					.denominacion("B")
					.precioVenta(240)
					.precioCompra(150)
					.stockActual(50)
					.stockMinimo(20)
					.unidadMedida("cm")
					.receta("b")
					.build();

			Rubro rubro1 = Rubro.builder()
					.denominacion("casa")
					.build();

			Rubro rubro2 = Rubro.builder()
					.denominacion("otros")
					.build();

			cliente.agregarDomicilio(domicilio1);
			cliente.agregarDomicilio(domicilio2);
			cliente.agregarPedidos(pedido1);
			cliente.agregarPedidos(pedido2);
			pedido1.setFactura(factura1);
			pedido1.agregarDetallesPedido(detallePedido1);
			pedido1.agregarDetallesPedido(detallePedido2);

			productoRepository.save(producto1);
			productoRepository.save(producto2);

			detallePedido1.setProducto(producto1);

			rubro1.agregarProductos(producto1);
			rubro1.agregarProductos(producto2);

			clienteRepository.save(cliente);
			detallePedidoRepository.save(detallePedido1);
			detallePedidoRepository.save(detallePedido2);
			domicilioRepository.save(domicilio1);
			domicilioRepository.save(domicilio2);
			facturaRepository.save(factura1);
			facturaRepository.save(factura2);
			pedidoRepository.save(pedido1);
			pedidoRepository.save(pedido2);
			rubroRepository.save(rubro1);
			rubroRepository.save(rubro2);

			Rubro rubroRecuperado = rubroRepository.findById(rubro1.getId()).orElse(null);

			if (rubroRecuperado != null) {
				rubroRecuperado.mostrarProductos();
			}

			Cliente clienteRecuperado = clienteRepository.findById(cliente.getId()).orElse(null);

			if (clienteRecuperado != null) {
				clienteRecuperado.mostrarPedidos();
				clienteRecuperado.mostrarDomicilios();
			}

			producto2.setReceta("c");
			productoRepository.save(producto2);

			Producto productoRecuperado = productoRepository.findById(producto2.getId()).orElse(null);
			if (productoRecuperado != null) {
				System.out.println("receta " + productoRecuperado.getReceta());
			}

			domicilioRepository.delete(domicilio2);


			Cliente clienteRecuperado2 = clienteRepository.findById(cliente.getId()).orElse(null);

			if (clienteRecuperado2 != null) {
				clienteRecuperado2.mostrarDomicilios();
			}

		};
		}


	}