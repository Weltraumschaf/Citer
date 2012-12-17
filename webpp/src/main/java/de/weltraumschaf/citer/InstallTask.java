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

package de.weltraumschaf.citer;

import java.io.File;
import java.util.logging.Logger;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
class InstallTask implements Command {

    private static final Logger LOG = Logger.getLogger(InstallTask.class.getName());

    private final String homeDir;

    public InstallTask(String homeDir) {
        this.homeDir = homeDir;
    }

    @Override
    public void execute() {
        final File home = new File(homeDir);

        if (! home.exists()) {
            home.mkdirs();
            LOG.info(String.format("Home dir '%s' created.", homeDir));
        }

        if (! home.isDirectory()) {
            throw new RuntimeException(String.format("Home '%s' must be directory! ", homeDir));
        }
    }


}
