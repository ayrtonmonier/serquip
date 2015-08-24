# --------------------------------------------------------
# Host:                         127.0.0.1
# Database:                     serquip
# Server version:               4.1.11-nt
# Server OS:                    Win32
# HeidiSQL version:             5.0.0.3222
# Date/time:                    2011-02-01 20:58:10
# --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
# Dumping database structure for serquip
CREATE DATABASE IF NOT EXISTS `serquip` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `serquip`;


# Dumping structure for table serquip.cad_bairro
CREATE TABLE IF NOT EXISTS `cad_bairro` (
  `cod_bairro` int(10) unsigned NOT NULL auto_increment,
  `nome_bairro` varchar(30) default NULL,
  `data_cadastro` date default NULL,
  PRIMARY KEY  (`cod_bairro`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dumping data for table serquip.cad_bairro: 0 rows
/*!40000 ALTER TABLE `cad_bairro` DISABLE KEYS */;
/*!40000 ALTER TABLE `cad_bairro` ENABLE KEYS */;


# Dumping structure for table serquip.cad_bombona
CREATE TABLE IF NOT EXISTS `cad_bombona` (
  `num_seq_bomb` int(20) NOT NULL auto_increment,
  `cod_cli` int(11) NOT NULL default '0',
  `contrato` int(15) default NULL,
  `matricula_usuario` varchar(8) NOT NULL default '0',
  `capacidade_bomb` int(11) NOT NULL default '0',
  `peso_bruto` decimal(10,2) NOT NULL default '0.00',
  `peso_liquido` decimal(10,2) NOT NULL default '0.00',
  `peso_excedido` decimal(10,2) NOT NULL default '0.00',
  `tipo_bomb` varchar(15) default NULL,
  `data_pesagem_bomb` date default NULL,
  `turno_pesagem` varchar(20) default NULL,
  `hora_pesagem_bomb` time default NULL,
  `tipo_pesagem` varchar(10) default NULL,
  `situacao_reg` char(1) default NULL,
  PRIMARY KEY  (`num_seq_bomb`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dumping data for table serquip.cad_bombona: 0 rows
/*!40000 ALTER TABLE `cad_bombona` DISABLE KEYS */;
/*!40000 ALTER TABLE `cad_bombona` ENABLE KEYS */;


# Dumping structure for table serquip.cad_cidade
CREATE TABLE IF NOT EXISTS `cad_cidade` (
  `cod_cidade` int(10) unsigned NOT NULL auto_increment,
  `nome_cidade` varchar(30) default NULL,
  `data_cadastro` date default NULL,
  PRIMARY KEY  (`cod_cidade`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dumping data for table serquip.cad_cidade: 0 rows
/*!40000 ALTER TABLE `cad_cidade` DISABLE KEYS */;
/*!40000 ALTER TABLE `cad_cidade` ENABLE KEYS */;


# Dumping structure for table serquip.cliente
CREATE TABLE IF NOT EXISTS `cliente` (
  `cod_cli` int(11) NOT NULL auto_increment,
  `nome_cli` varchar(70) default NULL,
  `rua` varchar(60) default NULL,
  `bairro` varchar(50) default NULL,
  `cidade` varchar(50) default NULL,
  `uf` char(2) default NULL,
  `fone` varchar(15) default NULL,
  `responsavel` varchar(30) default NULL,
  `pReferencia` varchar(40) default NULL,
  `inicio_coleta` int(2) default NULL,
  `fim_coleta` int(2) default NULL,
  `obs` varchar(90) default NULL,
  `contrato_cli` int(11) NOT NULL default '0',
  `situacao` varchar(20) default NULL,
  `h_abre` varchar(15) default NULL,
  `h_fecha` varchar(15) default NULL,
  `dia_de_coleta` varchar(50) default NULL,
  `qt_bomb200` int(11) default NULL,
  `qt_bomb50` int(11) default NULL,
  `qt_bomb20` int(11) default NULL,
  `qt_bomb_total` int(11) default NULL,
  `motivo` varchar(80) default NULL,
  `dia_cansus` date default NULL,
  `dia_retorno` date default NULL,
  `data_cadastro` date default NULL,
  PRIMARY KEY  (`cod_cli`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dumping data for table serquip.cliente: 0 rows
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;

# Dumping structure for table serquip.incineracao
CREATE TABLE IF NOT EXISTS `incineracao` (
  `num_seq_incineracao` int(20) NOT NULL auto_increment,
  `matricula_usuario` varchar(8) NOT NULL default '',
  `peso_incinerado` decimal(10,2) default NULL,
  `id_maquina` int(11) NOT NULL default '0',
  `tipo_residuo` varchar(15) default NULL,
  `data_incineracao` date default NULL,
  `turno_incineracao` varchar(11) NOT NULL default '0',
  `hora_incineracao` time default NULL,
  `situacao_reg` char(1) default NULL,
  PRIMARY KEY  (`num_seq_incineracao`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dumping data for table serquip.incineracao: 0 rows
/*!40000 ALTER TABLE `incineracao` DISABLE KEYS */;
/*!40000 ALTER TABLE `incineracao` ENABLE KEYS */;


# Dumping structure for table serquip.info_suporte
CREATE TABLE IF NOT EXISTS `info_suporte` (
  `cod_suporte` int(15) unsigned NOT NULL auto_increment,
  `email_suporte` varchar(50) default NULL,
  `tel_suporte` varchar(20) default NULL,
  `data_atualizacao` date default NULL,
  `hora_atualizacao` time default NULL,
  PRIMARY KEY  (`cod_suporte`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dumping data for table serquip.info_suporte: 0 rows
/*!40000 ALTER TABLE `info_suporte` DISABLE KEYS */;
/*!40000 ALTER TABLE `info_suporte` ENABLE KEYS */;


# Dumping structure for table serquip.log_usuario
CREATE TABLE IF NOT EXISTS `log_usuario` (
  `num_log` int(20) unsigned NOT NULL auto_increment,
  `matricula_usuario` varchar(8) default NULL,
  `tipo_usuario` varchar(30) default NULL,
  `situacao_usuario` varchar(15) default NULL,
  `host_login` varchar(30) default NULL,
  `turno_login` varchar(15) default NULL,
  `data_login` date default NULL,
  `hora_login` time default NULL,
  `data_logout` date default NULL,
  `hora_logout` time default NULL,
  `situacao_logout` varchar(70) default NULL,
  PRIMARY KEY  (`num_log`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dumping data for table serquip.log_usuario: 0 rows
/*!40000 ALTER TABLE `log_usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `log_usuario` ENABLE KEYS */;


# Dumping structure for table serquip.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `cod_usuario` int(11) NOT NULL auto_increment,
  `matricula_usuario` varchar(8) NOT NULL default '0',
  `nome_usuario` varchar(30) default NULL,
  `senha_usuario` varchar(41) NOT NULL default '0',
  `data_cad_usuario` date default NULL,
  `tipo_de_usuario` varchar(20) default NULL,
  `situacao` varchar(12) default NULL,
  PRIMARY KEY  (`cod_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dumping data for table serquip.usuario: 0 rows
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`cod_usuario`, `matricula_usuario`, `nome_usuario`, `senha_usuario`, `data_cad_usuario`, `tipo_de_usuario`, `situacao`) VALUES (1, '12345678', 'Ayrton Monier', '*9129F8877CEE1030A70392933C5891E194D5C503', '2011-02-01', 'Administrador', 'Ativo');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
