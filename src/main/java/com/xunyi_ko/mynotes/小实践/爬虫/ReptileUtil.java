import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Reptile {
    public static Document getHtmlByUrl(String url){
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
        }catch(Exception e) {
            e.printStackTrace();
        }
        return document == null ? new Document(url) : document;
    }
    
    public static Document postHtmlByUrl(String url){
        Document document = null;
        try {
            document = Jsoup.connect(url).post();
        }catch(Exception e) {
            e.printStackTrace();
        }
        return document == null ? new Document(url) : document;
    }
    
    public static List<Document> saveForEachHtml(String url) throws IOException {
        Document doc = getHtmlByUrl(url);
        Elements as = doc.getElementsByTag("a");
        
        OutputStream out = null;
        List<Document> documents = new LinkedList<>();
        int num = 1;
        for(Element e : as) {
            if(e.hasClass("css")) {
                String href = e.attr("href");
                String title = e.text();
                Document document = postHtmlByUrl("http://www.jscwf.org.cn/" + href);
                Charset charset = document.charset();

                try {
                    out = new FileOutputStream("C:/Users/Administrator/Desktop/新闻素材/公示公告/2/" + num + "-" + title + ".html");
                    out.write(document.toString().getBytes(charset));
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }finally {
                    if(out != null) {
                        try {
                            out.close();
                        }catch(Exception e2) {
                            
                        }
                    }
                }
                documents.add(document);
                num ++;
            }
        }
        
        return documents;
    }
    
    
}
