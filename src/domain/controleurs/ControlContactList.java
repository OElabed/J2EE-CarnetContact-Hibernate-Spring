package domain.controleurs;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import domain.modeles.metier.Contact;
import domain.modeles.service.ContactService;

/**
 * Un formulaire affichant la liste des contacts.
 * 
 * Ce menu permet de modifier ou supprimer des contacts existants.
 *
 */
@ManagedBean
public class ControlContactList implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * La liste de contacts affichée
	 */
	private List<Contact> list;

	/**
	 * L'objet permettant l'accès à la couche service
	 */
	@ManagedProperty(value = "#{ContactService}")
	private ContactService service;

	/**
	 * Consulte la base de données pour récupérer la liste des contacts à afficher
	 */
	@PostConstruct
	private void findContact() {
		list = (List<Contact>) service.getContactAll();
	}

	/**
	 * Supprime le contact sélectionné de la couche service, puis met à jour la liste de contacts affichée.
	 * 
	 * @return	la page vers laquelle rediriger l'utilisateur
	 */
	public String delete(long id) {
		service.deleteContact(id);
		/*for (Contact contact: list) {
			if (contact.getId() == id) {
				list.remove(contact);
				break;
			}
		}*/
		list = (List<Contact>) service.getContactAll();
		return "welcome-page";
	}

	/**
	 * @return	la liste des contacts affichés
	 */
	public List<Contact> getList() {
		return list;
	}

	/**
	 * Modifie la liste des contacts affichés
	 * 
	 * @param list
	 * 		la nouvelle liste de contacts à afficher
	 */
	public void setList(List<Contact> list) {
		this.list = list;
	}

	/**
	 * @return	la couche service
	 */
	public ContactService getService() {
		return service;
	}

	/**
	 * Fournit la couche service à utiliser
	 * 
	 * @param service
	 * 		l'objet de la couche service à utiliser
	 */
	public void setService(ContactService service) {
		this.service = service;
	}
}
