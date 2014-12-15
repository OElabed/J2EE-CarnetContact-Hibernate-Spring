package util;


/**
 * Le gestionnaire de logs
 *
 */
public class Log {
	/**
	 * Affiche un message signalant le début de l'enregistrement d'un nouvel objet
	 * 
	 * @param obj
	 * 		l'objet sauvegardé
	 */
	public void logSaveStart(Object obj) {
		System.out.println("------------------------------------------- SAVE BEGIN: " + obj);
	}
	
	/**
	 * Affiche un message signalant la fin de l'enregistrement d'un nouvel objet
	 * 
	 * @param obj
	 * 		l'objet sauvegardé
	 */
	public void logSaveEnd(Object obj) {
		System.out.println("------------------------------------------- SAVE END: " + obj);
	}
	
	/**
	 * Affiche un message signalant le début de la suppression d'un objet
	 * 
	 * @param obj
	 * 		l'objet supprimé
	 */
	public void logDeleteStart(Object obj) {
		System.out.println("------------------------------------------- DELETE START: " + obj);		
	}
	
	/**
	 * Affiche un message signalant la fin de la suppression d'un objet
	 * 
	 * @param obj
	 * 		l'objet supprimé
	 */
	public void logDeleteEnd(Object obj) {
		System.out.println("------------------------------------------- DELETE END: " + obj);		
	}
	
	/**
	 * Affiche un message signalant le début de la modification d'un objet
	 * 
	 * @param obj
	 * 		l'objet modifié
	 */
	public void logUpdateStart(Object obj) {
		System.out.println("------------------------------------------- UPDATE START: " + obj);			
	}
	
	/**
	 * Affiche un message signalant la fin de la modification d'un objet
	 * 
	 * @param obj
	 * 		l'objet modifié
	 */
	public void logUpdateEnd(Object obj) {
		System.out.println("------------------------------------------- UPDATE END: " + obj);			
	}
}
