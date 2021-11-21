--Authors
insert into LibraryMGKIT.dbo.Authors (ID,LName,FName,SName,BirthDay,Gender) values (N'DBA53807-9E52-40DE-91F8-5DE9211252F3',N'Толстой',N'Лев',N'Николаевич','1862.09.09 0:00:00',N'м')
insert into LibraryMGKIT.dbo.Authors (ID,LName,FName,BirthDay,Gender) values (N'FB904AF2-8BE5-455D-9001-9FFDE4CFCD92',N'Таненбаум',N'Эндрю','1944.03.16 0:00:00',N'м')
insert into LibraryMGKIT.dbo.Authors (ID,LName,FName,BirthDay,Gender) values (N'31B4FF79-C54E-463A-9ADB-04EE38BF9031',N'Макконнелл',N'Стив','1962.09.03 0:00:00',N'м')
insert into LibraryMGKIT.dbo.Authors (ID,LName,FName,BirthDay,Gender) values (N'90405C9B-E342-4D78-AE55-8D549A698150',N'Мартин',N'Роберт','1952.12.05 0:00:00',N'м')
--genres
insert into LibraryMGKIT.dbo.Genres (ID,Name) values(N'944A06B5-42AC-4E9B-9B17-289A77C5BE5E',N'Интернет и технологии')
insert into LibraryMGKIT.dbo.Genres (ID,Name) values(N'C00E9D87-32E9-4E4E-981F-05BE1843CEC8',N'Роман-эпопея')
--Publishers
insert into LibraryMGKIT.dbo.Publishers (ID,Name) values(N'68D9B9F7-4F4B-484E-B9B0-74404EC5CD2A',N'Питер')
insert into LibraryMGKIT.dbo.Publishers (ID,Name) values(N'1CAE58B0-DB2A-4DDB-A102-EA9BBEE61018',N'Азбука')
--Books
insert into LibraryMGKIT.dbo.Books (ID,Title,PublisherID,AuthorID,GenreID,AgeLimit,Count,YearEdition) 
VALUES (N'A32E3FEA-73C9-4083-93F1-70989049D417',N'Чистый код',N'68D9B9F7-4F4B-484E-B9B0-74404EC5CD2A',N'90405C9B-E342-4D78-AE55-8D549A698150',N'944A06B5-42AC-4E9B-9B17-289A77C5BE5E',12,6,2019)

insert into LibraryMGKIT.dbo.Books (ID,Title,PublisherID,AuthorID,GenreID,AgeLimit,Count,YearEdition) 
VALUES (N'03992B0D-0BA8-435C-A5B4-FBD848CE60E8',N'Компьютерные сети',N'68D9B9F7-4F4B-484E-B9B0-74404EC5CD2A',N'FB904AF2-8BE5-455D-9001-9FFDE4CFCD92',N'944A06B5-42AC-4E9B-9B17-289A77C5BE5E',12,13,2017)

insert into LibraryMGKIT.dbo.Books (ID,Title,PublisherID,AuthorID,GenreID,AgeLimit,Count,YearEdition) 
VALUES (N'4617A213-7991-4717-976A-802615F19CA2',N'Архитектура компьютера',N'68D9B9F7-4F4B-484E-B9B0-74404EC5CD2A',N'FB904AF2-8BE5-455D-9001-9FFDE4CFCD92',N'944A06B5-42AC-4E9B-9B17-289A77C5BE5E',12,7,2017)

insert into LibraryMGKIT.dbo.Books (ID,Title,PublisherID,AuthorID,GenreID,AgeLimit,Count,YearEdition) 
VALUES (N'A98D1518-2BB8-4CDC-BA19-8CA678D29E11',N'Война и мир',N'1CAE58B0-DB2A-4DDB-A102-EA9BBEE61018',N'DBA53807-9E52-40DE-91F8-5DE9211252F3',N'C00E9D87-32E9-4E4E-981F-05BE1843CEC8',16,3,2011)