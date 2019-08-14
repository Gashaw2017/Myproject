/*create table store(
  id INT AUTO_INCREMENT  PRIMARY KEY,
  shopName VARCHAR(250) NOT NULL,
  category VARCHAR(250) NOT NULL,
  ownerName VARCHAR(250) NOT NULL,
  latitude VARCHAR(250) NOT NULL,
  longitude VARCHAR(250) DEFAULT NULL,
  physicalAddress VARCHAR(250) NOT NULL
);

*/

insert into store(id, shopName, category, ownerName, latitude, longitude)
values(1,'walmart', 'GS', 'Fisho', '47.401738', '-122.19568');

insert into store(id, shopName, category, ownerName, latitude, longitude)
values(2,'castco', 'GS', 'Dave', '47.401738', '-122.19568');

insert into store(id, shopName, category, ownerName, latitude, longitude)
values(3,'kmart', 'GS', 'Dave', '47.401738', '-122.19568');