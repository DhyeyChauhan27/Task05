package webscraping;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.FileWriter;
import java.io.IOException;

public class WebScraping
{

    public static void main(String[] args) {
        String url = "https://www.amazon.com/s?k=laptop"; // Change URL to the desired product category

        try {
            Document doc = Jsoup.connect(url).get();
            Elements products = doc.select(".s-result-item");

            FileWriter csvWriter = new FileWriter("product_info.csv");
            csvWriter.append("Name,Price,Rating\n");

            for (Element product : products) {
                String name = product.select(".a-text-normal").text();
                String price = product.select(".a-price > .a-offscreen").text();
                String rating = product.select(".a-icon-star-small > .a-icon-alt").text().split(" ")[0];
                
                // Remove commas from name to avoid CSV issues
                name = name.replace(",", "");
                
                csvWriter.append(name + "," + price + "," + rating + "\n");
            }

            csvWriter.flush();
            csvWriter.close();

            System.out.println("Scraping and saving completed successfully.");

        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}

