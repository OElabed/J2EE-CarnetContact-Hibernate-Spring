package domain.modeles.metier;

import java.util.HashSet;
import java.util.Set;

/**
 * Une classe métier représentant un groupe de contacts.
 *
 */
public class ContactGroup extends IdentifiedObject {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Les groupes d'utilisateurs par défaut
	 */
	public static final String[] DEFAULTGROUPS = {"Famille", "Travail", "Amis"};
	
	/**
	 * Le nom du groupe
	 */
	private String groupName;
	
	/**
	 * Les contacts membres de ce groupe.
	 */
	private Set<Contact> contacts;
	
	/**
	 * Constructeur vide
	 */
	public ContactGroup() {
		contacts = new HashSet<>();		
	}

	/**
	 * Retourne le nom du groupe.
	 * 
	 * @return	le nom du groupe
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * Modifie le nom du groupe.
	 * 
	 * @param groupName
	 * 		le nouveau nom du groupe
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * Retourne la liste des contacts de ce groupe.
	 * 
	 * @return	tous les contacts enregistrés dans ce groupe
	 */
	public Set<Contact> getContacts() {
		return contacts;
	}

	/**
	 * Modifie la liste des contacts enregistrés.
	 * 
	 * @param contacts
	 * 		la nouvelle liste de contacts
	 */
	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}
	
	@Override
	public String toString() {
		return "ContactGroup " + getGroupName();
	}
}
