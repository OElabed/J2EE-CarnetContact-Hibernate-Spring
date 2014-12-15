package domain.controleurs;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import domain.modeles.metier.ContactGroup;
import domain.modeles.service.ContactGroupService;

/**
 * La classe gérant le formulaire de gestion des groupes.
 *
 */
@ManagedBean(name="controlGroups")
public class ControlGroups implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * L'objet permettant l'accès à la couche service
	 */
	@ManagedProperty(value = "#{ContactGroupService}")
	private ContactGroupService service;
	
	/**
	 * Le nom du nouveau groupe à ajouter.
	 */
	private String newGroupName;
	
	/**
	 * La liste des groupes
	 */
	private List<ContactGroup> groupList;
	
	/**
	 * Méthode d'initialisation appelée pour remplir la liste des groupes
	 */
	@PostConstruct
	public void init() {
		groupList = service.getContactGroups();
	}
	
	/**
	 * Ajoute un nouveau groupe d'utilisateurs au répertoire.
	 * 
	 * @return
	 * 		la page vers laquelle rediriger l'utilisateur
	 */
	public String addGroup() {
		service.addContactGroup(newGroupName);
		groupList = service.getContactGroups();
		return "manageGroups";
	}

	/**
	 * Supprime le groupe sélectionné de la couche service, puis met à jour la liste de groupes affichée.
	 * 
	 * @return	la page vers laquelle rediriger l'utilisateur
	 */
	public String delete(long groupId) {
		service.deleteContactGroup(groupId);
		groupList = service.getContactGroups();
		return "manageGroups";
	}

	/**
	 * @return	la couche service pour les groupes de contact
	 */
	public ContactGroupService getService() {
		return service;
	}

	/**
	 * Fournit la couche service à utiliser pour les groupes de contact
	 * 
	 * @param service
	 * 		l'objet de la couche service à utiliser
	 */
	public void setService(ContactGroupService groupService) {
		service = groupService;
	}

	/**
	 * @return	le nom du nouveau groupe à ajouter
	 */
	public String getNewGroupName() {
		return newGroupName;
	}

	/**
	 * Modifie le nom du groupe à ajouter
	 * 
	 * @param newGroupName
	 * 		le nouveau nom de groupe
	 */
	public void setNewGroupName(String newGroupName) {
		this.newGroupName = newGroupName;
	}

	/**
	 * @return	la liste des groupes de contacts
	 */
	public List<ContactGroup> getGroupList() {
		return groupList;
	}

	/**
	 * Modifie la liste des groupes de contacts
	 * 
	 * @param groupList
	 * 		la nouvelle liste
	 */
	public void setGroupList(List<ContactGroup> groupList) {
		this.groupList = groupList;
	}
}
