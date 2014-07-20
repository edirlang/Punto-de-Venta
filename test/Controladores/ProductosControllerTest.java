/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controladores;

import javax.swing.table.DefaultTableModel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Caja1
 */
public class ProductosControllerTest {
    
    public ProductosControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of nuevoPro method, of class ProductosController.
     */
    @Test
    public void testNuevoPro() {
        System.out.println("nuevoPro");
        String[] producto = null;
        ProductosController instance = new ProductosController();
        instance.nuevoPro(producto);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Productos method, of class ProductosController.
     */
    @Test
    public void testProductos() {
        System.out.println("Productos");
        ProductosController instance = new ProductosController();
        DefaultTableModel expResult = null;
        DefaultTableModel result = instance.Productos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Actualizar method, of class ProductosController.
     */
    @Test
    public void testActualizar() {
        System.out.println("Actualizar");
        ProductosController instance = new ProductosController();
        instance.Actualizar();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Eliminar method, of class ProductosController.
     */
    @Test
    public void testEliminar() {
        System.out.println("Eliminar");
        ProductosController instance = new ProductosController();
        instance.Eliminar();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Buscar method, of class ProductosController.
     */
    @Test
    public void testBuscar() {
        System.out.println("Buscar");
        String codigo = "";
        ProductosController instance = new ProductosController();
        String[] expResult = null;
        String[] result = instance.Buscar(codigo);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of EditarProducto method, of class ProductosController.
     */
    @Test
    public void testEditarProducto() {
        System.out.println("EditarProducto");
        String[] pro = null;
        ProductosController instance = new ProductosController();
        instance.EditarProducto(pro);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
