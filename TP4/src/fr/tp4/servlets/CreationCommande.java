package fr.tp4.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.tp4.beans.Client;
import fr.tp4.beans.Commande;
import fr.tp4.forms.FormCommande;

public class CreationCommande extends HttpServlet {

	public static final String CHEMIN = "chemin";
	public static final String ATT_COMMANDE = "commande";
	public static final String ATT_FORM = "form";
	public static final String SESSION_CLIENTS = "clients";
	public static final String SESSION_COMMANDES = "commandes";

	public static final String VUE_SUCCES = "/WEB-INF/afficherCommande.jsp";
	public static final String VUE_FORM = "/WEB-INF/creerCommande.jsp";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* À la réception d'une requête GET, simple affichage du formulaire */
		this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String chemin = this.getServletConfig().getInitParameter(CHEMIN);
		/* Préparation de l'objet formulaire */
		FormCommande form = new FormCommande();

		/* Traitement de la requête et récupération du bean en résultant */
		Commande commande = form.creationCommande(request, chemin);

		/* Ajout du bean et de l'objet métier à l'objet requête */
		request.setAttribute(ATT_COMMANDE, commande);
		request.setAttribute(ATT_FORM, form);

		/* Si aucune erreur */
		if (form.getErreurs().isEmpty()) {
			/* Alors récupération de la map des clients dans la session */
			HttpSession session = request.getSession();
			Map<String, Client> clients = (HashMap<String, Client>) session.getAttribute(SESSION_CLIENTS);
			/* Si aucune map n'existe, alors initialisation d'une nouvelle map */
			if (clients == null) {
				clients = new HashMap<String, Client>();
			}
			/* Puis ajout du client de la commande courante dans la map */
			clients.put(commande.getClient().getNom(), commande.getClient());
			/* Et enfin (ré)enregistrement de la map en session */
			session.setAttribute(SESSION_CLIENTS, clients);

			/* Ensuite récupération de la map des commandes dans la session */
			Map<String, Commande> commandes = (HashMap<String, Commande>) session.getAttribute(SESSION_COMMANDES);
			/* Si aucune map n'existe, alors initialisation d'une nouvelle map */
			if (commandes == null) {
				commandes = new HashMap<String, Commande>();
			}
			/* Puis ajout de la commande courante dans la map */
			commandes.put(commande.getDate(), commande);
			/* Et enfin (ré)enregistrement de la map en session */
			session.setAttribute(SESSION_COMMANDES, commandes);

			/* Affichage de la fiche récapitulative */
			this.getServletContext().getRequestDispatcher(VUE_SUCCES).forward(request, response);
		} else {
			/* Sinon, ré-affichage du formulaire de création avec les erreurs */
			this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
		}
	}
}
