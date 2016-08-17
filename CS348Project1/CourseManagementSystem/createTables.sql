create table Student(sname varchar(30),
                     sid integer not null,
                     primary key(sid)
                     );

create table Faculty(fname varchar(30),
                     fid integer not null,
                     primary key(fid)
                     );

create table Department(dname varchar(20),
                        depid integer not null,
                        headname varchar(30),
                        primary key(depid)
                        );

create table Work(fid integer,
                  depid integer,
                  primary key(fid, depid),
                  foreign key(fid) references Faculty(fid),
                  foreign key(depid) references Department(depid)
                  );

create table Course(cname varchar(25),
                    semester varchar(10),
                    year smallint,
                    meetat varchar(20),
                    room varchar(10),
                    fid integer not null,
                    cid integer not null,
                    primary key(cid),
                    foreign key(fid) references Faculty(fid)
                    );

create table Assign(cname varchar(25),
		    sid integer,
		    cid integer,
		    primary key(sid, cid),
		    foreign key(sid) references Student(sid),
		    foreign key(cid) references Course(cid)
		    );

create table Evaluation(eid integer,
                        evaluationType varchar(15),
                        fid integer,
                        cid integer,
                        weightage integer,
                        duedate date,
                        meetingroom varchar(10),
                        primary key(eid),
                        foreign key(fid) references Faculty(fid),
                        foreign key(cid) references Course(cid)
                        );

create table Grade(eid integer,
		   sid integer,
		   cid integer,
		   grade integer,
		   primary key(eid, sid),
		   foreign key(eid) references Evaluation(eid),
		   foreign key(sid) references Student(sid),
		   foreign key(cid) references Course(cid)
		   );


