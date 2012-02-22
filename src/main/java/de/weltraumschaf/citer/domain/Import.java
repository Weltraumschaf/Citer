package de.weltraumschaf.citer.domain;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import org.codehaus.jackson.map.ObjectMapper;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * @todo refactor to jettison.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 * @license http://www.weltraumschaf.de/the-beer-ware-license.txt THE BEER-WARE LICENSE
 */
public class Import {

    private static final DateTimeFormatter fmt = DateTimeFormat.forPattern("dd.MM.yyyy");
    private static ObjectMapper mapper = new ObjectMapper();;

    private static Data convertJsonToModel(List cites) {
        Data data = new Data();
        iterateJson(data, cites.iterator());
        return data;
    }

    private static void iterateJson(Data data, Iterator it) {
        Map jsonCite;

        while (it.hasNext()) {
            jsonCite    = (HashMap)it.next();
            Cite cite   = createCite((String)jsonCite.get("text"));
            String name = (String)jsonCite.get("name");
            Author author;

            if (data.hasAuthorWithName(name)) {
                author = data.getAuthorByName(name);
            } else {
                author = createAuthor(name);
                data.addAuthor(author);
            }

            cite.setCreator(author);
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
        URL resource = Import.class.getResource(filename);
        List cites = mapper.readValue(new File(resource.toURI()), ArrayList.class);
        return convertJsonToModel(cites);
    }

}
