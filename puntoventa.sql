-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 17-07-2014 a las 23:30:07
-- Versión del servidor: 5.6.16
-- Versión de PHP: 5.5.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `puntoventa`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE IF NOT EXISTS `clientes` (
  `Cedula` varchar(20) NOT NULL DEFAULT '',
  `Nombre` varchar(20) NOT NULL,
  `Apellido` varchar(20) NOT NULL,
  `Telefono` varchar(13) NOT NULL,
  `Direccion` varchar(30) NOT NULL,
  `CreditoCliente` tinyint(1) NOT NULL,
  PRIMARY KEY (`Cedula`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detallefactura`
--

CREATE TABLE IF NOT EXISTS `detallefactura` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NumeroFactura` int(11) NOT NULL,
  `Codigo` varchar(20) NOT NULL,
  `Valor` int(11) NOT NULL,
  `Cantidad` int(11) NOT NULL,
  `Total` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `NumeroFactura` (`NumeroFactura`,`Codigo`),
  KEY `Codigo` (`Codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `egresos`
--

CREATE TABLE IF NOT EXISTS `egresos` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Fecha` datetime NOT NULL,
  `Descripcion` text NOT NULL,
  `Valor` bigint(20) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `facturas`
--

CREATE TABLE IF NOT EXISTS `facturas` (
  `NumeroFactura` int(11) NOT NULL AUTO_INCREMENT,
  `Cedula` varchar(20) NOT NULL,
  `Fecha` datetime NOT NULL,
  `Total` bigint(20) NOT NULL,
  `CreditoFactura` tinyint(1) NOT NULL,
  PRIMARY KEY (`NumeroFactura`),
  KEY `Cedula` (`Cedula`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE IF NOT EXISTS `productos` (
  `Codigo` varchar(20) NOT NULL DEFAULT '',
  `Nombre` varchar(20) NOT NULL,
  `Precio` int(11) NOT NULL,
  `PrecioCompra` int(11) NOT NULL,
  `Cantidad` int(11) NOT NULL,
  `PrecioPromocion` int(11) NOT NULL,
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
  `Telefono` varchar(13) NOT NULL,
  `Usuario` varchar(15) NOT NULL,
  `Contrasena` varchar(15) NOT NULL,
  `Rol` varchar(10) NOT NULL,
  PRIMARY KEY (`Cedula`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `detallefactura`
--
ALTER TABLE `detallefactura`
  ADD CONSTRAINT `detallefactura_ibfk_2` FOREIGN KEY (`Codigo`) REFERENCES `productos` (`Codigo`),
  ADD CONSTRAINT `detallefactura_ibfk_1` FOREIGN KEY (`NumeroFactura`) REFERENCES `facturas` (`NumeroFactura`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `facturas`
--
ALTER TABLE `facturas`
  ADD CONSTRAINT `facturas_ibfk_1` FOREIGN KEY (`Cedula`) REFERENCES `clientes` (`Cedula`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
