package domain.controleurs;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import domain.modeles.metier.ContactGroup;

/**
 * La classe gérant le formulaire d'ajout d'un nouveau contact au répertoire.
 *
 */
@ManagedBean(name = "controlAdd")
@ViewScoped
public class ControlAdd extends ControlContact {
	private static final long serialVersionUID = 1L;

	/**
	 * Méthode d'initialisation appelée pour remplir la liste des groupes
	 */
	@PostConstruct
	public void init() {
		setGroupList(getGroupService().getContactGroups());
		for (ContactGroup group: getGroupList()) {
			getGroups().put(group, false);
		}
		addPhone();
		setError("");
	}

	/**
	 * Ajoute un contact d'après les champs du formulaire remplis par
	 * l'utilisateur.
	 * 
	 * @return la page vers laquelle renvoyer l'utilisateur
	 */
	public String addContact() {
		if (validate()) {
			/*
			 * Si aucun problème rencontré, on fait l'ajout au répertoire
			 */
			if ((getNumSiret() != null) && (getNumSiret().longValue() != 0)) {
				getService().addEntreprise(getFirstName(), getLastName(), getEmail(), getStreet(), getCity(),
						getZip(), getCountry(), getPhones(), getGroups(), getNumSiret());

			} else {
				getService().addContact(getFirstName(), getLastName(), getEmail(), getStreet(), getCity(),
						getZip(), getCountry(), getPhones(), getGroups());
			}
			return "welcome-page";
		}
		return "";
	}
}
