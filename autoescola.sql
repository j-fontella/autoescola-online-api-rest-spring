DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `prk` int NOT NULL AUTO_INCREMENT,
  `usuario` varchar(25) NOT NULL,
  `senha` varchar(25) NOT NULL,
  PRIMARY KEY (`prk`)
);


DROP TABLE IF EXISTS `turma`;
CREATE TABLE `turma` (
  `prk` int NOT NULL AUTO_INCREMENT,
  `vagas` int NOT NULL,
  `disciplina` varchar(40) NOT NULL,
  PRIMARY KEY (`prk`)
);


DROP TABLE IF EXISTS `alunocadastradoturma`;
CREATE TABLE `alunocadastradoturma` (
  `frkUsuario` int NOT NULL,
  `frkTurma` int NOT NULL,
  KEY `frkUsuario` (`frkUsuario`),
  KEY `frkTurma` (`frkTurma`),
  CONSTRAINT `alunocadastradoturma_ibfk_1` FOREIGN KEY (`frkUsuario`) REFERENCES `usuario` (`prk`),
  CONSTRAINT `alunocadastradoturma_ibfk_2` FOREIGN KEY (`frkTurma`) REFERENCES `turma` (`prk`)
);

