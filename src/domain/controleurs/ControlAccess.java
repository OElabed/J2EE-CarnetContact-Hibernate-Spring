package domain.controleurs;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

/**
 * La classe gérant le formulaire de connexion à l'application.
 * 
 * Il s'agit d'une authentification simplifiée qui se 
 * contente de vérifier que le nom d'utilisateur est identique au mot de passe.
 *
 */
/*annotation qui déclare le Bean à JSF. 
 Cela évite de le faire dans le fichier faces-config.xml
 il est possible de rajouter l'attribut "name" afin de donner un nom d'alias au Bean. exp @ManagedBean(name="control")
 Il sera référencé dans le form login.xhtml à travers ce nom d'alias. 
 Autrement, il sera référencé par controlAccess (le c en miniscule)*/
@ManagedBean(name="control")
public class ControlAccess implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Le message d'erreur actuellement affiché
	 */
	private String error;

	/**
	 * Le contenu champ "Nom d'utilisateur"
	 */
	private String login;

	/**
	 * Le contenu du champ "Mot de passe"
	 */
	private String password;

	/**
	 * @return 	le login entré par l'utilisateur
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Met à jour le login lorsque l'utilisateur modifie le champ texte
	 * 
	 * @param login
	 * 		le nouveau login
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return 	le mot de passe entré par l'utilisateur
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Met à jour le mot de passe lorsque l'utilisateur modifie le champ texte
	 * 
	 * @param password
	 * 		le nouveau mot de passe
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * Connecte l'utilisateur si le login et le mot de passe renseignés sont bons.
	 * 
	 * @return
	 * 		la page vers laquelle renvoyer l'utilisateur
	 */
	/* Methode Action Controller qui sera référencée dans le formulaire
	 de la page login.xhtml et appelée par JSF lors de la soumission du
	 formulaire */
	public String checkAccess() {

		if (login.equals(password)) {
			/* si le pass est correct,
			JSF forwadera l'exécution vers la page welcome-page.xhtml */
			return "welcome-page";
		}
		else {
			error = "Mot de passe incorrect";
		}
		return "login";
	}
}