select I.inc_name,I.inc_description,I.inc_date, S.sol_date, S.sol_decription ,A.are_name, P.pla_name
from Incidencia I , Solucion S,Area A, Plataforma P
where A.are_id = I.fk_area_id AND P.pla_id = I.fk_plataforma_id AND
 S.sol_id = I.fk_solucion_id OR I.fk_solucion_id = null;


CREATE TABLE Incidencia
(
    inc_id int identity PRIMARY KEY,
    inc_name nvarchar(255) NOT NULL,
    inc_description nVARCHAR(1000)NOT NULL,
    inc_date datetime NOT NULL,
	inc_soldesc nvarchar(1000),
    inc_soldate datetime
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
/*
CREATE TABLE Solucion
(
    sol_id int identity PRIMARY KEY,
    sol_decription nvarchar(1000) NOT NULL,
    sol_date datetime, 

);*/
 
--alter table Incidencia add   inc_solid int not null;
--alter table Incidencia add   inc_soldesc nvarchar(1000) not null;
 
--alter table Incidencia add   fk_solucion_id int;
alter table Incidencia add   fk_plataforma_id int not null;
alter table Incidencia add   fk_area_id int not null;


alter table Incidencia add Foreign key(fk_area_id)
 REFERENCES Area(are_id);

--alter table Incidencia add Foreign key(fk_solucion_id)
 --REFERENCES Solucion(sol_id);


alter table Incidencia add Foreign key(fk_plataforma_id)
 REFERENCES Plataforma(pla_id);
   
 /* alter table Incidencia drop column inc_date;
  alter table Solucion drop column sol_date;
  alter table Solucion add sol_date datetime not null;
  alter table Incidencia add inc_date datetime not null;*/
  
  
  
 insert into Area (are_name) values ('Seguridad de la informacion');
 insert into Area (are_name) values ('Servidores'); 
 insert into Area (are_name) values ('Base de Datos');
 insert into Area (are_name) values ('Telecomunicaciones');
 insert into Area (are_name) values ('Internet Banking');
 insert into Area (are_name) values ('Sistemas IBM');

 
 insert into Plataforma (pla_name) values ('IBS');
 insert into Plataforma (pla_name) values ('AS400');
 insert into Plataforma (pla_name) values ('CDP');
 
 
 
 
 ----
 
-- pla: IBS, AS400, CDP
 
 
 
insert into Incidencia(inc_name,inc_description,inc_date,fk_solucion_id,fk_plataforma_id,fk_area_id)
 values (?,?,?,?,?,?);
 
 
insert into Solucion(sol_decription,sol_date)
 values (?, ?);


 
 
 
CREATE OR REPLACE PROCEDURE m03_getcampaignsall ()

    RETURNS TABLE( inc_name nvarchar, inc_description character varying, inc_date TimeStamp , sol_date TimeStamp,
                sol_decription nvarchar, are_name nvarchar,pla_name nvarchar )
				BEGIN 

SELECT  I.inc_name,I.inc_description,I.inc_date, S.sol_date, S.sol_decription ,A.are_name, P.pla_name
		FROM Incidencia I , Solucion S,Area A, Plataforma P
		WHERE A.are_id = I.fk_area_id AND P.pla_id = I.fk_plataforma_id AND
	 	S.sol_id = I.fk_solucion_id OR I.fk_solucion_id = null;
END;



insert into 
Incidencia(inc_name,inc_description,inc_date,fk_plataforma_id,fk_area_id,fk_solucion_id)
 values (?,?,?,?,?,?)

 
 UPDATE Incidencia
		SET inc_name= ?, 
		inc_description=?, 
		inc_date= ?, 
		fk_plataforma_id= ?, 
		fk_area_id = ?,
		fk_solucion_id= ?
		WHERE inc_id =?;
 
 
 
 
 
 
 
 
 
 
 /*
     editIncidencia (incidencia): Observable<any> {
    console.log(incidencia)
    return this.http.put<any>(endpoint + '/Edit/AddSol',incidencia, httpOptions).pipe(
      tap((incidencia) => console.log(`Incidencia added w/`)),
    );
  }*/
 
 
 
 
 
     --poner en un sp
  /*  String CREATE_INCIDENCIA = "insert into Incidencia(inc_name, inc_description, inc_date, inc_soldate, inc_soldesc, fk_plataforma_id, fk_area_id) values (?,?,?,?,?,?,?);";
    String SELECT_ALL_INCIDENCIAS = "select I.inc_id, I.inc_name,I.inc_description,I.inc_date, I.inc_soldate, I.inc_soldesc ,A.are_id, A.are_name,pla_id, P.pla_name\n" +
                                    "FROM Incidencia I, Area A, Plataforma P\n" +
                                    "WHERE A.are_id = I.fk_area_id AND P.pla_id = I.fk_plataforma_id" ; //poner en un sp
    String READ_INCIDENCIA = "select I.* , A.*,P.*  from Incidencia I, Area A, Plataforma P  \n" +
                             "where I.inc_id = ?  and A.are_id = I.fk_area_id AND P.pla_id = I.fk_plataforma_id";
    String READ_AREA = "select are_id, are_name from Area";
    String READ_PLAT = "select pla_id, pla_name from Plataforma";
    String UPDATE_INCIDENCIA = "UPDATE Incidencia\n" +
                               "SET inc_name= ?, \n" +
                               "inc_description= ?, \n" +
                               "inc_date= ?, \n" +
                               "inc_soldate= ?, \n" +
                               "inc_soldesc= ?, \n" +
                               "fk_plataforma_id= ?, \n" +
                               "fk_area_id = ?\n" +
                               "WHERE inc_id = ?"; */
    
 
 

