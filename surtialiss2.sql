-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 12-07-2014 a las 00:34:02
-- Versión del servidor: 5.6.16
-- Versión de PHP: 5.5.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `surtialiss2`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE IF NOT EXISTS `productos` (
  `Codigo` varchar(20) NOT NULL DEFAULT '',
  `Descripcion` varchar(20) NOT NULL,
  `Precio` int(11) NOT NULL,
  `PrecioCompra` int(11) NOT NULL,
  `Cantidad` int(11) NOT NULL,
  `Jueves` tinyint(1) NOT NULL,
  PRIMARY KEY (`Codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE IF NOT EXISTS `usuarios` (
  `Cedula` varchar(20) NOT NULL DEFAULT '',
  `Nombre` varchar(20) NOT NULL,
  `Apellido` varchar(20) NOT NULL,
  `Telefono` varchar(20) NOT NULL,
  `Usuario` varchar(20) NOT NULL,
  `Contrasena` varchar(15) NOT NULL,
  `Rol` varchar(10) NOT NULL,
  PRIMARY KEY (`Cedula`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`Cedula`, `Nombre`, `Apellido`, `Telefono`, `Usuario`, `Contrasena`, `Rol`) VALUES
('1069748845', 'Edixon', 'Hernandez', '3134898669', 'admin', '123456', 'admin');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
