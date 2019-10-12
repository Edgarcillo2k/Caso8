package case8;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class HtmlWriter 
{
	public HtmlWriter () {}
	public void drawPolygons(String pHtmlPath, String pImagePath,String pImage2Path,String pImage3Path,double pPercentageOfPixels)
	{
		try {
			File f = new File(pHtmlPath);
	        BufferedWriter htmlFile = new BufferedWriter(new FileWriter(f));
	        ImageReader imageReader = new ImageReader();
	        Table table = imageReader.getImageTable(new File(pImagePath), pPercentageOfPixels);
	        GeneticAlgorithm genetic = new GeneticAlgorithm(table);
	        genetic.createPopulation(60);
	        genetic.run(10);
	        table = imageReader.getImageTable(new File(pImage2Path), pPercentageOfPixels);
	        genetic.setTable(table);
	        genetic.panel.repaint();
	        genetic.run(10);
	        table = imageReader.getImageTable(new File(pImage3Path), pPercentageOfPixels);
	        genetic.setTable(table);
	        genetic.panel.repaint();
	        genetic.run(10);
	        /*

	         for (int i = 0; i < maxs.size(); i++) {
            for (int j = 0; j < maxs.size() - 1; j++) {
                PixelInformation current = maxs.get(j);
                PixelInformation next = maxs.get(j + 1);
                if (current.getPoint().getX() > next.getPoint().getX()) {
                    maxs.set(j + 1, current);
                    maxs.set(j, next);
                }
            }
        }
        for (int i = 0; i < mins.size(); i++) {
            for (int j = 0; j < mins.size() - 1; j++) {
                PixelInformation current = mins.get(j);
                PixelInformation next = mins.get(j + 1);
                if (current.getPoint().getX() < next.getPoint().getX()) {
                    mins.set(j + 1, current);
                    mins.set(j, next);
                }
            }
        }

	         */
	        /*

	        ArrayList<Polygon> polygons = imageReader.getImagePolygons(new File(pImagePath), pPercentageOfPixels);
	        String html = "<!DOCTYPE html>\n"
	                + "<html>\n"
	                + "<body>\n"
	                + "\n"
	                + "<svg height=\"2100\" width=\"2100\">";
	        for (int currentPolygon = 0; currentPolygon < polygons.size(); currentPolygon++) {
	            Polygon polygon = polygons.get(currentPolygon);
	            ArrayList<PixelInformation> polygonPoints = polygon.getPoints();
	            Color color = polygon.getColor();
	            html+= "<polygon points='";
	            for (int currentPixel = 0; currentPixel < polygonPoints.size(); currentPixel++) {
	                Point pixel = polygonPoints.get(currentPixel).getPoint();
	                html +=pixel.getX() + "," + pixel.getY() + " ";
	            }
	            html += "'style=\"fill:rgb(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() +")\" />\n";
	        }
		    html += "</svg>\n\n</body>\n</html>";
		    htmlFile.write(html);
		    htmlFile.close();

		*/
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

}
