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
            File f = new File("C:\\Users\\mario\\OneDrive\\Desktop\\Caso8Netbeans\\src\\case8\\html.html");
            BufferedWriter htmlFile = new BufferedWriter(new FileWriter(f));
            ImageReader imageReader = new ImageReader();
            Table table = imageReader.getImageTable(new File(pImagePath), pPercentageOfPixels);
            GeneticAlgorithm genetic = new GeneticAlgorithm(table);
            genetic.createPopulation(60);
            StringBuilder builderHtml = new StringBuilder();
            
            System.out.println("Goku");
            int svg = 0;
            builderHtml.append("<!DOCTYPE html>\n<html>\n<body> <a href='#' id = 'nextGen' >Next Gen</a> \n<div id = 'svg" + svg + "' style = 'display:visible;'>");

            for (int iteration = 0; iteration < 5; iteration++) {
                genetic.run(2);
                HashMap<Integer, ArrayList<Individual<PixelInformation>>> hashPerGeneration = genetic.getPopulation();
                builderHtml.append("<svg height=\"2100\" width=\"2100\"><polygon points='");
                for (Map.Entry<Integer, ArrayList<Individual<PixelInformation>>> entry : hashPerGeneration.entrySet()) {
                    ArrayList<Individual<PixelInformation>> sectorToVisualize = entry.getValue();
                    int points = 0;
                    for (int pixel = 0; pixel < sectorToVisualize.size(); pixel++) {
                        PixelInformation currentPixel = sectorToVisualize.get(pixel).getObject();
                        if (points < 8) {
                            builderHtml.append(currentPixel.getPoint().getX() + "," + currentPixel.getPoint().getY() + " ");
                            points++;
                        } else {
                            Color color = currentPixel.getColor();
                            builderHtml.append("'style=\"fill:rgb(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ");stroke:rgb(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ");stroke-width:1\"/>\n");
                            builderHtml.append("<polygon points='");
                            points = 0;
                        }
                    }
                }
                svg++;
                builderHtml.append("'style=\"fill:white\"/></svg></div>\n\n<div id = 'svg" + svg + "' style = 'display:none;'>");
            }
            
            System.out.println("Vegetta");
            table = imageReader.getImageTable(new File(pImage2Path), pPercentageOfPixels);
            genetic.setTable(table);
            for (int iteration = 0; iteration < 5; iteration++) {
                genetic.run(2);
                HashMap<Integer, ArrayList<Individual<PixelInformation>>> HashPerGeneration2 = genetic.getPopulation();
                builderHtml.append("<svg height=\"2100\" width=\"2100\"><polygon points='");

                for (Map.Entry<Integer, ArrayList<Individual<PixelInformation>>> entry : HashPerGeneration2.entrySet()) {
                    ArrayList<Individual<PixelInformation>> sectorToVisualize = entry.getValue();
                    int points = 0;
                    for (int pixel = 0; pixel < sectorToVisualize.size(); pixel++) {
                        PixelInformation currentPixel = sectorToVisualize.get(pixel).getObject();
                        if (points < 8) {
                            builderHtml.append(currentPixel.getPoint().getX() + "," + currentPixel.getPoint().getY() + " ");
                            points++;
                        } else {
                            Color color = currentPixel.getColor();
                            builderHtml.append("'style=\"fill:rgb(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ");stroke:rgb(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ");stroke-width:1\"/>\n");
                            builderHtml.append("<polygon points='");
                            points = 0;
                        }

                    }
                }
                svg++;
                builderHtml.append("'style=\"fill:white\"/></svg></div>\n\n<div id = 'svg" + svg + "' style = 'display:none;'>");
            }

            System.out.println("Meliodas");
            table = imageReader.getImageTable(new File(pImage3Path), pPercentageOfPixels);
            genetic.setTable(table);
            for (int iteration = 0; iteration < 5; iteration++) {
                genetic.run(2);
                HashMap<Integer, ArrayList<Individual<PixelInformation>>> HashPerGeneration3 = genetic.getPopulation();
                builderHtml.append("<svg height=\"2100\" width=\"2100\"><polygon points='");

                for (Map.Entry<Integer, ArrayList<Individual<PixelInformation>>> entry : HashPerGeneration3.entrySet()) {
                    ArrayList<Individual<PixelInformation>> sectorToVisualize = entry.getValue();
                    int point = 0;
                    for (int pixel = 0; pixel < sectorToVisualize.size(); pixel++) {
                        PixelInformation currentPixel = sectorToVisualize.get(pixel).getObject();
                        if (point < 8) {
                            builderHtml.append(currentPixel.getPoint().getX() + "," + currentPixel.getPoint().getY() + " ");
                            point++;
                        } else {
                            Color color = currentPixel.getColor();
                            builderHtml.append("'style=\"fill:rgb(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ");stroke:rgb(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ");stroke-width:1\"/>\n");
                            builderHtml.append("<polygon points='");
                            point = 0;
                        }
                    }
                }
                svg++;
                builderHtml.append("'style=\"fill:white\"/></svg></div>\n\n<div id = 'svg" + svg + "' style = 'display:none;'>");

            }
            builderHtml.append("\n\n</div></body>");
            builderHtml.append("<script\n"
                    + "  src=\"https://code.jquery.com/jquery-3.4.1.min.js\"\n"
                    + "  integrity=\"sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=\"\n"
                    + "  crossorigin=\"anonymous\"></script>");
            builderHtml.append("<script src = js/jQuery.js></script>");
            builderHtml.append("\n</html>");
            String html = builderHtml.toString();
            htmlFile.write(html);
            htmlFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
