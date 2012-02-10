package de.weltraumschaf.citer.domain;

//import org.joda.time.DateTime;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 * @license http://www.weltraumschaf.de/the-beer-ware-license.txt THE BEER-WARE LICENSE
 */
public class Cite {

    private String id;
    private String text;
    private Author crator;
//    private DateTime date;

    public Author getCrator() {
        return crator;
    }

    public void setCrator(Author crator) {
        this.crator = crator;
    }

//    public DateTime getDate() {
//        return date;
//    }
//
//    public void setDate(DateTime date) {
//        this.date = date;
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
//        return String.format("Cite:\nid: %s\ntext: %s\ndate: %s\ncreator: %s\n", id, text, date, crator);
        return String.format("Cite:\nid: %s\ntext: %s\ndate: %s\ncreator: %s\n", id, text, crator);
    }

}
