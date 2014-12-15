package domain.modeles.persistance;

import java.io.Serializable;
import java.util.List;

import domain.modeles.metier.ContactGroup;

/**
 * Un DAO permettant de manipuler les groupes de contact dans la base de données.
 *
 */
public interface DAOContactGroup extends Serializable {
	/**
	 * Récupère tous les groupes de contact.
	 * 
	 * @return		les groupes de contact
	 */
	public List<ContactGroup> getContactGroups();
	
	/**
	 * Ajoute un groupe de contact.
	 * 
	 * @param group
	 * 		le groupe de contacts
	 */
	public void addContactGroup(ContactGroup group);
	
	/**
	 * Supprime un groupe existant de la base données.
	 * 
	 * @param id		l'identifiant numérique
	 */
	public void deleteContactGroup(long id);

	public ContactGroup getContactGroup(long id);

	public void modifyGroup(ContactGroup group);
}
