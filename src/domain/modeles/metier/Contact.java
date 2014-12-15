package domain.modeles.metier;

import java.util.Set;

/**
 * Classe métier représentant un contact du répertoire.
 *
 */
public class Contact extends IdentifiedObject {
	private static final long serialVersionUID = 1L;

	/**
	 * Le prénom du contact
	 */
	private String firstName;
	
	/**
	 * Le nom de famille du contact
	 */
	private String lastName;
	
	/**
	 * L'adresse email du contact
	 */
	private String email;
	
	/**
	 * L'adresse du contact
	 */
	private Address address;
	
	/**
	 * Les numéros de téléphone associés à ce contact
	 */
	private Set<PhoneNumber> phones;
	
	/**
	 * Les groupes auquel appartient ce contact
	 */
	private Set<ContactGroup> books;
	
	/**
	 * Constructeur vide
	 */
	public Contact(){
	}
	
	/**
	 * Constructeur fournissant l'adresse, la liste des numéros et les groupes du contact
	 * 
	 * @param address
	 * 		l'adresse du contact
	 * @param phones
	 * 		la liste des numéros de téléphone du contact
	 * @param books
	 * 		les groupes auxquels appartient ce contact
	 */
	public Contact(Address address, Set<PhoneNumber> phones, Set<ContactGroup> books) {
		this.address = address;
		this.phones = phones;
		this.books = books;
	}
	
	/**
	 * Retourne l'adresse de ce contact.
	 * 
	 * @return	l'adresse du contact
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * Modifie l'adresse du contact.
	 * 
	 * @param add
	 * 		la nouvelle adresse
	 */
	public void setAddress(Address add) {
		this.address = add;
	}

	/**
	 * Retourne les numéros de téléphone de ce contact.
	 * 
	 * @return	les numéros de ce contact
	 */
	public Set<PhoneNumber> getPhones() {
		return phones;
	}

	/**
	 * Modifie les numéros de téléphones associés à ce contact.
	 * 
	 * @param phones
	 * 		les nouveaux numéros du contact
	 */
	public void setPhones(Set<PhoneNumber> phones) {
		this.phones = phones;
	}

	/**
	 * Retourne la liste des groupes auxquels appartient ce contact.
	 * 
	 * @return	les groupes de ce contact
	 */
	public Set<ContactGroup> getBooks() {
		return books;
	}

	/**
	 * Modifie la liste des groupes auxquels appartient ce contact.
	 * 
	 * @param books
	 * 		la nouvelle liste de groupes
	 */
	public void setBooks(Set<ContactGroup> books) {
		this.books = books;
	}

	/**
	 * Retourne l'email enregistré pour ce contact.
	 * 
	 * @return 		l'email du contact
	 */
	public String getEmail(){
		return email;
	}
	
	/**
	 * Modifie l'email de ce contact.
	 * 
	 * @param email		le nouvel email de ce contact
	 */
	public void setEmail(String email){
		this.email = email;
	}
	
	/**
	 * Retourne le prénom de ce contact.
	 * 
	 * @return 		le prénom du contact
	 */
	public String getFirstName(){
		return firstName;
	}
	
	/**
	 * Modifie le prénom de ce contact.
	 * 
	 * @param firstname		le nouveau prénom de ce contact
	 */
	public void setFirstName(String firstname){
		this.firstName = firstname;
	}
	
	/**
	 * Retourne le nom de famille de ce contact.
	 * 
	 * @return		le nom de famille du contact
	 */
	public String getLastName(){
		return lastName;
	}
	
	/**
	 * Modifie le nom de famille enregistré pour ce contact.
	 * 
	 * @param lastname		le nouveau nom de famille du contact
	 */
	public void setLastName(String lastname){
		this.lastName = lastname;
	}
	
	@Override
	public String toString() {
		return "Contact " + getFirstName() + " " + getLastName();
	}
	
}
