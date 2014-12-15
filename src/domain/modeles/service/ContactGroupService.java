package domain.modeles.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import util.SpringUtil;
import domain.modeles.metier.ContactGroup;
import domain.modeles.persistance.DAOContactGroup;

/**
 * Le gestionnaire de groupes de contacts d&ans le répertoire.
 * 
 * @see	ContactGroup
 *
 */
public class ContactGroupService implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Spring application context
	 */
	private transient ApplicationContext context;

	/**
	 * La couche persistance
	 */
	private transient DAOContactGroup dao;

	/**
	 * Constructeur vide
	 */
	public ContactGroupService() {
		context = new ClassPathXmlApplicationContext(SpringUtil.CONTEXT_PATH);
		dao = (DAOContactGroup) context.getBean("DAOContactGroup");
	}

	/**
	 * Récupère tous les groupes de contact.
	 * 
	 * @return		les groupes de contact
	 */
	public List<ContactGroup> getContactGroups() {
		List<ContactGroup> result = dao.getContactGroups();

		/* Ajout des groupes par défaut à la première lecture d'une base vide */
		if (result.isEmpty()) {
			for (String group: ContactGroup.DEFAULTGROUPS) {
				addContactGroup(group);
			}
			result = dao.getContactGroups();
		}
		return result;
	}

	/**
	 * Ajoute un groupe de contact.
	 * 
	 * @param name
	 * 		le nom du groupe
	 */
	public ContactGroup addContactGroup(String name) {
		ContactGroup group = (ContactGroup) context.getBean("ContactGroup");
		if (updateGroup(group, name)) {
			dao.addContactGroup(group);
			return group;
		}
		else {
			return null;
		}
	}

	private boolean updateGroup(ContactGroup group, String name) {
		/* Les noms de groupes sont longs d'au moins deux caractères */
		if (name.length() < 2) {
			return false;
		}

		/* Les noms de groupe commencent par une majuscule */
		String groupName = name.substring(0, 1).toUpperCase() + name.substring(1);

		List<ContactGroup> groups = dao.getContactGroups();
		for (ContactGroup temp: groups) {
			if (temp.getGroupName().equals(groupName)) {
				return false;
			}
		}

		group.setGroupName(groupName);
		return true;
	}

	/**
	 * Supprime un groupe du répertoire.
	 * 
	 * @param idGroup
	 *            l'identifiant numérique du groupe à supprimer
	 */
	public void deleteContactGroup(long idGroup) {
		dao.deleteContactGroup(idGroup);
	}

	public ContactGroup getContactGroup(long id) {
		return dao.getContactGroup(id);
	}

	public boolean modifyGroup(ContactGroup group, String groupName) {
		boolean success = updateGroup(group, groupName);
		if (success) {
			dao.modifyGroup(group);
		}
		return success;
	}

}
