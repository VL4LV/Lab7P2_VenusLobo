/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab7p2_venuslobo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
public class adminProducto {

    private ArrayList<Producto> ap = new ArrayList();
    private File archivo = null;

    public adminProducto(String path) {
        archivo = new File(path);
    }

    public ArrayList<Producto> getAp() {
        return ap;
    }

    public void setAp(ArrayList<Producto> ap) {
        this.ap = ap;
    }

    public File getArchivo() {
        return archivo;
    }

    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }

    private void agregarProductoDesdeLinea(String[] partes) {
        int id = Integer.parseInt(partes[0]);
        String nombre = partes[1];
        int categoria = Integer.parseInt(partes[2]);
        double precio = Double.parseDouble(partes[3]);
        int aisle = Integer.parseInt(partes[4]);
        int bin = Integer.parseInt(partes[5]);
        Producto producto = new Producto(id, nombre, categoria, precio, aisle, bin);
        ap.add(producto);
    }

    public void cargarArchi() throws IOException {
        BufferedReader b = new BufferedReader(new FileReader(archivo.getName()));
        String s; 
        while((s = b.readLine()) != null){
            String[] tokens = s.split(",");
            ap.add(new Producto(Integer.parseInt(tokens[0]), tokens[1],Integer.parseInt(tokens[2]), Double.parseDouble(tokens[3]), Integer.parseInt(tokens[4]), Integer.parseInt(tokens[5])));
        }
    }

    public void escribirArchi() throws IOException {
        FileWriter fw = null;
        BufferedWriter bw = null;

        try {
            fw = new FileWriter(archivo, false);
            bw = new BufferedWriter(fw);

            for (Producto p : ap) {
                bw.write(p.getId() + ",");
                bw.write(p.getName() + ",");
                bw.write(p.getCategory() + ",");
                bw.write(p.getPrice() + ",");
                bw.write(p.getAisle() + ",");
                bw.write(p.getBin() + "\n");
            }
            bw.flush();
        } catch (Exception e) {
        }
        bw.close();
        fw.close();
    }
}
