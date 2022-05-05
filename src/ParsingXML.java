import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

        public class ParsingXML {
            public static void main(String[] args) throws Exception {
                System.setProperty("http.agent", "Chrome");
                List<String> listData = getLinkVocabulary("https://tienganhmoingay.com/600-tu-vung-toeic/chu-de-business-planning/");
                listData.forEach(ParsingXML::downloadImage);
    }

    static List<String> getLinkVocabulary(String url) throws Exception {
        String domain = "https://tienganhmoingay.com";
        ///600-tu-vung-toeic/address-verb-business-planning/

        Document doc = Jsoup.parse(new URL(url).openStream(), "UTF-8", "", Parser.xmlParser());
        Element elementRoot = doc.getElementById("page-container");
        assert elementRoot != null;
        Elements article = elementRoot.select("article");

        List<Node> folder = article.select("section").get(4).select("section").get(1).childNodes();

        List<Node> data = new ArrayList<>();

        folder.forEach(node -> {
            if (node instanceof Element) {
                node.childNodes().forEach(node1 -> {
                    if (node1 instanceof Element) {
                        data.add(node1);
                    }
                });
            }
        });

        List<String> listUrl = new ArrayList<>();

        data.forEach(node -> {
            if (node instanceof Element) {
                for (Node childNode : node.childNodes()) {
                    if (childNode instanceof Element) {
                        for (Node childNodeChild : childNode.childNodes()) {
                            if (childNodeChild instanceof Element) {
                                String link = childNodeChild.attributes().get("href");
                                listUrl.add(domain + link);
                            }
                        }
                    }
                }
            }
        });
        System.out.println(listUrl);

        return listUrl;
    }

    static void downloadImage(String url) {
        new Thread(() -> {
            try {
                String domain = "https://tienganhmoingay.com/";
                Document doc = Jsoup.parse(new URL(url).openStream(), "UTF-8", "", Parser.xmlParser());

                Element elementRoot = doc.getElementById("page-container");

                assert elementRoot != null;

                Elements article = elementRoot.select("article");

                Elements section = article.select("section");

                String nameWord = section.select("h1").get(1).childNodes().get(0).attributes().get("#text").replaceAll("\\r|\\n", "");

                Element lastElement = section.get(5).select("section").get(1).getAllElements().get(11);

                String urlImage;

                urlImage = Objects.requireNonNull(lastElement.attributes().get("src"));

                System.out.println(domain + urlImage);

                try (InputStream in = new URL(domain + urlImage).openStream()) {
                    Files.copy(in, Paths.get("/Users/namluongxuan/dddd/" + nameWord + ".jpeg"));
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }).start();

    }
}
