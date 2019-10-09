package case8;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Case8 {

    public static void main(String[] args) throws IOException, InterruptedException {
        File f = new File("C:\\Users\\Edgar\\Desktop\\Importante\\TEC\\IV SEMESTRE\\ANALISIS DE ALGORITMOS\\html.html");
        BufferedWriter prueba = new BufferedWriter(new FileWriter(f));

        ImageReader imageReader = new ImageReader();
        ArrayList<ArrayList<PixelInformation>> puntosPorSector = imageReader.getImagePixelsInformation(new File("C:\\Users\\Edgar\\Desktop\\Importante\\TEC\\IV SEMESTRE\\ANALISIS DE ALGORITMOS\\goku.png"), 0.3);
        String html = "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<body>\n"
                + "\n"
                + "<svg height=\"2100\" width=\"2100\">";
        for (int i = 0; i < puntosPorSector.size(); i++) {
            ArrayList<PixelInformation> sector = puntosPorSector.get(i);
            if (sector.size() > 0) {
                html+= "<polygon points='";
                for (int j = 0; j < sector.size(); j++) {
                    Point pixel = sector.get(j).getPoint();
                    html +=pixel.getX() + "," + pixel.getY() + " ";
                }
                Color color = imageReader.getAVG(sector);
                html += "'style=\"fill:rgb(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() +")\" />\n";
            }
        }
        html += "</svg>\n"
                + "\n"
                + "</body>\n"
                + "</html>";
        prueba.write(html);
        prueba.close();
    }
}
