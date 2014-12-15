package domain.controleurs;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import domain.modeles.metier.Contact;

/**
 * La classe gérant le formulaire de recherche de contacts dans le répertoire.
 *
 */
@ManagedBean(name = "controlSearch")
public class ControlSearch extends ControlContactList {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Le type de recherche (par id, prénom, nom, ou email)
	 */
	private String searchType = "id";
	
	/**
	 * La valeur recherchée
	 */
	private String searchValue = "";
	
	
	private ArrayList<Contact> searchList;

	/**
	 * Recherche des contacts suivant les paramètres fournis par l'utilisateur, puis met à jour
	 * la liste de résultats affichée.
	 * 
	 * @return
	 * 		la page vers laquelle rediriger l'utilisateur
	 */
	
	public String searchContact() {
		searchList = new ArrayList<Contact>();
		switch (searchType) {
		case "id":

			long id = -1;
			try {
				id = Long.parseLong(searchValue);
			}
			catch (NumberFormatException e) {}
			Contact contact = getService().getContact(id);
			if (contact != null) {
				searchList.add(contact);
			}
			break;
		case "firstName":
			List<Contact> temps= getService().getContactByFirstName(searchValue);
			for(Contact contTemp :temps ){
				if(contTemp != null){
					searchList.add(contTemp);
				}
			}
			
			break;
		case "lastName":
			List<Contact> temps2= getService().getContactByLastName(searchValue);
			for(Contact contTemp :temps2 ){
				if(contTemp != null){
					searchList.add(contTemp);
				}
			}
			break;
		default:
			List<Contact> temps3= getService().getContactByEmail(searchValue);
			for(Contact contTemp :temps3 ){
				if(contTemp != null){
					searchList.add(contTemp);
				}
			}
			
		}
	
		return "searchContact";
	}
	
	public ArrayList<Contact> getSearchList() {
		return searchList;
	}

	public void setSearchList(ArrayList<Contact> searchList) {
		this.searchList = searchList;
	}

	/**
	 * @return	le type de recherche effectuée
	 */
	public String getSearchType() {
		return searchType;
	}

	/**
	 * Modifie le type de recherche
	 * 
	 * @param searchType
	 * 		le nouveau type de recherche
	 */
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	/**
	 * @return	la valeur recherchée
	 */
	public String getSearchValue() {
		return searchValue;
	}

	/**
	 * Modifie la valeur recherchée
	 * 
	 * @param searchValue
	 * 		la nouvelle valeur recherchée
	 */
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

}
