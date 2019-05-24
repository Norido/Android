BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS `MatHang` (
	`MaHang`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	`TenHang`	TEXT,
	`DVT`	TEXT,
	`DonGia`	INTEGER
);
INSERT INTO `MatHang` (MaHang,TenHang,DVT,DonGia) VALUES (1,'IPhone','cai',3000);
INSERT INTO `MatHang` (MaHang,TenHang,DVT,DonGia) VALUES (2,'Sony','cai',5000);
CREATE TABLE IF NOT EXISTS `KhachHang` (
	`MaKH`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`TenKH`	TEXT,
	`DiaChi`	TEXT,
	`DienThoai`	TEXT
);
INSERT INTO `KhachHang` (MaKH,TenKH,DiaChi,DienThoai) VALUES (1,'Giang','Q8','123');
INSERT INTO `KhachHang` (MaKH,TenKH,DiaChi,DienThoai) VALUES (2,'Nho','BinhDuong','234');
INSERT INTO `KhachHang` (MaKH,TenKH,DiaChi,DienThoai) VALUES (3,'Phu','Q9','456');
INSERT INTO `KhachHang` (MaKH,TenKH,DiaChi,DienThoai) VALUES (4,'Khoa','TanBinh','678');
CREATE TABLE IF NOT EXISTS `HoaDon` (
	`SoHD`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	`NgayLap`	TEXT,
	`NgayGiao`	TEXT,
	`MaKH`	INTEGER,
	FOREIGN KEY(`MaKH`) REFERENCES `KhachHang`(`MaKH`)
);
INSERT INTO `HoaDon` (SoHD,NgayLap,NgayGiao,MaKH) VALUES (1,'1/1/2019','1/2/2019',1);
INSERT INTO `HoaDon` (SoHD,NgayLap,NgayGiao,MaKH) VALUES (2,'2/1/2019','2/2/2019',2);
CREATE TABLE IF NOT EXISTS `CTDonHang` (
	`SoHD`	INTEGER,
	`MaHang`	INTEGER,
	`SoLuong`	INTEGER,
	FOREIGN KEY(`SoHD`) REFERENCES `HoaDon`(`SoHD`),
	FOREIGN KEY(`MaHang`) REFERENCES `MatHang`(`MaHang`)
);
INSERT INTO `CTDonHang` (SoHD,MaHang,SoLuong) VALUES (1,1,3);
INSERT INTO `CTDonHang` (SoHD,MaHang,SoLuong) VALUES (2,2,4);
COMMIT;
