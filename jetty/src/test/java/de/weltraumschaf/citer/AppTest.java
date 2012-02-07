package de.weltraumschaf.citer;

import java.util.UUID;
import static org.junit.Assert.assertEquals;
import org.junit.Ignore;
import org.junit.Test;
import org.neo4j.graphdb.*;
import org.neo4j.kernel.EmbeddedGraphDatabase;

/**
 * Unit test for simple App.
 */
public class AppTest {

    enum RelationshipTypes implements RelationshipType {
        ACTS_IN, CREATED;
    };

    @Test
    public void testApp() {
        GraphDatabaseService database = new EmbeddedGraphDatabase("/tmp/citer/neo4j.db");
        Transaction tx = database.beginTx();

        try {
            Node creator;

            for (int i = 0; i < 10; ++i) {
                creator = database.createNode();
                creator.setProperty("name", "Number " + i);

                Node cite;

                for (int j = 0; j < 10; ++j) {
                    cite = database.createNode();
                    cite.setProperty("text", "Lorem ipsum dolor.");
                    Relationship created = creator.createRelationshipTo(cite, RelationshipTypes.CREATED);
                    database.index()
                            .forNodes("cites")
                            .add(cite, "id", UUID.randomUUID());
                }
            }

//            Node forrest = database.createNode();
//            forrest.setProperty("title", "Forrest Gump");
//            forrest.setProperty("year", 1994);

//            Node tom = database.createNode();
//            tom.setProperty("name", "Tom Hanks");
//
//            Relationship role = tom.createRelationshipTo(forrest, RelationshipTypes.ACTS_IN);
//            role.setProperty("role", "Forrest");
//
//            Node movie = database.index()
//                                .forNodes("movies")
//                                .get("id", 1)
//                                .getSingle();
//
//            assertEquals("Forrest Gump", movie.getProperty("title"));
//
//            for (Relationship role2 : movie.getRelationships(RelationshipTypes.ACTS_IN, Direction.INCOMING)) {
//                Node actor = role.getOtherNode(movie);
//                assertEquals("Tom Hanks", actor.getProperty("name"));
//                assertEquals("Forrest", role2.getProperty("role"));
//            }

            tx.success();
        } finally {
            tx.finish();
        }

        database.shutdown();
    }
}
