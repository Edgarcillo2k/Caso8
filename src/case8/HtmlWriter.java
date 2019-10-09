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
	public void drawPolygons(String pHtmlPath, String pImagePath,double pPercentageOfPixels)
	{
		try {
			File f = new File(pHtmlPath);
	        BufferedWriter htmlFile = new BufferedWriter(new FileWriter(f));
	        ImageReader imageReader = new ImageReader();
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
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
