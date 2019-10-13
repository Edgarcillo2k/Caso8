package case8;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HtmlWriter {

    public HtmlWriter() {
    }

    public void drawPolygons(String pHtmlPath, String pImagePath, String pImage2Path, String pImage3Path, double pPercentageOfPixels) {
        try {
//            File f = new File(pHtmlPath);
//            BufferedWriter htmlFile = new BufferedWriter(new FileWriter(f));C:\Users\mario\OneDrive\Desktop\Caso8Netbeans\src\case8
            ImageReader imageReader = new ImageReader();
            Table table = imageReader.getImageTable(new File(pImagePath), pPercentageOfPixels);
            GeneticAlgorithm genetic = new GeneticAlgorithm(table);
            genetic.createPopulation(60);

            for (int iteration = 0; iteration < 4; iteration++) {
                genetic.run(2);
                File f = new File("C:\\Users\\mario\\OneDrive\\Desktop\\Caso8Netbeans\\src\\case8\\htmlgoku" + iteration + ".html");
                BufferedWriter htmlFile = new BufferedWriter(new FileWriter(f));
                HashMap<Integer, ArrayList<Individual<PixelInformation>>> prueba = genetic.population;
                String html = "<!DOCTYPE html>\n"
                        + "<html>\n"
                        + "<body>\n"
                        + "\n"
                        + "<svg height=\"2100\" width=\"2100\">"
                        + "<polygon points='";
                for (Map.Entry<Integer, ArrayList<Individual<PixelInformation>>> entry : prueba.entrySet()) {
                    ArrayList<Individual<PixelInformation>> value = entry.getValue();
                    int cont = 0;
                    int cont2 = 0;
                    for (int pixel = 0; pixel < value.size(); pixel++) {
                        if (cont2 < 20) {
                            PixelInformation actual = value.get(pixel).getObject();
                            if (cont < 3) {
                                html += actual.getPoint().getX() + "," + actual.getPoint().getY() + " ";
                                cont++;
                            } else {
                                Color color = actual.getColor();
                                html += "'style=\"fill:rgb(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ");stroke:rgb(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ");stroke-width:8\"/>\n";
                                html += "<polygon points='";
                                cont = 0;
                                cont2++;
                            }
                        }
                    }
                }
                html += "/>";
                html += "</svg>\n\n</body>\n</html>";
                htmlFile.write(html);
                htmlFile.close();
            }

            table = imageReader.getImageTable(new File(pImage2Path), pPercentageOfPixels);
            HashMap<Integer, ArrayList<Individual<PixelInformation>>> prueba = new HashMap<>();
            genetic.setTable(table);
            for (int iteration = 0; iteration < 10; iteration++) {
                genetic.run(2);
                File f = new File("C:\\Users\\mario\\OneDrive\\Desktop\\Caso8Netbeans\\src\\case8\\htmlvegetta" + iteration + ".html");
                BufferedWriter htmlFile = new BufferedWriter(new FileWriter(f));
                prueba = genetic.population;
                String html = "<!DOCTYPE html>\n"
                        + "<html>\n"
                        + "<body>\n"
                        + "\n"
                        + "<svg height=\"2100\" width=\"2100\">"
                        + "<polygon points='";
                for (Map.Entry<Integer, ArrayList<Individual<PixelInformation>>> entry : prueba.entrySet()) {
                    ArrayList<Individual<PixelInformation>> value = entry.getValue();
                    int cont = 0;
                    int cont2 = 0;
                    for (int pixel = 0; pixel < value.size(); pixel++) {
                        if (cont2 < 20) {
                            PixelInformation actual = value.get(pixel).getObject();
                            if (cont < 3) {
                                html += actual.getPoint().getX() + "," + actual.getPoint().getY() + " ";
                                cont++;
                            } else {
                                Color color = actual.getColor();
                                html += "'style=\"fill:rgb(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ");stroke:rgb(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ");stroke-width:8\"/>\n";
                                html += "<polygon points='";
                                cont = 0;
                                cont2++;
                            }
                        }
                    }
                }
                html += "/>";
                html += "</svg>\n\n</body>\n</html>";
                htmlFile.write(html);
                htmlFile.close();
            }
            table = imageReader.getImageTable(new File(pImage3Path), pPercentageOfPixels);
            prueba = new HashMap<>();
            genetic.setTable(table);
            for (int iteration = 0; iteration < 10; iteration++) {
                genetic.run(2);
                File f = new File("C:\\Users\\mario\\OneDrive\\Desktop\\Caso8Netbeans\\src\\case8\\htmlmeliodas" + iteration + ".html");
                BufferedWriter htmlFile = new BufferedWriter(new FileWriter(f));
                prueba = genetic.population;
                String html = "<!DOCTYPE html>\n"
                        + "<html>\n"
                        + "<body>\n"
                        + "\n"
                        + "<svg height=\"2100\" width=\"2100\">"
                        + "<polygon points='";
                for (Map.Entry<Integer, ArrayList<Individual<PixelInformation>>> entry : prueba.entrySet()) {
                    ArrayList<Individual<PixelInformation>> value = entry.getValue();
                    int cont = 0;
                    int cont2 = 0;
                    for (int pixel = 0; pixel < value.size(); pixel++) {
                        if (cont2 < 20) {
                            PixelInformation actual = value.get(pixel).getObject();
                            if (cont < 3) {
                                html += actual.getPoint().getX() + "," + actual.getPoint().getY() + " ";
                                cont++;
                            } else {
                                Color color = actual.getColor();
                                html += "'style=\"fill:rgb(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ");stroke:rgb(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ");stroke-width:8\"/>\n";
                                html += "<polygon points='";
                                cont = 0;
                                cont2++;
                            }
                        }
                    }
                }
                html += "/>";
                html += "</svg>\n\n</body>\n</html>";
                htmlFile.write(html);
                htmlFile.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
