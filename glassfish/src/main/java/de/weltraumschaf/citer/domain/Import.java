package de.weltraumschaf.citer.domain;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Iterator;
import java.util.UUID;
import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 * @license http://www.weltraumschaf.de/the-beer-ware-license.txt THE BEER-WARE LICENSE
 */
public class Import {

    private static final DateTimeFormatter fmt = DateTimeFormat.forPattern("dd.MM.yyyy");

    private static String readFile(String filename) throws URISyntaxException, IOException {
        URL resource = Import.class.getResource(filename);
        File file    = new File(resource.toURI());
        return  FileUtils.readFileToString(file);
    }

    private static JSONArray parseJson(String json) {
        Object obj = JSONValue.parse(json);
        return (JSONArray)((JSONObject)obj).get("cites");
    }

    private static Data convertJsonToModel(JSONArray citesIn) {
        Data data = new Data();
        iterateJson(data, citesIn.iterator());
        return data;
    }

    private static void iterateJson(Data data, Iterator it) {
        JSONObject jsonCite;

        while (it.hasNext()) {
            jsonCite = (JSONObject)it.next();
            Cite cite = createCite((String)jsonCite.get("text"));
            String name = (String)jsonCite.get("name");
            Author author;

            if (data.hasAuthorWithName(name)) {
                author = data.getAuthorByName(name);
            } else {
                author = createAuthor(name);
                data.addAuthor(author);
            }

            cite.setCrator(author);
            DateTime date = fmt.parseDateTime((String)jsonCite.get("date"));
            cite.setDate(date);
            data.addCite(cite);
        }
    }

    private static Cite createCite(String text) {
        Cite cite = new Cite();
        cite.setId(UUID.randomUUID().toString());
        cite.setText(text);
        return cite;
    }
    private static Author createAuthor(String name) {
        Author author = new Author();
        author.setId(UUID.randomUUID().toString());
        author.setName(name);
        return author;
    }

    public static Data createModel(String filename) throws URISyntaxException, IOException {
        String jsonString = readFile(filename);
        JSONArray citesIn = parseJson(jsonString);
        return convertJsonToModel(citesIn);
    }
}
