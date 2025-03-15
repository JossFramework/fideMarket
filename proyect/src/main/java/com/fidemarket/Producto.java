package com.fidemarket;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

// Uso de interfaz gráfica
public class Producto {

    private int id;
    private String nombre;
    private int precio; // Cambiado a int
    private String categoria;

    public Producto(int id, String nombre, int precio, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecio() {
        return precio; // Cambiado a int
    }

    public String getCategoria() {
        return categoria;
    }

    private static int cantidadProductos = 0;
    private static int totalPrecio = 0; // Cambiado a int
    private static JLabel lblCantidad = new JLabel("Cantidad: 0");
    private static JLabel lblTotal = new JLabel("Total: $0");
    private static List<Factura> productosFacturados = new ArrayList<>();

    // Método para mostrar productos
    public static void mostrarProductos() {
        JFrame frame = new JFrame("Lista de Productos");

        // Datos de productos (ID, Nombre, Precio, Categoría)
        Object[][] datos = {
                { 1, "Arroz", 10, "Abarrotes" },  
                { 2, "Carne de res", 8, "Carnes" },  
                { 3, "Leche", 5, "Lácteos" },  
                { 4, "Manzanas", 2, "Frutas" }, 
                { 5, "Pan", 2, "Panadería" }  
        };

        // Modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel(datos,
                new String[] { "ID", "Producto", "Precio", "Categoría", "Acción" }) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4; // Solo la columna "Acción" es editable
            }
        };

        JTable tabla = new JTable(modelo);
        tabla.getColumn("Acción")
                .setCellRenderer((table, value, isSelected, hasFocus, row, column) -> new JButton("Añadir"));
        tabla.getColumn("Acción").setCellEditor(new DefaultCellEditor(new JCheckBox()) {
            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
                    int column) {
                JButton btn = new JButton("Añadir");
                btn.addActionListener((ActionEvent e) -> {
                    int id = (int) table.getValueAt(row, 0);
                    String producto = (String) table.getValueAt(row, 1);
                    int precio = (int) table.getValueAt(row, 2); 
                    String categoria = (String) table.getValueAt(row, 3);

                    // Verificar si el producto ya está en la lista
                    boolean productoExistente = false;
                    for (Factura factura : productosFacturados) {
                        if (factura.getId() == id) {
                            // Si existe, incrementar la cantidad
                            factura.setCantidad(factura.getCantidad() + 1);
                            totalPrecio += factura.getPrecio();
                            cantidadProductos++;
                            productoExistente = true;
                            break;
                        }
                    }

                    // Si el producto no existe, añadirlo como nuevo
                    if (!productoExistente) {
                        Factura factura = new Factura(id, producto, precio, categoria, 1);
                        productosFacturados.add(factura);
                        totalPrecio += factura.getTotal();
                        cantidadProductos++;
                    }

                    lblCantidad.setText("Cantidad: " + cantidadProductos);
                    lblTotal.setText("Total: $" + totalPrecio); // Total ahora es int
                });
                return btn;
            }
        });

        JScrollPane scroll = new JScrollPane(tabla);

        // Barra inferior con cantidad y total
        JPanel navPanel = new JPanel(new BorderLayout());
        navPanel.add(lblCantidad, BorderLayout.WEST);

        // Botón Comprar que llamará a Factura.mostrarFactura()
        JButton btnComprar = new JButton("Comprar");
        btnComprar.addActionListener((ActionEvent e) -> {
            frame.dispose();
            // Llamar al método estático de la clase Factura
            Factura.mostrarFactura(productosFacturados);
        });
        navPanel.add(btnComprar, BorderLayout.CENTER);
        navPanel.add(lblTotal, BorderLayout.EAST);

        // Diseño del frame
        frame.setLayout(new BorderLayout());
        frame.add(scroll, BorderLayout.CENTER);
        frame.add(navPanel, BorderLayout.SOUTH);

        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
