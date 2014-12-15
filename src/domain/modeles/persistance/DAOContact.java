package domain.modeles.persistance;

import java.io.Serializable;
import java.util.List;

import domain.modeles.metier.Contact;

/**
 * Un DAO permettant de manipuler la liste des contacts dans la base de données.
 *
 */
public interface DAOContact extends Serializable {

	/**
	 * Ajoute un nouveau contact à la base de données.
	 * 
	 * @param contact			le nouveau contact à ajouter
	 */
	public void addContact(Contact contact);
	
	/**
	 * Supprime un contact existant de la base données.
	 * 
	 * @param id		l'identifiant numérique
	 */
	public void deleteContact(long id);
	
	/**
	 * Modifie les coordonnées d'un contact existant dans la base de données.
	 * 	
	 * @param contact		le contact et ses nouvelles coordonnées
	 * @return				true si la mise à jour a eu lieu, false en cas d'erreur
	 */
	public void modifyContact(Contact contact);
	
	/**
	 * Récupère un contact dans la base de données à partir de son id.
	 * 
	 * @param id		l'id du contact recherché
	 * @return			le contact recherché, ou null s'il n'existe pas
	 */
	public Contact getContact(long id);
	
	/**
	 * Récupère dans la base de données la liste des contacts portant
	 * le prénom fourni.
	 * 
	 * @param firstname		le prénom recherché
	 * @return				tous les contacts portant le prénom firstName
	 */
	public List<Contact> getContactByFirstName(String firstname);
	
	/**
	 * Récupère dans la base de données la liste des contacts portant le
	 * nom de famille fourni.
	 * 
	 * @param lastname		le nom de famille recherché
	 * @return				tous les contacts portant le nom de famille lastName
	 */
	public List<Contact> getContactByLastName(String lastname);
	
	/**
	 * Récupère dans la base de données la liste des contacts utilisant
	 * l'email fourni.
	 * 
	 * @param email			l'adresse email recherchée
	 * @return				tous les contacts utilisant cet email
	 */
	public List<Contact> getContactByEmail(String email);
	
	/**
	 * Récupère dans la base de données toute la liste des contacts 
	 * 
	 * @return				tous les contacts 
	 */
	public List<Contact> getAllContacts();
	
}
