--CREATE DB

CREATE DATABASE bdconocimiento

--create 
CREATE TABLE Incidencia
(
    inc_id int identity PRIMARY KEY,
    inc_name nvarchar(255) NOT NULL,
    inc_description nVARCHAR(1000)NOT NULL,
    inc_date datetime NOT NULL,
	inc_soldesc nvarchar(1000),
    inc_soldate datetime
);

CREATE TABLE Archivo
(
    
	file_id int identity PRIMARY KEY,
    file_file varbinary(max) NOT NULL,
	file_name  nvarchar (250) NOT NULL,
	file_len   int NOT NULL
    
);

CREATE TABLE Plataforma
(
    pla_id int identity PRIMARY KEY,
    pla_name nvarchar(255) NOT NULL
    
);

CREATE TABLE Area
(
    are_id int identity PRIMARY KEY,
    are_name nvarchar(255) NOT NULL
    
);


--alters

alter table Incidencia add   fk_plataforma_id int not null;

alter table Incidencia add   fk_area_id int not null;

alter table Archivo add   fk_incidencia_id int not null;


alter table Incidencia add Foreign key(fk_area_id)
 REFERENCES Area(are_id);

alter table Incidencia add Foreign key(fk_plataforma_id)
 REFERENCES Plataforma(pla_id);
 
alter table Archivo add Foreign key(fk_incidencia_id)
 REFERENCES Plataforma(inc_id);
 
--Inserts

 
 insert into Area (are_name) values ('Seguridad de la informacion');
 insert into Area (are_name) values ('Servidores'); 
 insert into Area (are_name) values ('Base de Datos');
 insert into Area (are_name) values ('Telecomunicaciones');
 insert into Area (are_name) values ('Internet Banking');
 insert into Area (are_name) values ('Sistemas IBM');

 
 insert into Plataforma (pla_name) values ('IBS');
 insert into Plataforma (pla_name) values ('AS400/Credicard');
 insert into Plataforma (pla_name) values ('Directorio Activo');
 insert into Plataforma (pla_name) values ('Sockets');
 insert into Plataforma (pla_name) values ('Atalla');
 insert into Plataforma (pla_name) values ('Generador de Notificaciones');
 insert into Plataforma (pla_name) values ('Spice IV');
 insert into Plataforma (pla_name) values ('Generador de Reportes');
 insert into Plataforma (pla_name) values ('Módulo BCV');
 insert into Plataforma (pla_name) values ('Branch');
 insert into Plataforma (pla_name) values ('Fideicomiso LA');
 insert into Plataforma (pla_name) values ('Tesorería LA');
 insert into Plataforma (pla_name) values ('COE');
 insert into Plataforma (pla_name) values ('Data Warehouse');
 insert into Plataforma (pla_name) values ('IMAGECOM');
 insert into Plataforma (pla_name) values ('VPN-Ogangi');
 insert into Plataforma (pla_name) values ('VPN-Unplugged');
 insert into Plataforma (pla_name) values ('VPN-Conexión Usuarios Banco');
 insert into Plataforma (pla_name) values ('Enlace CANTV');
 insert into Plataforma (pla_name) values ('Enlace Movistar');
 insert into Plataforma (pla_name) values ('Balanceadores');
 insert into Plataforma (pla_name) values ('Switches Externos');
 insert into Plataforma (pla_name) values ('Core Switch Cisco');
 insert into Plataforma (pla_name) values ('Fortinet Firewall');           
 insert into Plataforma (pla_name) values ('Credicard');
 insert into Plataforma (pla_name) values ('Conexus');
 insert into Plataforma (pla_name) values ('Seniat');
 insert into Plataforma (pla_name) values ('Telcorp');
 insert into Plataforma (pla_name) values ('Nervicom');
 
----SP


-- {CALL  addIncidencia(?,?,?,?,?,?,?)}

CREATE PROCEDURE addIncidencia( @name nVARCHAR(255), @description nVARCHAR(1000), @Idate datetime, 
							    @soldate datetime, @soldesc  nVARCHAR,	@fk_pl int, 
							    @fk_are int	)
AS
BEGIN
SET NOCOUNT ON
 
insert into Incidencia(inc_name, inc_description, inc_date, inc_soldate, inc_soldesc,
			fk_plataforma_id, fk_area_id) values ( @name, @description, @Idate, @soldate, @soldesc, @fk_pl, @fk_are);
 
END



--{CALL getAll()}

CREATE PROCEDURE getAll
AS
BEGIN
SET NOCOUNT ON

SELECT I.inc_id, I.inc_name,I.inc_description,I.inc_date, I.inc_soldate, I.inc_soldesc ,A.are_id, A.are_name,pla_id, P.pla_name, I.fk_archivo_id
FROM Incidencia I, Area A, Plataforma P
WHERE A.are_id = I.fk_area_id AND P.pla_id = I.fk_plataforma_id
ORDER BY  I.inc_id DESC

END

--{CALL getIncidencia(?)}

CREATE PROCEDURE getIncidencia( @id int )
AS
BEGIN
SET NOCOUNT ON

SELECT I.* , A.*,P.*
FROM Incidencia I, Area A, Plataforma P 
WHERE I.inc_id = @id   and A.are_id = I.fk_area_id AND P.pla_id = I.fk_plataforma_id
 
END


--{CALL getArea()}
CREATE PROCEDURE getArea
AS
BEGIN
SET NOCOUNT ON
 
SELECT are_id, are_name
FROM Area
 
END

-- {CALL getPlat()}
CREATE PROCEDURE getPlat
AS
BEGIN
SET NOCOUNT ON

SELECT pla_id, pla_name 
FROM Plataforma
ORDER BY pla_name asc
 
END

-- {CALL updateInc( ?,?,?,?,?,?,?,? )}

CREATE PROCEDURE updateInc( @name nVARCHAR (255), @description nVARCHAR(1000), @Idate datetime, 
							@soldate datetime, @soldesc  nVARCHAR(1000),	@fk_pl int, 
						    @fk_are int, @id int)
AS
BEGIN
SET NOCOUNT ON

UPDATE Incidencia
SET  inc_name= @name, 
     inc_description= @description, 
	 inc_date= @Idate, 
     inc_soldate= @soldate, 
	 inc_soldesc= @soldesc, 
	 fk_plataforma_id= 	@fk_pl,
	 fk_area_id = @fk_are
	 WHERE inc_id = @id 

 
END


-- {CALL deleteInc (?)	}


CREATE PROCEDURE deleteInc(  @id int )
AS
BEGIN
SET NOCOUNT ON

DELETE 
FROM incidencia
WHERE inc_id = @id 

 
END





---------------------Estadisticas----------------------



---SP ESTADISTICAS



-- {CALL getAnual (?)}
CREATE PROCEDURE getAnual( @_year int )
AS
BEGIN
SET NOCOUNT ON

SELECT  DATENAME(MONTH,I.inc_date) as Mes,count(I.inc_id) as Cuenta
FROM Incidencia as I 
WHERE  YEAR(I.inc_date ) = @_year Group By  MONTH(I.inc_date), DATENAME(MONTH,I.inc_date); 
    

END


---- {CALL getByPlataforma (?)}

CREATE PROCEDURE getByPlataforma( @_year int )
AS
BEGIN
SET NOCOUNT ON

SELECT count(I.inc_id) as cuenta, P.pla_name  
FROM Incidencia as I, Plataforma as P 
WHERE YEAR(I.inc_date ) = @_year and I.fk_plataforma_id = P.pla_id
Group By  P.pla_name
Order BY  count(I.inc_id) DESC
    
END

--{CALL getByArea (?)}

CREATE PROCEDURE getByArea( @_year int )
AS
BEGIN
SET NOCOUNT ON

SELECT count(I.inc_id) as cuenta, A.are_name  
FROM Incidencia as I, Area as A 
WHERE YEAR(I.inc_date ) = @_year and  I.fk_area_id = A.are_id
Group By  A.are_name
Order BY  count(I.inc_id) DESC  
    
END



--{CALL getBySol (?)}

CREATE PROCEDURE getBySol( @_year int )
AS
BEGIN
SET NOCOUNT ON

SELECT count(I.inc_id) as cuenta, A.are_name  
FROM Incidencia as I, Area as A 
WHERE I.fk_area_id = A.are_id AND inc_soldesc is not null and  DATEPART(year,inc_date ) = @_year
Group By  A.are_name  
Order BY  count(I.inc_id) DESC
					  
END



--{CALL getByNoSol (?)}

CREATE PROCEDURE getByNoSol( @_year int )
AS
BEGIN
SET NOCOUNT ON

SELECT count(I.inc_id) as no_sol, A.are_name 
FROM Incidencia as I, Area as A 
WHERE I.fk_area_id = A.are_id AND inc_soldesc is  null and  DATEPART(year,inc_date ) = @_year
Group By  A.are_name
Order BY  count(I.inc_id) DESC
END

--{CALL cuentaSol (?)}

CREATE PROCEDURE cuentaSol( @_year int )
AS
BEGIN
SET NOCOUNT ON

SELECT count(I.inc_id) as cuenta
FROM Incidencia as I, Area as A 
WHERE I.fk_area_id = A.are_id AND inc_soldesc is not null and  DATEPART(year,inc_date ) = @_year

END

---------Archivos-------------------------------------------

CREATE TABLE Archivo
(
	arch_id int identity PRIMARY KEY,
    arch_file varbinary(max) NOT NULL,
    arch_name  nvarchar (250) NOT NULL,   
);

alter table Incidencia add fk_archivo_id  int NOT NULL;
alter table Incidencia add Foreign key(fk_archivo_id)
                        REFERENCES Archivo(arch_id);
 
--------------------SP ARCHIVO--------------------------
 

-- {CALL  InsertFile(?,?)} 
 CREATE PROCEDURE InsertFile( @name nVARCHAR(255),  @arch varbinary(max) )
AS
BEGIN
SET NOCOUNT ON

INSERT INTO Archivo(arch_name,arch_file) VALUES  ( @name, @arch);
 
END
 
-- {CALL  selectFile(?)} 
 CREATE PROCEDURE selectFile( @id int )
AS
BEGIN
SET NOCOUNT ON
  
  SELECT arch_file, arch_name 
  FROM Archivo
  WHERE arch_id= @id ;
 
END
   
 -- {CALL  selectFileName(?)} 
 CREATE PROCEDURE selectFileName(  @name nVARCHAR(255) )
AS
BEGIN
SET NOCOUNT ON
  
  SELECT arch_id 
  FROM Archivo 
  WHERE arch_name like @name ; 
   
END
   
   
-- {CALL insertIncFile(?,?)}
 
 CREATE PROCEDURE insertIncFile( @id int ,@name nVARCHAR (255) )
AS
BEGIN
SET NOCOUNT ON

UPDATE Incidencia 
SET fk_archivo_id = @id
WHERE inc_name = @name;
 
END
 
 -- {CALL updateIncFile(?,?)}
 
 CREATE PROCEDURE updateIncFile( @id int, @idInc int)
AS
BEGIN
SET NOCOUNT ON

UPDATE Incidencia 
SET fk_archivo_id = @id
WHERE inc_id = @idInc;
 
END
 
 
 
 
 -- {CALL deleteFile(?)	}


CREATE PROCEDURE deleteFile(  @id int )
AS
BEGIN
SET NOCOUNT ON

DELETE
FROM Archivo 
WHERE arch_id = @id
 
END

 
 
 
 
 
 
 
 
 
/*delete from Archivo  where arch_id = 3
DBCC CHECKIDENT ('Archivo', RESEED, 2)  */

--alter table Archivo add arch_name  nvarchar (250) NOT NULL;


/*insert into Archivo (arch_file) 
values (0x433A5C55736572735C4D696368656C65204C616E7A615C446F63756D656E)


select convert (varbinary,'C:\Users\Michele Lanza\Documents\UCABInstructivo para la realizacion del informe final de pasantia 2014.pdf')

INSERT INTO Archivo(arch_file)
SELECT * FROM
OPENROWSET (BULK N'C:\Users\Michele Lanza\Documents\UCAB\testing.txt', SINGLE_BLOB) as r

select * from Archivo where arch_id = 1

SELECT CONVERT(varchar(max), arch_file) AS TextCol
FROM Archivo*/


