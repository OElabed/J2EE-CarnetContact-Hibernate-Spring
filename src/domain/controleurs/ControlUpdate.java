package domain.controleurs;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import domain.modeles.metier.Contact;
import domain.modeles.metier.ContactGroup;
import domain.modeles.metier.Entreprise;

/**
 * Le formulaire de modification d'un contact existant.
 *
 */
@ManagedBean(name = "controlUpdate")
@ViewScoped
public class ControlUpdate extends ControlContact {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Le contact sélectionné pour édition
	 */
	private Contact contact;
	
	/**
	 * Indique si le contact est une entreprise
	 */
	private boolean entreprise;
	
	/**
	 * Indique si le formulaire doit être affiché
	 */
	private boolean displayForm;

	/**
	 * Récupère les données du contact dans la couche métier et préremplit les champs
	 */
	@PostConstruct
	private void init() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String contactStr_ID = (String) facesContext.getExternalContext()
				.getRequestParameterMap().get("contactId");
		contact = null;
		long id = -1;
		try {
			id = Long.parseLong(contactStr_ID);
		} catch (NumberFormatException e) {}
		
		if (id != -1) {
			contact = getService().getContact(id);
		}
		
		/* Le contact a été trouvé: on peut afficher et préremplir les champs */
		if (contact != null) {
	
			setEntreprise(false);
			setFirstName(contact.getFirstName());
			setLastName(contact.getLastName());
			setEmail(contact.getEmail());
			setStreet(contact.getAddress().getStreet());
			setCity(contact.getAddress().getCity());
			setZip(contact.getAddress().getZip());
			setCountry(contact.getAddress().getCountry());
			if (contact instanceof Entreprise) {
				Entreprise ent = (Entreprise) contact;
				setNumSiret(ent.getNumSiret());
				setEntreprise(true);
			}

			setPhones(new ArrayList<>(contact.getPhones()));

			setGroupList(getGroupService().getContactGroups());
			for (ContactGroup group: getGroupList()) {
				getGroups().put(group, contact.getBooks().contains(group));
			}
			setError("");
			setDisplayForm(true);
		}
		/* Le contact est introuvable: message d'erreur */
		else {
			setError("This contact does not exist");
			setDisplayForm(false);
		}

	}
	
	/**
	 * Met à jour les données du contact dans la couche service avec les valeurs entrées par l'utilisateur.
	 * 
	 * @return	la page vers laquelle rediriger l'utilisateur
	 */
	public String update() {
		if (validate()) {
			/* On ne peut pas transformer un contact simple en entreprise */
			if (!isEntreprise()) {
				setNumSiret(0L);
			}
			
			/* Une entreprise doit avoir un numéro siret */
			if ((isEntreprise()) && ((getNumSiret() == null) || (getNumSiret().longValue() == 0))) {
				setError("A company must have a Siret number.");
			} else {
				/*
				 * Finalement si aucun problème rencontré, on fait la modification dans
				 * le répertoire
				 */
				if (getService().modifyContact(contact, getFirstName(), getLastName(), getEmail(), getStreet(), getCity(),
						getZip(), getCountry(), getPhones(), getGroups(), getNumSiret())) {
					return "welcome-page";
				}
				/* En cas de modification concurrente, on informe l'utilisateur */
				setError("The modified contact is outdated. Thank you reload the page before making any changes.");
			}
		}
		return "";
	}

	/**
	 * @return	true si le contact lié à ce formulaire est une entreprise, false sinon
	 */
	public boolean isEntreprise() {
		return entreprise;
	}

	/**
	 * Définit le contact comme une entreprise ou un contact simple
	 * 
	 * @param entreprise
	 * 		true si le contact est une entreprise, false sinon
	 */
	public void setEntreprise(boolean entreprise) {
		this.entreprise = entreprise;
	}

	/**
	 * @return	true si le formulaire doit être affiché, false sinon
	 */
	public boolean isDisplayForm() {
		return displayForm;
	}

	/**
	 * Indique si le formulaire doit être affiché
	 * 
	 * @param displayForm
	 * 		true si le formulaire doit être affiché, false sinon
	 */
	public void setDisplayForm(boolean displayForm) {
		this.displayForm = displayForm;
	}

}
