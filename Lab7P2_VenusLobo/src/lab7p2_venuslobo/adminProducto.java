/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab7p2_venuslobo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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

    public DefaultTableModel cargarArchivoATabla(DefaultTableModel model) throws FileNotFoundException {
        if (!archivo.exists()) {
            throw new FileNotFoundException("El archivo no existe.");
        }

        model.setRowCount(0);

        try (Scanner scanner = new Scanner(archivo)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] partes = linea.split(",");
                if (partes.length == 6) {
                    model.addRow(partes); 
                } else {
                    throw new IllegalStateException("El formato del archivo es incorrecto.");
                }
            }
        }
        return model;
        
    }

    public void cargarArchi() throws IOException {
        BufferedReader b = new BufferedReader(new FileReader(archivo.getName()));
        String s;
        while ((s = b.readLine()) != null) {
            String[] tokens = s.split(",");
            ap.add(new Producto(Integer.parseInt(tokens[0]), tokens[1], Integer.parseInt(tokens[2]), Double.parseDouble(tokens[3]), Integer.parseInt(tokens[4]), Integer.parseInt(tokens[5])));
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
