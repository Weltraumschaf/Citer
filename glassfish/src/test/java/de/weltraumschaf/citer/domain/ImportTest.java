package de.weltraumschaf.citer.domain;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.Test;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 * @license http://www.weltraumschaf.de/the-beer-ware-license.txt THE BEER-WARE LICENSE
 */
public class ImportTest {

    public static String readFile(String filename) throws URISyntaxException, IOException {
        URL resource = ImportTest.class.getResource(filename);
        File file    = new File(resource.toURI());
        return  FileUtils.readFileToString(file);
    }

    public static JSONArray parseJson(String json) {
        Object obj = JSONValue.parse(json);
        return (JSONArray)((JSONObject)obj).get("cites");
    }

    @Test
    public void foo() throws URISyntaxException, IOException {
        String jsonString = readFile("/cites.json");
        JSONArray citesIn = parseJson(jsonString);

        Iterator it = citesIn.iterator();
        JSONObject citeIn;
        List<Cite> citesOut = new ArrayList<Cite>(citesIn.size());
        Map<String, Author> authors = new HashMap<String, Author>();

//        DateTimeFormatter fmt = DateTimeFormat.forPattern("dd.MM.yyyy");
        DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");

        while (it.hasNext()) {
            citeIn = (JSONObject)it.next();
            Cite citeOut = new Cite();
            citeOut.setId(UUID.randomUUID().toString());
            citeOut.setText((String)citeIn.get("text"));

            String authorIn = (String)citeIn.get("name");
            Author authorOut;

            if (authors.containsKey(authorIn)) {
                authorOut = authors.get(authorIn);
            } else {
                authorOut = new Author();
                authorOut.setId(UUID.randomUUID().toString());
                authorOut.setName(authorIn);
            }

            citeOut.setCrator(authorOut);
System.out.println((String)citeIn.get("date"));
//            DateTime date = fmt.parseDateTime((String)citeIn.get("date"));
            DateTime date = fmt.parseDateTime("04/02/2011 20:27:05");
//            citeOut.setDate(date);
            citesOut.add(citeOut);
        }

        for (Cite c : citesOut) {
            System.out.println(c.toString());
        }
    }

}