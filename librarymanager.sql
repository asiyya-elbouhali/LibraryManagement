-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : lun. 11 sep. 2023 à 18:02
-- Version du serveur : 10.4.25-MariaDB
-- Version de PHP : 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `librarymanager`
--

-- --------------------------------------------------------

--
-- Structure de la table `book`
--

CREATE TABLE `book` (
  `book_id` int(25) NOT NULL,
  `isbn` varchar(25) NOT NULL,
  `titre` varchar(25) NOT NULL,
  `auteur` varchar(25) NOT NULL,
  `etat` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `book`
--

INSERT INTO `book` (`book_id`, `isbn`, `titre`, `auteur`, `etat`) VALUES
(1, '1234', 'The secret garden', 'asiyya', 'disponible'),
(2, '456', 'L\'alchimiste', 'paulo coelo', 'emprunté'),
(3, '2', 'AZ', 'ZE', 'disponible'),
(6, '1212', 'NOW', 'HUGO', 'disponible'),
(7, '123456', 'Atomique habits', 'Mr bean', 'disponible'),
(8, '45', 'FGH', 'BN', 'emprunté');

-- --------------------------------------------------------

--
-- Structure de la table `emprunt`
--

CREATE TABLE `emprunt` (
  `emprunt_id` int(25) NOT NULL,
  `emprunteur_id` int(25) NOT NULL,
  `book_id` int(25) NOT NULL,
  `date_emprunt` varchar(25) NOT NULL,
  `date_retour` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `emprunt`
--

INSERT INTO `emprunt` (`emprunt_id`, `emprunteur_id`, `book_id`, `date_emprunt`, `date_retour`) VALUES
(44, 1, 2, '2023-09-11', '2023-09-25'),
(45, 1, 8, '2023-09-11', '2023-09-25');

--
-- Déclencheurs `emprunt`
--
DELIMITER $$
CREATE TRIGGER `emprunt` AFTER INSERT ON `emprunt` FOR EACH ROW BEGIN

UPDATE book 
SET etat='emprunté'
WHERE book_id= NEW.book_id;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `empruntDelete` BEFORE DELETE ON `emprunt` FOR EACH ROW BEGIN
UPDATE book
SET etat='disponible'
WHERE book.book_id =OLD.book_id;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `emprunteur`
--

CREATE TABLE `emprunteur` (
  `emprunteur_id` int(5) NOT NULL,
  `numero_membre` varchar(25) NOT NULL,
  `nom` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `emprunteur`
--

INSERT INTO `emprunteur` (`emprunteur_id`, `numero_membre`, `nom`) VALUES
(1, 'A1', 'asiyya'),
(2, '1234567', 'maya'),
(3, '1999', 'soukaina'),
(4, 'B2', 'hiba');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`book_id`);

--
-- Index pour la table `emprunt`
--
ALTER TABLE `emprunt`
  ADD PRIMARY KEY (`emprunt_id`),
  ADD KEY `emprunt_ibfk_1` (`book_id`),
  ADD KEY `emprunt_ibfk_2` (`emprunteur_id`);

--
-- Index pour la table `emprunteur`
--
ALTER TABLE `emprunteur`
  ADD PRIMARY KEY (`emprunteur_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `book`
--
ALTER TABLE `book`
  MODIFY `book_id` int(25) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `emprunt`
--
ALTER TABLE `emprunt`
  MODIFY `emprunt_id` int(25) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- AUTO_INCREMENT pour la table `emprunteur`
--
ALTER TABLE `emprunteur`
  MODIFY `emprunteur_id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `emprunt`
--
ALTER TABLE `emprunt`
  ADD CONSTRAINT `emprunt_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `emprunt_ibfk_2` FOREIGN KEY (`emprunteur_id`) REFERENCES `emprunteur` (`emprunteur_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
