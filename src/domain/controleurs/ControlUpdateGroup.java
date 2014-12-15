package domain.controleurs;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import domain.modeles.metier.ContactGroup;
import domain.modeles.service.ContactGroupService;

@ManagedBean(name = "controlGroupUpdate")
@ViewScoped
public class ControlUpdateGroup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2553697604731712046L;

	@ManagedProperty(value = "#{ContactGroupService}")
	private ContactGroupService service;
	
	private String groupName;
	
	private ContactGroup group;
	
	/**
	 * Le message d'erreur affiché
	 */
	private String error;
	
	/**
	 * Indique si le formulaire doit être affiché
	 */
	private boolean displayForm;
	
	

	@PostConstruct
	private void init() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String groupStr_ID = (String) facesContext.getExternalContext()
				.getRequestParameterMap().get("groupId");
		group = null;
		long id = -1;
		setError("");
		try {
			id = Long.parseLong(groupStr_ID);
		} catch (NumberFormatException e) {}
		

		if (id != -1) {
			 group = service.getContactGroup(id);
		}
		
		if (group != null) {
			 groupName = group.getGroupName();
			 setDisplayForm(true);
		}
		else {
			setError("The contact group is not exists.");
			setDisplayForm(false);
		}
	}
	
	
	public String update() {
		setError("");
		if(!groupName.equals("")){
			
			if (service.modifyGroup(group, groupName)) {
				return "manageGroups";
			}
		}
		setError("Le nom de groupe doit faire au moins 2 caractères");
		return "";
	}

	public ContactGroup getGroup() {
		return group;
	}

	public void setGroup(ContactGroup group) {
		this.group = group;
	}


	public String getGroupName() {
		return groupName;
	}


	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * @return	le message d'erreur actuellement affichél
	 */
	public String getError() {
		return error;
	}

	/**
	 * Met à jour le message d'erreur si l'utilisateur fait une erreur de saisie
	 * 
	 * @param error
	 * 		le nouveau message d'erreur
	 */
	public void setError(String error) {
		this.error = error;
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

}
