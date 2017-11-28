CREATE TABLE MODULO (
  Nombre varchar (50) NOT NULL PRIMARY KEY,
  alpha NUMERIC,
  betta NUMERIC,
  gamma NUMERIC,
  kappa NUMERIC,
);

CREATE TABLE MEDIDA(
  Nombre varchar (50) NOT NULL,
  idm INTEGER NOT NULL PRIMARY KEY,
  fecha varchar(50) NOT NULL,
  Isc NUMERIC,
  Voc NUMERIC,
  Pmax NUMERIC,
  IPmax NUMERIC,
  VPmax NUMERIC,
  FF NUMERIC,
  VViento NUMERIC,
  VVientoIni NUMERIC,
  VVientoFin NUMERIC,
  DirViento NUMERIC,
  DirVientoIni NUMERIC,
  DirVientoFin NUMERIC,
  HumedadRel  NUMERIC,
  HumedadIni NUMERIC,
  HumedadFin NUMERIC,
  Piranometro NUMERIC,
  PiranometroIni NUMERIC,
  PiranometroFin NUMERIC,
  RTD NUMERIC,
  RTDIni NUMERIC,
  RTDFin NUMERIC,
  CelulaIso NUMERIC,
  CelulaIsoIni NUMERIC,
  CelulaIsoFin NUMERIC,
  FOREIGN KEY Nombre REFERENCES MODULO(NOMBRE)
  ON UPDATE CASCADE
  ON DELETE CASCADE
);

CREATE TABLE PUNTO CURVA (
  idm  INTEGER NOT NULL,
  MEDIDA INTEGER,
  voltaje INTEGER NOT NULL,
  intensidad INTEGER NOT NULL,
  potencia INTEGER NOT NULL,
  FOREIGN KEY idm REFERENCES  MEDIDA(idm)
  ON UPDATE CASCADE
  ON DELETE CASCADE

);