package fr.tp4.dao;

import java.util.List;

import fr.tp4.beans.Client;

public interface ClientDao {
	void creer(Client client) throws DAOException;

	Client trouver(long id) throws DAOException;

	List<Client> lister() throws DAOException;

	void supprimer(Client client) throws DAOException;
}