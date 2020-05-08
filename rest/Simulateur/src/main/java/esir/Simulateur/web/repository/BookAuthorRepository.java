package esir.Simulateur.web.repository;

import esir.Simulateur.support.jpa.CustomJpaRepository;
import esir.Simulateur.web.entity.Author;
import esir.Simulateur.web.entity.Book;
import esir.Simulateur.web.entity.BookAuthor;

/**
 * <b>BookAuthor Repository</b><br>
 * You can use NamedQuery or Query annotation here.<br>
 * 
 * 
 * @author Wenbo Wang (jackie-1685@163.com)
 */
public interface BookAuthorRepository extends CustomJpaRepository<BookAuthor, Long> {

	public BookAuthor findByBookAndAuthor(Book book, Author author);

	public void deleteByAuthor(Author author);

	public void deleteByBook(Book book);
}
