/*
 * LICENSE
 *
 * "THE BEER-WARE LICENSE" (Revision 43):
 * "Sven Strittmatter" <weltraumschaf(at)googlemail(dot)com> wrote this file.
 * As long as you retain this notice you can do whatever you want with
 * this stuff. If we meet some day, and you think this stuff is worth it,
 * you can buy me a non alcohol-free beer in return.
 *
 * Copyright (C) 2012 "Sven Strittmatter" <weltraumschaf(at)googlemail(dot)com>
 */
package de.weltraumschaf.citer.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.neo4j.graphdb.*;
import org.neo4j.graphdb.index.Index;
import org.neo4j.kernel.EmbeddedGraphDatabase;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class AdminServlet extends HttpServlet {

    private static final String USERNAME_KEY = "username";

    private static enum RelTypes implements RelationshipType {
        USERS_REFERENCE, USER
    }

    private static String idToUserName(final int id) {
        return "user" + id + "@neo4j.org";
    }

    private static Node createAndIndexUser(final String username, final GraphDatabaseService graphDb, final Index<Node> nodeIndex) {
        Node node = graphDb.createNode();
        node.setProperty(USERNAME_KEY, username);
        nodeIndex.add(node, USERNAME_KEY, username);
        return node;
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        out.println("ADMIN");
        foo(out);
        out.close();
    }

    private void foo(PrintWriter out) {
        GraphDatabaseService graphDb;
        graphDb = new EmbeddedGraphDatabase("/tmp/citer/neo4j.db");
        Index<Node> nodeIndex = graphDb.index().forNodes("nodes");
        generateUsers(graphDb, nodeIndex);
        findUser(45, nodeIndex, out);
        graphDb.shutdown();
    }

    private void generateUsers(GraphDatabaseService graphDb, Index<Node> nodeIndex) {
        Transaction tx = graphDb.beginTx();

        try {
            // Create users sub reference node (see design guidelines on // http://wiki.neo4j.org/ )
            Node usersReferenceNode = graphDb.createNode();
            // Create some users and index their names with the IndexService
            graphDb.getReferenceNode().createRelationshipTo(usersReferenceNode, RelTypes.USERS_REFERENCE );

            for ( int id = 0; id < 100; id++ ) {
                Node userNode = createAndIndexUser(idToUserName(id), graphDb, nodeIndex);
                usersReferenceNode.createRelationshipTo(userNode, RelTypes.USER);
            }

            tx.success();
        } finally {
            tx.finish();
        }
    }

    private void findUser(int id, Index<Node> nodeIndex,PrintWriter out) {
        Node foundUser = nodeIndex.get(USERNAME_KEY, idToUserName(id)).getSingle();
        out.println("The username of user " + id + " is " + foundUser.getProperty(USERNAME_KEY));
    }


}
