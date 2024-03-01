/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab7p2_venuslobo;

import java.io.BufferedWriter;
import java.io.File;
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

    public void cargarArchi() {
        Scanner sc = null;
        ap = new ArrayList();
        if (archivo.exists()) {
            try {
                sc = new Scanner(archivo);
                sc.useDelimiter("\n");

                while (sc.hasNext()) {
                    ap.add(new Producto(sc.nextInt(),
                            sc.nextLine(),
                            sc.nextInt(),
                            sc.nextDouble(),
                            sc.nextInt(),
                            sc.nextInt()));
                }
            } catch (Exception e) {
            }
            sc.close();
        }
    }

    public void escribirArchi() throws IOException{
        FileWriter fw = null; 
        BufferedWriter bw = null; 
        
        try {
           fw = new FileWriter(archivo,false);
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
