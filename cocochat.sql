-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 20-05-2019 a las 01:15:01
-- Versión del servidor: 10.1.38-MariaDB
-- Versión de PHP: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `cocochat`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `amigos`
--

CREATE TABLE `amigos` (
  `idAmistad` int(11) NOT NULL,
  `idUsuario` int(11) NOT NULL,
  `idAmigo` int(11) NOT NULL,
  `apodo` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `amigos`
--

INSERT INTO `amigos` (`idAmistad`, `idUsuario`, `idAmigo`, `apodo`) VALUES
(1, 1, 2, 'amiguis'),
(2, 3, 1, 'amigui1'),
(5, 3, 2, 'amigui2'),
(6, 2, 3, 'esau');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `grupos`
--

CREATE TABLE `grupos` (
  `idGrupo` int(11) NOT NULL,
  `nombre` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `grupos`
--

INSERT INTO `grupos` (`idGrupo`, `nombre`) VALUES
(1, 'Grupo 1'),
(2, 'Grupo 2'),
(3, ''),
(4, 'asdfas');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mensajesamigos`
--

CREATE TABLE `mensajesamigos` (
  `idMensaje` int(11) NOT NULL,
  `idAmistad` int(11) NOT NULL,
  `mensaje` mediumtext COLLATE utf8_unicode_ci NOT NULL,
  `timestamp` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mensajesgrupos`
--

CREATE TABLE `mensajesgrupos` (
  `idMensaje` int(11) NOT NULL,
  `idGrupo` int(11) NOT NULL,
  `mensaje` mediumtext COLLATE utf8_unicode_ci NOT NULL,
  `timestamp` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `solicitud`
--

CREATE TABLE `solicitud` (
  `idSolicitud` int(11) NOT NULL,
  `nombreUsuario` varchar(50) NOT NULL,
  `nombreAmigo` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL,
  `username` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `nombre` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`idUsuario`, `username`, `password`, `nombre`) VALUES
(1, 'efra', '123456', 'Efrain '),
(2, '1', '1', '1'),
(3, 'H3lltronik', '123', 'esau');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuariosgrupo`
--

CREATE TABLE `usuariosgrupo` (
  `idRelacion` int(11) NOT NULL,
  `idUsuario` int(11) NOT NULL,
  `idGrupo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `amigos`
--
ALTER TABLE `amigos`
  ADD PRIMARY KEY (`idAmistad`),
  ADD KEY `idUsuario` (`idUsuario`),
  ADD KEY `idAmigo` (`idAmigo`);

--
-- Indices de la tabla `grupos`
--
ALTER TABLE `grupos`
  ADD PRIMARY KEY (`idGrupo`);

--
-- Indices de la tabla `mensajesamigos`
--
ALTER TABLE `mensajesamigos`
  ADD PRIMARY KEY (`idMensaje`),
  ADD KEY `idAmistad` (`idAmistad`);

--
-- Indices de la tabla `mensajesgrupos`
--
ALTER TABLE `mensajesgrupos`
  ADD PRIMARY KEY (`idMensaje`),
  ADD KEY `idGrupo` (`idGrupo`);

--
-- Indices de la tabla `solicitud`
--
ALTER TABLE `solicitud`
  ADD PRIMARY KEY (`idSolicitud`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`idUsuario`),
  ADD KEY `username` (`username`);

--
-- Indices de la tabla `usuariosgrupo`
--
ALTER TABLE `usuariosgrupo`
  ADD PRIMARY KEY (`idRelacion`),
  ADD KEY `idGrupo` (`idGrupo`),
  ADD KEY `idUsuario` (`idUsuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `amigos`
--
ALTER TABLE `amigos`
  MODIFY `idAmistad` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `grupos`
--
ALTER TABLE `grupos`
  MODIFY `idGrupo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `mensajesamigos`
--
ALTER TABLE `mensajesamigos`
  MODIFY `idMensaje` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `mensajesgrupos`
--
ALTER TABLE `mensajesgrupos`
  MODIFY `idMensaje` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `solicitud`
--
ALTER TABLE `solicitud`
  MODIFY `idSolicitud` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `idUsuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `usuariosgrupo`
--
ALTER TABLE `usuariosgrupo`
  MODIFY `idRelacion` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `amigos`
--
ALTER TABLE `amigos`
  ADD CONSTRAINT `amigoID` FOREIGN KEY (`idAmigo`) REFERENCES `usuario` (`idUsuario`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `usuarioID` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `mensajesamigos`
--
ALTER TABLE `mensajesamigos`
  ADD CONSTRAINT `mensajesamigos_ibfk_1` FOREIGN KEY (`idAmistad`) REFERENCES `amigos` (`idAmistad`);

--
-- Filtros para la tabla `mensajesgrupos`
--
ALTER TABLE `mensajesgrupos`
  ADD CONSTRAINT `mensajesgrupos_ibfk_1` FOREIGN KEY (`idGrupo`) REFERENCES `grupos` (`idGrupo`);

--
-- Filtros para la tabla `usuariosgrupo`
--
ALTER TABLE `usuariosgrupo`
  ADD CONSTRAINT `grupoID` FOREIGN KEY (`idGrupo`) REFERENCES `grupos` (`idGrupo`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `usuariosgrupo_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
