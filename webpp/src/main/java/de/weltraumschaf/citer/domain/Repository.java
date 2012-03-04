package de.weltraumschaf.citer.domain;

import java.util.Map;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 * @license http://www.weltraumschaf.de/the-beer-ware-license.txt THE BEER-WARE LICENSE
 */
public interface Repository<E> {

    public E create(Map<String, Object> params);
    public E findById(String id);
    public void delete(E entity);
    public Iterable<E> getAll();

}
