package fr.tp4.dao;

import java.util.List;

import fr.tp4.beans.Commande;

public interface CommandeDao {
	void creer(Commande commande) throws DAOException;

	Commande trouver(long id) throws DAOException;

	List<Commande> lister() throws DAOException;

	void supprimer(Commande commande) throws DAOException;
}