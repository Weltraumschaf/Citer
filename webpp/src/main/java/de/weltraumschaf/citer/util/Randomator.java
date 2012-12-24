/*
 *  LICENSE
 *
 * "THE BEER-WARE LICENSE" (Revision 43):
 * "Sven Strittmatter" <weltraumschaf@googlemail.com> wrote this file.
 * As long as you retain this notice you can do whatever you want with
 * this stuff. If we meet some day, and you think this stuff is worth it,
 * you can buy me a non alcohol-free beer in return.
 *
 * Copyright (C) 2012 "Sven Strittmatter" <weltraumschaf@googlemail.com>
 */

package de.weltraumschaf.citer.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import de.weltraumschaf.citer.domain.Cite;
import de.weltraumschaf.citer.domain.CiteRepository;
import de.weltraumschaf.citer.domain.Originator;
import de.weltraumschaf.citer.domain.OriginatorRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class Randomator {

    private static final Random RANDOM = new Random();
    private final CiteRepository citeRepo;
    private final OriginatorRepository originatorRepo;

    public Randomator(final CiteRepository citeRepo, final OriginatorRepository originatorRepo) {
        this.citeRepo = citeRepo;
        this.originatorRepo = originatorRepo;
    }

    public List<Cite> createCites(int cnt) throws Exception {
        final List<Cite> cites = Lists.newArrayList();

        for (int i = 0; i < cnt; ++i) {
            final Cite cite = citeRepo.create(createRandomParams());
            cite.setOriginator(getRandomOriginator());
            cites.add(cite);
        }

        return cites;
    }

    private Map<String, Object> createRandomParams() {
        final Map<String, Object> params  = new HashMap<String, Object>();
        params.put(Cite.TEXT, createText());
        return params;
    }

    private static final List<String> NAMES = Lists.newArrayList("Hans Dampf", "Bernd Müller", "Hildegard Munzinger");

    private List<Originator> createOriginators() throws Exception {
        List<Originator> originators = Lists.newArrayList(originatorRepo.getAll());

        if (! originators.isEmpty()) {
            return originators;
        }

        originators = Lists.newArrayList();
        for (final String name : NAMES) {
            final Map<String, Object> originatorParams  = Maps.newHashMap();
            originatorParams.put(Originator.NAME, name);
            originators.add(originatorRepo.create(originatorParams));
        }
        return originators;
    }

    private String createText() {
        return "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam";
    }

    private Originator getRandomOriginator() throws Exception {
        final List<Originator> originators = createOriginators();
        final int index = RANDOM.nextInt(originators.size());
        return originators.get(index);
    }

}
