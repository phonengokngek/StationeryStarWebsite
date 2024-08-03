USE master;
GO

IF EXISTS (SELECT * FROM sys.databases WHERE name='StationeryStarDB')
	DROP DATABASE [StationeryStarDB];
GO

CREATE DATABASE [StationeryStarDB];
GO

USE [StationeryStarDB];
GO


CREATE TRIGGER trg_CheckDuplicateUser
ON Users
INSTEAD OF INSERT
AS
BEGIN
    DECLARE @errorMessage NVARCHAR(255)

    -- Check if both email and phone exist
    IF EXISTS (SELECT 1 FROM Users u JOIN inserted i ON u.email = i.email AND u.phone = i.phone)
    BEGIN
        SET @errorMessage = 'Both Email and Phone have existed'
        RAISERROR (@errorMessage, 16, 1)
        ROLLBACK TRANSACTION
        RETURN
    END

    -- Check if email exists
    IF EXISTS (SELECT 1 FROM Users u JOIN inserted i ON u.email = i.email)
    BEGIN
        SET @errorMessage = 'Email has existed'
        RAISERROR (@errorMessage, 16, 1)
        ROLLBACK TRANSACTION
        RETURN
    END
    
    -- Check if phone exists
    IF EXISTS (SELECT 1 FROM Users u JOIN inserted i ON u.phone = i.phone)
    BEGIN
        SET @errorMessage = 'Phone has existed'
        RAISERROR (@errorMessage, 16, 1)
        ROLLBACK TRANSACTION
        RETURN
    END

    -- Insert new user
    INSERT INTO Users ([fullName], [roleID], [password], [email], [phone], [externalID], [externalMethod], [status])
    SELECT [fullName], [roleID], [password], [email], [phone], [externalID], [externalMethod], [status]
    FROM inserted
END


CREATE TABLE [dbo].[Users] (
    [userID] INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
    [fullName] NVARCHAR(255) NOT NULL,
    [roleID] NVARCHAR(50) NOT NULL DEFAULT 'US',
	[password] NVARCHAR(50) NULL,
    [email] NVARCHAR(50) NULL UNIQUE,
    [phone] NVARCHAR(50) NOT NULL UNIQUE,
	[externalID] NVARCHAR(50) NULL,
	[externalMethod] NVARCHAR(50) NULL,
	[status] BIT NOT NULL DEFAULT 1
);

INSERT INTO [dbo].[Users] ([fullName], [roleID], [password], [email], [phone], [externalID], [externalMethod], [status]) VALUES 
(N'The Phone', 'AD', NULL, 'dinhthephong11@gmail.com', '9999888822', '1228499841480987', 'Facebook', 1),
(N'The Phong', 'US', NULL, 'dinhthephong1412@gmail.com', '1234567890' ,'112076652417437140344', 'Google', 1),
(N'Phoneee', 'US', '3', 'dinhthephong03689@gmail.com', '1122334455', NULL, NULL, 1),
(N'dinhthephong', 'AD', '5', 'dinhthephongse02@gmail.com', '3344556677', NULL, NULL, 1),
(N'phonengokngek', 'US', '6', 'phongdtse183785@fpt.edu.vn', '6666666666', NULL, NULL, 1),
(N'thephong', 'US', '7', 'dinhthephongse03@gmail.com', '7777777777', NULL, NULL, 1),
(N'phong', 'US', '8', 'dinhthephong160102@gmail.com', '8888888888', NULL, NULL, 1);

CREATE TABLE [dbo].[tokenForgetPassword] (
    [id] INT IDENTITY(1,1) PRIMARY KEY,
    [token] VARCHAR(255) NOT NULL,
    [expiryTime] DATETIME NOT NULL,
	[isUsed] BIT NOT NULL,
	[userID] INT NOT NULL,
	FOREIGN KEY (userID) REFERENCES [Users](userID)
);

CREATE TABLE [dbo].[Types] (
    [typeID] INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
    [typeName] NVARCHAR(50) NOT NULL,
	[status] BIT NOT NULL DEFAULT 1
);

INSERT INTO [dbo].[Types] ([typeName]) VALUES
(N'calculator'),
(N'covers - file shelves'),
(N'notesbook'),
(N'paper'),
(N'paper clips'),
(N'pen'),
(N'scissors - paper knife');

CREATE TABLE [dbo].[Products] (
    [productID] INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
    [name] NVARCHAR(255) NOT NULL,
    [typeID] INT NOT NULL,
    [image] NVARCHAR(255) NOT NULL,
    [price] DECIMAL(18, 3) NOT NULL, 
    [quantity] INT NOT NULL,
    [status] BIT NOT NULL DEFAULT 1,
    FOREIGN KEY ([typeID]) REFERENCES [Types]([typeID])
);


INSERT INTO [dbo].[Products] ([name], [typeID], [image], [price], [quantity], [status]) VALUES
(N'MAY TINH KHOA HOC FLEXIO FX680VN TU THIET KE THEO CA TINH', 1, './assets/img/product/calculator/product-01.jpg', 592.800, 100, 1),
(N'MAY TINH KHOA HOC FLEXOFFICE FLEXIO FX590VN', 1, './assets/img/product/calculator/product-02.jpg', 455.200, 200, 1),
(N'MAY TINH FLEXOFFICE FLEXIO CAL-05P', 1, './assets/img/product/calculator/product-03.jpg', 210.000, 150, 1),
(N'MAY TINH FLEXOFFICE FLEXIO CAL-05P', 1, './assets/img/product/calculator/product-04.jpg', 182.000, 300, 1),
(N'MAY TINH FLEXOFFICE FLEXIO CAL-02S', 1, './assets/img/product/calculator/product-05.jpg', 125.000, 100, 1),
(N'MAY TINH FLEXOFFICE FLEXIO CAL-06S', 1, './assets/img/product/calculator/product-06.jpg', 168.000, 200, 1),
(N'MAY TINH KHOA HOC THIEN LONG FLEXIO FX680VN PLUS', 1, './assets/img/product/calculator/product-07.jpg', 495.200, 150, 1),
(N'MAY TINH KHOA HOC THIEN LONG FLEXIO FX799VN', 1, './assets/img/product/calculator/product-08.jpg', 535.200, 300, 1);

INSERT INTO [dbo].[Products] ([name], [typeID], [image], [price], [quantity], [status]) VALUES
(N'BIA NUT NAME CARD CLEAR BAG THIEN LONG FLEXOFFICE', 2, './assets/img/product/covers - file shelves/product-09.jpg', 35.000, 100, 1),
(N'BIA DUNG HO SO 80 LA SUC CHUA 400 TO A4 80 GSM THIEN LONG FO-DB010', 2, './assets/img/product/covers - file shelves/product-10.jpg', 40.000, 200, 1),
(N'BIA CONG THIEN LONG FLEXOFFICE 70F4 FO-BC016', 2, './assets/img/product/covers - file shelves/product-11.jpg', 45.000, 150, 1),
(N'BIA HO SO MAU PASTEL 20 LA NHUA PP THIEN LONG FLEXOFFICE FO-DB007', 2, './assets/img/product/covers - file shelves/product-12.jpg', 50.000, 300, 1),
(N'BIA NHAN ORING FO-ORB04 - XANH DAM', 2, './assets/img/product/covers - file shelves/product-13.jpg', 55.000, 100, 1),
(N'BIA HOC SINH 30 LA A4 THIEN LONG DB-002 - SUC CHUA 150 TO A4', 2, './assets/img/product/covers - file shelves/product-14.jpg', 60.000, 200, 1),
(N'BIA NUT MAU PASTEL F4 THIEN LONG CBF-003 - SUC CHUA 125 TO A4', 2, './assets/img/product/covers - file shelves/product-15.jpg', 65.000, 150, 1),
(N'BIA HOC SINH 40 LA A4 THIEN LONG DB-003 - SUC CHUA 200 TO A4', 2, './assets/img/product/covers - file shelves/product-16.jpg', 70.000, 300, 1);

INSERT INTO [dbo].[Products] ([name], [typeID], [image], [price], [quantity], [status]) VALUES
(N'SO LO XO DUONG KE NGANG A5 THIEN LONG', 3, './assets/img/product/notesbook/product-17.jpg', 45.000, 100, 1),
(N'SO LO XO DUONG KE NGANG B5 THIEN LONG', 3, './assets/img/product/notesbook/product-18.jpg', 30.000, 200, 1),
(N'SO LO XO DOC A5 160T MB-004', 3, './assets/img/product/notesbook/product-19.jpg', 55.000, 150, 1),
(N'SO LO XO DUONG KE CARO B5 THIEN LONG MB-016- MAU NGAU NHEN', 3, './assets/img/product/notesbook/product-20.jpg', 60.000, 300, 1),
(N'SO LO XO DUONG KE NGANG B5 THIEN LONG MB-012- MAU NGAU NHEN', 3, './assets/img/product/notesbook/product-21.jpg', 65.000, 100, 1),
(N'SO LO XO DUONG KE CHAM BI B5 THIEN LONG MB-018- MAU NGAU NHEN', 3, './assets/img/product/notesbook/product-22.jpg', 60.000, 200, 1),
(N'SO LO XO DUONG KE NGANG A5 THIEN LONG MB-011 - MAU NGAU NHEN', 3, './assets/img/product/notesbook/product-23.jpg', 35.000, 150, 1),
(N'SO LO XO DUONG KE CHAM BI A5 THIEN LONG MB-017 - MAU NGAU', 3, './assets/img/product/notesbook/product-24.jpg', 50.000, 300, 1);

INSERT INTO [dbo].[Products] ([name], [typeID], [image], [price], [quantity], [status]) VALUES
(N'COMBO GIAY IN VAN PHONG IK COPY 80 GSM TINH THONG', 4, './assets/img/product/paper/product-25.jpg', 901.000, 100, 1),
(N'COMBO GIAY IN VAN PHONG IK SIEU TIET KIEM', 4, './assets/img/product/paper/product-26.jpg', 771.000, 200, 1),
(N'COMBO 10 REAM GIAY A4 80 GSM IK PLUS-02 (500 TO) - HANG NHAP KHAU', 4, './assets/img/product/paper/product-27.jpg', 765.500, 150, 1),
(N'COMBO 10 REAM GIAY A4 80 GSM IK NATURAL-02 (500 TO) - HANG NHAP KHAU', 4, './assets/img/product/paper/product-28.jpg', 722.500, 300, 1),
(N'COMBO 5 REAM GIAY A3 80 GSM IK COPY (500 TO) - HANG NHAP', 4, './assets/img/product/paper/product-29.jpg', 805.000, 100, 1),
(N'GIAY GHI CHU - GIAY NOTES NEON THIEN LONG - NHIEU KICH CO', 4, './assets/img/product/paper/product-30.jpg', 10.800, 200, 1),
(N'REAM GIAY A4 80 GSM IK NATURAL-02 (500 TO) - HANG NHAP KHAU INDONESIA', 4, './assets/img/product/paper/product-31.jpg', 76.500, 150, 1),
(N'REAM GIAY A4 80 GSM IK PLUS-02 (500 TO) - HANG NHAP KHAU INDONESIA', 4, './assets/img/product/paper/product-32.jpg', 150.000, 300, 1);

INSERT INTO [dbo].[Products] ([name], [typeID], [image], [price], [quantity], [status]) VALUES
(N'KEP BUOM FLEXOFFICE 25MM FO-DC03', 5, './assets/img/product/paper clips/product-33.jpg', 15.000, 100, 1),
(N'KEP BUOM MAU FLEXOFFICE 25MM FO-DCC03', 5, './assets/img/product/paper clips/product-34.jpg', 49.000, 200, 1),
(N'KEP BUOM MAU FLEXOFFICE 19MM FO-DCC02 (FS)', 5, './assets/img/product/paper clips/product-35.jpg', 65.000, 150, 1),
(N'KEP BUOM MAU FLEXOFFICE 32MM FO-DCC04 (FS)', 5, './assets/img/product/paper clips/product-36.jpg', 57.000, 300, 1),
(N'KEP BUOM MAU FLEXOFFICE 41MM FO-DCC05 (FS)', 5, './assets/img/product/paper clips/product-37.jpg', 83.000, 100, 1),
(N'KEP BUOM FLEXOFFICE 19MM FO-DC02 (FS)', 5, './assets/img/product/paper clips/product-38.jpg', 9.000, 200, 1),
(N'KEP BUOM FLEXOFFICE 15MM FO-DC01', 5, './assets/img/product/paper clips/product-39.jpg', 8.500, 150, 1),
(N'HOP 12 KEP BUOM CONG THAI HOC THIEN LONG - ERGONOMIC BINDER CLIPS', 5, './assets/img/product/paper clips/product-40.jpg', 9.500, 300, 1);

INSERT INTO [dbo].[Products] ([name], [typeID], [image], [price], [quantity], [status]) VALUES
(N'BUT BI PARKER IM SPECIAL EDITION SUBMERGE BLUE - THEP KHONG GI - NGOI 0.07MM', 6, './assets/img/product/pen/product-41.jpg', 2195.000, 100, 1),
(N'BUT BI PARKER IM SPECIAL EDITION SUBMERGE RED - THEP KHONG GI NGOI 0.07MM', 6, './assets/img/product/pen/product-42.jpg', 2195.000, 200, 1),
(N'BUT LONG DAU THIEN LONG PM-04', 6, './assets/img/product/pen/product-43.jpg', 12.500, 150, 1),
(N'BUT XOA THIEN LONG CP-02', 6, './assets/img/product/pen/product-44.jpg', 22.000, 300, 1),
(N'HOP BUT DA QUANG MAU PASTEL THIEN LONG PAZTO HL-016 - BUT DA QUANG', 6, './assets/img/product/pen/product-45.jpg', 5.500, 100, 1),
(N'BUT CHI GO 2B THIEN LONG GP-018', 6, './assets/img/product/pen/product-46.jpg', 4.500, 200, 1),
(N'BUT DA QUANG THIEN LONG HL-03 (FS)', 6, './assets/img/product/pen/product-47.jpg', 6.500, 150, 1),
(N'BUT LONG BANG THIEN LONG WB-03', 6, './assets/img/product/pen/product-48.jpg', 14.000, 300, 1);

INSERT INTO [dbo].[Products] ([name], [typeID], [image], [price], [quantity], [status]) VALUES
(N'DAO ROC GIAY FLEXOFFICE FO-KN01', 7, './assets/img/product/scissors - paper knife/product-49.jpg', 23.500, 100, 1),
(N'KEO DA NANG THIEN LONG SC-020', 7, './assets/img/product/scissors - paper knife/product-50.jpg', 24.000, 200, 1),
(N'LUOI DAO ROC GIAY FLEXOFFICE FO-BL01', 7, './assets/img/product/scissors - paper knife/product-51.jpg', 45.000, 150, 1),
(N'KEO FLEXOFFICE FO-SC01', 7, './assets/img/product/scissors - paper knife/product-52.jpg', 32.000, 300, 1),
(N'DAO ROC GIAY FLEXOFFICE FO-KN01B', 7, './assets/img/product/scissors - paper knife/product-53.jpg', 25.500, 100, 1),
(N'DAO ROC GIAY FLEXOFFICE FO-KN04B', 7, './assets/img/product/scissors - paper knife/product-54.jpg', 26.000, 200, 1),
(N'KEO FLEXOFFICE FO-SC03', 7, './assets/img/product/scissors - paper knife/product-55.jpg', 45.000, 150, 1),
(N'KEO VAN PHONG CHIU LUC THIEN LONG', 7, './assets/img/product/scissors - paper knife/product-56.jpg', 60.000, 300, 1);




CREATE TABLE [dbo].[Order] (
    [orderID] INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
    [userID] INT NOT NULL,
    [total] DECIMAL(18, 3) NOT NULL,
    [date] DATETIME NOT NULL,
	[status] BIT NOT NULL DEFAULT 1,
	FOREIGN KEY ([userID]) REFERENCES [Users]([userID]),
);

CREATE TABLE [dbo].[OrderDetail] (
    [orderID] INT NOT NULL,
    [productID] INT NOT NULL,
    [price] DECIMAL(18, 3) NOT NULL, 
    [quantity] INT NOT NULL,
    [status] BIT NOT NULL DEFAULT 1,
    PRIMARY KEY ([orderID], [productID]), 
    FOREIGN KEY ([orderID]) REFERENCES [Order]([orderID]),
    FOREIGN KEY ([productID]) REFERENCES [Products]([productID])
);